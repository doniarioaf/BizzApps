package com.servlet.stockadjusment.handler;

import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.mappingstock.entity.MappingStockCategoryID;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.PurchaseReceiveItemsNotJoin;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.stockadjusment.entity.*;
import com.servlet.stockadjusment.mapper.QueryDataDetail;
import com.servlet.stockadjusment.mapper.QueryDataItemsJoin;
import com.servlet.stockadjusment.mapper.QueryDataItemsNotJoin;
import com.servlet.stockadjusment.mapper.QueryDataList;
import com.servlet.stockadjusment.repo.StockAdjusmentItemRepo;
import com.servlet.stockadjusment.repo.StockAdjusmentRepo;
import com.servlet.stockadjusment.service.StockAdjusmentService;
import com.servlet.stockitems.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StockAdjusmentHandler implements StockAdjusmentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StockAdjusmentRepo repo;
    @Autowired
    private StockAdjusmentItemRepo stockAdjusmentItemRepo;
    @Autowired
    private StockItemService stockItemService;
    @Autowired
    private MappingStockService mappingStockService;
    @Autowired
    private ProductService productService;

    @Autowired
    private RunningNumberService runningNumberService;
    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "STOCKADJUSMENT";
    @Override
    public List<StockAdjusmentDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(from != null){
            Date dt = new Date(from);
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(to != null){
            Date dt = new Date(to);
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyStockAdjusment body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_STOCKADJUSMENT, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                StockAdjusment table = new StockAdjusment();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setPricedate(new Date(body.getPricedate()));
                table.setIdpricelist(body.getIdpricelist());
                table.setNote(body.getNote());
                table.setType(body.getType());
                table.setCreatedby(iduser);
                table.setCreateddate(ts);
                idsave = repo.saveAndFlush(table).getId();
                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch, body.getItems(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,mixData,"","",ts);
                }else{
                    repo.deleteById(idsave);
                    stockAdjusmentItemRepo.deleteAllDetailByIdStockAdjusment(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_STOCKADJUSMENT);
                    validations.add(validationsItems.get(0));
                }
            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_STOCKADJUSMENT);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyStockAdjusment body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            StockAdjusment table = repo.getById(id);
            table.setNote(body.getNote());
            table.setType(body.getType());
            table.setModifiedby(iduser);
            table.setModifieddate(ts);
            idsave = repo.saveAndFlush(table).getId();

            kurangiStockItems(idcompany,idbranch, id);

            stockAdjusmentItemRepo.deleteAllDetailByIdStockAdjusment(id);

            HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch, body.getItems(), idsave);
            List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
            if(validationsItems.size() == 0){
                String data = table.toString();
                String dataItems = (String) mapsItems.get("dataItems");
                String mixData = "header = "+data+" | Items = "+dataItems;
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,mixData,"","",ts);
            }else{
                validations.add(validationsItems.get(0));
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
        try{
            StockAdjusment table = repo.getById(id);
            table.setIsdelete(true);
            table.setDeleteby(iduser);
            table.setDeletedate(ts);
            idsave = repo.saveAndFlush(table).getId();

            kurangiStockItems(idcompany,idbranch, id);
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
    public StockAdjusmentTemplate getTemplate(Long idcompany, Long idbranch) {
        StockAdjusmentTemplate data = new StockAdjusmentTemplate();
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        return data;
    }

    @Override
    public StockAdjsumentDataDetail getDetail(Long idcompany, Long idbranch, Long id) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<StockAdjsumentDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            StockAdjsumentDataDetail det = list.get(0);
            det.setItems(getItems(id));
            return det;
        }

        return null;
    }

    private List<StockAdjsumentDataItem> getItems(Long idstockadjusment){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataItemsJoin().schema());
        sqlBuilder.append(" where data.idstockadjusment = ?  ");
        final Object[] queryParameters = new Object[] {idstockadjusment};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataItemsJoin(), queryParameters);
    }

    private List<StockAdjsumentDataItemNotJoin> getItemsNotJoin(Long idstockadjusment){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataItemsNotJoin().schema());
        sqlBuilder.append(" where data.idstockadjusment = ?  ");
        final Object[] queryParameters = new Object[] {idstockadjusment};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataItemsNotJoin(), queryParameters);
    }

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch, BodyStockAdjusmentItem[] items, Long idstockadjusment){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        HashMap<String, StockAdjusmentItem> mapsStock = new HashMap<>();
        try{
            if(items.length > 0){
                for(BodyStockAdjusmentItem val : items){
                    String keyMaps = idstockadjusment+val.getIdcategoryproduct()+val.getIdproduct()+val.getType();
                    StockAdjusmentItemPK itemsPK = new StockAdjusmentItemPK();
                    itemsPK.setIdstockadjusment(idstockadjusment);
                    itemsPK.setIdcategoryproduct(val.getIdcategoryproduct());
                    itemsPK.setIdproduct(val.getIdproduct());
                    itemsPK.setType(val.getType());
                    StockAdjusmentItem table = new StockAdjusmentItem();
                    table.setStockAdjusmentItemPK(itemsPK);
                    table.setQty(val.getQty());
                    table.setPrice(val.getPrice());
                    table.setSubtotalprice(val.getSubtotalprice());
                    stockAdjusmentItemRepo.saveAndFlush(table);
                    mapsStock.put(keyMaps,table);
                }
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }

        if(validations.size() == 0){
            for (StockAdjusmentItem value : mapsStock.values()) {
                long categoryProductID = value.getStockAdjusmentItemPK().getIdcategoryproduct();
                MappingStockCategoryID mapping = mappingStockService.getDetailMapping(categoryProductID,idcompany,idbranch);
                if(mapping != null){
                    categoryProductID = mapping.getCategoryproductidmapping();
                }
                stockItemService.tambah(idcompany,idbranch,value.getStockAdjusmentItemPK().getIdproduct(),categoryProductID ,value.getStockAdjusmentItemPK().getType(),value.getQty());
            }
        }
        maps.put("validations",validations);
        maps.put("dataItems","");
        return maps;
    }

    private HashMap<Object,Object> kurangiStockItems(Long idcompany, Long idbranch, Long idstockadjusment){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<StockAdjsumentDataItemNotJoin> listItems = getItemsNotJoin(idstockadjusment);
        for(StockAdjsumentDataItemNotJoin value : listItems){
            long categoryProductID = value.getIdcategoryproduct();
            MappingStockCategoryID mapping = mappingStockService.getDetailMapping(categoryProductID,idcompany,idbranch);
            if(mapping != null){
                categoryProductID = mapping.getCategoryproductidmapping();
            }
            stockItemService.kurang(idcompany,idbranch,value.getIdproduct(),categoryProductID ,value.getType(),value.getQty());
        }


        maps.put("validations",validations);
        return maps;
    }
}
