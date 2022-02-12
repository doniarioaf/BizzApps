package com.servlet.mobile.project.entity;

public class BodyProject {
	private String nama;
	private String description;
	private String projectnumber;
	private Long[] customers;
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
	public String getProjectnumber() {
		return projectnumber;
	}
	public void setProjectnumber(String projectnumber) {
		this.projectnumber = projectnumber;
	}
	public Long[] getCustomers() {
		return customers;
	}
	public void setCustomers(Long[] customers) {
		this.customers = customers;
	}
}
