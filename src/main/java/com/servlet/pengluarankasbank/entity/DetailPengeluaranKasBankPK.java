package com.servlet.pengluarankasbank.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DetailPengeluaranKasBankPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long idpengeluarankasbank;
	private Long counter;
	private Long idcompany;
	private Long idbranch;
	public Long getIdpengeluarankasbank() {
		return idpengeluarankasbank;
	}
	public void setIdpengeluarankasbank(Long idpengeluarankasbank) {
		this.idpengeluarankasbank = idpengeluarankasbank;
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
