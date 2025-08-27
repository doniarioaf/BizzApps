package com.servlet.cancelpackinglist.service;

import com.servlet.cancelpackinglist.entity.*;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface CancelPackingListService {
    List<CancelPLList> getList(Long idcompany, Long idbranch, ParamSearchCancelPackingList param);
    ReturnData cancelPackingList(Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body);
    ReturnData editCancelPackingList(Long idcancel, Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body);
    ReturnData deleteCancelPackingListByidpackinglist(Long idcompany, Long idbranch, Long iduser, Long idpackinglist);
    List<QueryNotJoinCancelPackingListData> getDataByIdPackingList(Long idcompany, Long idbranch, Long idpackinglist);
    CancelPackingListData getDetail(Long idcompany, Long idbranch, Long id);
    Long calculateQtyCPL(Long idcompany, Long idbranch, ParamCalculateQtyCPL param);
}
