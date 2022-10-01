package com.servlet.workordertype.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.workordertype.entity.WorkOrderTypeData;

public class GetWorkOrderTypeData implements RowMapper<WorkOrderTypeData>{
	private String schemaSql;
	
	public GetWorkOrderTypeData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.code as code, data.name as name, data.note as note, data.isactive as isactive ");
		sqlBuilder.append("from m_workorder_type as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	@Override
	public WorkOrderTypeData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String code = rs.getString("code");
		final String name = rs.getString("name");
		final String note = rs.getString("note");
		final boolean isactive = rs.getBoolean("isactive");
		WorkOrderTypeData data = new WorkOrderTypeData();
		data.setId(id);
		data.setCode(code);
		data.setName(name);
		data.setNote(note);
		data.setIsactive(isactive);
		return data;
	}

}
