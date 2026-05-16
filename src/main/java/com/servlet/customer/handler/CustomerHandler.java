package com.servlet.customer.handler;

import com.servlet.customer.entity.*;
import com.servlet.customer.mapper.QueryCustomerDetail;
import com.servlet.customer.mapper.QueryCustomerForReport;
import com.servlet.customer.mapper.QueryCustomerList;
import com.servlet.customer.mapper.QueryDistinctCustomerGrup;
import com.servlet.customer.repo.CustomerRepo;
import com.servlet.customer.service.CustomerService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerHandler implements CustomerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Override
    public List<ListCustomerData> getListAll(Long idcompany, Long idbranch) {
        /**
         * sengaja cuma query by idcompany
         */

        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCustomerList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCustomerList(), queryParameters);
    }

    @Override
    public CustomerData getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCustomerDetail().schema());
        sqlBuilder.append(" where data.id = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id};
        List<CustomerData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCustomerDetail(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyCustomer body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Customer customer = new Customer();
            customer.setIdcompany(idcompany);
            customer.setIdbranch(idbranch);
            customer.setNama(body.getNama());
            customer.setAlias(body.getAlias());
            customer.setBank(body.getBank());
            customer.setBanknumber(body.getBanknumber());
            customer.setAccountbankname(body.getAccountbankname());
            customer.setAddress(body.getAddress());
            String grup = "";
            if(body.getGrup() != null){
                grup = body.getGrup();
            }
            customer.setGrup(grup);
            customer.setGrupcode(grup.trim().replaceAll(" ","").toUpperCase());
            customer.setPhonenumber(body.getPhonenumber());
            customer.setAttention(body.getAttention());
            customer.setCity(body.getCity());
            customer.setIsdelete(false);
            customer.setCreateddate(ts);
            customer.setCreatedby(iduser);
            idsave = repo.saveAndFlush(customer).getId();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD","Customer",customer.toString(),"","",ts);

        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyCustomer body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Customer customer = repo.getById(id);
            String databefore = customer.toString();
            customer.setNama(body.getNama());
            customer.setAlias(body.getAlias());
            customer.setBank(body.getBank());
            customer.setBanknumber(body.getBanknumber());
            customer.setAccountbankname(body.getAccountbankname());
            customer.setAddress(body.getAddress());
            String grup = "";
            if(body.getGrup() != null){
                grup = body.getGrup();
            }
            customer.setGrup(grup);
            customer.setGrupcode(grup.trim().replaceAll(" ","").toUpperCase());
            customer.setPhonenumber(body.getPhonenumber());
            customer.setAttention(body.getAttention());
            customer.setCity(body.getCity());
            customer.setModifieddate(ts);
            customer.setModifiedby(iduser);
            idsave = repo.saveAndFlush(customer).getId();
            String dataafter = customer.toString();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT","Customer","",dataafter,databefore,ts);
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData delete(Long id,Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Customer customer = repo.getById(id);
            customer.setIsdelete(true);
            customer.setDeletedate(ts);
            customer.setDeleteby(iduser);
            idsave = repo.saveAndFlush(customer).getId();
            historyAppsService.saveHistory(customer.getIdcompany(),customer.getIdbranch(),iduser,"DELETE","Customer",customer.toString(),"","",ts);
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public List<CustomerGrup> getListCustomerGrup(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDistinctCustomerGrup().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false and data.grupcode notnull ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDistinctCustomerGrup(), queryParameters);
    }

    @Override
    public List<CustomerForReport> getListCustomerForReport(Long idcompany, Long idbranch, String listId) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCustomerForReport().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        if(listId != null && !listId.equals("")){
            sqlBuilder.append(" and data.id in ("+listId+") ");
        }
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCustomerForReport(), queryParameters);
    }
}
