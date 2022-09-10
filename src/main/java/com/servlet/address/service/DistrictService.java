package com.servlet.address.service;

import java.util.List;

import com.servlet.address.entity.DistrictData;

public interface DistrictService {
	List<DistrictData> getListDistrict();
	List<DistrictData> getListDistrictByCity(long cityid);
}
