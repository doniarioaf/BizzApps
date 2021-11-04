package com.servlet.admin.branch.service;

import java.util.Collection;
import java.util.List;

import com.servlet.admin.branch.entity.BodyBranch;
import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.entity.BranchData;

public interface BranchService {
	List<Branch> getListBranchActive();
	List<Branch> getAllListBranch();
	Branch getBranchByID(long id);
	BranchData saveBranch(BodyBranch branch);
	BranchData updateBranch(long id,BodyBranch branch);
	Collection<Branch> getListBranchActiveJdbc();
	List<BranchData> getAllListBranchNotExistInCompany();
}
