package com.servlet.report.entity;

import java.util.List;

import com.servlet.admin.usermobile.entity.UserMobileListData;
import com.servlet.mobile.project.entity.ProjectData;

public class TemplateMaps {
	private List<UserMobileListData> usermobileoptions;
	private List<ProjectData> projectoptions;
	public List<ProjectData> getProjectoptions() {
		return projectoptions;
	}

	public void setProjectoptions(List<ProjectData> projectoptions) {
		this.projectoptions = projectoptions;
	}

	public List<UserMobileListData> getUsermobileoptions() {
		return usermobileoptions;
	}

	public void setUsermobileoptions(List<UserMobileListData> usermobileoptions) {
		this.usermobileoptions = usermobileoptions;
	}
}
