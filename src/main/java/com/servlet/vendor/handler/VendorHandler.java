package com.servlet.vendor.handler;

import com.servlet.product.entity.Product;
import com.servlet.product.mapper.QueryProductList;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.entity.BodyVendor;
import com.servlet.vendor.entity.ListVendorData;
import com.servlet.vendor.entity.Vendor;
import com.servlet.vendor.entity.VendorData;
import com.servlet.vendor.mapper.QueryListVendor;
import com.servlet.vendor.mapper.QueryVendorDetail;
import com.servlet.vendor.repo.VendorRepo;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendorHandler implements VendorService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private VendorRepo repo;

    /**
     * sengaja, semua cuma query by idcompany
     */

    @Override
    public List<ListVendorData> getListAll(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListVendor().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListVendor(), queryParameters);
    }

    @Override
    public VendorData getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryVendorDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<VendorData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryVendorDetail(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyVendor body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Vendor vendor = new Vendor();
            vendor.setIdcompany(idcompany);
            vendor.setIdbranch(idbranch);
            vendor.setNama(body.getNama());
            vendor.setAlias(body.getAlias());
            vendor.setType(body.getType());
            vendor.setBank(body.getBank());
            vendor.setAccountnobank(body.getAccountnobank());
            vendor.setAccountnamebank(body.getAccountnamebank());
            vendor.setIsdelete(false);
            vendor.setCreateddate(ts);
            vendor.setCreatedby(iduser);
            idsave = repo.saveAndFlush(vendor).getId();
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyVendor body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Vendor vendor = repo.getById(id);
            vendor.setNama(body.getNama());
            vendor.setAlias(body.getAlias());
            vendor.setType(body.getType());
            vendor.setBank(body.getBank());
            vendor.setAccountnobank(body.getAccountnobank());
            vendor.setAccountnamebank(body.getAccountnamebank());
            vendor.setModifieddate(ts);
            vendor.setModifiedby(iduser);
            idsave = repo.saveAndFlush(vendor).getId();
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
    public ReturnData delete(Long id, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Vendor vendor = repo.getById(id);
            vendor.setIsdelete(true);
            vendor.setDeletedate(ts);
            vendor.setDeleteby(iduser);
            idsave = repo.saveAndFlush(vendor).getId();
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
