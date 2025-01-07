package com.servlet.report.service;

import com.servlet.report.entity.*;

import java.text.ParseException;

public interface ReportService {
    ReportWorkBookExcel getExcelPackingListByID(long id, long idcompany, long idbranch);
    ReportWorkBookExcel getExcelInvoiceByID(long id, long idcompany, long idbranch);
    ReportWorkBookExcel reportPembelian(long idcompany, long idbranch, ParamReportPembelian param);
    ReportWorkBookExcel reportStockUdangHidupMati(long idcompany, long idbranch, ParamReportStockUdangHidupMati param);
    ReportWorkBookExcel reportRekapStock(long idcompany, long idbranch, ParamReportRekapStock param);
    ReportWorkBookExcel reportStatusTagihanCargo(long idcompany, long idbranch, ParamReportStatusTagihanCargo param);
    ReportTemplate reportTemplateStatusTagihanCargo(long idcompany, long idbranch);
    ReportWorkBookExcel reportHutang(long idcompany, long idbranch, ParamReportHutang param);
    ReportTemplate reportTemplateReportHutang(long idcompany, long idbranch);
    ReportWorkBookExcel reportPiutang(long idcompany, long idbranch, ParamReportPiutang param);
    ReportTemplate reportTemplateReportPiutang(long idcompany, long idbranch);
    ReportWorkBookExcel reportPenjualan(long idcompany, long idbranch, ParamReportPenjualan param);
    ReportWorkBookExcel reportPelunasanPiutang(long idcompany, long idbranch, ParamReportPelunasanPiutang param);
}
