package com.servlet.admin.usermobile.entity;

import com.servlet.shared.ReturnData;

public class ReturnLoginMobile {
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
}
