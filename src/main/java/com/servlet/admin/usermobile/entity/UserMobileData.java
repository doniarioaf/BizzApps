package com.servlet.admin.usermobile.entity;

import java.util.List;

public class UserMobileData {
	private List<String> permissions;
	private String token;
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
