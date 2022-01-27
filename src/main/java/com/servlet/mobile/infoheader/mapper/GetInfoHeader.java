package com.servlet.mobile.infoheader.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.servlet.mobile.infoheader.entity.InfoHeaderData;

public class GetInfoHeader implements RowMapper<InfoHeaderData>{
	
	private String schemaSql;
	
	public GetInfoHeader() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("*,mct.nama as customertypename ");
		sqlBuilder.append("from m_info_header as data ");
		sqlBuilder.append("join m_customer_type as mct on mct.id = data.idcustomertype ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public InfoHeaderData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idcustomertype = rs.getLong("idcustomertype");
		final String question = rs.getString("question");
		final String type = rs.getString("type");
		final Long sequence = rs.getLong("sequence");
		final String customertypename = rs.getString("customertypename");
		
		InfoHeaderData data = new InfoHeaderData();
		data.setId(id);
		data.setQuestion(question);
		data.setType(type);
		data.setSequence(sequence);
		data.setIdcustomertype(idcustomertype);
		data.setCustomertypename(customertypename);
		
		return data;
	}

}
