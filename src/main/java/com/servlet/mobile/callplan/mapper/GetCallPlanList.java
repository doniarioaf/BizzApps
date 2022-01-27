package com.servlet.mobile.callplan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.customer.entity.CustomerListData;
import com.servlet.mobile.callplan.entity.CallPlanListData;

public class GetCallPlanList implements RowMapper<CallPlanListData>{
	
	private String schemaSql;
	
	public GetCallPlanList() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_call_plan as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public CallPlanListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String description = rs.getString("description");
		
		CallPlanListData data = new CallPlanListData();
		data.setId(id);
		data.setNama(nama);
		data.setDescription(description);
		return data;
	}

}
