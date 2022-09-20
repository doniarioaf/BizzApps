package com.servlet.customermanggala.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "detail_customer_manggala_info_gudang", schema = "public")
public class DetailCustomerManggalaInfoGudang implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailCustomerManggalaInfoGudangPK detailCustomerManggalaInfoGudangPK;
	private String namagudang;
	private String areakirim;
	private String alamatgudang;
	private String ancerancer;
	private String kontakgudang;
	private String hpkontakgudang;
	private String note;
	public DetailCustomerManggalaInfoGudangPK getDetailCustomerManggalaInfoGudangPK() {
		return detailCustomerManggalaInfoGudangPK;
	}
	public void setDetailCustomerManggalaInfoGudangPK(
			DetailCustomerManggalaInfoGudangPK detailCustomerManggalaInfoGudangPK) {
		this.detailCustomerManggalaInfoGudangPK = detailCustomerManggalaInfoGudangPK;
	}
	public String getNamagudang() {
		return namagudang;
	}
	public void setNamagudang(String namagudang) {
		this.namagudang = namagudang;
	}
	public String getAreakirim() {
		return areakirim;
	}
	public void setAreakirim(String areakirim) {
		this.areakirim = areakirim;
	}
	public String getAlamatgudang() {
		return alamatgudang;
	}
	public void setAlamatgudang(String alamatgudang) {
		this.alamatgudang = alamatgudang;
	}
	public String getAncerancer() {
		return ancerancer;
	}
	public void setAncerancer(String ancerancer) {
		this.ancerancer = ancerancer;
	}
	public String getKontakgudang() {
		return kontakgudang;
	}
	public void setKontakgudang(String kontakgudang) {
		this.kontakgudang = kontakgudang;
	}
	public String getHpkontakgudang() {
		return hpkontakgudang;
	}
	public void setHpkontakgudang(String hpkontakgudang) {
		this.hpkontakgudang = hpkontakgudang;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
