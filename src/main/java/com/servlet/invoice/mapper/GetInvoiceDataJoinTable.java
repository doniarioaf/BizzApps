package com.servlet.invoice.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoice.entity.InvoiceData;

public class GetInvoiceDataJoinTable implements RowMapper<InvoiceData>{
	
	private String schemaSql;
	
	public GetInvoiceDataJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.idcustomer as idcustomer, data.refno as refno, ");
		sqlBuilder.append("data.deliveredto as deliveredto, data.deliverydate as deliverydate, data.idwo as idwo, data.idsuratjalan as idsuratjalan, ");
		sqlBuilder.append("data.idinvoicetype as idinvoicetype, data.totalinvoice as totalinvoice, data.isactive as isactive,data.diskonnota as diskonnota, data.ppn as ppn,data.nilaippn as nilaippn, ");
		sqlBuilder.append("cust.customername as customername, wo.nodocument as nodocumentwo, sj.nodocument as nodocumentsj, invtype.codename as invtypename, ");
		sqlBuilder.append("wo.jalur as jalurwo, sj.idwarehouse as idwarehousesj, paramjalur.codename as jalurname, data.notes1 as notes1, data.notes2 as notes2 ");
		sqlBuilder.append("from m_invoice as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idwo ");
		sqlBuilder.append("left join t_surat_jalan as sj on sj.id = data.idsuratjalan ");
		sqlBuilder.append("left join m_parameter as invtype on invtype.code = data.idinvoicetype and invtype.grup = 'INVOICETYPE' ");
		sqlBuilder.append("left join m_parameter as paramjalur on paramjalur.code = wo.jalur and paramjalur.grup = 'WARNA_JALUR' ");
		
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
		final String customername = rs.getString("customername");
		final String refno = rs.getString("refno");
		final String deliveredto = rs.getString("deliveredto");
		final Date deliverydate = rs.getDate("deliverydate");
		final Long idwo = rs.getLong("idwo");
		final String nodocumentwo = rs.getString("nodocumentwo");
		final Long idsuratjalan = rs.getLong("idsuratjalan");
		final String nodocumentsj = rs.getString("nodocumentsj");
		final String idinvoicetype = rs.getString("idinvoicetype");
		final String invtypename = rs.getString("invtypename");
		final Double totalinvoice = rs.getDouble("totalinvoice");
		final boolean isactive = rs.getBoolean("isactive");
		final Double diskonnota = rs.getDouble("diskonnota");
		final Double ppn = rs.getDouble("ppn");
		final Double nilaippn = rs.getDouble("nilaippn");
		final String jalurwo = rs.getString("jalurwo");
		final Long idwarehousesj = rs.getLong("idwarehousesj");
		final String jalurname = rs.getString("jalurname");
		final String notes1 = rs.getString("notes1");
		final String notes2 = rs.getString("notes2");
		
		
		InvoiceData data = new InvoiceData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setIdcustomer(idcustomer);
		data.setNamaCustomer(customername);
		data.setRefno(refno);
		data.setDeliveredto(deliveredto);
		data.setDeliverydate(deliverydate);
		data.setIdwo(idwo);
		data.setNoocumentwo(nodocumentwo);
		data.setIdsuratjalan(idsuratjalan);
		data.setNoocumentsuratjalan(nodocumentsj);
		data.setIdinvoicetype(idinvoicetype);
		data.setNamainvoicetype(invtypename);
		data.setTotalinvoice(totalinvoice);
		data.setIsactive(isactive);
		data.setDiskonnota(diskonnota);
		data.setJalurwo(jalurwo);
		data.setJalurname(jalurname);
		data.setIdwarehousesuratjalan(idwarehousesj);
		data.setPpn(ppn);
		data.setNilaippn(nilaippn);
		data.setNotes1(notes1);
		data.setNotes2(notes2);
		return data;
	}

}
