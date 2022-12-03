package com.servlet.report.service;

import com.servlet.report.entity.ManggalaStatusInvoice;
import com.servlet.report.entity.Manggala_BodyReportBongkarMuatDanDepo;
import com.servlet.report.entity.ReportWorkBookExcel;

public interface ReportServiceManggala {
	ReportWorkBookExcel getReportBongkarMuatDanDepo(Manggala_BodyReportBongkarMuatDanDepo body,long idcompany,long idbranch);
	ReportWorkBookExcel getReportStatusInvoice(ManggalaStatusInvoice body,long idcompany,long idbranch);
}
