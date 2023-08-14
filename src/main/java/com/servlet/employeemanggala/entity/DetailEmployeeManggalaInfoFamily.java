package com.servlet.employeemanggala.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "detail_employee_manggala_family", schema = "public")
public class DetailEmployeeManggalaInfoFamily implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailEmployeeManggalaInfoFamilyPK detailEmployeeManggalaInfoFamilyPK;
	private String namaanak;
	private Date tanggallahir;
	private String jeniskelamin;
	private String status;
	public DetailEmployeeManggalaInfoFamilyPK getDetailEmployeeManggalaInfoFamilyPK() {
		return detailEmployeeManggalaInfoFamilyPK;
	}
	public void setDetailEmployeeManggalaInfoFamilyPK(
			DetailEmployeeManggalaInfoFamilyPK detailEmployeeManggalaInfoFamilyPK) {
		this.detailEmployeeManggalaInfoFamilyPK = detailEmployeeManggalaInfoFamilyPK;
	}
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
