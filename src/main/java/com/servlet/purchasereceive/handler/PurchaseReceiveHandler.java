package com.servlet.purchasereceive.handler;

import com.servlet.area.service.AreaService;
import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.charge.service.ChargeService;
import com.servlet.chartofaccount.AccountCOAEnum;
import com.servlet.deposit.entity.*;
import com.servlet.deposit.service.DepositService;
import com.servlet.draftpurchasereceive.entity.ParamGetDataDraftPR;
import com.servlet.draftpurchasereceive.entity.ParamSearchDraftPurchaseReceive;
import com.servlet.draftpurchasereceive.service.DraftPurchaseReceiveService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.inventori.service.InventoriService;
import com.servlet.journal.entity.PostingJournalParam;
import com.servlet.journal.entity.SaldoJournal;
import com.servlet.journal.entity.SaldoJournalParam;
import com.servlet.journal.entity.SourceTypeEnum;
import com.servlet.journal.service.JournalService;
import com.servlet.komisi.entity.KomisiItemJoinHeader;
import com.servlet.komisi.entity.ParamKomisi;
import com.servlet.komisi.service.KomisiService;
import com.servlet.mappingstock.entity.MappingStockCategoryID;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.parameterclient.entity.ValueParameter;
import com.servlet.parameterclient.service.ParameterClientService;
import com.servlet.pelunasanhutang.entity.FilterParamPelunasanHutang;
import com.servlet.pelunasanhutang.entity.PelunasanHutangDataNotJoin;
import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;
import com.servlet.pelunasanhutang.service.PelunasanHutangService;
import com.servlet.pinjaman.entity.ReportKartuPinjaman;
import com.servlet.pinjaman.service.PinjamanService;
import com.servlet.pricelist.service.PriceService;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.*;
import com.servlet.purchasereceive.mapper.*;
import com.servlet.purchasereceive.repo.*;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.report.entity.ParamReportPembelian;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.*;
import com.servlet.stockitems.entity.ReportKartuStock;
import com.servlet.stockitems.service.StockItemService;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
import com.servlet.vendor.entity.ParamVendor;
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
    private PurchaseReceiveDepositRepo purchaseReceiveDepositRepo;

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
    @Autowired
    private DraftPurchaseReceiveService draftPurchaseReceiveService;

    @Autowired
    private PelunasanHutangService pelunasanHutangService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private KomisiService komisiService;

    @Autowired
    private PinjamanService pinjamanService;

    @Autowired
    private JournalService journalService;

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
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);

    }

    @Override
    public PurchaseReceiveAllTabData getListAllTab(Long idcompany, Long idbranch, Long from, Long to) {
        PurchaseReceiveAllTabData data = new PurchaseReceiveAllTabData();
        data.setListPr(getListAll(idcompany,idbranch,from,to));

        ParamSearchDraftPurchaseReceive paramDpr = new ParamSearchDraftPurchaseReceive();
        paramDpr.setFrom(from);
        paramDpr.setTo(to);
        paramDpr.setOnlyShowNotInLinkedPR(true);
        data.setListDpr(draftPurchaseReceiveService.getList(idcompany,idbranch,paramDpr));
        return data;
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

            //untuk tsCd ini sengaja, soalnya pas query di sql, walaupun sama, tapi ga ke detect
            //jadi solusinya di tambahin sedikit, sekitar beberapa 1 detik, biar sedikit lebih gede
//            Timestamp tsCd = data.getCreateddate();
//
//            // ambil millisecond
//            int ms = tsCd.getNanos() / 1000000;
//
//            // reset ke detik
//            tsCd.setNanos(0);
//
//            // jika ada ms → naikkan, 1 detik = 1000
//            if (ms > 0) {
//                tsCd.setTime(tsCd.getTime() + 1000);
//            }
            Timestamp tsCd = GlobalFunc.getTimeForCalcSaldo(data.getCreateddate());

            SaldoJournalParam paramDeposit = new SaldoJournalParam();
            paramDeposit.setIdcompany(idcompany);
            paramDeposit.setIdbranch(idbranch);
            paramDeposit.setIdvendor(data.getIdvendor());
            paramDeposit.setAccountCode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
            paramDeposit.setTransaksiTime(tsCd.toString());
            SaldoJournal saldoDeposit = journalService.calculateSaldo(paramDeposit);

            SaldoJournalParam paramPinjaman = new SaldoJournalParam();
            paramPinjaman.setIdcompany(idcompany);
            paramPinjaman.setIdbranch(idbranch);
            paramPinjaman.setIdvendor(data.getIdvendor());
            paramPinjaman.setAccountCode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
            paramPinjaman.setTransaksiTime(tsCd.toString());
            SaldoJournal saldoPinjaman = journalService.calculateSaldo(paramPinjaman);

//            data.setSisaDeposit(depositService.calculateSisaDepositByIdVendor(idcompany,idbranch,data.getIdvendor()));
//            data.setSisaPinjaman(pinjamanService.calculateSisaPinjamanByIdVendor(idcompany,idbranch, data.getIdvendor(),null));
            data.setSisaDeposit(saldoDeposit.getSaldo());
            data.setSisaPinjaman(saldoPinjaman.getSaldo());
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
        data.setAreaOpt(areaService.getList(idcompany,idbranch));
        return data;
    }

    @Override
    public ReportPurchaseReceiveTemplate getReportTemplate(Long idcompany, Long idbranch) {
        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setVendorTypes("'UDANG'");

        ReportPurchaseReceiveTemplate data = new ReportPurchaseReceiveTemplate();
        data.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch,paramVendor));
        data.setAreaOpt(areaService.getList(idcompany,idbranch));
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;

        long iddeposit = 0;
        if(body.getTambahdeposit() != null && body.getTambahdeposit().doubleValue() > 0){
            Timestamp depoDate = new Timestamp(new java.util.Date().getTime());
            BodyDeposit bodyDep = new BodyDeposit();
            bodyDep.setIdvendor(body.getIdvendor());
            bodyDep.setAmount(body.getTambahdeposit());
            bodyDep.setDepositdate(depoDate.getTime());
            ReturnData retDeposit = depositService.save(idcompany,idbranch,iduser,bodyDep);
            if(retDeposit.getValidations().size() > 0){
                validations.add(retDeposit.getValidations().get(0));
            }else{
                iddeposit = retDeposit.getId();
            }

        }

        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PURCHASERECEIVE, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
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
                table.setSetor_pinjaman(body.getSetor_pinjaman());
                table.setOutstanding(body.getTotalprice().doubleValue() - body.getSetor().doubleValue() - body.getSetor_pinjaman().doubleValue());
                table.setIsdefaultvaluesetor(body.isIsdefaultvaluesetor());
                table.setIddeposit(iddeposit);
                table.setIddraftpurchasereceive(body.getIddraftpurchasereceive());
                table.setIdarea(body.getIdarea());
                table.setFlightno(body.getFlightno());
                table.setSmu(body.getSmu());
                table.setNotes2(body.getNotes2());
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = purchaseReceiveRepo.saveAndFlush(table).getId();


                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,body.getCharges(), body.getItems(),body.getInventori(), idsave);
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");

                PostingJournalParam paramPosting = new PostingJournalParam();
                paramPosting.setIdcompany(idcompany);
                paramPosting.setIdbranch(idbranch);
                paramPosting.setAmountPemakaianDeposit(table.getSetor());
                paramPosting.setDescriptionDetailDeposit("");
                paramPosting.setAmountPembayaranPinjaman(table.getSetor_pinjaman());
                paramPosting.setDescriptionDetailPinjaman("");
                paramPosting.setIdvendor(table.getIdvendor());
                paramPosting.setSourcenumber(docNumber);
                paramPosting.setSourcedocumentdate(table.getTransactiondate());
                paramPosting.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
                paramPosting.setTransaksitime(ts);
                paramPosting.setDescription("");
                paramPosting.setCreatedby(iduser);
                List<ValidationDataMessage> validationsPosting = journalService.postingJournal(paramPosting);
                //sengaja set ke validationsItems, karena jika gagal posting, rollback
                validationsItems.addAll(validationsPosting);

                if(validationsItems.size() == 0){
                    HashMap<Object, Object> mapsItemsDeposit = setItemsDeposit(idcompany,idbranch,iduser,idsave, body.getIdvendor());
                    List<ValidationDataMessage> validationsItemsDeposit = (List<ValidationDataMessage>) mapsItemsDeposit.get("validations");
                    String dataItemsDeposit = (String) mapsItemsDeposit.get("dataItems");

                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems+" | Deposit = "+dataItemsDeposit;
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
        }else{
            if(iddeposit > 0){
                depositService.deleteRollBack(iddeposit);
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
        List<PelunasanHutangDataNotJoin> list = pelunasanHutangService.getDataByIdPr(idcompany,idbranch,id);
        if(list != null && list.size() > 0){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PELUNASANHUTANG,"document ini terpasang pada pelunasan hutang ("+list.get(0).getNodocument()+")");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            KomisiItemJoinHeader komisiitems = komisiService.getDetailItemByIdPR(idcompany,idbranch,id);
            if(komisiitems != null){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_KOMISI,"document ini terpasang pada document komisi ("+komisiitems.getNodocument()+") ");
                validations.add(msg);
            }
        }
        if(validations.size() == 0) {
            try {
                PurchaseReceive table = purchaseReceiveRepo.getById(id);
                final PurchaseReceive befTable = table;
                if (table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    List<PurchaseReceiveItemsNotJoin> listItems = getDataItemsNotJoin(id);
                    List<PurchaseReceiveChargeNotJoin> listItemsCharge = getDataItemsChargeNotJoin(id);
                    List<PurchaseReceiveInventoriNotJoin> listItemsInventori = getDataItemsInventoriNotJoin(id);
                    String dataBefore = table.toString();
                    String dataItemsBefore = listItems.toString() + " | " + listItemsCharge.toString() + " | " + listItemsInventori.toString();
                    String mixDataBefore = "header = " + dataBefore + " | Items = " + dataItemsBefore;

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
                    table.setSetor_pinjaman(body.getSetor_pinjaman());
                    table.setIsdefaultvaluesetor(body.isIsdefaultvaluesetor());
                    table.setIdarea(body.getIdarea());
                    table.setOutstanding(body.getTotalprice().doubleValue() - body.getSetor().doubleValue() - body.getSetor_pinjaman().doubleValue());
                    table.setFlightno(body.getFlightno());
                    table.setSmu(body.getSmu());
                    table.setNotes2(body.getNotes2());
                    table.setModifiedby(iduser);
                    table.setModifieddate(ts);
                    idsave = purchaseReceiveRepo.saveAndFlush(table).getId();

                    if(validations.size() == 0){
                        PostingJournalParam paramPosting = new PostingJournalParam();
                        paramPosting.setIdcompany(table.getIdcompany());
                        paramPosting.setIdbranch(table.getIdbranch());
                        paramPosting.setAmountPemakaianDeposit(table.getSetor());
                        paramPosting.setDescriptionDetailDeposit("");
                        paramPosting.setAmountPembayaranPinjaman(table.getSetor_pinjaman());
                        paramPosting.setDescriptionDetailPinjaman("");
                        paramPosting.setIdvendor(table.getIdvendor());
                        paramPosting.setSourcenumber(table.getNodocument());
                        paramPosting.setSourcedocumentdate(table.getTransactiondate());
                        paramPosting.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
                        paramPosting.setTransaksitime(table.getCreateddate());
                        paramPosting.setDescription("");
                        paramPosting.setCreatedby(iduser);

                        List<ValidationDataMessage> validationsPosting = journalService.updateJournalDetail(paramPosting);
                        validations.addAll(validationsPosting);
                        if(validationsPosting.size() > 0){
                            purchaseReceiveRepo.saveAndFlush(befTable);
                        }
                    }
                    if(validations.size() == 0){
                        kurangiStockItems(idcompany, idbranch, id);

                        purchaseReceiveItemsRepo.deleteAllDetailByIdPurchaseReceive(idsave);
                        purchaseReceiveChargeRepo.deleteAllDetailByIdPurchaseReceive(idsave);
                        purchaseReceiveInventoriRepo.deleteAllDetailByIdPurchaseReceiveInventory(idsave);

                        HashMap<Object, Object> mapsItems = setItems(idcompany, idbranch, body.getCharges(), body.getItems(), body.getInventori(), idsave);
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

                }
            } catch (Exception e) {
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
    public ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());

        List<PelunasanHutangDataNotJoin> list = pelunasanHutangService.getDataByIdPr(idcompany,idbranch,id);
        if(list != null && list.size() > 0){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PELUNASANHUTANG,"document ini terpasang pada pelunasan hutang ("+list.get(0).getNodocument()+")");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            KomisiItemJoinHeader komisiitems = komisiService.getDetailItemByIdPR(idcompany,idbranch,id);
            if(komisiitems != null){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_KOMISI,"document ini terpasang pada document komisi ("+komisiitems.getNodocument()+") ");
                validations.add(msg);
            }
        }
        if(validations.size() == 0) {
            try {
                PurchaseReceive table = purchaseReceiveRepo.getById(id);
                if (table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    table.setIsdelete(true);
                    table.setDeleteby(iduser);
                    table.setDeletedate(ts);
                    idsave = purchaseReceiveRepo.saveAndFlush(table).getId();

                    journalService.deleteJournalBySourceNumber(table.getNodocument());

                    aktivasiDeposit(idcompany,idbranch,iduser,id);

                    kurangiStockItems(idcompany, idbranch, id);
                    historyAppsService.saveHistory(table.getIdcompany(), table.getIdbranch(), iduser, "DELETE", namaMenu, table.toString(), "", "", ts);
                }
            } catch (Exception e) {
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
        paramCategoryProduct.setForcategory("VENDOR");
        paramCategoryProduct.setIdvendor(idvendor);
        data.setCategoryproductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,paramCategoryProduct));

        SaldoJournalParam paramDeposit = new SaldoJournalParam();
        paramDeposit.setIdcompany(idcompany);
        paramDeposit.setIdbranch(idbranch);
        paramDeposit.setIdvendor(idvendor);
        paramDeposit.setAccountCode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
        SaldoJournal saldoDeposit = journalService.calculateSaldo(paramDeposit);

        SaldoJournalParam paramPinjaman = new SaldoJournalParam();
        paramPinjaman.setIdcompany(idcompany);
        paramPinjaman.setIdbranch(idbranch);
        paramPinjaman.setIdvendor(idvendor);
        paramPinjaman.setAccountCode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
        SaldoJournal saldoPinjaman = journalService.calculateSaldo(paramPinjaman);

