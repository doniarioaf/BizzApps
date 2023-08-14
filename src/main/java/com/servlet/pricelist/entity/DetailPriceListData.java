package com.servlet.pricelist.entity;

public class DetailPriceListData {
	private long idpricelist;
	private long idwarehouse;
	private String warehouseName;
    private long idinvoicetype;
    private String invoicetypename;
    private String jalur;
    private String jalurname;
    private Double price;
	private String ismandatory;
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
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public long getIdinvoicetype() {
		return idinvoicetype;
	}
	public void setIdinvoicetype(long idinvoicetype) {
		this.idinvoicetype = idinvoicetype;
	}
	public String getInvoicetypename() {
		return invoicetypename;
	}
	public void setInvoicetypename(String invoicetypename) {
		this.invoicetypename = invoicetypename;
	}
	public String getJalur() {
		return jalur;
	}
	public void setJalur(String jalur) {
		this.jalur = jalur;
	}
	public String getJalurname() {
		return jalurname;
	}
	public void setJalurname(String jalurname) {
		this.jalurname = jalurname;
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
