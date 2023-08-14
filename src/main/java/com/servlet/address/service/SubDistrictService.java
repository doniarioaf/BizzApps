package com.servlet.address.service;

import java.util.List;

import com.servlet.address.entity.SubDistrictData;

public interface SubDistrictService {
	List<SubDistrictData> getListSubDistrict();
	List<SubDistrictData> getListSubDistrictByDistrictId(long districtid);
}
