package com.servlet.pengluarankasbank.entity;

public class DetailPengeluaranKasBankData {
	private Long idcoa;
	private String coaName;
	private String catatan;
	private Double amount;
	private Long idasset;
	private String assetName;
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
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
}
