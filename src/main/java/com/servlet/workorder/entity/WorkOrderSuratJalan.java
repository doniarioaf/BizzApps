package com.servlet.workorder.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class WorkOrderSuratJalan {
	private Long idSuratJalan;
	private String nodocument;
	private Timestamp tanggal;
	private Date tanggalkembali;
	private String lembur;
	private String namaSupir;
	private String kepemilikanmobil;
	public Long getIdSuratJalan() {
		return idSuratJalan;
	}
	public void setIdSuratJalan(Long idSuratJalan) {
		this.idSuratJalan = idSuratJalan;
	}
	public String getNodocument() {
		return nodocument;
	}
	public void setNodocument(String nodocument) {
		this.nodocument = nodocument;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	public Date getTanggalkembali() {
		return tanggalkembali;
	}
	public void setTanggalkembali(Date tanggalkembali) {
		this.tanggalkembali = tanggalkembali;
	}
	public String getLembur() {
		return lembur;
	}
	public void setLembur(String lembur) {
		this.lembur = lembur;
	}
	public String getNamaSupir() {
		return namaSupir;
	}
	public void setNamaSupir(String namaSupir) {
		this.namaSupir = namaSupir;
	}
	public String getKepemilikanmobil() {
		return kepemilikanmobil;
	}
	public void setKepemilikanmobil(String kepemilikanmobil) {
		this.kepemilikanmobil = kepemilikanmobil;
	}
	
}
