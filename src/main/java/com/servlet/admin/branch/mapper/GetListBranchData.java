package com.servlet.admin.branch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.branch.entity.BranchData;

public class GetListBranchData implements RowMapper<BranchData>{
	private String schemaSql;
	
	public GetListBranchData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_branch as mb ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	public String schema() {
		return this.schemaSql;
	}

	@Override
	public BranchData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		
		BranchData data = new BranchData();
		data.setId(id);
		data.setName(nama);
		return data;
	}

}
