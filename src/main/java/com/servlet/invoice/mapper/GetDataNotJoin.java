package com.servlet.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoice.entity.InvoiceData;

public class GetDataNotJoin implements RowMapper<InvoiceData>{
	private String schemaSql;
	
	public GetDataNotJoin() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument  ");
		sqlBuilder.append("from m_invoice as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public InvoiceData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		
		InvoiceData data = new InvoiceData();
		data.setId(id);
		data.setNodocument(nodocument);
		
		return data;
	}
}
