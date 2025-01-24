package com.servlet.historyapps.handler;

import com.servlet.deposit.mapper.QueryCalculateAmountDeposit;
import com.servlet.historyapps.entity.HistoryApps;
import com.servlet.historyapps.mapper.QueryCalculteCountRow;
import com.servlet.historyapps.repo.HistoryAppsRepo;
import com.servlet.historyapps.service.HistoryAppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class HistoryAppsHandler implements HistoryAppsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HistoryAppsRepo repo;
    @Override
    public void saveHistory(Long idcompany, Long idbranch, Long iduser, String action, String menu, String data, String datafter, String databefore, Timestamp ts) {
        try{
            HistoryApps table = new HistoryApps();
            table.setIdcompany(idcompany);
            table.setIdbranch(idbranch);
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

    @Override
    public Long countByActionAndMenu(Long idcompany, Long idbranch, String action, String menu) {
        final StringBuilder sqlBuilder = new StringBuilder("select " + new QueryCalculteCountRow().schema());
        sqlBuilder.append(" where data.idcompany = ? and data.idbranch = ?  and data.action = ? and data.menu = ? ");
        final Object[] queryParameters = new Object[] {idcompany,idbranch,action, menu};
        List<Long> list = this.jdbcTemplate.query(sqlBuilder.toString(), new QueryCalculteCountRow(), queryParameters);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return 0L;
    }
}
