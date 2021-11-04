package com.servlet.admin.company.entity;

import java.util.List;

import com.servlet.admin.companybranch.entity.CompanyBranchData;

public class CompanyDataDetail {

	private Company company;
	private List<CompanyBranchData> listbranches;
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<CompanyBranchData> getListbranches() {
		return listbranches;
	}
	public void setListbranches(List<CompanyBranchData> listbranches) {
		this.listbranches = listbranches;
	}

}
