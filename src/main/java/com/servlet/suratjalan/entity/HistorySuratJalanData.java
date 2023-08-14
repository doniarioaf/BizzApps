package com.servlet.suratjalan.entity;

import java.sql.Timestamp;

public class HistorySuratJalanData {
	private Long id;
	private Long idsuratjalan;
	private Timestamp tanggal;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdsuratjalan() {
		return idsuratjalan;
	}
	public void setIdsuratjalan(Long idsuratjalan) {
		this.idsuratjalan = idsuratjalan;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
}
