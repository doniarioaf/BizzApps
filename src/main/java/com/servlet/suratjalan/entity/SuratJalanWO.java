package com.servlet.suratjalan.entity;

import java.sql.Date;
import java.sql.Timestamp;


public class SuratJalanWO {
	private Long id;
	private String nodocument;
	private Timestamp tanggal;
	private Long idwarehouse;
	private String warehousename;
	private String nocontainer;
	private Date tanggalkembali;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTanggalkembali() {
		return tanggalkembali;
	}
	public void setTanggalkembali(Date tanggalkembali) {
		this.tanggalkembali = tanggalkembali;
	}
	public String getNocontainer() {
		return nocontainer;
	}
	public void setNocontainer(String nocontainer) {
		this.nocontainer = nocontainer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNodocument() {
		return nodocument;
	}
	public void setNodocument(String nodocument) {
		this.nodocument = nodocument;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	public Long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(Long idwarehouse) {
		this.idwarehouse = idwarehouse;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
}
