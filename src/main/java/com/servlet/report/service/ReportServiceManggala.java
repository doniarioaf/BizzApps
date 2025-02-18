package com.servlet.report.service;

import com.servlet.invoice.entity.ParamReportInvoice;
import com.servlet.report.entity.*;

public interface ReportServiceManggala {
	ReportWorkBookExcel getReportBongkarMuatDanDepo(Manggala_BodyReportBongkarMuatDanDepo body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportStatusInvoice(ManggalaStatusInvoice body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportKasBank(ParamReportManggala body,long idcompany,long idbranch, long iduser);
	ReportWorkBookExcel getReportLabaRugi(ParamReportManggala body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportLabaRugi2(ParamReportManggala body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportSummaryKegiatanTruck(ParamReportManggala body,long idcompany,long idbranch);
	ReportSummaryKegiatanTructTemplate getSummaryKegiatanTructTemplate(long idcompany,long idbranch);
	HistoryTruckTemplate getHistoryTrucktTemplate(long idcompany,long idbranch);
	ReportWorkBookExcel getReportHistoryTruck(ParamReportManggala body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportInvoice(ParamReportInvoice param, long idcompany, long idbranch);
	ReportInvoiceTemplate getReportInvoiceTemplate(long idcompany, long idbranch);
}
