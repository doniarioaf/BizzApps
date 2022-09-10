package com.servlet.address.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.PostalCodeData;
import com.servlet.address.mapper.GetDataDistrict;
import com.servlet.address.mapper.GetDataPostalCode;
import com.servlet.address.repo.DistrictRepo;
import com.servlet.address.repo.PostalCodeRepo;
import com.servlet.address.service.DistrictService;
import com.servlet.address.service.PostalCodeService;

@Service
public class PostalCodeHandler implements PostalCodeService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PostalCodeRepo repository;
	
	@Override
	public List<PostalCodeData> getListPostalCode() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

	@Override
	public List<PostalCodeData> getListPostalCodeByPostalCode(long postalcode) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		sqlBuilder.append(" where data.postal_code = ? ");
		final Object[] queryParameters = new Object[] {postalcode};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

	@Override
	public List<PostalCodeData> getListPostalCodeByPostalCodeBySubDistrictId(long subdistrictid) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataPostalCode().schema());
		sqlBuilder.append(" where data.subdis_id = ? ");
		final Object[] queryParameters = new Object[] {subdistrictid};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataPostalCode(), queryParameters);
	}

}
