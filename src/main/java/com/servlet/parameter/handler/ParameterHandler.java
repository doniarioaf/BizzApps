package com.servlet.parameter.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.address.mapper.GetDataDistrict;
import com.servlet.parameter.entity.ParameterData;
import com.servlet.parameter.mapper.GetParameter;
import com.servlet.parameter.repo.ParameterRepo;
import com.servlet.parameter.service.ParameterService;

@Service
public class ParameterHandler implements ParameterService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ParameterRepo repository;
	
	@Override
	public List<ParameterData> getListParameterByGrup(String grup) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameter().schema());
		sqlBuilder.append(" where data.grup = ? and data.isactive = true ");
		final Object[] queryParameters = new Object[] {grup};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameter(), queryParameters);
	}

	@Override
	public List<ParameterData> getListParameterAll() {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameter().schema());
		final Object[] queryParameters = new Object[] {};
		return this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameter(), queryParameters);
	}

	@Override
	public ParameterData getListParameterByCode(String code) {
		// TODO Auto-generated method stub
		final StringBuilder sqlBuilder = new StringBuilder("select " + new GetParameter().schema());
		sqlBuilder.append(" where data.code = ? ");
		final Object[] queryParameters = new Object[] {code};
		List<ParameterData> list = this.jdbcTemplate.query(sqlBuilder.toString(), new GetParameter(), queryParameters);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
