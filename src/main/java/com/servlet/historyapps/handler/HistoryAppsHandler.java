package com.servlet.historyapps.handler;

import com.servlet.historyapps.entity.HistoryApps;
import com.servlet.historyapps.repo.HistoryAppsRepo;
import com.servlet.historyapps.service.HistoryAppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class HistoryAppsHandler implements HistoryAppsService {
    @Autowired
    private HistoryAppsRepo repo;
    @Override
    public void saveHistory(Long idcompany, Long idbranch, Long iduser, String action, String menu, String data, String datafter, String databefore, Timestamp ts) {
        try{
            HistoryApps table = new HistoryApps();
            table.setAction(action);
            table.setMenu(menu);
            table.setDatabefore(databefore);
            table.setDataafter(datafter);
            table.setData(data);
            if(ts != null){
                table.setDatetime(ts);
            }else{
                Timestamp curr = new Timestamp(new Date().getTime());
                table.setDatetime(curr);
            }
            table.setIduser(iduser);
            repo.saveAndFlush(table);
        }catch (Exception e){

        }
    }
}
