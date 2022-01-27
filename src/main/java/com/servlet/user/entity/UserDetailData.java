package com.servlet.user.entity;

import java.util.List;

import com.servlet.admin.userappsrole.entity.UserAppsRoleData;

public class UserDetailData {
//	private UserApps user;
	private UserDataDetail user;
	private List<UserAppsRoleData> roles;
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
