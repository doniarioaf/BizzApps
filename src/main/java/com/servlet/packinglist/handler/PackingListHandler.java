package com.servlet.packinglist.handler;

import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.customer.service.CustomerService;
import com.servlet.draftpurchasereceive.entity.BodyDraftPurchaseReceiveItems;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.packinglist.entity.*;
import com.servlet.packinglist.mapper.*;
import com.servlet.packinglist.repo.PakcingListItemRepo;
import com.servlet.packinglist.repo.PakcingListRepo;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.product.service.ProductService;
import com.servlet.runningnumber.service.RunningNumberService;
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
import java.util.HashMap;
import java.util.List;

@Service
public class PackingListHandler implements PackingListService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PakcingListRepo repo;

    @Autowired
    private PakcingListItemRepo itemRepo;

    @Autowired
    private RunningNumberService runningNumberService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HistoryAppsService historyAppsService;

    protected final String namaMenu = "PackingList";
    @Override
    public List<PackingListDataList> getList(Long idcompany, Long idbranch, ParamSearchPackingList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public PackingListTemplate getTemplate(Long idcompany, Long idbranch) {
        PackingListTemplate data = new PackingListTemplate();
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        data.setCategoryProductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,null));
        data.setCustomerOpt(customerService.getListAll(idcompany,idbranch));
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPackingList body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PACKINGLIST, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                PackingList table = new PackingList();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setIdcustomer(body.getIdcustomer());
                table.setCity(body.getCity());
                table.setAttention(body.getAttention());
                table.setFlightnumber(body.getFlightnumber());
                table.setAwbnumber(body.getAwbnumber());
                table.setNetto(body.getNetto());
                table.setKoli(body.getKoli());
                table.setIdpricelist(body.getIdpricelist());
                table.setIsdelete(false);
                table.setCreatedby(iduser);
                table.setCreateddate(ts);
                idsave = repo.saveAndFlush(table).getId();
                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,idsave, body.getItems());
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,mixData,"","",ts);
                }else{
                    repo.deleteById(idsave);
                    itemRepo.deleteAllDetailByIdPackingList(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PACKINGLIST);
                    validations.add(validationsItems.get(0));
                }

            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PACKINGLIST);
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public PackingListDataDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PackingListDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            PackingListDataDetail data = list.get(0);
            data.setItems(getListItems(data.getId()));
            return data;
        }

        return null;
    }

    @Override
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyPackingList body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                PackingList table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    String mixDataBef = "header = " + table.toString() + " | Items = " + getListItemsNotJoin(id).toString();

                    table.setDate(new Date(body.getDate()));
                    table.setIdcustomer(body.getIdcustomer());
                    table.setCity(body.getCity());
                    table.setAttention(body.getAttention());
                    table.setFlightnumber(body.getFlightnumber());
                    table.setAwbnumber(body.getAwbnumber());
                    table.setNetto(body.getNetto());
                    table.setKoli(body.getKoli());
                    table.setModifiedby(iduser);
                    table.setModifieddate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    itemRepo.deleteAllDetailByIdPackingList(id);

                    HashMap<Object, Object> mapsItems = setItems(idcompany, idbranch, idsave, body.getItems());
                    List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                    if (validationsItems.size() == 0) {
                        String data = table.toString();
                        String dataItems = (String) mapsItems.get("dataItems");
                        String mixData = "header = " + data + " | Items = " + dataItems;
                        historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, "", mixData, mixDataBef, ts);
                    } else {
                        validations.add(validationsItems.get(0));
                    }
                }

            }catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
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
        if(validations.size() == 0) {
            try{
                PackingList table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    String mixDataBef = "header = " + table.toString() + " | Items = " + getListItemsNotJoin(id).toString();
                    table.setIsdelete(true);
                    table.setDeleteby(iduser);
                    table.setDeletedate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "DELETE", namaMenu, mixDataBef, "", "", ts);
                }

            }catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public List<PackingListDropDown> getDropDown(Long idcompany, Long idbranch, ParamDropDownPackingList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDropDown().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getMenu().equals("INVOICE")){
            sqlBuilder.append(" and data.id not in (select idpackinglist from invoice as inv where inv.idcompany = "+idcompany+" and inv.idbranch = "+idbranch+" and inv.isdelete = false ) ");
        }

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDropDown(), queryParameters);
    }

    private List<PackingListDataItemDetail> getListItems(Long idpackinglist){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryItemDataDetail().schema());
        sqlBuilder.append(" where data.idpackinglist = ? ");
        sqlBuilder.append(" order by data.box ");

        final Object[] queryParameters = new Object[] {idpackinglist};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryItemDataDetail(), queryParameters);
    }

    private List<PackingListItemData> getListItemsNotJoin(Long idpackinglist){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryItemNotJoin().schema());
        sqlBuilder.append(" where data.idpackinglist = ? ");
        final Object[] queryParameters = new Object[] {idpackinglist};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryItemNotJoin(), queryParameters);
    }

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch, Long idpackinglist, BodyPackingListItem[] items){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        try{
            if(items.length > 0){
                for(BodyPackingListItem val:items){
                    PackingListItemPK pk = new PackingListItemPK();
                    pk.setIdpackinglist(idpackinglist);
                    pk.setIdproduct(val.getIdproduct());
                    pk.setIdcategoryproduct(val.getIdcategoryproduct());
                    PackingListItem table = new PackingListItem();
                    table.setPackingListItemPK(pk);
                    table.setQty(val.getQty());
                    table.setBrutoweight(val.getBrutoweight());
                    table.setAllowance(val.getAllowance());
                    table.setNettoweight(val.getNettoweight());
                    table.setPrice(val.getPrice());
                    table.setTotalprice(val.getTotalprice());
                    table.setBox(val.getBox());
                    itemRepo.saveAndFlush(table);
                }
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        String dataItems = "";//listitem.toString();
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }
}
