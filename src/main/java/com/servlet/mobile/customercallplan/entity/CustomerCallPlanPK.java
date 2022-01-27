package com.servlet.mobile.customercallplan.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerCallPlanPK implements Serializable {
	private long idcompany;
    private long idbranch;
    private long idcustomer;
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
	public long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public long getIdcallplan() {
		return idcallplan;
	}
	public void setIdcallplan(long idcallplan) {
		this.idcallplan = idcallplan;
	}
}
