package com.servlet.report.entity;

import java.util.List;

import com.servlet.asset.entity.AssetData;
import com.servlet.employeemanggala.entity.EmployeManggalaDataList;

public class ReportSummaryKegiatanTructTemplate {
	private List<AssetData> assetOptions;
	private List<EmployeManggalaDataList> driverOptions;
	public List<AssetData> getAssetOptions() {
		return assetOptions;
	}
	public void setAssetOptions(List<AssetData> assetOptions) {
		this.assetOptions = assetOptions;
	}
	public List<EmployeManggalaDataList> getDriverOptions() {
		return driverOptions;
	}
	public void setDriverOptions(List<EmployeManggalaDataList> driverOptions) {
		this.driverOptions = driverOptions;
	}
}
