package com.servlet.admin.usermobile.entity;

import java.util.List;

import com.servlet.admin.usermobilerole.entity.UserMobileRoleData;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanData;

public class UserDetailMobile {
	private UserMobile user;
	private List<UserMobileRoleData> roles;
	private List<UserMobileCallPlanData> callplans;
	public UserMobile getUser() {
		return user;
	}
	public void setUser(UserMobile user) {
		this.user = user;
	}
	public List<UserMobileRoleData> getRoles() {
		return roles;
	}
	public void setRoles(List<UserMobileRoleData> roles) {
		this.roles = roles;
	}
	public List<UserMobileCallPlanData> getCallplans() {
		return callplans;
	}
	public void setCallplans(List<UserMobileCallPlanData> callplans) {
		this.callplans = callplans;
	}
	
	

}
