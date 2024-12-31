package com.servlet.filedocument.handler;

import com.servlet.cargo.mapper.QueryCargoList;
import com.servlet.filedocument.entity.BodyFileDocument;
import com.servlet.filedocument.entity.FileDocument;
import com.servlet.filedocument.entity.FileDocumentData;
import com.servlet.filedocument.mapper.QueryData;
import com.servlet.filedocument.repo.FileDocumentRepo;
import com.servlet.filedocument.service.FileDocumentService;
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
public class FileDocumentHandler implements FileDocumentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FileDocumentRepo repo;

    @Autowired
    private HistoryAppsService historyAppsService;

    protected final String namaMenu = "FileDoc";

    @Override
    public ReturnData save(Long idcompany, Long idbranch, Long iduser, Timestamp ts, BodyFileDocument body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp tsCurr = new Timestamp(new java.util.Date().getTime());
        if(validations.size() == 0) {
            try{
                FileDocument table = new FileDocument();
                table.setIdcompany(idcompany);
                table.setIdbranch(idbranch);
                table.setIddata(body.getIddata());
                table.setMenu(body.getMenu());
                table.setFilename(body.getFilename());
                table.setFiledocument(body.getFiledocument());
                table.setFilecontenttype(body.getFilecontenttype());
                table.setCreatedby(iduser);
                if(ts != null){
                    table.setCreateddate(ts);
                }else{
                    table.setCreateddate(tsCurr);
                }
                idsave = repo.saveAndFlush(table).getId();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"ADD",namaMenu,table.toString(),"","",(ts != null?ts:tsCurr));

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
    public ReturnData update(Long idcompany, Long idbranch, Long iduser, Timestamp ts, BodyFileDocument body) {
        List<ValidationDataMessage> validations = new ArrayList<>();
        long idsave = 0;
        Timestamp tsCurr = new Timestamp(new java.util.Date().getTime());
        FileDocumentData file = getDetail(body.getIddata(), body.getMenu(), idcompany,idbranch);
        if(validations.size() == 0 && file != null) {
            try{
                FileDocument table = repo.getById(file.getId());
                String before = table.toString();
                table.setFilename(body.getFilename());
                table.setFiledocument(body.getFiledocument());
                table.setFilecontenttype(body.getFilecontenttype());
                table.setModifiedby(iduser);
                if(ts != null){
                    table.setModifieddate(ts);
                }else{
                    table.setModifieddate(tsCurr);
                }
                idsave = repo.saveAndFlush(table).getId();
                String after = table.toString();
                historyAppsService.saveHistory(idcompany,idbranch,iduser,"EDIT",namaMenu,"",after,before,(ts != null?ts:tsCurr));

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
    public ReturnData uploadDoc(Long idcompany, Long idbranch, Long iduser, Timestamp ts, BodyFileDocument body) {
        ReturnData update = update(idcompany,idbranch,iduser,ts,body);
        if(update.getValidations().size() > 0 || update.getId() > 0){
            return update;
        }
        return save(idcompany,idbranch,iduser,ts,body);
    }

    @Override
    public FileDocumentData getDetail(Long iddata, String menu, Long idcompany, Long idbranch) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryData().schema());
        sqlBuilder.append(" where data.iddata = ? and data.menu = ? and data.idcompany = ? and data.idbranch = ? ");

        final Object[] queryParameters = new Object[] {iddata,menu,idcompany,idbranch};
        List<FileDocumentData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryData(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
