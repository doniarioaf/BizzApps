package com.servlet.purchasereceive.handler;

import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.charge.service.ChargeService;
import com.servlet.deposit.entity.BodyDeposit;
import com.servlet.deposit.service.DepositService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.inventori.service.InventoriService;
import com.servlet.mappingstock.entity.MappingStockCategoryID;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.parameterclient.entity.ValueParameter;
import com.servlet.parameterclient.service.ParameterClientService;
import com.servlet.pricelist.service.PriceService;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.*;
import com.servlet.purchasereceive.mapper.*;
import com.servlet.purchasereceive.repo.PurchaseReceiveChargeRepo;
import com.servlet.purchasereceive.repo.PurchaseReceiveInventoriRepo;
import com.servlet.purchasereceive.repo.PurchaseReceiveItemsRepo;
import com.servlet.purchasereceive.repo.PurchaseReceiveRepo;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.stockitems.service.StockItemService;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PurchaseReceiveHandler implements PurchaseReceiveService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PurchaseReceiveRepo purchaseReceiveRepo;
    @Autowired
    private PurchaseReceiveItemsRepo purchaseReceiveItemsRepo;
    @Autowired
    private PurchaseReceiveChargeRepo purchaseReceiveChargeRepo;
    @Autowired
    private PurchaseReceiveInventoriRepo purchaseReceiveInventoriRepo;

    @Autowired
    private VendorService vendorService;
    @Autowired
    private PriceService priceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RunningNumberService runningNumberService;
    @Autowired
    private ChargeService chargeService;

    @Autowired
    private CategoryProductService categoryProductService;
    @Autowired
    private StockItemService stockItemService;
    @Autowired
    private MappingStockService mappingStockService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private ParameterClientService parameterClientService;
    @Autowired
    private InventoriService inventoriService;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private UserAppsService userAppsService;
    protected final String namaMenu = "PURCHASE_RECEIVE";

    @Override
    public List<PurchaseReceiveDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(from != null){
            Date dt = new Date(from);
            sqlBuilder.append(" and data.transactiondate >= '"+dt.toString()+"'");
        }
        if(to != null){
            Date dt = new Date(to);
            sqlBuilder.append(" and data.transactiondate <= '"+dt.toString()+"'");
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);

    }

    @Override
    public PurchaseReceiveDataDetail getDetail(Long idcompany, Long idbranch, Long id) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PurchaseReceiveDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            PurchaseReceiveDataDetail data = list.get(0);
            data.setItems(getPrintDataItems(id));
            data.setCharges(getPrintDataCharge(id));
            data.setInventori(getPrintDataItemsInventori(id));
            data.setSisaDeposit(depositService.calculateSisaDepositByIdVendor(idcompany,idbranch,data.getIdvendor()));
            return data;
        }
        return null;
    }

    @Override
    public PurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch) {
        PurchaseReceiveTemplate data = new PurchaseReceiveTemplate();
        data.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch));
