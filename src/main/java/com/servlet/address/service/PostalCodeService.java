package com.servlet.address.service;

import java.util.List;

import com.servlet.address.entity.PostalCodeData;

public interface PostalCodeService {
	List<PostalCodeData> getListPostalCode();
	List<PostalCodeData> getListPostalCodeByPostalCode(long postalcode);
	List<PostalCodeData> getListPostalCodeByPostalCodeBySubDistrictId(long subdistrictid);
}
