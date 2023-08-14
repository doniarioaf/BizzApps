package com.servlet.report.entity;

import java.util.List;

import com.servlet.admin.usermobile.entity.UserMobileListData;
import com.servlet.mobile.project.entity.ProjectData;

public class TemplateReport {
	private List<UserMobileListData> userMobileOptions;
	private List<ProjectData> projectoptions;
	public List<UserMobileListData> getUserMobileOptions() {
		return userMobileOptions;
	}
	public void setUserMobileOptions(List<UserMobileListData> userMobileOptions) {
		this.userMobileOptions = userMobileOptions;
	}
	public List<ProjectData> getProjectoptions() {
		return projectoptions;
	}
	public void setProjectoptions(List<ProjectData> projectoptions) {
		this.projectoptions = projectoptions;
	}
}
