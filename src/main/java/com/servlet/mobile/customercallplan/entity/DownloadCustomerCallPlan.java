package com.servlet.mobile.customercallplan.entity;

import java.util.List;

public class DownloadCustomerCallPlan {
	private long size;
	private List<CustomerCallPlanData> customercallplan;
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public List<CustomerCallPlanData> getCustomercallplan() {
		return customercallplan;
	}
	public void setCustomercallplan(List<CustomerCallPlanData> customercallplan) {
		this.customercallplan = customercallplan;
	}
}
