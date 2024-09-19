package com.servlet.bankaccount.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.bankaccount.entity.BankAccountData;

public class GetDataBankAccount implements RowMapper<BankAccountData>{
	
	private String schemaSql;
	
	public GetDataBankAccount() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.cabang as cabang, data.norekening as norekening, data.dateopen as dateopen, data.catatan1 as catatan1, data.catatan2 as catatan2, data.isactive as isactive, data.namabank as namabank, data.saldoawal as saldoawal, data.showfinancejunior as showfinancejunior ");
		sqlBuilder.append("from m_bank_account as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public BankAccountData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String cabang = rs.getString("cabang");
		final String namabank = rs.getString("namabank");
		final String norekening = rs.getString("norekening");
		final Date dateopen = rs.getDate("dateopen");
		final String catatan1 = rs.getString("catatan1");
		final String catatan2 = rs.getString("catatan2");
		final boolean isactive = rs.getBoolean("isactive");
		final boolean showfinancejunior = rs.getBoolean("showfinancejunior");
		final Double saldoawal = rs.getDouble("saldoawal");
		
		BankAccountData data = new BankAccountData();
		data.setId(id);
		data.setNamabank(namabank);
		data.setCabang(cabang);
		data.setNorekening(norekening);
		data.setDateopen(dateopen);
		data.setCatatan1(catatan1);
		data.setCatatan2(catatan2);
		data.setIsactive(isactive);
		data.setSaldoawal(0.00);
		data.setShowfinancejunior(showfinancejunior);
		return data;
	}

}
