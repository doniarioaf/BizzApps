package com.servlet.bankaccount.entity;

import java.sql.Timestamp;

public class BankAccountData {
	private Long id;
	private String cabang;
	private String norekening;
	private Timestamp dateopen;
	private String catatan1;
	private String catatan2;
	private boolean isactive;
	private String namabank;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCabang() {
		return cabang;
	}
	public void setCabang(String cabang) {
		this.cabang = cabang;
	}
	public String getNorekening() {
		return norekening;
	}
	public void setNorekening(String norekening) {
		this.norekening = norekening;
	}
	public Timestamp getDateopen() {
		return dateopen;
	}
	public void setDateopen(Timestamp dateopen) {
		this.dateopen = dateopen;
	}
	public String getCatatan1() {
		return catatan1;
	}
	public void setCatatan1(String catatan1) {
		this.catatan1 = catatan1;
	}
	public String getCatatan2() {
		return catatan2;
	}
	public void setCatatan2(String catatan2) {
		this.catatan2 = catatan2;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getNamabank() {
		return namabank;
	}
	public void setNamabank(String namabank) {
		this.namabank = namabank;
	}
}
