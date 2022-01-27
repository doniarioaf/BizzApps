package com.servlet.mobile.callplan.entity;

import java.util.List;

import com.servlet.admin.customer.entity.CustomerListData;

public class TemplateDataCallPlan {
	List<CustomerListData> customerOptions;

	public List<CustomerListData> getCustomerOptions() {
		return customerOptions;
	}

	public void setCustomerOptions(List<CustomerListData> customerOptions) {
		this.customerOptions = customerOptions;
	}
}
