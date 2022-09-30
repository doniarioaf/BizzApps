package com.servlet.vendor.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContactPK;

@Entity
@Table(name = "detail_vendor_bank", schema = "public")
public class DetailVendorBank implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailVendorBankPK detailVendorBankPK;
	private String norek;
	private String atasnama;
	private String bank;
	public DetailVendorBankPK getDetailVendorBankPK() {
		return detailVendorBankPK;
	}
	public void setDetailVendorBankPK(DetailVendorBankPK detailVendorBankPK) {
		this.detailVendorBankPK = detailVendorBankPK;
	}
	public String getNorek() {
		return norek;
	}
	public void setNorek(String norek) {
		this.norek = norek;
	}
	public String getAtasnama() {
		return atasnama;
	}
	public void setAtasnama(String atasnama) {
		this.atasnama = atasnama;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
}
