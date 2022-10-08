package com.servlet.invoicetype.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoicetype.entity.InvoiceTypeData;

public class GetInvoiceTypeData implements RowMapper<InvoiceTypeData>{
	
	private String schemaSql;
	
	public GetInvoiceTypeData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.invoicetype as invoicetype, data.nama as nama, data.isactive as isactive, param.codename as codename ");
		sqlBuilder.append("from m_invoice_type as data ");
		sqlBuilder.append("left join m_parameter as param on param.code = data.invoicetype and param.grup = 'INVOICETYPE' ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public InvoiceTypeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String invoicetype = rs.getString("invoicetype");
		final String codename = rs.getString("codename");
		final String nama = rs.getString("nama");
		final boolean isactive = rs.getBoolean("isactive");
		
		InvoiceTypeData data = new InvoiceTypeData();
		data.setId(id);
		data.setInvoicetype(invoicetype);
		data.setInvoicetypename(codename);
		data.setNama(nama);
		data.setIsactive(isactive);
		return data;
	}

}
