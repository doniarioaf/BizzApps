package com.servlet.admin.usermobile.entity;

import com.servlet.shared.ReturnData;

public class ReturnLoginMobile {
	private long idcompany;
	private long idbranch;
	private String username;
	private UserMobileData userMobileData;
	private ReturnData returnData;
	public UserMobileData getUserMobileData() {
		return userMobileData;
	}
	public void setUserMobileData(UserMobileData userMobileData) {
		this.userMobileData = userMobileData;
	}
	public ReturnData getReturnData() {
		return returnData;
	}
	public void setReturnData(ReturnData returnData) {
		this.returnData = returnData;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
