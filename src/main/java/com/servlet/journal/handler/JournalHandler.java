package com.servlet.journal.handler;

import com.servlet.chartofaccount.AccountCOAEnum;
import com.servlet.deposit.entity.Deposit;
import com.servlet.deposit.entity.ParamList;
import com.servlet.deposit.entity.ReportKartuDeposit;
import com.servlet.deposit.repo.DepositRepo;
import com.servlet.deposit.service.DepositService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.journal.entity.*;
import com.servlet.journal.mapper.QuerySaldo;
import com.servlet.journal.repo.JournalDetailRepo;
import com.servlet.journal.repo.JournalRepo;
import com.servlet.journal.service.JournalService;
import com.servlet.pinjaman.entity.ParamReportKartuPinjamanList;
import com.servlet.pinjaman.entity.Pinjaman;
import com.servlet.pinjaman.entity.ReportKartuPinjaman;
import com.servlet.pinjaman.repo.PinjamanRepo;
import com.servlet.pinjaman.service.PinjamanService;
import com.servlet.purchasereceive.entity.FilterParamPurchaseReceive;
import com.servlet.purchasereceive.entity.PurchaseReceive;
import com.servlet.purchasereceive.mapper.QueryCalculateAmountSetor;
import com.servlet.purchasereceive.repo.PurchaseReceiveRepo;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.entity.ParamVendor;
import com.servlet.vendor.entity.VendorDataForTemplate;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalHandler implements JournalService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JournalRepo repo;

    @Autowired
    private JournalDetailRepo detailrepo;

    @Autowired
    private DepositRepo depositRepo;

    @Autowired
    private PinjamanRepo pinjamanRepo;

    @Autowired
    private PurchaseReceiveRepo purchaseReceiveRepo;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    DepositService depositService;

    @Autowired
    PurchaseReceiveService purchaseReceiveService;

    @Autowired
    PinjamanService pinjamanService;

    protected final String namaMenu = "POSTING-JOURNAL";

    @Override
    public List<ValidationDataMessage> postingJournal(PostingJournalParam param) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        Long idjournal = 0L;
        try {
            Long currDateLong = new java.util.Date().getTime();
            Timestamp ts = new Timestamp(currDateLong);
            Journal journal = new Journal();
            journal.setIdcompany(param.getIdcompany());
            journal.setIdbranch(param.getIdbranch());
            journal.setJournalnumber("J-" + param.getSourcenumber());
            journal.setJournaldate(new Date(currDateLong));
            journal.setIdvendor(param.getIdvendor());
            journal.setSourcenumber(param.getSourcenumber());
            journal.setSourcetype(param.getSourcetype());
            if(param.getTransaksitime() != null){
                journal.setTransaksitime(param.getTransaksitime());
            }else{
                journal.setTransaksitime(ts);
            }
            journal.setDescription(param.getDescription());
            journal.setCreatedby(param.getCreatedby());
            journal.setCreateddate(ts);

            idjournal = repo.saveAndFlush(journal).getId();

            JournalDetail detailDebit = new JournalDetail();
            JournalDetail detailCredit = new JournalDetail();
            boolean flag = false;
            if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType())){
//                Debit   1-1001 Kas / Bank
//                Kredit  2-2301 Deposit Vendor

                flag = true;
//                JournalDetailPK debitPK = new JournalDetailPK();
//                debitPK.setJournalid(idjournal);
//                debitPK.setAccountcode(AccountCOAEnum.KAS_ASSET.getAccCode());
//                detailDebit.setJournalDetailPK(debitPK);
//                detailDebit.setDebit(param.getAmount());
//                detailDebit.setCredit(0.0);
//                detailDebit.setDescription(param.getDescriptionDetail());


                JournalDetailPK creditPK = new JournalDetailPK();
                creditPK.setJournalid(idjournal);
                creditPK.setAccountcode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
                detailCredit.setJournalDetailPK(creditPK);

                //Karena input top up bisa deposit bisa minus, jika minus dimasukan ke debit, karena di analogikan sebgai pengurangan saldo
                if(param.getAmount() >= 0){
                    detailCredit.setDebit(0.0);
                    detailCredit.setCredit(param.getAmount());
                }else{
                    detailCredit.setDebit(Math.abs(param.getAmount()));
                    detailCredit.setCredit(0.0);
                }

                detailCredit.setDescription(param.getDescriptionDetail());

                detailCredit.setIdvendor(param.getIdvendor());
                detailCredit.setSourcenumber(param.getSourcenumber());
                if(param.getTransaksitime() != null){
                    detailCredit.setTransaksitime(param.getTransaksitime());
                }else{
                    detailCredit.setTransaksitime(ts);
                }

                detailCredit.setIdcompany(param.getIdcompany());
                detailCredit.setIdbranch(param.getIdbranch());
                detailCredit.setSourcedocumentdate(param.getSourcedocumentdate());
                detailrepo.saveAndFlush(detailCredit);

            }else if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType())){
                flag = true;

//                Debit   1-1001 Kas / Bank
//                Kredit  2-2201 Pinjaman Vendor

//                JournalDetailPK debitPK = new JournalDetailPK();
//                debitPK.setJournalid(idjournal);
//                debitPK.setAccountcode(AccountCOAEnum.KAS_ASSET.getAccCode());
//                detailDebit.setJournalDetailPK(debitPK);
//                detailDebit.setDebit(param.getAmount());
//                detailDebit.setCredit(0.0);
//                detailDebit.setDescription(param.getDescriptionDetail());

                JournalDetailPK creditPK = new JournalDetailPK();
                creditPK.setJournalid(idjournal);
                creditPK.setAccountcode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
                detailCredit.setJournalDetailPK(creditPK);
                //Karena input top up bisa pinjaman bisa minus, jika minus dimasukan ke debit, karena di analogikan sebgai pengurangan saldo
                if(param.getAmount() >= 0){
                    detailCredit.setDebit(0.0);
                    detailCredit.setCredit(param.getAmount());
                }else{
                    detailCredit.setDebit(Math.abs(param.getAmount()));
                    detailCredit.setCredit(0.0);
                }
                detailCredit.setDescription(param.getDescriptionDetail());

                detailCredit.setIdvendor(param.getIdvendor());
                detailCredit.setSourcenumber(param.getSourcenumber());
                if(param.getTransaksitime() != null){
                    detailCredit.setTransaksitime(param.getTransaksitime());
                }else{
                    detailCredit.setTransaksitime(ts);
                }
                detailCredit.setIdcompany(param.getIdcompany());
                detailCredit.setIdbranch(param.getIdbranch());
                detailCredit.setSourcedocumentdate(param.getSourcedocumentdate());
                detailrepo.saveAndFlush(detailCredit);

            }else if(param.getSourcetype().equals(SourceTypeEnum.TRANSAKSI_PRC.getSourceType())){
                if(param.getAmountPemakaianDeposit().doubleValue() > 0){
                    createPemakaianDeposit(idjournal,ts,param);

//                    JournalDetail pemakaianDepositDebit2 = new JournalDetail();
//                    JournalDetailPK pemakaianDepositDebit2PK = new JournalDetailPK();
//                    pemakaianDepositDebit2PK.setJournalid(idjournal);
//                    pemakaianDepositDebit2PK.setAccountcode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
//                    pemakaianDepositDebit2.setJournalDetailPK(pemakaianDepositDebit2PK);
//                    pemakaianDepositDebit2.setDebit(param.getAmountPemakaianDeposit());
//                    pemakaianDepositDebit2.setCredit(0.0);
//                    pemakaianDepositDebit2.setDescription(param.getDescriptionDetailDeposit());
//
//                    pemakaianDepositDebit2.setIdvendor(param.getIdvendor());
//                    pemakaianDepositDebit2.setSourcenumber(param.getSourcenumber());
//                    if(param.getTransaksitime() != null){
//                        pemakaianDepositDebit2.setTransaksitime(param.getTransaksitime());
//                    }else{
//                        pemakaianDepositDebit2.setTransaksitime(ts);
//                    }
//                    pemakaianDepositDebit2.setIdcompany(param.getIdcompany());
//                    pemakaianDepositDebit2.setIdbranch(param.getIdbranch());
//                    pemakaianDepositDebit2.setSourcedocumentdate(param.getSourcedocumentdate());
//
//                    detailrepo.saveAndFlush(pemakaianDepositDebit2);
                }
//                if(param.getSourcenumber().equals("PRC-2000523")){
//                    System.out.println("getAmountPembayaranPinjaman "+param.getAmountPembayaranPinjaman().doubleValue());
//                }
                if(param.getAmountPembayaranPinjaman().doubleValue() > 0){
                    createPembayaranPinjaman(idjournal,ts,param);
//                    JournalDetail pembayaranPinjamanDebit2 = new JournalDetail();
//                    JournalDetailPK pembayaranPinjamanDebit2PK = new JournalDetailPK();
//                    pembayaranPinjamanDebit2PK.setJournalid(idjournal);
//                    pembayaranPinjamanDebit2PK.setAccountcode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
//                    pembayaranPinjamanDebit2.setJournalDetailPK(pembayaranPinjamanDebit2PK);
//                    pembayaranPinjamanDebit2.setDebit(param.getAmountPembayaranPinjaman());
//                    pembayaranPinjamanDebit2.setCredit(0.0);
//                    pembayaranPinjamanDebit2.setDescription(param.getDescriptionDetailPinjaman());
//
//                    pembayaranPinjamanDebit2.setIdvendor(param.getIdvendor());
//                    pembayaranPinjamanDebit2.setSourcenumber(param.getSourcenumber());
//                    if(param.getTransaksitime() != null){
//                        pembayaranPinjamanDebit2.setTransaksitime(param.getTransaksitime());
//                    }else{
//                        pembayaranPinjamanDebit2.setTransaksitime(ts);
//                    }
//                    pembayaranPinjamanDebit2.setIdcompany(param.getIdcompany());
//                    pembayaranPinjamanDebit2.setIdbranch(param.getIdbranch());
//                    pembayaranPinjamanDebit2.setSourcedocumentdate(param.getSourcedocumentdate());
//
//                    detailrepo.saveAndFlush(pembayaranPinjamanDebit2);
//                    if(param.getSourcenumber().equals("PRC-2000523")){
//                        System.out.println("getAmountPembayaranPinjaman Masuk "+param.getAmountPembayaranPinjaman().doubleValue());
//                    }
                }


            }

