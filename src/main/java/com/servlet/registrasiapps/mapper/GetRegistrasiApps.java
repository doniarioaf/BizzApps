package com.servlet.registrasiapps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.registrasiapps.entity.RegistrasiAppsData;

public class GetRegistrasiApps implements RowMapper<RegistrasiAppsData>{
	private String schemaSql;
	
	public GetRegistrasiApps() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.keyapps as keyapps ");
		sqlBuilder.append("from m_registrasi_apps as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public RegistrasiAppsData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		RegistrasiAppsData data = new RegistrasiAppsData();
		final Long id = rs.getLong("id");
		final String keyapps = rs.getString("keyapps");
		data.setId(id);
		data.setKeyapps(keyapps);
		return data;
	}

}
