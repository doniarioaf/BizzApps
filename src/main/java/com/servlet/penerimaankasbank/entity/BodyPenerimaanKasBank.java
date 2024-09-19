package com.servlet.penerimaankasbank.entity;


public class BodyPenerimaanKasBank {
	private Long receivedate;
	private String receivefrom;
	private Long idcoa;
	private Long idbank;
	private String keterangan;
	private boolean isactive;
	private Long idcustomer;
	private Long idvendor;
	private Long idemployee;
	private String idreceivetype;
	private BodyDetailPenerimaanKasBank[] details;

	private Long idwo;
	public Long getIdwo() {
		return idwo;
	}

	public void setIdwo(Long idwo) {
		this.idwo = idwo;
	}

	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public Long getIdvendor() {
		return idvendor;
	}
	public void setIdvendor(Long idvendor) {
		this.idvendor = idvendor;
	}
	public Long getIdemployee() {
		return idemployee;
	}
	public void setIdemployee(Long idemployee) {
		this.idemployee = idemployee;
	}
	public String getIdreceivetype() {
		return idreceivetype;
	}
	public void setIdreceivetype(String idreceivetype) {
		this.idreceivetype = idreceivetype;
	}
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
