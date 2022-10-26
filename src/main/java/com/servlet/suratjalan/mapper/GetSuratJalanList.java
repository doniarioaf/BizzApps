package com.servlet.suratjalan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.suratjalan.entity.SuratJalanData;

public class GetSuratJalanList implements RowMapper<SuratJalanData>{
	private String schemaSql;
	
	public GetSuratJalanList() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.nocantainer as nocantainer, data.status as status, ");
		sqlBuilder.append("cust.customername as customername, wo.namacargo as namacargo, param.codename as statusname ");
		sqlBuilder.append("from t_surat_jalan as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idworkorder ");
		sqlBuilder.append("left join m_parameter as param on param.code = data.status and param.grup = 'STATUS_SURATJALAN' ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public SuratJalanData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		final String nocantainer = rs.getString("nocantainer");
		final String customername = rs.getString("customername");
		final String namacargo = rs.getString("namacargo");
		final String status = rs.getString("status");
		final String statusname = rs.getString("statusname");
		
		SuratJalanData data = new SuratJalanData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setNocantainer(nocantainer);
		data.setNamacustomer(customername);
		data.setNamacargoWO(namacargo);
		data.setStatus(status);
		data.setStatusname(statusname);
		return data;
	}

}
