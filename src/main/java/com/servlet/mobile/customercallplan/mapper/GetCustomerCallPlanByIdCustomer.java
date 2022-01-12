package com.servlet.mobile.customercallplan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;

public class GetCustomerCallPlanByIdCustomer implements RowMapper<CustomerCallPlanData>{
	
	private String schemaSql;
	
	public GetCustomerCallPlanByIdCustomer(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mc.*,mccp.idcallplan as idcallplan from m_customer_call_plan as mccp ");
		sqlBuilder.append("join m_customer as mc on mc.id = mccp.idcustomer ");
		sqlBuilder.append("join m_call_plan as mcp on mcp.id = mccp.idcallplan ");
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public CustomerCallPlanData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final long idcallplan = rs.getLong("idcallplan");
		final long idcustomertype = rs.getLong("idcustomertype");
		final String nama = rs.getString("nama");
		final String address = rs.getString("address");
		final String city = rs.getString("city");
		final String areaname = rs.getString("areaname");
		final String subarename = rs.getString("subarename");
		final String phone = rs.getString("phone");
		final String latitude = rs.getString("latitude");
		final String longitude = rs.getString("longitude");
		
		CustomerCallPlanData data = new CustomerCallPlanData();
		data.setId(id);
		data.setNama(nama);
		data.setAddress(address);
		data.setCity(city);
		data.setAreaname(areaname);
		data.setSubarename(subarename);
		data.setPhone(phone);
		data.setIdcallplan(idcallplan);
		data.setIdcustomertype(idcustomertype);
		data.setLatitude(latitude);
		data.setLongitude(longitude);
		
		return data;
	}

}
