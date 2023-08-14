package com.servlet.employeemanggala.entity;

import java.util.List;

public class EmployeManggalaDataListParam {
	private List<EmployeManggalaDataList> items;
	private EmployeeManggalaTemplate template;
	public List<EmployeManggalaDataList> getItems() {
		return items;
	}
	public void setItems(List<EmployeManggalaDataList> items) {
		this.items = items;
	}
	public EmployeeManggalaTemplate getTemplate() {
		return template;
	}
	public void setTemplate(EmployeeManggalaTemplate template) {
		this.template = template;
	}
}
