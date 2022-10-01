package com.servlet.partai.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.partai.entity.PartaiData;

public class GetPartaiData implements RowMapper<PartaiData>{
	private String schemaSql;
	
	public GetPartaiData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.code as code, data.name as name, data.note as note, data.isactive as isactive ");
		sqlBuilder.append("from m_partai as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public PartaiData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String code = rs.getString("code");
		final String name = rs.getString("name");
		final String note = rs.getString("note");
		final boolean isactive = rs.getBoolean("isactive");
		PartaiData data = new PartaiData();
		data.setId(id);
		data.setCode(code);
		data.setName(name);
		data.setNote(note);
		data.setIsactive(isactive);
		return data;
	}
	
}
