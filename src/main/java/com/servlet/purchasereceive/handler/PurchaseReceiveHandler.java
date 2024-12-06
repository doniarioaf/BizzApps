package com.servlet.purchasereceive.handler;

import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.charge.service.ChargeService;
import com.servlet.deposit.entity.BodyDeposit;
import com.servlet.deposit.service.DepositService;
import com.servlet.mappingstock.entity.MappingStockCategoryID;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.parameterclient.entity.ValueParameter;
import com.servlet.parameterclient.service.ParameterClientService;
import com.servlet.pricelist.service.PriceService;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.*;
import com.servlet.purchasereceive.mapper.QueryDataList;
import com.servlet.purchasereceive.mapper.QueryPrintDataPurchaseReceiveCharge;
import com.servlet.purchasereceive.mapper.QueryPrintDataPurchaseReceiveItems;
import com.servlet.purchasereceive.mapper.QueryPrintPurchaseReceive;
import com.servlet.purchasereceive.repo.PurchaseReceiveChargeRepo;
import com.servlet.purchasereceive.repo.PurchaseReceiveItemsRepo;
import com.servlet.purchasereceive.repo.PurchaseReceiveRepo;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.stockitems.service.StockItemService;
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
    public PurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch) {
        PurchaseReceiveTemplate data = new PurchaseReceiveTemplate();
        data.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch));
//        data.setPriceItems(priceService.getDataPriceByDate(idcompany,idbranch,pricedate));
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        data.setChargeOpt(chargeService.getListCharge(idcompany,idbranch));
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
                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,body.getCharges(), body.getItems(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if(validationsItems.size() == 0){

                }else{
                    purchaseReceiveRepo.deleteById(idsave);
                    purchaseReceiveItemsRepo.deleteAllDetailByIdPurchaseReceive(idsave);
                    purchaseReceiveChargeRepo.deleteAllDetailByIdPurchaseReceive(idsave);
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
            print.setSaldoDepositBeforeNotaSubmit(depositService.calculateSaldoDepositByIdVendorAndBeforeDateCreated(idcompany,idbranch, print.getIdvendor(),print.getCreateddate().getTime()));
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

    private List<PrintDataPurchaseReceiveItems> getPrintDataItems(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintDataPurchaseReceiveItems().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintDataPurchaseReceiveItems(), queryParameters);
    }

    private List<PrintDataPurchaseReceiveCharge> getPrintDataCharge(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintDataPurchaseReceiveCharge().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintDataPurchaseReceiveCharge(), queryParameters);
    }

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch,BodyPurchaseReceiveCharge[] charges, BodyPurchaseReceiveItems[] items, Long idpurchasereceive){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        HashMap<String,PurchaseReceiveItems> mapsStock = new HashMap<>();
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
        maps.put("validations",validations);
//        maps.put("dataItems",dataItems);
        return maps;
    }
}
