package com.servlet.invoice.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoice.entity.PrintInvoiceData;

public class GetPrintInvoiceData implements RowMapper<PrintInvoiceData>{
	private String schemaSql;
	
	public GetPrintInvoiceData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.idcustomer as idcustomer, data.refno as refno, ");
		sqlBuilder.append("data.deliveredto as deliveredto, data.deliverydate as deliverydate, data.idwo as idwo, data.idsuratjalan as idsuratjalan, ");
		sqlBuilder.append("data.idinvoicetype as idinvoicetype, data.totalinvoice as totalinvoice, data.isactive as isactive, data.diskonnota as diskonnota ");
		sqlBuilder.append("from m_invoice as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public PrintInvoiceData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Date tanggal = rs.getDate("tanggal");
		final Long idcustomer = rs.getLong("idcustomer");
		final String refno = rs.getString("refno");
		final String deliveredto = rs.getString("deliveredto");
		final Date deliverydate = rs.getDate("deliverydate");
		final Long idwo = rs.getLong("idwo");
//		final Long idsuratjalan = rs.getLong("idsuratjalan");
		final String idinvoicetype = rs.getString("idinvoicetype");
		final Double totalinvoice = rs.getDouble("totalinvoice");
//		final boolean isactive = rs.getBoolean("isactive");
		final Double diskonnota = rs.getDouble("diskonnota");
		
		PrintInvoiceData data = new PrintInvoiceData();
		data.setId(id);
		data.setIdwo(idwo);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setIdcustomer(idcustomer);
		data.setRefno(refno);
		data.setDeliveredto(deliveredto);
		data.setDeliverydate(deliverydate);
		data.setTotalinvoice(totalinvoice);
		data.setDiskonnota(diskonnota);
		data.setIdinvoicetype(idinvoicetype);

		return data;
	}

}
