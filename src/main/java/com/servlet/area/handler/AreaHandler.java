package com.servlet.area.handler;

import com.servlet.area.entity.Area;
import com.servlet.area.entity.AreaDetail;
import com.servlet.area.entity.AreaList;
import com.servlet.area.entity.BodyArea;
import com.servlet.area.mapper.QueryDetailData;
import com.servlet.area.mapper.QueryListData;
import com.servlet.area.repo.AreaRepo;
import com.servlet.area.service.AreaService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AreaHandler implements AreaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AreaRepo repo;

    @Autowired
    private HistoryAppsService historyAppsService;
    protected final String namaMenu = "Area";

    @Override
    public List<AreaList> getList(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryListData().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryListData(), queryParameters);
    }

    @Override
    public AreaDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryDetailData().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ? and data.isdelete = false  ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<AreaDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryDetailData(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }

        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyArea body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            Area table = new Area();
            table.setIdcompany(idcompany);
            table.setIdbranch(idbranch);
            table.setNama(body.getNama());
            table.setAlias(body.getAlias());
            table.setCreateddate(ts);
            table.setCreatedby(iduser);
            idsave = repo.saveAndFlush(table).getId();

            String data = table.toString();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,data,"","",ts);
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
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyArea body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            Area table = repo.getById(id);
            String before = table.toString();

            table.setIdcompany(idcompany);
            table.setIdbranch(idbranch);
            table.setNama(body.getNama());
            table.setAlias(body.getAlias());
            table.setModifieddate(ts);
            table.setModifiedby(iduser);
            idsave = repo.saveAndFlush(table).getId();

            String data = table.toString();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",data,before,ts);
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
    public ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        try{
            Area table = repo.getById(id);
            table.setIsdelete(true);
            table.setDeletedate(ts);
            table.setDeleteby(iduser);
            idsave = repo.saveAndFlush(table).getId();

            String data = table.toString();
            historyAppsService.saveHistory(idcompany,idbranch,iduser,"DELETE",namaMenu,data,"","",ts);
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
}
