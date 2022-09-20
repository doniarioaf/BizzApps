package com.servlet.customermanggala.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailCustomerManggalaInfoGudangPK implements Serializable{
	private long idcompany;
    private long idbranch;
    private long idcustomermanggala;
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
	public long getIdcustomermanggala() {
		return idcustomermanggala;
	}
	public void setIdcustomermanggala(long idcustomermanggala) {
		this.idcustomermanggala = idcustomermanggala;
	}
	public int getCountdetail() {
		return countdetail;
	}
	public void setCountdetail(int countdetail) {
		this.countdetail = countdetail;
	}
}
