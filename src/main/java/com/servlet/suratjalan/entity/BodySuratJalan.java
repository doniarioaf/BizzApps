package com.servlet.suratjalan.entity;


public class BodySuratJalan {
	private Long tanggal;
	private Long idworkorder;
	private Long idcustomer;
	private String keterangan;
	private Long idwarehouse;
	private String catatan;
	private String nocantainer;
	private boolean isactive;
	
	public Long getTanggal() {
		return tanggal;
	}
	public void setTanggal(Long tanggal) {
		this.tanggal = tanggal;
	}
	public Long getIdworkorder() {
		return idworkorder;
	}
	public void setIdworkorder(Long idworkorder) {
		this.idworkorder = idworkorder;
	}
	public Long getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Long idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public Long getIdwarehouse() {
		return idwarehouse;
	}
	public void setIdwarehouse(Long idwarehouse) {
		this.idwarehouse = idwarehouse;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public String getNocantainer() {
		return nocantainer;
	}
	public void setNocantainer(String nocantainer) {
		this.nocantainer = nocantainer;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
