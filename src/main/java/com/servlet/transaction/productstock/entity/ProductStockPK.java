package com.servlet.transaction.productstock.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductStockPK implements Serializable{
	private long idcompany;
	private long idproduct;
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(long idproduct) {
		this.idproduct = idproduct;
	}
}
