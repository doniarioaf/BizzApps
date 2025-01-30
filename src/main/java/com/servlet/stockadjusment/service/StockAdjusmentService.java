package com.servlet.stockadjusment.service;

import com.servlet.shared.ReturnData;
import com.servlet.stockadjusment.entity.*;
import com.servlet.stockitems.entity.ReportKartuStock;

import java.util.List;

public interface StockAdjusmentService {
    List<StockAdjusmentDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyStockAdjusment body);
    ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyStockAdjusment body);
    ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser);
    StockAdjusmentTemplate getTemplate(Long idcompany, Long idbranch);
    StockAdjsumentDataDetail getDetail(Long idcompany, Long idbranch,Long id);
    Long calculateQtySA(Long idcompany, Long idbranch,String type, ParamCalculateQtySA param);
    List<ReportKartuStock> getListReportKartuStock(Long idcompany, Long idbranch, ParamCalculateQtySA param);
    PrintDataStockUdangMati getPrintData(Long idcompany, Long idbranch,Long iduser,Long id);
}
