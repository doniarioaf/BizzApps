package com.servlet.admin.usermobile.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.usermobile.entity.UserMobilePermission;
import com.servlet.user.entity.UserPermissionData;

public class GetUserMobilePermission implements RowMapper<UserMobilePermission>{
	private String schemaSql;
	
	public GetUserMobilePermission() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mp.code as permissioncode ");
		sqlBuilder.append("from m_user_mobile_role mur ");
		sqlBuilder.append("join m_role_permissions mrp on mur.idrole = mrp.idrole ");
		sqlBuilder.append("join m_permissions mp on mp.id = mrp.idpermissions ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}

	@Override
	public UserMobilePermission mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserMobilePermission data = new UserMobilePermission();
		final String permissioncode = rs.getString("permissioncode");
		data.setPermissioncode(permissioncode);
		return data;
	}

}
