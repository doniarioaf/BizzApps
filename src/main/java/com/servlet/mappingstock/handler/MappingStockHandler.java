package com.servlet.mappingstock.handler;

import com.servlet.categoryproduct.entity.CategoryProduct;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.mappingstock.entity.*;
import com.servlet.mappingstock.mapper.QueryDataCategoryProductID;
import com.servlet.mappingstock.mapper.QueryDataDetail;
import com.servlet.mappingstock.mapper.QueryDataList;
import com.servlet.mappingstock.mapper.QueryGetCategoryProductID;
import com.servlet.mappingstock.repo.MappingStockRepo;
import com.servlet.mappingstock.service.MappingStockService;
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
public class MappingStockHandler implements MappingStockService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MappingStockRepo repo;

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "MappingStock";
    @Override
    public List<MappingStockList> getListAll(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ?  ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public MappingStockDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.categoryproductid = ? ");
        final Object[] queryParameters = new Object[] {idcompany,id};
        List<MappingStockDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyMappingStock body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            boolean check = repo.existsById(body.getCategoryproductid());
            if(check){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CATEGORYPRODUCT_ALREADY_MAPPING,"Category Product Sudah di mapping");
                validations.add(msg);
            }else{
                MappingStock table = new MappingStock();
                table.setCategoryproductid(body.getCategoryproductid());
                table.setCategoryproductidmapping(body.getCategoryproductidmapping());
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = repo.saveAndFlush(table).getCategoryproductid();
                String data = table.toString();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,data,"","",ts);
            }



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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyMappingStock body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            MappingStock table = repo.getById(id);
            String databefore = table.toString();
            table.setCategoryproductidmapping(body.getCategoryproductidmapping());
            table.setModifieddate(ts);
            table.setModifiedby(iduser);
            idsave = repo.saveAndFlush(table).getCategoryproductid();
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
            MappingStock table = repo.getById(id);
            String data = table.toString();

            /**
             * delete ini , bukan soft delete. karena data mapping ini tidak dipakai dimana2, cuma untuk patokan aja. jika product A maka akan menjadi apa.
             */
            repo.deleteById(id);

            historyAppsService.saveHistory(table.getIdcompany(), table.getIdbranch(),iduser,"DELETE",namaMenu,data,"","",ts);

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
    public MappingStockTemplateData getTemplate(Long idcompany, Long idbranch) {
        MappingStockTemplateData data = new MappingStockTemplateData();
        data.setCategoryProductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,null));
        return data;
    }

    @Override
    public MappingStockCategoryID getDetailMapping(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataCategoryProductID().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.categoryproductid = ? ");
        final Object[] queryParameters = new Object[] {idcompany,id};
        List<MappingStockCategoryID> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataCategoryProductID(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public String getSelectidCategory(Long idcompany, Long idbranch) {
        return "select ms.categoryproductid from mapping_stock as ms where ms.idcompany = "+idcompany;
    }

    @Override
    public List<Long> getCategoryProducts(Long idcompany, Long idbranch, ParamSearchMappingStock param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryGetCategoryProductID().schema());
        sqlBuilder.append(" where data.idcompany = ?  ");
        if(param.getListIdCategoryProduct() != null && !param.getListIdCategoryProduct().equals("")){
            sqlBuilder.append(" and data.categoryproductidmapping in ("+param.getListIdCategoryProduct()+") ");
        }
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryGetCategoryProductID(), queryParameters);
    }
}
