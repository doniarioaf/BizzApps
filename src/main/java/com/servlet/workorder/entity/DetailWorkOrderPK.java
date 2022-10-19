package com.servlet.workorder.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailWorkOrderPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idworkorder;
	private Long idpartai;
	private Long idcompany;
    private Long idbranch;
	public long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
	public long getIdpartai() {
		return idpartai;
	}
	public void setIdpartai(Long idpartai) {
		this.idpartai = idpartai;
	}
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(Long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(Long idbranch) {
		this.idbranch = idbranch;
	}
}
