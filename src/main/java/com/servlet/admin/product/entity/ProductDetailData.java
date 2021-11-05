package com.servlet.admin.product.entity;

public class ProductDetailData {
	private long id;
	private String nama;
	private String description;
	private long idproducttype;
	private String nameproducttype;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getIdproducttype() {
		return idproducttype;
	}
	public void setIdproducttype(long idproducttype) {
		this.idproducttype = idproducttype;
	}
	public String getNameproducttype() {
		return nameproducttype;
	}
	public void setNameproducttype(String nameproducttype) {
		this.nameproducttype = nameproducttype;
	}
}
