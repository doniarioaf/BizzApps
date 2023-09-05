package com.servlet.invoice.entity;

import java.sql.Date;

public class InvoiceDPData {
	private Long id;
	private String nodocument;
	private Date tanggal;
	private Double totalinvoice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNodocument() {
		return nodocument;
	}
	public void setNodocument(String nodocument) {
		this.nodocument = nodocument;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	public Double getTotalinvoice() {
		return totalinvoice;
	}
	public void setTotalinvoice(Double totalinvoice) {
		this.totalinvoice = totalinvoice;
	}
}
