package com.servlet.cancelpackinglist.handler;

import com.servlet.cancelpackinglist.entity.*;
import com.servlet.cancelpackinglist.mapper.*;
import com.servlet.cancelpackinglist.repo.CancelPackingListRepo;
import com.servlet.cancelpackinglist.repo.CancelPakcingListItemRepo;
import com.servlet.cancelpackinglist.service.CancelPackingListService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.entity.InvoiceDataList;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.packinglist.mapper.QueryDataList;
import com.servlet.packinglist.mapper.QueryPackingListReportKartuStock;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.stockadjusment.mapper.QueryCalculateQtySA;
import com.servlet.stockitems.entity.ReportKartuStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CancelPackingListHandler implements CancelPackingListService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CancelPackingListRepo repo;

    @Autowired
    private CancelPakcingListItemRepo itemrepo;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private RunningNumberService runningNumberService;

    protected final String namaMenu = "CancelPackingList";

    @Override
    public List<CancelPLList> getList(Long idcompany, Long idbranch, ParamSearchCancelPackingList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new Query_CancelPLList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.datecancel >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.datecancel <= '"+dt.toString()+"'");
        }
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new Query_CancelPLList(), queryParameters);
    }

    @Override
    public ReturnData cancelPackingList(Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        InvoiceDataList inv = invoiceService.getDataByIdPackingList(idcompany,idbranch, body.getIdpackinglist());
        if(inv != null){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_INVOICE,"packinglist ini terpasang pada invoice ("+inv.getNodocument()+")");
            validations.add(msg);
        }
        if(validations.size() == 0){
            List<QueryNotJoinCancelPackingListData> cancelData = getDataByIdPackingList(idcompany,idbranch, body.getIdpackinglist());
            if(cancelData != null && cancelData.size() > 0){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_CANCEL,"Document ini sudah di Cancel");
                validations.add(msg);
            }
        }
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_CANCELPACKINGLIST, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                CancelPackingList table = new CancelPackingList();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
//                table.setNodocument("C"+body.getNodocumentPL());
                table.setNodocument(docNumber);
                table.setDatecancel(new Date(body.getDatecancel()));
                table.setIdpackinglist(body.getIdpackinglist());
