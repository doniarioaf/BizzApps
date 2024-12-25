package com.servlet.invoice.handler;

import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.entity.*;
import com.servlet.invoice.mapper.QueryDataDetail;
import com.servlet.invoice.mapper.QueryDataList;
import com.servlet.invoice.mapper.QueryPrintInvoice;
import com.servlet.invoice.repo.InvoiceRepo;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.packinglist.entity.ParamDropDownPackingList;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.parameterclient.entity.ValueParameter;
import com.servlet.parameterclient.service.ParameterClientService;
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
import java.util.List;

@Service
public class InvoiceHandler implements InvoiceService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InvoiceRepo repo;
    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private RunningNumberService runningNumberService;
    @Autowired
    private PackingListService packingListService;
    @Autowired
    private ParameterClientService parameterClientService;

    protected final String namaMenu = "Invoice";

    @Override
    public List<InvoiceDataList> getList(Long idcompany, Long idbranch, ParamSearchInvoice param) {
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
        sqlBuilder.append(" order by data.id desc ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataList(), queryParameters);
    }

    @Override
    public InvoiceTemplate getTemplate(Long idcompany, Long idbranch) {
        InvoiceTemplate data = new InvoiceTemplate();
        ParamDropDownPackingList paramPL = new ParamDropDownPackingList();
        paramPL.setMenu("INVOICE");
        data.setPackingListOpt(packingListService.getDropDown(idcompany,idbranch,paramPL));
        return data;
    }

    @Override
    public InvoiceDataDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<InvoiceDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataDetail(), queryParameters);
        if(list != null && list.size() > 0){
            InvoiceDataDetail det = list.get(0);
            det.setPackinglist(packingListService.getDetail(det.getIdpackinglist(), idcompany,idbranch));

            return det;
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyInvoice body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        String docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE, ts);
        if(docNumber.equals("")) {
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                Invoice table = new Invoice();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setDate(new Date(body.getDate()));
                table.setKurs(body.getKurs());
                table.setIdpackinglist(body.getIdpackinglist());
                table.setPhone(body.getPhone());
                table.setIsdelete(false);
                table.setCreatedby(iduser);
                table.setCreateddate(ts);
                idsave = repo.saveAndFlush(table).getId();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,table.toString(),"","",ts);
            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyInvoice body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());

        if(validations.size() == 0) {
            try{
                Invoice table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue()){
                    String before = table.toString();
                    table.setKurs(body.getKurs());
                    table.setPhone(body.getPhone());
                    table.setModifiedby(iduser);
                    table.setModifieddate(ts);
                    idsave = repo.saveAndFlush(table).getId();
                    String after = table.toString();
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",after,before,ts);
                }
            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE);
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
                Invoice table = repo.getById(id);
                if(table.getIdcompany().longValue() == idcompany.longValue() && table.getIdbranch().longValue() == idbranch.longValue()){
                    table.setIsdelete(true);
                    table.setDeleteby(iduser);
                    table.setDeletedate(ts);
                    idsave = repo.saveAndFlush(table).getId();

                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE",namaMenu,table.toString(),"","",ts);
                }
            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_INVOICE);
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
    public PrintInvoice getPrintDataByID(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryPrintInvoice().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<PrintInvoice> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryPrintInvoice(), queryParameters);
        if(list != null && list.size() > 0){
            ValueParameter param = parameterClientService.getValueByParamName(idcompany,idbranch,"COMPANYNAME","TEXT");
            ValueParameter paramAddress1 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS1","TEXT");
            ValueParameter paramAddress2 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS2","TEXT");
            ValueParameter paramAddress3 = parameterClientService.getValueByParamName(idcompany,idbranch,"ADDRESS3","TEXT");
            ValueParameter parambankComp = parameterClientService.getValueByParamName(idcompany,idbranch,"BANK","TEXT");
            ValueParameter parambankAccnoComp = parameterClientService.getValueByParamName(idcompany,idbranch,"BANKACCNO","TEXT");
            ValueParameter parambankAccnameComp = parameterClientService.getValueByParamName(idcompany,idbranch,"BANKACCNAME","TEXT");

            PrintInvoice det = list.get(0);
            det.setPackinglist(packingListService.getDetail(det.getIdpackinglist(), idcompany,idbranch));
            det.setCompanyName(param.getStrValue());
            det.setAddress1(paramAddress1.getStrValue());
            det.setAddress2(paramAddress2.getStrValue());
            det.setAddress3(paramAddress3.getStrValue());
            det.setBankCompany(parambankComp.getStrValue());
            det.setBankAccNoCompany(parambankAccnoComp.getStrValue());
            det.setBankAccNameCompany(parambankAccnameComp.getStrValue());

            return det;
        }
        return null;
    }
}
