package com.servlet.draftpurchasereceive.handler;

import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.draftpurchasereceive.entity.*;
import com.servlet.draftpurchasereceive.mapper.*;
import com.servlet.draftpurchasereceive.repo.DraftPurchaseReceiveItemsRepo;
import com.servlet.draftpurchasereceive.repo.DraftPurchaseReceiveRepo;
import com.servlet.draftpurchasereceive.service.DraftPurchaseReceiveService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.komisi.entity.Komisi;
import com.servlet.komisi.entity.KomisiList;
import com.servlet.komisi.mapper.QueryKomisiList;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.PurchaseReceiveDataList;
import com.servlet.purchasereceive.mapper.QueryCalculateQty;
import com.servlet.purchasereceive.mapper.QueryPRReportKartuStock;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.*;
import com.servlet.stockitems.entity.ReportKartuStock;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
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
    @Autowired
    private PurchaseReceiveService purchaseReceiveService;

    @Autowired
    private UserAppsService userAppsService;

    protected final String namaMenu = "DraftPurchaseReceive";

    @Override
    public List<DraftPurchaseReceiveList> getList(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if (param.getFrom() != null) {
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '" + dt.toString() + "'");
        }
        if (param.getTo() != null) {
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '" + dt.toString() + "'");
        }
        if (param.getOnlyShowNotInLinkedPR() != null) {
            if (param.getOnlyShowNotInLinkedPR().booleanValue()) {
                sqlBuilder.append(" and data.id not in (select pr.iddraftpurchasereceive from purchasereceive as pr where pr.idcompany = " + idcompany + " and pr.idbranch = " + idbranch + " and pr.isdelete = false and pr.iddraftpurchasereceive notnull) ");
            }
        }
        final Object[] queryParameters = new Object[]{idcompany, idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public DraftPurchaseReceiveDetailData getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[]{id, idcompany, idbranch};
        List<DraftPurchaseReceiveDetailData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if (list != null && list.size() > 0) {
            DraftPurchaseReceiveDetailData data = list.get(0);
            data.setItems(getListItems(id, idcompany, idbranch));
            return data;
        }
        return null;
    }

    @Override
    public DraftPurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch) {
        DraftPurchaseReceiveTemplate template = new DraftPurchaseReceiveTemplate();
        template.setVendorOpt(vendorService.getListDropdown(idcompany, idbranch));
        template.setProductOpt(productService.getListAll(idcompany, idbranch));
        return template;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDraftPurchaseReceive body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DRAFTPURCHASERECEIVE, ts);
        if (docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER, "Gagal Generate Document Number");
            validations.add(msg);
        }

        if(validations.size() == 0){
            /*
                REQUEST 17 Desember 2025
                Pada input penerimaan barang dilakukan pengecekan jika sudah ada vendor yang sama di tanggal yang sama , maka di TOLAK jika mau add lagi.
             */
            List<DraftPurchaseReceiveDropDownList> listcheck = checkIdVendorAndDate(idcompany,idbranch,body);
            if(listcheck != null && listcheck.size() > 0){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_DO_TRANS_IN_THE_SAME_TIME_ONLY_ONE_TRANS, "Vendor Sudah melakukan transaksi pada tanggal tersebut");
                validations.add(msg);
            }
        }
        if (validations.size() == 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                DraftPurchaseReceive table = new DraftPurchaseReceive();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setIdvendor(body.getIdvendor());
                if (body.getArriveltime() != null) {
                    long msArrival = sdf.parse(body.getArriveltime()).getTime();
                    Time arrival = new Time(msArrival);
                    table.setArriveltime(arrival);
                } else {
                    table.setArriveltime(null);
                }

                if (body.getReceivetime() != null) {
                    long msReceive = sdf.parse(body.getReceivetime()).getTime();
                    Time receive = new Time(msReceive);
                    table.setReceivetime(receive);
                } else {
                    table.setReceivetime(null);
                }
                table.setFlightno(body.getFlightno());
                table.setNotes1(body.getNotes1());
                table.setNotes2(body.getNotes2());
                table.setSmu(body.getSmu());
                table.setBox(body.getBox());
                table.setTotalekor(body.getTotalekor());
                table.setTotalkg(body.getTotalkg());
                table.setPersentase(body.getPersentase());
                table.setIsdelete(false);
                table.setCreatedby(iduser);
                table.setCreateddate(ts);
                idsave = repo.saveAndFlush(table).getId();
                HashMap<Object, Object> mapsItems = setItems(idcompany, idbranch, idsave, body.getItems());
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if (validationsItems.size() == 0) {
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = " + data + " | Items = " + dataItems;
                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "ADD", namaMenu, mixData, "", "", ts);
                } else {
                    repo.deleteById(idsave);
                    repoItems.deleteAllDetailByIdDraftPurchaseReceive(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DRAFTPURCHASERECEIVE);
                    validations.add(validationsItems.get(0));
                }

                String data = table.toString();
                historyAppsService.saveHistory(idcompany, idbranch, iduser, "ADD", namaMenu, data, "", "", ts);

            } catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DRAFTPURCHASERECEIVE);
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if (validations.size() > 0) {
            historyAppsService.saveHistory(idcompany, idbranch, iduser, "ADD_ERROR", namaMenu, validations.get(0).getMessage(), "", "", ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0 ? false : true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyDraftPurchaseReceive body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        PurchaseReceiveDataList pr = purchaseReceiveService.getDataByIdDratPurchaseReceive(id, idcompany, idbranch);
        if (pr != null) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PURCHASERECEIVE, "draft ini terpasang pada purchase receive (" + pr.getNodocument() + ")");
            validations.add(msg);
        }
        DraftPurchaseReceive table = repo.getById(id);

        if(validations.size() == 0){
            /*
                REQUEST 17 Desember 2025
                Pada input penerimaan barang dilakukan pengecekan jika sudah ada vendor yang sama di tanggal yang sama , maka di TOLAK jika mau add lagi.
             */
            try {
                String docDateDB = GlobalFunc.getDateLongToString(table.getDate().getTime(), "yyyy-MM-dd");
                String docDateBody = GlobalFunc.getDateLongToString(body.getDate(), "yyyy-MM-dd");
                if (!docDateDB.equals(docDateBody)) {
                    List<DraftPurchaseReceiveDropDownList> listcheck = checkIdVendorAndDate(idcompany, idbranch, body);
                    if (listcheck != null && listcheck.size() > 0) {
                        ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_DO_TRANS_IN_THE_SAME_TIME_ONLY_ONE_TRANS, "Vendor Sudah melakukan transaksi pada tanggal tersebut");
                        validations.add(msg);
                    }
                }
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        if (validations.size() == 0) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                if (table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    String dataBefore = table.toString();
                    List<DraftPurchaseReceiveItemNotJoin> listitems = getListItemsNotJoin(id, idcompany, idbranch);
                    String dataItemsBefore = listitems.toString();
                    String mixDataBefore = "header = " + dataBefore + " | Items = " + dataItemsBefore;

                    table.setDate(new Date(body.getDate()));
                    table.setIdvendor(body.getIdvendor());
                    if (body.getArriveltime() != null) {
                        long msArrival = sdf.parse(body.getArriveltime()).getTime();
                        Time arrival = new Time(msArrival);
                        table.setArriveltime(arrival);
                    } else {
                        table.setArriveltime(null);
                    }

                    if (body.getReceivetime() != null) {
                        long msReceive = sdf.parse(body.getReceivetime()).getTime();
                        Time receive = new Time(msReceive);
                        table.setReceivetime(receive);
                    } else {
                        table.setReceivetime(null);
                    }

                    table.setFlightno(body.getFlightno());
                    table.setNotes1(body.getNotes1());
                    table.setNotes2(body.getNotes2());
                    table.setBox(body.getBox());
                    table.setSmu(body.getSmu());
                    table.setTotalekor(body.getTotalekor());
                    table.setTotalkg(body.getTotalkg());
                    table.setPersentase(body.getPersentase());
                    table.setBox(body.getBox());
                    table.setModifiedby(iduser);
                    table.setModifieddate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    repoItems.deleteAllDetailByIdDraftPurchaseReceive(id);

                    HashMap<Object, Object> mapsItems = setItems(idcompany, idbranch, idsave, body.getItems());
                    List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                    if (validationsItems.size() == 0) {
                        String data = table.toString();
                        String dataItems = (String) mapsItems.get("dataItems");
                        String mixData = "header = " + data + " | Items = " + dataItems;
                        historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, "", mixData, mixDataBefore, ts);
                    } else {
                        validations.add(validationsItems.get(0));
                    }
                }
            } catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }

        if (validations.size() > 0) {
            historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT_ERROR", namaMenu, validations.get(0).getMessage(), "", "", ts);
        }

        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0 ? false : true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        PurchaseReceiveDataList pr = purchaseReceiveService.getDataByIdDratPurchaseReceive(id, idcompany, idbranch);
        if (pr != null) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PURCHASERECEIVE, "draft ini terpasang pada purchase receive (" + pr.getNodocument() + ")");
            validations.add(msg);
        }
        if (validations.size() == 0) {
            try {
                DraftPurchaseReceive table = repo.getById(id);
                table.setIsdelete(true);
                table.setDeleteby(iduser);
                table.setDeletedate(ts);
                idsave = repo.saveAndFlush(table).getId();
                historyAppsService.saveHistory(table.getIdcompany(), table.getIdbranch(), iduser, "DELETE", namaMenu, table.toString(), "", "", ts);
            } catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }

        }

        if (validations.size() > 0) {
            historyAppsService.saveHistory(idcompany, idbranch, iduser, "DELETE_ERROR", namaMenu, validations.get(0).getMessage(), "", "", ts);
        }

        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0 ? false : true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public SearchDataTemplateByVendor getTemplateByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        ParamTemplate paramCategoryProduct = new ParamTemplate();
        paramCategoryProduct.setMenu("DRAFTPURCHASE_RECEIVE");
        paramCategoryProduct.setForcategory("VENDOR");
        paramCategoryProduct.setIdvendor(idvendor);

        SearchDataTemplateByVendor data = new SearchDataTemplateByVendor();
        data.setCategoryproductOpt(categoryProductService.getDataForTemplate(idcompany, idbranch, paramCategoryProduct));
        return data;
    }

    @Override
    public boolean checkIDVendor(Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryColumnIDVendor().schema());
        sqlBuilder.append(" where data.idvendor = ? and data.isdelete = false limit 1 ");
        final Object[] queryParameters = new Object[]{idvendor};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryColumnIDVendor(), queryParameters);
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<DraftPurchaseReceiveDropDownList> getDropDownList(Long idcompany, Long idbranch, ParamGetDataDraftPR param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDropDownData().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        if (param.getMenu().equals("PURCHASERECEIVE")) {
            sqlBuilder.append(" and data.idvendor = " + param.getIdvendor());
            sqlBuilder.append(" and data.id not in (select pr.iddraftpurchasereceive from purchasereceive as pr where pr.idcompany = " + idcompany + " and pr.idbranch = " + idbranch + " and pr.idvendor = " + param.getIdvendor() + " and pr.isdelete = false and pr.iddraftpurchasereceive notnull ) ");
        }
        final Object[] queryParameters = new Object[]{idcompany, idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDropDownData(), queryParameters);
    }

    @Override
    public List<DraftPurchaseReceiveItemsDetailData> getListItemsByID(Long iddraftpurchasereceive) {
        return getListItems(iddraftpurchasereceive, null, null);
    }

    @Override
    public List<DraftPurchaseReceiveItemsDetailData> getListItemsByIDForPR(Long iddraftpurchasereceive) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataItemsDetail().schema());
        sqlBuilder.append(" where data.iddraftpurchasereceive = ?  ");
        sqlBuilder.append(" order by cp.weightfromingram desc  ");
        final Object[] queryParameters = new Object[]{iddraftpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataItemsDetail(), queryParameters);
    }

    @Override
    public List<DraftPurchaseReceiveList> getListNotLinksInPR(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        String selectidPR = "select id from purchasereceive as pr where pr.idcompany = " + idcompany + " and pr.idbranch = " + idbranch + " and pr.isdelete = false ";
        selectidPR += " and pr.iddraftpurchasereceive notnull ";
        sqlBuilder.append(" and data.id not in (" + selectidPR + ")  ");
//        if(param.getFrom() != null){
//            Date dt = new Date(param.getFrom());
//            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
//        }
//        if(param.getTo() != null){
//            Date dt = new Date(param.getTo());
//            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
//        }

        final Object[] queryParameters = new Object[]{idcompany, idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public PrintDataDraftPR printDataDraftPR(Long idcompany, Long idbranch, Long iduser, Long id) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintDraftPR().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[]{id, idcompany, idbranch};
        List<PrintDataDraftPR> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintDraftPR(), queryParameters);
        if (list != null && list.size() > 0) {
            PrintDataDraftPR print = list.get(0);

            print.setItems(getListItems(id, idcompany, idbranch));
            HashMap mapParamPrint = new HashMap();
            mapParamPrint.put("data-id", id);
            print.setCountPrint(historyAppsService.countByActionAndMenuParam(idcompany, idbranch, "DOWNLOADPDF", namaMenu, mapParamPrint));
//            print.setCountPrint(historyAppsService.countByActionAndMenu(idcompany,idbranch,"DOWNLOADPDF",namaMenu));
            print.setCountEdit(historyAppsService.countByActionAndMenu(idcompany, idbranch, "EDIT", namaMenu));
            if (iduser != null) {
                UserListData user = userAppsService.getUserByID(iduser);
                String namaUser = "";
                if (user != null) {
                    namaUser = user.getNama();
                }
                print.setNamaUser(namaUser);
            }
//            catatDownload(id,idcompany,idbranch,iduser);
            return print;
        }
        return null;
    }

    @Override
    public Long calculateQtyDpr(Long idcompany, Long idbranch, ParamCalculateQtyDPR param) {
        String selectidPr = " select pr.id from draft_purchasereceive as pr where pr.idcompany = " + idcompany + " and pr.idbranch = " + idbranch + " and pr.isdelete = false ";
        if (param.getDateFrom() != null) {
            Date dt = new Date(param.getDateFrom());
            selectidPr += " and pr.date >= '" + dt.toString() + "' ";
        }
        if (param.getDateThru() != null) {
            Date dt = new Date(param.getDateThru());
            selectidPr += " and pr.date <= '" + dt.toString() + "' ";
        }
        if (param.getIdvendor() != null) {
            selectidPr += " and pr.idvendor = " + param.getIdvendor() + " ";
        }
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateQtyDpr().schema());
        sqlBuilder.append(" where data.type = 'H' and data.iddraftpurchasereceive in (" + selectidPr + ") ");
        if (param.getIdcategoryproduct() != null) {
            sqlBuilder.append(" and data.idcategoryproduct = " + param.getIdcategoryproduct() + "  ");
        }
        if (param.getListidcategoryproduct() != null && !param.getListidcategoryproduct().equals("")) {
            sqlBuilder.append(" and data.idcategoryproduct in (" + param.getListidcategoryproduct() + ") ");
        }
        if (param.getIdproduct() != null) {
            sqlBuilder.append(" and data.idproduct = " + param.getIdproduct() + "  ");
        }
        if (param.getListidproduct() != null && !param.getListidproduct().equals("")) {
            sqlBuilder.append(" and data.idproduct in (" + param.getListidproduct() + ") ");
        }

        final Object[] queryParameters = new Object[]{};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateQtyDpr(), queryParameters);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return 0L;
    }

    @Override
    public List<ReportKartuStock> getListDprReportKartuStock(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDPRReportKartuStock().schema());
        sqlBuilder.append(" where pr.idcompany = ? and pr.idbranch = ? and pr.isdelete = false  ");
        if (param.getFrom() != null) {
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and pr.date >= '" + dt.toString() + "'");
        }
        if (param.getTo() != null) {
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and pr.date <= '" + dt.toString() + "'");
        }

        if (param.getListIdProduct() != null && !param.getListIdProduct().equals("")) {
            sqlBuilder.append(" and data.idproduct in (" + param.getListIdProduct() + ") ");
        }
        if (param.getListIdCategoryProduct() != null && !param.getListIdCategoryProduct().equals("")) {
            sqlBuilder.append(" and data.idcategoryproduct in (" + param.getListIdCategoryProduct() + ") ");
        }

        if (param.getType() != null && !param.getType().equals("")) {
            sqlBuilder.append(" and data.type = '" + param.getType() + "' ");
        }
        sqlBuilder.append(" and data.ekor > 0 ");

        sqlBuilder.append(" GROUP BY  data.idproduct, mp.categoryproductidmapping, pr.nodocument,pr.notes1,ven.nama, ven.alias, pr.date ");

        System.out.println("sqlBuilder " + sqlBuilder.toString());
        final Object[] queryParameters = new Object[]{idcompany, idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDPRReportKartuStock(), queryParameters);
    }

    @Override
    public List<Long> checkIdCP(Long idcompany, Long idbranch, Long idcategoryProduct) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCheckIdCategoryProduct().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and items.idcategoryproduct = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[]{idcompany, idbranch, idcategoryProduct};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCheckIdCategoryProduct(), queryParameters);
    }

    @Override
    public ReturnData catatDownload(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try {
            DraftPurchaseReceive table = repo.getById(id);
            historyAppsService.saveHistory(table.getIdcompany(), table.getIdbranch(), iduser, "DOWNLOADPDF", namaMenu, id.toString(), "", "", ts);
        } catch (Exception e) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }

        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0 ? false : true);
        data.setValidations(validations);
        return data;
    }


    private HashMap<Object, Object> setItems(Long idcompany, Long idbranch, Long iddraftpurchasereceive, BodyDraftPurchaseReceiveItems[] items) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object, Object> maps = new HashMap<>();
        List<BodyDraftPurchaseReceiveItems> listitem = new ArrayList<>();
        try {
            if (items.length > 0) {
                for (BodyDraftPurchaseReceiveItems val : items) {
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
        } catch (Exception e) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }
        String dataItems = listitem.toString();
        maps.put("validations", validations);
        maps.put("dataItems", dataItems);
        return maps;
    }

    private List<DraftPurchaseReceiveItemsDetailData> getListItems(Long iddraftpurchasereceive, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataItemsDetail().schema());
        sqlBuilder.append(" where data.iddraftpurchasereceive = ?  ");
        sqlBuilder.append(" order by data.boxsequence asc  ");
        final Object[] queryParameters = new Object[]{iddraftpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataItemsDetail(), queryParameters);
    }

    private List<DraftPurchaseReceiveItemNotJoin> getListItemsNotJoin(Long iddraftpurchasereceive, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataItemsNotJoin().schema());
        sqlBuilder.append(" where data.iddraftpurchasereceive = ?  ");
        final Object[] queryParameters = new Object[]{iddraftpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataItemsNotJoin(), queryParameters);
    }

    private List<DraftPurchaseReceiveDropDownList> checkIdVendorAndDate(Long idcompany, Long idbranch, BodyDraftPurchaseReceive body) {
        try {
            String docDate = GlobalFunc.getDateLongToString(body.getDate(), "yyyy-MM-dd");
            final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDropDownData().schema());
            sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
            sqlBuilder.append(" and data.idvendor = " + body.getIdvendor());
            sqlBuilder.append(" and data.date = '"+docDate+"' ");
            final Object[] queryParameters = new Object[]{idcompany, idbranch};
            return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDropDownData(), queryParameters);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
