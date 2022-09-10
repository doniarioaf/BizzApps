package com.servlet.address.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.address.entity.SubDistrictData;

public class GetDataSubDistrict implements RowMapper<SubDistrictData>{
	
	private String schemaSql;
	
	public GetDataSubDistrict() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.subdis_id as subdis_id, data.subdis_name as subdis_name,data.dis_id as dis_id ");
		sqlBuilder.append("from subdistricts as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public SubDistrictData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long subdis_id = rs.getLong("subdis_id");
		final String subdis_name = rs.getString("subdis_name");
		final Long dis_id = rs.getLong("dis_id");
		SubDistrictData data = new SubDistrictData(subdis_id,subdis_name,dis_id);
		return data;
	}

}
