package com.servlet.admin.customer.entity;

import java.util.List;

import com.servlet.admin.customertype.entity.CustomerTypeData;

public class CustomerTemplate {
	List<CustomerTypeData> customertypeoptions;

	public List<CustomerTypeData> getCustomertypeoptions() {
		return customertypeoptions;
	}

	public void setCustomertypeoptions(List<CustomerTypeData> customertypeoptions) {
		this.customertypeoptions = customertypeoptions;
	}
}
