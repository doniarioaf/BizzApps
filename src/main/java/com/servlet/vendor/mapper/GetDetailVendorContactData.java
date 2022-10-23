package com.servlet.vendor.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.vendor.entity.DetailVendorContactData;

public class GetDetailVendorContactData implements RowMapper<DetailVendorContactData>{
	private String schemaSql;
	
	public GetDetailVendorContactData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.panggilan as panggilan, data.namakontak as namakontak, data.nocontacthp as nocontacthp, ");
		sqlBuilder.append("data.email as email, data.contactofficenumber as contactofficenumber, data.extention as extention ");
		sqlBuilder.append("from detail_vendor_contact as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	
	@Override
	public DetailVendorContactData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String panggilan = rs.getString("panggilan");
		final String namakontak = rs.getString("namakontak");
		final String nocontacthp = rs.getString("nocontacthp");
		final String email = rs.getString("email");
		final String contactofficenumber = rs.getString("contactofficenumber");
		final String extention = rs.getString("extention");
		
		DetailVendorContactData data = new DetailVendorContactData();
		data.setContactofficenumber(contactofficenumber);
		data.setEmail(email);
		data.setExtention(extention);
		data.setNamakontak(namakontak);
		data.setNocontacthp(nocontacthp);
		data.setPanggilan(panggilan);
		return data;
	}

}
