package com.servlet.vendor.entity;

public class BodyVendor {
	private Long idvendorcategory;
	private String code;
	private String jenisbadanusaha;
	private String nama;
	private String alias;
	private String npwp;
	private String address;
	private String provinsi;
	private String kota;
	private String kodepos;
	private boolean isactive;
	private BodyDetailVendorBank[] detailsbank;
	private BodyDetailVendorContact[] detailscontact;
	
	public Long getIdvendorcategory() {
		return idvendorcategory;
	}
	public void setIdvendorcategory(Long idvendorcategory) {
		this.idvendorcategory = idvendorcategory;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getJenisbadanusaha() {
		return jenisbadanusaha;
	}
	public void setJenisbadanusaha(String jenisbadanusaha) {
		this.jenisbadanusaha = jenisbadanusaha;
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
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvinsi() {
		return provinsi;
	}
	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}
	public String getKodepos() {
		return kodepos;
	}
	public void setKodepos(String kodepos) {
		this.kodepos = kodepos;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public BodyDetailVendorBank[] getDetailsbank() {
		return detailsbank;
	}
	public void setDetailsbank(BodyDetailVendorBank[] detailsbank) {
		this.detailsbank = detailsbank;
	}
	public BodyDetailVendorContact[] getDetailscontact() {
		return detailscontact;
	}
	public void setDetailscontact(BodyDetailVendorContact[] detailscontact) {
		this.detailscontact = detailscontact;
	}
}
