package com.servlet.mobile.usermobilecallplan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanData;

public class GetUserMobileCallPlanByIdUser implements RowMapper<UserMobileCallPlanData>{
	
	private String schemaSql;
	
	public GetUserMobileCallPlanByIdUser(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mc.* from m_user_mobile_call_plan as mccp ");
		sqlBuilder.append("join m_call_plan as mc on mc.id = mccp.idcallplan ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public UserMobileCallPlanData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String description = rs.getString("description");
		
		UserMobileCallPlanData data = new UserMobileCallPlanData();
		data.setIdcallplan(id);
		data.setNama(nama);
		data.setDescription(description);
		
		return data;
	}

}
