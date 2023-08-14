package com.servlet.pricelist.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.pricelist.entity.PriceListData;

public class GetPriceListSearchData implements RowMapper<PriceListData>{
	private String schemaSql;
	
	public GetPriceListSearchData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idcustomer as idcustomer, data.isactive as isactive, cust.customername as customername ");
		sqlBuilder.append("from m_price_list as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		
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
		final String customername = rs.getString("customername");
		final boolean isactive = rs.getBoolean("isactive");
		
		PriceListData data = new PriceListData();
		data.setId(id);
		data.setIdcustomer(idcustomer);
		data.setNamacustomer(customername);
		data.setNodocument(nodocument);
		data.setIsactive(isactive);
		return data;
	}

}
