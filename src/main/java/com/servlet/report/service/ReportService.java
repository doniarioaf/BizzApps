package com.servlet.report.service;

import com.servlet.report.entity.ParamReportPembelian;
import com.servlet.report.entity.ParamReportStockUdangHidupMati;
import com.servlet.report.entity.ReportWorkBookExcel;

import java.text.ParseException;

public interface ReportService {
    ReportWorkBookExcel getExcelPackingListByID(long id, long idcompany, long idbranch);
    ReportWorkBookExcel getExcelInvoiceByID(long id, long idcompany, long idbranch);
    ReportWorkBookExcel reportPembelian(long idcompany, long idbranch, ParamReportPembelian param);
    ReportWorkBookExcel reportStockUdangHidupMati(long idcompany, long idbranch, ParamReportStockUdangHidupMati param);
}
