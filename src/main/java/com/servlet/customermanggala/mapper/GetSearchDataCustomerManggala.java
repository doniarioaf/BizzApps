package com.servlet.customermanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.customermanggala.entity.CustomerManggalaData;

public class GetSearchDataCustomerManggala implements RowMapper<CustomerManggalaData>{
	
	private String schemaSql;
	
	public GetSearchDataCustomerManggala() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.customertype as customertype, data.customername as customername, data.alias as alias, data.alamat as alamat, data.provinsi as provinsi, data.kota as kota, data.kodepos as kodepos, data.npwp as npwp, data.nib as nib, data.isactive as isactive, data.telpkantor as telpkantor ");
		sqlBuilder.append("from m_customer_manggala as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	@Override
	public CustomerManggalaData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
//		final String customertype = rs.getString("customertype");
		final String customername = rs.getString("customername");
//		final String alias = rs.getString("alias");
//		final String alamat = rs.getString("alamat");
//		final String provinsi = rs.getString("provinsi");
		final String kota = rs.getString("kota");
//		final String kodepos = rs.getString("kodepos");
//		final String npwp = rs.getString("npwp");
//		final String nib = rs.getString("nib");
//		final boolean isactive = rs.getBoolean("isactive");
//		final String telpkantor = rs.getString("telpkantor");
		
		CustomerManggalaData data = new CustomerManggalaData();
		data.setId(id);
		data.setCustomertype("");
		data.setCustomername(customername);
		data.setAlias("");
		data.setAlamat("");
		data.setProvinsi("");
		data.setProvinsiname("");
		data.setKota(kota);
		data.setKotaname("");
		data.setKodepos("");
		data.setKodeposname("");
		data.setNpwp("");
		data.setNib("");
//		data.setIsactive(isactive);
//		data.setTelpkantor(telpkantor);
		return data;
	}

}
