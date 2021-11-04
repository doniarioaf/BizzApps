package com.servlet.admin.customer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.admin.customer.entity.CustomerDetailData;
import com.servlet.admin.customer.entity.CustomerListData;

public class GetDetailCustomer implements RowMapper<CustomerDetailData>{
private String schemaSql;
	
	public GetDetailCustomer() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mc.id as id ,mc.nama as namacust ,mc.address as custaddress ,mc.provinsi as custprovinsi, ");
		sqlBuilder.append("mc.city as custcity ,mc.areaname as custareaname ,mc.subarename as custsubareaname ,mc.phone as custphone, ");
		sqlBuilder.append("mc.latitude as custlatitude , mc.longitude as custlongitude ,mct.id as custtypeid ,mct.nama as custtypenama from m_customer as mc ");
		sqlBuilder.append("join m_customer_type as mct on mct.id = mc.idcustomertype ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}

	@Override
	public CustomerDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long id = rs.getLong("id");
		final String namacust = rs.getString("namacust");
		final String custaddress = rs.getString("custaddress");
		final String custprovinsi = rs.getString("custprovinsi");
		final String custcity = rs.getString("custcity");
		final String custareaname = rs.getString("custareaname");
		final String custsubareaname = rs.getString("custsubareaname");
		final String custphone = rs.getString("custphone");
		final String custlatitude = rs.getString("custlatitude");
		final String custlongitude = rs.getString("custlongitude");
		final long custtypeid = rs.getLong("custtypeid");
		final String custtypenama = rs.getString("custtypenama");
		
		CustomerDetailData data = new CustomerDetailData();
		data.setId(id);
		data.setNama(namacust);
		data.setAddress(custaddress);
		data.setProvinsi(custprovinsi);
		data.setCity(custcity);
		data.setAreaname(custareaname);
		data.setSubarename(custsubareaname);
		data.setPhone(custphone);
		data.setLatitude(custlatitude);
		data.setLongitude(custlongitude);
		data.setIdcustomertype(custtypeid);
		data.setNamecustomertype(custtypenama);
		return data;
	}
}