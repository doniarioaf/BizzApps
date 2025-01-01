package com.servlet.pelunasanpiutang.handler;

import com.servlet.customer.service.CustomerService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.pelunasanpiutang.entity.*;
import com.servlet.pelunasanpiutang.repo.PelunasanPiutangItemRepo;
import com.servlet.pelunasanpiutang.repo.PelunasanPiutangRepo;
import com.servlet.pelunasanpiutang.service.PelunasanPiutangService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PelunasanPiutangHandler implements PelunasanPiutangService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PelunasanPiutangRepo repo;

    @Autowired
    private PelunasanPiutangItemRepo repoItem;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RunningNumberService runningNumberService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "PELUNASANPIUTANG";

    @Override
    public PelunasanPiutangTemplate getTemplate(Long idcompany, Long idbranch) {
        PelunasanPiutangTemplate data = new PelunasanPiutangTemplate();
        data.setCustomerGrupOpt(customerService.getListCustomerGrup(idcompany,idbranch));
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPelunasanPiutang body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANPIUTANG, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                PelunasanPiutang table = new PelunasanPiutang();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setKurs(body.getKurs());
                table.setIsdelete(false);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
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
                    repoItem.deleteAllDetailByIdPelunasanPiutang(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANPIUTANG);
                    validations.add(validationsItems.get(0));
                }

            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_PELUNASANPIUTANG);
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

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch, Long idpelunasanpiutang, BodyPelunasanPiutangItem[] items){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<BodyPelunasanPiutangItem> listitem = new ArrayList<>();
        try{
            if(items.length > 0){
                for(BodyPelunasanPiutangItem val:items){
                    PelunasanPiutangItemPK pk = new PelunasanPiutangItemPK();
                    pk.setIdpelunasanpiutang(idpelunasanpiutang);
                    pk.setIdinvoice(val.getIdinvoice());
                    PelunasanPiutangItem table = new PelunasanPiutangItem();
                    table.setPelunasanPiutangItemPK(pk);
                    table.setBiayabebanudangmati(val.getBiayabebanudangmati());
                    table.setBiayabank(val.getBiayabank());
                    table.setPembayaran(val.getPembayaran());
                    table.setMetodepembayaran(val.getMetodepembayaran());
                    repoItem.saveAndFlush(table);
                    listitem.add(val);
                }

                for(BodyPelunasanPiutangItem val:items){
                    Double pembayaran = val.getPembayaran() + val.getBiayabebanudangmati() + val.getBiayabank();
                    invoiceService.updateOustandingKurang(val.getIdinvoice(), pembayaran);
                }
            }
        }catch (Exception e){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }

        String dataItems = listitem.toString();
        maps.put("validations",validations);
        maps.put("dataItems",dataItems);
        return maps;
    }
}
