package com.servlet.customermanggala.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.customermanggala.entity.DetailCustomerManggalaInfoKementerianData;

public class GetDataDetailCustomerManggalaKementerian implements RowMapper<DetailCustomerManggalaInfoKementerianData>{
	
	private String schemaSql;
	
	public GetDataDetailCustomerManggalaKementerian() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.kementerian as kementerian, data.alamat_email as alamat_email, data.password_email as password_email ");
		sqlBuilder.append("from detail_customer_manggala_info_kementerian as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public DetailCustomerManggalaInfoKementerianData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String kementerian = rs.getString("kementerian");
		final String alamat_email = rs.getString("alamat_email");
		final String password_email = rs.getString("password_email");
		
		DetailCustomerManggalaInfoKementerianData data = new DetailCustomerManggalaInfoKementerianData();
		data.setKementerian(kementerian);
		data.setAlamat_email(alamat_email);
		data.setPassword_email(password_email);
		return data;
	}

}
