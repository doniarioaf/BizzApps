package com.servlet.cargo.handler;

import com.servlet.cargo.entity.*;
import com.servlet.cargo.mapper.*;
import com.servlet.cargo.repo.CargoRepo;
import com.servlet.cargo.service.CargoService;
import com.servlet.filedocument.entity.BodyFileDocument;
import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.filedocument.service.FileDocumentService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.pelunasanhutang.entity.PelunasanHutangDataNotJoin;
import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;
import com.servlet.pelunasanhutang.service.PelunasanHutangService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.upload.image.FileStorageService;
import com.servlet.upload.image.InfoFile;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CargoHandler implements CargoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CargoRepo repo;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    private PelunasanHutangService pelunasanHutangService;

    @Autowired
    private FileDocumentService fileDocumentService;
    @Autowired
    private FileStorageService fileStorageService;

    protected final String namaMenu = "Cargo";

    @Override
    public List<CargoDataList> getList(Long idcompany, Long idbranch, ParamCargoSearch param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCargoList().schema());
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
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCargoList(), queryParameters);
    }

    @Override
    public CargoTemplate getTemplate(Long idcompany, Long idbranch) {
        CargoTemplate data = new CargoTemplate();
        data.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch));
        return data;
    }

    @Override
    public CargoDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCargoDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");

        final Object[] queryParameters = new Object[] {id,idcompany,idbranch};
        List<CargoDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCargoDetail(), queryParameters);
        if(list != null && list.size() > 0){
            CargoDetail data = list.get(0);
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
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyCargo body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                Cargo table = new Cargo();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setDate(new Date(body.getDate()));
                table.setIdvendor(body.getIdvendor());
                table.setInvoicenumber(body.getInvoicenumber());
                table.setSmunumber(body.getSmunumber());
                table.setAwbnumber(body.getAwbnumber());
                table.setKoli(body.getKoli());
                table.setGrossamount(body.getGrossamount());
                table.setPpnamount(body.getPpnamount());
                table.setPpn23amount(body.getPpn23amount());
                table.setNetamount(body.getNetamount());
                table.setOutstanding(body.getNetamount());
                table.setIsdelete(false);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = repo.saveAndFlush(table).getId();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,table.toString(),"","",ts);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyCargo body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        List<PelunasanHutangDataNotJoin> list = pelunasanHutangService.getDataByIdCargo(idcompany,idbranch,id);
        if(list != null && list.size() > 0){
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.THIS_ID_ALREADY_INSTALLED_PELUNASANHUTANG,"document ini terpasang pada pelunasan hutang ("+list.get(0).getNodocument()+")");
            validations.add(msg);
        }
        if(validations.size() == 0) {
            try{
                Cargo table = repo.getById(id);
                String before = table.toString();

                table.setDate(new Date(body.getDate()));
                table.setInvoicenumber(body.getInvoicenumber());
                table.setSmunumber(body.getSmunumber());
                table.setAwbnumber(body.getAwbnumber());
                table.setKoli(body.getKoli());
                table.setGrossamount(body.getGrossamount());
                table.setPpnamount(body.getPpnamount());
                table.setPpn23amount(body.getPpn23amount());
                table.setNetamount(body.getNetamount());
                table.setOutstanding(body.getNetamount());
                table.setModifieddate(ts);
                table.setModifiedby(iduser);
                idsave = repo.saveAndFlush(table).getId();
                String after = table.toString();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",after,before,ts);
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
    public ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                Cargo table = repo.getById(id);
                table.setIsdelete(true);
                table.setDeletedate(ts);
                table.setDeleteby(iduser);
                idsave = repo.saveAndFlush(table).getId();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE",namaMenu,table.toString(),"","",ts);
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

                /** validasi Size file **/
//                double sizeInKb = infofile.getSizeFile().longValue() / 1024;
//                double sizeInMb =  sizeInKb / 1024;
//                List<ParameterData> arrmaxSize = parameterService.getListParameterByGrup("MAX_SIZE_DOCUMENT_IN_MB");
//                if(arrmaxSize != null && arrmaxSize.size() > 0) {
//                    double maxSize = new Double(arrmaxSize.get(0).getCode()).doubleValue();
//                    if(sizeInMb > maxSize) {
//                        ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_DOCUMENT_MAX_SIZE_OVER_LIMIT,"Ukuran Melebihi Batas, Max= "+maxSize+" MB");
//                        validations.add(msg);
//                    }
//                }

                /** validasi Type file **/
//                if(contentType.equals("application/pdf") || contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")) {
//
//                }else {
//                    ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.VALIDASI_DOCUMENT_INCORRECT_FORMAT,"Format Tidak Sesuai");
//                    validations.add(msg);
//                }
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
    public ReturnData updateOustandingTambah(Long id, Double bayar) {

        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                Cargo table = repo.getById(id);
                double outstanding = table.getOutstanding().doubleValue() + bayar.doubleValue();
                table.setOutstanding(outstanding);
                idsave = repo.saveAndFlush(table).getId();
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
    public ReturnData updateOustandingKurang(Long id, Double bayar) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                Cargo table = repo.getById(id);
                double outstanding = table.getOutstanding().doubleValue() - bayar.doubleValue();
                table.setOutstanding(outstanding);
                idsave = repo.saveAndFlush(table).getId();
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
    public List<CargoDataNotJoin> getListCargoPelunasanHutang(Long idcompany, Long idbranch, ParamCargoSearch param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCargoNotJoin().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        String selecetIdVendor ="";
        if(param.getCategory().equals("CARGO")){
            selecetIdVendor = " select ven.id from m_vendor as ven where ven.type = 'CARGO' and ven.idcompany = "+idcompany+" and ven.idbranch = "+idbranch+" and ven.isdelete = false ";
        }else if(param.getCategory().equals("UPI")){
            selecetIdVendor = " select ven.id from m_vendor as ven where ven.type = 'UPI' and ven.idcompany = "+idcompany+" and ven.idbranch = "+idbranch+" and ven.isdelete = false ";
        }
        if(!selecetIdVendor.equals("")){
            sqlBuilder.append(" and data.idvendor in ("+selecetIdVendor+")  ");
        }
        if(param.getStatus().equals("LUNAS")){
            sqlBuilder.append(" and data.outstanding < 1  ");
        }else if(param.getStatus().equals("BELUMLUNAS")){
            sqlBuilder.append(" and data.outstanding >= 1  ");
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCargoNotJoin(), queryParameters);
    }

    @Override
    public List<CargoDataReportStatusTagihanCargo> getListCargoReportStatusTagihanCargo(Long idcompany, Long idbranch, ParamCargoSearch param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCargoDataReportStatusTagihanCargo().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }

        if(param.getIdvendor() != null ){
            sqlBuilder.append(" and data.idvendor = "+param.getIdvendor()+"  ");
        }
        if(param.getStatus().equals("LUNAS")){
            sqlBuilder.append(" and data.outstanding < 1  ");
        }else if(param.getStatus().equals("BELUMLUNAS")){
            sqlBuilder.append(" and data.outstanding >= 1  ");
        }
        sqlBuilder.append(" order by data.date ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCargoDataReportStatusTagihanCargo(), queryParameters);
    }

    @Override
    public List<ReportPelunasanHutangDocumentHutang> getListCargoReportHutang(Long idcompany, Long idbranch, ParamCargoSearch param) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCargoReportHutang().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ? and data.isdelete = false  ");
        if(param.getFrom() != null){
            Date dt = new Date(param.getFrom());
            sqlBuilder.append(" and data.date >= '"+dt.toString()+"'");
        }
        if(param.getTo() != null){
            Date dt = new Date(param.getTo());
            sqlBuilder.append(" and data.date <= '"+dt.toString()+"'");
        }

        if(param.getIdvendor() != null ){
            sqlBuilder.append(" and data.idvendor = "+param.getIdvendor()+"  ");
        }
        if(param.getStatus().equals("LUNAS")){
            sqlBuilder.append(" and data.outstanding < 1  ");
        }else if(param.getStatus().equals("BELUMLUNAS")){
            sqlBuilder.append(" and data.outstanding >= 1  ");
        }
        if(param.getOrderBy() != null && !param.getOrderBy().equals("")){
            sqlBuilder.append(" order by data."+param.getOrderBy());
        }

        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCargoReportHutang(), queryParameters);
    }
}
