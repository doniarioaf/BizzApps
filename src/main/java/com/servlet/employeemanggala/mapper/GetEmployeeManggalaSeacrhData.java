package com.servlet.employeemanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.employeemanggala.entity.EmployeeSearchData;

public class GetEmployeeManggalaSeacrhData implements RowMapper<EmployeeSearchData>{
	
	private String schemaSql;
	
	public GetEmployeeManggalaSeacrhData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.jabatan as jabatan, param.codename as jabatanname, data.nama as nama, data.noidentitas as noidentitas ");
		sqlBuilder.append("from m_employee_manggala as data ");
		sqlBuilder.append("left join m_parameter as param on param.code = data.jabatan and param.grup = 'POSITION' ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public EmployeeSearchData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
//		final String jabatan = rs.getString("jabatan");
		final String nama = rs.getString("nama");
		final String noidentitas = rs.getString("noidentitas");
		final String jabatanname = rs.getString("jabatanname");
		
		EmployeeSearchData data = new EmployeeSearchData();
		data.setId(id);
		data.setJabatan(jabatanname);
		data.setNama(nama);
		data.setNoidentitas(noidentitas);
		
		return data;
	}

}
