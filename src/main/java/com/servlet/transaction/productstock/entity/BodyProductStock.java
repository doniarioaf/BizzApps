package com.servlet.transaction.productstock.entity;

public class BodyProductStock {
	private long idproduct;
	private long stock;
	private String reason;
	public long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(long idproduct) {
		this.idproduct = idproduct;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
