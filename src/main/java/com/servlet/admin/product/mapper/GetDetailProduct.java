package com.servlet.admin.product.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.admin.product.entity.ProductDetailData;

public class GetDetailProduct implements RowMapper<ProductDetailData>{
	
	private String schemaSql;
	
	public GetDetailProduct() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mp.id as id ,mp.nama as namaprod ,mp.description as descprod, ");
		sqlBuilder.append("mpt.id as prodtypeid ,mpt.nama as prodtypename ");
		sqlBuilder.append("from m_product as mp ");
		sqlBuilder.append("join m_product_type as mpt on mpt.id = mp.idproducttype ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ProductDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String namaprod = rs.getString("namaprod");
		final String descprod = rs.getString("descprod");
		final long prodtypeid = rs.getLong("prodtypeid");
		final String prodtypename = rs.getString("prodtypename");
		
		ProductDetailData data = new ProductDetailData();
		data.setId(id);
		data.setNama(namaprod);
		data.setDescription(descprod);
		data.setIdproducttype(prodtypeid);
		data.setNameproducttype(prodtypename);
		return data;
	}

}
