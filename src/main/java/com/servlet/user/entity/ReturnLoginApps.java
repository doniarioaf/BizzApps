package com.servlet.user.entity;

import com.servlet.shared.ReturnData;

public class ReturnLoginApps {
	private long idcompany;
	private long idbranch;
	private String username;
	private ReturnData returnData;
	private UserData userData;
	public ReturnData getReturnData() {
		return returnData;
	}
	public void setReturnData(ReturnData returnData) {
		this.returnData = returnData;
	}
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
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
