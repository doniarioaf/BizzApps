package com.servlet.vendor.entity;

import java.util.List;

import com.servlet.address.entity.City;
import com.servlet.address.entity.DistrictData;
import com.servlet.address.entity.Province;
import com.servlet.parameter.entity.ParameterData;
import com.servlet.vendorcategory.entity.VendorCategoryData;

public class VendorTemplate {
	private List<City> cityOptions;
	private List<Province> provinceOptions;
	private List<ParameterData> badanUsahaOptions;
	private List<ParameterData> panggilanOptions;
	private List<VendorCategoryData> vendorCategoryOptions;
	private List<DistrictData> districtOptions;
	public List<DistrictData> getDistrictOptions() {
		return districtOptions;
	}
	public void setDistrictOptions(List<DistrictData> districtOptions) {
		this.districtOptions = districtOptions;
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
	public List<ParameterData> getBadanUsahaOptions() {
		return badanUsahaOptions;
	}
	public void setBadanUsahaOptions(List<ParameterData> badanUsahaOptions) {
		this.badanUsahaOptions = badanUsahaOptions;
	}
	public List<ParameterData> getPanggilanOptions() {
		return panggilanOptions;
	}
	public void setPanggilanOptions(List<ParameterData> panggilanOptions) {
		this.panggilanOptions = panggilanOptions;
	}
	public List<VendorCategoryData> getVendorCategoryOptions() {
		return vendorCategoryOptions;
	}
	public void setVendorCategoryOptions(List<VendorCategoryData> vendorCategoryOptions) {
		this.vendorCategoryOptions = vendorCategoryOptions;
	}
}
