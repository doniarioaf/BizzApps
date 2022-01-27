package com.servlet.user.entity;

import java.util.List;

import com.servlet.admin.role.entity.Role;

public class TemplateInternalUser {
	private List<Role> roleoptions;

	public List<Role> getRoleoptions() {
		return roleoptions;
	}

	public void setRoleoptions(List<Role> roleoptions) {
		this.roleoptions = roleoptions;
	}
}
