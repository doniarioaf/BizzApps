package com.servlet.admin.rolepermissions.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.role.entity.RolePermissionData;

public class GetRolePermissionByIdRole implements RowMapper<RolePermissionData>{
	private String schemaSql;
	
	public GetRolePermissionByIdRole() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mp.* from m_role_permissions as mrp ");
		sqlBuilder.append("join m_permissions as mp on mp.id = mrp.idpermissions ");
		sqlBuilder.append("join m_role as mr on mr.id = mrp.idrole ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}

	@Override
	public RolePermissionData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		RolePermissionData data = new RolePermissionData();
		final long id = rs.getLong("id");
		final String code = rs.getString("code");
		final String descriptions = rs.getString("descriptions");
		
		data.setId(id);
		data.setCode(code);
		data.setDescriptions(descriptions);
		
		return data;
	}

}
