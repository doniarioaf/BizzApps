package com.servlet.warehouse.entity;

import java.util.List;

import com.servlet.address.entity.City;
import com.servlet.address.entity.Province;

public class WarehouseTemplate {
	private List<City> cityOptions;
	private List<Province> provinceOptions;
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
}
