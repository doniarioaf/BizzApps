package com.servlet.product.handler;

import com.servlet.product.entity.*;
import com.servlet.product.mapper.QueryProductDetail;
import com.servlet.product.mapper.QueryProductList;
import com.servlet.product.repo.ProductRepo;
import com.servlet.product.service.ProductService;
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
public class ProductHandler implements ProductService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepo repo;

    /**
     * sengaja, semua cuma query by idcompany
     */

    @Override
    public List<ListProductData> getListAll(Long idcompany, Long idbranch) {

        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryProductList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryProductList(), queryParameters);
    }

    @Override
    public ProductDataDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryProductDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<ProductDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryProductDetail(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyProduct body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Product product = new Product();
            product.setIdcompany(idcompany);
            product.setIdbranch(idbranch);
            product.setNama(body.getNama());
            product.setSku(body.getSku());
            product.setIsdelete(false);
            product.setCreateddate(ts);
            product.setCreatedby(iduser);
            idsave = repo.saveAndFlush(product).getId();
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyProduct body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Product product = repo.getById(id);
            product.setNama(body.getNama());
            product.setSku(body.getSku());
            product.setIsdelete(false);
            product.setModifieddate(ts);
            product.setModifiedby(iduser);
            idsave = repo.saveAndFlush(product).getId();
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
            Product product = repo.getById(id);
            product.setIsdelete(true);
            product.setDeletedate(ts);
            product.setDeleteby(iduser);
            idsave = repo.saveAndFlush(product).getId();
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
    public List<ListProductData> getListAll(Long idcompany, Long idbranch, ParamProduct param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryProductList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        if(param.getListIdProduct() != null && !param.getListIdProduct().equals("")){
            sqlBuilder.append(" and data.id in ("+param.getListIdProduct()+") ");
        }
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryProductList(), queryParameters);
    }
}
