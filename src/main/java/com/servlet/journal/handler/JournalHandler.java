package com.servlet.journal.handler;

import com.servlet.chartofaccount.AccountCOAEnum;
import com.servlet.deposit.entity.Deposit;
import com.servlet.deposit.repo.DepositRepo;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.journal.entity.*;
import com.servlet.journal.repo.JournalDetailRepo;
import com.servlet.journal.repo.JournalRepo;
import com.servlet.journal.service.JournalService;
import com.servlet.pinjaman.entity.Pinjaman;
import com.servlet.pinjaman.repo.PinjamanRepo;
import com.servlet.purchasereceive.entity.PurchaseReceive;
import com.servlet.purchasereceive.repo.PurchaseReceiveRepo;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ValidationDataMessage;
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
            journal.setTransaksitime(ts);
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
                JournalDetailPK debitPK = new JournalDetailPK();
                debitPK.setJournalid(idjournal);
                debitPK.setAccountcode(AccountCOAEnum.KAS_ASSET.getAccCode());
                detailDebit.setDebit(param.getAmount());
                detailDebit.setCredit(0.0);
                detailDebit.setDescription(param.getDescriptionDetail());


                JournalDetailPK creditPK = new JournalDetailPK();
                creditPK.setJournalid(idjournal);
                creditPK.setAccountcode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
                detailCredit.setDebit(0.0);
                detailCredit.setCredit(param.getAmount());
                detailCredit.setDescription(param.getDescriptionDetail());
            }else if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType())){
                flag = true;

//                Debit   1-1001 Kas / Bank
//                Kredit  2-2201 Pinjaman Vendor

                JournalDetailPK debitPK = new JournalDetailPK();
                debitPK.setJournalid(idjournal);
                debitPK.setAccountcode(AccountCOAEnum.KAS_ASSET.getAccCode());
                detailDebit.setDebit(param.getAmount());
                detailDebit.setCredit(0.0);
                detailDebit.setDescription(param.getDescriptionDetail());

                JournalDetailPK creditPK = new JournalDetailPK();
                creditPK.setJournalid(idjournal);
                creditPK.setAccountcode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
                detailCredit.setDebit(0.0);
                detailCredit.setCredit(param.getAmount());
                detailCredit.setDescription(param.getDescriptionDetail());
            }else if(param.getSourcetype().equals(SourceTypeEnum.TRANSAKSI_PRC.getSourceType())){
//                flag = true;
                if(param.getAmountPemakaianDeposit().doubleValue() > 0){
//                    Debit   2-2101 Hutang Usaha   (clearing)
//                    Kredit  2-2301 Deposit Vendor

                    JournalDetail pemakaianDepositDebit = new JournalDetail();
                    JournalDetailPK pemakaianDepositDebitPK = new JournalDetailPK();
                    pemakaianDepositDebitPK.setJournalid(idjournal);
                    pemakaianDepositDebitPK.setAccountcode(AccountCOAEnum.HUTANGUSAHA_DEPOSIT_LIABILITY.getAccCode());
                    pemakaianDepositDebit.setDebit(param.getAmountPemakaianDeposit());
                    pemakaianDepositDebit.setCredit(0.0);
                    pemakaianDepositDebit.setDescription(param.getDescriptionDetailDeposit());

                    pemakaianDepositDebit.setIdvendor(param.getIdvendor());
                    pemakaianDepositDebit.setSourcenumber(param.getSourcenumber());
                    pemakaianDepositDebit.setTransaksitime(ts);
                    pemakaianDepositDebit.setIdcompany(param.getIdcompany());
                    pemakaianDepositDebit.setIdbranch(param.getIdbranch());

                    JournalDetail pemakaianDepositCredit = new JournalDetail();
                    JournalDetailPK pemakaianDepositCreditPK = new JournalDetailPK();
                    pemakaianDepositCreditPK.setJournalid(idjournal);
                    pemakaianDepositCreditPK.setAccountcode(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode());
                    pemakaianDepositCredit.setDebit(0.0);
                    pemakaianDepositCredit.setCredit(param.getAmountPemakaianDeposit());
                    pemakaianDepositCredit.setDescription(param.getDescriptionDetailDeposit());

                    pemakaianDepositCredit.setIdvendor(param.getIdvendor());
                    pemakaianDepositCredit.setSourcenumber(param.getSourcenumber());
                    pemakaianDepositCredit.setTransaksitime(ts);
                    pemakaianDepositCredit.setIdcompany(param.getIdcompany());
                    pemakaianDepositCredit.setIdbranch(param.getIdbranch());

                    detailrepo.saveAndFlush(pemakaianDepositDebit);
                    detailrepo.saveAndFlush(pemakaianDepositCredit);
                }

                if(param.getAmountPembayaranPinjaman().doubleValue() > 0){
//                    Debit   2-2101 Hutang Usaha   (clearing)
//                    Kredit  2-2201 Pinjaman Vendor
                    JournalDetail pembayaranPinjamanDebit = new JournalDetail();
                    JournalDetailPK pembayaranPinjamanDebitPK = new JournalDetailPK();
                    pembayaranPinjamanDebitPK.setJournalid(idjournal);
                    pembayaranPinjamanDebitPK.setAccountcode(AccountCOAEnum.HUTANGUSAHA_PINJAMAN_LIABILITY.getAccCode());
                    pembayaranPinjamanDebit.setDebit(param.getAmountPembayaranPinjaman());
                    pembayaranPinjamanDebit.setCredit(0.0);
                    pembayaranPinjamanDebit.setDescription(param.getDescriptionDetailPinjaman());

                    pembayaranPinjamanDebit.setIdvendor(param.getIdvendor());
                    pembayaranPinjamanDebit.setSourcenumber(param.getSourcenumber());
                    pembayaranPinjamanDebit.setTransaksitime(ts);
                    pembayaranPinjamanDebit.setIdcompany(param.getIdcompany());
                    pembayaranPinjamanDebit.setIdbranch(param.getIdbranch());

                    JournalDetail pembayaranPinjamanCredit = new JournalDetail();
                    JournalDetailPK pembayaranPinjamanCreditPK = new JournalDetailPK();
                    pembayaranPinjamanCreditPK.setJournalid(idjournal);
                    pembayaranPinjamanCreditPK.setAccountcode(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode());
                    pembayaranPinjamanCredit.setDebit(0.0);
                    pembayaranPinjamanCredit.setCredit(param.getAmountPembayaranPinjaman());
                    pembayaranPinjamanCredit.setDescription(param.getDescriptionDetailPinjaman());

                    pembayaranPinjamanCredit.setIdvendor(param.getIdvendor());
                    pembayaranPinjamanCredit.setSourcenumber(param.getSourcenumber());
                    pembayaranPinjamanCredit.setTransaksitime(ts);
                    pembayaranPinjamanCredit.setIdcompany(param.getIdcompany());
                    pembayaranPinjamanCredit.setIdbranch(param.getIdbranch());

                    detailrepo.saveAndFlush(pembayaranPinjamanDebit);
                    detailrepo.saveAndFlush(pembayaranPinjamanCredit);
                }


            }

            if(flag) {
                detailDebit.setIdvendor(param.getIdvendor());
                detailDebit.setSourcenumber(param.getSourcenumber());
                detailDebit.setTransaksitime(ts);
                detailDebit.setIdcompany(param.getIdcompany());
                detailDebit.setIdbranch(param.getIdbranch());

                detailCredit.setIdvendor(param.getIdvendor());
                detailCredit.setSourcenumber(param.getSourcenumber());
                detailCredit.setTransaksitime(ts);
                detailCredit.setIdcompany(param.getIdcompany());
                detailCredit.setIdbranch(param.getIdbranch());

                detailrepo.saveAndFlush(detailDebit);
                detailrepo.saveAndFlush(detailCredit);
            }


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
    public List<ValidationDataMessage> migrationOrIntegrity(BodyMigrasi payload) {
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        List<ValidationDataMessage> validations = new ArrayList<>();
        List<Deposit> listDeposit = new ArrayList<>();
        List<Pinjaman> listPinjaman = new ArrayList<>();
        List<PurchaseReceive> listPurchaseReceive = new ArrayList<>();
        List<String> listSourceNumber = new ArrayList<>();
        HashMap<String,String> listCompBranch = new HashMap<>();
        try {
            if (payload.getIsall().equals("Y")) {
                listDeposit = depositRepo.findAll();
                listPinjaman = pinjamanRepo.findAll();
                listPurchaseReceive = purchaseReceiveRepo.findAll();
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
                    listDeposit = depositRepo.fingByRangeDate(fromDate, toDate);
                    listPinjaman = pinjamanRepo.fingByRangeDate(fromDate, toDate);
                    listPurchaseReceive = purchaseReceiveRepo.fingByRangeDate(fromDate, toDate);
                }

            }

            if(listDeposit != null && listDeposit.size() > 0){
                for(Deposit val : listDeposit){
                    String compBranch = val.getIdcompany()+"-"+ val.getIdbranch();
                    if(listCompBranch.get(compBranch) == null){
                        listCompBranch.put(compBranch,compBranch);
                    }

                    if(val.isIsdelete()){
                        listSourceNumber.add(val.getNodocument());
                        repo.deleteBySourceNumber(val.getNodocument());
                        detailrepo.deleteDetailBySourceNumber(val.getNodocument());
                    }else{
                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(val.getIdcompany(),val.getIdbranch(),val.getNodocument());
                        PostingJournalParam param = new PostingJournalParam();
                        if(listDetail != null && listDetail.size() > 0){
                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdcompany());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcetype(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType());
                            if(val.getModifieddate() != null){
                                param.setTransaksitime(val.getModifieddate());
                            }else{
                                param.setTransaksitime(val.getCreateddate());
                            }

                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }else{

                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdcompany());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setIdvendor(val.getIdvendor());
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcetype(SourceTypeEnum.TOPUP_DEPOSIT.getSourceType());
                            if(val.getModifieddate() != null){
                                param.setTransaksitime(val.getModifieddate());
                            }else{
                                param.setTransaksitime(val.getCreateddate());
                            }
                            param.setDescription("INTEGRASI");
                            param.setCreatedby(payload.getIduser());

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
                    String compBranch = val.getIdcompany()+"-"+ val.getIdbranch();
                    if(listCompBranch.get(compBranch) == null){
                        listCompBranch.put(compBranch,compBranch);
                    }
                    if(val.isIsdelete()){
                        listSourceNumber.add(val.getNodocument());
                        repo.deleteBySourceNumber(val.getNodocument());
                        detailrepo.deleteDetailBySourceNumber(val.getNodocument());
                    }else{
                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(val.getIdcompany(),val.getIdbranch(),val.getNodocument());
                        PostingJournalParam param = new PostingJournalParam();
                        if(listDetail != null && listDetail.size() > 0){
                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdcompany());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
                            if(val.getModifieddate() != null){
                                param.setTransaksitime(val.getModifieddate());
                            }else{
                                param.setTransaksitime(val.getCreateddate());
                            }

                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }else{

                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdcompany());
                            param.setAmount(val.getAmount());
                            param.setDescriptionDetail("INTEGRASI");
                            param.setIdvendor(val.getIdvendor());
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
                            if(val.getModifieddate() != null){
                                param.setTransaksitime(val.getModifieddate());
                            }else{
                                param.setTransaksitime(val.getCreateddate());
                            }
                            param.setDescription("INTEGRASI");
                            param.setCreatedby(payload.getIduser());

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
                    String compBranch = val.getIdcompany()+"-"+ val.getIdbranch();
                    if(listCompBranch.get(compBranch) == null){
                        listCompBranch.put(compBranch,compBranch);
                    }

                    if(val.isIsdelete()){
                        listSourceNumber.add(val.getNodocument());
                        repo.deleteBySourceNumber(val.getNodocument());
                        detailrepo.deleteDetailBySourceNumber(val.getNodocument());
                    }else{
                        List<JournalDetail> listDetail = detailrepo.fingBySourceNumber(val.getIdcompany(),val.getIdbranch(),val.getNodocument());
                        PostingJournalParam param = new PostingJournalParam();
                        if(listDetail != null && listDetail.size() > 0){
                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdcompany());
                            param.setAmountPemakaianDeposit(val.getSetor());
                            param.setDescriptionDetailDeposit("INTEGRASI");
                            param.setAmountPembayaranPinjaman(val.getSetor_pinjaman());
                            param.setDescriptionDetailPinjaman("INTEGRASI");
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
                            if(val.getModifieddate() != null){
                                param.setTransaksitime(val.getModifieddate());
                            }else{
                                param.setTransaksitime(val.getCreateddate());
                            }

                            List<ValidationDataMessage> validationsPosting = updateJournalDetail(param);
                            if(validationsPosting != null && validationsPosting.size() > 0){
                                validations.addAll(validationsPosting);
                            }
                        }else{
                            param.setIdcompany(val.getIdcompany());
                            param.setIdbranch(val.getIdcompany());
                            param.setAmountPemakaianDeposit(val.getSetor());
                            param.setDescriptionDetailDeposit("INTEGRASI");
                            param.setAmountPembayaranPinjaman(val.getSetor_pinjaman());
                            param.setDescriptionDetailPinjaman("INTEGRASI");
                            param.setIdvendor(val.getIdvendor());
                            param.setSourcenumber(val.getNodocument());
                            param.setSourcetype(SourceTypeEnum.TRANSAKSI_PRC.getSourceType());
                            if(val.getModifieddate() != null){
                                param.setTransaksitime(val.getModifieddate());
                            }else{
                                param.setTransaksitime(val.getCreateddate());
                            }
                            param.setDescription("INTEGRASI");
                            param.setCreatedby(payload.getIduser());

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
            if(listCompBranch != null && listCompBranch.size() > 0){
                for (Map.Entry<String, String> entry : listCompBranch.entrySet()) {
                    String[] arrVal = entry.getValue().split("-");
                    if(arrVal.length > 0){
                        Long idcoompany = Long.getLong(arrVal[0]);
                        Long idbranch = Long.getLong(arrVal[1]);
                        historyAppsService.saveHistory(idcoompany, idbranch, payload.getIduser(), "INTEGRASI",namaMenu,payload.toString(),"","",ts);
                    }


                }
            }


        }
        return validations;
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
                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.KAS_ASSET.getAccCode())){
                            table.setDebit(param.getAmount());
                        }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
                            table.setCredit(param.getAmount());
                        }
                        table.setDescription(param.getDescriptionDetail());
                    }else if(param.getSourcetype().equals(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType())){
                        if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.KAS_ASSET.getAccCode())){
                            table.setDebit(param.getAmount());
                        }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
                            table.setCredit(param.getAmount());
                        }
                        table.setDescription(param.getDescriptionDetail());
                    }else if(param.getSourcetype().equals(SourceTypeEnum.TRANSAKSI_PRC.getSourceType())){
                        if(param.getAmountPemakaianDeposit().doubleValue() > 0){
                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.HUTANGUSAHA_DEPOSIT_LIABILITY.getAccCode())){
                                table.setDebit(param.getAmountPemakaianDeposit());
                            }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode())){
                                table.setCredit(param.getAmountPemakaianDeposit());
                            }
                            table.setDescription(param.getDescriptionDetailDeposit());
                        }

                        if(param.getAmountPembayaranPinjaman().doubleValue() > 0){
                            if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.HUTANGUSAHA_PINJAMAN_LIABILITY.getAccCode())){
                                table.setDebit(param.getAmountPembayaranPinjaman());
                            }else if(val.getJournalDetailPK().getAccountcode().equals(AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode())){
                                table.setCredit(param.getAmountPembayaranPinjaman());
                            }
                            table.setDescription(param.getDescriptionDetailPinjaman());
                        }

                    }
                    table.setTransaksitime(param.getTransaksitime());
                    detailrepo.saveAndFlush(table);
                }
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
}
