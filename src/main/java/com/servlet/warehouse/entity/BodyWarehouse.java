package com.servlet.warehouse.entity;

public class BodyWarehouse {
	private Long id;
	private String province;
	private String city;
	private String kecamatan;
	private String nama;
	private String alamat;
	private String ancerancer;
	private String contactnumber;
	private String contacthp;
	private String note;
	private boolean isactive;
	private Long district;
	
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
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
	
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
