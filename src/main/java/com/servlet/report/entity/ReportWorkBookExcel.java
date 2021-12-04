package com.servlet.report.entity;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportWorkBookExcel {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}
	public XSSFSheet getSheet() {
		return sheet;
	}
	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}
}
