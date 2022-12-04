package com.servlet.penerimaankasbank.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class GetTotalAmount implements RowMapper<Double>{
	
	private String schemaSql;
	
	public GetTotalAmount() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("sum(data.amount) as total ");
		sqlBuilder.append("from detail_penerimaan_kas_bank as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Double totalamount = rs.getDouble("total");
		return totalamount;
	}

}
