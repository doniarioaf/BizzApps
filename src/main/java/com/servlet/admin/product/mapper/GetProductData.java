package com.servlet.admin.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.product.entity.ProductData;

public class GetProductData implements RowMapper<ProductData>{
	private String schemaSql;
	
	public String schema() {
		return this.schemaSql;
	}
	
	public GetProductData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mp.id as id ,mp.nama as namaprod ,mp.description as descprod, ");
		sqlBuilder.append("mp.productcode as productcode ,mp.shortname as shortname ,mp.uom1 as uom1, ");
		sqlBuilder.append("mp.uom2 as uom2 ,mp.uom3 as uom3 ,mp.uom4 as uom4, ");
		sqlBuilder.append("mp.pricebuy as pricebuy ,mp.pricesell as pricesell ,mp.conversion1to4 as conversion1to4, ");
		sqlBuilder.append("mp.conversion2to4 as conversion2to4 ,mp.conversion3to4 as conversion3to4 ");
		sqlBuilder.append("from m_product as mp ");
		
		this.schemaSql = sqlBuilder.toString();
	}


	@Override
	public ProductData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String namaprod = rs.getString("namaprod");
		final String descprod = rs.getString("descprod");
		final String productcode = rs.getString("productcode");
		final String shortname = rs.getString("shortname");
		final String uom1 = rs.getString("uom1");
		final String uom2 = rs.getString("uom2");
		final String uom3 = rs.getString("uom3");
		final String uom4 = rs.getString("uom4");
		final double pricebuy = rs.getDouble("pricebuy");
		final double pricesell = rs.getDouble("pricesell");
		final int conversion1to4 = rs.getInt("conversion1to4");
		final int conversion2to4 = rs.getInt("conversion2to4");
		final int conversion3to4 = rs.getInt("conversion3to4");
		
		ProductData data = new ProductData();
		data.setId(id);
		data.setNama(namaprod);
		data.setDescription(descprod);
		data.setProductcode(productcode);
		data.setShortname(shortname);
		data.setUom1(uom1);
		data.setUom2(uom2);
		data.setUom3(uom3);
		data.setUom4(uom4);
		data.setPricebuy(pricebuy);
		data.setPricesell(pricesell);
		data.setConversion1to4(conversion1to4);
		data.setConversion2to4(conversion2to4);
		data.setConversion3to4(conversion3to4);
		return data;
	}
}
