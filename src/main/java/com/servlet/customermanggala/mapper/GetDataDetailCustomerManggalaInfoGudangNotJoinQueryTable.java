package com.servlet.customermanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoGudangData;

public class GetDataDetailCustomerManggalaInfoGudangNotJoinQueryTable implements RowMapper<DetailCustomerManggalaInfoGudangData>{
	
	private String schemaSql;
	
	public GetDataDetailCustomerManggalaInfoGudangNotJoinQueryTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idwarehouse as idwarehouse ");
		sqlBuilder.append("from detail_customer_manggala_info_gudang as data ");
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailCustomerManggalaInfoGudangData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idwarehouse = rs.getLong("idwarehouse");
		DetailCustomerManggalaInfoGudangData data = new DetailCustomerManggalaInfoGudangData();
		data.setId(idwarehouse);
		return data;
	}

}
