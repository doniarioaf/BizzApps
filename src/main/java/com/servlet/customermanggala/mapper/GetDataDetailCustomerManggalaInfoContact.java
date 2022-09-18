package com.servlet.customermanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoContactData;

public class GetDataDetailCustomerManggalaInfoContact implements RowMapper<DetailCustomerManggalaInfoContactData>{
	private String schemaSql;
	
	public GetDataDetailCustomerManggalaInfoContact() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.panggilan as panggilan, data.namakontak as namakontak, data.notelepon as notelepon, data.email as email, data.noext as noext ");
		sqlBuilder.append("from detail_customer_manggala_info_contact as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailCustomerManggalaInfoContactData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String panggilan = rs.getString("panggilan");
		final String namakontak = rs.getString("namakontak");
		final String notelepon = rs.getString("notelepon");
		final String email = rs.getString("email");
		final String noext = rs.getString("noext");
		
		DetailCustomerManggalaInfoContactData data = new DetailCustomerManggalaInfoContactData();
		data.setPanggilan(panggilan);
		data.setNamakontak(namakontak);
		data.setNotelepon(notelepon);
		data.setEmail(email);
		data.setNoext(noext);
		return data;
	}

}
