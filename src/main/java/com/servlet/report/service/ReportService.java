package com.servlet.report.service;

import java.util.List;

import com.servlet.mobile.monitorusermobile.entity.DataMonitorForMaps;
import com.servlet.report.entity.BodyGetMaps;
import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.entity.ReportToPDF;
import com.servlet.report.entity.ReportToPPT;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.entity.TemplateMaps;
import com.servlet.report.entity.TemplateReport;

public interface ReportService {
	ReportWorkBookExcel getReportMonitoringData(BodyReportMonitoring body,long idcompany,long idbranch);
	ReportToPDF getReportMonitoringDataPDF(BodyReportMonitoring body,long idcompany,long idbranch);
	List<DataMonitorForMaps> getListDataMaps(BodyGetMaps body,long idcompany,long idbranch);
	TemplateMaps getTemplateMaps(long idcompany,long idbranch);
	TemplateReport getTemplateReport(long idcompany,long idbranch);
	ReportToPPT getReportMonitoringDataPPT(long idcompany,long idbranch);
}
