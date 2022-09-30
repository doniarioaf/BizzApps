package com.servlet.employeemanggala.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailEmployeeManggalaInfoFamilyPK implements Serializable{
	private long idcompany;
    private long idbranch;
    private long idemployeemanggala;
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
	public long getIdemployeemanggala() {
		return idemployeemanggala;
	}
	public void setIdemployeemanggala(long idemployeemanggala) {
		this.idemployeemanggala = idemployeemanggala;
	}
	public int getCountdetail() {
		return countdetail;
	}
	public void setCountdetail(int countdetail) {
		this.countdetail = countdetail;
	}
}
