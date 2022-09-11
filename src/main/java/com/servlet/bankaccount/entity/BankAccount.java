package com.servlet.bankaccount.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_bank_account", schema = "public")
public class BankAccount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="bankaccount_id_seq")
	private Long id;
	private String cabang;
	private String norekening;
	private Timestamp dateopen;
	private String catatan1;
	private String catatan2;
	private boolean isactive;
	private boolean isdelete;
	private Long idcompany;
	private Long idbranch;
	private String namabank;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCabang() {
		return cabang;
	}
	public void setCabang(String cabang) {
		this.cabang = cabang;
	}
	public String getNorekening() {
		return norekening;
	}
	public void setNorekening(String norekening) {
		this.norekening = norekening;
	}
	public Timestamp getDateopen() {
		return dateopen;
	}
	public void setDateopen(Timestamp dateopen) {
		this.dateopen = dateopen;
	}
	public String getCatatan1() {
		return catatan1;
	}
	public void setCatatan1(String catatan1) {
		this.catatan1 = catatan1;
	}
	public String getCatatan2() {
		return catatan2;
	}
	public void setCatatan2(String catatan2) {
		this.catatan2 = catatan2;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public boolean isIsdelete() {
		return isdelete;
	}
	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
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
	public String getNamabank() {
		return namabank;
	}
	public void setNamabank(String namabank) {
		this.namabank = namabank;
	}
}
