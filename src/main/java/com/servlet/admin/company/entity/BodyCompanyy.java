package com.servlet.admin.company.entity;


public class BodyCompanyy {
	private long idcompany;
	private long jumlahmobile;
	private long jumlahweb;
	private int waktu;
	private String type;
	private String macaddress;
	public int getWaktu() {
		return waktu;
	}
	public void setWaktu(int waktu) {
		this.waktu = waktu;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(long idcompany) {
		this.idcompany = idcompany;
	}
	public long getJumlahmobile() {
		return jumlahmobile;
	}
	public void setJumlahmobile(long jumlahmobile) {
		this.jumlahmobile = jumlahmobile;
	}
	public long getJumlahweb() {
		return jumlahweb;
	}
	public void setJumlahweb(long jumlahweb) {
		this.jumlahweb = jumlahweb;
	}
	public String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

}
