package com.servlet.mobile.monitorusermobile.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.mobile.monitorusermobile.entity.DataMonitorForMaps;


public class GetDataMonitorForMaps implements RowMapper<DataMonitorForMaps>{
	
	private String schemaSql;
	
	public GetDataMonitorForMaps() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("monitor.latitudein as monitorlat, monitor.longitudein as monitorlong, ");
		sqlBuilder.append("monitor.checkintime, cust.latitude as custlat, cust.longitude as custlong, ");
		sqlBuilder.append("cust.nama as custnama , custtype.nama as custtypenama ");
		sqlBuilder.append("from m_monitor_user_mobile as monitor ");
		sqlBuilder.append("join m_customer as cust on cust.id = monitor.idcustomer ");
		sqlBuilder.append("left join m_customer_type as custtype on custtype.id = cust.idcustomertype ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DataMonitorForMaps mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final String monitorlat = rs.getString("monitorlat");
		final String monitorlong = rs.getString("monitorlong");
		final String checkintime = rs.getString("checkintime");
		final String custlat = rs.getString("custlat");
		final String custlong = rs.getString("custlong");
		final String custnama = rs.getString("custnama");
		final String custtypenama = rs.getString("custtypenama");
		
		DataMonitorForMaps data = new DataMonitorForMaps();
		data.setLatitude(monitorlat);
		data.setLongitude(monitorlong);
		data.setCheckintime(checkintime);
		data.setCustlatitude(custlat);
		data.setCustlongitude(custlong);
		data.setCustnama(custnama);
		data.setCusttypenama(custtypenama);
		
		return data;
	}

}
