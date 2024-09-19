package com.servlet.invoice.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoice.entity.InvoiceDPData;

public class GetInvoiceDPData implements RowMapper<InvoiceDPData>{
	private String schemaSql;
	
	public GetInvoiceDPData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.totalinvoice as totalinvoice  ");
		sqlBuilder.append("from m_invoice as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public InvoiceDPData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Date tanggal = rs.getDate("tanggal");
		final Double totalinvoice = rs.getDouble("totalinvoice");
		
		InvoiceDPData data = new InvoiceDPData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setTotalinvoice(totalinvoice);
		
		return data;
	}

}
