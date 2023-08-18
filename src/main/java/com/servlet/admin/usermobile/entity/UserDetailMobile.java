package com.servlet.admin.usermobile.entity;

import java.util.List;

import com.servlet.admin.usermobilerole.entity.UserMobileRoleData;

public class UserDetailMobile {
	private UserMobile user;
	private List<UserMobileRoleData> roles;
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
	
}
