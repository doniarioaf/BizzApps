package com.servlet.customer.handler;

import com.servlet.customer.entity.BodyCustomer;
import com.servlet.customer.entity.Customer;
import com.servlet.customer.entity.CustomerData;
import com.servlet.customer.entity.ListCustomerData;
import com.servlet.customer.mapper.QueryCustomerDetail;
import com.servlet.customer.mapper.QueryCustomerList;
import com.servlet.customer.repo.CustomerRepo;
import com.servlet.customer.service.CustomerService;
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
            customer.setIsdelete(false);
            customer.setCreateddate(ts);
            customer.setCreatedby(iduser);
            idsave = repo.saveAndFlush(customer).getId();
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
//            customer.setIdcompany(idcompany);
//            customer.setIdbranch(idbranch);
            customer.setNama(body.getNama());
            customer.setAlias(body.getAlias());
            customer.setBank(body.getBank());
            customer.setBanknumber(body.getBanknumber());
            customer.setAccountbankname(body.getAccountbankname());
            customer.setAddress(body.getAddress());
            customer.setModifieddate(ts);
            customer.setModifiedby(iduser);
            idsave = repo.saveAndFlush(customer).getId();
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
            Customer customer = repo.getById(iduser);
            customer.setIsdelete(true);
            customer.setDeletedate(ts);
            customer.setDeleteby(iduser);
            idsave = repo.saveAndFlush(customer).getId();
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
}
