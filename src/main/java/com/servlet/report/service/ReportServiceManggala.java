package com.servlet.report.service;

import com.servlet.report.entity.HistoryTruckTemplate;
import com.servlet.report.entity.ManggalaStatusInvoice;
import com.servlet.report.entity.Manggala_BodyReportBongkarMuatDanDepo;
import com.servlet.report.entity.ParamReportManggala;
import com.servlet.report.entity.ReportSummaryKegiatanTructTemplate;
import com.servlet.report.entity.ReportWorkBookExcel;

public interface ReportServiceManggala {
	ReportWorkBookExcel getReportBongkarMuatDanDepo(Manggala_BodyReportBongkarMuatDanDepo body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportStatusInvoice(ManggalaStatusInvoice body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportKasBank(ParamReportManggala body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportLabaRugi(ParamReportManggala body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportSummaryKegiatanTruck(ParamReportManggala body,long idcompany,long idbranch);
	ReportSummaryKegiatanTructTemplate getSummaryKegiatanTructTemplate(long idcompany,long idbranch);
	HistoryTruckTemplate getHistoryTrucktTemplate(long idcompany,long idbranch);
	ReportWorkBookExcel getReportHistoryTruck(ParamReportManggala body,long idcompany,long idbranch);
}
