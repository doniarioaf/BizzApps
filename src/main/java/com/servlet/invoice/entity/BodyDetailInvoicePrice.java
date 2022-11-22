package com.servlet.invoice.entity;

public class BodyDetailInvoicePrice {
	private Long idwarehouse;
	private Long idinvoicetype;
	private String jalur;
	private Double price;
	private String ismandatory;
	private Long idpricelist;
	private Long qty;
	private Double diskon;
	private Double subtotal;
	public Long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(Long idwarehouse) {
		this.idwarehouse = idwarehouse;
	}
	public Long getIdinvoicetype() {
		return idinvoicetype;
	}
	public void setIdinvoicetype(Long idinvoicetype) {
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
	public Long getIdpricelist() {
		return idpricelist;
	}
	public void setIdpricelist(Long idpricelist) {
		this.idpricelist = idpricelist;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
	public Double getDiskon() {
		return diskon;
	}
	public void setDiskon(Double diskon) {
		this.diskon = diskon;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
}
