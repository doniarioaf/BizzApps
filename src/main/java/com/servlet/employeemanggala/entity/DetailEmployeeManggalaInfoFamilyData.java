package com.servlet.employeemanggala.entity;

import java.sql.Date;

public class DetailEmployeeManggalaInfoFamilyData {
	private String namaanak;
	private Date tanggallahir;
	private String jeniskelamin;
	private String status;
	public String getNamaanak() {
		return namaanak;
	}
	public void setNamaanak(String namaanak) {
		this.namaanak = namaanak;
	}
	public Date getTanggallahir() {
		return tanggallahir;
	}
	public void setTanggallahir(Date tanggallahir) {
		this.tanggallahir = tanggallahir;
	}
	public String getJeniskelamin() {
		return jeniskelamin;
	}
	public void setJeniskelamin(String jeniskelamin) {
		this.jeniskelamin = jeniskelamin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
