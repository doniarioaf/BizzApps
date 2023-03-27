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
	private Long district;
	private BodyCustomerManggalaInfoKementerian[] detailsinfokementerian;
	private BodyCustomerManggalaInfoContact[] detailsinfocontact;
	private BodyCustomerManggalaInfoGudang[] detailsinfogudang;
	private String alamat2;
	private String alamat3;
	
	public String getAlamat2() {
		return alamat2;
	}
	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}
	public String getAlamat3() {
		return alamat3;
	}
	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}
	public Long getDistrict() {
		return district;
	}
	public void setDistrict(Long district) {
		this.district = district;
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
	public String getTelpkantor() {
		return telpkantor;
	}
	public void setTelpkantor(String telpkantor) {
		this.telpkantor = telpkantor;
	}
	public BodyCustomerManggalaInfoKementerian[] getDetailsinfokementerian() {
		return detailsinfokementerian;
	}
	public void setDetailsinfokementerian(BodyCustomerManggalaInfoKementerian[] detailsinfokementerian) {
		this.detailsinfokementerian = detailsinfokementerian;
	}
	public BodyCustomerManggalaInfoContact[] getDetailsinfocontact() {
		return detailsinfocontact;
	}
	public void setDetailsinfocontact(BodyCustomerManggalaInfoContact[] detailsinfocontact) {
		this.detailsinfocontact = detailsinfocontact;
	}
	public BodyCustomerManggalaInfoGudang[] getDetailsinfogudang() {
		return detailsinfogudang;
	}
	public void setDetailsinfogudang(BodyCustomerManggalaInfoGudang[] detailsinfogudang) {
		this.detailsinfogudang = detailsinfogudang;
	}
}
