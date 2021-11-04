package com.servlet.admin.customer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.customer.entity.CustomerListData;

public class GetCustomerList implements RowMapper<CustomerListData>{

	private String schemaSql;
	
	public GetCustomerList() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nama as nama,data.address as address,data.phone as phone ");
		sqlBuilder.append("from m_customer as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public CustomerListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String address = rs.getString("address");
		final String phone = rs.getString("phone");
		
		CustomerListData data = new CustomerListData();
		data.setId(id);
		data.setNama(nama);
		data.setAddress(address);
		data.setPhone(phone);
		
		return data;
	}

}
