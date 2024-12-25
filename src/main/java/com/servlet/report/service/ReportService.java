package com.servlet.report.service;

import com.servlet.report.entity.ReportWorkBookExcel;

import java.text.ParseException;

public interface ReportService {
    ReportWorkBookExcel getExcelPackingListByID(long id, long idcompany, long idbranch);
}
