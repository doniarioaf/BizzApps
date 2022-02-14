package com.servlet.mobile.project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.mobile.project.entity.ProjectData;

public class GetDataProject implements RowMapper<ProjectData>{
	private String schemaSql;
	
	public GetDataProject() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_project as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ProjectData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nama = rs.getString("nama");
		final String description = rs.getString("description");
		final String projectnumber = rs.getString("projectnumber");
		
		ProjectData data = new ProjectData();
		data.setId(id);
		data.setNama(nama);
		data.setDescription(description);
		data.setProjectnumber(projectnumber);
		return data;
	}

}
