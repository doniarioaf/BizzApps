package com.servlet.admin.companybranch.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_company_branch", schema = "public")
public class CompanyBranch {
	
	@EmbeddedId
    private CompanyBranchPK companyBranchPK;

	public CompanyBranchPK getCompanyBranchPK() {
		return companyBranchPK;
	}

	public void setCompanyBranchPK(CompanyBranchPK companyBranchPK) {
		this.companyBranchPK = companyBranchPK;
	}

	

}
