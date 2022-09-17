package com.servlet.customermanggala.entity;

public class CustomerManggalaData {
	private Long id;
	private String customertype;
	private String customername;
	private String alias;
	private String alamat;
	private String provinsi;
	private String provinsiname;
	private String kota;
	private String kotaname;
	private String kodepos;
	private String kodeposname;
	private String npwp;
	private String nib;
	private boolean isactive;
	private String telpkantor;
	private CustomerManggalaTemplate template;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getProvinsiname() {
		return provinsiname;
	}
	public void setProvinsiname(String provinsiname) {
		this.provinsiname = provinsiname;
	}
	public String getKotaname() {
		return kotaname;
	}
	public void setKotaname(String kotaname) {
		this.kotaname = kotaname;
	}
	public String getKodeposname() {
		return kodeposname;
	}
	public void setKodeposname(String kodeposname) {
		this.kodeposname = kodeposname;
	}
	public CustomerManggalaTemplate getTemplate() {
		return template;
	}
	public void setTemplate(CustomerManggalaTemplate template) {
		this.template = template;
	}
	public String getTelpkantor() {
		return telpkantor;
	}
	public void setTelpkantor(String telpkantor) {
		this.telpkantor = telpkantor;
	}
}
