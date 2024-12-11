package com.servlet.deposit.handler;

import com.servlet.deposit.entity.*;
import com.servlet.deposit.mapper.QueryCalculateAmountDeposit;
import com.servlet.deposit.mapper.QueryDetailData;
import com.servlet.deposit.mapper.QueryListData;
import com.servlet.deposit.repo.DepositRepo;
import com.servlet.deposit.service.DepositService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.purchasereceive.entity.PurchaseReceiveDataList;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepositHandler implements DepositService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepositRepo repo;

    @Autowired
    private PurchaseReceiveService purchaseReceiveService;
    @Autowired
    private VendorService vendorService;

    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "Deposit";

    @Override
    public List<DepositDataNotJoin> getDepositNotJoinByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        return null;
    }

    @Override
    public List<DepositList> getList(Long idcompany, Long idbranch, ParamList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListData().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.depositdate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.depositdate <= '"+dt.toString()+"'");
        }

        if(param.getIdvendor() != null){
            sqlBuilder.append(" and data.idvendor = "+param.getIdvendor().longValue()+" ");
        }
        sqlBuilder.append(" order by  data.depositdate desc ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListData(), queryParameters);
    }

    @Override
    public DepositDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDetailData().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<DepositDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDetailData(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Double calculateAmountByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany,idvendor};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountDeposit(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public Double calculateSisaDepositByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        double summaryDeposit = calculateAmountByIdVendor(idcompany,idbranch,idvendor).doubleValue();
        double summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendor(idcompany,idbranch,idvendor).doubleValue();
        double hasil = summaryDeposit - summarySetorPurchaseReceive;
        return hasil;
    }

    private Double calculateAmountByIdVendorNotInIDDeposit(Long id,Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false and data.id not in ("+id+")");
        final Object[] queryParameters = new Object[] {idcompany,idvendor};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountDeposit(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDeposit body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            Deposit table = new Deposit();
            table.setIdcompany(idcompany);
            table.setIdbranch(idbranch);
            table.setIdvendor(body.getIdvendor());
            table.setAmount(body.getAmount());
            table.setDepositdate(new Date(body.getDepositdate()));
            table.setCreateddate(ts);
            table.setCreatedby(iduser);
            idsave = repo.saveAndFlush(table).getId();

            String data = table.toString();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,data,"","",ts);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyDeposit body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());

        try {
            Deposit table = repo.getById(id);
            double summaryDeposit = calculateAmountByIdVendorNotInIDDeposit(id,idcompany,idbranch, table.getIdvendor()).doubleValue() + body.getAmount().doubleValue();
            double summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendor(idcompany,idbranch,table.getIdvendor()).doubleValue();
            if(summarySetorPurchaseReceive > summaryDeposit){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.TOTAL_SETOR_GREATER_THAN, "Total Setor Lebih besar dari total deposit");
                validations.add(msg);
            }
            if(validations.size() == 0) {
                PurchaseReceiveDataList check = purchaseReceiveService.checkIdDeposit(id);
                if(check != null){
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PURCHASERECEIVE, "deposit ini terpasang pada purchase receive ("+check.getNodocument()+") ");
                    validations.add(msg);
                }
            }
            if(validations.size() == 0) {
                String dataBefore = table.toString();
                table.setAmount(body.getAmount());
                table.setModifieddate(ts);
                table.setModifiedby(iduser);
                idsave = repo.saveAndFlush(table).getId();

                String data = table.toString();
                historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, "", data, dataBefore, ts);
            }
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
    public ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            Deposit table = repo.getById(id);
            double summaryDeposit = calculateAmountByIdVendorNotInIDDeposit(id,idcompany,idbranch, table.getIdvendor()).doubleValue();
            double summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendor(idcompany,idbranch,table.getIdvendor()).doubleValue();
            if(summarySetorPurchaseReceive > summaryDeposit){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.TOTAL_SETOR_GREATER_THAN, "Total Setor Lebih besar dari total deposit");
                validations.add(msg);
            }

            if(validations.size() == 0) {
                PurchaseReceiveDataList check = purchaseReceiveService.checkIdDeposit(id);
                if(check != null){
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PURCHASERECEIVE, "deposit ini terpasang pada purchase receive ("+check.getNodocument()+") ");
                    validations.add(msg);
                }
            }

            if(validations.size() == 0) {
                table.setIsdelete(true);
                table.setDeletedate(ts);
                table.setDeleteby(iduser);
                idsave = repo.saveAndFlush(table).getId();

                String data = table.toString();
                historyAppsService.saveHistory(idcompany, idbranch, iduser, "DELETE", namaMenu, data, "", "", ts);
            }
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
    public DepositTemplate getTemplate(Long idcompany, Long idbranch) {
        DepositTemplate template = new DepositTemplate();
        template.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch));
        return template;
    }

    @Override
    public ReturnData deleteRollBack(Long id) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        try {
            repo.deleteById(id);
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
    public Double calculateSaldoDepositByIdVendorAndBeforeDateCreated(Long idcompany, Long idbranch, Long idvendor, Long date) {
        double summaryDeposit = summaryCalculateSaldoDepositByIdVendorAndBeforeDateCreated(idcompany,idbranch,idvendor,date).doubleValue();
        double summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendorAndCreatedDate(idcompany,idbranch,idvendor,date).doubleValue();
        double hasil = summaryDeposit - summarySetorPurchaseReceive;
        return hasil;
    }

    private Double summaryCalculateSaldoDepositByIdVendorAndBeforeDateCreated(Long idcompany, Long idbranch, Long idvendor, Long date){
        Timestamp dt = new Timestamp(date);
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false and data.createddate < '"+dt+"' ");
        final Object[] queryParameters = new Object[] {idcompany,idvendor};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountDeposit(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }
}
