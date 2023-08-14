package com.servlet.workorder.entity;

import java.sql.Timestamp;

public class ListDocumentWorkOrderData {
	private Long id;
	private Long idworkorder;
	private String filedocument;
	private String filename;
	private String contenttype;
	private Timestamp tanggal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
	public String getFiledocument() {
		return filedocument;
	}
	public void setFiledocument(String filedocument) {
		this.filedocument = filedocument;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
}
