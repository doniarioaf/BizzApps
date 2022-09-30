package com.servlet.vendorcategory.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.vendorcategory.entity.VendorCategoryData;

public class GetDataVendorCategory implements RowMapper<VendorCategoryData>{
	private String schemaSql;
	
	public GetDataVendorCategory() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.categoryname as categoryname, data.note as note, data.isactive as isactive ");
		sqlBuilder.append("from m_vendor_category as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public VendorCategoryData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String categoryname = rs.getString("categoryname");
		final String note = rs.getString("note");
		final boolean isactive = rs.getBoolean("isactive");
		VendorCategoryData data = new VendorCategoryData();
		data.setId(id);
		data.setCategoryname(categoryname);
		data.setNote(note);
		data.setIsactive(isactive);
		return data;
	}

}
