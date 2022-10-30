package com.servlet.suratjalan.entity;

import java.util.List;

import com.servlet.employeemanggala.entity.EmployeManggalaDataList;
import com.servlet.parameter.entity.ParameterData;
import com.servlet.vendor.entity.VendorListData;

public class PenandaanSuratJalanTemplate {
	private List<ParameterData> statusSJOptions;
	private List<ParameterData> kepimilikanMobilOptions;
	private List<ParameterData> chooseYesNoOptions;
	private List<EmployeManggalaDataList> supirOptions;
	private List<VendorListData> vendorOptions ;
	public List<ParameterData> getStatusSJOptions() {
		return statusSJOptions;
	}
	public void setStatusSJOptions(List<ParameterData> statusSJOptions) {
		this.statusSJOptions = statusSJOptions;
	}
	public List<ParameterData> getKepimilikanMobilOptions() {
		return kepimilikanMobilOptions;
	}
	public void setKepimilikanMobilOptions(List<ParameterData> kepimilikanMobilOptions) {
		this.kepimilikanMobilOptions = kepimilikanMobilOptions;
	}
	public List<ParameterData> getChooseYesNoOptions() {
		return chooseYesNoOptions;
	}
	public void setChooseYesNoOptions(List<ParameterData> chooseYesNoOptions) {
		this.chooseYesNoOptions = chooseYesNoOptions;
	}
	public List<EmployeManggalaDataList> getSupirOptions() {
		return supirOptions;
	}
	public void setSupirOptions(List<EmployeManggalaDataList> supirOptions) {
		this.supirOptions = supirOptions;
	}
	public List<VendorListData> getVendorOptions() {
		return vendorOptions;
	}
	public void setVendorOptions(List<VendorListData> vendorOptions) {
		this.vendorOptions = vendorOptions;
	}
}
