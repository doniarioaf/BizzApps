package com.servlet.runningnumber.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RunningNumberPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private long idcompany;
    private long idbranch;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
