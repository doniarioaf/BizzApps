package com.servlet.invoice.service;

import com.servlet.invoice.entity.*;
import com.servlet.pelunasanpiutang.entity.FilterParamPelunasanPiutang;
import com.servlet.shared.ReturnData;
import com.servlet.stockitems.entity.ReportKartuStock;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDataList> getList(Long idcompany, Long idbranch, ParamSearchInvoice param);
    InvoiceTemplate getTemplate(Long idcompany, Long idbranch);
    InvoiceDataDetail getDetail(Long id, Long idcompany, Long idbranch);
    ReturnData save(Long idcompany, Long idbranch, Long iduser, BodyInvoice body);
    ReturnData update(Long id,Long idcompany, Long idbranch, Long iduser, BodyInvoice body);
    ReturnData delete(Long id,Long idcompany, Long idbranch, Long iduser);
    PrintInvoice getPrintDataByID(Long id, Long idcompany, Long idbranch,Long iduser,ParamPrintInvoice paramPrintInvoice);
    InvoiceDataList getDataByIdPackingList(Long idcompany, Long idbranch,Long idpackinglist);
    List<InvoiceDataPelunasanPiutang> getListInvoicePelunasanPiutang(Long idcompany, Long idbranch, FilterParamPelunasanPiutang param);
    List<InvoiceDataPelunasanPiutang> getListInvoicePelunasanPiutangByListID(Long idcompany, Long idbranch, String listIdInvoice);

    ReturnData updateOustandingTambah(Long id,Double bayar);
    ReturnData updateOustandingKurang(Long id,Double bayar);
    ReturnData catatDownload(Long id,Long idcompany, Long idbranch, Long iduser);
    List<InvoiceDataReportPiutang> getListInvoiceReportPiutang(Long idcompany, Long idbranch, ParamSearchInvoice param);
    List<InvoiceDataReportPelunasanPiutang> getListInvoiceReportPelunasanPiutang(Long idcompany, Long idbranch, ParamSearchInvoice param);
    List<ReportKartuStock> getListInvoiceReportKartuStock(Long idcompany, Long idbranch, ParamSearchInvoice param);
}
