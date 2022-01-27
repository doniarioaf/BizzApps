package com.servlet.admin.permission.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.permission.entity.PermissionData;

public class GetPermissionData implements RowMapper<PermissionData>{
	private String schemaSql;
	
	public GetPermissionData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* from m_permissions as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public PermissionData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String descriptions = rs.getString("descriptions");
		
		PermissionData data = new PermissionData();
		data.setId(id);
		data.setDescriptions(descriptions);
		return data;
	}

}
