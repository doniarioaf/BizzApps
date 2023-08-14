package com.servlet.suratjalan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.suratjalan.entity.SuratJalanData;

public class GetSuratJalanNotJoin implements RowMapper<SuratJalanData>{
	private String schemaSql;
	
	public GetSuratJalanNotJoin() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.nocantainer as nocantainer, data.status as status ");
		sqlBuilder.append("from t_surat_jalan as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public SuratJalanData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		final String nocantainer = rs.getString("nocantainer");
		final String status = rs.getString("status");
		
		SuratJalanData data = new SuratJalanData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setNocantainer(nocantainer);
		data.setStatus(status);
		
		return data;
	}

}
