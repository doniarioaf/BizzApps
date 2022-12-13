package com.servlet.penerimaankasbank.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailPenerimaanKasBankPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idpenerimaankasbank;
	private Long counter;
	private Long idcompany;
	private Long idbranch;
	
	public Long getIdpenerimaankasbank() {
		return idpenerimaankasbank;
	}
	public void setIdpenerimaankasbank(Long idpenerimaankasbank) {
		this.idpenerimaankasbank = idpenerimaankasbank;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	public Long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(Long idcompany) {
		this.idcompany = idcompany;
	}
	public Long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(Long idbranch) {
		this.idbranch = idbranch;
	}
}
