package com.servlet.mobile.usermobilecallplan.entity;

import java.util.List;

import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;

public class UserMobileCallPlanDataMobile {
	private List<UserMobileCallPlanData> callplans;
	private List<CustomerCallPlanData> customercallplans;
	public List<UserMobileCallPlanData> getCallplans() {
		return callplans;
	}
	public void setCallplans(List<UserMobileCallPlanData> callplans) {
		this.callplans = callplans;
	}
	public List<CustomerCallPlanData> getCustomercallplans() {
		return customercallplans;
	}
	public void setCustomercallplans(List<CustomerCallPlanData> customercallplans) {
		this.customercallplans = customercallplans;
	}
}
