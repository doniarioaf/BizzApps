package com.servlet.suratjalan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.suratjalan.entity.SuratJalanWO;

public class GetSuratJalanWO implements RowMapper<SuratJalanWO>{
	private String schemaSql;
	
	public GetSuratJalanWO() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.nocantainer as nocantainer, ");
		sqlBuilder.append("data.idwarehouse as idwarehouse, warehouse.nama as warehousename ");
		sqlBuilder.append("from t_surat_jalan as data ");
		sqlBuilder.append("left join m_warehouse as warehouse on warehouse.id = data.idwarehouse ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public SuratJalanWO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		final String nocantainer = rs.getString("nocantainer");
		final Long idwarehouse = rs.getLong("idwarehouse");
		final String warehousename = rs.getString("warehousename");
		
		SuratJalanWO data = new SuratJalanWO();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setIdwarehouse(idwarehouse);
		data.setWarehousename(warehousename);
		data.setNocontainer(nocantainer);
		
		return data;
	}

}
