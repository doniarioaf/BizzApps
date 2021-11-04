package com.servlet.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.user.entity.UserListData;

public class GetListAllUser implements RowMapper<UserListData>{
	private String schemaSql;
	
	public GetListAllUser() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* from m_user_apps mua ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	@Override
	public UserListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserListData data = new UserListData();
		final long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String username = rs.getString("username");
		final String notelepon = rs.getString("notelepon");
		final boolean isactive = rs.getBoolean("isactive");
		
		data.setId(id);
		data.setNama(nama);
		data.setNotelepon(notelepon);
		data.setUsername(username);
		data.setIsactive(isactive);
		return data;
	}
	
	public String schema() {
		return this.schemaSql;
	}

}
