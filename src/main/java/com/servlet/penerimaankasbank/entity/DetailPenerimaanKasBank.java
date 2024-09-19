package com.servlet.penerimaankasbank.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "detail_penerimaan_kas_bank", schema = "public")
public class DetailPenerimaanKasBank implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DetailPenerimaanKasBankPK detailPenerimaanKasBankPK;
	private Long idcoa;
	private String catatan;
	private Double amount;
	private String isdownpayment;
	private Long idinvoice;
	private Long idworkorder;
	private Double penyesuaian;
	private String keterangan_penyesuaian;

	public Double getPenyesuaian() {
		return penyesuaian;
	}

	public void setPenyesuaian(Double penyesuaian) {
		this.penyesuaian = penyesuaian;
	}

	public String getKeterangan_penyesuaian() {
		return keterangan_penyesuaian;
	}

	public void setKeterangan_penyesuaian(String keterangan_penyesuaian) {
		this.keterangan_penyesuaian = keterangan_penyesuaian;
	}

	public DetailPenerimaanKasBankPK getDetailPenerimaanKasBankPK() {
		return detailPenerimaanKasBankPK;
	}
	public void setDetailPenerimaanKasBankPK(DetailPenerimaanKasBankPK detailPenerimaanKasBankPK) {
		this.detailPenerimaanKasBankPK = detailPenerimaanKasBankPK;
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
	public String getIsdownpayment() {
		return isdownpayment;
	}
	public void setIsdownpayment(String isdownpayment) {
		this.isdownpayment = isdownpayment;
	}
	public Long getIdinvoice() {
		return idinvoice;
	}
	public void setIdinvoice(Long idinvoice) {
		this.idinvoice = idinvoice;
	}
	public Long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
}
