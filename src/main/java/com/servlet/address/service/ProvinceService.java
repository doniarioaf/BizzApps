package com.servlet.address.service;

import java.util.List;

import com.servlet.address.entity.Province;

public interface ProvinceService {
	List<Province> getListProvince();
	Province getById(long id);
}
