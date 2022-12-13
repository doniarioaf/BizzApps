package com.servlet.report.entity;

public class ManggalaStatusInvoice {
	private Long fromDate;
	private Long toDate;
	private String status;
	private String typeReport;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypeReport() {
		return typeReport;
	}
	public void setTypeReport(String typeReport) {
		this.typeReport = typeReport;
	}
	public Long getFromDate() {
		return fromDate;
	}
	public void setFromDate(Long fromDate) {
		this.fromDate = fromDate;
	}
	public Long getToDate() {
		return toDate;
	}
	public void setToDate(Long toDate) {
		this.toDate = toDate;
	}
}
