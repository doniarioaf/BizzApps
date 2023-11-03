package com.servlet.user.entity;

public class BodyEditPass {
	private long companyid;
	private long branchid;
	private String username;
	private String password;
	private String passwordchange;
	public long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(long companyid) {
		this.companyid = companyid;
	}
	public long getBranchid() {
		return branchid;
	}
	public void setBranchid(long branchid) {
		this.branchid = branchid;
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
	public String getPasswordchange() {
		return passwordchange;
	}
	public void setPasswordchange(String passwordchange) {
		this.passwordchange = passwordchange;
	}
}
