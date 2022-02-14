package com.servlet.mobile.project.entity;

import java.util.List;

import com.servlet.admin.customer.entity.CustomerListData;

public class ProjectTemplateData {
	List<CustomerListData> customerOptions;

	public List<CustomerListData> getCustomerOptions() {
		return customerOptions;
	}

	public void setCustomerOptions(List<CustomerListData> customerOptions) {
		this.customerOptions = customerOptions;
	}
}
