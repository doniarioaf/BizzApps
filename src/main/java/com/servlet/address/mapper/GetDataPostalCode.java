package com.servlet.address.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.address.entity.PostalCodeData;

public class GetDataPostalCode implements RowMapper<PostalCodeData>{
	private String schemaSql;
	
	public GetDataPostalCode() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.postal_id as postal_id, data.subdis_id as subdis_id, data.dis_id as dis_id, data.city_id as city_id, data.prov_id as prov_id, data.postal_code as postal_code ");
		sqlBuilder.append("from postalcode as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public PostalCodeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long postal_id = rs.getLong("postal_id");
		final Long subdis_id = rs.getLong("subdis_id");
		final Long dis_id = rs.getLong("dis_id");
		final Long city_id = rs.getLong("city_id");
		final Long prov_id = rs.getLong("prov_id");
		final Long postal_code = rs.getLong("postal_code");
		
		PostalCodeData data = new PostalCodeData(postal_id,subdis_id,dis_id,city_id,prov_id,postal_code);
		return data;
	}

}
