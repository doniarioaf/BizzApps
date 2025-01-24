package com.servlet.parameter.service;

import java.util.List;

import com.servlet.address.entity.City;
import com.servlet.parameter.entity.ParameterData;

public interface ParameterService {
	List<ParameterData> getListParameterByGrup(String grup);
	List<ParameterData> getListParameterAll();
	ParameterData getListParameterByCode(String code);
}
