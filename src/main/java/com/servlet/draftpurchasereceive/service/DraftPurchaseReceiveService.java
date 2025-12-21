package com.servlet.draftpurchasereceive.service;

import com.servlet.draftpurchasereceive.entity.*;
import com.servlet.shared.ReturnData;
import com.servlet.stockitems.entity.ReportKartuStock;

import java.util.List;

public interface DraftPurchaseReceiveService {
    List<DraftPurchaseReceiveList> getList(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param);
    DraftPurchaseReceiveDetailData getDetail(Long id,Long idcompany, Long idbranch);
    DraftPurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyDraftPurchaseReceive body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyDraftPurchaseReceive body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    SearchDataTemplateByVendor getTemplateByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    boolean checkIDVendor(Long idvendor);
    List<DraftPurchaseReceiveDropDownList> getDropDownList(Long idcompany, Long idbranch, ParamGetDataDraftPR param);
    List<DraftPurchaseReceiveItemsDetailData> getListItemsByID(Long iddraftpurchasereceive);
    List<DraftPurchaseReceiveItemsDetailData> getListItemsByIDForPR(Long iddraftpurchasereceive);
    List<DraftPurchaseReceiveList> getListNotLinksInPR(Long idcompany, Long idbranch, ParamSearchDraftPurchaseReceive param);
    PrintDataDraftPR printDataDraftPR(Long idcompany, Long idbranch, Long iduser, Long id);
    Long calculateQtyDpr(Long idcompany, Long idbranch,ParamCalculateQtyDPR param);
    List<ReportKartuStock> getListDprReportKartuStock(Long idcompany, Long idbranch,ParamSearchDraftPurchaseReceive param);
    List<Long> checkIdCP(Long idcompany, Long idbranch, Long idcategoryProduct);
    ReturnData catatDownload(Long id,Long idcompany, Long idbranch, Long iduser);
}
