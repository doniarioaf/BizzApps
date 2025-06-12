package com.servlet.purchasereceive.service;

import com.servlet.deposit.entity.ReportKartuDeposit;
import com.servlet.komisi.entity.ParamKomisi;
import com.servlet.pelunasanhutang.entity.FilterParamPelunasanHutang;
import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;
import com.servlet.purchasereceive.entity.*;
import com.servlet.report.entity.ParamReportPembelian;
import com.servlet.shared.ReturnData;
import com.servlet.stockitems.entity.ReportKartuStock;

import java.util.HashMap;
import java.util.List;

public interface PurchaseReceiveService {
    List<PurchaseReceiveDataList> getListAll(Long idcompany, Long idbranch, Long from, Long to);
    PurchaseReceiveAllTabData getListAllTab(Long idcompany, Long idbranch, Long from, Long to);
    PurchaseReceiveDataDetail getDetail(Long idcompany, Long idbranch, Long id);
    PurchaseReceiveTemplate getTemplate(Long idcompany, Long idbranch);
    ReportPurchaseReceiveTemplate getReportTemplate(Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyPurchaseReceive body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    ReturnData catatDownload(Long id,Long idcompany, Long idbranch, Long iduser);
    SearchDataTemplateByVendor searchDataByVendor(Long idcompany, Long idbranch,Long idvendor);
    Double calculateSetorByIdVendor(Long idcompany, Long idbranch, Long idvendor,String listidvendor);
    Double calculateSetorPinjamanByIdVendor(Long idcompany, Long idbranch, Long idvendor,String listidvendor);
    PrintDataPurchaseReceive printNotaPurchaseReceive(Long idcompany, Long idbranch,Long iduser, Long id,String printtype);
    Double calculateSetorByIdVendorAndCreatedDate(Long idcompany, Long idbranch, Long idvendor, Long date, String listidvendor);
    PurchaseReceiveDataList checkIdDeposit(Long iddeposit);
    PurchaseReceiveDataList getDataByIdDratPurchaseReceive(Long iddraftpurchasereceive,Long idcompany, Long idbranch);
    boolean checkIDVendor(Long idvendor);
    HashMap<String,Object> getDataForReport(Long idcompany, Long idbranch, ParamReportPembelian param);
    Long calculateQtyPr(Long idcompany, Long idbranch,ParamCalculateQtyPR param);
    ReturnData updateOustandingTambah(Long id,Double bayar);
    ReturnData updateOustandingKurang(Long id,Double bayar);
    List<PurchaseReceiveDataPelunasanHutang> getListForPelunasanHutang(Long idcompany, Long idbranch, FilterParamPelunasanHutang param);
    List<ReportPelunasanHutangDocumentHutang> getListPRReportHutang(Long idcompany, Long idbranch, FilterParamPurchaseReceive param);
    Double calculateSetorByIdVendorAndDate(Long idcompany, Long idbranch, Long idvendor, Long date, String listidvendor);
    List<ReportKartuDeposit> getListPrReportKartuDeposit(Long idcompany, Long idbranch, FilterParamPurchaseReceive param);
    PurchaseReceiveItemsNotJoin getItemInLastDocumentPR(Long idcompany, Long idbranch, Long idproduct, Long idcategoryproduct);
    List<ReportKartuStock> getListPrReportKartuStock(Long idcompany, Long idbranch, FilterParamPurchaseReceive param);
    List<PurchaseReceiveDataKomisi> getListKomisi(Long idcompany, Long idbranch, ParamKomisi param);
    List<PurchaseReceiveGetPrice> getListPurchaseReceiveGetPrice(Long idcompany, Long idbranch, ParamGetPrice param);
}
