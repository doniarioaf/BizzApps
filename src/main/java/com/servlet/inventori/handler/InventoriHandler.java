package com.servlet.inventori.handler;

import com.servlet.inventori.entity.BodyInventori;
import com.servlet.inventori.entity.Inventori;
import com.servlet.inventori.entity.InventoriDataDetail;
import com.servlet.inventori.entity.ListInventoriData;
import com.servlet.inventori.mapper.QueryInventoriDetail;
import com.servlet.inventori.mapper.QueryInventoriList;
import com.servlet.inventori.repo.InventoriRepo;
import com.servlet.inventori.service.InventoriService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InventoriHandler implements InventoriService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InventoriRepo repo;

    /**
     * sengaja, semua cuma query by idcompany
     */


    @Override
    public List<ListInventoriData> getListAll(Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryInventoriList().schema());
        sqlBuilder.append(" where data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {idcompany};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryInventoriList(), queryParameters);
    }

    @Override
    public InventoriDataDetail getDetail(Long id, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryInventoriDetail().schema());
        sqlBuilder.append(" where data.id = ? and data.idcompany = ?  and data.isdelete = false ");
        final Object[] queryParameters = new Object[] {id,idcompany};
        List<InventoriDataDetail> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryInventoriDetail(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyInventori body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Inventori Inventori = new Inventori();
            Inventori.setIdcompany(idcompany);
            Inventori.setIdbranch(idbranch);
            Inventori.setNama(body.getNama());
            Inventori.setSku(body.getSku());
            Inventori.setIsdelete(false);
            Inventori.setCreateddate(ts);
            Inventori.setCreatedby(iduser);
            idsave = repo.saveAndFlush(Inventori).getId();
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyInventori body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Inventori Inventori = repo.getById(id);
            Inventori.setNama(body.getNama());
            Inventori.setSku(body.getSku());
            Inventori.setModifieddate(ts);
            Inventori.setModifiedby(iduser);
            idsave = repo.saveAndFlush(Inventori).getId();
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }

    @Override
    public ReturnData delete(Long id, Long iduser) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new Date().getTime());
        try{
            Inventori Inventori = repo.getById(id);
            Inventori.setIsdelete(true);
            Inventori.setDeletedate(ts);
            Inventori.setDeleteby(iduser);
            idsave = repo.saveAndFlush(Inventori).getId();
        }catch (Exception e){
            // TODO: handle exception
            ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR,"Kesalahan Pada Server");
            validations.add(msg);
        }
        ReturnData data = new ReturnData();
        data.setId(idsave);
        data.setSuccess(validations.size() > 0?false:true);
        data.setValidations(validations);
        return data;
    }
}
