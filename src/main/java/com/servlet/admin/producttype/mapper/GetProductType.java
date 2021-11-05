package com.servlet.admin.producttype.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.producttype.entity.ProductTypeData;

public class GetProductType implements RowMapper<ProductTypeData>{
	private String schemaSql;
	
	public GetProductType() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_product_type as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public ProductTypeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String description = rs.getString("description");
		
		ProductTypeData data = new ProductTypeData();
		data.setId(id);
		data.setNama(nama);
		data.setDescription(description);
		return data;
	}
}
