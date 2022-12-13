package com.servlet.penerimaankasbank.entity;


public class BodyPenerimaanKasBank {
	private Long receivedate;
	private String receivefrom;
	private Long idcoa;
	private Long idbank;
	private String keterangan;
	private boolean isactive;
	private BodyDetailPenerimaanKasBank[] details;
	
	public Long getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Long receivedate) {
		this.receivedate = receivedate;
	}
	public String getReceivefrom() {
		return receivefrom;
	}
	public void setReceivefrom(String receivefrom) {
		this.receivefrom = receivefrom;
	}
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public Long getIdbank() {
		return idbank;
	}
	public void setIdbank(Long idbank) {
		this.idbank = idbank;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public BodyDetailPenerimaanKasBank[] getDetails() {
		return details;
	}
	public void setDetails(BodyDetailPenerimaanKasBank[] details) {
		this.details = details;
	}
}
