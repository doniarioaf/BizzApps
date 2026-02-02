package com.servlet.journal.handler;

import com.servlet.chartofaccount.AccountCOAEnum;
import com.servlet.deposit.entity.Deposit;
import com.servlet.deposit.repo.DepositRepo;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.journal.entity.*;
import com.servlet.journal.mapper.QuerySaldo;
import com.servlet.journal.repo.JournalDetailRepo;
import com.servlet.journal.repo.JournalRepo;
import com.servlet.journal.service.JournalService;
import com.servlet.pinjaman.entity.Pinjaman;
import com.servlet.pinjaman.repo.PinjamanRepo;
import com.servlet.purchasereceive.entity.PurchaseReceive;
import com.servlet.purchasereceive.mapper.QueryCalculateAmountSetor;
import com.servlet.purchasereceive.repo.PurchaseReceiveRepo;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
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
                detailCredit.setDebit(0.0);
                detailCredit.setCredit(param.getAmount());
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
                detailCredit.setDebit(0.0);
                detailCredit.setCredit(param.getAmount());
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
//                flag = true;
                if(param.getAmountPemakaianDeposit().doubleValue() > 0){
//                    JournalDetail pemakaianDepositDebit1 = new JournalDetail();
//                    JournalDetailPK pemakaianDepositDebit1PK = new JournalDetailPK();
//                    pemakaianDepositDebit1PK.setJournalid(idjournal);
//                    pemakaianDepositDebit1PK.setAccountcode(AccountCOAEnum.HUTANGUSAHA_DEPOSIT_LIABILITY.getAccCode());
//                    pemakaianDepositDebit1.setJournalDetailPK(pemakaianDepositDebit1PK);
//                    pemakaianDepositDebit1.setDebit(param.getAmountPemakaianDeposit());
//                    pemakaianDepositDebit1.setCredit(0.0);
//                    pemakaianDepositDebit1.setDescription(param.getDescriptionDetailDeposit());
//
//                    pemakaianDepositDebit1.setIdvendor(param.getIdvendor());
//                    pemakaianDepositDebit1.setSourcenumber(param.getSourcenumber());
//                    pemakaianDepositDebit1.setTransaksitime(ts);
//                    pemakaianDepositDebit1.setIdcompany(param.getIdcompany());
//                    pemakaianDepositDebit1.setIdbranch(param.getIdbranch());

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

//                    detailrepo.saveAndFlush(pemakaianDepositDebit1);
                    detailrepo.saveAndFlush(pemakaianDepositDebit2);
                }

                if(param.getAmountPembayaranPinjaman().doubleValue() > 0){
//                    JournalDetail pembayaranPinjamanDebit1 = new JournalDetail();
//                    JournalDetailPK pembayaranPinjamanDebit1tPK = new JournalDetailPK();
//                    pembayaranPinjamanDebit1tPK.setJournalid(idjournal);
//                    pembayaranPinjamanDebit1tPK.setAccountcode(AccountCOAEnum.HUTANGUSAHA_PINJAMAN_LIABILITY.getAccCode());
//                    pembayaranPinjamanDebit1.setJournalDetailPK(pembayaranPinjamanDebit1tPK);
//                    pembayaranPinjamanDebit1.setDebit(param.getAmountPembayaranPinjaman());
//                    pembayaranPinjamanDebit1.setCredit(0.0);
//                    pembayaranPinjamanDebit1.setDescription(param.getDescriptionDetailPinjaman());
//
//                    pembayaranPinjamanDebit1.setIdvendor(param.getIdvendor());
//                    pembayaranPinjamanDebit1.setSourcenumber(param.getSourcenumber());
//                    pembayaranPinjamanDebit1.setTransaksitime(ts);
//                    pembayaranPinjamanDebit1.setIdcompany(param.getIdcompany());
//                    pembayaranPinjamanDebit1.setIdbranch(param.getIdbranch());

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

        try {
            if (payload.getIsall().equals("Y")) {
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
                    listDeposit = depositRepo.fingByRangeDate(idcompany,idbranch,fromDate, toDate);
                    listPinjaman = pinjamanRepo.fingByRangeDate(idcompany,idbranch,fromDate, toDate);
                    listPurchaseReceive = purchaseReceiveRepo.fingByRangeDate(idcompany,idbranch,fromDate, toDate);
                }

            }

            if(listDeposit != null && listDeposit.size() > 0){
                for(Deposit val : listDeposit){
                    if(val.isIsdelete()){
                        listSourceNumber.add(val.getNodocument());
                        repo.deleteBySourceNumber(val.getNodocument());
                        detailrepo.deleteDetailBySourceNumber(val.getNodocument());
                    }else{
                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(val.getIdcompany(),val.getIdbranch(),val.getNodocument());
                        PostingJournalParam param = new PostingJournalParam();
                        if(listDetail != null && listDetail.size() > 0){
                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdbranch());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setIdvendor(val.getIdvendor());
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcedocumentdate(val.getDepositdate());
                            param.setSourcetype(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType());
                            param.setTransaksitime(val.getCreateddate());
//                            if(val.getModifieddate() != null){
//                                param.setTransaksitime(val.getModifieddate());
//                            }else{
//                                param.setTransaksitime(val.getCreateddate());
//                            }

                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }else{

                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdbranch());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setIdvendor(val.getIdvendor());
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcedocumentdate(val.getDepositdate());
                            param.setSourcetype(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType());
                            param.setTransaksitime(val.getCreateddate());
//                            if(val.getModifieddate() != null){
//                                param.setTransaksitime(val.getModifieddate());
//                            }else{
//                                param.setTransaksitime(val.getCreateddate());
//                            }
                            param.setDescription("INTEGRASI");
                            param.setCreatedby(iduser);

                            List<ValidationDataMessage> validationsPosting = postingJournal(param);

                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }
                    }
                }
            }

            if(listPinjaman != null && listPinjaman.size() > 0){
                for(Pinjaman val : listPinjaman){
                    if(val.isIsdelete()){
                        listSourceNumber.add(val.getNodocument());
                        repo.deleteBySourceNumber(val.getNodocument());
                        detailrepo.deleteDetailBySourceNumber(val.getNodocument());
                    }else{
                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(val.getIdcompany(),val.getIdbranch(),val.getNodocument());
                        PostingJournalParam param = new PostingJournalParam();
                        if(listDetail != null && listDetail.size() > 0){
                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdbranch());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setIdvendor(val.getIdvendor());
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcedocumentdate(val.getDate());
                            param.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
                            param.setTransaksitime(val.getCreateddate());
//                            if(val.getModifieddate() != null){
//                                param.setTransaksitime(val.getModifieddate());
//                            }else{
//                                param.setTransaksitime(val.getCreateddate());
//                            }

                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }else{

                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdbranch());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setIdvendor(val.getIdvendor());
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcedocumentdate(val.getDate());
                            param.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
                            param.setTransaksitime(val.getCreateddate());
//                            if(val.getModifieddate() != null){
//                                param.setTransaksitime(val.getModifieddate());
//                            }else{
//                                param.setTransaksitime(val.getCreateddate());
//                            }
                            param.setDescription("INTEGRASI");
                            param.setCreatedby(iduser);

                            List<ValidationDataMessage> validationsPosting = postingJournal(param);

                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }
                    }
                }
            }


            if(listPurchaseReceive != null && listPurchaseReceive.size() > 0){
                for(PurchaseReceive val : listPurchaseReceive){
                    if(val.isIsdelete()){
                        listSourceNumber.add(val.getNodocument());
                        repo.deleteBySourceNumber(val.getNodocument());
                        detailrepo.deleteDetailBySourceNumber(val.getNodocument());
                    }else{
                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(val.getIdcompany(),val.getIdbranch(),val.getNodocument());
                        PostingJournalParam param = new PostingJournalParam();
                        if(listDetail != null && listDetail.size() > 0){
                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdbranch());
                            param.setIdvendor(val.getIdvendor());
                            param.setAmountPemakaianDeposit(val.getSetor());
                            param.setDescriptionDetailDeposit("INTEGRASI");
                            param.setAmountPembayaranPinjaman(val.getSetor_pinjaman());
                            param.setDescriptionDetailPinjaman("INTEGRASI");
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcedocumentdate(val.getTransactiondate());
                            param.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
                            param.setTransaksitime(val.getCreateddate());
//                            if(val.getModifieddate() != null){
//                                param.setTransaksitime(val.getModifieddate());
//                            }else{
//                                param.setTransaksitime(val.getCreateddate());
//                            }

                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }else{
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
//                            if(val.getModifieddate() != null){
//                                param.setTransaksitime(val.getModifieddate());
//                            }else{
//                                param.setTransaksitime(val.getCreateddate());
//                            }
                            param.setDescription("INTEGRASI");
                            param.setCreatedby(iduser);

                            List<ValidationDataMessage> validationsPosting = postingJournal(param);

                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }

                    }
                }
            }

            //untuk hapus, data yang sudah di delete , tapi di jurnal masih ada
            if(listSourceNumber.size() > 0){
                detailrepo.deleteDetailByListSourceNumber(listSourceNumber);
                repo.deleteByListSourceNumber(listSourceNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
            validations.add(msg);
        }

//        String compBranch = val.getIdcompany()+"-"+ val.getIdbranch();
        if(validations.size() == 0){
            historyAppsService.saveHistory(idcompany, idbranch, iduser, "INTEGRASI",namaMenu,payload.toString(),"","",ts);
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
            List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(param.getIdcompany(),param.getIdbranch(),param.getSourcenumber());
            if(listDetail != null && listDetail.size() > 0){
                for(JournalDetail val : listDetail){
                    JournalDetail table = val;
                    if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType())){
//                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.KAS_ASSET.getAccCode())){
//                            table.setDebit(param.getAmount());
//                        }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
//                            table.setCredit(param.getAmount());
//                        }
                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
                            table.setCredit(param.getAmount());
                        }
                        table.setDescription(param.getDescriptionDetail());
                    }else if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType())){
//                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.KAS_ASSET.getAccCode())){
//                            table.setDebit(param.getAmount());
//                        }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
//                            table.setCredit(param.getAmount());
//                        }
                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
                            table.setCredit(param.getAmount());
                        }
                        table.setDescription(param.getDescriptionDetail());
                    }else if(param.getSourcetype().equals(SourceTypeEnum.TRANSAKSI_PRC.getSourceType())){
                        if(param.getAmountPemakaianDeposit().doubleValue() > 0){
//                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.HUTANGUSAHA_DEPOSIT_LIABILITY.getAccCode())){
//                                table.setDebit(param.getAmountPemakaianDeposit());
//                            }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
//                                table.setDebit(param.getAmountPemakaianDeposit());
//                            }

                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
                                table.setDebit(param.getAmountPemakaianDeposit());
                            }
                            table.setDescription(param.getDescriptionDetailDeposit());
                        }

                        if(param.getAmountPembayaranPinjaman().doubleValue() > 0){
//                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.HUTANGUSAHA_PINJAMAN_LIABILITY.getAccCode())){
//                                table.setDebit(param.getAmountPembayaranPinjaman());
//                            }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
//                                table.setDebit(param.getAmountPembayaranPinjaman());
//                            }
                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
                                table.setDebit(param.getAmountPembayaranPinjaman());
                            }
                            table.setDescription(param.getDescriptionDetailPinjaman());
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

    @Override
    public SaldoJournal calculateSaldo(SaldoJournalParam param) {
        Long idven = vendorService.getIdParent(param.getIdcompany(),param.getIdbranch(),param.getIdvendor());
        if(idven == null){
            idven = param.getIdvendor();
        }else if(idven == 0){
            idven = param.getIdvendor();
        }
        List<Long> listidven = vendorService.getListSubIdParent(param.getIdcompany(),param.getIdbranch(),idven);
        //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet hanya sub nya saja
        listidven.add(idven);
        String listidvendor = "";
        if(listidven != null && listidven.size() > 0){
            listidvendor = listidven.toString().replaceAll("\\[","");
            listidvendor = listidvendor.replaceAll("\\]","");
        }

        final StringBuilder sqlBuilder = new StringBuilder("select " + new QuerySaldo().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.accountcode = ? ");
        if(listidvendor != null && !listidvendor.equals("")){
            sqlBuilder.append(" and data.idvendor in ("+listidvendor+") ");
        }else{
            if(param.getIdvendor() != null){
                sqlBuilder.append(" and data.idvendor = "+param.getIdvendor());
            }
        }

        if(param.getTransaksiTime() != null){
            sqlBuilder.append(" and data.transaksitime <= '"+param.getTransaksiTime().toString()+"'");
        }

        sqlBuilder.append(" GROUP BY data.accountcode, data.idvendor ");
//        System.out.println("Query "+param.getAccountCode()+" | "+sqlBuilder.toString());
//        System.out.println("Companyid "+param.getIdcompany()+" | branchID "+param.getIdbranch());
        final Object[] queryParameters = new Object[] {param.getIdcompany(), param.getIdbranch(), param.getAccountCode()};
        List<SaldoJournal> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QuerySaldo(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        SaldoJournal temp = new SaldoJournal();
        temp.setAccountCode("");
        temp.setIdvendor(0L);
        temp.setSaldo(0.0);
        return temp;
    }
}
