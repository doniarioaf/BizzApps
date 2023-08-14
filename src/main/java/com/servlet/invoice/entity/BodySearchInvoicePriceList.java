package com.servlet.invoice.entity;

public class BodySearchInvoicePriceList {
	private Long idcustomer;
	private Long idwarehouse;
	private String idinvoicetype;
	private String jalur;
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public Long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(Long idwarehouse) {
		this.idwarehouse = idwarehouse;
	}
	public String getIdinvoicetype() {
		return idinvoicetype;
	}
	public void setIdinvoicetype(String idinvoicetype) {
		this.idinvoicetype = idinvoicetype;
	}
	public String getJalur() {
		return jalur;
	}
	public void setJalur(String jalur) {
		this.jalur = jalur;
	}
}
