package com.servlet.draftpurchasereceive.handler;

import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.draftpurchasereceive.entity.*;
import com.servlet.draftpurchasereceive.mapper.QueryDataList;
import com.servlet.draftpurchasereceive.repo.DraftPurchaseReceiveItemsRepo;
import com.servlet.draftpurchasereceive.repo.DraftPurchaseReceiveRepo;
import com.servlet.draftpurchasereceive.service.DraftPurchaseReceiveService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.product.service.ProductService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DraftPurchaseReceiveHandler implements DraftPurchaseReceiveService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DraftPurchaseReceiveRepo repo;
    @Autowired
    private DraftPurchaseReceiveItemsRepo repoItems;

    @Autowired
    private HistoryAppsService historyAppsService;
    @Autowired
    private VendorService vendorService;

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private RunningNumberService runningNumberService;

    protected final String namaMenu = "DraftPurchaseReceive";

    @Override
    public List<DraftPurchaseReceiveList> getList(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param) {
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
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public DraftPurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch) {
        DraftPurchaseReceiveTemplate template = new DraftPurchaseReceiveTemplate();
        template.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch));
        template.setProductOpt(productService.getListAll(idcompany,idbranch));
        return template;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDraftPurchaseReceive body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DRAFTPURCHASERECEIVE, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                DraftPurchaseReceive table = new DraftPurchaseReceive();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setIdvendor(body.getIdvendor());
                if(body.getArriveltime() != null){
                    long msArrival = sdf.parse(body.getArriveltime()).getTime();
                    Time arrival = new Time(msArrival);
                    table.setArriveltime(arrival);
                }else{
                    table.setArriveltime(null);
                }

                if(body.getReceivetime() != null){
                    long msReceive = sdf.parse(body.getReceivetime()).getTime();
                    Time receive = new Time(msReceive);
                    table.setReceivetime(receive);
                }else{
                    table.setReceivetime(null);
                }

                table.setSmu(body.getSmu());
                table.setTotalekor(body.getTotalekor());
                table.setTotalkg(body.getTotalkg());
                table.setPersentase(body.getPersentase());
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
                    repoItems.deleteAllDetailByIdDraftPurchaseReceive(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DRAFTPURCHASERECEIVE);
                    validations.add(validationsItems.get(0));
                }

                String data = table.toString();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,data,"","",ts);

            } catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DRAFTPURCHASERECEIVE);
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
    public SearchDataTemplateByVendor getTemplateByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        ParamTemplate paramCategoryProduct = new ParamTemplate();
        paramCategoryProduct.setMenu("DRAFTPURCHASE_RECEIVE");
        paramCategoryProduct.setIdvendor(idvendor);

        SearchDataTemplateByVendor data = new SearchDataTemplateByVendor();
        data.setCategoryproductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,paramCategoryProduct));
        return data;
    }

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch, Long iddraftpurchasereceive, BodyDraftPurchaseReceiveItems[] items){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<BodyDraftPurchaseReceiveItems> listitem = new ArrayList<>();
        try{
            if(items.length > 0){
                for(BodyDraftPurchaseReceiveItems val:items){
                    DraftPurchaseReceiveItemsPK pk = new DraftPurchaseReceiveItemsPK();
                    pk.setIddraftpurchasereceive(iddraftpurchasereceive);
                    pk.setBoxsequence(val.getBoxsequence());
                    pk.setIdproduct(val.getIdproduct());
                    pk.setIdcategoryproduct(val.getIdcategoryproduct());
                    DraftPurchaseReceiveItems table = new DraftPurchaseReceiveItems();
                    table.setDraftPurchaseReceiveItemsPK(pk);
                    table.setEkor(val.getEkor());
                    table.setKilo(val.getKilo());
                    table.setType(val.getType());
                    repoItems.saveAndFlush(table);
                    listitem.add(val);
                }
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        String dataItems = listitem.toString();
        maps.put("validations",validations);
        maps.put("dataItems","dataItems");
        return maps;
    }
}
