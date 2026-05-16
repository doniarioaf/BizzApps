package com.servlet.cancelpackinglist.service;

import com.servlet.cancelpackinglist.entity.*;
import com.servlet.shared.ReturnData;
import com.servlet.stockitems.entity.ReportKartuStock;

import java.util.List;

public interface CancelPackingListService {
    List<CancelPLList> getList(Long idcompany, Long idbranch, ParamSearchCancelPackingList param);
    ReturnData cancelPackingList(Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body);
    ReturnData editCancelPackingList(Long idcancel, Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body);
    ReturnData deleteCancelPackingListByidpackinglist(Long idcompany, Long idbranch, Long iduser, Long idpackinglist);
    List<QueryNotJoinCancelPackingListData> getDataByIdPackingList(Long idcompany, Long idbranch, Long idpackinglist);
    CancelPackingListData getDetail(Long idcompany, Long idbranch, Long id);
    Long calculateQtyCPL(Long idcompany, Long idbranch, ParamCalculateQtyCPL param);
    List<ReportKartuStock> getListReportKartuStock(Long idcompany, Long idbranch, ParamSearchCancelPackingList param);
    List<ReportCancelPackingList> getReportCancelPackingList(Long idcompany, Long idbranch, ParamReportCancelPackingList param);
    List<Long> checkIdCP(Long idcompany, Long idbranch, Long idcategoryProduct);
    ReturnData catatDownload(Long id,Long idcompany, Long idbranch, Long iduser);
    PrintCancelPackingList getPrintData(Long id,Long idcompany, Long idbranch,Long iduser);
}
