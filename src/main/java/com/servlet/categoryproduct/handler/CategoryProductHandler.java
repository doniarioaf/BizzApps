package com.servlet.categoryproduct.handler;

import com.servlet.categoryproduct.entity.*;
import com.servlet.categoryproduct.mapper.QueryDataDetail;
import com.servlet.categoryproduct.mapper.QueryDataList;
import com.servlet.categoryproduct.repo.CategoryProductRepo;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.customer.entity.Customer;
import com.servlet.customer.mapper.QueryCustomerList;
import com.servlet.customer.repo.CustomerRepo;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryProductHandler implements CategoryProductService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryProductRepo repo;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private VendorService vendorService;
    protected final String namaMenu = "CategoryProduct";

    /**
     * sengaja cuma query by idcompany
     */

    @Override
    public List<CategoryProductList> getListAll(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public CategoryProductDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<CategoryProductDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyCategoryProduct body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            CategoryProduct table = new CategoryProduct();
            table.setIdcompany(idcompany);
            table.setIdbranch(idbranch);
            table.setNama(body.getNama());
            table.setSize(body.getSize());
            table.setWeightfromingram(body.getWeightfromingram());
            table.setWeighttoingram(body.getWeighttoingram());
            table.setJumlahitemsperkoli(body.getJumlahitemsperkoli());
            table.setIsdelete(false);
            table.setCreateddate(ts);
            table.setCreatedby(iduser);
            idsave = repo.saveAndFlush(table).getId();
            String data = table.toString();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,data,"","",ts);

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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyCategoryProduct body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            CategoryProduct table = repo.getById(id);
            String databefore = table.toString();
            table.setNama(body.getNama());
            table.setSize(body.getSize());
            table.setWeightfromingram(body.getWeightfromingram());
            table.setWeighttoingram(body.getWeighttoingram());
            table.setJumlahitemsperkoli(body.getJumlahitemsperkoli());
            table.setModifieddate(ts);
            table.setModifiedby(iduser);
            idsave = repo.saveAndFlush(table).getId();
            String dataafter = table.toString();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",dataafter,databefore,ts);

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
            CategoryProduct table = repo.getById(id);
            table.setIsdelete(true);
            table.setDeletedate(ts);
            table.setDeleteby(iduser);
            idsave = repo.saveAndFlush(table).getId();
            String data = table.toString();
            historyAppsService.saveHistory(table.getIdcompany(),table.getIdbranch(),iduser,"DELETE",namaMenu,data,"","",ts);

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
    public List<CategoryProductList> getDataForTemplate(Long idcompany, Long idbranch, ParamTemplate param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        if(param != null){
            if(param.getMenu() != null){
                if(param.getMenu().equals("PURCHASE_RECEIVE")){
                    sqlBuilder.append(" and data.id not in ("+vendorService.queryIdVendorCategoryProductNotInclud(idcompany,idbranch,param.getIdvendor())+") ");
                }
            }
        }
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }
}
