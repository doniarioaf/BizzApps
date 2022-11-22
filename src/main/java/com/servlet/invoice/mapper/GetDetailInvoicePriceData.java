package com.servlet.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoice.entity.DetailInvoicePriceData;

public class GetDetailInvoicePriceData implements RowMapper<DetailInvoicePriceData>{
	private String schemaSql;
	
	public GetDetailInvoicePriceData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idinvoice as idinvoice, data.idwarehouse as idwarehouse, data.idinvoicetype as idinvoicetype, data.jalur as jalur, data.price as price, ");
		sqlBuilder.append("data.ismandatory as ismandatory, data.idpricelist as idpricelist, data.qty as qty, data.diskon as diskon, data.subtotal as subtotal ");
		sqlBuilder.append("from detail_invoice_price as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public DetailInvoicePriceData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
//		final Long idinvoice = rs.getLong("idinvoice");
		final Long idwarehouse = rs.getLong("idwarehouse");
		final Long idinvoicetype = rs.getLong("idinvoicetype");
		final String jalur = rs.getString("jalur");
		final Double price = rs.getDouble("price");
		final String ismandatory = rs.getString("ismandatory");
		final Long idpricelist = rs.getLong("idpricelist");
		final Long qty = rs.getLong("qty");
		final Double diskon = rs.getDouble("diskon");
		final Double subtotal = rs.getDouble("subtotal");
		
		DetailInvoicePriceData data = new DetailInvoicePriceData();
		data.setIdwarehouse(idwarehouse);
		data.setIdinvoicetype(idinvoicetype);
		data.setInvoicetypename("");
		data.setJalur(jalur);
		data.setPrice(price);
		data.setIsmandatory(ismandatory);
		data.setIdpricelist(idpricelist);
		data.setNodocumentpricelist("");
		data.setQty(qty);
		data.setDiskon(diskon);
		data.setSubtotal(subtotal);
		return data;
	}

}
