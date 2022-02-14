package com.servlet.mobile.customercallplan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GetCountCustomerCallPlanMobile implements RowMapper<Long>{
private String schemaSql;
	
	public GetCountCustomerCallPlanMobile(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("idcustomer from m_customer_call_plan as mccp ");
		sqlBuilder.append("join m_customer as mc on mc.id = mccp.idcustomer ");
		sqlBuilder.append("join m_call_plan as mcp on mcp.id = mccp.idcallplan ");
		sqlBuilder.append("join m_project as mp on mp.id = mcp.idproject and mp.isdelete = false ");
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
