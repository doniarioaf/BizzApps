package com.servlet.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.user.entity.UserPermissionData;


public class UserPermissionMapper implements RowMapper<UserPermissionData>{
	private String schemaSql;
	
	public UserPermissionMapper(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mp.code as permissioncode ");
		sqlBuilder.append("from m_user_apps_role mur ");
		sqlBuilder.append("join m_role_permissions mrp on mur.idrole = mrp.idrole ");
		sqlBuilder.append("join m_permissions mp on mp.id = mrp.idpermissions ");
		sqlBuilder.append("join m_role mr on mr.id = mur.idrole ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}

	@Override
	public UserPermissionData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserPermissionData data = new UserPermissionData();
		final String permissioncode = rs.getString("permissioncode");
		data.setPermissioncode(permissioncode);
		return data;
	}
}
