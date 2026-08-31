package com.servlet.pinjaman.handler;

import com.servlet.common.entity.PagingData;
import com.servlet.filedocument.entity.BodyFileDocument;
import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.filedocument.service.FileDocumentService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.journal.entity.PostingJournalParam;
import com.servlet.journal.entity.SourceTypeEnum;
import com.servlet.journal.service.JournalService;
import com.servlet.pinjaman.entity.*;
import com.servlet.pinjaman.mapper.*;
import com.servlet.pinjaman.repo.PinjamanRepo;
import com.servlet.pinjaman.service.PinjamanService;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PinjamanHandler implements PinjamanService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PinjamanRepo repo;

    @Autowired
    private FileDocumentService fileDocumentService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private VendorService vendorService;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private PurchaseReceiveService purchaseReceiveService;

    @Autowired
    private RunningNumberService runningNumberService;

    @Autowired
    private JournalService journalService;

    protected final String namaMenu = "Pinjaman";

    @Override
    public List<PinjamanList> getList(ParameterPinjaman param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new PinjamanQueryListData().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        if(param.getParameterList().getFrom() != null){
            Date dt = new Date(param.getParameterList().getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getParameterList().getTo() != null){
            Date dt = new Date(param.getParameterList().getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }

        if(param.getParameterList().getIdvendor() != null){
            sqlBuilder.append(" and data.idvendor = "+param.getParameterList().getIdvendor().longValue()+" ");
        }
//        if(param.getIsactive() != null && !param.getIsactive().equals("")){
//            if(param.getIsactive().equals("Y")){
//                sqlBuilder.append(" and data.isactive = true ");
//            }else{
//                sqlBuilder.append(" and data.isactive = false ");
//            }
//
//        }
        sqlBuilder.append(" order by  data.date desc ");
        final Object[] queryParameters = new Object[] {param.getIdcompany()};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new PinjamanQueryListData(), queryParameters);
    }

    @Override
    public PinjamanDetail getDetail(ParameterPinjaman param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new PinjamanQueryDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {param.getId(),param.getIdcompany()};
        List<PinjamanDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new PinjamanQueryDetail(), queryParameters);
        if(list != null && list.size() > 0){
            PinjamanDetail data = list.get(0);
            FileDocumentData file  =fileDocumentService.getDetail(data.getId(),namaMenu,param.getIdcompany(),param.getIdbranch());
            if(file != null){
                data.setFileId(file.getId());
                data.setFileName(file.getFilename());
            }
            return data;
        }
        return null;
    }

    @Override
    public ReturnData save(ParameterPinjaman param) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());

        if(validations.size() == 0) {
            ListVendorData venPinjaman = vendorService.checkVendorCanDepositOrPinjaman(param.getIdcompany(),param.getIdbranch(), param.getBody().getIdvendor(),"Y","N");
            if(venPinjaman == null){
                ListVendorData ven = vendorService.checkVendorIsParent(param.getIdcompany(),param.getIdbranch(), param.getBody().getIdvendor());
                if(ven == null){
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_PARENT, "Vendor Bukan Parent");
                    validations.add(msg);
                }
            }

        }

        String docNumber = "";
        if(validations.size() == 0) {
            docNumber = runningNumberService.getDocNumber(param.getIdcompany(), param.getIdbranch(), ConstantCodeDocument.DOC_PINJAMAN, ts);
            if(docNumber.equals("")) {
                ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_GENERATE_DOC_NUMBER,"Gagal Generate Document Number");
                validations.add(msg);
            }
        }
        if(validations.size() == 0) {
            try {
                Pinjaman table = new Pinjaman();
                table.setIdcompany(param.getIdcompany());
                table.setIdbranch(param.getIdbranch());
                table.setNodocument(docNumber);
                table.setIdvendor(param.getBody().getIdvendor());
                table.setAmount(param.getBody().getAmount());
                table.setCatatan(param.getBody().getCatatan());
                table.setDate(new Date(param.getBody().getDate()));
                table.setIsactive(true);
                table.setCreateddate(ts);
                table.setCreatedby(param.getIduser());
                idsave = repo.saveAndFlush(table).getId();

                PostingJournalParam paramPosting = new PostingJournalParam();
                paramPosting.setIdcompany(param.getIdcompany());
                paramPosting.setIdbranch(param.getIdbranch());
                paramPosting.setAmount(param.getBody().getAmount());
                paramPosting.setDescriptionDetail("");
                paramPosting.setIdvendor(param.getBody().getIdvendor());
                paramPosting.setSourcenumber(docNumber);
                paramPosting.setSourcedocumentdate(table.getDate());
                paramPosting.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
                paramPosting.setTransaksitime(ts);
                paramPosting.setDescription("");
                paramPosting.setCreatedby(param.getIduser());
                List<ValidationDataMessage> validationsPosting = journalService.postingJournal(paramPosting);
                if(validationsPosting.size() > 0){
                    runningNumberService.rollBackDocNumber(param.getIdcompany(), param.getIdbranch(), ConstantCodeDocument.DOC_PINJAMAN);
                    repo.deleteById(idsave);
                    validations.addAll(validationsPosting);
                }else{
                    String data = table.toString();
                    historyAppsService.saveHistory(param.getIdcompany(), param.getIdbranch(), param.getIduser(), "ADD", namaMenu, data, "", "", ts);
                }

            }catch (Exception e) {
                runningNumberService.rollBackDocNumber(param.getIdcompany(), param.getIdbranch(), ConstantCodeDocument.DOC_PINJAMAN);
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
    public ReturnData update(ParameterPinjaman param) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        Pinjaman table = repo.getById(param.getId());
        final Pinjaman befTable = table;
        if(!table.getIsactive()){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STATUS_DEPOSIT_NON_ACTIVE,"Status Deposit Non Active");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try {
                ParameterPinjaman paramPinjaman = param;//new ParameterPinjaman();
                paramPinjaman.setIdvendor(table.getIdvendor());

                double summaryPinjaman = calculateAmountByIdVendor(table.getIdcompany(), table.getIdbranch(), paramPinjaman).doubleValue();
                summaryPinjaman = summaryPinjaman - table.getAmount();
                summaryPinjaman = summaryPinjaman + param.getBody().getAmount();

                List<Long> listidven = vendorService.getListSubIdParent(table.getIdcompany(), table.getIdbranch(), table.getIdvendor(),"Y","N");
//                //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet, hanya sub nya saja
                listidven.add(table.getIdvendor());
                String listidvendor = "";
                if (listidven != null && listidven.size() > 0) {
                    listidvendor = listidven.toString().replaceAll("\\[", "");
                    listidvendor = listidvendor.replaceAll("\\]", "");
                }

                double summarySetorPurchaseReceive = purchaseReceiveService.calculateSetorPinjamanByIdVendor(table.getIdcompany(), table.getIdbranch(), null, listidvendor,null).doubleValue();
                if(summarySetorPurchaseReceive > 0) {
                    if (summarySetorPurchaseReceive > summaryPinjaman) {
                        ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.TOTAL_SETOR_GREATER_THAN, "Total Pinjaman PRC Lebih besar dari total pinjaman");
                        validations.add(msg);
                    }
                }
                if (validations.size() == 0) {
                    ListVendorData venPinjaman = vendorService.checkVendorCanDepositOrPinjaman(param.getIdcompany(),param.getIdbranch(), param.getBody().getIdvendor(),"Y","N");
                    if(venPinjaman == null) {
                        ListVendorData ven = vendorService.checkVendorIsParent(param.getIdcompany(), param.getIdbranch(), param.getBody().getIdvendor());
                        if (ven == null) {
                            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VENDOR_NOT_PARENT, "Vendor Bukan Parent");
                            validations.add(msg);
                        }
                    }
                }
                if (validations.size() == 0 && !table.isIsdelete()) {
                    String dataBefore = table.toString();
                    table.setDate(new Date(param.getBody().getDate()));
                    table.setAmount(param.getBody().getAmount());
                    table.setCatatan(param.getBody().getCatatan());
                    table.setModifieddate(ts);
                    table.setModifiedby(param.getIduser());
                    idsave = repo.saveAndFlush(table).getId();

                    PostingJournalParam paramPosting = new PostingJournalParam();
                    paramPosting.setIdcompany(table.getIdcompany());
                    paramPosting.setIdbranch(table.getIdbranch());
                    paramPosting.setAmount(param.getBody().getAmount());
                    paramPosting.setIdvendor(table.getIdvendor());
                    paramPosting.setDescriptionDetail("");
                    paramPosting.setSourcenumber(table.getNodocument());
                    paramPosting.setSourcedocumentdate(table.getDate());
                    paramPosting.setSourcetype(SourceTypeEnum.TOPUP_PINJAMAN.getSourceType());
                    paramPosting.setTransaksitime(table.getCreateddate());
                    paramPosting.setDescription("");
                    paramPosting.setCreatedby(param.getIduser());

                    List<ValidationDataMessage> validationsPosting = journalService.updateJournalDetail(paramPosting);
                    if(validationsPosting.size() > 0){
                        repo.saveAndFlush(befTable);
                        validations.addAll(validationsPosting);
                    }else{
                        String data = table.toString();
                        historyAppsService.saveHistory(param.getIdcompany(), param.getIdbranch(), param.getIduser(), "EDIT", namaMenu, "", data, dataBefore, ts);
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
    public ReturnData delete(ParameterPinjaman param) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        Pinjaman table = repo.getById(param.getId());
        if(!table.getIsactive()){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.STATUS_DEPOSIT_NON_ACTIVE,"Status Deposit Non Active");
            validations.add(msg);
        }

        if(validations.size() == 0) {
            try {
                ParameterPinjaman paramPinjaman = new ParameterPinjaman();
                paramPinjaman.setIdvendor(table.getIdvendor());
                double summaryPinjaman = calculateAmountByIdVendor(table.getIdcompany(), table.getIdbranch(), paramPinjaman).doubleValue();
                summaryPinjaman = summaryPinjaman - table.getAmount();

                List<Long> listidven = vendorService.getListSubIdParent(table.getIdcompany(), table.getIdbranch(), table.getIdvendor(),"Y","N");
//                //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet, hanya sub nya saja
                listidven.add(table.getIdvendor());
                String listidvendor = "";
                if (listidven != null && listidven.size() > 0) {
                    listidvendor = listidven.toString().replaceAll("\\[", "");
                    listidvendor = listidvendor.replaceAll("\\]", "");
                }
//
                double summarySetorPurchaseReceive = purchaseReceiveService.calculateSetorPinjamanByIdVendor(table.getIdcompany(), table.getIdbranch(), null, listidvendor,null).doubleValue();
                if (summarySetorPurchaseReceive > summaryPinjaman) {
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.TOTAL_SETOR_GREATER_THAN, "Total Setor Lebih besar dari total pinjaman");
                    validations.add(msg);
                }
                if (validations.size() == 0) {
                    table.setIsdelete(true);
                    table.setDeletedate(ts);
                    table.setDeleteby(param.getIduser());
                    idsave = repo.saveAndFlush(table).getId();

                    journalService.deleteJournalBySourceNumber(table.getNodocument());

                    String data = table.toString();
                    historyAppsService.saveHistory(param.getIdcompany(), param.getIdbranch(), param.getIduser(), "DELETE", namaMenu, data, "", "", ts);
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
    public PinjamanTemplate getTemplate(ParameterPinjaman param) {
        PinjamanTemplate template = new PinjamanTemplate();
        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setOnlyParent("Y");
        paramVendor.setForPinjaman("Y");
        template.setVendorOpt(vendorService.getListDropdown(param.getIdcompany(), param.getIdbranch(),paramVendor));
        return template;
    }

    @Override
    public ReturnData uploadFileDoc(ParameterPinjaman param) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                byte[] fileencode = Base64.encodeBase64(param.getFile().getBytes());
                String result = new String(fileencode);
                InfoFile infofile = fileStorageService.getInfoFile(param.getFile());
                String fileName = infofile.getNamaFile();//fileStorageService.storeFile(file);
                String contentType = infofile.getContectType();//fileStorageService.getContentType(file);

                if(contentType.equals("application/pdf") || contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")) {

                }else {
                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_DOCUMENT_INCORRECT_FORMAT,"Hanya format PDF,JPG,PNG yang bisa di upload");
                    validations.add(msg);
                }
                if(validations.size() == 0) {
                    BodyFileDocument bodyFileDocument = new BodyFileDocument();
                    bodyFileDocument.setIddata(param.getId());
                    bodyFileDocument.setMenu(namaMenu);
                    bodyFileDocument.setFilename(fileName);
                    bodyFileDocument.setFiledocument(result);
                    bodyFileDocument.setFilecontenttype(contentType);
                    ReturnData data = fileDocumentService.uploadDoc(param.getIdcompany(), param.getIdbranch(), param.getIduser(), ts,bodyFileDocument);
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
    public FileDocumentData downloadFile(ParameterPinjaman param) {
        return fileDocumentService.getDetail(param.getId(), namaMenu, param.getIdcompany(), param.getIdbranch());
    }

    @Override
    public Double calculateAmountByIdVendor(Long idcompany, Long idbranch, ParameterPinjaman param) {
        Long idvendor = param.getIdvendor();
        Long idven = idvendor;
        ListVendorData venPinjaman = vendorService.checkVendorCanDepositOrPinjaman(idcompany,idbranch, param.getBody().getIdvendor(),"Y","N");
        if(venPinjaman == null) {
            idven = vendorService.getIdParent(idcompany, idbranch, idvendor);
            if (idven == null) {
                idven = idvendor;
            } else if (idven == 0) {
                idven = idvendor;
            }
        }
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculateAmountPinjaman().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idvendor = ?  and data.isdelete = false ");
        if(param.getDate() != null && param.getOperatorPerbandingan() != null){
//            and data.createddate < '"+dt+"'
            sqlBuilder.append(" and data.date "+param.getOperatorPerbandingan()+" '"+param.getDate()+"' ");
        }
        final Object[] queryParameters = new Object[] {idcompany,idven};
        List<Double> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculateAmountPinjaman(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0.0;
    }

    @Override
    public Double calculateSisaPinjamanByIdVendor(Long idcompany, Long idbranch, Long idvendor, Date dateFrom) {
        ParameterPinjaman parameterPinjaman = new ParameterPinjaman();
        parameterPinjaman.setIdvendor(idvendor);
        parameterPinjaman.setDate(dateFrom);
        parameterPinjaman.setOperatorPerbandingan("<");
        double summaryAmount = calculateAmountByIdVendor(idcompany,idbranch,parameterPinjaman).doubleValue();
        Long idven = idvendor;
        ListVendorData venPinjaman = vendorService.checkVendorCanDepositOrPinjaman(idcompany,idbranch, idvendor,"Y","N");
        if(venPinjaman == null) {
            idven = vendorService.getIdParent(idcompany, idbranch, idvendor);
            if (idven == null) {
                idven = idvendor;
            } else if (idven == 0) {
                idven = idvendor;
            }
        }
        List<Long> listidven = vendorService.getListSubIdParent(idcompany,idbranch,idven,"Y","N");
        //kenapa di add, karena di anggap ini idparent, jika query diatas ga dapet hanya sub nya saja
        listidven.add(idven);
        String listidvendor = "";
        if(listidven != null && listidven.size() > 0){
            listidvendor = listidven.toString().replaceAll("\\[","");
            listidvendor = listidvendor.replaceAll("\\]","");
        }

        double summarySetorPinjamanPurchaseReceive =  0;//purchaseReceiveService.calculateSetorByIdVendor(idcompany,idbranch,idvendor).doubleValue();
        if(!listidvendor.equals("")){
            summarySetorPinjamanPurchaseReceive =  purchaseReceiveService.calculateSetorPinjamanByIdVendor(idcompany,idbranch,null,listidvendor,dateFrom).doubleValue();
        }else{
            summarySetorPinjamanPurchaseReceive =  purchaseReceiveService.calculateSetorPinjamanByIdVendor(idcompany,idbranch,idvendor,"",dateFrom).doubleValue();
        }
        double hasil = summaryAmount - summarySetorPinjamanPurchaseReceive;
        return hasil;

    }

    @Override
    public List<ReportKartuPinjaman> getListReportKartuPinjaman(Long idcompany, Long idbranch, ParamReportKartuPinjamanList param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryReportKartuPinjaman().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }

        if(param.getListIdVendor() != null && !param.getListIdVendor().equals("")){
            sqlBuilder.append(" and data.idvendor in ("+param.getListIdVendor()+") ");
        }
        if(param.getTransaksiTime() != null){
            sqlBuilder.append(" and data.createddate <= '"+param.getTransaksiTime().toString()+"'");
        }
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryReportKartuPinjaman(), queryParameters);
    }

    @Override
    public PagingData getListVendorSisaPinjaman(Long idcompany, Long idbranch, Integer Limit, Integer Offset, String search) {
        final StringBuilder sqlBuilder = new StringBuilder(new QueryVendorSisaPinjaman().schema());
//        sqlBuilder.append(" where v.idcompany = ? and v.idbranch = ? and v.isdelete = false and v.type = 'UDANG' and COALESCE(j.balance,0) > 0  ");
        sqlBuilder.append(" where v.idcompany = ? and v.idbranch = ? and v.isdelete = false and v.type = 'UDANG' and COALESCE(j.balancePinjaman, 0) - COALESCE(jpr.balancePembayaranPinjaman, 0) > 0  ");
        if(!search.equals("")){
            sqlBuilder.append(" and ( LOWER(v.nama) LIKE LOWER(CONCAT('%' ,'"+search+"', '%')) or LOWER(v.alias) LIKE LOWER(CONCAT('%' ,'"+search+"', '%')) )");
        }
//        String queryTotal = sqlBuilder.toString();

//        sqlBuilder.append(" ORDER BY v.nama ");
        sqlBuilder.append(" LIMIT "+Limit+" OFFSET "+Offset+" ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};

        List<VendorSisaPinjaman> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryVendorSisaPinjaman(), queryParameters);

        final StringBuilder sqlBuilderTotalData = new StringBuilder(new QueryTotalDataVendorSisaPinjaman().schema());
        sqlBuilderTotalData.append(" where v.idcompany = ? and v.idbranch = ? and v.isdelete = false and v.type = 'UDANG' ");
        if(!search.equals("")){
            sqlBuilderTotalData.append(" and ( LOWER(v.nama) LIKE LOWER(CONCAT('%' ,'"+search+"', '%')) or LOWER(v.alias) LIKE LOWER(CONCAT('%' ,'"+search+"', '%')) )");
        }
//        sqlBuilderTotalData.append(queryTotal);
        List<Long> listTotal = this.jdbcTemplate.query(sqlBuilderTotalData.toString(), new QueryTotalDataVendorSisaPinjaman(), queryParameters);
        Long totalElements = 0L;
        if(listTotal != null && listTotal.size() > 0){
            totalElements = listTotal.get(0);
        }
        PagingData paging = new PagingData();
        paging.setPage(Offset);
        paging.setSize(Limit);
        paging.setTotalElements(totalElements);
        paging.setData(list);
        return paging;
    }

    @Override
    public Boolean checkVendorAdaTransaksiPinjaman(Long idcompany, Long idbranch, Long idvendor) {
        final StringBuilder sqlBuilder = new StringBuilder(
                "select exists (select 1 from pinjaman data " +
                        " where data.idcompany = ? and data.idbranch = ? and data.idvendor = ? and data.isdelete = false "
        );

        sqlBuilder.append(") as ada_transaksi");

        final Object[] queryParameters = new Object[] {idcompany, idbranch,idvendor};

        Boolean result = this.jdbcTemplate.queryForObject(sqlBuilder.toString(), Boolean.class, queryParameters);
        return Boolean.TRUE.equals(result);
    }
}