//                table.setKeterangan("Pembatalan Packinglist ("+body.getNodocumentPL()+")");
                table.setKeterangan(body.getKeterangan());
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
                    itemrepo.deleteAllDetailByIdCancelPackingList(idsave);
                    runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_CANCELPACKINGLIST);
                    validations.add(validationsItems.get(0));
                }
            }catch (Exception e) {
                e.printStackTrace();
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
    public ReturnData editCancelPackingList(Long idcancel, Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                CancelPackingList table = repo.getById(idcancel);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue() && !table.isIsdelete()) {
                    String mixDataBef = "";
                    table.setKeterangan(body.getKeterangan());
                    table.setModifieddate(ts);
                    table.setModifiedby(iduser);
                    idsave = repo.saveAndFlush(table).getId();
                    itemrepo.deleteAllDetailByIdCancelPackingList(idcancel);
                    HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,idsave, body.getItems());
                    List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                    if (validationsItems.size() == 0) {
//                        String data = table.toString();
//                        String dataItems = (String) mapsItems.get("dataItems");
//                        String mixData = "header = " + data + " | Items = " + dataItems;
//                        historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, "", mixData, mixDataBef, ts);
                    } else {
                        validations.add(validationsItems.get(0));
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();
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
    public ReturnData deleteCancelPackingListByidpackinglist(Long idcompany, Long idbranch, Long iduser, Long idpackinglist) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
//        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                repo.deleteAllByIdPackingList(iduser,idcompany,idbranch,idpackinglist);
                idsave = idpackinglist;
//                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,table.toString(),"","",ts);
            }catch (Exception e) {
                e.printStackTrace();
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
    public List<QueryNotJoinCancelPackingListData> getDataByIdPackingList(Long idcompany, Long idbranch, Long idpackinglist) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryNotJoinCancelPackingList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryNotJoinCancelPackingList(), queryParameters);
    }

    @Override
    public CancelPackingListData getDetail(Long idcompany, Long idbranch, Long id) {
        List<CancelPackingListData> list = getDetailByID(idcompany,idbranch,id);
        if(list != null && list.size() > 0){
            CancelPackingListData data = list.get(0);
            data.setItems(getListItemByIDCancel(data.getId()));

            return data;
        }
        return null;
    }

    @Override
    public Long calculateQtyCPL(Long idcompany, Long idbranch, ParamCalculateQtyCPL param) {
        String selectidCPL = " select cpl.id from cancel_packinglist as cpl where cpl.idcompany = "+idcompany+" and cpl.idbranch = "+idbranch+" and cpl.isdelete = false ";
        if(param.getDateFrom() != null){
            Date dt = new Date(param.getDateFrom());
            selectidCPL += " and cpl.datecancel >= '"+dt.toString()+"' ";
        }
        if(param.getDateThru() != null){
            Date dt = new Date(param.getDateThru());
            selectidCPL += " and cpl.datecancel <= '"+dt.toString()+"' ";
        }

        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateQtyCPL().schema());
        sqlBuilder.append(" where data.idcancelpackinglist in ("+selectidCPL+") ");
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

        if(param.getType() != null){
            sqlBuilder.append(" and data.type = '"+param.getType()+"' ");
        }
        final Object[] queryParameters = new Object[] {};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateQtyCPL(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0L;
    }

    @Override
    public List<ReportKartuStock> getListReportKartuStock(Long idcompany, Long idbranch, ParamSearchCancelPackingList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCancelPackingListReportKartuStock().schema());
        sqlBuilder.append(" where cpl.idcompany = ? and cpl.idbranch = ? and cpl.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and cpl.datecancel >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and cpl.datecancel <= '"+dt.toString()+"'");
        }
        if(param.getListIdProduct() != null && !param.getListIdProduct().equals("")){
            sqlBuilder.append(" and data.idproduct in ("+param.getListIdProduct()+") ");
        }
        if(param.getListIdCategoryProduct() != null && !param.getListIdCategoryProduct().equals("")){
            sqlBuilder.append(" and data.idcategoryproduct in ("+param.getListIdCategoryProduct()+") ");
        }
//        sqlBuilder.append(" and data.qty > 0 ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCancelPackingListReportKartuStock(), queryParameters);
    }

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch, Long idcancelpackinglist, BodyCancelPackingListItem[] items){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<BodyCancelPackingListItem> listitem = new ArrayList<>();
        try{
            if(items.length > 0){
                int noseq =1;
                for(BodyCancelPackingListItem val:items){
                    CancelPackingListItemPK pk = new CancelPackingListItemPK();
                    pk.setIdcancelpackinglist(idcancelpackinglist);
                    pk.setIdproduct(val.getIdproduct());
                    pk.setIdcategoryproduct(val.getIdcategoryproduct());
                    pk.setBox(val.getBox());
                    pk.setNoseq(noseq);
                    CancelPackingListItem table = new CancelPackingListItem();
                    table.setCancelPackingListItemPK(pk);
                    table.setQty(val.getQty());
                    table.setBrutoweight(0.0);
                    table.setAllowance(0.0);
                    table.setNettoweight(0.0);
                    table.setPrice(0.0);
                    table.setTotalprice(0.0);
                    table.setType(val.getType());
                    itemrepo.saveAndFlush(table);
                    listitem.add(val);
                    noseq++;
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

    private List<CancelPackingListData> getDetailByID(Long idcompany, Long idbranch, Long id) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new Query_CancelPackingListData().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new Query_CancelPackingListData(), queryParameters);
    }

    private List<CancelPackingListItemData> getListItemByIDCancel(Long idcancel) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new Query_CancelPackingListItemData().schema());
        sqlBuilder.append(" where data.idcancelpackinglist = ? ");
        final Object[] queryParameters = new Object[] {idcancel};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new Query_CancelPackingListItemData(), queryParameters);
    }

}
