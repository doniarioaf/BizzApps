package com.servlet.mobile.usermobilecallplan.entity;

import java.util.List;

public class DownloadUserMobileCallPlan {
	private long size;
	private List<UserMobileCallPlanData> callplans;
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public List<UserMobileCallPlanData> getCallplans() {
		return callplans;
	}
	public void setCallplans(List<UserMobileCallPlanData> callplans) {
		this.callplans = callplans;
	}
}
