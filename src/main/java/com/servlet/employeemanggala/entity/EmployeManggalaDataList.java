package com.servlet.employeemanggala.entity;

public class EmployeManggalaDataList {
	private Long id;
	private String code;
	private String statuskaryawan;
	private String jabatan;
	private String nama;
	private boolean isactive;
	private String jeniskelamin;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatuskaryawan() {
		return statuskaryawan;
	}
	public void setStatuskaryawan(String statuskaryawan) {
		this.statuskaryawan = statuskaryawan;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getJeniskelamin() {
		return jeniskelamin;
	}
	public void setJeniskelamin(String jeniskelamin) {
		this.jeniskelamin = jeniskelamin;
	}
}
