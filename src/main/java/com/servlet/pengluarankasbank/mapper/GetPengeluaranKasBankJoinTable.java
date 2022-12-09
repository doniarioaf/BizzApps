package com.servlet.pengluarankasbank.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;

public class GetPengeluaranKasBankJoinTable implements RowMapper<PengeluaranKasBankData>{
	private String schemaSql;
	
	public GetPengeluaranKasBankJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.paymentdate as paymentdate, data.paymentto as paymentto, data.idcoa as idcoa, ");
		sqlBuilder.append("data.idbank as idbank, data.keterangan as keterangan, data.isactive as isactive, coa.nama as coaname, bank.namabank as namabank, data.idwo as idwo, wo.nodocument as nodocumentwo ");
		sqlBuilder.append("from m_pengeluaran_kas_bank as data ");
		sqlBuilder.append("left join m_coa as coa on coa.id = data.idcoa ");
		sqlBuilder.append("left join m_bank_account as bank on bank.id = data.idbank ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idwo ");
		
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
		final String coaname = rs.getString("coaname");
		final String namabank = rs.getString("namabank");
		final Long idwo = rs.getLong("idwo");
		final String nodocumentwo = rs.getString("nodocumentwo");
		
		
		PengeluaranKasBankData data = new PengeluaranKasBankData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setPaymentdate(paymentdate);
		data.setPaymentto(paymentto);
		data.setIdcoa(idcoa);
		data.setIdbank(idbank);
		data.setKeterangan(keterangan);
		data.setIsactive(isactive);
		data.setCoaName(coaname);
		data.setBankName(namabank);
		data.setIdwo(idwo);
		data.setNodocumentWO(nodocumentwo);
		return data;
	}

}
