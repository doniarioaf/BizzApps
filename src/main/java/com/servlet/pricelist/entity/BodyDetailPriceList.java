package com.servlet.pricelist.entity;

public class BodyDetailPriceList {
	private long idwarehouse;
    private long idinvoicetype;
    private String jalur;
    private Double price;
	private String ismandatory;
	public long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(long idwarehouse) {
		this.idwarehouse = idwarehouse;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getIsmandatory() {
		return ismandatory;
	}
	public void setIsmandatory(String ismandatory) {
		this.ismandatory = ismandatory;
	}
}
