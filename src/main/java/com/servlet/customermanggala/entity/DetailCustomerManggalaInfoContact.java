package com.servlet.customermanggala.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "detail_customer_manggala_info_contact", schema = "public")
public class DetailCustomerManggalaInfoContact implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailCustomerManggalaInfoContactPK detailCustomerManggalaInfoContactPK;
	private String panggilan;
	private String namakontak;
	private String notelepon;
	private String email;
	private String noext;
	public DetailCustomerManggalaInfoContactPK getDetailCustomerManggalaInfoContactPK() {
		return detailCustomerManggalaInfoContactPK;
	}
	public void setDetailCustomerManggalaInfoContactPK(
			DetailCustomerManggalaInfoContactPK detailCustomerManggalaInfoContactPK) {
		this.detailCustomerManggalaInfoContactPK = detailCustomerManggalaInfoContactPK;
	}
	public String getPanggilan() {
		return panggilan;
	}
	public void setPanggilan(String panggilan) {
		this.panggilan = panggilan;
	}
	public String getNamakontak() {
		return namakontak;
	}
	public void setNamakontak(String namakontak) {
		this.namakontak = namakontak;
	}
	public String getNotelepon() {
		return notelepon;
	}
	public void setNotelepon(String notelepon) {
		this.notelepon = notelepon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNoext() {
		return noext;
	}
	public void setNoext(String noext) {
		this.noext = noext;
	}
	
	
}
