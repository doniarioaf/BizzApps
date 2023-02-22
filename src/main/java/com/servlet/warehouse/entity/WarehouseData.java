package com.servlet.warehouse.entity;

public class WarehouseData {
	private Long id;
	private String province;
	private String provincename;
	private String city;
	private String cityname;
	private String kecamatan;
	private String kecamatanname;
	private String nama;
	private String alamat;
	private String ancerancer;
	private String contactnumber;
	private String contacthp;
	private String note;
	private Long idcustomer;
	private String customername;
	private boolean isactive;
	private WarehouseTemplate template;
	private Long district;
	private String districtname;
	
	public String getDistrictname() {
		return districtname;
	}
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	public Long getDistrict() {
		return district;
	}
	public void setDistrict(Long district) {
		this.district = district;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}
	public String getKecamatanname() {
		return kecamatanname;
	}
	public void setKecamatanname(String kecamatanname) {
		this.kecamatanname = kecamatanname;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getAncerancer() {
		return ancerancer;
	}
	public void setAncerancer(String ancerancer) {
		this.ancerancer = ancerancer;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getContacthp() {
		return contacthp;
	}
	public void setContacthp(String contacthp) {
		this.contacthp = contacthp;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public WarehouseTemplate getTemplate() {
		return template;
	}
	public void setTemplate(WarehouseTemplate template) {
		this.template = template;
	}
}
