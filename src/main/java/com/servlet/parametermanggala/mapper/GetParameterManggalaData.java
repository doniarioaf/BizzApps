package com.servlet.parametermanggala.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.parametermanggala.entity.ParameterManggalaData;

public class GetParameterManggalaData implements RowMapper<ParameterManggalaData>{
	
	private String schemaSql;
	
	public GetParameterManggalaData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.paramname as paramname, data.paramvalue as paramvalue, data.isactive as isactive, data.paramtype as paramtype, data.paramdate as paramdate ");
		sqlBuilder.append("from m_parameter_manggala as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ParameterManggalaData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String paramname = rs.getString("paramname");
		final String paramvalue = rs.getString("paramvalue");
		final boolean isactive = rs.getBoolean("isactive");
		final String paramtype = rs.getString("paramtype");
		final Date paramdate = rs.getDate("paramdate");
		ParameterManggalaData data = new ParameterManggalaData();
		data.setId(id);
		data.setParamname(paramname);
		data.setParamvalue(paramvalue);
		data.setIsactive(isactive);
		data.setParamtype(paramtype);
		data.setParamdate(paramdate);
		return data;
	}

}
