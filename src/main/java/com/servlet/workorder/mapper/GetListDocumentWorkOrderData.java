package com.servlet.workorder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.workorder.entity.ListDocumentWorkOrderData;

public class GetListDocumentWorkOrderData implements RowMapper<ListDocumentWorkOrderData>{
	private String schemaSql;
	
	public GetListDocumentWorkOrderData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.idworkorder as idworkorder, data.filedocument as filedocument, data.filename as filename, data.contenttype as contenttype, data.tanggal as tanggal ");
		sqlBuilder.append("from list_document_workorder as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public ListDocumentWorkOrderData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idworkorder = rs.getLong("idworkorder");
		final String filedocument = rs.getString("filedocument");
		final String filename = rs.getString("filename");
		final String contenttype = rs.getString("contenttype");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		
		ListDocumentWorkOrderData data = new ListDocumentWorkOrderData();
		data.setId(id);
		data.setIdworkorder(idworkorder);
		data.setFiledocument("");
		data.setFilename(filename);
		data.setContenttype(contenttype);
		data.setTanggal(tanggal);
		return data;
	}

}
