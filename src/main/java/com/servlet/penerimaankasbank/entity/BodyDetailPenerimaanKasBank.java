package com.servlet.penerimaankasbank.entity;

public class BodyDetailPenerimaanKasBank {
	private Long idcoa;
	private String catatan;
	private Double amount;
	private String isdownpayment;
	private Long idinvoice;
	private Long idworkorder;
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getIsdownpayment() {
		return isdownpayment;
	}
	public void setIsdownpayment(String isdownpayment) {
		this.isdownpayment = isdownpayment;
	}
	public Long getIdinvoice() {
		return idinvoice;
	}
	public void setIdinvoice(Long idinvoice) {
		this.idinvoice = idinvoice;
	}
	public Long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
	
	
}
