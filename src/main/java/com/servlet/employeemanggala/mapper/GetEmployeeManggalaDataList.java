package com.servlet.employeemanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.employeemanggala.entity.EmployeManggalaDataList;

public class GetEmployeeManggalaDataList implements RowMapper<EmployeManggalaDataList>{
	private String schemaSql;
	
	public GetEmployeeManggalaDataList() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.code as code, data.statuskaryawan as statuskaryawan, data.jabatan as jabatan, data.nama as nama, data.isactive as isactive, data.jeniskelamin as jeniskelamin ");
		sqlBuilder.append("from m_employee_manggala as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public EmployeManggalaDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String code = rs.getString("code");
		final String statuskaryawan = rs.getString("statuskaryawan");
		final String jabatan = rs.getString("jabatan");
		final String nama = rs.getString("nama");
		final boolean isactive = rs.getBoolean("isactive");
		final String jeniskelamin = rs.getString("jeniskelamin");
		
		EmployeManggalaDataList data = new EmployeManggalaDataList();
		data.setId(id);
		data.setCode(code);
		data.setStatuskaryawan(statuskaryawan);
		data.setJabatan(jabatan);
		data.setNama(nama);
		data.setJeniskelamin(jeniskelamin);
		data.setIsactive(isactive);
		return data;
	}

}
