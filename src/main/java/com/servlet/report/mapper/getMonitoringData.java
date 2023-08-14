package com.servlet.report.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.report.entity.MonitoringData;

public class getMonitoringData implements RowMapper<MonitoringData>{
	
	private String schemaSql;
	
	public getMonitoringData(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("monitor.*, usermobile.nama as namauser, ");
		sqlBuilder.append("customer.nama as namacust ,  customertype.nama as namacusttype,customer.customercode as customercode, ");
		sqlBuilder.append("customer.address as addresscust ,  customer.provinsi as provinsicust,customer.city as citycust, ");
		sqlBuilder.append("customer.areaname as areanamecust ,  customer.subarename as subarenamecust,customer.phone as phonecust, customer.contactperson as contactpersoncust ");
		sqlBuilder.append("from  m_monitor_user_mobile as monitor ");
		sqlBuilder.append("join m_user_mobile as usermobile on usermobile.id = monitor.idusermobile ");
		sqlBuilder.append("join m_customer as customer on customer.id = monitor.idcustomer ");
		sqlBuilder.append("left join m_customer_type as customertype on customer.idcustomertype = customertype.id ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public MonitoringData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long idmonitoring = rs.getLong("id");
		final long idusermobile = rs.getLong("idusermobile");
		final String namausermobile = rs.getString("namauser");
		final long idcustomer = rs.getLong("idcustomer");
		final String namacust = rs.getString("namacust");
		final String namacusttype = rs.getString("namacusttype");
		final Date tanggal = rs.getDate("tanggal");
		final String checkintime = rs.getString("checkintime");
		final String checkouttime = rs.getString("checkouttime");
		final String latitudein = rs.getString("latitudein");
		final String longitudein = rs.getString("longitudein");
		final String latitudeout = rs.getString("latitudeout");
		final String longitudeout = rs.getString("longitudeout");
		final String photo1 = rs.getString("photo1");
		final String photo2 = rs.getString("photo2");
		final String photo3 = rs.getString("photo3");
		final String photo4 = rs.getString("photo4");
		final String photo5 = rs.getString("photo5");
		final String photo6 = rs.getString("photo6");
		final String photo7 = rs.getString("photo7");
		final String photo8 = rs.getString("photo8");
		final String customercode = rs.getString("customercode");
		final Long idcallplan = rs.getLong("idcallplan");
		final String addresscust = rs.getString("addresscust");
		final String provinsicust = rs.getString("provinsicust");
		final String citycust = rs.getString("citycust");
		final String areanamecust = rs.getString("areanamecust");
		final String subarenamecust = rs.getString("subarenamecust");
		final String phonecust = rs.getString("phonecust");
		final String contactpersoncust = rs.getString("contactpersoncust");
		
		
		MonitoringData data = new MonitoringData();
		data.setIdmonitoring(idmonitoring);
		data.setIdusermobile(idusermobile);
		data.setNamauser(namausermobile);
		data.setIdcustomer(idcustomer);
		data.setNamacustomer(namacust);
		data.setNamacustomertype(namacusttype);
		data.setTanggal(tanggal);
		data.setCheckintime(checkintime);
		data.setCheckouttime(checkouttime);
		data.setLatitudein(latitudein);
		data.setLongitudein(longitudein);
		data.setLatitudeout(latitudeout);
		data.setLongitudeout(longitudeout);
		data.setPhoto1(photo1);
		data.setPhoto2(photo2);
		data.setPhoto3(photo3);
		data.setPhoto4(photo4);
		data.setPhoto5(photo5);
		data.setPhoto6(photo6);
		data.setPhoto7(photo7);
		data.setPhoto8(photo8);
		data.setProject("");
		data.setCustomercode(customercode == null ?"":customercode);
		data.setIdcallplan(idcallplan == null ?0:idcallplan.longValue());
		data.setAddress(addresscust);
		data.setProvinsi(provinsicust);
		data.setCity(citycust);
		data.setAreaname(areanamecust);
		data.setSubarename(subarenamecust);
		data.setPhone(phonecust);
		data.setContactperson(contactpersoncust);
		return data;
	}

}
