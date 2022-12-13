package com.servlet.penerimaankasbank.entity;

public class DetailPenerimaanKasBankData {
	private Long idpenerimaankasbank;
	private Long counter;
	private Long idcoa;
	private String coaname;
	private String catatan;
	private Double amount;
	private String isdownpayment;
	private Long idinvoice;
	private String nodocinvoice;
	private Long idworkorder;
	private String nodocworkorder;
	private String noaju;
	@Override
	public String toString() {
		return "DetailPenerimaanKasBankData [idpenerimaankasbank=" + idpenerimaankasbank + ", counter=" + counter
				+ ", idcoa=" + idcoa + ", coaname=" + coaname + ", catatan=" + catatan + ", amount=" + amount
				+ ", isdownpayment=" + isdownpayment + ", idinvoice=" + idinvoice + ", nodocinvoice=" + nodocinvoice
				+ ", idworkorder=" + idworkorder + ", nodocworkorder=" + nodocworkorder + ", noaju=" + noaju + "]";
	}
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public String getCoaname() {
		return coaname;
	}
	public void setCoaname(String coaname) {
		this.coaname = coaname;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getIsdownpayment() {
		return isdownpayment;
	}
	public void setIsdownpayment(String isdownpayment) {
		this.isdownpayment = isdownpayment;
	}
	public Long getIdinvoice() {
		return idinvoice;
	}
	public void setIdinvoice(Long idinvoice) {
		this.idinvoice = idinvoice;
	}
	public String getNodocinvoice() {
		return nodocinvoice;
	}
	public void setNodocinvoice(String nodocinvoice) {
		this.nodocinvoice = nodocinvoice;
	}
	public Long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
	public String getNodocworkorder() {
		return nodocworkorder;
	}
	public void setNodocworkorder(String nodocworkorder) {
		this.nodocworkorder = nodocworkorder;
	}
	public Long getCounter() {
		return counter;
	}
	public void setCounter(Long counter) {
		this.counter = counter;
	}
	public Long getIdpenerimaankasbank() {
		return idpenerimaankasbank;
	}
	public void setIdpenerimaankasbank(Long idpenerimaankasbank) {
		this.idpenerimaankasbank = idpenerimaankasbank;
	}
	public String getNoaju() {
		return noaju;
	}
	public void setNoaju(String noaju) {
		this.noaju = noaju;
	}
}
