package com.servlet.vendor.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailVendorBankPK implements Serializable{
	private long idcompany;
    private long idbranch;
    private long idvendor;
    private int countdetail;
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
	public long getIdvendor() {
		return idvendor;
	}
	public void setIdvendor(long idvendor) {
		this.idvendor = idvendor;
	}
	public int getCountdetail() {
		return countdetail;
	}
	public void setCountdetail(int countdetail) {
		this.countdetail = countdetail;
	}
}
