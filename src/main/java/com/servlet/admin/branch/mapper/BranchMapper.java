package com.servlet.admin.branch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.branch.entity.Branch;

public class BranchMapper implements RowMapper<Branch>{
	private String schemaSql;
	
	public BranchMapper(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_branch where isdelete = false ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	@Override
	public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Branch data = new Branch();
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String code = rs.getString("code");
		final String contactnumber = rs.getString("contactnumber");
		final String displayname = rs.getString("displayname");
		final String address = rs.getString("address");
		final String email = rs.getString("email");
		final boolean isactive = rs.getBoolean("isactive");
		final boolean isdelete = rs.getBoolean("isdelete");
		final Timestamp created = rs.getTimestamp("created");
		final Timestamp modified = rs.getTimestamp("modified");
		
		data.setId(id);
		data.setNama(nama);
		data.setCode(code);
		data.setContactnumber(contactnumber);
		data.setDisplayname(displayname);
		data.setAddress(address);
		data.setEmail(email);
		data.setIsactive(isactive);
		data.setIsdelete(isdelete);
		data.setCreated(created);
		data.setModified(modified);
		
		return data;
	}

	

}
