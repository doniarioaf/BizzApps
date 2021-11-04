package com.servlet.admin.companybranch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servlet.admin.companybranch.entity.CompanyBranch;
import com.servlet.admin.companybranch.entity.CompanyBranchPK;

@Repository("CompanyBranchRepo")
public interface CompanyBranchRepo extends JpaRepository<CompanyBranch, CompanyBranchPK>{
	

}
