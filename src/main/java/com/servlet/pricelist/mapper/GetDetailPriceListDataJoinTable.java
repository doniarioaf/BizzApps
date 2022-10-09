package com.servlet.pricelist.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.pricelist.entity.DetailPriceListData;

public class GetDetailPriceListDataJoinTable implements RowMapper<DetailPriceListData>{
	private String schemaSql;
	
	public GetDetailPriceListDataJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idpricelist as idpricelist, data.idwarehouse as idwarehouse, data.idinvoicetype as idinvoicetype, data.price as price, ");
		sqlBuilder.append("data.jalur as jalur, data.ismandatory as ismandatory, ");
		sqlBuilder.append("warehouse.nama as namawarehouse, invtype.nama as invtypename, param.codename as paramname ");
		sqlBuilder.append("from detail_price_list as data ");
		sqlBuilder.append("left join m_warehouse as warehouse on warehouse.id = data.idwarehouse ");
		sqlBuilder.append("left join m_invoice_type as invtype on invtype.id = data.idinvoicetype ");
		sqlBuilder.append("left join m_parameter as param on param.code = data.jalur and param.grup = 'WARNA_JALUR' ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public DetailPriceListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idpricelist = rs.getLong("idpricelist");
		final Long idwarehouse = rs.getLong("idwarehouse");
		final Long idinvoicetype = rs.getLong("idinvoicetype");
		final Double price = rs.getDouble("price");
		final String jalur = rs.getString("jalur");
		final String ismandatory = rs.getString("ismandatory");
		final String namawarehouse = rs.getString("namawarehouse");
		final String invtypename = rs.getString("invtypename");
		final String paramname = rs.getString("paramname");
		
		DetailPriceListData data = new DetailPriceListData();
		data.setIdpricelist(idpricelist);
		data.setIdwarehouse(idwarehouse);
		data.setWarehouseName(namawarehouse);
		data.setIdinvoicetype(idinvoicetype);
		data.setInvoicetypename(invtypename);
		data.setJalur(jalur);
		data.setJalurname(paramname);
		data.setPrice(price);
		data.setIsmandatory(ismandatory);

		return data;
	}

}
