package com.servlet.suratjalan.entity;

import java.util.List;

import com.servlet.parameter.entity.ParameterData;
import com.servlet.workorder.entity.WorkOrderDropDownData;

public class SuratJalanTemplate {
	private List<WorkOrderDropDownData> woOptions;
	private List<ParameterData> statusSJOptions;

	public List<WorkOrderDropDownData> getWoOptions() {
		return woOptions;
	}

	public void setWoOptions(List<WorkOrderDropDownData> woOptions) {
		this.woOptions = woOptions;
	}

	public List<ParameterData> getStatusSJOptions() {
		return statusSJOptions;
	}

	public void setStatusSJOptions(List<ParameterData> statusSJOptions) {
		this.statusSJOptions = statusSJOptions;
	}
}
