package com.servlet.address.service;

import java.util.List;

import com.servlet.address.entity.District;
import com.servlet.address.entity.DistrictData;

public interface DistrictService {
	District getById(Long id);
	List<DistrictData> getListDistrict();
	List<DistrictData> getListDistrictByCity(long cityid);
	List<DistrictData> getListDistrictByPostalCode(long postalcode);
}
