package com.servlet.user.entity;

public class BodyUserApps {
	private String username;
	private String password;
	private String nama;
	private String notelepon;
	private String address;
	private boolean isactive;
	private String email;
//	private long idcompany;
//	private long idbranch;
	private Long[] roles;
	public Long[] getRoles() {
		return roles;
	}
	public void setRoles(Long[] roles) {
		this.roles = roles;
	}
//	public long getIdcompany() {
//		return idcompany;
//	}
//	public void setIdcompany(long idcompany) {
//		this.idcompany = idcompany;
//	}
//	public long getIdbranch() {
//		return idbranch;
//	}
//	public void setIdbranch(long idbranch) {
//		this.idbranch = idbranch;
//	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNotelepon() {
		return notelepon;
	}
	public void setNotelepon(String notelepon) {
		this.notelepon = notelepon;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
