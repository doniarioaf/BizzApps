package com.servlet.address.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.address.entity.DistrictData;

public class GetDataDistrict implements RowMapper<DistrictData>{
	
	private String schemaSql;
	
	public GetDataDistrict() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.dis_id as dis_id, data.dis_name as dis_name,data.city_id as city_id ");
		sqlBuilder.append("from districts as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public DistrictData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long dis_id = rs.getLong("dis_id");
		final Long city_id = rs.getLong("city_id");
		final String dis_name = rs.getString("dis_name");
		
		DistrictData data = new DistrictData(dis_id,dis_name,city_id);
		return data;
	}

}
