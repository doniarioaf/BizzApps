package com.servlet.mobile.callplan.entity;

import java.util.List;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;

public class CallPlanDetailData {
	private long id;
	private String nama;
	private String description;
	private long idproject;
	private String projectname;
	private List<CustomerCallPlanData> customers;
	
	public List<CustomerCallPlanData> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerCallPlanData> customers) {
		this.customers = customers;
	}
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
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
}
