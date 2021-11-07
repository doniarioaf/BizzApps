package com.servlet.mobile.usermobilecallplan.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserMobileCallPlanPK implements Serializable{
	private long idcompany;
    private long idbranch;
    private long idusermobile;
    private long idcallplan;
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
	public long getIdusermobile() {
		return idusermobile;
	}
	public void setIdusermobile(long idusermobile) {
		this.idusermobile = idusermobile;
	}
	public long getIdcallplan() {
		return idcallplan;
	}
	public void setIdcallplan(long idcallplan) {
		this.idcallplan = idcallplan;
	}
}
