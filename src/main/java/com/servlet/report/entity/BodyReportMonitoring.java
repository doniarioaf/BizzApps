package com.servlet.report.entity;

public class BodyReportMonitoring {
	private String idusermobile;
	private String fromdate;
	private String todate;
	private String typereport;
	public String getTypereport() {
		return typereport;
	}
	public void setTypereport(String typereport) {
		this.typereport = typereport;
	}
	public String getIdusermobile() {
		return idusermobile;
	}
	public void setIdusermobile(String idusermobile) {
		this.idusermobile = idusermobile;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
}
