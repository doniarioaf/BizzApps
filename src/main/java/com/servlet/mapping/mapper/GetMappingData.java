package com.servlet.mapping.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.mapping.entity.MappingData;

public class GetMappingData implements RowMapper<MappingData>{
	
	private String schemaSql;
	
	public GetMappingData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.mappingcode as mappingcode, data.mappinggrup as mappinggrup, data.idmaster as idmaster ");
		sqlBuilder.append("from m_mapping as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public MappingData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String mappingcode = rs.getString("mappingcode");
		final String mappinggrup = rs.getString("mappinggrup");
		final Long idmaster = rs.getLong("idmaster");
		
		MappingData data = new MappingData();
		data.setId(id);
		data.setIdmaster(idmaster);
		data.setMappingcode(mappingcode);
		data.setMappinggrup(mappinggrup);
		return data;
	}

}
