package com.servlet.invoice.handler;

import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.entity.*;
import com.servlet.invoice.mapper.*;
import com.servlet.invoice.repo.InvoiceRepo;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.packinglist.entity.ParamDropDownPackingList;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.parameterclient.entity.ValueParameter;
import com.servlet.parameterclient.service.ParameterClientService;
import com.servlet.pelunasanpiutang.entity.FilterParamPelunasanPiutang;
import com.servlet.pelunasanpiutang.entity.PelunasanPiutangItemJoinHeader;
import com.servlet.pelunasanpiutang.service.PelunasanPiutangService;
import com.servlet.purchasereceive.entity.PurchaseReceive;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.*;
import com.servlet.stockitems.entity.ReportKartuStock;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InvoiceHandler implements InvoiceService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InvoiceRepo repo;
    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private RunningNumberService runningNumberService;
    @Autowired
    private PackingListService packingListService;
    @Autowired
    private ParameterClientService parameterClientService;

    @Autowired
    private PelunasanPiutangService pelunasanPiutangService;
    @Autowired
    private UserAppsService userAppsService;

    protected final String namaMenu = "Invoice";

    @Override
    public List<InvoiceDataList> getList(Long idcompany, Long idbranch, ParamSearchInvoice param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public InvoiceTemplate getTemplate(Long idcompany, Long idbranch) {
        InvoiceTemplate data = new InvoiceTemplate();
        ParamDropDownPackingList paramPL = new ParamDropDownPackingList();
        paramPL.setMenu("INVOICE");
        data.setPackingListOpt(packingListService.getDropDown(idcompany,idbranch,paramPL));
        return data;
    }

    @Override
    public InvoiceDataDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<InvoiceDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            InvoiceDataDetail det = list.get(0);
            det.setPackinglist(packingListService.getDetail(det.getIdpackinglist(), idcompany,idbranch));

            return det;
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyInvoice body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        HashMap<String,Integer> hash = GlobalFunc.getMonthYearDate(body.getDate());
        int year = hash.get("year");
        int month = hash.get("month");
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumberWithYearMonth(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE, ts,year,month);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                Invoice table = new Invoice();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setKurs(body.getKurs());
                table.setIdpackinglist(body.getIdpackinglist());
                table.setPhone(body.getPhone());
                table.setAmount(body.getTotalamount());
                table.setOutstanding(body.getTotalamount());
                table.setIsdelete(false);
                table.setCreatedby(iduser);
                table.setCreateddate(ts);
                idsave = repo.saveAndFlush(table).getId();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,table.toString(),"","",ts);
            }catch (Exception e) {
                e.printStackTrace();
                runningNumberService.rollBackDocNumberWithYearMonth(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE,year,month);
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyInvoice body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        PelunasanPiutangItemJoinHeader pp = pelunasanPiutangService.getPelunasanPiutangItemByIdInvoice(idcompany,idbranch,id);
        if(pp != null){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PELUNASANPIUTANG,"invoice ini terpasang pada Pelunasan Piutang ("+pp.getNodocument()+")");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                Invoice table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue()){
                    String before = table.toString();
                    table.setKurs(body.getKurs());
                    table.setPhone(body.getPhone());
                    table.setModifiedby(iduser);
                    table.setModifieddate(ts);
                    idsave = repo.saveAndFlush(table).getId();
                    String after = table.toString();
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",after,before,ts);
                }
            }catch (Exception e) {
                e.printStackTrace();
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData updateRecalculate(Long id, Long idcompany, Long idbranch, Long iduser, BodyInvoice body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        PelunasanPiutangItemJoinHeader pp = pelunasanPiutangService.getPelunasanPiutangItemByIdInvoice(idcompany,idbranch,id);
        if(pp != null){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PELUNASANPIUTANG,"invoice ini terpasang pada Pelunasan Piutang ("+pp.getNodocument()+")");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                Invoice table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && table.isIspackinglistupdate()){
                    String before = table.toString();
                    table.setAmount(body.getTotalamount());
                    table.setOutstanding(body.getTotalamount());
                    table.setIspackinglistupdate(false);
                    table.setModifiedby(iduser);
                    table.setModifieddate(ts);
                    idsave = repo.saveAndFlush(table).getId();
                    String after = table.toString();
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT_RECALC",namaMenu,"",after,before,ts);
                }
            }catch (Exception e) {
                e.printStackTrace();
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData updateChangeDataPackingList(Long id, Boolean flag) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;

        if(validations.size() == 0) {
            try{
                Invoice table = repo.getById(id);
                table.setIspackinglistupdate(flag);
                idsave = repo.saveAndFlush(table).getId();
            }catch (Exception e) {
                e.printStackTrace();
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
        PelunasanPiutangItemJoinHeader pp = pelunasanPiutangService.getPelunasanPiutangItemByIdInvoice(idcompany,idbranch,id);
        if(pp != null){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PELUNASANPIUTANG,"invoice ini terpasang pada Pelunasan Piutang ("+pp.getNodocument()+")");
            validations.add(msg);
        }

        if(validations.size() == 0) {
            try{
                Invoice table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue()){
                    table.setIsdelete(true);
                    table.setDeleteby(iduser);
                    table.setDeletedate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE",namaMenu,table.toString(),"","",ts);
                }
            }catch (Exception e) {
                e.printStackTrace();
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public PrintInvoice getPrintDataByID(Long id, Long idcompany, Long idbranch,Long iduser,ParamPrintInvoice paramPrintInvoice) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintInvoice().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PrintInvoice> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintInvoice(), queryParameters);
        if(list != null && list.size() > 0){
            ValueParameter param = parameterClientService.getValueByParamName(idcompany,idbranch,"COMPANYNAME","TEXT");
            ValueParameter paramAddress1 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS1","TEXT");
            ValueParameter paramAddress2 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS2","TEXT");
            ValueParameter paramAddress3 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS3","TEXT");
            ValueParameter parambankComp = parameterClientService.getValueByParamName(idcompany,idbranch,"BANK","TEXT");
            ValueParameter parambankAccnoComp = parameterClientService.getValueByParamName(idcompany,idbranch,"BANKACCNO","TEXT");
            ValueParameter parambankAccnameComp = parameterClientService.getValueByParamName(idcompany,idbranch,"BANKACCNAME","TEXT");
            ValueParameter paramCountryOfOrigin = parameterClientService.getValueByParamName(idcompany,idbranch,"COUNTRYOFORIGIN","TEXT");

            PrintInvoice det = list.get(0);
            det.setPackinglist(packingListService.getDetail(det.getIdpackinglist(), idcompany,idbranch));
            det.setCountryOfOrigin(paramCountryOfOrigin.getStrValue());
            det.setCompanyName(param.getStrValue());
            det.setAddress1(paramAddress1.getStrValue());
            det.setAddress2(paramAddress2.getStrValue());
            det.setAddress3(paramAddress3.getStrValue());
            det.setBankCompany(parambankComp.getStrValue());
            det.setBankAccNoCompany(parambankAccnoComp.getStrValue());
            det.setBankAccNameCompany(parambankAccnameComp.getStrValue());
            HashMap mapParamPrint = new HashMap();
            mapParamPrint.put("data-id",id);
            det.setCountPrint(historyAppsService.countByActionAndMenuParam(idcompany,idbranch,"DOWNLOADPDF",namaMenu,mapParamPrint));
//            det.setCountPrint(historyAppsService.countByActionAndMenu(idcompany,idbranch,"DOWNLOADPDF",namaMenu));
            det.setCountEdit(historyAppsService.countByActionAndMenu(idcompany,idbranch,"EDIT",namaMenu));
            if(iduser != null) {
                UserListData user = userAppsService.getUserByID(iduser);
                String namaUser = "";
                if (user != null) {
                    namaUser = user.getNama();
                }
                det.setNamaUser(namaUser);
            }

            if(paramPrintInvoice != null){
                if(paramPrintInvoice.getNamaMenu() != null){
                    if(paramPrintInvoice.getNamaMenu().equals("PRINT")){
//                        catatDownload(id,idcompany,idbranch,iduser);
                    }
                }
            }

            return det;
        }
        return null;
    }

    @Override
    public InvoiceDataList getDataByIdPackingList(Long idcompany, Long idbranch, Long idpackinglist) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idpackinglist = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[] {idpackinglist,idcompany,idbranch};
        List<InvoiceDataList> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<InvoiceDataPelunasanPiutang> getListInvoicePelunasanPiutang(Long idcompany, Long idbranch, FilterParamPelunasanPiutang param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataPelunasanPiutang().schema());
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
        if(!param.getCustomergrup().equals("ALL")){
            sqlBuilder.append(" and cust.grupcode = '"+param.getCustomergrup()+"' ");
        }
        if(param.getStatus().equals("LUNAS")){
            sqlBuilder.append(" and data.outstanding < 1 ");
        }else if(param.getStatus().equals("BELUMLUNAS")){
            sqlBuilder.append(" and data.outstanding >= 1 ");
        }
        sqlBuilder.append(" ORDER BY data.id desc ");

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataPelunasanPiutang(), queryParameters);
    }

    @Override
    public List<InvoiceDataPelunasanPiutang> getListInvoicePelunasanPiutangByListID(Long idcompany, Long idbranch, String listIdInvoice) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataPelunasanPiutang().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        sqlBuilder.append(" and data.id in ("+listIdInvoice+") ");
        sqlBuilder.append(" ORDER BY data.id desc ");

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataPelunasanPiutang(), queryParameters);
    }

    @Override
    public ReturnData updateOustandingTambah(Long id, Double bayar) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        try {
            Invoice table = repo.getById(id);
            double outstanding = table.getOutstanding().doubleValue() + bayar.doubleValue();
            table.setOutstanding(outstanding);
            idsave = repo.saveAndFlush(table).getId();

        }catch (Exception e) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }

        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData updateOustandingKurang(Long id, Double bayar) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        try {
            Invoice table = repo.getById(id);
            double outstanding = table.getOutstanding().doubleValue() - bayar.doubleValue();
            table.setOutstanding(outstanding);
            idsave = repo.saveAndFlush(table).getId();

        }catch (Exception e) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }

        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData catatDownload(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            Invoice table = repo.getById(id);
            historyAppsService.saveHistory(table.getIdcompany(),table.getIdbranch(),iduser,"DOWNLOADPDF",namaMenu,id.toString(),"","",ts);
        }catch (Exception e) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }

        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public List<InvoiceDataReportPiutang> getListInvoiceReportPiutang(Long idcompany, Long idbranch, ParamSearchInvoice param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryInvoiceReportPiutang().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }
        if(param.getIdcustomer() != null){
            sqlBuilder.append(" and pl.idcustomer = "+param.getIdcustomer()+" ");
        }
        if(param.getListGroup() != null && !param.getListGroup().equals("")){
            sqlBuilder.append(" and cust.grupcode in ("+param.getListGroup()+") ");
        }
        if(param.getStatus() != null && !param.getStatus().equals("")){
            if(param.getStatus().equals("LUNAS")){
                sqlBuilder.append(" and data.outstanding < 1 ");
            }else if(param.getStatus().equals("BELUMLUNAS")){
                sqlBuilder.append(" and data.outstanding >= 1 ");
            }
        }
        sqlBuilder.append(" order by cust.grupcode, data.date ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryInvoiceReportPiutang(), queryParameters);
    }

    @Override
    public List<InvoiceDataReportPelunasanPiutang> getListInvoiceReportPelunasanPiutang(Long idcompany, Long idbranch, ParamSearchInvoice param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryInvoiceReportPelunasanPiutang().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }
        if(param.getIdcustomer() != null){
            sqlBuilder.append(" and pl.idcustomer = "+param.getIdcustomer()+" ");
        }
        if(param.getListGroup() != null && !param.getListGroup().equals("")){
            sqlBuilder.append(" and cust.grupcode in ("+param.getListGroup()+") ");
        }
        if(param.getStatus() != null && !param.getStatus().equals("")){
            if(param.getStatus().equals("LUNAS")){
                sqlBuilder.append(" and data.outstanding < 1 ");
            }else if(param.getStatus().equals("BELUMLUNAS")){
                sqlBuilder.append(" and data.outstanding >= 1 ");
            }
        }
        sqlBuilder.append(" order by cust.grupcode, data.date ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryInvoiceReportPelunasanPiutang(), queryParameters);
    }

    @Override
    public List<ReportKartuStock> getListInvoiceReportKartuStock(Long idcompany, Long idbranch, ParamSearchInvoice param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryInvoiceReportKartuStock().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }
        if(param.getListIdProduct() != null && !param.getListIdProduct().equals("")){
            sqlBuilder.append(" and pli.idproduct in ("+param.getListIdProduct()+") ");
        }
        if(param.getListIdCategoryProduct() != null && !param.getListIdCategoryProduct().equals("")){
            sqlBuilder.append(" and pli.idcategoryproduct in ("+param.getListIdCategoryProduct()+") ");
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryInvoiceReportKartuStock(), queryParameters);
    }

}
