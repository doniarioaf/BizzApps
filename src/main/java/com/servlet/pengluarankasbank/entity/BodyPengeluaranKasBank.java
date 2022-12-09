package com.servlet.pengluarankasbank.entity;


public class BodyPengeluaranKasBank {
	private Long paymentdate;
	private Long idcoa;
	private Long idbank;
	private String paymentto;
	private String keterangan;
	private boolean isactive;
	private BodyDetailPengeluaranKasBank[] details;
	private Long idwo;
	public Long getIdwo() {
		return idwo;
	}
	public void setIdwo(Long idwo) {
		this.idwo = idwo;
	}
	public Long getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(Long paymentdate) {
		this.paymentdate = paymentdate;
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
	public String getPaymentto() {
		return paymentto;
	}
	public void setPaymentto(String paymentto) {
		this.paymentto = paymentto;
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
	public BodyDetailPengeluaranKasBank[] getDetails() {
		return details;
	}
	public void setDetails(BodyDetailPengeluaranKasBank[] details) {
		this.details = details;
	}
}
