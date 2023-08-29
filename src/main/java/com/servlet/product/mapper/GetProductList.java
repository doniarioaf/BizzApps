package com.servlet.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.product.entity.ProductData;

public class GetProductList implements RowMapper<ProductData>{
	private String schemaSql;
	
	public GetProductList() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.code as code, data.nama as nama, data.hargabeli as hargabeli, data.hargajual as hargajual, ");
		sqlBuilder.append("data.conv1to4 as conv1to4, data.conv2to4 as conv2to4, data.conv3to4 as conv3to4, data.conv4to4 as conv4to4 ");
		sqlBuilder.append("from m_product as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ProductData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		ProductData data = new ProductData();
		final Long id = rs.getLong("id");
		final String code = rs.getString("code");
		final String nama = rs.getString("nama");
		final Double hargabeli = rs.getDouble("hargabeli");
		final Double hargajual = rs.getDouble("hargajual");
		final Double conv1to4 = rs.getDouble("conv1to4");
		final Double conv2to4 = rs.getDouble("conv2to4");
		final Double conv3to4 = rs.getDouble("conv3to4");
		final Double conv4to4 = rs.getDouble("conv4to4");
		
		data.setId(id);
		data.setCode(code);
		data.setNama(nama);
		data.setHargabeli(hargabeli);
		data.setHargajual(hargajual);
		data.setConv1to4(conv1to4);
		data.setConv2to4(conv2to4);
		data.setConv3to4(conv3to4);
		data.setConv4to4(conv4to4);
		
		return data;
	}

}
