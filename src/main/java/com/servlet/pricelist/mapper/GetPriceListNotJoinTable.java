package com.servlet.pricelist.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.pricelist.entity.PriceListData;

public class GetPriceListNotJoinTable implements RowMapper<PriceListData>{
	private String schemaSql;
	
	public GetPriceListNotJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idcustomer as idcustomer, data.isactive as isactive ");
		sqlBuilder.append("from m_price_list as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public PriceListData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idcustomer = rs.getLong("idcustomer");
		final String nodocument = rs.getString("nodocument");
		final boolean isactive = rs.getBoolean("isactive");
		
		PriceListData data = new PriceListData();
		data.setId(id);
		data.setIdcustomer(idcustomer);
		data.setNamacustomer("");
		data.setNodocument(nodocument);
		data.setIsactive(isactive);
		return data;
	}

}
