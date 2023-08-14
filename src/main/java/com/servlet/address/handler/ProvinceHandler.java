package com.servlet.address.handler;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.Province;
import com.servlet.address.repo.ProvinceRepo;
import com.servlet.address.service.ProvinceService;


@Service
public class ProvinceHandler implements ProvinceService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceHandler.class);
	@Autowired
	private ProvinceRepo repository;
//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Province> getListProvince() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Province getById(long id) {
		// TODO Auto-generated method stub
		Optional<Province> prov = repository.findById(id);
		if(prov.isPresent()) {
			return prov.get();
		}
		return null;
	}

}
