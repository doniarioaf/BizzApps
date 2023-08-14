package com.servlet.admin.customerproject.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerProjectPK implements Serializable{
	private long idcompany;
    private long idbranch;
    private long idproject;
    private long idcustomer;
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
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
	public long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(long idcustomer) {
		this.idcustomer = idcustomer;
	}
}
