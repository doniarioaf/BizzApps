package com.servlet.workorder.entity;

public class WorkOrderDropDownData {
	private Long id;
	private String nodocument;
	private Long idcustomer;
	private String namaCustomer;
	private String namacargo;
	private String nobl;
	private String noaju;
	private String jalur;
	private String jalurname;
	
	public String getJalurname() {
		return jalurname;
	}
	public void setJalurname(String jalurname) {
		this.jalurname = jalurname;
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
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getNamaCustomer() {
		return namaCustomer;
	}
	public void setNamaCustomer(String namaCustomer) {
		this.namaCustomer = namaCustomer;
	}
	public String getNamacargo() {
		return namacargo;
	}
	public void setNamacargo(String namacargo) {
		this.namacargo = namacargo;
	}
	public String getNobl() {
		return nobl;
	}
	public void setNobl(String nobl) {
		this.nobl = nobl;
	}
	public String getNoaju() {
		return noaju;
	}
	public void setNoaju(String noaju) {
		this.noaju = noaju;
	}
	public String getJalur() {
		return jalur;
	}
	public void setJalur(String jalur) {
		this.jalur = jalur;
	}
}
