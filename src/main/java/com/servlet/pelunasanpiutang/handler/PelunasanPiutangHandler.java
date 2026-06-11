package com.servlet.pelunasanpiutang.handler;

import com.servlet.customer.service.CustomerService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.mapper.QueryDataPelunasanPiutang;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.pelunasanpiutang.entity.*;
import com.servlet.pelunasanpiutang.mapper.*;
import com.servlet.pelunasanpiutang.repo.PelunasanPiutangItemRepo;
import com.servlet.pelunasanpiutang.repo.PelunasanPiutangRepo;
import com.servlet.pelunasanpiutang.service.PelunasanPiutangService;
import com.servlet.purchasereceive.mapper.QueryItemsNotJoin;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PelunasanPiutangHandler implements PelunasanPiutangService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PelunasanPiutangRepo repo;

    @Autowired
    private PelunasanPiutangItemRepo repoItem;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RunningNumberService runningNumberService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "PELUNASANPIUTANG";

    @Override
    public PelunasanPiutangTemplate getTemplate(Long idcompany, Long idbranch) {
        PelunasanPiutangTemplate data = new PelunasanPiutangTemplate();
        data.setCustomerGrupOpt(customerService.getListCustomerGrup(idcompany,idbranch));
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPelunasanPiutang body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANPIUTANG, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                PelunasanPiutang table = new PelunasanPiutang();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setKurs(body.getKurs());
                table.setTotalpembayaran(calculateTotalPembayaran(body.getItems()).doubleValue());
                table.setIsdelete(false);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = repo.saveAndFlush(table).getId();

                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,idsave, body.getItems());
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,mixData,"","",ts);
                }else{
                    repo.deleteById(idsave);
                    repoItem.deleteAllDetailByIdPelunasanPiutang(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANPIUTANG);
                    validations.add(validationsItems.get(0));
                }

            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANPIUTANG);
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyPelunasanPiutang body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                PelunasanPiutang table = repo.getById(id);
                List<PelunasanPiutangItemNotJoin> listitems = getListItemNotJoin(id);
                String dataBef = "header = "+table.toString()+" | Items = "+listitems.toString();

                table.setDate(new Date(body.getDate()));
                table.setKurs(body.getKurs());
                table.setTotalpembayaran(calculateTotalPembayaran(body.getItems()).doubleValue());
                table.setModifieddate(ts);
                table.setModifiedby(iduser);
                idsave = repo.saveAndFlush(table).getId();

                for(PelunasanPiutangItemNotJoin val : listitems){
                    Double pembayaran = val.getPembayaran() + val.getBiayabebanudangmati() + val.getBiayabank();
                    invoiceService.updateOustandingTambah(val.getIdinvoice(), pembayaran);
                }

                repoItem.deleteAllDetailByIdPelunasanPiutang(id);

                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,idsave, body.getItems());
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",mixData,dataBef,ts);
                }else{
                    validations.add(validationsItems.get(0));
                }

            }catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                PelunasanPiutang table = repo.getById(id);
                List<PelunasanPiutangItemNotJoin> listitems = getListItemNotJoin(id);

                table.setIsdelete(true);
                table.setDeletedate(ts);
                table.setDeleteby(iduser);
                idsave = repo.saveAndFlush(table).getId();

                for(PelunasanPiutangItemNotJoin val : listitems){
                    Double pembayaran = val.getPembayaran() + val.getBiayabebanudangmati() + val.getBiayabank();
                    invoiceService.updateOustandingTambah(val.getIdinvoice(), pembayaran);
                }

                historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE",namaMenu,table.toString(),"","",ts);

            }catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public List<PelunasanPiutangList> getPelunasanPiutangList(Long idcompany, Long idbranch, FilterParamPelunasanPiutang param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanPiutangList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }
        if(param.getNamaCust() != null && !param.getNamaCust().equals("")){
            String namaCust = param.getNamaCust().toLowerCase();
            sqlBuilder.append(" and lower(cust.nama) like '%"+namaCust+"%' ");
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanPiutangList(), queryParameters);
    }

    @Override
    public PelunasanPiutangDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanPiutangDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PelunasanPiutangDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanPiutangDetail(), queryParameters);
        if(list != null && list.size() > 0){
            PelunasanPiutangDetail data = list.get(0);
            data.setItems(getListItem(id));
            return data;
        }
        return null;
    }

    @Override
    public List<ReportPelunasanPiutang> getReportPelunasanPiutang(Long idcompany, Long idbranch, FilterParamPelunasanPiutang param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataReportPelunasanPiutang().schema());
        sqlBuilder.append(" where pp.idcompany = ? and pp.idbranch = ? and pp.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and pp.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and pp.date <= '"+dt.toString()+"'");
        }
        if(param.getListIdInvoice() != null && !param.getListIdInvoice().equals("")){
            sqlBuilder.append(" and data.idinvoice in ("+param.getListIdInvoice()+") ");
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataReportPelunasanPiutang(), queryParameters);
    }

    @Override
    public PelunasanPiutangItemJoinHeader getPelunasanPiutangItemByIdInvoice(Long idcompany, Long idbranch, Long idinvoice) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanPiutangItemJoinHeader().schema());
        sqlBuilder.append(" where data.idinvoice = ?  ");
        sqlBuilder.append(" and pelunasan.idcompany = "+idcompany+" and pelunasan.idbranch = "+idbranch+" and pelunasan.isdelete = false  ");
        final Object[] queryParameters = new Object[] {idinvoice};
        List<PelunasanPiutangItemJoinHeader> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanPiutangItemJoinHeader(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    private BigDecimal calculateTotalPembayaran(BodyPelunasanPiutangItem[] items){
        BigDecimal total = BigDecimal.ZERO;
        if(items != null && items.length > 0){
            for(BodyPelunasanPiutangItem val:items){
                BigDecimal pembayaran = val.getPembayaran() != null?BigDecimal.valueOf(val.getPembayaran().doubleValue()).setScale(2, RoundingMode.DOWN):BigDecimal.ZERO;
                total = total.add(pembayaran);
            }
        }
        return total;
    }
    private List<PelunasanPiutangItemDetail> getListItem(Long idpelunasanpiutang){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanPiutangItemDetail().schema());
        sqlBuilder.append(" where data.idpelunasanpiutang = ?  ");
        sqlBuilder.append(" ORDER BY data.idinvoice desc ");
        final Object[] queryParameters = new Object[] {idpelunasanpiutang};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanPiutangItemDetail(), queryParameters);
    }
    private List<PelunasanPiutangItemNotJoin> getListItemNotJoin(Long idpelunasanpiutang){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanPiutangItemNotJoin().schema());
        sqlBuilder.append(" where data.idpelunasanpiutang = ?  ");
        sqlBuilder.append(" ORDER BY data.idinvoice desc ");
        final Object[] queryParameters = new Object[] {idpelunasanpiutang};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanPiutangItemNotJoin(), queryParameters);
    }

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch, Long idpelunasanpiutang, BodyPelunasanPiutangItem[] items){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<BodyPelunasanPiutangItem> listitem = new ArrayList<>();
        try{
            if(items.length > 0){
                for(BodyPelunasanPiutangItem val:items){
                    PelunasanPiutangItemPK pk = new PelunasanPiutangItemPK();
                    pk.setIdpelunasanpiutang(idpelunasanpiutang);
                    pk.setIdinvoice(val.getIdinvoice());
                    PelunasanPiutangItem table = new PelunasanPiutangItem();
                    table.setPelunasanPiutangItemPK(pk);
                    table.setBiayabebanudangmati(val.getBiayabebanudangmati());
                    table.setBiayabank(val.getBiayabank());
                    table.setPembayaran(val.getPembayaran());
                    table.setMetodepembayaran(val.getMetodepembayaran());
                    repoItem.saveAndFlush(table);
                    listitem.add(val);
                }

                for(BodyPelunasanPiutangItem val:items){
                    Double pembayaran = val.getPembayaran() + val.getBiayabebanudangmati() + val.getBiayabank();
                    invoiceService.updateOustandingKurang(val.getIdinvoice(), pembayaran);
                }
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }

        String dataItems = listitem.toString();
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }
}
