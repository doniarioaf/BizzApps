package com.servlet.stockadjusment.handler;

import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.cancelpackinglist.entity.ParamCalculateQtyCPL;
import com.servlet.cancelpackinglist.service.CancelPackingListService;
import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.draftpurchasereceive.entity.ParamCalculateQtyDPR;
import com.servlet.draftpurchasereceive.service.DraftPurchaseReceiveService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.mappingstock.entity.MappingStockCategoryID;
import com.servlet.mappingstock.entity.MappingStockList;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.packinglist.entity.ParamCalculateQtyPL;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.ParamCalculateQtyPR;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.report.entity.ParamReportStockUdangHidupMati;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.*;
import com.servlet.stockadjusment.entity.*;
import com.servlet.stockadjusment.mapper.*;
import com.servlet.stockadjusment.repo.StockAdjusmentItemRepo;
import com.servlet.stockadjusment.repo.StockAdjusmentRepo;
import com.servlet.stockadjusment.service.StockAdjusmentService;
import com.servlet.stockitems.entity.ParamCalculateQty;
import com.servlet.stockitems.entity.ReportKartuStock;
import com.servlet.stockitems.service.StockItemService;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
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
    private CategoryProductService categoryProductService;

    @Autowired
    private RunningNumberService runningNumberService;
    @Autowired
    private UserAppsService userAppsService;
    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    BranchService branchService;

    @Autowired
    PurchaseReceiveService purchaseReceiveService;

    @Autowired
    StockAdjusmentService stockAdjusmentService;

    @Autowired
    private DraftPurchaseReceiveService draftPurchaseReceiveService;

    @Autowired
    CancelPackingListService cancelPackingListService;


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
            if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                table.setNote(body.getNote());
                table.setType(body.getType());
                table.setModifiedby(iduser);
                table.setModifieddate(ts);
                idsave = repo.saveAndFlush(table).getId();

                kurangiStockItems(idcompany, idbranch, id);

                stockAdjusmentItemRepo.deleteAllDetailByIdStockAdjusment(id);

                HashMap<Object, Object> mapsItems = setItems(idcompany, idbranch, body.getItems(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if (validationsItems.size() == 0) {
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = " + data + " | Items = " + dataItems;
                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, mixData, "", "", ts);
                } else {
                    validations.add(validationsItems.get(0));
                }
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
            if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                table.setIsdelete(true);
                table.setDeleteby(iduser);
                table.setDeletedate(ts);
                idsave = repo.saveAndFlush(table).getId();

                kurangiStockItems(idcompany, idbranch, id);
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
    public StockAdjusmentTemplate getTemplate(Long idcompany, Long idbranch) {
        StockAdjusmentTemplate data = new StockAdjusmentTemplate();
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        ParamTemplate paramcp = new ParamTemplate();
        paramcp.setShowOnlyCpMapping(true);
        paramcp.setForcategory("CUSTOMER");
        data.setCategoryProductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,paramcp));
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

    @Override
    public Long calculateQtySA(Long idcompany, Long idbranch,String type, ParamCalculateQtySA param) {
        String selectidPr = " select pr.id from stock_adjusment as pr where pr.idcompany = "+idcompany+" and pr.idbranch = "+idbranch+" and pr.isdelete = false ";
        if(param.getDateFrom() != null){
            Date dt = new Date(param.getDateFrom());
            selectidPr += " and pr.date >= '"+dt.toString()+"' ";
        }
        if(param.getDateThru() != null){
            Date dt = new Date(param.getDateThru());
            selectidPr += " and pr.date <= '"+dt.toString()+"' ";
        }
        if(type.equals("MINUS_QTY")){
            selectidPr += " and pr.type in ('H','M') ";
        }else{
            selectidPr += " and pr.type = '"+type+"' ";
        }


        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateQtySA().schema());
        sqlBuilder.append(" where data.idstockadjusment in ("+selectidPr+") ");

        if(param.getIdcategoryproduct() != null){
            sqlBuilder.append(" and data.idcategoryproduct = "+param.getIdcategoryproduct()+" ");
        }

        if(param.getListidcategoryproduct() != null && !param.getListidcategoryproduct().equals("")){
            sqlBuilder.append(" and data.idcategoryproduct in ("+param.getListidcategoryproduct()+") ");
        }

        if(param.getIdproduct() != null){
            sqlBuilder.append(" and data.idproduct = "+param.getIdproduct()+" ");
        }

        if(param.getListidproduct() != null && !param.getListidproduct().equals("")){
            sqlBuilder.append(" and data.idproduct in ("+param.getListidproduct()+") ");
        }
        if(type.equals("MINUS_QTY")){
            sqlBuilder.append(" and data.qty < 0  ");
        }else{
            sqlBuilder.append(" and data.qty > 0  ");
        }
        final Object[] queryParameters = new Object[] {};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateQtySA(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0L;
    }

    @Override
    public List<ReportKartuStock> getListReportKartuStock(Long idcompany, Long idbranch, ParamCalculateQtySA param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryStockAdjusmentReportKartuStock().schema());
        sqlBuilder.append(" where sa.idcompany = ? and sa.idbranch = ? and sa.isdelete = false  ");
        if(param != null){
            if(param.getDateFrom() != null){
                Date dt = new Date(param.getDateFrom());
                sqlBuilder.append(" and sa.date >= '"+dt.toString()+"'");
            }
            if(param.getDateThru() != null){
                Date dt = new Date(param.getDateThru());
                sqlBuilder.append(" and sa.date <= '"+dt.toString()+"'");
            }
            if(param.getType() != null){
                sqlBuilder.append(" and sa.type = '"+param.getType()+"' ");
            }
            if(param.getListidproduct() != null && !param.getListidproduct().equals("")){
                sqlBuilder.append(" and data.idproduct in ("+param.getListidproduct()+") ");
            }
            if(param.getListidcategoryproduct() != null && !param.getListidcategoryproduct().equals("")){
                sqlBuilder.append(" and data.idcategoryproduct in ("+param.getListidcategoryproduct()+") ");
            }
        }

        sqlBuilder.append(" GROUP BY data.idproduct, data.idcategoryproduct,data.type, sa.nodocument, sa.date,sa.note ");
