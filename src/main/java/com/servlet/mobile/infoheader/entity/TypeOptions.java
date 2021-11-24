package com.servlet.mobile.infoheader.entity;

public class TypeOptions {
	String id;
	String nama;
	
	public TypeOptions(String id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
}
