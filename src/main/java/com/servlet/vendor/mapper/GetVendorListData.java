package com.servlet.vendor.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.vendor.entity.VendorListData;

public class GetVendorListData implements RowMapper<VendorListData>{
	
	private String schemaSql;
	
	public GetVendorListData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.idvendorcategory as idvendorcategory, data_vc.categoryname as categoryname, ");
		sqlBuilder.append("data.code as code, data.nama as nama, data.alias as alias, data.isactive as isactive ");
		sqlBuilder.append("from m_vendor as data ");
		sqlBuilder.append("left join m_vendor_category as data_vc on data_vc.id = data.idvendorcategory ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public VendorListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idvendorcategory = rs.getLong("idvendorcategory");
		final String categoryname = rs.getString("categoryname");
		final String code = rs.getString("code");
		final String nama = rs.getString("nama");
		final String alias = rs.getString("alias");
		final boolean isactive = rs.getBoolean("isactive");
		
		VendorListData data = new VendorListData();
		data.setId(id);
		data.setIdvendorcategory(idvendorcategory);
		data.setVendorcategoryname(categoryname);
		data.setCode(code);
		data.setNama(nama);
		data.setAlias(alias);
		data.setIsactive(isactive);
		return data;
	}

}
