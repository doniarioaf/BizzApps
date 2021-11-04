package com.servlet.admin.company.entity;

import java.util.List;

import com.google.gson.JsonArray;

public class BodyCompany {
	private String nama;
	private String code;
	private String contactnumber;
	private String displayname;
	private String address;
	private String email;
	private boolean isactive;
	private Long[] branches;
	
	
	public Long[] getBranches() {
		return branches;
	}
	public void setBranches(Long[] branches) {
		this.branches = branches;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
