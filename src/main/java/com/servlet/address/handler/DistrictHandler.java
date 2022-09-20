package com.servlet.address.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.DistrictData;
import com.servlet.address.mapper.GetDataDistrict;
import com.servlet.address.repo.DistrictRepo;
import com.servlet.address.service.DistrictService;

@Service
public class DistrictHandler implements DistrictService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DistrictRepo repository;
	
	@Override
	public List<DistrictData> getListDistrict() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataDistrict().schema());
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataDistrict(), queryParameters);
	}

	@Override
	public List<DistrictData> getListDistrictByCity(long cityid) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataDistrict().schema());
		sqlBuilder.append(" where data.city_id = ? ");
		final Object[] queryParameters = new Object[] {cityid};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataDistrict(), queryParameters);
	}

	@Override
	public List<DistrictData> getListDistrictByPostalCode(long postalcode) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataDistrict().schema());
		sqlBuilder.append(" where data.dis_id in ( select dis_id from subdistricts where subdis_id in (select subdis_id from postalcode where postal_code = ?) ) ");
		final Object[] queryParameters = new Object[] {postalcode};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataDistrict(), queryParameters);
	}

}
