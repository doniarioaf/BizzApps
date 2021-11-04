package com.servlet.admin.usermobile.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.usermobile.entity.UserMobileListData;

public class GetListAllUserMobile implements RowMapper<UserMobileListData>{
	
	private String schemaSql;
	
	public GetListAllUserMobile() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* from m_user_mobile mua ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public UserMobileListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserMobileListData data = new UserMobileListData();
		final long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String username = rs.getString("username");
		final String notelepon = rs.getString("contactnumber");
		final boolean isactive = rs.getBoolean("isactive");
		
		data.setId(id);
		data.setNama(nama);
		data.setNotelepon(notelepon);
		data.setUsername(username);
		data.setIsactive(isactive);
		return data;
	}

	
}
