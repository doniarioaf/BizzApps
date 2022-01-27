package com.servlet.mobile.infoheader.entity;

import java.util.HashMap;
import java.util.List;

import com.servlet.admin.customertype.entity.CustomerTypeData;

public class TemplateInfo {
	List<CustomerTypeData> customertypeoptions;
	List<TypeOptions> typeoptions;

	
	public List<TypeOptions> getTypeoptions() {
		return typeoptions;
	}

	public void setTypeoptions(List<TypeOptions> typeoptions) {
		this.typeoptions = typeoptions;
	}

	public List<CustomerTypeData> getCustomertypeoptions() {
		return customertypeoptions;
	}

	public void setCustomertypeoptions(List<CustomerTypeData> customertypeoptions) {
		this.customertypeoptions = customertypeoptions;
	}
}
