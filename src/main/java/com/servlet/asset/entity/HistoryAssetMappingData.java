package com.servlet.asset.entity;

import java.sql.Timestamp;

public class HistoryAssetMappingData {
	private Long id;
	private Long idasset;
	private Long iduser;
	private Long before;
	private Long after;
	private String type;
	private Timestamp tanggal;
	private String kodeasset;
	private String assettype;
	private String kepala_nama;
	private String buntut_nama;
	private String sparepartkepala_nama;
	private String sparepartbuntut_nama;
	private Long idassetmapping;
	
	public Long getIdassetmapping() {
		return idassetmapping;
	}
	public void setIdassetmapping(Long idassetmapping) {
		this.idassetmapping = idassetmapping;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdasset() {
		return idasset;
	}
	public void setIdasset(Long idasset) {
		this.idasset = idasset;
	}
	public Long getIduser() {
		return iduser;
	}
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
	public Long getBefore() {
		return before;
	}
	public void setBefore(Long before) {
		this.before = before;
	}
	public Long getAfter() {
		return after;
	}
	public void setAfter(Long after) {
		this.after = after;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Timestamp getTanggal() {
		return tanggal;
	}
	public void setTanggal(Timestamp tanggal) {
		this.tanggal = tanggal;
	}
	public String getKodeasset() {
		return kodeasset;
	}
	public void setKodeasset(String kodeasset) {
		this.kodeasset = kodeasset;
	}
	public String getAssettype() {
		return assettype;
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public String getKepala_nama() {
		return kepala_nama;
	}
	public void setKepala_nama(String kepala_nama) {
		this.kepala_nama = kepala_nama;
	}
	public String getBuntut_nama() {
		return buntut_nama;
	}
	public void setBuntut_nama(String buntut_nama) {
		this.buntut_nama = buntut_nama;
	}
	public String getSparepartkepala_nama() {
		return sparepartkepala_nama;
	}
	public void setSparepartkepala_nama(String sparepartkepala_nama) {
		this.sparepartkepala_nama = sparepartkepala_nama;
	}
	public String getSparepartbuntut_nama() {
		return sparepartbuntut_nama;
	}
	public void setSparepartbuntut_nama(String sparepartbuntut_nama) {
		this.sparepartbuntut_nama = sparepartbuntut_nama;
	}
}
