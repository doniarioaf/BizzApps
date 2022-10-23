package com.servlet.historyemployeemanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.historyemployeemanggala.entity.HistoryEmployeeData;

public class GetHistoryEmployeeManggala implements RowMapper<HistoryEmployeeData>{
	private String schemaSql;
	
	public GetHistoryEmployeeManggala() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.idemployee as idemployee, data.jabatan as jabatan, data.statusemployee as statusemployee, data.gaji as gaji, data.tanggal as tanggal, ");
		sqlBuilder.append("paramstatus.codename as statusname ");
		sqlBuilder.append("from history_employee_manggala as data ");
		sqlBuilder.append("left join m_parameter as paramstatus on paramstatus.code = data.statusemployee and paramstatus.grup = 'STATUS_KARYAWAN' ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public HistoryEmployeeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		HistoryEmployeeData data = new HistoryEmployeeData();
		final Long id = rs.getLong("id");
		final Long idemployee = rs.getLong("idemployee");
		final String jabatan = rs.getString("jabatan");
//		final String statusemployee = rs.getString("statusemployee");
		final String gaji = rs.getString("gaji");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		final String statusname = rs.getString("statusemployee");
		
		data.setId(id);
		data.setIdemployee(idemployee);
		data.setGaji(gaji);
		data.setJabatan(jabatan);
		data.setStatusemployee(statusname);
		data.setTanggal(tanggal);
		return data;
	}

}
