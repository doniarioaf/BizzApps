package com.servlet.mobile.customercallplan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;

public class GetCustomerCallPlanByIdCustomer implements RowMapper<CustomerCallPlanData>{
	
	private String schemaSql;
	
	public GetCustomerCallPlanByIdCustomer(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mc.* from m_customer_call_plan as mccp ");
		sqlBuilder.append("join m_customer as mc on mc.id = mccp.idcustomer ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public CustomerCallPlanData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String address = rs.getString("address");
		final String city = rs.getString("city");
		final String areaname = rs.getString("areaname");
		final String subarename = rs.getString("subarename");
		final String phone = rs.getString("phone");
		
		CustomerCallPlanData data = new CustomerCallPlanData();
		data.setId(id);
		data.setNama(nama);
		data.setAddress(address);
		data.setCity(city);
		data.setAreaname(areaname);
		data.setSubarename(subarename);
		data.setPhone(phone);
		
		return data;
	}

}
