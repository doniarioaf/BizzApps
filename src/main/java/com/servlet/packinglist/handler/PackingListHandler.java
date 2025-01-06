package com.servlet.packinglist.handler;

import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.customer.service.CustomerService;
import com.servlet.draftpurchasereceive.entity.BodyDraftPurchaseReceiveItems;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.entity.Invoice;
import com.servlet.invoice.entity.InvoiceDataList;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.mappingstock.entity.MappingStockCategoryID;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.packinglist.entity.*;
import com.servlet.packinglist.mapper.*;
import com.servlet.packinglist.repo.PakcingListItemRepo;
import com.servlet.packinglist.repo.PakcingListRepo;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.parameterclient.entity.ValueParameter;
import com.servlet.parameterclient.service.ParameterClientService;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.PurchaseReceiveItems;
import com.servlet.purchasereceive.entity.PurchaseReceiveItemsNotJoin;
import com.servlet.purchasereceive.mapper.QueryCalculateQty;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.stockitems.service.StockItemService;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
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
    private ParameterClientService parameterClientService;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private StockItemService stockItemService;
    @Autowired
    private MappingStockService mappingStockService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserAppsService userAppsService;

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
        InvoiceDataList inv = invoiceService.getDataByIdPackingList(idcompany,idbranch,id);
        if(inv != null){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_INVOICE,"packinglist ini terpasang pada invoice ("+inv.getNodocument()+")");
            validations.add(msg);
        }
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

                    tambahStockItems(idcompany,idbranch,id);
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
        InvoiceDataList inv = invoiceService.getDataByIdPackingList(idcompany,idbranch,id);
        if(inv != null){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_INVOICE,"packinglist ini terpasang pada invoice ("+inv.getNodocument()+")");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                PackingList table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    String mixDataBef = "header = " + table.toString() + " | Items = " + getListItemsNotJoin(id).toString();
                    table.setIsdelete(true);
                    table.setDeleteby(iduser);
                    table.setDeletedate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    tambahStockItems(idcompany,idbranch,id);
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

    @Override
    public PrintPackingList getPrintData(Long id, Long idcompany, Long idbranch,Long iduser) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataPrint().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PrintPackingList> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataPrint(), queryParameters);
        if(list != null && list.size() > 0){
            ValueParameter param = parameterClientService.getValueByParamName(idcompany,idbranch,"COMPANYNAME","TEXT");
            ValueParameter paramAddress1 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS1","TEXT");
            ValueParameter paramAddress2 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS2","TEXT");
            ValueParameter paramAddress3 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS3","TEXT");

            PrintPackingList data = list.get(0);
            data.setItems(getListItems(data.getId()));
            data.setCompanyName(param.getStrValue());
            data.setAddress1(paramAddress1.getStrValue());
            data.setAddress2(paramAddress2.getStrValue());
            data.setAddress3(paramAddress3.getStrValue());
            data.setCountPrint(historyAppsService.countByActionAndMenu(idcompany,idbranch,"DOWNLOADPDF",namaMenu));
            data.setCountEdit(historyAppsService.countByActionAndMenu(idcompany,idbranch,"EDIT",namaMenu));
            if(iduser != null) {
                UserListData user = userAppsService.getUserByID(iduser);
                String namaUser = "";
                if (user != null) {
                    namaUser = user.getNama();
                }
                data.setNamaUser(namaUser);
            }
            return data;
        }
        return null;
    }

    @Override
    public Long calculateQtyPL(Long idcompany, Long idbranch, ParamCalculateQtyPL param) {
        String selectidPr = " select pr.id from packinglist as pr where pr.idcompany = "+idcompany+" and pr.idbranch = "+idbranch+" and pr.isdelete = false ";
        if(param.getDateFrom() != null){
            Date dt = new Date(param.getDateFrom());
            selectidPr += " and pr.date >= '"+dt.toString()+"' ";
        }
        if(param.getDateThru() != null){
            Date dt = new Date(param.getDateThru());
            selectidPr += " and pr.date <= '"+dt.toString()+"' ";
        }
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateQtyPL().schema());
        sqlBuilder.append(" where data.idcategoryproduct = ? and data.idpackinglist in ("+selectidPr+") ");

        final Object[] queryParameters = new Object[] {param.getIdcategoryproduct()};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateQtyPL(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0L;
    }

    @Override
    public ReturnData catatDownload(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            PackingList table = repo.getById(id);
            historyAppsService.saveHistory(table.getIdcompany(),table.getIdbranch(),iduser,"DOWNLOADPDF",namaMenu,id.toString(),"","",ts);
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

    private HashMap<Object,Object> tambahStockItems(Long idcompany, Long idbranch, Long idpackinglist){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<PackingListItemData> listItems = getListItemsNotJoin(idpackinglist);
        for(PackingListItemData value : listItems){
            long categoryProductID = value.getIdcategoryproduct();
            MappingStockCategoryID mapping = mappingStockService.getDetailMapping(categoryProductID,idcompany,idbranch);
            if(mapping != null){
                categoryProductID = mapping.getCategoryproductidmapping();
            }
            stockItemService.tambah(idcompany,idbranch,value.getIdproduct(),categoryProductID ,"H",value.getQty());
        }


        maps.put("validations",validations);
        return maps;
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
        HashMap<String, PackingListItem> mapsStock = new HashMap<>();
        List<BodyPackingListItem> listitem = new ArrayList<>();
        try{
            if(items.length > 0){
                int noseq =1;
                for(BodyPackingListItem val:items){
                    String keyMaps = idpackinglist+val.getIdcategoryproduct()+val.getIdproduct()+val.getBox()+noseq;
                    PackingListItemPK pk = new PackingListItemPK();
                    pk.setIdpackinglist(idpackinglist);
                    pk.setIdproduct(val.getIdproduct());
                    pk.setIdcategoryproduct(val.getIdcategoryproduct());
                    pk.setBox(val.getBox());
                    pk.setNoseq(noseq);
                    PackingListItem table = new PackingListItem();
                    table.setPackingListItemPK(pk);
                    table.setQty(val.getQty());
                    table.setBrutoweight(val.getBrutoweight());
                    table.setAllowance(val.getAllowance());
                    table.setNettoweight(val.getNettoweight());
                    table.setPrice(val.getPrice());
                    table.setTotalprice(val.getTotalprice());
                    itemRepo.saveAndFlush(table);
                    listitem.add(val);
                    mapsStock.put(keyMaps,table);
                    noseq++;
                }
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        if(validations.size() == 0){
            for (PackingListItem value : mapsStock.values()) {
                long categoryProductID = value.getPackingListItemPK().getIdcategoryproduct();
                MappingStockCategoryID mapping = mappingStockService.getDetailMapping(categoryProductID,idcompany,idbranch);
                if(mapping != null){
                    categoryProductID = mapping.getCategoryproductidmapping();
                }
                stockItemService.kurang(idcompany,idbranch,value.getPackingListItemPK().getIdproduct(),categoryProductID ,"H",value.getQty());
            }
        }
        String dataItems = listitem.toString();
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }
}
