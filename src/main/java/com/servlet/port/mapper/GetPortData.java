package com.servlet.port.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.port.entity.PortData;

public class GetPortData implements RowMapper<PortData>{
	private String schemaSql;
	
	public GetPortData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.name as name, data.note as note, data.isactive as isactive ");
		sqlBuilder.append("from m_port as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}


	@Override
	public PortData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String name = rs.getString("name");
		final String note = rs.getString("note");
		final boolean isactive = rs.getBoolean("isactive");
		PortData data = new PortData();
		data.setId(id);
		data.setName(name);
		data.setNote(note);
		data.setIsactive(isactive);
		return data;
	}

}
