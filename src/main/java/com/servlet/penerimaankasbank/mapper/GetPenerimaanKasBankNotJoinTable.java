package com.servlet.penerimaankasbank.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;

public class GetPenerimaanKasBankNotJoinTable implements RowMapper<PenerimaanKasBankData>{
	private String schemaSql;
	
	public GetPenerimaanKasBankNotJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.receivedate as receivedate, data.receivefrom as receivefrom, data.idcoa as idcoa, ");
		sqlBuilder.append("data.idbank as idbank, data.keterangan as keterangan, data.isactive as isactive ");
		sqlBuilder.append("from m_penerimaan_kas_bank as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public PenerimaanKasBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Date receivedate = rs.getDate("receivedate");
		final String receivefrom = rs.getString("receivefrom");
		final Long idcoa = rs.getLong("idcoa");
		final Long idbank = rs.getLong("idbank");
		final String keterangan = rs.getString("keterangan");
		final boolean isactive = rs.getBoolean("isactive");
		
		PenerimaanKasBankData data = new PenerimaanKasBankData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setReceivedate(receivedate);
		data.setReceivefrom(receivefrom);
		data.setIdcoa(idcoa);
		data.setIdbank(idbank);
		data.setKeterangan(keterangan);
		data.setIsactive(isactive);
		return data;
	}

}
