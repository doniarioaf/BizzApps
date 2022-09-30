package com.servlet.historyemployeemanggala.entity;

public class BodyHistoryEmployee {
	private Long idcompany;
	private Long idbranch;
	private Long idemployee;
	private String jabatan;
	private String statusemployee;
	private String gaji;
	private Long iduser;
	public Long getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(Long idcompany) {
		this.idcompany = idcompany;
	}
	public Long getIdbranch() {
		return idbranch;
	}
	public void setIdbranch(Long idbranch) {
		this.idbranch = idbranch;
	}
	public Long getIdemployee() {
		return idemployee;
	}
	public void setIdemployee(Long idemployee) {
		this.idemployee = idemployee;
	}
	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}
	public String getStatusemployee() {
		return statusemployee;
	}
	public void setStatusemployee(String statusemployee) {
		this.statusemployee = statusemployee;
	}
	public String getGaji() {
		return gaji;
	}
	public void setGaji(String gaji) {
		this.gaji = gaji;
	}
	public Long getIduser() {
		return iduser;
	}
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
}
