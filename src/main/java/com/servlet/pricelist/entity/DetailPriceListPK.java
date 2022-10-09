package com.servlet.pricelist.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailPriceListPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long idpricelist;
	private long idwarehouse;
	private long idcompany;
    private long idbranch;
    private long idinvoicetype;
    private String jalur;
	public long getIdpricelist() {
		return idpricelist;
	}
	public void setIdpricelist(long idpricelist) {
		this.idpricelist = idpricelist;
	}
	public long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(long idwarehouse) {
		this.idwarehouse = idwarehouse;
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
	public long getIdinvoicetype() {
		return idinvoicetype;
	}
	public void setIdinvoicetype(long idinvoicetype) {
		this.idinvoicetype = idinvoicetype;
	}
	public String getJalur() {
		return jalur;
	}
	public void setJalur(String jalur) {
		this.jalur = jalur;
	}
}
