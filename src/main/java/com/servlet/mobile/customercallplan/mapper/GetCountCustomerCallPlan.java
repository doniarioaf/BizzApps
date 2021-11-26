package com.servlet.mobile.customercallplan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GetCountCustomerCallPlan implements RowMapper<Long>{
	
	private String schemaSql;
	
	public GetCountCustomerCallPlan(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("idcustomer from m_customer_call_plan as mccp ");		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	@Override
	public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long idcustomer = rs.getLong("idcustomer");
		return idcustomer;
	}

}
