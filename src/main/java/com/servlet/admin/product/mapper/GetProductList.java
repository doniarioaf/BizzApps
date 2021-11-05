package com.servlet.admin.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.product.entity.ProductListData;

public class GetProductList implements RowMapper<ProductListData>{
	
	private String schemaSql;
	
	public GetProductList() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nama as nama,data.description as description ");
		sqlBuilder.append("from m_product as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ProductListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String description = rs.getString("description");
		
		ProductListData data = new ProductListData();
		data.setId(id);
		data.setNama(nama);
		data.setDescription(description);
		
		return data;
	}

}
