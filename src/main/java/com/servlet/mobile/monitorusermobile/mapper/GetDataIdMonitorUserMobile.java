package com.servlet.mobile.monitorusermobile.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.mobile.monitorusermobile.entity.MonitorUserMobile;
public class GetDataIdMonitorUserMobile implements RowMapper<MonitorUserMobile> {
	
	private String schemaSql;
	
	public GetDataIdMonitorUserMobile() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* ");
		sqlBuilder.append("from m_monitor_user_mobile as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public MonitorUserMobile mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idcompany = rs.getLong("idcompany");
		final Long idbranch = rs.getLong("idbranch");
		final Long idproject = rs.getLong("idproject");
		final Long idusermobile = rs.getLong("idusermobile");
		final Long idcustomer = rs.getLong("idcustomer");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
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
		final Timestamp created = rs.getTimestamp("created");
		final Timestamp modified = rs.getTimestamp("modified");
		
		MonitorUserMobile data = new MonitorUserMobile();
		data.setId(id);
		data.setIdcompany(idcompany);
		data.setIdbranch(idbranch);
		data.setIdproject(idproject);
		data.setIdusermobile(idusermobile);
		data.setIdcustomer(idcustomer);
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
		data.setCreated(created);
		data.setModified(modified);
		return data;
	}

}
