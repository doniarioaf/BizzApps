package com.servlet.cargo.handler;

import com.servlet.cargo.entity.*;
import com.servlet.cargo.mapper.QueryCargoDetail;
import com.servlet.cargo.mapper.QueryCargoList;
import com.servlet.cargo.repo.CargoRepo;
import com.servlet.cargo.service.CargoService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
            return list.get(0);
//            return data;
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
                table.setFile("");
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
}
