package com.servlet.user.entity;

import java.util.List;

import com.servlet.admin.branch.entity.UserBranchAllData;
import com.servlet.admin.role.entity.Role;

public class TemplateInternalUser {
	private List<Role> roleoptions;
	private List<UserBranchAllData> branchOptions;

	public List<UserBranchAllData> getBranchOptions() {
		return branchOptions;
	}

	public void setBranchOptions(List<UserBranchAllData> branchOptions) {
		this.branchOptions = branchOptions;
	}

	public List<Role> getRoleoptions() {
		return roleoptions;
	}

	public void setRoleoptions(List<Role> roleoptions) {
		this.roleoptions = roleoptions;
	}
}
