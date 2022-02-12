package com.servlet.admin.customerproject.entity;

public class CustomerProjectData {
	private long id;
	private long idproject;
	private long idcustomertype;
	private String nama;
	private String address;
	private String city;
	private String areaname;
	private String subarename;
	private String phone;
	private String latitude;
	private String longitude;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getSubarename() {
		return subarename;
	}
	public void setSubarename(String subarename) {
		this.subarename = subarename;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public long getIdcustomertype() {
		return idcustomertype;
	}
	public void setIdcustomertype(long idcustomertype) {
		this.idcustomertype = idcustomertype;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
}
