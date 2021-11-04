package com.servlet.admin.companybranch.service;

import java.util.Collection;
import java.util.List;

import com.servlet.admin.companybranch.entity.CompanyBranch;
import com.servlet.admin.companybranch.entity.CompanyBranchData;
import com.servlet.admin.companybranch.entity.CompanyBranchPK;

public interface CompanyBranchService {
	Object saveCompanyBranch(CompanyBranchPK companybranch);
	Object saveCompanyBranchList(List<CompanyBranch> listcompanybranch);
	Collection<CompanyBranchData> getListCompanyBranch(long idcompany);
	Object deleteAllCompanyBranchByListPK(List<CompanyBranchPK> listPK);
}
