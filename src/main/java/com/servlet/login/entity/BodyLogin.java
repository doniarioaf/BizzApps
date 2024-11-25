package com.servlet.login.entity;

public class BodyLogin {
	private String user;
	private String password;
	private Long idbranch;

	public Long getIdbranch() {
		return idbranch;
	}

	public void setIdbranch(Long idbranch) {
		this.idbranch = idbranch;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
