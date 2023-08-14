package com.servlet.mobile.callplan.entity;

public class BodyCallPlan {
	private String nama;
	private String description;
	private Long[] customers;
	private long idproject;
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
	public Long[] getCustomers() {
		return customers;
	}
	public void setCustomers(Long[] customers) {
		this.customers = customers;
	}
	public long getIdproject() {
		return idproject;
	}
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
}
