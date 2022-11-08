package com.servlet.coa.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.coa.entity.CoaData;

public class GetCoaData implements RowMapper<CoaData>{
	
	private String schemaSql;
	
	public GetCoaData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.code as code, data.nama as nama, data.refid as refid, data.description as description, data.isactive as isactive ");
		sqlBuilder.append("from m_coa as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public CoaData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String code = rs.getString("code");
		final String nama = rs.getString("nama");
		final String refid = rs.getString("refid");
		final String description = rs.getString("description");
		final boolean isactive = rs.getBoolean("isactive");
		
		CoaData data = new CoaData();
		data.setId(id);
		data.setCode(code);
		data.setNama(nama);
		data.setRefid(refid);
		data.setDescription(description);
		data.setIsactive(isactive);
		return data;
	}

}
