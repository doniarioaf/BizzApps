package com.servlet.admin.companybranch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.companybranch.entity.CompanyBranchData;

public class GetCompanyBranchByIdCompany implements RowMapper<CompanyBranchData>{
	
private String schemaSql;
	
	public GetCompanyBranchByIdCompany(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mb.* from m_company_branch as mcb ");
		sqlBuilder.append("join m_branch as mb on mb.id = mcb.idbranch ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public CompanyBranchData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		CompanyBranchData data = new CompanyBranchData();
		final long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String contactnumber = rs.getString("contactnumber");
		final String displayname = rs.getString("displayname");
		final String email = rs.getString("email");
		final boolean isactive = rs.getBoolean("isactive");
		
		data.setId(id);
		data.setNama(nama);
		data.setContactnumber(contactnumber);
		data.setDisplayname(displayname);
		data.setEmail(email);
		data.setIsactive(isactive);
		return data;
	}

	

}
