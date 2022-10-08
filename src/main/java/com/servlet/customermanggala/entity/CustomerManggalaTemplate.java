package com.servlet.customermanggala.entity;

import java.util.List;

import com.servlet.address.entity.City;
import com.servlet.address.entity.Province;
import com.servlet.parameter.entity.ParameterData;
import com.servlet.warehouse.entity.WarehouseData;

public class CustomerManggalaTemplate {
	private List<City> cityOptions;
	private List<Province> provinceOptions;
	private List<ParameterData> customertypeOptions;
	private List<ParameterData> panggilanOptions;
	private List<WarehouseData> warehouseOptions ;
	public List<ParameterData> getCustomertypeOptions() {
		return customertypeOptions;
	}
	public void setCustomertypeOptions(List<ParameterData> customertypeOptions) {
		this.customertypeOptions = customertypeOptions;
	}
	public List<ParameterData> getPanggilanOptions() {
		return panggilanOptions;
	}
	public void setPanggilanOptions(List<ParameterData> panggilanOptions) {
		this.panggilanOptions = panggilanOptions;
	}
	public List<City> getCityOptions() {
		return cityOptions;
	}
	public void setCityOptions(List<City> cityOptions) {
		this.cityOptions = cityOptions;
	}
	public List<Province> getProvinceOptions() {
		return provinceOptions;
	}
	public void setProvinceOptions(List<Province> provinceOptions) {
		this.provinceOptions = provinceOptions;
	}
	public List<WarehouseData> getWarehouseOptions() {
		return warehouseOptions;
	}
	public void setWarehouseOptions(List<WarehouseData> warehouseOptions) {
		this.warehouseOptions = warehouseOptions;
	}
}
