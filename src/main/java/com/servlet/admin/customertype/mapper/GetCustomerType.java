package com.servlet.admin.customertype.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.customertype.entity.CustomerTypeData;

public class GetCustomerType implements RowMapper<CustomerTypeData>{
	private String schemaSql;
	
	public GetCustomerType() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_customer_type as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public CustomerTypeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String description = rs.getString("description");
		
		CustomerTypeData data = new CustomerTypeData();
		data.setId(id);
		data.setNama(nama);
		data.setDescription(description);
		return data;
	}

}
