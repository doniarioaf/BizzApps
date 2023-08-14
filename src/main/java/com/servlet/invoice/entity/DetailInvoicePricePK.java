package com.servlet.invoice.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailInvoicePricePK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idinvoice;
	private Long counter;
	public Long getIdinvoice() {
		return idinvoice;
	}
	public void setIdinvoice(Long idinvoice) {
		this.idinvoice = idinvoice;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
}
