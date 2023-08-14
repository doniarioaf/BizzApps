package com.servlet.address.service;

import java.util.List;

import com.servlet.address.entity.PostalCode;
import com.servlet.address.entity.PostalCodeData;

public interface PostalCodeService {
	List<PostalCodeData> getListPostalCode();
	List<PostalCodeData> getListPostalCodeByPostalCode(long postalcode);
	List<PostalCodeData> getListPostalCodeByPostalCodeBySubDistrictId(long subdistrictid);
	List<PostalCodeData> getListPostalCodeByPostalCodeByCityAndProvince(long cityid,long provid);
	List<PostalCodeData> getListPostalCodeByPostalCodeByDistrictId(long districtid);
	PostalCode getById(long id);
}
