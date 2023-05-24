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
		sqlBuilder.append("data.idinvoicetype as idinvoicetype, data.totalinvoice as totalinvoice, data.isactive as isactive, data.diskonnota as diskonnota, data.ppn as ppn, wo.nobl as wonobl, ");
		sqlBuilder.append("wh.nama as whname, sj.tanggalkembali as sjtanggalkembali ");
		sqlBuilder.append("from m_invoice as data ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idwo ");
		sqlBuilder.append("left join t_surat_jalan as sj on sj.id = data.idsuratjalan ");
		sqlBuilder.append("left join m_warehouse as wh on wh.id = sj.idwarehouse ");
		
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
		final Date deliverydate = rs.getDate("sjtanggalkembali");
		final Long idwo = rs.getLong("idwo");
//		final Long idsuratjalan = rs.getLong("idsuratjalan");
		final String idinvoicetype = rs.getString("idinvoicetype");
		final Double totalinvoice = rs.getDouble("totalinvoice");
//		final boolean isactive = rs.getBoolean("isactive");
		final Double diskonnota = rs.getDouble("diskonnota");
		final Double ppn = rs.getDouble("ppn");
		final String wonobl = rs.getString("wonobl");
		final String whname = rs.getString("whname");
		
		PrintInvoiceData data = new PrintInvoiceData();
		data.setId(id);
		data.setIdwo(idwo);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setIdcustomer(idcustomer);
		data.setRefno(refno);
		data.setDeliveredto(whname);
		data.setDeliverydate(deliverydate);
		data.setTotalinvoice(totalinvoice);
		data.setDiskonnota(diskonnota);
		data.setIdinvoicetype(idinvoicetype);
		data.setPpn(ppn != null ?ppn.toString():"0");
		data.setNobl(wonobl);
		return data;
	}

}
