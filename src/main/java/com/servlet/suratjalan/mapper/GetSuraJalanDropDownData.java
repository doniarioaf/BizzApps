package com.servlet.suratjalan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.suratjalan.entity.SuratJalanDropDown;

public class GetSuraJalanDropDownData implements RowMapper<SuratJalanDropDown>{
	private String schemaSql;
	
	public GetSuraJalanDropDownData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idcustomer as idcustomer, cust.customername as customername, data.idwarehouse as idwarehouse ");
		sqlBuilder.append("from t_surat_jalan as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	@Override
	public SuratJalanDropDown mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Long idcustomer = rs.getLong("idcustomer");
		final String customername = rs.getString("customername");
		final Long idwarehouse = rs.getLong("idwarehouse");
		//
		SuratJalanDropDown data = new SuratJalanDropDown();
		data.setId(id);
		data.setIdcustomer(idcustomer);
		data.setNodocument(nodocument);
		data.setNamacustomer(customername);
		data.setIdwarehouse(idwarehouse);
		return data;
	}

}
