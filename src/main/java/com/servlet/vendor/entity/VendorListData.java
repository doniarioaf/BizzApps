package com.servlet.vendor.entity;

public class VendorListData {
	private Long id;
	private Long idvendorcategory;
	private String vendorcategoryname;
	private String code;
	private String nama;
	private String alias;
	private boolean isactive;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdvendorcategory() {
		return idvendorcategory;
	}
	public void setIdvendorcategory(Long idvendorcategory) {
		this.idvendorcategory = idvendorcategory;
	}
	public String getVendorcategoryname() {
		return vendorcategoryname;
	}
	public void setVendorcategoryname(String vendorcategoryname) {
		this.vendorcategoryname = vendorcategoryname;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
