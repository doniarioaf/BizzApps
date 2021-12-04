package com.servlet.mobile.monitorusermobileinfo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GetDistinctInfoId implements RowMapper<Long>{
	
private String schemaSql;
	
	public GetDistinctInfoId(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("distinct infoid from m_monitor_user_mobile_info as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long infoid = rs.getLong("infoid");
		return infoid;
	}

}