//        data.setSisaDeposit(depositService.calculateSisaDepositByIdVendor(idcompany,idbranch,idvendor));
//        data.setSisaPinjaman(pinjamanService.calculateSisaPinjamanByIdVendor(idcompany,idbranch,idvendor,null));

        data.setSisaDeposit(saldoDeposit.getSaldo());
        data.setSisaPinjaman(saldoPinjaman.getSaldo());

        ParamGetDataDraftPR paramDraftPR = new ParamGetDataDraftPR();
        paramDraftPR.setIdvendor(idvendor);
        paramDraftPR.setMenu("PURCHASERECEIVE");
        data.setDraftPurchaseReceiveOpt(draftPurchaseReceiveService.getDropDownList(idcompany,idbranch,paramDraftPR));
        return data;
    }

    @Override
    public Double calculateSetorByIdVendor(Long idcompany, Long idbranch, Long idvendor,String listidvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountSetor().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false ");
        if(idvendor != null){
            sqlBuilder.append(" and data.idvendor = "+idvendor+" ");
        }
        if(listidvendor != null && !listidvendor.equals("")){
            sqlBuilder.append(" and data.idvendor in ("+listidvendor+") ");
        }

        final Object[] queryParameters = new Object[] {idcompany};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountSetor(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public Double calculateSetorPinjamanByIdVendor(Long idcompany, Long idbranch, Long idvendor,String listidvendor, Date date) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountSetorPinjaman().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false ");
        if(idvendor != null){
            sqlBuilder.append(" and data.idvendor = "+idvendor+" ");
        }
        if(listidvendor != null && !listidvendor.equals("")){
            sqlBuilder.append(" and data.idvendor in ("+listidvendor+") ");
        }
        if(date != null){
            sqlBuilder.append(" and data.transactiondate < '"+date+"' ");
        }
        final Object[] queryParameters = new Object[] {idcompany};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountSetorPinjaman(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public PrintDataPurchaseReceive printNotaPurchaseReceive(Long idcompany, Long idbranch, Long iduser, Long id,String printtype) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintPurchaseReceive().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PrintDataPurchaseReceive> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintPurchaseReceive(), queryParameters);
        if(list != null && list.size() > 0){
            if(printtype.equals("SUPPLIER") || printtype.equals("PAJAK")){
                return getPrintDataNotaSupplier(id,iduser,idcompany,idbranch,list.get(0));
            }
//            else if(printtype.equals("INTERNAL") || printtype.equals("PAJAK")){
//                return getPrintDataNotaInternalAndPajak(id,iduser,idcompany,idbranch,list.get(0),printtype);
//            }
        }
        return null;
    }

    private PrintDataPurchaseReceive getPrintDataNotaInternalAndPajak(Long id,Long iduser, Long idcompany,Long idbranch,PrintDataPurchaseReceive value,String printtype){
        ValueParameter param = parameterClientService.getValueByParamName(idcompany,idbranch,"COMPANYNAME","TEXT");
        PrintDataPurchaseReceive print = value;
        print.setSisaDeposit(0.0);
        print.setSaldoDepositBeforeNotaSubmit(0.0);
        print.setCountPrint(historyAppsService.countByActionAndMenu(idcompany,idbranch,"DOWNLOADNOTA",namaMenu));
        print.setCountEdit(historyAppsService.countByActionAndMenu(idcompany,idbranch,"EDIT",namaMenu));
        print.setCompanyName(param.getStrValue());
        UserListData user = userAppsService.getUserByID(iduser);
        String namaUser  = "";
        if(user != null){
            namaUser = user.getNama();
        }
        print.setNamaUser(namaUser);

        List<PrintDataPurchaseReceiveItems> items = getPrintDataItems(id);
        List<PrintDataPurchaseReceiveCharge> charges = new ArrayList<>();//getPrintDataCharge(id);
        List<PrintDataPurchaseReceiveInventori> inventori = new ArrayList<>();//getPrintDataItemsInventori(id);
        double totalPrice = 0.0;
        double totalPricePajak = 0.0;
        List<PrintDataPurchaseReceiveItems> listitems = new ArrayList<>();
        for(PrintDataPurchaseReceiveItems val : items){
            if(val.getType().equals("H")){
                double price = val.getPrice().doubleValue();
                long qty = val.getQty().longValue();
                double subprice = price * qty;
                totalPrice += subprice;
                totalPricePajak += val.getSubtotalprice().doubleValue();

                PrintDataPurchaseReceiveItems temps = new PrintDataPurchaseReceiveItems();
                temps.setIdpurchasereceive(val.getIdpurchasereceive());
                temps.setIdproduct(val.getIdproduct());
                temps.setProductName(val.getProductName());
                temps.setIdcategoryproduct(val.getIdcategoryproduct());
                temps.setCategoryProductName(val.getCategoryProductName());
                temps.setSize(val.getSize());
                temps.setWeightto(val.getWeightto());
                temps.setWeightfrom(val.getWeightfrom());
                temps.setType(val.getType());
                temps.setQty(qty);
                if(printtype.equals("INTERNAL")){
                    temps.setQtybonus(0l);
                    temps.setSubtotalprice(subprice);
                }else if(printtype.equals("PAJAK")){
                    temps.setQtybonus(val.getQtybonus());
                    temps.setSubtotalprice(val.getSubtotalprice());
                }
                temps.setPrice(price);

                listitems.add(temps);
            }else{
                listitems.add(val);
            }

        }
        if(printtype.equals("INTERNAL")){
            print.setTotalprice(totalPrice);
        }else if(printtype.equals("PAJAK")){
            print.setTotalprice(totalPricePajak);
        }
        print.setItems(listitems);

        print.setCharges(charges);
        print.setInventori(inventori);

        catatDownload(id,idcompany,idbranch,iduser);

        return print;
    }
    private PrintDataPurchaseReceive getPrintDataNotaSupplier(Long id,Long iduser, Long idcompany,Long idbranch,PrintDataPurchaseReceive value){
        ValueParameter param = parameterClientService.getValueByParamName(idcompany,idbranch,"COMPANYNAME","TEXT");

        PrintDataPurchaseReceive print = value;
        List<PurchaseReceiveDepositData> listdeposit = getListPurchaseReceiveDepositByIdPR(id);
        List<Long> listIdDeposit = new ArrayList<>();
        if(listdeposit != null && listdeposit.size() > 0){
            for(PurchaseReceiveDepositData depo : listdeposit){
                listIdDeposit.add(depo.getIddeposit());
            }
        }
        String iddeposits = "";
        if(listIdDeposit.size() > 0){
            iddeposits = listIdDeposit.toString().replaceAll("\\[","");
            iddeposits = iddeposits.replaceAll("\\]","");
        }
//        print.setSisaDeposit(depositService.calculateSisaDepositByIdVendor(idcompany,idbranch, print.getIdvendor()));
        print.setItems(getPrintDataItems(id));
        print.setDeposits(listdeposit);
        print.setCharges(getPrintDataCharge(id));
        print.setCompanyName(param.getStrValue());
        print.setInventori(getPrintDataItemsInventori(id));

        /*
            kenapa saldo dp minus, itu karena Summary(Deposit)  - Summary(Deposit yang terpakai di PRC),
            yang hitung Summary(Deposit). untuk Tambah DP itu dikecualikan atau tidak terhitung,
            makanya jadi minus tuh si Saldo DP. karena si tambahDP itu, hitungnya di depan,
         */
//        ParamCalculateDeposit paramCalcDeposit = new ParamCalculateDeposit();
//        paramCalcDeposit.setDate(print.getTransactiondate().getTime());
//        paramCalcDeposit.setIdvendor(print.getIdvendor());
//        paramCalcDeposit.setListNotSUMIdDeposit(iddeposits);
//        Double sd = depositService.calculateSaldoDepositForPrinted(idcompany,idbranch, paramCalcDeposit);
//        print.setSaldoDepositBeforeNotaSubmit(sd);
        /*
        ========================
         */


//        print.setSaldoPinjaman(pinjamanService.calculateSisaPinjamanByIdVendor(idcompany,idbranch,print.getIdvendor(),null));

        //untuk tsCd ini sengaja, soalnya pas query di sql, walaupun sama, tapi ga ke detect
        //jadi solusinya di tambahin sedikit, sekitar beberapa 1 detik, biar sedikit lebih gede
        Timestamp tsCd = print.getCreateddate();

        // ambil millisecond
        int ms = tsCd.getNanos() / 1000000;

        // reset ke detik
        tsCd.setNanos(0);

        // jika ada ms → naikkan, 1 detik = 1000
        if (ms > 0) {
            tsCd.setTime(tsCd.getTime() + 1000);
        }

        SaldoJournalParam paramDeposit = new SaldoJournalParam();
        paramDeposit.setIdcompany(idcompany);
        paramDeposit.setIdbranch(idbranch);
        paramDeposit.setIdvendor(print.getIdvendor());
        paramDeposit.setAccountCode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
        paramDeposit.setTransaksiTime(tsCd.toString());
        SaldoJournal saldoDeposit = journalService.calculateSaldo(paramDeposit);
        Double sisaDeposit = saldoDeposit.getSaldo();
//        if(listdeposit != null && listdeposit.size() > 0){
//            for(PurchaseReceiveDepositData depo : listdeposit){
//                sisaDeposit = sisaDeposit - depo.getAmount();
//            }
//        }
        print.setSisaDeposit(sisaDeposit);
        print.setSaldoDepositBeforeNotaSubmit(sisaDeposit);

        SaldoJournalParam paramPinjaman = new SaldoJournalParam();
        paramPinjaman.setIdcompany(idcompany);
        paramPinjaman.setIdbranch(idbranch);
        paramPinjaman.setIdvendor(print.getIdvendor());
        paramPinjaman.setAccountCode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
        paramPinjaman.setTransaksiTime(tsCd.toString());
        SaldoJournal saldoPinjaman = journalService.calculateSaldo(paramPinjaman);
        print.setSaldoPinjaman(saldoPinjaman.getSaldo());

        HashMap mapParamPrint = new HashMap();
        mapParamPrint.put("nodocument",value.getNodocument());
        print.setCountPrint(historyAppsService.countByActionAndMenuParam(idcompany,idbranch,"DOWNLOADNOTA",namaMenu,mapParamPrint));
        print.setCountEdit(historyAppsService.countByActionAndMenu(idcompany,idbranch,"EDIT",namaMenu));
        UserListData user = userAppsService.getUserByID(iduser);
        String namaUser  = "";
        if(user != null){
            namaUser = user.getNama();
        }
        print.setNamaUser(namaUser);
//        catatDownload(id,idcompany,idbranch,iduser);
        return print;
    }

    @Override
    public Double calculateSetorByIdVendorAndCreatedDate(Long idcompany, Long idbranch, Long idvendor, Long date,String listidvendor) {
//        Timestamp dt = new Timestamp(date);
        Date dt = new Date(date);
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountSetor().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false and data.transactiondate < '"+dt+"' ");
        if(idvendor != null){
            sqlBuilder.append(" and data.idvendor = "+idvendor+"  ");
        }
        if(listidvendor != null && !listidvendor.equals("")){
            sqlBuilder.append(" and data.idvendor in ("+listidvendor+")  ");
        }
        final Object[] queryParameters = new Object[] {idcompany};
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

    @Override
    public boolean checkIDVendor(Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryColumnIDVendor().schema());
        sqlBuilder.append(" where data.idvendor = ? and data.isdelete = false limit 1 ");
        final Object[] queryParameters = new Object[] {idvendor};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryColumnIDVendor(), queryParameters);
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public HashMap<String, Object> getDataForReport(Long idcompany, Long idbranch, ParamReportPembelian param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintPurchaseReceive().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        String selectIdPR = "select pr.id from purchasereceive as pr where pr.idcompany = "+idcompany+" and pr.idbranch = "+idbranch+" and pr.isdelete = false ";
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom().longValue());
            sqlBuilder.append(" and data.transactiondate >= '"+dt.toString()+"'");
            selectIdPR += " and pr.transactiondate >= '"+dt.toString()+"' ";
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo().longValue());
            sqlBuilder.append(" and data.transactiondate <= '"+dt.toString()+"'");
            selectIdPR += " and pr.transactiondate <= '"+dt.toString()+"' ";
        }
        if(param.getIdvendor() != null){
            if(param.getIdvendor().longValue() > 0){
                sqlBuilder.append(" and data.idvendor = "+param.getIdvendor().longValue()+" ");
                selectIdPR += " and pr.idvendor = "+param.getIdvendor().longValue()+" ";
            }
        }

        if(param.getListidvendor() != null && !param.getListidvendor().equals("")){
            sqlBuilder.append(" and data.idvendor in ("+param.getListidvendor()+") ");
            selectIdPR += " and pr.idvendor in ("+param.getListidvendor()+") ";
        }

        if(param.getIdarea() != null){
            sqlBuilder.append(" and data.idarea = "+param.getIdarea().longValue()+" ");
            selectIdPR += " and pr.idarea = "+param.getIdarea().longValue()+" ";
        }
        sqlBuilder.append(" order by data.id desc ");

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        List<PrintDataPurchaseReceive> listPR = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintPurchaseReceive(), queryParameters);

        final StringBuilder sqlBuilderItems = new StringBuilder("select " + new QueryPrintDataPurchaseReceiveItems().schema());
        sqlBuilderItems.append(" where data.type = 'H' and data.idpurchasereceive in ("+selectIdPR+")  ");
        sqlBuilderItems.append(" order by data.idpurchasereceive desc ");

        final Object[] queryParametersItems = new Object[] {};
        List<PrintDataPurchaseReceiveItems> listItems = this.jdbcTemplate.query(sqlBuilderItems.toString(), new QueryPrintDataPurchaseReceiveItems(), queryParametersItems);

        final StringBuilder sqlBuilderBiaya = new StringBuilder("select " + new QueryItemsChargeNotJoin().schema());
        sqlBuilderBiaya.append(" where data.idpurchasereceive in ("+selectIdPR+")  ");
        sqlBuilderBiaya.append(" order by data.idpurchasereceive desc ");

        final Object[] queryParametersBiaya = new Object[] {};
        List<PurchaseReceiveChargeNotJoin> listBiaya = this.jdbcTemplate.query(sqlBuilderBiaya.toString(), new QueryItemsChargeNotJoin(), queryParametersBiaya);

        HashMap<String, Object> maps = new HashMap<>();
        maps.put("listPR",listPR);
        maps.put("listItems",listItems);
        maps.put("listBiaya",listBiaya);

        return maps;
    }

    @Override
    public Long calculateQtyPr(Long idcompany, Long idbranch, ParamCalculateQtyPR param) {
        String selectidPr = " select pr.id from purchasereceive as pr where pr.idcompany = "+idcompany+" and pr.idbranch = "+idbranch+" and pr.isdelete = false ";
        if(param.getDateFrom() != null){
            Date dt = new Date(param.getDateFrom());
            selectidPr += " and pr.transactiondate >= '"+dt.toString()+"' ";
        }
        if(param.getDateThru() != null){
            Date dt = new Date(param.getDateThru());
            selectidPr += " and pr.transactiondate <= '"+dt.toString()+"' ";
        }
        if(param.getIdvendor() != null){
            selectidPr += " and pr.idvendor = "+param.getIdvendor()+" ";
        }
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateQty().schema());
        sqlBuilder.append(" where data.type = 'H' and data.idpurchasereceive in ("+selectidPr+") ");
        if(param.getIdcategoryproduct() != null){
            sqlBuilder.append(" and data.idcategoryproduct = "+param.getIdcategoryproduct()+"  ");
        }
        if(param.getListidcategoryproduct() != null && !param.getListidcategoryproduct().equals("")){
            sqlBuilder.append(" and data.idcategoryproduct in ("+param.getListidcategoryproduct()+") ");
        }
        if(param.getIdproduct() != null){
            sqlBuilder.append(" and data.idproduct = "+param.getIdproduct()+"  ");
        }
        if(param.getListidproduct() != null && !param.getListidproduct().equals("")){
            sqlBuilder.append(" and data.idproduct in ("+param.getListidproduct()+") ");
        }

        final Object[] queryParameters = new Object[] {};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateQty(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0L;
    }

    @Override
    public ReturnData updateOustandingTambah(Long id, Double bayar) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        try {
            PurchaseReceive table = purchaseReceiveRepo.getById(id);
            double outstanding = table.getOutstanding().doubleValue() + bayar.doubleValue();
            table.setOutstanding(outstanding);
            idsave = purchaseReceiveRepo.saveAndFlush(table).getId();

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
    public ReturnData updateOustandingKurang(Long id, Double bayar) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        try {
            PurchaseReceive table = purchaseReceiveRepo.getById(id);
            double outstanding = table.getOutstanding().doubleValue() - bayar.doubleValue();
            table.setOutstanding(outstanding);
            idsave = purchaseReceiveRepo.saveAndFlush(table).getId();

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
    public List<PurchaseReceiveDataPelunasanHutang> getListForPelunasanHutang(Long idcompany, Long idbranch, FilterParamPelunasanHutang param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataPurchaseReceivePelunasanHutang().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getStatus().equals("LUNAS")){
            sqlBuilder.append(" and data.outstanding < 1 ");
        }else if(param.getStatus().equals("BELUMLUNAS")){
            sqlBuilder.append(" and data.outstanding >= 1 ");
        }
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataPurchaseReceivePelunasanHutang(), queryParameters);
    }

    @Override
    public List<ReportPelunasanHutangDocumentHutang> getListPRReportHutang(Long idcompany, Long idbranch, FilterParamPurchaseReceive param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPurchaseReceiveReportHutang().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.transactiondate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.transactiondate <= '"+dt.toString()+"'");
        }

        if(param.getIdvendor() != null){
            sqlBuilder.append(" and data.idvendor = "+param.getIdvendor());
        }
        if(param.getStatus().equals("LUNAS")){
            sqlBuilder.append(" and data.outstanding < 1 ");
        }else if(param.getStatus().equals("BELUMLUNAS")){
            sqlBuilder.append(" and data.outstanding >= 1 ");
        }
        if(param.getOrderBy() != null && !param.getOrderBy().equals("")){
            sqlBuilder.append(" order by data."+param.getOrderBy());
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPurchaseReceiveReportHutang(), queryParameters);
    }

    @Override
    public Double calculateSetorByIdVendorAndDate(Long idcompany, Long idbranch, Long idvendor, Long date,String listidvendor) {
        Date dt = new Date(date);
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountSetor().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false and data.transactiondate < '"+dt+"' ");
        if(idvendor != null){
            sqlBuilder.append(" and data.idvendor = "+idvendor+" ");
        }

        if(listidvendor != null && !listidvendor.equals("")){
            sqlBuilder.append(" and data.idvendor in ("+listidvendor+") ");
        }
        final Object[] queryParameters = new Object[] {idcompany};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountSetor(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public List<ReportKartuDeposit> getListPrReportKartuDeposit(Long idcompany, Long idbranch, FilterParamPurchaseReceive param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPRReportKartuDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.transactiondate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.transactiondate <= '"+dt.toString()+"'");
        }

        if(param.getListIdVendor() != null && !param.getListIdVendor().equals("")){
            sqlBuilder.append(" and data.idvendor in ("+param.getListIdVendor()+") ");
        }

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPRReportKartuDeposit(), queryParameters);
    }

    @Override
    public List<ReportKartuPinjaman> getListPrReportKartuPinjaman(Long idcompany, Long idbranch, FilterParamPurchaseReceive param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPRReportKartuPinjaman().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.transactiondate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.transactiondate <= '"+dt.toString()+"'");
        }

        if(param.getListIdVendor() != null && !param.getListIdVendor().equals("")){
            sqlBuilder.append(" and data.idvendor in ("+param.getListIdVendor()+") ");
        }

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPRReportKartuPinjaman(), queryParameters);
    }

    @Override
    public PurchaseReceiveItemsNotJoin getItemInLastDocumentPR(Long idcompany, Long idbranch, Long idproduct, Long idcategoryproduct) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryItemsNotJoin().schema());
        sqlBuilder.append(" where data.idproduct = ? and data.idcategoryproduct = ?  ");
        sqlBuilder.append(" and data.idpurchasereceive in (select pr.id from purchasereceive as pr where pr.idcompany = "+idcompany+" and pr.idbranch = "+idbranch+" and pr.isdelete = false ) ");
        sqlBuilder.append(" order by data.idpurchasereceive desc limit 1  ");

        final Object[] queryParameters = new Object[] {idproduct,idcategoryproduct};
        List<PurchaseReceiveItemsNotJoin> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryItemsNotJoin(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ReportKartuStock> getListPrReportKartuStock(Long idcompany, Long idbranch, FilterParamPurchaseReceive param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPRReportKartuStock().schema());
        sqlBuilder.append(" where pr.idcompany = ? and pr.idbranch = ? and pr.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and pr.transactiondate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and pr.transactiondate <= '"+dt.toString()+"'");
        }

        if(param.getListIdProduct() != null && !param.getListIdProduct().equals("")){
            sqlBuilder.append(" and data.idproduct in ("+param.getListIdProduct()+") ");
        }
        if(param.getListIdCategoryProduct() != null && !param.getListIdCategoryProduct().equals("")){
            sqlBuilder.append(" and data.idcategoryproduct in ("+param.getListIdCategoryProduct()+") ");
        }

        if(param.getType() != null && !param.getType().equals("")){
            sqlBuilder.append(" and data.type = '"+param.getType()+"' ");
        }

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPRReportKartuStock(), queryParameters);
    }

    @Override
    public List<PurchaseReceiveDataKomisi> getListKomisi(Long idcompany, Long idbranch, ParamKomisi param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPurchaseReceiveKomisi(param.getIdbox()).schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.transactiondate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.transactiondate <= '"+dt.toString()+"'");
        }

        if(param.getListIdVendor() != null && !param.getListIdVendor().equals("")){
            sqlBuilder.append(" and ven.idvendorbroker in ("+param.getListIdVendor()+") ");
        }

        if(param.getListidpurchaisereceive() != null && !param.getListidpurchaisereceive().equals("")){
            sqlBuilder.append(" and data.id in ("+param.getListidpurchaisereceive()+") ");
        }
        if(param.getMenu() != null && !param.getMenu().equals("")){
            if(param.getMenu().equals("DETAILITEMKOMISI")){
                if(param.getIdkomisi() != null){
                    sqlBuilder.append(" and data.id in (select ki.idpurchasereceive from komisi_item as ki where ki.idkomisi = "+param.getIdkomisi()+"  ) ");
                }

            }
        }else{
            sqlBuilder.append(" and data.id not in (select ki.idpurchasereceive from komisi_item as ki left join komisi as k on k.id = ki.idkomisi where k.idcompany = "+idcompany+" and k.idbranch = "+idbranch+" and k.isdelete = false  ) ");
            sqlBuilder.append(" and ven.idvendorbroker notnull ");
        }

        sqlBuilder.append(" order by data.transactiondate ");

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPurchaseReceiveKomisi(param.getIdbox()), queryParameters);
    }

    @Override
    public List<PurchaseReceiveGetPrice> getListPurchaseReceiveGetPrice(Long idcompany, Long idbranch, ParamGetPrice param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPurchaseReceiveGetPrice().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false and item.type = 'H' ");
        sqlBuilder.append(" and item.idproduct = "+param.getIdproduct()+" and (item.idcategoryproduct = "+param.getIdproductcategory() +" or item.idcategoryproduct in (select ms.categoryproductid from mapping_stock as ms where ms.categoryproductidmapping = "+param.getIdproductcategory()+" ) )");
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        List<PurchaseReceiveGetPrice> templist = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPurchaseReceiveGetPrice(), queryParameters);
        List<PurchaseReceiveGetPrice> list = new ArrayList<>();
        if(templist != null && templist.size() > 0){
            int limitdoc = 3;
            if(param.getLimitdoc() != null){
                if(param.getLimitdoc().intValue() > 0){
                    limitdoc = param.getLimitdoc().intValue();
                }
            }
            HashMap<Long,List<PurchaseReceiveGetPrice>> grupByIDPR = new HashMap<>();
            for(PurchaseReceiveGetPrice data : templist){
                List<PurchaseReceiveGetPrice> temp = grupByIDPR.get(data.getId());
                if(temp == null){
                    temp = new ArrayList<>();
                    temp.add(data);
                    grupByIDPR.put(data.getId(),temp);
                }else{
                    List<PurchaseReceiveGetPrice> temp1 = new ArrayList<>();
                    temp1 = temp;
                    temp1.add(data);
                    grupByIDPR.put(data.getId(),temp1);
                }
                if(grupByIDPR.size() == limitdoc){
                    break;
                }
            }

            for (HashMap.Entry<Long, List<PurchaseReceiveGetPrice>> entry : grupByIDPR.entrySet()) {
                list.addAll(entry.getValue());
            }
            return list;
        }
        return templist;
    }

    @Override
    public PurchaseReceiveDataDetail getDetailLastDocumentByVendor(Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.idvendor = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idvendor,idcompany,idbranch};
        List<PurchaseReceiveDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            PurchaseReceiveDataDetail data = list.get(0);
            data.setItems(getPrintDataItems(data.getId()));
