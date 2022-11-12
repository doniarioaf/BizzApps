package com.servlet.pengluarankasbank.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "detail_pengeluaran_kas_bank", schema = "public")
public class DetailPengeluaranKasBank implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailPengeluaranKasBankPK detailPengeluaranKasBankPK;
	private Long idcoa;
	private String catatan;
	private Double amount;
	private Long idasset;
	public DetailPengeluaranKasBankPK getDetailPengeluaranKasBankPK() {
		return detailPengeluaranKasBankPK;
	}
	public void setDetailPengeluaranKasBankPK(DetailPengeluaranKasBankPK detailPengeluaranKasBankPK) {
		this.detailPengeluaranKasBankPK = detailPengeluaranKasBankPK;
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
