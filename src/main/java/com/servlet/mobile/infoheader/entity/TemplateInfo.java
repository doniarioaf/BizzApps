package com.servlet.mobile.infoheader.entity;

import java.util.HashMap;
import java.util.List;

import com.servlet.admin.customertype.entity.CustomerTypeData;
import com.servlet.mobile.project.entity.ProjectData;

public class TemplateInfo {
	private List<CustomerTypeData> customertypeoptions;
	private List<TypeOptions> typeoptions;
	private List<ProjectData> projectoptions;
	
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

	public List<ProjectData> getProjectoptions() {
		return projectoptions;
	}

	public void setProjectoptions(List<ProjectData> projectoptions) {
		this.projectoptions = projectoptions;
	}
}
