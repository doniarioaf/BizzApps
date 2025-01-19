package com.servlet.bank.handler;

import com.servlet.bank.entity.*;
import com.servlet.bank.mapper.QueryBankList;
import com.servlet.bank.repo.BankBranchRepo;
import com.servlet.bank.repo.BankRepo;
import com.servlet.bank.service.BankService;
import com.servlet.cargo.mapper.QueryCargoList;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.packinglist.entity.BodyPackingListItem;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstantCodeDocument;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BankHandler implements BankService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BankRepo repo;

    @Autowired
    private BankBranchRepo bankBranchRepo;

    @Autowired
    private HistoryAppsService historyAppsService;

    @Autowired
    UserAppsService userAppsService;

    protected final String namaMenu = "Bank";

    @Override
    public List<BankDataList> getList(Long idcompany, Long idbranch, Long iduser) {
        UserListData user = userAppsService.getUserByID(iduser);

        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryBankList().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.isdelete = false  ");
        if(user != null){
            if(!user.isIsallbranch()){
                sqlBuilder.append(" and data.id in (select bb.idbank from bank_branch as bb where bb.idbranch = "+idbranch+" )  ");
            }
        }
        final Object[] queryParameters = new Object[] {idcompany,idbranch};
        return this.jdbcTemplate.query(sqlBuilder.toString(), new QueryBankList(), queryParameters);
    }

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyBank body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                Bank table = new Bank();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setBankname(body.getBankname());
                table.setAccname(body.getAccname());
                table.setAccno(body.getAccno());
                table.setDateopen(new Date(body.getDateopen()));
                table.setSaldoawal(body.getSaldoawal());
                table.setCatatan1(body.getCatatan1());
                table.setCatatan2(body.getCatatan2());
                table.setIsdelete(false);
                table.setCreateddate(ts);
                table.setCreatedby(iduser);
                idsave = repo.saveAndFlush(table).getId();
                HashMap<Object, Object> mapsItems = setItems(idcompany,idbranch,iduser,idsave, body.getBankbranchs());
                List<ValidationDataMessage> validationsItems = (List<ValidationDataMessage>) mapsItems.get("validations");
                if(validationsItems.size() == 0){
                    String data = table.toString();
                    String dataItems = (String) mapsItems.get("dataItems");
                    String mixData = "header = "+data+" | Items = "+dataItems;
                    historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,mixData,"","",ts);
                }else{
                    repo.deleteById(idsave);
                    bankBranchRepo.deleteAllDetailByIdBank(idsave);
                    validations.add(validationsItems.get(0));
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

    private HashMap<Object,Object> setItems(Long idcompany, Long idbranch,Long iduser, Long idsave, Long[] items){
        List<ValidationDataMessage> validations = new ArrayList<>();
        HashMap<Object,Object> maps = new HashMap<>();
        List<Long> listitem = new ArrayList<>();
        try{
            if(items.length > 0){
                UserListData user = userAppsService.getUserByID(iduser);

                for(Long idbr : items){
                    if(!user.isIsallbranch()){
                        if(idbranch.longValue() != idbr.longValue()){
                            continue;
                        }
                    }
                    BankBranchPK pk = new BankBranchPK();
                    pk.setIdbank(idsave);
                    pk.setIdbranch(idbr);
                    BankBranch table = new BankBranch();
                    table.setBankBranchPK(pk);
                    bankBranchRepo.saveAndFlush(table);
                    listitem.add(idbr);
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
}
