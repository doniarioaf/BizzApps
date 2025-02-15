package com.servlet.penerimaankasbank.entity;


public class BodyDetailPenerimaanKasBank {
	private Long idcoa;
	private String catatan;
	private Double amount;
	private String isdownpayment;
	private Long idinvoice;
	private Long idworkorder;
	private Double penyesuaian;
	private String keterangan_penyesuaian;
	private Double nilaijasa;
	private Double nilaireimbursement;
	private Double nilaibuktipotong;
	private String nobuktipotong;
	private Long tanggalbuktipotong;
	private Double nilaippn;

	public Double getNilaijasa() {
		return nilaijasa;
	}

	public void setNilaijasa(Double nilaijasa) {
		this.nilaijasa = nilaijasa;
	}

	public Double getNilaireimbursement() {
		return nilaireimbursement;
	}

	public void setNilaireimbursement(Double nilaireimbursement) {
		this.nilaireimbursement = nilaireimbursement;
	}

	public Double getNilaibuktipotong() {
		return nilaibuktipotong;
	}

	public void setNilaibuktipotong(Double nilaibuktipotong) {
		this.nilaibuktipotong = nilaibuktipotong;
	}

	public String getNobuktipotong() {
		return nobuktipotong;
	}

	public void setNobuktipotong(String nobuktipotong) {
		this.nobuktipotong = nobuktipotong;
	}

	public Long getTanggalbuktipotong() {
		return tanggalbuktipotong;
	}

	public void setTanggalbuktipotong(Long tanggalbuktipotong) {
		this.tanggalbuktipotong = tanggalbuktipotong;
	}

	public Double getNilaippn() {
		return nilaippn;
	}

	public void setNilaippn(Double nilaippn) {
		this.nilaippn = nilaippn;
	}

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
