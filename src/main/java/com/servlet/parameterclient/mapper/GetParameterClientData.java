package com.servlet.parameterclient.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.servlet.parameterclient.entity.ParameterClientData;
import org.springframework.jdbc.core.RowMapper;

public class GetParameterClientData implements RowMapper<ParameterClientData>{
	
	private String schemaSql;
	
	public GetParameterClientData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.paramname as paramname, data.paramvalue as paramvalue, data.isactive as isactive, data.paramtype as paramtype, data.paramdate as paramdate ");
		sqlBuilder.append("from m_parameter_client as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ParameterClientData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String paramname = rs.getString("paramname");
		final String paramvalue = rs.getString("paramvalue");
		final boolean isactive = rs.getBoolean("isactive");
		final String paramtype = rs.getString("paramtype");
		final Date paramdate = rs.getDate("paramdate");
		ParameterClientData data = new ParameterClientData();
		data.setId(id);
		data.setParamname(paramname);
		data.setParamvalue(paramvalue);
		data.setIsactive(isactive);
		data.setParamtype(paramtype);
		data.setParamdate(paramdate);
		return data;
	}

}
