package com.servlet.user.entity;

public class UserListData {
	private long id;
	private String nama;
	private String username;
	private String notelepon;
	private boolean isactive;
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNotelepon() {
		return notelepon;
	}
	public void setNotelepon(String notelepon) {
		this.notelepon = notelepon;
	}

}
