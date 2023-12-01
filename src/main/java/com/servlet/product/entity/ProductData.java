package com.servlet.product.entity;

public class ProductData {
	private Long id;
	private String code;
	private String nama;
	private Double hargabeli;
	private Double hargajual;
	private Double conv1to4;
	private Double conv2to4;
	private Double conv3to4;
	private Double conv4to4;
	private String image;
	private String barcode;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public Double getHargabeli() {
		return hargabeli;
	}
	public void setHargabeli(Double hargabeli) {
		this.hargabeli = hargabeli;
	}
	public Double getHargajual() {
		return hargajual;
	}
	public void setHargajual(Double hargajual) {
		this.hargajual = hargajual;
	}
	public Double getConv1to4() {
		return conv1to4;
	}
	public void setConv1to4(Double conv1to4) {
		this.conv1to4 = conv1to4;
	}
	public Double getConv2to4() {
		return conv2to4;
	}
	public void setConv2to4(Double conv2to4) {
		this.conv2to4 = conv2to4;
	}
	public Double getConv3to4() {
		return conv3to4;
	}
	public void setConv3to4(Double conv3to4) {
		this.conv3to4 = conv3to4;
	}
	public Double getConv4to4() {
		return conv4to4;
	}
	public void setConv4to4(Double conv4to4) {
		this.conv4to4 = conv4to4;
	}
}
