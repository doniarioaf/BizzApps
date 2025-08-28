package com.servlet.packinglist.service;

import com.servlet.packinglist.entity.*;
import com.servlet.shared.ReturnData;
import com.servlet.stockitems.entity.ReportKartuStock;

import java.util.List;

public interface PackingListService {
    List<PackingListDataList> getList(Long idcompany, Long idbranch, ParamSearchPackingList param);
    PackingListTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPackingList body);
    PackingListDataDetail getDetail(Long id,Long idcompany, Long idbranch);
    ReturnData update(Long id, Long idcompany, Long idbranch, Long iduser, BodyPackingList body);
    ReturnData delete(Long id, Long idcompany, Long idbranch, Long iduser);
    List<PackingListDropDown> getDropDown(Long idcompany, Long idbranch, ParamDropDownPackingList param);
    PrintPackingList getPrintData(Long id,Long idcompany, Long idbranch,Long iduser, ParamPrint paramPrint);
    Long calculateQtyPL(Long idcompany, Long idbranch, ParamCalculateQtyPL param);
    Long calculateQtyPLByIdPackingList(Long idcompany, Long idbranch, ParamCalculateQtyPL param);
    ReturnData catatDownload(Long id,Long idcompany, Long idbranch, Long iduser);
    List<ReportKartuStock> getListReportKartuStock(Long idcompany, Long idbranch, ParamSearchPackingList param);
    ReturnData updateColumsIsAlreadyUpdatePriceToFalse(Long idcompany, Long idbranch, Long idcustomer, Long idpricelist);
    ReturnData updatePrice(Long id, Long idcompany, Long idbranch, Long iduser);
    ReturnData cancelPackingList(Long idcompany, Long idbranch, Long iduser, Long idpackinglist);
}
