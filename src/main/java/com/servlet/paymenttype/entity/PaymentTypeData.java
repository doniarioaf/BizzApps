package com.servlet.paymenttype.entity;

public class PaymentTypeData {
	private Long id;
	private String paymenttype;
	private String paymenttypename;
	private String nama;
	private boolean isactive;
	private PaymentTypeTemplate template;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getPaymenttypename() {
		return paymenttypename;
	}
	public void setPaymenttypename(String paymenttypename) {
		this.paymenttypename = paymenttypename;
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
	public PaymentTypeTemplate getTemplate() {
		return template;
	}
	public void setTemplate(PaymentTypeTemplate template) {
		this.template = template;
	}
	
}
