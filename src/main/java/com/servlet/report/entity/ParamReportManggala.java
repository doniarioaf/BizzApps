package com.servlet.report.entity;

public class ParamReportManggala {
	private Long fromDate;
	private Long toDate;
	private String status;
	private Long idbank;
	private String typeReport;
	private Long idAsset;
	private Long idEmployee;
	private Long idAssetSparepart;
	public Long getIdAssetSparepart() {
		return idAssetSparepart;
	}
	public void setIdAssetSparepart(Long idAssetSparepart) {
		this.idAssetSparepart = idAssetSparepart;
	}
	public Long getIdAsset() {
		return idAsset;
	}
	public void setIdAsset(Long idAsset) {
		this.idAsset = idAsset;
	}
	public Long getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getIdbank() {
		return idbank;
	}
	public void setIdbank(Long idbank) {
		this.idbank = idbank;
	}
	public String getTypeReport() {
		return typeReport;
	}
	public void setTypeReport(String typeReport) {
		this.typeReport = typeReport;
	}
}
