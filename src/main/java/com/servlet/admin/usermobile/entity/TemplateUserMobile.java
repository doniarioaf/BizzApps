package com.servlet.admin.usermobile.entity;

import java.util.List;

import com.servlet.admin.role.entity.Role;
import com.servlet.mobile.callplan.entity.CallPlanListData;

public class TemplateUserMobile {
	private List<Role> roleoptions;
	private List<CallPlanListData> callplanoptions;
	public List<Role> getRoleoptions() {
		return roleoptions;
	}
	public void setRoleoptions(List<Role> roleoptions) {
		this.roleoptions = roleoptions;
	}
	public List<CallPlanListData> getCallplanoptions() {
		return callplanoptions;
	}
	public void setCallplanoptions(List<CallPlanListData> callplanoptions) {
		this.callplanoptions = callplanoptions;
	}
}
