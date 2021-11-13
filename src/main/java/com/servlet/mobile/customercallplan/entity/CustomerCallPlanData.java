package com.servlet.mobile.customercallplan.entity;

public class CustomerCallPlanData {
	private long id;
	private long idcallplan;
	private String nama;
	private String address;
	private String city;
	private String areaname;
	private String subarename;
	private String phone;
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
	public long getIdcallplan() {
		return idcallplan;
	}
	public void setIdcallplan(long idcallplan) {
		this.idcallplan = idcallplan;
	}
}
