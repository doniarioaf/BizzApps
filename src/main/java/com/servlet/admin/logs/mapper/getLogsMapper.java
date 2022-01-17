package com.servlet.admin.logs.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.logs.entity.LogsData;

public class getLogsMapper implements RowMapper<LogsData>{
	
private String schemaSql;
	
	public getLogsMapper(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("datalogs.*,datacomp.nama as namacomp, databranch.nama as namabranch from m_logs as datalogs ");
		sqlBuilder.append("join m_company as datacomp on datalogs.idcompany = datacomp.id ");
		sqlBuilder.append("m_branch as databranch on datalogs.idbranch = databranch.id ");
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public LogsData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long idcompany = rs.getLong("idcompany");
		final long idbranch = rs.getLong("idbranch");
		final String username = rs.getString("username");
		final String activity = rs.getString("activity");
		final String message = rs.getString("message");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		final String namacomp = rs.getString("namacomp");
		final String namabranch = rs.getString("namabranch");
		LogsData data = new LogsData();
		data.setIdcompany(idcompany);
		data.setIdbranch(idbranch);
		data.setUsername(username);
		data.setActivity(activity);
		data.setMessage(message);
		data.setCompanyName(namacomp);
		data.setBrachName(namabranch);
		data.setTanggal(tanggal);
		return data;
	}

}
