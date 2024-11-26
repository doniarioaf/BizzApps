package com.servlet.user.entity;

import java.util.List;

import com.servlet.admin.userappsrole.entity.UserAppsRoleData;
import com.servlet.admin.userbranch.entity.UserBranchData;

public class UserDetailData {
//	private UserApps user;
	private UserDataDetail user;
	private List<UserAppsRoleData> roles;
	private List<UserBranchData> branchs;

	public List<UserBranchData> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<UserBranchData> branchs) {
		this.branchs = branchs;
	}

	public UserDataDetail getUser() {
		return user;
	}
	public void setUser(UserDataDetail user) {
		this.user = user;
	}
	//	public UserApps getUser() {
//		return user;
//	}
//	public void setUser(UserApps user) {
//		this.user = user;
//	}
	public List<UserAppsRoleData> getRoles() {
		return roles;
	}
	public void setRoles(List<UserAppsRoleData> roles) {
		this.roles = roles;
	}
	

}
