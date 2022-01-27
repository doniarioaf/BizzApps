package com.servlet.mobile.usermobilecallplan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GetCountSizeListUserMobileCallPlanByIdUserMobile implements RowMapper<Long>{
	
	
	private String schemaSql;
	
	public GetCountSizeListUserMobileCallPlanByIdUserMobile(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("idcallplan from m_user_mobile_call_plan as mccp ");
		sqlBuilder.append("join m_call_plan as mc on mc.id = mccp.idcallplan ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("idcallplan");
		return id;
	}

}
