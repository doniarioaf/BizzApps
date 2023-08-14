package com.servlet.vendor.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.vendor.entity.DetailVendorBankData;

public class GetDetailVendorBankData implements RowMapper<DetailVendorBankData>{
	
	private String schemaSql;
	
	public GetDetailVendorBankData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.norek as norek, data.atasnama as atasnama, data.bank as bank ");
		sqlBuilder.append("from detail_vendor_bank as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public DetailVendorBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String norek = rs.getString("norek");
		final String atasnama = rs.getString("atasnama");
		final String bank = rs.getString("bank");
		DetailVendorBankData data = new DetailVendorBankData();
		data.setNorek(norek);
		data.setAtasnama(atasnama);
		data.setBank(bank);
		
		return data;
	}

}
