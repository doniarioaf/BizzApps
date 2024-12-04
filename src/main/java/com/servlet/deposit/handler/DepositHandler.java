package com.servlet.deposit.handler;

import com.servlet.customer.mapper.QueryCustomerList;
import com.servlet.deposit.entity.BodyDeposit;
import com.servlet.deposit.entity.Deposit;
import com.servlet.deposit.entity.DepositDataNotJoin;
import com.servlet.deposit.mapper.QueryCalculateAmountDeposit;
import com.servlet.deposit.repo.DepositRepo;
import com.servlet.deposit.service.DepositService;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
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
public class DepositHandler implements DepositService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepositRepo repo;

    @Autowired
    private PurchaseReceiveService purchaseReceiveService;

    @Override
    public List<DepositDataNotJoin> getDepositNotJoinByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
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
}
