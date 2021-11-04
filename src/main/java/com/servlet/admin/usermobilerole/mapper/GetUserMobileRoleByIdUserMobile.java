package com.servlet.admin.usermobilerole.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.usermobilerole.entity.UserMobileRoleData;

public class GetUserMobileRoleByIdUserMobile implements RowMapper<UserMobileRoleData>{
	
	private String schemaSql;
	
	public GetUserMobileRoleByIdUserMobile() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mr.* from m_user_mobile_role as userrole ");
		sqlBuilder.append("join m_role as mr on mr.id =  userrole.idrole ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}

	@Override
	public UserMobileRoleData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserMobileRoleData data = new UserMobileRoleData();
		final long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String descriptions = rs.getString("descriptions");
		
		data.setId(id);
		data.setNama(nama);
		data.setDescriptions(descriptions);
		return data;
	}

}
