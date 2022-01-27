package com.servlet.admin.product.entity;

public class ProductData {
	private long id;
	private String nama;
	private String description;
	private String productcode;
	private String shortname;
	private String uom1;
	private String uom2;
	private String uom3;
	private String uom4;
	private double pricebuy;
	private double pricesell;
	private int conversion1to4;
	private int conversion2to4;
	private int conversion3to4;
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
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getUom1() {
		return uom1;
	}
	public void setUom1(String uom1) {
		this.uom1 = uom1;
	}
	public String getUom2() {
		return uom2;
	}
	public void setUom2(String uom2) {
		this.uom2 = uom2;
	}
	public String getUom3() {
		return uom3;
	}
	public void setUom3(String uom3) {
		this.uom3 = uom3;
	}
	public String getUom4() {
		return uom4;
	}
	public void setUom4(String uom4) {
		this.uom4 = uom4;
	}
	public double getPricebuy() {
		return pricebuy;
	}
	public void setPricebuy(double pricebuy) {
		this.pricebuy = pricebuy;
	}
	public double getPricesell() {
		return pricesell;
	}
	public void setPricesell(double pricesell) {
		this.pricesell = pricesell;
	}
	public int getConversion1to4() {
		return conversion1to4;
	}
	public void setConversion1to4(int conversion1to4) {
		this.conversion1to4 = conversion1to4;
	}
	public int getConversion2to4() {
		return conversion2to4;
	}
	public void setConversion2to4(int conversion2to4) {
		this.conversion2to4 = conversion2to4;
	}
	public int getConversion3to4() {
		return conversion3to4;
	}
	public void setConversion3to4(int conversion3to4) {
		this.conversion3to4 = conversion3to4;
	}
}
