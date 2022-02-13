package com.servlet.mobile.callplan.entity;

import java.util.List;

import com.servlet.admin.customer.entity.CustomerListData;
import com.servlet.mobile.project.entity.ProjectData;

public class TemplateDataCallPlan {
	List<CustomerListData> customerOptions;
	private List<ProjectData> projectoptions;

	public List<CustomerListData> getCustomerOptions() {
		return customerOptions;
	}

	public void setCustomerOptions(List<CustomerListData> customerOptions) {
		this.customerOptions = customerOptions;
	}

	public List<ProjectData> getProjectoptions() {
		return projectoptions;
	}

	public void setProjectoptions(List<ProjectData> projectoptions) {
		this.projectoptions = projectoptions;
	}
}
