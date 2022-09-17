package com.servlet.customermanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.customermanggala.entity.CustomerManggalaData;

public class GetDataCustomerManggala implements RowMapper<CustomerManggalaData>{
	
	private String schemaSql;
	
	public GetDataCustomerManggala() {
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
		final String customertype = rs.getString("customertype");
		final String customername = rs.getString("customername");
		final String alias = rs.getString("alias");
		final String alamat = rs.getString("alamat");
		final String provinsi = rs.getString("provinsi");
		final String kota = rs.getString("kota");
		final String kodepos = rs.getString("kodepos");
		final String npwp = rs.getString("npwp");
		final String nib = rs.getString("nib");
		final boolean isactive = rs.getBoolean("isactive");
		final String telpkantor = rs.getString("telpkantor");
		
		CustomerManggalaData data = new CustomerManggalaData();
		data.setId(id);
		data.setCustomertype(customertype);
		data.setCustomername(customername);
		data.setAlias(alias);
		data.setAlamat(alamat);
		data.setProvinsi(provinsi);
		data.setProvinsiname("");
		data.setKota(kota);
		data.setKotaname("");
		data.setKodepos(kodepos);
		data.setKodeposname("");
		data.setNpwp(npwp);
		data.setNib(nib);
		data.setIsactive(isactive);
		data.setTelpkantor(telpkantor);
		return data;
	}

}
