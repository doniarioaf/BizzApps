package com.servlet.mobile.callplan.entity;

import java.util.List;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;

public class CallPlanDetailData {
	private long id;
	private String nama;
	private String description;
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
}
