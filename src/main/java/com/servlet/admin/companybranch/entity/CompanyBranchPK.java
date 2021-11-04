package com.servlet.admin.companybranch.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CompanyBranchPK implements Serializable {
	private long idcompany;
    private long idbranch;
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(long idbranch) {
		this.idbranch = idbranch;
	}
    
	

	

}
