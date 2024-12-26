package com.servlet.purchasereceive.service;

import com.servlet.purchasereceive.entity.*;
import com.servlet.report.entity.ParamReportPembelian;
import com.servlet.shared.ReturnData;

import java.util.HashMap;
import java.util.List;

public interface PurchaseReceiveService {
    List<PurchaseReceiveDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to);
    PurchaseReceiveDataDetail getDetail(Long idcompany, Long idbranch, Long id);
    PurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch);
    ReportPurchaseReceiveTemplate getReportTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    ReturnData catatDownload(Long id,Long idcompany, Long idbranch, Long iduser);
    SearchDataTemplateByVendor searchDataByVendor(Long idcompany, Long idbranch,Long idvendor);
    Double calculateSetorByIdVendor(Long idcompany, Long idbranch, Long idvendor);
    PrintDataPurchaseReceive printNotaPurchaseReceive(Long idcompany, Long idbranch,Long iduser, Long id,String printtype);
    Double calculateSetorByIdVendorAndCreatedDate(Long idcompany, Long idbranch, Long idvendor, Long date);
    PurchaseReceiveDataList checkIdDeposit(Long iddeposit);
    PurchaseReceiveDataList getDataByIdDratPurchaseReceive(Long iddraftpurchasereceive,Long idcompany, Long idbranch);
    boolean checkIDVendor(Long idvendor);
    HashMap<String,Object> getDataForReport(Long idcompany, Long idbranch, ParamReportPembelian param);
    Long calculateQtyPr(Long idcompany, Long idbranch,ParamCalculateQtyPR param);
}
