package com.servlet.invoicetype.entity;

public class InvoiceTypeData {
	private Long id;
	private String invoicetype;
	private String invoicetypename;
	private String nama;
	private Long idcoa;
	private String coaName;
	private boolean isactive;
	private InvoiceTypeTemplate template;
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public String getCoaName() {
		return coaName;
	}
	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInvoicetype() {
		return invoicetype;
	}
	public void setInvoicetype(String invoicetype) {
		this.invoicetype = invoicetype;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public InvoiceTypeTemplate getTemplate() {
		return template;
	}
	public void setTemplate(InvoiceTypeTemplate template) {
		this.template = template;
	}
	public String getInvoicetypename() {
		return invoicetypename;
	}
	public void setInvoicetypename(String invoicetypename) {
		this.invoicetypename = invoicetypename;
	}
}