//            if(flag) {
//                detailDebit.setIdvendor(param.getIdvendor());
//                detailDebit.setSourcenumber(param.getSourcenumber());
//                detailDebit.setTransaksitime(ts);
//                detailDebit.setIdcompany(param.getIdcompany());
//                detailDebit.setIdbranch(param.getIdbranch());
//                detailDebit.setSourcedocumentdate(param.getSourcedocumentdate());
//
//                detailCredit.setIdvendor(param.getIdvendor());
//                detailCredit.setSourcenumber(param.getSourcenumber());
//                detailCredit.setTransaksitime(ts);
//                detailCredit.setIdcompany(param.getIdcompany());
//                detailCredit.setIdbranch(param.getIdbranch());
//                detailCredit.setSourcedocumentdate(param.getSourcedocumentdate());
//
//                detailrepo.saveAndFlush(detailDebit);
//                detailrepo.saveAndFlush(detailCredit);
//            }


        }catch (Exception e){
            e.printStackTrace();
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
            if(idjournal.longValue() > 0){
                repo.deleteByIdJournal(idjournal);
                detailrepo.deleteDetailByIdJournal(idjournal);
            }
        }

        if(validations.size() == 0){
            historyAppsService.saveHistory(param.getIdcompany(), param.getIdbranch(), param.getCreatedby(), "POSTING",namaMenu,param.toString(),"","",param.getTransaksitime());
        }
        return validations;
    }

    @Override
    public ReturnData migrationOrIntegrity(Long idcompany, Long idbranch, Long iduser, BodyMigrasi payload) {
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        List<ValidationDataMessage> validations = new ArrayList<>();
        List<Deposit> listDeposit = new ArrayList<>();
        List<Pinjaman> listPinjaman = new ArrayList<>();
        List<PurchaseReceive> listPurchaseReceive = new ArrayList<>();
        List<String> listSourceNumber = new ArrayList<>();
        List<String> listSourceNumberIdVendor = new ArrayList<>();

        try {
            if (payload.getIsall().equals("Y")) {
                repo.deleteAllJournal(idcompany,idbranch);
                detailrepo.deleteAllJournalDetail(idcompany,idbranch);

                listDeposit = depositRepo.fingByIdcompanyAndBranch(idcompany,idbranch);
                listPinjaman = pinjamanRepo.fingByIdcompanyAndBranch(idcompany,idbranch);
                listPurchaseReceive = purchaseReceiveRepo.fingByIdcompanyAndBranch(idcompany,idbranch);
            } else {
                if (payload.getFrom() != null && payload.getTo() != null) {

                    String fromDate = "";
                    try {
                        fromDate = GlobalFunc.getDateLongToString(payload.getFrom(), "yyyy-MM-dd");
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    String toDate = "";
                    try {
                        toDate = GlobalFunc.getDateLongToString(payload.getTo(), "yyyy-MM-dd");
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    repo.deleteAllJournalBySourceDocDate(idcompany,idbranch,fromDate,toDate);
                    detailrepo.deleteAllJournalDetailBySourceDocDate(idcompany,idbranch,fromDate,toDate);

                    listDeposit = depositRepo.fingByRangeDate(idcompany,idbranch,fromDate, toDate);
                    listPinjaman = pinjamanRepo.fingByRangeDate(idcompany,idbranch,fromDate, toDate);
                    listPurchaseReceive = purchaseReceiveRepo.fingByRangeDate(idcompany,idbranch,fromDate, toDate);
                }

            }
            if(listDeposit != null && listDeposit.size() > 0){
                for(Deposit val : listDeposit){
                    if(!val.isIsdelete()){
                        PostingJournalParam param = new PostingJournalParam();
                        param.setIdcompany(val.getIdcompany());
                        param.setIdbranch(val.getIdbranch());
                        param.setAmount(val.getAmount());
                        param.setDescriptionDetail("INTEGRASI");
                        param.setIdvendor(val.getIdvendor());
                        param.setSourcenumber(val.getNodocument());
                        param.setSourcedocumentdate(val.getDepositdate());
                        param.setSourcetype(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType());
                        param.setTransaksitime(val.getCreateddate());

                        param.setDescription("INTEGRASI");
                        param.setCreatedby(iduser);

                        List<ValidationDataMessage> validationsPosting = postingJournal(param);

                        if(validationsPosting != null && validationsPosting.size() > 0){
                            validations.addAll(validationsPosting);
                        }
                    }
                }
            }

            if(listPinjaman != null && listPinjaman.size() > 0){
                for(Pinjaman val : listPinjaman){
                    if(!val.isIsdelete()){
                        PostingJournalParam param = new PostingJournalParam();
                        param.setIdcompany(val.getIdcompany());
                        param.setIdbranch(val.getIdbranch());
                        param.setAmount(val.getAmount());
                        param.setDescriptionDetail("INTEGRASI");
                        param.setIdvendor(val.getIdvendor());
                        param.setSourcenumber(val.getNodocument());
                        param.setSourcedocumentdate(val.getDate());
                        param.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
                        param.setTransaksitime(val.getCreateddate());
                        param.setDescription("INTEGRASI");
                        param.setCreatedby(iduser);

                        List<ValidationDataMessage> validationsPosting = postingJournal(param);

                        if(validationsPosting != null && validationsPosting.size() > 0){
                            validations.addAll(validationsPosting);
                        }
                    }
                }
            }

            if(listPurchaseReceive != null && listPurchaseReceive.size() > 0){
                for(PurchaseReceive val : listPurchaseReceive){
                    if(!val.isIsdelete()){
                        PostingJournalParam param = new PostingJournalParam();
                        param.setIdcompany(val.getIdcompany());
                        param.setIdbranch(val.getIdbranch());
                        param.setAmountPemakaianDeposit(val.getSetor());
                        param.setDescriptionDetailDeposit("INTEGRASI");
                        param.setAmountPembayaranPinjaman(val.getSetor_pinjaman());
                        param.setDescriptionDetailPinjaman("INTEGRASI");
                        param.setIdvendor(val.getIdvendor());
                        param.setSourcenumber(val.getNodocument());
                        param.setSourcedocumentdate(val.getTransactiondate());
                        param.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
                        param.setTransaksitime(val.getCreateddate());
                        param.setDescription("INTEGRASI");
                        param.setCreatedby(iduser);

                        List<ValidationDataMessage> validationsPosting = postingJournal(param);

                        if(validationsPosting != null && validationsPosting.size() > 0){
                            validations.addAll(validationsPosting);
                        }
                    }
                }
            }

//            if(listDeposit != null && listDeposit.size() > 0){
//                for(Deposit val : listDeposit){
//                    if(val.isIsdelete()){
//                        listSourceNumberIdVendor.add(val.getIdvendor()+val.getNodocument());
//                    }else{
//                        listSourceNumberIdVendor.remove(val.getIdvendor()+val.getNodocument());
//
//                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumberAndIdVendor(val.getIdcompany(),val.getIdbranch(),val.getIdvendor(),val.getNodocument());
//                        PostingJournalParam param = new PostingJournalParam();
//                        if(listDetail != null && listDetail.size() > 0){
//                            param.setIdcompany(val.getIdcompany());
//                            param.setIdbranch(val.getIdbranch());
//                            param.setAmount(val.getAmount());
//                            param.setDescriptionDetail("INTEGRASI");
//                            param.setIdvendor(val.getIdvendor());
//                            param.setSourcenumber(val.getNodocument());
//                            param.setSourcedocumentdate(val.getDepositdate());
//                            param.setSourcetype(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType());
//                            param.setTransaksitime(val.getCreateddate());
//
//                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
//                            if(validationsPosting != null && validationsPosting.size() > 0){
//                                validations.addAll(validationsPosting);
//                            }
//                        }else{
//
//                            param.setIdcompany(val.getIdcompany());
//                            param.setIdbranch(val.getIdbranch());
//                            param.setAmount(val.getAmount());
//                            param.setDescriptionDetail("INTEGRASI");
//                            param.setIdvendor(val.getIdvendor());
//                            param.setSourcenumber(val.getNodocument());
//                            param.setSourcedocumentdate(val.getDepositdate());
//                            param.setSourcetype(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType());
//                            param.setTransaksitime(val.getCreateddate());
//
//                            param.setDescription("INTEGRASI");
//                            param.setCreatedby(iduser);
//
//                            List<ValidationDataMessage> validationsPosting = postingJournal(param);
//
//                            if(validationsPosting != null && validationsPosting.size() > 0){
//                                validations.addAll(validationsPosting);
//                            }
//                        }
//                    }
//                }
//            }

//            if(listPinjaman != null && listPinjaman.size() > 0){
//                for(Pinjaman val : listPinjaman){
//                    if(val.isIsdelete()){
//                        listSourceNumberIdVendor.add(val.getIdvendor()+val.getNodocument());
//                    }else{
//                        listSourceNumberIdVendor.remove(val.getIdvendor()+val.getNodocument());
//                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumberAndIdVendor(val.getIdcompany(),val.getIdbranch(), val.getIdvendor(), val.getNodocument());
//                        PostingJournalParam param = new PostingJournalParam();
//                        if(listDetail != null && listDetail.size() > 0){
//                            param.setIdcompany(val.getIdcompany());
//                            param.setIdbranch(val.getIdbranch());
//                            param.setAmount(val.getAmount());
//                            param.setDescriptionDetail("INTEGRASI");
//                            param.setIdvendor(val.getIdvendor());
//                            param.setSourcenumber(val.getNodocument());
//                            param.setSourcedocumentdate(val.getDate());
//                            param.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
//                            param.setTransaksitime(val.getCreateddate());
//
//                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
//                            if(validationsPosting != null && validationsPosting.size() > 0){
//                                validations.addAll(validationsPosting);
//                            }
//                        }else{
//
//                            param.setIdcompany(val.getIdcompany());
//                            param.setIdbranch(val.getIdbranch());
//                            param.setAmount(val.getAmount());
//                            param.setDescriptionDetail("INTEGRASI");
//                            param.setIdvendor(val.getIdvendor());
//                            param.setSourcenumber(val.getNodocument());
//                            param.setSourcedocumentdate(val.getDate());
//                            param.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
//                            param.setTransaksitime(val.getCreateddate());
//                            param.setDescription("INTEGRASI");
//                            param.setCreatedby(iduser);
//
//                            List<ValidationDataMessage> validationsPosting = postingJournal(param);
//
//                            if(validationsPosting != null && validationsPosting.size() > 0){
//                                validations.addAll(validationsPosting);
//                            }
//                        }
//                    }
//                }
//            }


//            if(listPurchaseReceive != null && listPurchaseReceive.size() > 0){
//                for(PurchaseReceive val : listPurchaseReceive){
//                    if(val.isIsdelete()){
//                        listSourceNumber.add(val.getNodocument());
//                        repo.deleteBySourceNumber(val.getNodocument());
//                        detailrepo.deleteDetailBySourceNumber(val.getNodocument());
//                    }else{
//                        listSourceNumber.remove(val.getNodocument());
//
//                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(val.getIdcompany(),val.getIdbranch(),val.getNodocument());
//                        PostingJournalParam param = new PostingJournalParam();
//                        if(listDetail != null && listDetail.size() > 0){
//                            param.setIdcompany(val.getIdcompany());
//                            param.setIdbranch(val.getIdbranch());
//                            param.setIdvendor(val.getIdvendor());
//                            param.setAmountPemakaianDeposit(val.getSetor());
//                            param.setDescriptionDetailDeposit("INTEGRASI");
//                            param.setAmountPembayaranPinjaman(val.getSetor_pinjaman());
//                            param.setDescriptionDetailPinjaman("INTEGRASI");
//                            param.setSourcenumber(val.getNodocument());
//                            param.setSourcedocumentdate(val.getTransactiondate());
//                            param.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
//                            param.setTransaksitime(val.getCreateddate());
//
//                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
//                            if(validationsPosting != null && validationsPosting.size() > 0){
//                                validations.addAll(validationsPosting);
//                            }
//                        }else{
//                            param.setIdcompany(val.getIdcompany());
//                            param.setIdbranch(val.getIdbranch());
//                            param.setAmountPemakaianDeposit(val.getSetor());
//                            param.setDescriptionDetailDeposit("INTEGRASI");
//                            param.setAmountPembayaranPinjaman(val.getSetor_pinjaman());
//                            param.setDescriptionDetailPinjaman("INTEGRASI");
//                            param.setIdvendor(val.getIdvendor());
//                            param.setSourcenumber(val.getNodocument());
//                            param.setSourcedocumentdate(val.getTransactiondate());
//                            param.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
//                            param.setTransaksitime(val.getCreateddate());
//                            param.setDescription("INTEGRASI");
//                            param.setCreatedby(iduser);
//
//                            List<ValidationDataMessage> validationsPosting = postingJournal(param);
//
//                            if(validationsPosting != null && validationsPosting.size() > 0){
//                                validations.addAll(validationsPosting);
//                            }
//                        }
//
//                    }
//
//                    //fungsi dibawah ini, untuk menghapus nominal yang lebih besar dari 0, padahal di data PRC nya 0
//                    //kenapa dilakuin disini , karena di fungsi postingJournal sudah ada penjagaan harus > 0 jadi ketika data 0, ttidak terupdate mau dihapus, takut impact kemana2
//                    if(val.getSetor() < 1){
//                        detailrepo.deleteDetailBySourceNumberAccCode(val.getNodocument(),AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
//                    }
//                    if(val.getSetor_pinjaman() < 1){
//                        detailrepo.deleteDetailBySourceNumberAccCode(val.getNodocument(),AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
//                    }
//                    //
//
//                }
//            }

            //khusus untuk pinjamn dan deposit, karena ada error source number sama,
            //dikarenakan ketika clear db pinjaman deposit tidak dihapus, sedangkan penomoran running number di reset ke 1.
//            if(listSourceNumberIdVendor.size() > 0){
//                detailrepo.deleteDetailByListIdVendorAndSourceNumber(listSourceNumberIdVendor);
//                repo.deleteByListSourceNumberIdVendor(listSourceNumberIdVendor);
//            }
//
//            //untuk hapus, data yang sudah di delete , tapi di jurnal masih ada
//            if(listSourceNumber.size() > 0){
//                detailrepo.deleteDetailByListSourceNumber(listSourceNumber);
//                repo.deleteByListSourceNumber(listSourceNumber);
//            }
//
//            detailrepo.deleteDetailDoubleSourceNumberData();
        }catch (Exception e){
            e.printStackTrace();
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }

//        String compBranch = val.getIdcompany()+"-"+ val.getIdbranch();
        if(validations.size() == 0){
            historyAppsService.saveHistory(idcompany, idbranch, iduser, "INTEGRASI",namaMenu,payload.toString(),"","",ts);
        }else{
            historyAppsService.saveHistory(idcompany, idbranch, iduser, "INTEGRASI-ERROR",namaMenu,payload.toString()+" Error : "+validations.toString(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(0L);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public List<ValidationDataMessage> updateJournalDetail(PostingJournalParam param) {
        List<ValidationDataMessage> validations = new ArrayList<>();

        try{
            List<JournalDetail> listDetail = new ArrayList<>();//detailrepo.fingBySourceNumber(param.getIdcompany(),param.getIdbranch(),param.getSourcenumber());
            if(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType().equals(param.getSourcetype()) || SourceTypeEnum.TOPUP_DEPOSIT.getSourceType().equals(param.getSourcetype()) ){
                listDetail = detailrepo.fingBySourceNumberAndIdVendor(param.getIdcompany(),param.getIdbranch(), param.getIdvendor(), param.getSourcenumber());
            }else{
                listDetail = detailrepo.fingBySourceNumber(param.getIdcompany(),param.getIdbranch(),param.getSourcenumber());
            }
            if(listDetail != null && listDetail.size() > 0){
                for(JournalDetail val : listDetail){
                    JournalDetail table = val;
                    if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType())){
                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
                            //Karena input top up bisa deposit bisa minus, jika minus dimasukan ke debit, karena di analogikan sebgai pengurangan saldo
                            if(param.getAmount() >= 0){
                                table.setDebit(0.0);
                                table.setCredit(param.getAmount());
                            }else{
                                table.setDebit(Math.abs(param.getAmount()));
                                table.setCredit(0.0);
                            }
                        }
                        table.setDescription(param.getDescriptionDetail());
                    }else if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType())){

                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
//                            table.setCredit(param.getAmount());

                            //Karena input top up bisa deposit bisa minus, jika minus dimasukan ke debit, karena di analogikan sebgai pengurangan saldo
                            if(param.getAmount() >= 0){
                                table.setDebit(0.0);
                                table.setCredit(param.getAmount());
                            }else{
                                table.setDebit(Math.abs(param.getAmount()));
                                table.setCredit(0.0);
                            }
                        }
                        table.setDescription(param.getDescriptionDetail());
                    }else if(param.getSourcetype().equals(SourceTypeEnum.TRANSAKSI_PRC.getSourceType())){
                        if(param.getAmountPemakaianDeposit().doubleValue() > 0){

                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
                                table.setDebit(param.getAmountPemakaianDeposit());
                                table.setDescription(param.getDescriptionDetailDeposit());
                            }else{
                                //kenapa jika size == 1 , di create. karena ketika size , tapi pemakaian deposit lebih besar dari 0,
                                //bisa dipastikan itu tidak ada di journalDetail, sehingga perlu di create.
                                if(listDetail.size() == 1){
                                    Long currDateLong = new java.util.Date().getTime();
                                    Timestamp ts = new Timestamp(currDateLong);
                                    createPemakaianDeposit(val.getJournalDetailPK().getJournalid(),ts,param);
                                }
                            }

                        }

                        if(param.getAmountPembayaranPinjaman().doubleValue() > 0){

                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
                                table.setDebit(param.getAmountPembayaranPinjaman());
                                table.setDescription(param.getDescriptionDetailPinjaman());
                            }else{
                                //kenapa jika size == 1 , di create. karena ketika size , tapi pembayaran pinjaman lebih besar dari 0,
                                //bisa dipastikan itu tidak ada di journalDetail, sehingga perlu di create.
                                if(listDetail.size() == 1) {
                                    Long currDateLong = new java.util.Date().getTime();
                                    Timestamp ts = new Timestamp(currDateLong);
                                    createPembayaranPinjaman(val.getJournalDetailPK().getJournalid(), ts, param);
                                }
                            }

                        }
                    }
                    table.setTransaksitime(param.getTransaksitime());
                    table.setSourcedocumentdate(param.getSourcedocumentdate());
                    detailrepo.saveAndFlush(table);
                }
            }else{
                List<ValidationDataMessage> validationsPosting = postingJournal(param);
                validations.addAll(validationsPosting);
            }
        }catch (Exception e){
            e.printStackTrace();
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }
        return validations;
    }

    @Override
    public List<ValidationDataMessage> deleteJournalBySourceNumber(String sourceNumber) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        try{
            repo.deleteBySourceNumber(sourceNumber);
            detailrepo.deleteDetailBySourceNumber(sourceNumber);
        }catch (Exception e){
            e.printStackTrace();
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }
        return validations;
    }

    private SaldoJournal calculateSaldoEqReportDeposit(SaldoJournalParam param){
        String listIdVendor = param.getIdvendor()+"";
        ParamVendor paramvendor =  new ParamVendor();
        paramvendor.setListIdVendor(listIdVendor);
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(param.getIdcompany(),param.getIdbranch(),paramvendor);
        List<Long> listIdParentAndSubIdParent = vendorService.getListSubIdParentByListIdParent(param.getIdcompany(),param.getIdbranch(),listIdVendor,"N","Y");
        for(VendorDataForTemplate ven : getListVendor){
            listIdParentAndSubIdParent.add(ven.getId());
        }
        String listIdVendorSubParent = listIdParentAndSubIdParent.toString().replaceAll("\\[","");
        listIdVendorSubParent = listIdVendorSubParent.replaceAll("\\]","");

        ParamList paramdp = new ParamList();
        paramdp.setFrom(null);
        paramdp.setTo(null);
        paramdp.setListIdVendor(listIdVendorSubParent);
        paramdp.setTransaksiTime(param.getTransaksiTime());
        List<ReportKartuDeposit> listdp = depositService.getListReportKartuDeposit(param.getIdcompany(),param.getIdbranch(),paramdp);
        double saldoDeposit = 0.0;
        if(listdp != null && listdp.size() > 0){
            for(ReportKartuDeposit dep : listdp){
                saldoDeposit = saldoDeposit + (dep.getAmount() != null?dep.getAmount():0.0);
            }
        }
        FilterParamPurchaseReceive paramPR = new FilterParamPurchaseReceive();
        paramPR.setFrom(null);
        paramPR.setTo(null);
        paramPR.setListIdVendor(listIdVendorSubParent);
        paramPR.setTransaksiTime(param.getTransaksiTime());
        List<ReportKartuDeposit> listSetorPR = purchaseReceiveService.getListPrReportKartuDeposit(param.getIdcompany(),param.getIdbranch(),paramPR);
        double totalPemakaianDepositPr = 0.0;
        if(listSetorPR != null && listSetorPR.size() > 0){
            for(ReportKartuDeposit dep : listSetorPR){
                totalPemakaianDepositPr = totalPemakaianDepositPr + (dep.getAmount() != null?dep.getAmount():0.0);
            }
        }
        double sisaSaldoDeposit = saldoDeposit - totalPemakaianDepositPr;

        SaldoJournal temp = new SaldoJournal();
        temp.setAccountCode("");
        temp.setIdvendor(0L);
        temp.setSaldo(sisaSaldoDeposit);
        return temp;
    }

    private SaldoJournal calculateSaldoEqReportPinjaman(SaldoJournalParam param){
        String listIdVendor = param.getIdvendor()+"";
        ParamVendor paramvendor =  new ParamVendor();
        paramvendor.setListIdVendor(listIdVendor);
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(param.getIdcompany(),param.getIdbranch(),paramvendor);
        List<Long> listIdParentAndSubIdParent = vendorService.getListSubIdParentByListIdParent(param.getIdcompany(),param.getIdbranch(),listIdVendor,"Y","N");
        for(VendorDataForTemplate ven : getListVendor){
            listIdParentAndSubIdParent.add(ven.getId());
        }
        String listIdVendorSubParent = listIdParentAndSubIdParent.toString().replaceAll("\\[","");
        listIdVendorSubParent = listIdVendorSubParent.replaceAll("\\]","");

        ParamReportKartuPinjamanList paramPinjaman = new ParamReportKartuPinjamanList();
        paramPinjaman.setFrom(null);
        paramPinjaman.setTo(null);
        paramPinjaman.setListIdVendor(listIdVendorSubParent);
        paramPinjaman.setTransaksiTime(param.getTransaksiTime());
        List<ReportKartuPinjaman> listpinjaman = pinjamanService.getListReportKartuPinjaman(param.getIdcompany(),param.getIdbranch(), paramPinjaman);
        double saldoPinjaman = 0.0;
        if(listpinjaman != null && listpinjaman.size() > 0){
            for(ReportKartuPinjaman dep : listpinjaman){
                saldoPinjaman = saldoPinjaman + (dep.getAmount() != null?dep.getAmount():0.0);
            }
        }

        FilterParamPurchaseReceive paramPR = new FilterParamPurchaseReceive();
        paramPR.setFrom(null);
        paramPR.setTo(null);
        paramPR.setListIdVendor(listIdVendorSubParent);
        paramPR.setTransaksiTime(param.getTransaksiTime());
        List<ReportKartuPinjaman> listSetorPinjamanPR = purchaseReceiveService.getListPrReportKartuPinjaman(param.getIdcompany(),param.getIdbranch(), paramPR);
        double totalPembayaranPinjamanPr = 0.0;
        if(listSetorPinjamanPR != null && listSetorPinjamanPR.size() > 0){
            for(ReportKartuPinjaman dep : listSetorPinjamanPR){
                totalPembayaranPinjamanPr = totalPembayaranPinjamanPr + (dep.getAmount() != null?dep.getAmount():0.0);
            }
        }
        double sisaSaldo = saldoPinjaman - totalPembayaranPinjamanPr;

        SaldoJournal temp = new SaldoJournal();
        temp.setAccountCode("");
        temp.setIdvendor(0L);
        temp.setSaldo(sisaSaldo);
        return temp;
    }

    @Override
    public SaldoJournal calculateSaldo(SaldoJournalParam param) {
        //sementara samakan dulu dengan kartu report deposit / pinjaman
        if(param.getAccountCode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
            return calculateSaldoEqReportDeposit(param);
        }else if (param.getAccountCode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
            return calculateSaldoEqReportPinjaman(param);
        }
        SaldoJournal temp = new SaldoJournal();
        temp.setAccountCode("");
        temp.setIdvendor(0L);
        temp.setSaldo(0.0);
        return temp;


//        Long idven = vendorService.getIdParent(param.getIdcompany(),param.getIdbranch(),param.getIdvendor());
//        if(idven == null){
//            idven = param.getIdvendor();
//        }else if(idven == 0){
//            idven = param.getIdvendor();
//        }
//        List<Long> listidven = vendorService.getListSubIdParent(param.getIdcompany(),param.getIdbranch(),idven);
//        //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet hanya sub nya saja
//        listidven.add(idven);
//        String listidvendor = "";
//        if(listidven != null && listidven.size() > 0){
//            listidvendor = listidven.toString().replaceAll("\\[","");
//            listidvendor = listidvendor.replaceAll("\\]","");
//        }
//
//        final StringBuilder sqlBuilder = new StringBuilder("select " + new QuerySaldo().schema());
//        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.accountcode = ? ");
//        if(listidvendor != null && !listidvendor.equals("")){
//            sqlBuilder.append(" and data.idvendor in ("+listidvendor+") ");
//        }else{
//            if(param.getIdvendor() != null){
//                sqlBuilder.append(" and data.idvendor = "+param.getIdvendor());
//            }
//        }
//
//        if(param.getTransaksiTime() != null){
//            sqlBuilder.append(" and data.transaksitime <= '"+param.getTransaksiTime().toString()+"'");
//        }
//
//        sqlBuilder.append(" GROUP BY data.accountcode, data.idvendor ");
////        System.out.println("Query "+param.getAccountCode()+" | "+sqlBuilder.toString());
////        System.out.println("Companyid "+param.getIdcompany()+" | branchID "+param.getIdbranch());
//        final Object[] queryParameters = new Object[] {param.getIdcompany(), param.getIdbranch(), param.getAccountCode()};
//        List<SaldoJournal> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QuerySaldo(), queryParameters);
//        if(list != null && list.size() > 0){
//            return list.get(0);
//        }
//        SaldoJournal temp = new SaldoJournal();
//        temp.setAccountCode("");
//        temp.setIdvendor(0L);
//        temp.setSaldo(0.0);
//        return temp;
    }

    private void createPemakaianDeposit(Long idjournal,Timestamp ts, PostingJournalParam param){
        JournalDetail pemakaianDepositDebit2 = new JournalDetail();
        JournalDetailPK pemakaianDepositDebit2PK = new JournalDetailPK();
        pemakaianDepositDebit2PK.setJournalid(idjournal);
        pemakaianDepositDebit2PK.setAccountcode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
        pemakaianDepositDebit2.setJournalDetailPK(pemakaianDepositDebit2PK);
        pemakaianDepositDebit2.setDebit(param.getAmountPemakaianDeposit());
        pemakaianDepositDebit2.setCredit(0.0);
        pemakaianDepositDebit2.setDescription(param.getDescriptionDetailDeposit());

        pemakaianDepositDebit2.setIdvendor(param.getIdvendor());
        pemakaianDepositDebit2.setSourcenumber(param.getSourcenumber());
        if(param.getTransaksitime() != null){
            pemakaianDepositDebit2.setTransaksitime(param.getTransaksitime());
        }else{
            pemakaianDepositDebit2.setTransaksitime(ts);
        }
        pemakaianDepositDebit2.setIdcompany(param.getIdcompany());
        pemakaianDepositDebit2.setIdbranch(param.getIdbranch());
        pemakaianDepositDebit2.setSourcedocumentdate(param.getSourcedocumentdate());

        detailrepo.saveAndFlush(pemakaianDepositDebit2);
    }

    private void createPembayaranPinjaman(Long idjournal,Timestamp ts, PostingJournalParam param){
        JournalDetail pembayaranPinjamanDebit2 = new JournalDetail();
        JournalDetailPK pembayaranPinjamanDebit2PK = new JournalDetailPK();
        pembayaranPinjamanDebit2PK.setJournalid(idjournal);
        pembayaranPinjamanDebit2PK.setAccountcode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
        pembayaranPinjamanDebit2.setJournalDetailPK(pembayaranPinjamanDebit2PK);
        pembayaranPinjamanDebit2.setDebit(param.getAmountPembayaranPinjaman());
        pembayaranPinjamanDebit2.setCredit(0.0);
        pembayaranPinjamanDebit2.setDescription(param.getDescriptionDetailPinjaman());

        pembayaranPinjamanDebit2.setIdvendor(param.getIdvendor());
        pembayaranPinjamanDebit2.setSourcenumber(param.getSourcenumber());
        if(param.getTransaksitime() != null){
            pembayaranPinjamanDebit2.setTransaksitime(param.getTransaksitime());
        }else{
            pembayaranPinjamanDebit2.setTransaksitime(ts);
        }
        pembayaranPinjamanDebit2.setIdcompany(param.getIdcompany());
        pembayaranPinjamanDebit2.setIdbranch(param.getIdbranch());
        pembayaranPinjamanDebit2.setSourcedocumentdate(param.getSourcedocumentdate());

//                    detailrepo.saveAndFlush(pembayaranPinjamanDebit1);
        detailrepo.saveAndFlush(pembayaranPinjamanDebit2);
    }
}