//        data.setPriceItems(priceService.getDataPriceByDate(idcompany,idbranch,pricedate));
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        data.setChargeOpt(chargeService.getListCharge(idcompany,idbranch));
        data.setInventoriOpt(inventoriService.getListDropDown(idcompany,idbranch));
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PURCHASERECEIVE, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        long iddeposit = 0;
        if(body.getTambahdeposit() != null && body.getTambahdeposit().doubleValue() > 0){
            BodyDeposit bodyDep = new BodyDeposit();
            bodyDep.setIdvendor(body.getIdvendor());
            bodyDep.setAmount(body.getTambahdeposit());
            bodyDep.setDepositdate(ts.getTime());
            ReturnData retDeposit = depositService.save(idcompany,idbranch,iduser,bodyDep);
            if(retDeposit.getValidations().size() > 0){
                validations.add(retDeposit.getValidations().get(0));
            }else{
                iddeposit = retDeposit.getId();
            }

        }

        if(validations.size() == 0) {
            try {
                PurchaseReceive table = new PurchaseReceive();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setIdvendor(body.getIdvendor());
                table.setTransactiondate(new Date(body.getTransactiondate()));
                table.setKoli(body.getKoli());
                table.setNotes(body.getNotes());
                table.setBank(body.getBank());
                table.setAccountnobank(body.getAccountnobank());
                table.setAccountnamebank(body.getAccountnamebank());
                table.setTotalprice(body.getTotalprice());
                table.setSetor(body.getSetor());
                table.setIsdefaultvaluesetor(body.isIsdefaultvaluesetor());
                table.setIddeposit(iddeposit);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = purchaseReceiveRepo.saveAndFlush(table).getId();
                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,body.getCharges(), body.getItems(),body.getInventori(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,mixData,"","",ts);
                }else{

                    purchaseReceiveRepo.deleteById(idsave);
                    purchaseReceiveItemsRepo.deleteAllDetailByIdPurchaseReceive(idsave);
                    purchaseReceiveChargeRepo.deleteAllDetailByIdPurchaseReceive(idsave);
                    purchaseReceiveInventoriRepo.deleteAllDetailByIdPurchaseReceiveInventory(idsave);
                    depositService.deleteRollBack(iddeposit);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PURCHASERECEIVE);
                    validations.add(validationsItems.get(0));
                }
            } catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PURCHASERECEIVE);
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
    public ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            PurchaseReceive table = purchaseReceiveRepo.getById(id);
            List<PurchaseReceiveItemsNotJoin> listItems = getDataItemsNotJoin(id);
            List<PurchaseReceiveChargeNotJoin> listItemsCharge = getDataItemsChargeNotJoin(id);
            List<PurchaseReceiveInventoriNotJoin> listItemsInventori = getDataItemsInventoriNotJoin(id);
            String dataBefore = table.toString();
            String dataItemsBefore = listItems.toString()+" | "+listItemsCharge.toString()+" | "+listItemsInventori.toString();
            String mixDataBefore = "header = "+dataBefore+" | Items = "+dataItemsBefore;

            /**
             * idvendor tidak diupdate, terlalu banyak relasi.
             *
             * note:ini sementara
             */

