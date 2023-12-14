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
	private String noPolisi;
	private String vendormobilname;
	private String warehouseCity;
	private String warehouseKecamatan;
	public String getWarehouseCity() {
		return warehouseCity;
	}
	public void setWarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}
	public String getWarehouseKecamatan() {
		return warehouseKecamatan;
	}
	public void setWarehouseKecamatan(String warehouseKecamatan) {
		this.warehouseKecamatan = warehouseKecamatan;
	}
	public String getVendormobilname() {
		return vendormobilname;
	}
	public void setVendormobilname(String vendormobilname) {
		this.vendormobilname = vendormobilname;
	}
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
	public String getNoPolisi() {
		return noPolisi;
	}
	public void setNoPolisi(String noPolisi) {
		this.noPolisi = noPolisi;
	}
	
}
