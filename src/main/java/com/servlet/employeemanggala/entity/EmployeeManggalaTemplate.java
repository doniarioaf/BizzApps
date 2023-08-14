package com.servlet.employeemanggala.entity;

import java.util.List;

import com.servlet.parameter.entity.ParameterData;

public class EmployeeManggalaTemplate {
	private List<ParameterData> statusKaryawanOptions;
	private List<ParameterData> jabatanOptions;
	private List<ParameterData> statusOptions;
	private List<ParameterData> jeniskelaminOptions;
	public List<ParameterData> getStatusKaryawanOptions() {
		return statusKaryawanOptions;
	}
	public void setStatusKaryawanOptions(List<ParameterData> statusKaryawanOptions) {
		this.statusKaryawanOptions = statusKaryawanOptions;
	}
	public List<ParameterData> getJabatanOptions() {
		return jabatanOptions;
	}
	public void setJabatanOptions(List<ParameterData> jabatanOptions) {
		this.jabatanOptions = jabatanOptions;
	}
	public List<ParameterData> getStatusOptions() {
		return statusOptions;
	}
	public void setStatusOptions(List<ParameterData> statusOptions) {
		this.statusOptions = statusOptions;
	}
	public List<ParameterData> getJeniskelaminOptions() {
		return jeniskelaminOptions;
	}
	public void setJeniskelaminOptions(List<ParameterData> jeniskelaminOptions) {
		this.jeniskelaminOptions = jeniskelaminOptions;
	}
}
