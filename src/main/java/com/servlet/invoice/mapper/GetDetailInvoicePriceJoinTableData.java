package com.servlet.invoice.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.invoice.entity.DetailInvoicePriceData;

public class GetDetailInvoicePriceJoinTableData implements RowMapper<DetailInvoicePriceData>{
	private String schemaSql;
	
	public GetDetailInvoicePriceJoinTableData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idinvoice as idinvoice, data.idwarehouse as idwarehouse, data.idinvoicetype as idinvoicetype, data.jalur as jalur, data.price as price, ");
		sqlBuilder.append("data.ismandatory as ismandatory, data.idpricelist as idpricelist, data.qty as qty, data.diskon as diskon, data.subtotal as subtotal, ");
		sqlBuilder.append("invtype.nama as invtypename, prl.nodocument as prlnodocument, data.idpengeluarankasbank as idpengeluarankasbank, pengeluaran.nodocument as pengeluarannodocument, warehouse.nama as warehousename ");
		sqlBuilder.append("from detail_invoice_price as data ");
		sqlBuilder.append("left join m_invoice_type as invtype on invtype.id = data.idinvoicetype ");
		sqlBuilder.append("left join m_price_list as prl on prl.id = data.idpricelist ");
		sqlBuilder.append("left join m_pengeluaran_kas_bank as pengeluaran on pengeluaran.id = data.idpengeluarankasbank ");
		sqlBuilder.append("left join m_warehouse as warehouse on warehouse.id = data.idwarehouse ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	@Override
	public DetailInvoicePriceData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idwarehouse = rs.getLong("idwarehouse");
		final String warehousename = rs.getString("warehousename");
		final Long idinvoicetype = rs.getLong("idinvoicetype");
		final String invtypename = rs.getString("invtypename");
		final String jalur = rs.getString("jalur");
		final Double price = rs.getDouble("price");
		final String ismandatory = rs.getString("ismandatory");
		final Long idpricelist = rs.getLong("idpricelist");
		final String prlnodocument = rs.getString("prlnodocument");
		final Long qty = rs.getLong("qty");
		final Double diskon = rs.getDouble("diskon");
		final Double subtotal = rs.getDouble("subtotal");
		final String pengeluarannodocument = rs.getString("pengeluarannodocument");
		final Long idpengeluarankasbank = rs.getLong("idpengeluarankasbank");
		
		DetailInvoicePriceData data = new DetailInvoicePriceData();
		data.setIdwarehouse(idwarehouse);
		data.setWarehouseName(warehousename);
		data.setIdinvoicetype(idinvoicetype);
		data.setInvoicetypename(invtypename);
		data.setJalur(jalur);
		data.setPrice(price);
		data.setIsmandatory(ismandatory);
		data.setIdpricelist(idpricelist);
		data.setNodocumentpricelist(prlnodocument);
		data.setQty(qty);
		data.setDiskon(diskon);
		data.setSubtotal(subtotal);
		data.setIdpengeluarankasbank(idpengeluarankasbank);
		data.setNodocumentpengeluaran(pengeluarannodocument);
		return data;
	}

}
