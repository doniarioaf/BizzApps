package com.servlet.invoice.entity;

public class DetailInvoicePriceData {
	private Long idwarehouse;
	private String warehouseName;
	private Long idinvoicetype;
	private String invoicetypename;
	private String jalur;
	private Double price;
	private String ismandatory;
	private Long idpricelist;
	private String nodocumentpricelist;
	private Long qty;
	private Double diskon;
	private Double subtotal;
	private Long idpengeluarankasbank;
	private String nodocumentpengeluaran;
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getNodocumentpengeluaran() {
		return nodocumentpengeluaran;
	}
	public void setNodocumentpengeluaran(String nodocumentpengeluaran) {
		this.nodocumentpengeluaran = nodocumentpengeluaran;
	}
	public Long getIdpengeluarankasbank() {
		return idpengeluarankasbank;
	}
	public void setIdpengeluarankasbank(Long idpengeluarankasbank) {
		this.idpengeluarankasbank = idpengeluarankasbank;
	}
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
	public String getNodocumentpricelist() {
		return nodocumentpricelist;
	}
	public void setNodocumentpricelist(String nodocumentpricelist) {
		this.nodocumentpricelist = nodocumentpricelist;
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
