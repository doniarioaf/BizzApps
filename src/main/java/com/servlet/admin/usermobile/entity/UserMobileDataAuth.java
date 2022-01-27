package com.servlet.admin.usermobile.entity;

public class UserMobileDataAuth {
	private long id;	
	private String username;
	private String password;
	private long idcompany;
	private long idbranch;
	private String token;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(long idbranch) {
		this.idbranch = idbranch;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
