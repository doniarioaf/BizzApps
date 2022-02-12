package com.servlet.mobile.project.entity;

import java.util.List;

import com.servlet.admin.customerproject.entity.CustomerProjectData;

public class ProjectDetailData {
	private long id;
	private String nama;
	private String description;
	private String projectnumber;
	private List<CustomerProjectData> customers;
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
	public String getProjectnumber() {
		return projectnumber;
	}
	public void setProjectnumber(String projectnumber) {
		this.projectnumber = projectnumber;
	}
	public List<CustomerProjectData> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerProjectData> customers) {
		this.customers = customers;
	}
}
