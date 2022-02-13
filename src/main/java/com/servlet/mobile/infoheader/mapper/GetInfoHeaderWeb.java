package com.servlet.mobile.infoheader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.mobile.infoheader.entity.ListInfoHeader;

public class GetInfoHeaderWeb implements RowMapper<ListInfoHeader>{
private String schemaSql;
	
	public GetInfoHeaderWeb() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("*,mct.nama as customertypename, mp.id as idproject, mp.nama as projectname ");
		sqlBuilder.append("from m_info_header as data ");
		sqlBuilder.append("left join m_customer_type as mct on mct.id = data.idcustomertype ");
		sqlBuilder.append("left join m_project as mp on mp.id = data.idproject and mp.isdelete = false ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ListInfoHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idcustomertype = rs.getLong("idcustomertype");
		final String question = rs.getString("question");
		final String type = rs.getString("type");
		final Long sequence = rs.getLong("sequence");
		final String customertypename = rs.getString("customertypename");
		final Long idproject = rs.getLong("idproject");
		final String projectname = rs.getString("projectname");
		
		ListInfoHeader data = new ListInfoHeader();
		data.setId(id);
		data.setQuestion(question);
		data.setType(type);
		data.setSequence(sequence);
//		data.setIdcustomertype(idcustomertype);
//		data.setCustomertypename(customertypename);
		data.setIdproject(idproject == null ?0:idproject);
		data.setProjectname(projectname);
		return data;
	}
}
