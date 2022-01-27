package com.servlet.security.entity;

import java.sql.Timestamp;

public class AuthorizationData {
	private long id;
	private String username;
	private String password;
	private String passwordtoken;
	private Timestamp datelogin;
	private long idcompany;
	private long idbranch;
	private String typelogin;
	public String getTypelogin() {
		return typelogin;
	}
	public void setTypelogin(String typelogin) {
		this.typelogin = typelogin;
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
	public Timestamp getDatelogin() {
		return datelogin;
	}
	public void setDatelogin(Timestamp datelogin) {
		this.datelogin = datelogin;
	}
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
	public String getPasswordtoken() {
		return passwordtoken;
	}
	public void setPasswordtoken(String passwordtoken) {
		this.passwordtoken = passwordtoken;
	}
	
}
