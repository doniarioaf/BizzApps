package com.servlet.customermanggala.entity;

public class BodyCustomerManggala {
	private String customertype;
	private String customername;
	private String alias;
	private String alamat;
	private String provinsi;
	private String kota;
	private String kodepos;
	private String npwp;
	private String nib;
	private String telpkantor;
	private boolean isactive;
	
	public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
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
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getNib() {
		return nib;
	}
	public void setNib(String nib) {
		this.nib = nib;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getTelpkantor() {
		return telpkantor;
	}
	public void setTelpkantor(String telpkantor) {
		this.telpkantor = telpkantor;
	}
}
