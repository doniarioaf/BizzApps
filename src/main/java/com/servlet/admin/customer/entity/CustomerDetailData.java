package com.servlet.admin.customer.entity;

import java.util.List;

import com.servlet.mobile.project.entity.ProjectData;

public class CustomerDetailData {
	private long id;
	private String nama;
	private String address;
	private String provinsi;
	private String city;
	private String areaname;
	private String subarename;
	private String phone;
	private String contactperson;
	private String customercode;
	private String latitude;
	private String longitude;
	private long idcustomertype;
	private String namecustomertype;
	private List<ProjectData> projects;
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
	public String getProvinsi() {
		return provinsi;
	}
	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
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
	public long getIdcustomertype() {
		return idcustomertype;
	}
	public void setIdcustomertype(long idcustomertype) {
		this.idcustomertype = idcustomertype;
	}
	public String getNamecustomertype() {
		return namecustomertype;
	}
	public void setNamecustomertype(String namecustomertype) {
		this.namecustomertype = namecustomertype;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public List<ProjectData> getProjects() {
		return projects;
	}
	public void setProjects(List<ProjectData> projects) {
		this.projects = projects;
	}
}
