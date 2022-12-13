package com.servlet.suratjalan.entity;


public class BodyStatusSuratJalan {
	private String status;
	private String kepemilikanmobil;
	private Long idemployee_supir;
	private Long idasset;
	private Long idvendormobil;
	private String lembur;
	private Long tanggalkembali;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKepemilikanmobil() {
		return kepemilikanmobil;
	}

	public void setKepemilikanmobil(String kepemilikanmobil) {
		this.kepemilikanmobil = kepemilikanmobil;
	}

	public Long getIdemployee_supir() {
		return idemployee_supir;
	}

	public void setIdemployee_supir(Long idemployee_supir) {
		this.idemployee_supir = idemployee_supir;
	}

	public Long getIdasset() {
		return idasset;
	}

	public void setIdasset(Long idasset) {
		this.idasset = idasset;
	}

	public Long getIdvendormobil() {
		return idvendormobil;
	}

	public void setIdvendormobil(Long idvendormobil) {
		this.idvendormobil = idvendormobil;
	}

	public String getLembur() {
		return lembur;
	}

	public void setLembur(String lembur) {
		this.lembur = lembur;
	}

	public Long getTanggalkembali() {
		return tanggalkembali;
	}

	public void setTanggalkembali(Long tanggalkembali) {
		this.tanggalkembali = tanggalkembali;
	}
}
