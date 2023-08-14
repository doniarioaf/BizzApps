package com.servlet.parameter.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.parameter.entity.ParameterData;

public class GetParameter implements RowMapper<ParameterData>{
	
	private String schemaSql;
	
	public GetParameter() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.code as code, data.codename as codename ");
		sqlBuilder.append("from m_parameter as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public ParameterData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String code = rs.getString("code");
		final String codename = rs.getString("codename");
		return new ParameterData(code,codename);
	}

}
