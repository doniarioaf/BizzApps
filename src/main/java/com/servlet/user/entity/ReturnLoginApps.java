package com.servlet.user.entity;

import com.servlet.shared.ReturnData;

public class ReturnLoginApps {
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
}
