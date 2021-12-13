package com.servlet.admin.company.entity;

import java.util.List;

import com.servlet.admin.branch.entity.BranchData;

public class TemplateCompany {
	private List<BranchData> branchoptions;

	public List<BranchData> getBranchoptions() {
		return branchoptions;
	}

	public void setBranchoptions(List<BranchData> branchoptions) {
		this.branchoptions = branchoptions;
	}
}
