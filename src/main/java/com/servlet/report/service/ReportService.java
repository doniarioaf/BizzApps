package com.servlet.report.service;

import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.entity.ReportWorkBookExcel;

public interface ReportService {
	ReportWorkBookExcel getReportMonitoringData(BodyReportMonitoring body,long idcompany,long idbranch);
}
