package com.servlet.komisi.handler;

import com.servlet.charge.entity.ChargeList;
import com.servlet.charge.service.ChargeService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.komisi.entity.*;
import com.servlet.komisi.mapper.QueryKomisiDetail;
import com.servlet.komisi.mapper.QueryKomisiItemNotJoin;
import com.servlet.komisi.mapper.QueryKomisiList;
import com.servlet.komisi.repo.KomisiItemRepo;
import com.servlet.komisi.repo.KomisiRepo;
import com.servlet.komisi.service.KomisiService;
import com.servlet.packinglist.entity.BodyPackingListItem;
import com.servlet.packinglist.entity.PackingList;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
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
public class KomisiHandler implements KomisiService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private KomisiRepo repo;

    @Autowired
    private KomisiItemRepo repoItems;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private PurchaseReceiveService purchaseReceiveService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private ChargeService chargeService;

    @Autowired
    private RunningNumberService runningNumberService;

    protected final String namaMenu = "Komisi";

    @Override
    public List<KomisiList> getList(Long idcompany, Long idbranch, ParamKomisi param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryKomisiList().schema());
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
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryKomisiList(), queryParameters);
    }

    @Override
    public KomisiTabData getAll(Long idcompany, Long idbranch, ParamKomisi param) {
        KomisiTabData data = new KomisiTabData();
        data.setListkomisi(getList(idcompany,idbranch,param));
        data.setListpr(purchaseReceiveService.getListKomisi(idcompany,idbranch,param));
        return data;
    }

    @Override
    public KomisiTemplate getTemplate(Long idcompany, Long idbranch) {
        ParamVendor pvbroker = new ParamVendor();
        pvbroker.setVendorTypes("'BROKER'");
        long idbox = 0;
        ChargeList chargeList = chargeService.getChargeByName(idcompany,idbranch,"BOX");
        if(chargeList != null){
            idbox = chargeList.getId().longValue();
        }
        KomisiTemplate data = new KomisiTemplate();
        data.setVendorBrokerOpt(vendorService.getListDropdown(idcompany,idbranch,pvbroker));
        data.setIdbox(idbox);
        return data;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyKomisi body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_KOMISI, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                Komisi table = new Komisi();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setNote(body.getNote());
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
                    repoItems.deleteAllDetailByIdKomisi(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_KOMISI);
                    validations.add(validationsItems.get(0));
                }

            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_KOMISI);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyKomisi body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                Komisi table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    String mixDataBef = "header = " + table.toString() + " | Items = " + getItemsNotJoin(id).toString();

                    table.setDate(new Date(body.getDate()));
                    table.setNote(body.getNote());
                    table.setModifiedby(iduser);
                    table.setModifieddate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    repoItems.deleteAllDetailByIdKomisi(id);

                    HashMap<Object, Object> mapsItems = setItems(idcompany, idbranch, idsave, body.getItems());
                    List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                    if (validationsItems.size() == 0) {
                        String data = table.toString();
                        String dataItems = (String) mapsItems.get("dataItems");
                        String mixData = "header = " + data + " | Items = " + dataItems;
                        historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, "", mixData, mixDataBef, ts);
                    } else {
                        validations.add(validationsItems.get(0));
                    }
                }
            }catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }
        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
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
        if(validations.size() == 0) {
            try{
                Komisi table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    String mixDataBef = "header = " + table.toString() + " | Items = " + getItemsNotJoin(id).toString();
                    table.setIsdelete(true);
                    table.setDeleteby(iduser);
                    table.setDeletedate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "DELETE", namaMenu, mixDataBef, "", "", ts);
                }

            }catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        }
        if(validations.size() > 0){
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE_ERROR",namaMenu,validations.get(0).getMessage(),"","",ts);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public KomisiDetail getDetail(Long idcompany, Long idbranch, Long id) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryKomisiDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<KomisiDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryKomisiDetail(), queryParameters);
        if(list != null && list.size() > 0){
            long idbox = 0;
            ChargeList chargeList = chargeService.getChargeByName(idcompany,idbranch,"BOX");
            if(chargeList != null){
                idbox = chargeList.getId().longValue();
            }

            ParamKomisi pk = new ParamKomisi();
            pk.setMenu("DETAILITEMKOMISI");
            pk.setIdkomisi(id);
            pk.setIdbox(idbox);

            KomisiDetail data = list.get(0);
            data.setItems(purchaseReceiveService.getListKomisi(idcompany,idbranch,pk));

            return data;

        }
        return null;
    }

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch, Long idkomisi, BodyKomisiItem[] items){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<BodyKomisiItem> listitem = new ArrayList<>();
        try{
            if(items.length > 0){
                for(BodyKomisiItem val:items){
                    KomisiItemPK pk = new KomisiItemPK();
                    pk.setIdkomisi(idkomisi);
                    pk.setIdpurchasereceive(val.getIdpurchasereceive());
                    KomisiItem table = new KomisiItem();
                    table.setKomisiItemPK(pk);
                    table.setKoli(val.getKoli());
                    table.setKomisiperkoli(val.getKomisiperkoli());
                    table.setSubtotalkomisi(val.getSubtotalkomisi());
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
        maps.put("dataItems",dataItems);
        return maps;
    }

    private List<KomisiItemNotJoin> getItemsNotJoin(Long idkomisi) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryKomisiItemNotJoin().schema());
        sqlBuilder.append(" where data.idkomisi = ?  ");

        final Object[] queryParameters = new Object[] {idkomisi};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryKomisiItemNotJoin(), queryParameters);
    }
}