//        sqlBuilder.append(" order by sa.id ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryStockAdjusmentReportKartuStock(), queryParameters);
    }

    @Override
    public PrintDataStockUdangMati getPrintData(Long idcompany, Long idbranch, Long iduser, Long id,String typefile) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintDataStockUdangMati().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
//        sqlBuilder.append(" and data.type = 'M' ");

        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PrintDataStockUdangMati> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintDataStockUdangMati(), queryParameters);
        if(list != null && list.size() > 0){
            PrintDataStockUdangMati print = list.get(0);
            print.setItems(getItems(id));
            if(typefile.equals("PDF")) {
//                print.setCountPrint(historyAppsService.countByActionAndMenu(idcompany, idbranch, "DOWNLOADPDF", namaMenu));
                HashMap mapParamPrint = new HashMap();
                mapParamPrint.put("data-id",id);
                print.setCountPrint(historyAppsService.countByActionAndMenuParam(idcompany,idbranch,"DOWNLOADPDF",namaMenu,mapParamPrint));
                print.setCountEdit(historyAppsService.countByActionAndMenu(idcompany, idbranch, "EDIT", namaMenu));
            }
            if(iduser != null) {
                UserListData user = userAppsService.getUserByID(iduser);
                String namaUser = "";
                if (user != null) {
                    namaUser = user.getNama();
                }
                print.setNamaUser(namaUser);
            }
            ParamTemplate paramcp = new ParamTemplate();
            paramcp.setShowOnlyCpMapping(true);
            paramcp.setForcategory("CUSTOMER");
            print.setListcp(categoryProductService.getDataForTemplate(idcompany,idbranch,paramcp));
            print.setMappingstock(mappingStockService.getListAll(idcompany,idbranch));
            if(typefile.equals("PDF")){
//                catatDownload(id,idcompany,idbranch,iduser);
            }

            return print;
        }
        return null;
    }

    @Override
    public List<PrintDataStockAdjusmentHidupDanMati> printStockUdangHidupMati(
            Long idcompany, Long idbranch, ParamReportStockUdangHidupMati param) {

        String namaCabang = "";
        Branch branch = branchService.getBranchByID(idbranch);
        if (branch != null) {
            namaCabang = branch.getNama();
        }

        List<CategoryProductList> listCP = categoryProductService.getDataForTemplate(idcompany, idbranch, null);
        HashMap<Long, Long> stockTotalEkorByIDcategory = new HashMap<>();
        HashMap<Long, Long> stockKolamTerakhirByIDcategory = new HashMap<>();
        HashMap<Long, Long> stockUdangMatiByIDcategory     = new HashMap<>();
        HashMap<Long, Long> stockUdangMasukByIDcategory    = new HashMap<>();
        HashMap<Long, CategoryProductList> cpByIDcategory  = new HashMap<>();

        Long dateMinus1 = 0L;
        try {
            dateMinus1 = GlobalFunc.addDays(param.getDate(), -1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long satuJan70 = 56169461L;

        for (CategoryProductList cp : listCP) {
            cpByIDcategory.put(cp.getId(), cp);

            // --- Stok kolam terakhir (s.d. hari kemarin) ---
            ParamCalculateQtyDPR paramPR = new ParamCalculateQtyDPR();
            paramPR.setDateFrom(satuJan70);
            paramPR.setDateThru(dateMinus1);
            paramPR.setIdcategoryproduct(cp.getId());

            ParamCalculateQtySA paramSA = new ParamCalculateQtySA();
            paramSA.setDateFrom(satuJan70);
            paramSA.setDateThru(dateMinus1);
            paramSA.setIdcategoryproduct(cp.getId());

            ParamCalculateQtyPL paramPL = new ParamCalculateQtyPL();
            paramPL.setDateFrom(satuJan70);
            paramPL.setDateThru(dateMinus1);
            paramPL.setIdcategoryproduct(cp.getId());

            ParamCalculateQtyCPL paramCPL = new ParamCalculateQtyCPL();
            paramCPL.setDateFrom(satuJan70);
            paramCPL.setDateThru(dateMinus1);
            paramCPL.setIdcategoryproduct(cp.getId());

            ParamCalculateQty paramQty = new ParamCalculateQty();
            paramQty.setParamCalculateQtyDPR(paramPR);
            paramQty.setParamCalculateQtySA(paramSA);
            paramQty.setParamCalculateQtyPL(paramPL);
            paramQty.setParamCalculateQtyCPL(paramCPL);
            Long stockKolamTerakhir = stockItemService.calculateQty(idcompany, idbranch, paramQty);
            stockKolamTerakhirByIDcategory.put(cp.getId(), stockKolamTerakhir != null ? stockKolamTerakhir : 0L);

            // --- Udang mati hari ini (SA type "M" + CPL type "M") ---
            ParamCalculateQtySA paramSAUdangMati = new ParamCalculateQtySA();
            paramSAUdangMati.setDateFrom(param.getDate());
            paramSAUdangMati.setDateThru(param.getDate());
            paramSAUdangMati.setIdcategoryproduct(cp.getId());
            Long stockUdangMati = stockAdjusmentService.calculateQtySA(idcompany, idbranch, "M", paramSAUdangMati);
            stockUdangMati = stockUdangMati != null ? stockUdangMati : 0L;

            ParamCalculateQtyCPL paramCalculateQtyCPL = new ParamCalculateQtyCPL();
            paramCalculateQtyCPL.setDateFrom(param.getDate());
            paramCalculateQtyCPL.setDateThru(param.getDate());
            paramCalculateQtyCPL.setIdcategoryproduct(cp.getId());
            paramCalculateQtyCPL.setType("M");
            Long stockUdangMatiCPL = cancelPackingListService.calculateQtyCPL(idcompany, idbranch, paramCalculateQtyCPL);
            stockUdangMatiCPL = stockUdangMatiCPL != null ? stockUdangMatiCPL : 0L;

            stockUdangMatiByIDcategory.put(cp.getId(), stockUdangMati + stockUdangMatiCPL);

            // --- Udang masuk hari ini (DPR + SA + CPL) ---
            ParamCalculateQtyDPR paramPRUdangMasuk = new ParamCalculateQtyDPR();
            paramPRUdangMasuk.setDateFrom(param.getDate());
            paramPRUdangMasuk.setDateThru(param.getDate());
            paramPRUdangMasuk.setIdcategoryproduct(cp.getId());

            ParamCalculateQtySA paramSAUdangMasuk = new ParamCalculateQtySA();
            paramSAUdangMasuk.setDateFrom(param.getDate());
            paramSAUdangMasuk.setDateThru(param.getDate());
            paramSAUdangMasuk.setIdcategoryproduct(cp.getId());

            ParamCalculateQtyCPL paramCPLUdangMasuk = new ParamCalculateQtyCPL();
            paramCPLUdangMasuk.setDateFrom(param.getDate());
            paramCPLUdangMasuk.setDateThru(param.getDate());
            paramCPLUdangMasuk.setIdcategoryproduct(cp.getId());

            ParamCalculateQty paramQtyUdangMasuk = new ParamCalculateQty();
            paramQtyUdangMasuk.setParamCalculateQtyDPR(paramPRUdangMasuk);
            paramQtyUdangMasuk.setParamCalculateQtySA(paramSAUdangMasuk);
            paramQtyUdangMasuk.setParamCalculateQtyCPL(paramCPLUdangMasuk);
            // PL tidak di-set karena source udang masuk hanya DPR + SA + CPL
            Long stockUdangMasuk = stockItemService.calculateQtyUdangMasuk(idcompany, idbranch, paramQtyUdangMasuk);
            stockUdangMasukByIDcategory.put(cp.getId(), stockUdangMasuk != null ? stockUdangMasuk : 0L);



            // --- Total Ekor = Stok Hari Itu sesuai tanggal ---
            ParamCalculateQtyDPR paramPR_TotalEkor = new ParamCalculateQtyDPR();
            paramPR_TotalEkor.setDateFrom(satuJan70);
            paramPR_TotalEkor.setDateThru(param.getDate());
            paramPR_TotalEkor.setIdcategoryproduct(cp.getId());

            ParamCalculateQtySA paramSA_TotalEkor = new ParamCalculateQtySA();
            paramSA_TotalEkor.setDateFrom(satuJan70);
            paramSA_TotalEkor.setDateThru(param.getDate());
            paramSA_TotalEkor.setIdcategoryproduct(cp.getId());

            ParamCalculateQtyPL paramPL_TotalEkor = new ParamCalculateQtyPL();
            paramPL_TotalEkor.setDateFrom(satuJan70);
            paramPL_TotalEkor.setDateThru(param.getDate());
            paramPL_TotalEkor.setIdcategoryproduct(cp.getId());

            ParamCalculateQtyCPL paramCPL_TotalEkor = new ParamCalculateQtyCPL();
            paramCPL_TotalEkor.setDateFrom(satuJan70);
            paramCPL_TotalEkor.setDateThru(param.getDate());
            paramCPL_TotalEkor.setIdcategoryproduct(cp.getId());

            ParamCalculateQty paramQty_TotalEKor = new ParamCalculateQty();
            paramQty_TotalEKor.setParamCalculateQtyDPR(paramPR_TotalEkor);
            paramQty_TotalEKor.setParamCalculateQtySA(paramSA_TotalEkor);
            paramQty_TotalEKor.setParamCalculateQtyPL(paramPL_TotalEkor);
            paramQty_TotalEKor.setParamCalculateQtyCPL(paramCPL_TotalEkor);
            Long stockTotalEkor = stockItemService.calculateQty(idcompany, idbranch, paramQty_TotalEKor);
            stockTotalEkorByIDcategory.put(cp.getId(), stockTotalEkor != null ? stockTotalEkor : 0L);
        }

        Long grandTotalStockKolamTerakhir = 0L;
        Long grandTotalUdangMati          = 0L;
        Long grandTotalUdangMasuk         = 0L;
        Long grandTotalTotalEkor          = 0L;
        Double grandTotalTotalKoli          = 0.00;

        List<MappingStockList> listMapping = mappingStockService.getListAll(idcompany, idbranch);

        HashMap<Long, Long> calculateStockByIdCPMappingStockKolamTerakhir = new HashMap<>();
        HashMap<Long, Long> calculateStockByIdCPMappingStockUdangMati     = new HashMap<>();
        HashMap<Long, Long> calculateStockByIdCPMappingStockUdangMasuk    = new HashMap<>();
        HashMap<Long, Long> calculateStockByIdCPMappingTotalEkor = new HashMap<>();

        if (listMapping != null && !listMapping.isEmpty()) {
            for (MappingStockList mapp : listMapping) {
                Long mappingKey = mapp.getCategoryproductidmapping();

                long sk1 = stockKolamTerakhirByIDcategory.getOrDefault(mapp.getCategoryproductid(), 0L);
                long sk2 = stockKolamTerakhirByIDcategory.getOrDefault(mappingKey, 0L);

                long sm1 = stockUdangMatiByIDcategory.getOrDefault(mapp.getCategoryproductid(), 0L);
                long sm2 = stockUdangMatiByIDcategory.getOrDefault(mappingKey, 0L);

                long su1 = stockUdangMasukByIDcategory.getOrDefault(mapp.getCategoryproductid(), 0L);
                long su2 = stockUdangMasukByIDcategory.getOrDefault(mappingKey, 0L);

                long s_te1 = stockTotalEkorByIDcategory.getOrDefault(mapp.getCategoryproductid(), 0L);
                long s_te2 = stockTotalEkorByIDcategory.getOrDefault(mappingKey, 0L);

                if (calculateStockByIdCPMappingStockKolamTerakhir.containsKey(mappingKey)) {
                    // FIX double counting: iterasi berikutnya hanya tambah sk1/sm1/su1 (id sumber saja)
                    // sk2/sm2/su2 (stock dari mappingKey itu sendiri) sudah dihitung di iterasi pertama
                    calculateStockByIdCPMappingStockKolamTerakhir.merge(mappingKey, sk1, Long::sum);
                    calculateStockByIdCPMappingStockUdangMati.merge(mappingKey, sm1, Long::sum);
                    calculateStockByIdCPMappingStockUdangMasuk.merge(mappingKey, su1, Long::sum);
                    calculateStockByIdCPMappingTotalEkor.merge(mappingKey, s_te1, Long::sum);
                } else {
                    // Iterasi pertama: simpan sk1+sk2, sm1+sm2, su1+su2
                    calculateStockByIdCPMappingStockKolamTerakhir.put(mappingKey, sk1 + sk2);
                    calculateStockByIdCPMappingStockUdangMati.put(mappingKey, sm1 + sm2);
                    calculateStockByIdCPMappingStockUdangMasuk.put(mappingKey, su1 + su2);
                    calculateStockByIdCPMappingTotalEkor.put(mappingKey, s_te1 + s_te2);
                }
            }
        }

        HashMap<Long, Long> done                 = new HashMap<>();
        HashMap<Long, Long> cekIDCPMappingKembar = new HashMap<>();
        List<PrintDataStockAdjusmentHidupDanMati> listData = new ArrayList<>();

        if (listMapping != null && !listMapping.isEmpty()) {
            for (MappingStockList mapp : listMapping) {
                done.put(mapp.getCategoryproductid(), mapp.getCategoryproductidmapping());
                done.put(mapp.getCategoryproductidmapping(), mapp.getCategoryproductidmapping());

                Long mappingKey = mapp.getCategoryproductidmapping();

                // Skip duplikat sebelum akumulasi grand total
                if (cekIDCPMappingKembar.containsKey(mappingKey)) {
                    continue;
                }
                cekIDCPMappingKembar.put(mappingKey, mappingKey);

                CategoryProductList cp = cpByIDcategory.get(mappingKey);
                if (cp == null) continue;

                long stockKolamTerakhir = calculateStockByIdCPMappingStockKolamTerakhir.getOrDefault(mappingKey, 0L);
                long stockUdangMati     = calculateStockByIdCPMappingStockUdangMati.getOrDefault(mappingKey, 0L);
                long stockUdangMasuk    = calculateStockByIdCPMappingStockUdangMasuk.getOrDefault(mappingKey, 0L);
                long stockTotalEkor    = calculateStockByIdCPMappingTotalEkor.getOrDefault(mappingKey, 0L);

                grandTotalStockKolamTerakhir += stockKolamTerakhir;
                grandTotalUdangMati          += stockUdangMati;
                grandTotalUdangMasuk         += stockUdangMasuk;
                grandTotalTotalEkor         += stockTotalEkor;

                long totalEkor = stockTotalEkor;
//                long totalEkor = stockKolamTerakhir + stockUdangMasuk - stockUdangMati;
//                grandTotalTotalEkor += totalEkor;

                Double totalKoli = 0.00;
                if (cp.getJumlahitemsperkoli() != null && cp.getJumlahitemsperkoli().intValue() > 0) {
                    Double koli = Math.abs(totalEkor) / cp.getJumlahitemsperkoli().doubleValue();
                    if(koli.doubleValue() > 0 && koli.doubleValue() < 1){
                        koli = 1.0;
                    }
                    totalKoli = new BigDecimal(koli).setScale(2, RoundingMode.DOWN).doubleValue();
                }
                grandTotalTotalKoli += totalKoli;

                PrintDataStockAdjusmentHidupDanMati data = new PrintDataStockAdjusmentHidupDanMati();
                data.setCabang(namaCabang);
                data.setUkuran(cp.getSize());
                data.setGram(cp.getWeightfromingram() + " - " + cp.getWeighttoingram());
                data.setPatokanperkoli(cp.getJumlahitemsperkoli().longValue());
                data.setStockkolamterakhir(stockKolamTerakhir);
                data.setUdangmati(stockUdangMati);
                data.setUdangmasuk(stockUdangMasuk);
                data.setTotalekor(totalEkor);
                data.setTotalkoli(totalKoli);
                listData.add(data);
            }
        }

        // Category product yang tidak masuk mapping
        for (CategoryProductList cp : listCP) {
            if (done.containsKey(cp.getId())) continue;

            long stockKolamTerakhir = stockKolamTerakhirByIDcategory.getOrDefault(cp.getId(), 0L);
            long stockUdangMati     = stockUdangMatiByIDcategory.getOrDefault(cp.getId(), 0L);
            long stockUdangMasuk    = stockUdangMasukByIDcategory.getOrDefault(cp.getId(), 0L);
            long stockTotalEkor    = stockTotalEkorByIDcategory.getOrDefault(cp.getId(), 0L);

            grandTotalStockKolamTerakhir += stockKolamTerakhir;
            grandTotalUdangMati          += stockUdangMati;
            grandTotalUdangMasuk         += stockUdangMasuk;
            grandTotalTotalEkor         += stockTotalEkor;

            long totalEkor = stockTotalEkor;
//            long totalEkor = stockKolamTerakhir + stockUdangMasuk - stockUdangMati;
//            grandTotalTotalEkor += totalEkor;

            Double totalKoli = 0.00;
            if (cp.getJumlahitemsperkoli() != null && cp.getJumlahitemsperkoli().intValue() > 0) {
                Double koli = Math.abs(totalEkor) / cp.getJumlahitemsperkoli().doubleValue();
                if(koli.doubleValue() > 0 && koli.doubleValue() < 1){
                    koli = 1.0;
                }
                totalKoli = new BigDecimal(koli).setScale(2, RoundingMode.DOWN).doubleValue();
            }
            grandTotalTotalKoli += totalKoli;

            PrintDataStockAdjusmentHidupDanMati data = new PrintDataStockAdjusmentHidupDanMati();
            data.setCabang(namaCabang);
            data.setUkuran(cp.getSize());
            data.setGram(cp.getWeightfromingram() + " - " + cp.getWeighttoingram());
            data.setPatokanperkoli(cp.getJumlahitemsperkoli().longValue());
            data.setStockkolamterakhir(stockKolamTerakhir);
            data.setUdangmati(stockUdangMati);
            data.setUdangmasuk(stockUdangMasuk);
            data.setTotalekor(totalEkor);
            data.setTotalkoli(totalKoli);
            listData.add(data);
        }

        // Baris grand total
        PrintDataStockAdjusmentHidupDanMati total = new PrintDataStockAdjusmentHidupDanMati();
        total.setCabang(namaCabang);
        total.setUkuran("TOTAL");
        total.setGram("");
        total.setPatokanperkoli(0L);
        total.setStockkolamterakhir(grandTotalStockKolamTerakhir);
        total.setUdangmati(grandTotalUdangMati);
        total.setUdangmasuk(grandTotalUdangMasuk);
        total.setTotalekor(grandTotalTotalEkor);
        total.setTotalkoli(grandTotalTotalKoli);
        listData.add(total);

        return listData;
    }

    @Override
    public List<Long> checkIdCP(Long idcompany, Long idbranch, Long idcategoryProduct) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCheckIdCategoryProduct().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and items.idcategoryproduct = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch,idcategoryProduct};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCheckIdCategoryProduct(), queryParameters);
    }
    @Override
    public ReturnData catatDownload(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            StockAdjusment table = repo.getById(id);
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
                long count = 1;
                for(BodyStockAdjusmentItem val : items){
                    String keyMaps = idstockadjusment+val.getIdcategoryproduct()+val.getIdproduct()+val.getType();
                    StockAdjusmentItemPK itemsPK = new StockAdjusmentItemPK();
                    itemsPK.setIdstockadjusment(idstockadjusment);
                    itemsPK.setIdcategoryproduct(val.getIdcategoryproduct());
                    itemsPK.setIdproduct(val.getIdproduct());
                    itemsPK.setType(val.getType());
                    itemsPK.setCounting(count);
                    StockAdjusmentItem table = new StockAdjusmentItem();
                    table.setStockAdjusmentItemPK(itemsPK);
                    table.setQty(val.getQty());
                    table.setPrice(val.getPrice());
                    table.setSubtotalprice(val.getSubtotalprice());
                    table.setStocktime(val.getStocktime());
                    stockAdjusmentItemRepo.saveAndFlush(table);
                    count++;
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
