package com.servlet.invoice.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoice.entity.InvoiceData;

public class GetInvoiceData implements RowMapper<InvoiceData>{
	private String schemaSql;
	
	public GetInvoiceData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.idcustomer as idcustomer, data.refno as refno, ");
		sqlBuilder.append("data.deliveredto as deliveredto, data.deliverydate as deliverydate, data.idwo as idwo, data.idsuratjalan as idsuratjalan, ");
		sqlBuilder.append("data.idinvoicetype as idinvoicetype, data.totalinvoice as totalinvoice, data.isactive as isactive, data.diskonnota as diskonnota, ");
		sqlBuilder.append("wo.nodocument as nodocumentwo , wo.noaju as noajuwo  ");
		sqlBuilder.append("from m_invoice as data ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idwo ");
		
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
		final Date tanggal = rs.getDate("tanggal");
		final Long idcustomer = rs.getLong("idcustomer");
		final String refno = rs.getString("refno");
		final String deliveredto = rs.getString("deliveredto");
		final Date deliverydate = rs.getDate("deliverydate");
		final Long idwo = rs.getLong("idwo");
		final Long idsuratjalan = rs.getLong("idsuratjalan");
		final String idinvoicetype = rs.getString("idinvoicetype");
		final Double totalinvoice = rs.getDouble("totalinvoice");
		final boolean isactive = rs.getBoolean("isactive");
		final Double diskonnota = rs.getDouble("diskonnota");
		final String nodocumentwo = rs.getString("nodocumentwo");
		final String noajuwo = rs.getString("noajuwo");
		
		
		InvoiceData data = new InvoiceData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setNoocumentwo(nodocumentwo);
		data.setNoajuwo(noajuwo);
		data.setTanggal(tanggal);
		data.setIdcustomer(idcustomer);
		data.setNamaCustomer("");
		data.setRefno(refno);
		data.setDeliveredto(deliveredto);
		data.setDeliverydate(deliverydate);
		data.setIdwo(idwo);
		data.setNoocumentwo("");
		data.setIdsuratjalan(idsuratjalan);
		data.setNoocumentsuratjalan("");
		data.setIdinvoicetype(idinvoicetype);
		data.setNamainvoicetype("");
		data.setTotalinvoice(totalinvoice);
		data.setIsactive(isactive);
		data.setDiskonnota(diskonnota);
		return data;
	}

}
