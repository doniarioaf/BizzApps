package com.servlet.employeemanggala.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.employeemanggala.entity.DetailEmployeeManggalaInfoFamilyData;

public class GetDetailEmployeeManggalaInfoFamilyData implements RowMapper<DetailEmployeeManggalaInfoFamilyData>{
	private String schemaSql;
	
	public GetDetailEmployeeManggalaInfoFamilyData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.namaanak as namaanak, data.tanggallahir as tanggallahir, data.jeniskelamin as jeniskelamin, data.status as status ");
		sqlBuilder.append("from detail_employee_manggala_family as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailEmployeeManggalaInfoFamilyData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String namaanak = rs.getString("namaanak");
		final Date tanggallahir = rs.getDate("tanggallahir");
		final String jeniskelamin = rs.getString("jeniskelamin");
		final String status = rs.getString("status");
		
		DetailEmployeeManggalaInfoFamilyData data = new DetailEmployeeManggalaInfoFamilyData();
		data.setNamaanak(namaanak);
		data.setTanggallahir(tanggallahir);
		data.setJeniskelamin(jeniskelamin);
		data.setStatus(status);
		return data;
	}

}
