package com.servlet.address.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.SubDistrictData;
import com.servlet.address.mapper.GetDataSubDistrict;
import com.servlet.address.repo.SubDistrictRepo;
import com.servlet.address.service.SubDistrictService;

@Service
public class SubDistrictHandler implements SubDistrictService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SubDistrictRepo repository;
	
	@Override
	public List<SubDistrictData> getListSubDistrict() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataSubDistrict().schema());
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataSubDistrict(), queryParameters);
	}

	@Override
	public List<SubDistrictData> getListSubDistrictByDistrictId(long districtid) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetDataSubDistrict().schema());
		sqlBuilder.append(" where data.dis_id = ? ");
		final Object[] queryParameters = new Object[] {districtid};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetDataSubDistrict(), queryParameters);
	}

}
