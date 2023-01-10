package com.servlet.pengluarankasbank.entity;

public class BodyDetailPengeluaranKasBank {
	private Long idcoa;
	private String catatan;
	private Double amount;
	private Long idasset;
	private Long idinvoiceitem;
	private Long idpaymentitem;
	private Long idassetsparepart;
	private String sparepartassettype;
	public Long getIdpaymentitem() {
		return idpaymentitem;
	}
	public void setIdpaymentitem(Long idpaymentitem) {
		this.idpaymentitem = idpaymentitem;
	}
	public Long getIdassetsparepart() {
		return idassetsparepart;
	}
	public void setIdassetsparepart(Long idassetsparepart) {
		this.idassetsparepart = idassetsparepart;
	}
	public String getSparepartassettype() {
		return sparepartassettype;
	}
	public void setSparepartassettype(String sparepartassettype) {
		this.sparepartassettype = sparepartassettype;
	}
	public Long getIdinvoiceitem() {
		return idinvoiceitem;
	}
	public void setIdinvoiceitem(Long idinvoiceitem) {
		this.idinvoiceitem = idinvoiceitem;
	}
	public Long getIdcoa() {
		return idcoa;
	}
	public void setIdcoa(Long idcoa) {
		this.idcoa = idcoa;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getIdasset() {
		return idasset;
	}
	public void setIdasset(Long idasset) {
		this.idasset = idasset;
	}
}
