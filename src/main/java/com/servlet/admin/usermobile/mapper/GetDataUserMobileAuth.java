package com.servlet.admin.usermobile.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.usermobile.entity.UserMobileDataAuth;
public class GetDataUserMobileAuth implements RowMapper<UserMobileDataAuth>{
	private String schemaSql;
	
	public GetDataUserMobileAuth() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* from m_user_mobile mua ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public UserMobileDataAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String password = rs.getString("password");
		final String username = rs.getString("username");
		UserMobileDataAuth data = new UserMobileDataAuth();
		data.setId(id);
		data.setUsername(username);
		data.setPassword(password);
		
		return data;
	}
}
