package com.servlet.vendor.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "detail_vendor_contact", schema = "public")
public class DetailVendorContact implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailVendorContactPK detailVendorContactPK;
	private String panggilan;
	private String namakontak;
	private String nocontacthp;
	private String email;
	private String contactofficenumber;
	private String extention;
	public DetailVendorContactPK getDetailVendorContactPK() {
		return detailVendorContactPK;
	}
	public void setDetailVendorContactPK(DetailVendorContactPK detailVendorContactPK) {
		this.detailVendorContactPK = detailVendorContactPK;
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
	public String getNocontacthp() {
		return nocontacthp;
	}
	public void setNocontacthp(String nocontacthp) {
		this.nocontacthp = nocontacthp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactofficenumber() {
		return contactofficenumber;
	}
	public void setContactofficenumber(String contactofficenumber) {
		this.contactofficenumber = contactofficenumber;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
}