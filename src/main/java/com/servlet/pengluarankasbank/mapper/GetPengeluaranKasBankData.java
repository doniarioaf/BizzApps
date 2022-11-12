package com.servlet.pengluarankasbank.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;

public class GetPengeluaranKasBankData implements RowMapper<PengeluaranKasBankData>{
	private String schemaSql;
	
	public GetPengeluaranKasBankData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.paymentdate as paymentdate, data.paymentto as paymentto, data.idcoa as idcoa, ");
		sqlBuilder.append("data.idbank as idbank, data.keterangan as keterangan, data.isactive as isactive ");
		sqlBuilder.append("from m_pengeluaran_kas_bank as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}


	@Override
	public PengeluaranKasBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Date paymentdate = rs.getDate("paymentdate");
		final String paymentto = rs.getString("paymentto");
		final Long idcoa = rs.getLong("idcoa");
		final Long idbank = rs.getLong("idbank");
		final String keterangan = rs.getString("keterangan");
		final boolean isactive = rs.getBoolean("isactive");
		
		PengeluaranKasBankData data = new PengeluaranKasBankData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setPaymentdate(paymentdate);
		data.setPaymentto(paymentto);
		data.setIdcoa(idcoa);
		data.setIdbank(idbank);
		data.setKeterangan(keterangan);
		data.setIsactive(isactive);
		return data;
	}

}
