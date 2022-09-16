package com.servlet.customermanggala.entity;

import java.util.List;

import com.servlet.address.entity.City;
import com.servlet.address.entity.Province;
import com.servlet.parameter.entity.ParameterData;

public class CustomerManggalaTemplate {
	private List<City> cityOptions;
	private List<Province> provinceOptions;
	private List<ParameterData> customertypeOptions;
	private List<ParameterData> panggilanOptions;
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
}
