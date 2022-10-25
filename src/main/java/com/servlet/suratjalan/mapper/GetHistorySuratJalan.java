package com.servlet.suratjalan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.suratjalan.entity.HistorySuratJalanData;

public class GetHistorySuratJalan implements RowMapper<HistorySuratJalanData>{
	private String schemaSql;
	
	public GetHistorySuratJalan() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.idsuratjalan as idsuratjalan, data.tanggal as tanggal, data.status as status ");
		sqlBuilder.append("from history_surat_jalan as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public HistorySuratJalanData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idsuratjalan = rs.getLong("idsuratjalan");
		final String status = rs.getString("status");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		
		HistorySuratJalanData data = new HistorySuratJalanData();
		data.setId(id);
		data.setIdsuratjalan(idsuratjalan);
		data.setStatus(status);
		data.setTanggal(tanggal);
		return data;
	}

}
