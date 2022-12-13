package com.servlet.workorder.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.workorder.entity.WorkOrderDropDownData;

public class GetWorkOrderDropdownData implements RowMapper<WorkOrderDropDownData>{
	private String schemaSql;
	
	public GetWorkOrderDropdownData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idcustomer as idcustomer,data.namacargo as namacargo, ");
		sqlBuilder.append("data.nobl as nobl ,data.noaju as noaju, cust.customername as customername, data.jalur as jalur ");
		sqlBuilder.append("from m_workorder as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public WorkOrderDropDownData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idcustomer = rs.getLong("idcustomer");
		final String nodocument = rs.getString("nodocument");
		final String namacargo = rs.getString("namacargo");
		final String customername = rs.getString("customername");
		final String nobl = rs.getString("nobl");
		final String noaju = rs.getString("noaju");
		final String jalur = rs.getString("jalur");
		
		WorkOrderDropDownData data = new WorkOrderDropDownData();
		data.setId(id);
		data.setIdcustomer(idcustomer);
		data.setNamacargo(namacargo);
		data.setNamaCustomer(customername);
		data.setNodocument(nodocument);
		data.setNobl(nobl);
		data.setNoaju(noaju);
		data.setJalur(jalur);
		
		return data;
	}

}
