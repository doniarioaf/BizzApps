package com.servlet.customermanggala.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.service.CityService;
import com.servlet.address.service.ProvinceService;
import com.servlet.customermanggala.entity.CustomerManggalaTemplate;
import com.servlet.customermanggala.service.CustomerManggalaService;

@Service
public class CustomerManggalaHandler implements CustomerManggalaService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CityService cityService;
	@Autowired
	private ProvinceService provinceService;
	
	@Override
	public CustomerManggalaTemplate customerManggalaTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		CustomerManggalaTemplate data = new CustomerManggalaTemplate();
		data.setCityOptions(cityService.getListCity());
		data.setProvinceOptions(provinceService.getListProvince());
		return data;
	}
	
}
