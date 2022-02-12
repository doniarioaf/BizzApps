package com.servlet.admin.customerproject.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.customerproject.entity.CustomerProjectData;

public class GetCustomerProjectJoinTable implements RowMapper<CustomerProjectData>{
	
	private String schemaSql;
	
	public GetCustomerProjectJoinTable(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mc.*,mccp.idproject as idproject from m_customer_project as mccp ");
		sqlBuilder.append("left join m_customer as mc on mc.id = mccp.idcustomer ");
		sqlBuilder.append("left join m_project as mcp on mcp.id = mccp.idproject ");
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public CustomerProjectData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final long idproject = rs.getLong("idproject");
		final long idcustomertype = rs.getLong("idcustomertype");
		final String nama = rs.getString("nama");
		final String address = rs.getString("address");
		final String city = rs.getString("city");
		final String areaname = rs.getString("areaname");
		final String subarename = rs.getString("subarename");
		final String phone = rs.getString("phone");
		final String latitude = rs.getString("latitude");
		final String longitude = rs.getString("longitude");
		
		CustomerProjectData data = new CustomerProjectData();
		data.setId(id);
		data.setNama(nama);
		data.setAddress(address);
		data.setCity(city);
		data.setAreaname(areaname);
		data.setSubarename(subarename);
		data.setPhone(phone);
		data.setIdproject(idproject);
		data.setIdcustomertype(idcustomertype);
		data.setLatitude(latitude);
		data.setLongitude(longitude);
		
		return data;
	}

}
