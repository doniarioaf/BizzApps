package com.servlet.penerimaankasbank.entity;

import java.sql.Date;
import java.util.List;

public class PenerimaanKasBankData {
	private Long id;
	private String nodocument;
	private Date receivedate;
	private String receivefrom;
	private Long idcoa;
	private String coaName;
	private Long idbank;
	private String bankName;
	private String keterangan;
	private boolean isactive;
	private List<DetailPenerimaanKasBankData> details;
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
	public Date getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Date receivedate) {
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
	public String getCoaName() {
		return coaName;
	}
	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<DetailPenerimaanKasBankData> getDetails() {
		return details;
	}
	public void setDetails(List<DetailPenerimaanKasBankData> details) {
		this.details = details;
	}
}
