package com.servlet.pelunasanhutang.handler;

import com.servlet.cargo.entity.ParamCargoSearch;
import com.servlet.cargo.service.CargoService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.pelunasanhutang.entity.*;
import com.servlet.pelunasanhutang.mapper.QueryPelunasanHutangDataDetail;
import com.servlet.pelunasanhutang.mapper.QueryPelunasanHutangDataList;
import com.servlet.pelunasanhutang.mapper.QueryPelunasanHutangDataNotJoin;
import com.servlet.pelunasanhutang.repo.PelunasanHutangRepo;
import com.servlet.pelunasanhutang.service.PelunasanHutangService;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PelunasanHutangHandler implements PelunasanHutangService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PelunasanHutangRepo repo;

    @Autowired
    private PurchaseReceiveService purchaseReceiveService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private RunningNumberService runningNumberService;

    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "PELUNASANHUTANG";

    @Override
    public List<PelunasanHutangDataList> getList(Long idcompany, Long idbranch, FilterParamPelunasanHutang param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanHutangDataList().schema());
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
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanHutangDataList(), queryParameters);
    }

    @Override
    public HutangDataList getListHutang(Long idcompany, Long idbranch, FilterParamPelunasanHutang param) {
        HutangDataList data = new HutangDataList();
        if(param.getCategory().equals("SUPPLIER") || param.getCategory().equals("ALL")){
            data.setListPR(purchaseReceiveService.getListForPelunasanHutang(idcompany,idbranch,param));
        }

        if(param.getCategory().equals("CARGO") || param.getCategory().equals("UPI") || param.getCategory().equals("ALL")){
            ParamCargoSearch paramcargo = new ParamCargoSearch();
            paramcargo.setStatus(param.getStatus());
            paramcargo.setCategory(param.getCategory());
            data.setListCargo(cargoService.getListCargoPelunasanHutang(idcompany,idbranch,paramcargo));
        }
        return data;
    }

    @Override
    public DetailHutangPR getDetailHutangPR(Long idcompany, Long idbranch, Long idpurchasereceive) {
        DetailHutangPR data = new DetailHutangPR();
        data.setDetailPR(purchaseReceiveService.getDetail(idcompany,idbranch,idpurchasereceive));
        data.setListpembayaran(getListPembayaranHutangByIDPR(idcompany,idbranch,idpurchasereceive));
        return data;
    }

    @Override
    public DetailHutangCargo getDetailHutangCargo(Long idcompany, Long idbranch, Long idcargo) {
        DetailHutangCargo data = new DetailHutangCargo();
        data.setDetailCargo(cargoService.getDetail(idcargo,idcompany,idbranch));
        data.setListpembayaran(getListPembayaranHutangByIDCargo(idcompany,idbranch,idcargo));
        return data;
    }

    @Override
    public PelunasanHutangDataDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanHutangDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PelunasanHutangDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanHutangDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPelunasanHutang body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANHUTANG, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                PelunasanHutang table = new PelunasanHutang();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setIdpurchasereceive(body.getIdpurchasereceive());
                table.setIdcargo(body.getIdcargo());
                table.setDate(new Date(body.getDate()));
                table.setAmount(body.getAmount());
                table.setNotes(body.getNotes());
                table.setIsdelete(false);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = repo.saveAndFlush(table).getId();

                if(body.getIdpurchasereceive() != null){
                    purchaseReceiveService.updateOustandingKurang(body.getIdpurchasereceive(), body.getAmount());
                }
                if(body.getIdcargo() != null){
                    cargoService.updateOustandingKurang(body.getIdcargo(), body.getAmount());
                }

                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,table.toString(),"","",ts);
            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANHUTANG);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyPelunasanHutang body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                PelunasanHutang table = repo.getById(id);
                String bef = table.toString();
                if(table.getIdpurchasereceive() != null){
                    purchaseReceiveService.updateOustandingTambah(table.getIdpurchasereceive(), table.getAmount());
                }
                if(table.getIdcargo() != null){
                    cargoService.updateOustandingTambah(table.getIdcargo(), table.getAmount());
                }
                table.setDate(new Date(body.getDate()));
                table.setAmount(body.getAmount());
                table.setNotes(body.getNotes());
                table.setModifieddate(ts);
                table.setModifiedby(iduser);
                idsave = repo.saveAndFlush(table).getId();
                String after = table.toString();

                if(table.getIdpurchasereceive() != null){
                    purchaseReceiveService.updateOustandingKurang(table.getIdpurchasereceive(), body.getAmount());
                }
                if(table.getIdcargo() != null){
                    cargoService.updateOustandingKurang(table.getIdcargo(), body.getAmount());
                }

                historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",after,bef,ts);
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
                PelunasanHutang table = repo.getById(id);
                if(table.getIdpurchasereceive() != null){
                    purchaseReceiveService.updateOustandingTambah(table.getIdpurchasereceive(), table.getAmount());
                }
                if(table.getIdcargo() != null){
                    purchaseReceiveService.updateOustandingTambah(table.getIdcargo(), table.getAmount());
                }
                table.setIsdelete(true);
                table.setDeletedate(ts);
                table.setDeleteby(iduser);
                idsave = repo.saveAndFlush(table).getId();
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

    private List<PelunasanHutangDataNotJoin> getListPembayaranHutangByIDPR(Long idcompany, Long idbranch, Long idpurchasereceive) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanHutangDataNotJoin().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idpurchasereceive,idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanHutangDataNotJoin(), queryParameters);
    }

    private List<PelunasanHutangDataNotJoin> getListPembayaranHutangByIDCargo(Long idcompany, Long idbranch, Long idcargo) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPelunasanHutangDataNotJoin().schema());
        sqlBuilder.append(" where data.idcargo = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcargo,idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPelunasanHutangDataNotJoin(), queryParameters);
    }
}
