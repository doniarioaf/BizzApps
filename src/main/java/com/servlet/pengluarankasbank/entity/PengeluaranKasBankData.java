package com.servlet.pengluarankasbank.entity;

import java.sql.Date;
import java.util.List;

public class PengeluaranKasBankData {
	private Long id;
	private String nodocument;
	private Date paymentdate;
	private Long idcoa;
	private String coaName;
	private Long idbank;
	private String bankName;
	private String paymentto;
	private String keterangan;
	private boolean isactive;
	private PengeluaranKasBankTemplate template;
	private List<DetailPengeluaranKasBankData> details;
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
	public Date getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public String getCoaName() {
		return coaName;
	}
	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}
	public Long getIdbank() {
		return idbank;
	}
	public void setIdbank(Long idbank) {
		this.idbank = idbank;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public PengeluaranKasBankTemplate getTemplate() {
		return template;
	}
	public void setTemplate(PengeluaranKasBankTemplate template) {
		this.template = template;
	}
	public List<DetailPengeluaranKasBankData> getDetails() {
		return details;
	}
	public void setDetails(List<DetailPengeluaranKasBankData> details) {
		this.details = details;
	}
}
