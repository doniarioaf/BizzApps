package com.servlet.pricelist.handler;

import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.pricelist.entity.*;
import com.servlet.pricelist.mapper.QueryDataDetail;
import com.servlet.pricelist.mapper.QueryDataList;
import com.servlet.pricelist.mapper.QueryDataPriceItem;
import com.servlet.pricelist.repo.PriceListItemRepo;
import com.servlet.pricelist.repo.PriceListRepo;
import com.servlet.pricelist.service.PriceService;
import com.servlet.product.service.ProductService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PriceHandler implements PriceService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private PriceListRepo priceListRepo;

    @Autowired
    private PriceListItemRepo priceListItemRepo;

    @Autowired
    private HistoryAppsService historyAppsService;
    @Autowired
    private ProductService productService;
    protected final String namaMenu = "PriceList";

    @Override
    public List<PriceListData> getListAll(Long idcompany, Long idbranch, Long from, Long to) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(from != null){
            Date dt = new Date(from);
            sqlBuilder.append(" and data.pricedate >= '"+dt.toString()+"'");
        }
        if(to != null){
            Date dt = new Date(to);
            sqlBuilder.append(" and data.pricedate <= '"+dt.toString()+"'");
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    private List<PriceListItemData> getItemFromLastPriceListDoc(Long idcompany, Long idbranch){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        sqlBuilder.append(" order by id desc limit 1 ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        List<PriceListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
        if(list != null && list.size() > 0){
            return getPriceListItems(list.get(0).getId());
        }
        return null;
    }

    @Override
    public PriceListTemplate getTemplateData(Long idcompany, Long idbranch) {
        PriceListTemplate data = new PriceListTemplate();
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        data.setCategoryProductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,null));

        //priicelistudang harian ketika membuat dokumen baru , akan mengambil data dari dokumen terakhir dan diisi langsung seperti dokumen terakhir baru diedit oleh user lalu  disave
        data.setItems(getItemFromLastPriceListDoc(idcompany,idbranch));
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPriceList body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            List<PriceListData> check = getListAll(idcompany,idbranch, body.getPricedate(), body.getPricedate());
            if(check != null && check.size() > 0){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.PRICE_ALREADY_GENERATE,"harga sudah di generate untuk tanggal ini");
                validations.add(msg);
            }else{
                PriceList table = new PriceList();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setPricedate(new Date(body.getPricedate()));
                table.setPricedatethru(new Date(body.getPricedatethru()));
                table.setNotes(body.getNotes());
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = priceListRepo.saveAndFlush(table).getId();
                HashMap<Object,Object> mapsItems = setItems(body.getItems(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");

                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,mixData,"","",ts);
                }else{
                    priceListRepo.deleteById(idsave);
                    priceListItemRepo.deleteAllDetailByPriceListID(idsave);

                    validations.add(validationsItems.get(0));
                }
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyPriceList body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            PriceList table = priceListRepo.getById(id);
            if(table.getIdcompany() == idcompany.longValue() && table.getIdbranch() == idbranch.longValue()){
                table.setNotes(body.getNotes());
                table.setModifieddate(ts);
                table.setModifiedby(iduser);
                idsave = priceListRepo.saveAndFlush(table).getId();

                List<PriceListItemData> listItems = getPriceListItems(idsave);
                String dataBefore = table.toString();
                String dataItemsBefore = listItems.toString();
                String mixDataBefore = "header = "+dataBefore+" | Items = "+dataItemsBefore;

                priceListItemRepo.deleteAllDetailByPriceListID(idsave);
                HashMap<Object,Object> mapsItems = setItems(body.getItems(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");

                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",mixData,mixDataBefore,ts);
                }else{
                    validations.add(validationsItems.get(0));
                }
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
    public ReturnData delete(Long id, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            PriceList table = priceListRepo.getById(id);
            table.setIsdelete(true);
            table.setDeletedate(ts);
            table.setDeleteby(iduser);
            idsave = priceListRepo.saveAndFlush(table).getId();
            historyAppsService.saveHistory(table.getIdcompany(),table.getIdbranch(),iduser,"DELETE",namaMenu,table.toString(),"","",ts);
        }catch (Exception e){
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
    public PriceListDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PriceListDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            PriceListDetail data = list.get(0);
            data.setItems(getPriceListItems(id));
            return data;
        }

        return null;
    }

    @Override
    public PriceItemsDataForTemplate getDataPriceByDate(Long idcompany, Long idbranch, Long priceDate) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        if(priceDate != null && priceDate.longValue() != 0){
            Date dt = new Date(priceDate.longValue());
            sqlBuilder.append(" and data.pricedate <= '"+dt.toString()+"' and data.pricedatethru >= '"+dt.toString()+"' ");
            sqlBuilder.append(" order by id desc limit 1 ");
            final Object[] queryParameters = new Object[] {idcompany,idbranch};
            List<PriceListData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
            if(list != null && list.size() > 0){
                PriceListData data = list.get(0);

                PriceItemsDataForTemplate val = new PriceItemsDataForTemplate();
                val.setId(data.getId());
                val.setPricedate(data.getPricedate());
                val.setItems(getPriceListItems(data.getId()));

                return val;
            }
        }
        return null;

    }

    private List<PriceListItemData> getPriceListItems(Long idpricelist){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataPriceItem().schema());
        sqlBuilder.append(" where data.pricelistid = ? ");
        final Object[] queryParameters = new Object[] {idpricelist};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataPriceItem(), queryParameters);
    }

    private HashMap<Object,Object> setItems(BodyPriceItem[] items,long idpricelist){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        String dataItems = "";
        List<BodyPriceItem> listitems = new ArrayList<>();
        try {
            if (items.length > 0) {
                for (BodyPriceItem val : items) {
                    PriceItemPK pk = new PriceItemPK();
                    pk.setPricelistid(idpricelist);
                    pk.setCategoryproductid(val.getCategoryproductid());
                    pk.setIdproduct(val.getIdproduct());
                    PriceListItem item = new PriceListItem();
                    item.setPriceItemPK(pk);
                    item.setAmount(val.getAmount());
                    item.setAllowance(val.getAllowance());
                    priceListItemRepo.saveAndFlush(item);
                    listitems.add(val);
                }
                dataItems = listitems.toString();
            }
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }
}
