package com.servlet.historyemployeemanggala.entity;

import java.sql.Timestamp;

public class HistoryEmployeeData {
	private Long id;
	private Long idemployee;
	private String jabatan;
	private String statusemployee;
	private String gaji;
	private Timestamp tanggal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdemployee() {
		return idemployee;
	}
	public void setIdemployee(Long idemployee) {
		this.idemployee = idemployee;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getStatusemployee() {
		return statusemployee;
	}
	public void setStatusemployee(String statusemployee) {
		this.statusemployee = statusemployee;
	}
	public String getGaji() {
		return gaji;
	}
	public void setGaji(String gaji) {
		this.gaji = gaji;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
}
