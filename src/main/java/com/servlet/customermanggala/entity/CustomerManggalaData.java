package com.servlet.customermanggala.entity;

import java.util.List;

import com.servlet.address.entity.DistrictData;

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
	private Long district;
	private String districtName;
	private boolean isactive;
	private String telpkantor;
	private CustomerManggalaTemplate template;
	private List<DetailCustomerManggalaInfoKementerianData> detailsInfoKementerian;
	private List<DetailCustomerManggalaInfoContactData> detailsInfoContact;
	private List<DetailCustomerManggalaInfoGudangData> detailsInfoGudang;
	private List<DistrictData> districtOptions;
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
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
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
	public List<DetailCustomerManggalaInfoKementerianData> getDetailsInfoKementerian() {
		return detailsInfoKementerian;
	}
	public void setDetailsInfoKementerian(List<DetailCustomerManggalaInfoKementerianData> detailsInfoKementerian) {
		this.detailsInfoKementerian = detailsInfoKementerian;
	}
	public List<DetailCustomerManggalaInfoContactData> getDetailsInfoContact() {
		return detailsInfoContact;
	}
	public void setDetailsInfoContact(List<DetailCustomerManggalaInfoContactData> detailsInfoContact) {
		this.detailsInfoContact = detailsInfoContact;
	}
	public List<DetailCustomerManggalaInfoGudangData> getDetailsInfoGudang() {
		return detailsInfoGudang;
	}
	public void setDetailsInfoGudang(List<DetailCustomerManggalaInfoGudangData> detailsInfoGudang) {
		this.detailsInfoGudang = detailsInfoGudang;
	}
	public List<DistrictData> getDistrictOptions() {
		return districtOptions;
	}
	public void setDistrictOptions(List<DistrictData> districtOptions) {
		this.districtOptions = districtOptions;
	}
}