//            table.setIdvendor(body.getIdvendor());
            table.setTransactiondate(new Date(body.getTransactiondate()));
            table.setKoli(body.getKoli());
            table.setNotes(body.getNotes());
            table.setBank(body.getBank());
            table.setAccountnobank(body.getAccountnobank());
            table.setAccountnamebank(body.getAccountnamebank());
            table.setTotalprice(body.getTotalprice());
            table.setSetor(body.getSetor());
            table.setIsdefaultvaluesetor(body.isIsdefaultvaluesetor());
            table.setModifiedby(iduser);
            table.setModifieddate(ts);
            idsave = purchaseReceiveRepo.saveAndFlush(table).getId();

            kurangiStockItems(idcompany,idbranch, id);

            purchaseReceiveItemsRepo.deleteAllDetailByIdPurchaseReceive(idsave);
            purchaseReceiveChargeRepo.deleteAllDetailByIdPurchaseReceive(idsave);
            purchaseReceiveInventoriRepo.deleteAllDetailByIdPurchaseReceiveInventory(idsave);

            HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,body.getCharges(), body.getItems(),body.getInventori(), idsave);
            List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
            if(validationsItems.size() == 0){
                String data = table.toString();
                String dataItems = (String) mapsItems.get("dataItems");
                String mixData = "header = "+data+" | Items = "+dataItems;
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",mixData,mixDataBefore,ts);
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
        try {
            PurchaseReceive table = purchaseReceiveRepo.getById(id);
            table.setIsdelete(true);
            table.setDeleteby(iduser);
            table.setDeletedate(ts);
            idsave = purchaseReceiveRepo.saveAndFlush(table).getId();

            kurangiStockItems(idcompany,idbranch, id);
            historyAppsService.saveHistory(table.getIdcompany(),table.getIdbranch(),iduser,"DELETE",namaMenu,table.toString(),"","",ts);
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
    public ReturnData catatDownload(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            PurchaseReceive table = purchaseReceiveRepo.getById(id);
            historyAppsService.saveHistory(table.getIdcompany(),table.getIdbranch(),iduser,"DOWNLOADNOTA",namaMenu,table.toString(),"","",ts);
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
    public SearchDataTemplateByVendor searchDataByVendor(Long idcompany, Long idbranch, Long idvendor) {
        SearchDataTemplateByVendor data = new SearchDataTemplateByVendor();
        ParamTemplate paramCategoryProduct = new ParamTemplate();
        paramCategoryProduct.setMenu("PURCHASE_RECEIVE");
        paramCategoryProduct.setIdvendor(idvendor);
        data.setCategoryproductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,paramCategoryProduct));
        data.setSisaDeposit(depositService.calculateSisaDepositByIdVendor(idcompany,idbranch,idvendor));
        return data;
    }

    @Override
    public Double calculateSetorByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountSetor().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany,idvendor};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountSetor(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public PrintDataPurchaseReceive printNotaPurchaseReceive(Long idcompany, Long idbranch, Long iduser, Long id) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintPurchaseReceive().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PrintDataPurchaseReceive> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintPurchaseReceive(), queryParameters);
        if(list != null && list.size() > 0){
            ValueParameter param = parameterClientService.getValueByParamName(idcompany,idbranch,"COMPANYNAME","TEXT");

            PrintDataPurchaseReceive print = list.get(0);
            print.setSisaDeposit(depositService.calculateSisaDepositByIdVendor(idcompany,idbranch, print.getIdvendor()));
            print.setItems(getPrintDataItems(id));
            print.setCharges(getPrintDataCharge(id));
            print.setCompanyName(param.getStrValue());
            print.setInventori(getPrintDataItemsInventori(id));
            print.setSaldoDepositBeforeNotaSubmit(depositService.calculateSaldoDepositByIdVendorAndBeforeDateCreated(idcompany,idbranch, print.getIdvendor(),print.getCreateddate().getTime()));
            print.setCountPrint(historyAppsService.countByActionAndMenu(idcompany,idbranch,"DOWNLOADNOTA",namaMenu));
            print.setCountEdit(historyAppsService.countByActionAndMenu(idcompany,idbranch,"EDIT",namaMenu));
            UserListData user = userAppsService.getUserByID(iduser);
            String namaUser  = "";
            if(user != null){
                namaUser = user.getNama();
            }
            print.setNamaUser(namaUser);
            return print;
        }
        return null;
    }

    @Override
    public Double calculateSetorByIdVendorAndCreatedDate(Long idcompany, Long idbranch, Long idvendor, Long date) {
        Timestamp dt = new Timestamp(date);
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountSetor().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false and data.createddate < '"+dt+"' ");
        final Object[] queryParameters = new Object[] {idcompany,idvendor};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountSetor(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public PurchaseReceiveDataList checkIdDeposit(Long iddeposit) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataNotJoin().schema());
        sqlBuilder.append(" where data.iddeposit = ? and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {iddeposit};
        List<PurchaseReceiveDataList> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataNotJoin(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public PurchaseReceiveDataList getDataByIdDratPurchaseReceive(Long iddraftpurchasereceive, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataNotJoin().schema());
        sqlBuilder.append(" where data.iddraftpurchasereceive = ? and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {iddraftpurchasereceive};
        List<PurchaseReceiveDataList> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataNotJoin(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    private List<PrintDataPurchaseReceiveInventori> getPrintDataItemsInventori(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintDataPurchaseReceiveInventori().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintDataPurchaseReceiveInventori(), queryParameters);
    }

    private List<PrintDataPurchaseReceiveItems> getPrintDataItems(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintDataPurchaseReceiveItems().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintDataPurchaseReceiveItems(), queryParameters);
    }

    private List<PurchaseReceiveItemsNotJoin> getDataItemsNotJoin(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryItemsNotJoin().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryItemsNotJoin(), queryParameters);
    }

    private List<PurchaseReceiveChargeNotJoin> getDataItemsChargeNotJoin(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryItemsChargeNotJoin().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryItemsChargeNotJoin(), queryParameters);
    }

    private List<PurchaseReceiveInventoriNotJoin> getDataItemsInventoriNotJoin(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryItemsInventoriNotJoin().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryItemsInventoriNotJoin(), queryParameters);
    }

    private List<PrintDataPurchaseReceiveCharge> getPrintDataCharge(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintDataPurchaseReceiveCharge().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintDataPurchaseReceiveCharge(), queryParameters);
    }

    private HashMap<Object,Object> kurangiStockItems(Long idcompany, Long idbranch, Long idpurchasereceive){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<PurchaseReceiveItemsNotJoin> listItems = getDataItemsNotJoin(idpurchasereceive);
        for(PurchaseReceiveItemsNotJoin value : listItems){
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
    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch,BodyPurchaseReceiveCharge[] charges, BodyPurchaseReceiveItems[] items,BodyPurchaseReceiveInventori[] inventori, Long idpurchasereceive){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        HashMap<String,PurchaseReceiveItems> mapsStock = new HashMap<>();
        List<BodyPurchaseReceiveItems> listItems = new ArrayList<>();
        List<BodyPurchaseReceiveCharge> listCharge = new ArrayList<>();
        List<BodyPurchaseReceiveInventori> listInventori = new ArrayList<>();
        try{
            if(items.length > 0){
                for(BodyPurchaseReceiveItems val : items){
                    String keyMaps = idpurchasereceive+val.getIdcategoryproduct()+val.getIdproduct()+val.getType();
                    PurchaseReceiveItemsPK itemsPK = new PurchaseReceiveItemsPK();
                    itemsPK.setIdpurchasereceive(idpurchasereceive);
                    itemsPK.setIdcategoryproduct(val.getIdcategoryproduct());
                    itemsPK.setIdproduct(val.getIdproduct());
                    itemsPK.setType(val.getType());

                    PurchaseReceiveItems table = new PurchaseReceiveItems();
                    table.setPurchaseReceiveItemsPK(itemsPK);
                    table.setQty(val.getQty());
                    table.setQtybonus(val.getQtybonus());
                    table.setPrice(val.getPrice());
                    table.setSubtotalprice(val.getSubtotalprice());
                    purchaseReceiveItemsRepo.saveAndFlush(table);
                    listItems.add(val);
                    mapsStock.put(keyMaps,table);
                }
            }

            if(charges.length > 0){
                for(BodyPurchaseReceiveCharge val : charges){
                    PurchaseReceiveChargePK itemsPK = new PurchaseReceiveChargePK();
                    itemsPK.setIdpurchasereceive(idpurchasereceive);
                    itemsPK.setIdcharge(val.getIdcharge());

                    PurchaseReceiveCharge table = new PurchaseReceiveCharge();
                    table.setPurchaseReceiveChargePK(itemsPK);
                    table.setQty(val.getQty());
                    table.setPrice(val.getPrice());
                    table.setSubtotalprice(val.getSubtotalprice());
                    purchaseReceiveChargeRepo.saveAndFlush(table);
                    listCharge.add(val);

                }
            }

            if(inventori.length > 0){
                for(BodyPurchaseReceiveInventori val : inventori){
                    PurchaseReceiveInventoriPK pk = new PurchaseReceiveInventoriPK();
                    pk.setIdinventori(val.getIdinventori());
                    pk.setIdpurchasereceive(idpurchasereceive);

                    PurchaseReceiveInventori table = new PurchaseReceiveInventori();
                    table.setPurchaseReceiveInventoriPK(pk);
                    table.setQty(val.getQty());
                    table.setPrice(val.getPrice());
                    table.setSubtotalprice(val.getSubtotalprice());
                    purchaseReceiveInventoriRepo.saveAndFlush(table);
                    listInventori.add(val);
                }
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }

        if(validations.size() == 0){
            for (PurchaseReceiveItems value : mapsStock.values()) {
                long categoryProductID = value.getPurchaseReceiveItemsPK().getIdcategoryproduct();
                MappingStockCategoryID mapping = mappingStockService.getDetailMapping(categoryProductID,idcompany,idbranch);
                if(mapping != null){
                    categoryProductID = mapping.getCategoryproductidmapping();
                }
                stockItemService.tambah(idcompany,idbranch,value.getPurchaseReceiveItemsPK().getIdproduct(),categoryProductID ,value.getPurchaseReceiveItemsPK().getType(),value.getQty());
            }
        }
        String dataItems = listItems.toString()+" | "+listCharge.toString()+" | "+listInventori.toString();
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }
}