//            data.setCharges(getPrintDataCharge(id));
//            data.setInventori(getPrintDataItemsInventori(id));
//            data.setSisaDeposit(depositService.calculateSisaDepositByIdVendor(idcompany,idbranch,data.getIdvendor()));
//            data.setSisaPinjaman(pinjamanService.calculateSisaPinjamanByIdVendor(idcompany,idbranch, data.getIdvendor(),null));
            return data;
        }
        return null;
    }

    @Override
    public List<Long> checkIdCP(Long idcompany, Long idbranch, Long idcategoryProduct) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCheckIdCategoryProduct().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and items.idcategoryproduct = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch,idcategoryProduct};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCheckIdCategoryProduct(), queryParameters);
    }

    private List<Long> getListIdDeposit(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataPurchaseReceiveDeposit().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataPurchaseReceiveDeposit(), queryParameters);
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
        sqlBuilder.append(" order by  cprod.sequence ");
        //cprod
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

    private List<PurchaseReceiveDepositData> getListPurchaseReceiveDepositByIdPR(Long idpurchasereceive){
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPurchaseReceiveDepositData().schema());
        sqlBuilder.append(" where data.idpurchasereceive = ?  ");

        final Object[] queryParameters = new Object[] {idpurchasereceive};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPurchaseReceiveDepositData(), queryParameters);
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
    private HashMap<Object,Object> setItemsDeposit(Long idcompany, Long idbranch,Long iduser,Long idpr,Long idvendor){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        Long idparent = vendorService.getIdParent(idcompany,idbranch,idvendor);
        ParamList paramDeposit = new ParamList();
        paramDeposit.setIdvendor(idparent);
        List<DepositDataNotJoin> listDeposit = depositService.getListDepositActive(idcompany,idbranch,paramDeposit);
        List<String> listDepo = new ArrayList<>();
        if(listDeposit != null && listDeposit.size() > 0){
            for(DepositDataNotJoin depo : listDeposit){
                depositService.updateStatusDeposit(depo.getId(), idcompany,idbranch,iduser,false);
                listDepo.add(depo.getId().toString());

                PurchaseReceiveDepositPK pkDepo = new PurchaseReceiveDepositPK();
                pkDepo.setIdpurchasereceive(idpr);
                pkDepo.setIddeposit(depo.getId());
                PurchaseReceiveDeposit table = new PurchaseReceiveDeposit();
                table.setPurchaseReceiveDepositPK(pkDepo);
                purchaseReceiveDepositRepo.saveAndFlush(table);
            }
        }
        String dataItems = listDepo.toString();
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }

    private HashMap<Object,Object> aktivasiDeposit(Long idcompany, Long idbranch,Long iduser,Long idpr){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<Long> listDeposit = getListIdDeposit(idpr);
        List<String> listDepo = new ArrayList<>();
        if(listDeposit != null && listDeposit.size() > 0){
            for(Long iddeposit : listDeposit){
                if(iddeposit.longValue() > 0){
                    depositService.updateStatusDeposit(iddeposit, idcompany,idbranch,iduser,true);
                }
            }
        }
        String dataItems = listDepo.toString();
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
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
                    table.setQtynota(val.getQtynota());
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
                    table.setChargenamecustom(val.getChargenamecustom());
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
