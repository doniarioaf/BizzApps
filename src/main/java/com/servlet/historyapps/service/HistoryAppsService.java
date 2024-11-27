package com.servlet.historyapps.service;

import java.sql.Timestamp;

public interface HistoryAppsService {
    void saveHistory(Long idcompany, Long idbranch, Long iduser, String action, String menu, String data, String datafter, String databefore, Timestamp ts);
}
