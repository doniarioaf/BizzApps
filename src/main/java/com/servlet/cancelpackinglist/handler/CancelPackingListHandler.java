package com.servlet.cancelpackinglist.handler;

import com.servlet.cancelpackinglist.entity.BodyCancelPackingList;
import com.servlet.cancelpackinglist.entity.CancelPackingList;
import com.servlet.cancelpackinglist.entity.QueryNotJoinCancelPackingListData;
import com.servlet.cancelpackinglist.mapper.QueryNotJoinCancelPackingList;
import com.servlet.cancelpackinglist.repo.CancelPackingListRepo;
import com.servlet.cancelpackinglist.service.CancelPackingListService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.shared.ConstansCodeMessage;
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
public class CancelPackingListHandler implements CancelPackingListService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CancelPackingListRepo repo;

    @Autowired
    private HistoryAppsService historyAppsService;

    protected final String namaMenu = "CancelPackingList";

    @Override
    public ReturnData cancelPackingList(Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                CancelPackingList table = new CancelPackingList();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setNodocument("C"+body.getNodocumentPL());
                table.setDatecancel(new Date(body.getDatecancel()));
                table.setIdpackinglist(body.getIdpackinglist());
                table.setKeterangan("Pembatalan Packinglist ("+body.getNodocumentPL()+")");
                table.setIsdelete(false);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = repo.saveAndFlush(table).getId();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,table.toString(),"","",ts);

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
}
