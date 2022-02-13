package com.servlet.mobile.callplan.entity;

public class CallPlanListData {
	private long id;
	private String nama;
	private String description;
	private long idproject;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
}
