package com.servlet.mobile.infodetail.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.mobile.infodetail.entity.InfoHeaderDetailData;


public class GetInfoHeaderDetail implements RowMapper<InfoHeaderDetailData>{
	
	private String schemaSql;
	
	public GetInfoHeaderDetail() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_info_header_detail as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public InfoHeaderDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String answer = rs.getString("answer");
		InfoHeaderDetailData data = new InfoHeaderDetailData();
		data.setId(id);
		data.setAnswer(answer);
		
		return data;
	}

}
