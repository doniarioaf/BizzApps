package com.servlet.suratjalan.entity;

import java.util.List;

import com.servlet.workorder.entity.WorkOrderDropDownData;

public class SuratJalanTemplate {
	private List<WorkOrderDropDownData> woOptions;

	public List<WorkOrderDropDownData> getWoOptions() {
		return woOptions;
	}

	public void setWoOptions(List<WorkOrderDropDownData> woOptions) {
		this.woOptions = woOptions;
	}
}
