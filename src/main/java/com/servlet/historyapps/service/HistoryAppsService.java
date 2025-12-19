package com.servlet.historyapps.service;

import java.sql.Timestamp;
import java.util.HashMap;

public interface HistoryAppsService {
    void saveHistory(Long idcompany, Long idbranch, Long iduser, String action, String menu, String data, String datafter, String databefore, Timestamp ts);
    Long countByActionAndMenu(Long idcompany, Long idbranch, String action, String menu);
    Long countByActionAndMenuParam(Long idcompany, Long idbranch, String action, String menu, HashMap<String,Object> param);
}
