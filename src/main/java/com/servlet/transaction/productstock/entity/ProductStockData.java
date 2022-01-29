package com.servlet.transaction.productstock.entity;

public class ProductStockData {
	private long idcompany;
	private long idproduct;
	private long stock;
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
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
}
