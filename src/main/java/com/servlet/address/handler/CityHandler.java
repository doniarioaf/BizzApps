package com.servlet.address.handler;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.City;
import com.servlet.address.repo.CityRepo;
import com.servlet.address.service.CityService;

@Service
public class CityHandler implements CityService{
	private static final Logger LOGGER = LoggerFactory.getLogger(CityHandler.class);
	@Autowired
	private CityRepo repository;
//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	@Override
	public List<City> getListCity() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public City getById(long id) {
		// TODO Auto-generated method stub
		Optional<City> obj = repository.findById(id);
		if(obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

}
