package com.servlet.admin.role.entity;

import java.util.List;

public class RoleDetail {

	private Role role;
	private List<RolePermissionData> permissions;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<RolePermissionData> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<RolePermissionData> permissions) {
		this.permissions = permissions;
	}

}
