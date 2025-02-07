package com.servlet.deposit.handler;

import com.servlet.deposit.entity.*;
import com.servlet.deposit.mapper.*;
import com.servlet.deposit.repo.DepositRepo;
import com.servlet.deposit.service.DepositService;
import com.servlet.filedocument.entity.BodyFileDocument;
import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.filedocument.service.FileDocumentService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.purchasereceive.entity.PurchaseReceiveDataList;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.runningnumber.service.RunningNumberService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.upload.image.FileStorageService;
import com.servlet.upload.image.InfoFile;
import com.servlet.vendor.entity.ListVendorData;
import com.servlet.vendor.entity.ParamVendor;
import com.servlet.vendor.service.VendorService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepositHandler implements DepositService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepositRepo repo;

    @Autowired
    private PurchaseReceiveService purchaseReceiveService;
    @Autowired
    private VendorService vendorService;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private RunningNumberService runningNumberService;

    @Autowired
    private FileDocumentService fileDocumentService;
    @Autowired
    private FileStorageService fileStorageService;

    protected final String namaMenu = "Deposit";

    @Override
    public List<DepositDataNotJoin> getDepositNotJoinByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        return null;
    }

    @Override
    public List<DepositList> getList(Long idcompany, Long idbranch, ParamList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListData().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.depositdate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.depositdate <= '"+dt.toString()+"'");
        }

        if(param.getIdvendor() != null){
            sqlBuilder.append(" and data.idvendor = "+param.getIdvendor().longValue()+" ");
        }
        if(param.getIsactive() != null && !param.getIsactive().equals("")){
            if(param.getIsactive().equals("Y")){
                sqlBuilder.append(" and data.isactive = true ");
            }else{
                sqlBuilder.append(" and data.isactive = false ");
            }

        }
        sqlBuilder.append(" order by  data.depositdate desc ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListData(), queryParameters);
    }

    @Override
    public DepositDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDetailData().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<DepositDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDetailData(), queryParameters);
        if(list != null && list.size() > 0){
            DepositDetail data = list.get(0);
            FileDocumentData file  =fileDocumentService.getDetail(data.getId(),namaMenu,idcompany,idbranch);
            if(file != null){
                data.setFileId(file.getId());
                data.setFileName(file.getFilename());
            }
            return data;
        }
        return null;
    }

    @Override
    public Double calculateAmountByIdVendor(Long idcompany, Long idbranch, Long idvendor) {
        Long idven = vendorService.getIdParent(idcompany,idbranch,idvendor);
        if(idven == null){
            idven = idvendor;
        }else if(idven == 0){
            idven = idvendor;
        }
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany,idven};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountDeposit(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public Double calculateSisaDepositByIdVendor(Long idcompany, Long idbranch, Long idvendor) {

        double summaryDeposit = calculateAmountByIdVendor(idcompany,idbranch,idvendor).doubleValue();
        Long idven = vendorService.getIdParent(idcompany,idbranch,idvendor);
        if(idven == null){
            idven = idvendor;
        }else if(idven == 0){
            idven = idvendor;
        }
        List<Long> listidven = vendorService.getListSubIdParent(idcompany,idbranch,idven);
        //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet, hanya sub nya saja
        listidven.add(idven);
        String listidvendor = "";
        if(listidven != null && listidven.size() > 0){
            listidvendor = listidven.toString().replaceAll("\\[","");
            listidvendor = listidvendor.replaceAll("\\]","");
        }


        double summarySetorPurchaseReceive =  0;//purchaseReceiveService.calculateSetorByIdVendor(idcompany,idbranch,idvendor).doubleValue();
        if(!listidvendor.equals("")){
            summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendor(idcompany,idbranch,null,listidvendor).doubleValue();
        }else{
            summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendor(idcompany,idbranch,idvendor,"").doubleValue();
        }
        double hasil = summaryDeposit - summarySetorPurchaseReceive;
        return hasil;
    }

    private Double calculateAmountByIdVendorNotInIDDeposit(Long id,Long idcompany, Long idbranch, Long idvendor) {
        Long idven = vendorService.getIdParent(idcompany,idbranch,idvendor);
        if(idven == null){
            idven = idvendor;
        }else if(idven == 0){
            idven = idvendor;
        }
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false and data.id not in ("+id+")");
        final Object[] queryParameters = new Object[] {idcompany,idven};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountDeposit(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDeposit body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());

        if(validations.size() == 0) {
            ListVendorData ven = vendorService.checkVendorIsParent(idcompany,idbranch, body.getIdvendor());
            if(ven == null){
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_PARENT, "Vendor Bukan Parent");
                validations.add(msg);
            }
        }

        String docNumber = "";
        if(validations.size() == 0) {
            docNumber = runningNumberService.getDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DEPOSIT, ts);
            if(docNumber.equals("")) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
                validations.add(msg);
            }
        }


        if(validations.size() == 0) {
            try {
                Deposit table = new Deposit();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument(docNumber);
                table.setIdvendor(body.getIdvendor());
                table.setAmount(body.getAmount());
                table.setDepositdate(new Date(body.getDepositdate()));
                table.setIsactive(true);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = repo.saveAndFlush(table).getId();

                String data = table.toString();
                historyAppsService.saveHistory(idcompany, idbranch, iduser, "ADD", namaMenu, data, "", "", ts);
            } catch (Exception e) {
                runningNumberService.rollBackDocNumber(idcompany, idbranch, ConstantCodeDocument.DOC_DEPOSIT);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyDeposit body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        Deposit table = repo.getById(id);
        if(!table.getIsactive()){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STATUS_DEPOSIT_NON_ACTIVE,"Status Deposit Non Active");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try {
                double summaryDeposit = calculateAmountByIdVendorNotInIDDeposit(id, idcompany, idbranch, table.getIdvendor()).doubleValue() + body.getAmount().doubleValue();
                List<Long> listidven = vendorService.getListSubIdParent(idcompany, idbranch, table.getIdvendor());
                //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet, hanya sub nya saja
                listidven.add(table.getIdvendor());
                String listidvendor = "";
                if (listidven != null && listidven.size() > 0) {
                    listidvendor = listidven.toString().replaceAll("\\[", "");
                    listidvendor = listidvendor.replaceAll("\\]", "");
                }

                double summarySetorPurchaseReceive = purchaseReceiveService.calculateSetorByIdVendor(idcompany, idbranch, null, listidvendor).doubleValue();
                if (summarySetorPurchaseReceive > summaryDeposit) {
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.TOTAL_SETOR_GREATER_THAN, "Total Setor Lebih besar dari total deposit");
                    validations.add(msg);
                }
                if (validations.size() == 0) {
                    PurchaseReceiveDataList check = purchaseReceiveService.checkIdDeposit(id);
                    if (check != null) {
                        ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PURCHASERECEIVE, "deposit ini terpasang pada purchase receive (" + check.getNodocument() + ") ");
                        validations.add(msg);
                    }
                }
                if (validations.size() == 0) {
                    ListVendorData ven = vendorService.checkVendorIsParent(idcompany, idbranch, body.getIdvendor());
                    if (ven == null) {
                        ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_PARENT, "Vendor Bukan Parent");
                        validations.add(msg);
                    }
                }
                if (validations.size() == 0 && !table.isIsdelete()) {
                    String dataBefore = table.toString();
                    table.setDepositdate(new Date(body.getDepositdate()));
                    table.setAmount(body.getAmount());
                    table.setModifieddate(ts);
                    table.setModifiedby(iduser);
                    idsave = repo.saveAndFlush(table).getId();

                    String data = table.toString();
                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "EDIT", namaMenu, "", data, dataBefore, ts);
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
        Deposit table = repo.getById(id);
        if(!table.getIsactive()){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STATUS_DEPOSIT_NON_ACTIVE,"Status Deposit Non Active");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try {
                double summaryDeposit = calculateAmountByIdVendorNotInIDDeposit(id, idcompany, idbranch, table.getIdvendor()).doubleValue();
                List<Long> listidven = vendorService.getListSubIdParent(idcompany, idbranch, table.getIdvendor());
                //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet, hanya sub nya saja
                listidven.add(table.getIdvendor());
                String listidvendor = "";
                if (listidven != null && listidven.size() > 0) {
                    listidvendor = listidven.toString().replaceAll("\\[", "");
                    listidvendor = listidvendor.replaceAll("\\]", "");
                }

                double summarySetorPurchaseReceive = purchaseReceiveService.calculateSetorByIdVendor(idcompany, idbranch, null, listidvendor).doubleValue();
                if (summarySetorPurchaseReceive > summaryDeposit) {
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.TOTAL_SETOR_GREATER_THAN, "Total Setor Lebih besar dari total deposit");
                    validations.add(msg);
                }

                if (validations.size() == 0) {
                    PurchaseReceiveDataList check = purchaseReceiveService.checkIdDeposit(id);
                    if (check != null) {
                        ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PURCHASERECEIVE, "deposit ini terpasang pada purchase receive (" + check.getNodocument() + ") ");
                        validations.add(msg);
                    }
                }

                if (validations.size() == 0) {
                    table.setIsdelete(true);
                    table.setDeletedate(ts);
                    table.setDeleteby(iduser);
                    idsave = repo.saveAndFlush(table).getId();

                    String data = table.toString();
                    historyAppsService.saveHistory(idcompany, idbranch, iduser, "DELETE", namaMenu, data, "", "", ts);
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
    public DepositTemplate getTemplate(Long idcompany, Long idbranch) {
        DepositTemplate template = new DepositTemplate();
        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setOnlyParent("Y");
        template.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch,paramVendor));
        return template;
    }

    @Override
    public ReturnData deleteRollBack(Long id) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        try {
            repo.deleteById(id);
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
    public Double calculateSaldoDepositByIdVendorAndBeforeDateCreated(Long idcompany, Long idbranch, Long idvendor, Long date) {
        double summaryDeposit = summaryCalculateSaldoDepositByIdVendorAndBeforeDateCreated(idcompany,idbranch,idvendor,date).doubleValue();
        Long idven = vendorService.getIdParent(idcompany,idbranch,idvendor);
        if(idven == null){
            idven = idvendor;
        }else if(idven == 0){
            idven = idvendor;
        }
        List<Long> listidven = vendorService.getListSubIdParent(idcompany,idbranch,idven);
        //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet, hanya sub nya saja
        listidven.add(idven);
        String listidvendor = "";
        if(listidven != null && listidven.size() > 0){
            listidvendor = listidven.toString().replaceAll("\\[","");
            listidvendor = listidvendor.replaceAll("\\]","");
        }
        double summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendorAndCreatedDate(idcompany,idbranch,null,date,listidvendor).doubleValue();
        double hasil = summaryDeposit - summarySetorPurchaseReceive;
        return hasil;
    }

    @Override
    public Double calculateSaldoDepositByIdVendorAndBeforeDate(Long idcompany, Long idbranch, Long idvendor, Long date) {
        double summaryDeposit = summaryCalculateSaldoDepositByIdVendorAndBeforeDate(idcompany,idbranch,idvendor,date).doubleValue();

        Long idven = vendorService.getIdParent(idcompany,idbranch,idvendor);
        if(idven == null){
            idven = idvendor;
        }else if(idven == 0){
            idven = idvendor;
        }
        List<Long> listidven = vendorService.getListSubIdParent(idcompany,idbranch,idven);
        //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet, hanya sub nya saja
        listidven.add(idven);
        String listidvendor = "";
        if(listidven != null && listidven.size() > 0){
            listidvendor = listidven.toString().replaceAll("\\[","");
            listidvendor = listidvendor.replaceAll("\\]","");
        }

        double summarySetorPurchaseReceive =  purchaseReceiveService.calculateSetorByIdVendorAndDate(idcompany,idbranch,null,date,listidvendor).doubleValue();
        double hasil = summaryDeposit - summarySetorPurchaseReceive;
        return hasil;
    }

    @Override
    public List<ReportKartuDeposit> getListReportKartuDeposit(Long idcompany, Long idbranch, ParamList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryReportKartuDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.depositdate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.depositdate <= '"+dt.toString()+"'");
        }

        if(param.getListIdVendor() != null && !param.getListIdVendor().equals("")){
            sqlBuilder.append(" and data.idvendor in ("+param.getListIdVendor()+") ");
        }
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryReportKartuDeposit(), queryParameters);
    }

    @Override
    public ReturnData uploadFileDoc(Long id, MultipartFile file, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                byte[] fileencode = Base64.encodeBase64(file.getBytes());
                String result = new String(fileencode);
                InfoFile infofile = fileStorageService.getInfoFile(file);
                String fileName = infofile.getNamaFile();//fileStorageService.storeFile(file);
                String contentType = infofile.getContectType();//fileStorageService.getContentType(file);

                if(contentType.equals("application/pdf") || contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")) {

                }else {
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_DOCUMENT_INCORRECT_FORMAT,"Hanya format PDF,JPG,PNG yang bisa di upload");
                    validations.add(msg);
                }
                if(validations.size() == 0) {
                    BodyFileDocument bodyFileDocument = new BodyFileDocument();
                    bodyFileDocument.setIddata(id);
                    bodyFileDocument.setMenu(namaMenu);
                    bodyFileDocument.setFilename(fileName);
                    bodyFileDocument.setFiledocument(result);
                    bodyFileDocument.setFilecontenttype(contentType);
                    ReturnData data = fileDocumentService.uploadDoc(idcompany,idbranch,iduser,ts,bodyFileDocument);
                    idsave = data.getId();
                    if(data.getValidations().size() > 0){
                        validations.add(data.getValidations().get(0));
                    }
                }
            }catch (Exception e) {
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
    public FileDocumentData downloadFile(Long id, Long idcompany, Long idbranch) {
        return fileDocumentService.getDetail(id,namaMenu,idcompany,idbranch);
    }

    @Override
    public List<DepositDataNotJoin> getListDepositActive(Long idcompany, Long idbranch, ParamList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDataNotJoin().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.depositdate >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.depositdate <= '"+dt.toString()+"'");
        }

        if(param.getIdvendor() != null){
            sqlBuilder.append(" and data.idvendor = "+param.getIdvendor().longValue()+" ");
        }
        sqlBuilder.append(" and data.isactive = true ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDataNotJoin(), queryParameters);
    }

    @Override
    public ReturnData updateStatusDeposit(Long id, Long idcompany, Long idbranch, Long iduser, Boolean status) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        Deposit table = repo.getById(id);
            try {
                if (validations.size() == 0) {
                    table.setIsactive(status);
                    idsave = repo.saveAndFlush(table).getId();
                }
            } catch (Exception e) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR, "Kesalahan Pada Server");
                validations.add(msg);
            }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    private Double summaryCalculateSaldoDepositByIdVendorAndBeforeDateCreated(Long idcompany, Long idbranch, Long idvendor, Long date){
        Long idven = vendorService.getIdParent(idcompany,idbranch,idvendor);
        if(idven == null){
            idven = idvendor;
        }else if(idven == 0){
            idven = idvendor;
        }
        Timestamp dt = new Timestamp(date);
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false and data.createddate < '"+dt+"' ");
        final Object[] queryParameters = new Object[] {idcompany,idven};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountDeposit(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    private Double summaryCalculateSaldoDepositByIdVendorAndBeforeDate(Long idcompany, Long idbranch, Long idvendor, Long date){
        Long idven = vendorService.getIdParent(idcompany,idbranch,idvendor);
        if(idven == null){
            idven = idvendor;
        }else if(idven == 0){
            idven = idvendor;
        }
        Date dt = new Date(date);
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountDeposit().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false and data.depositdate < '"+dt+"' ");
        final Object[] queryParameters = new Object[] {idcompany,idven};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountDeposit(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }
}
