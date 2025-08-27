package com.servlet.cancelpackinglist.service;

import com.servlet.cancelpackinglist.entity.BodyCancelPackingList;
import com.servlet.cancelpackinglist.entity.CancelPLList;
import com.servlet.cancelpackinglist.entity.ParamSearchCancelPackingList;
import com.servlet.cancelpackinglist.entity.QueryNotJoinCancelPackingListData;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface CancelPackingListService {
    List<CancelPLList> getList(Long idcompany, Long idbranch, ParamSearchCancelPackingList param);
    ReturnData cancelPackingList(Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body);
    ReturnData deleteCancelPackingListByidpackinglist(Long idcompany, Long idbranch, Long iduser, Long idpackinglist);
    List<QueryNotJoinCancelPackingListData> getDataByIdPackingList(Long idcompany, Long idbranch, Long idpackinglist);
}
