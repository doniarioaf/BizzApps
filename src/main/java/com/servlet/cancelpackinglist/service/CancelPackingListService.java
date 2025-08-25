package com.servlet.cancelpackinglist.service;

import com.servlet.cancelpackinglist.entity.BodyCancelPackingList;
import com.servlet.cancelpackinglist.entity.QueryNotJoinCancelPackingListData;
import com.servlet.shared.ReturnData;

import java.util.List;

public interface CancelPackingListService {
    ReturnData cancelPackingList(Long idcompany, Long idbranch, Long iduser, BodyCancelPackingList body);
    ReturnData deleteCancelPackingListByidpackinglist(Long idcompany, Long idbranch, Long iduser, Long idpackinglist);
    List<QueryNotJoinCancelPackingListData> getDataByIdPackingList(Long idcompany, Long idbranch, Long idpackinglist);
}
