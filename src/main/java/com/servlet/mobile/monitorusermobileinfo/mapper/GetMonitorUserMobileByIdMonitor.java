package com.servlet.mobile.monitorusermobileinfo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfoPK;

public class GetMonitorUserMobileByIdMonitor implements RowMapper<MonitorUserMobileInfo>{
	private String schemaSql;
	
	public GetMonitorUserMobileByIdMonitor(){
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("* from m_monitor_user_mobile_info as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public MonitorUserMobileInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final long idmonitorusermobile = rs.getLong("idmonitorusermobile");
		final long infoid = rs.getLong("infoid");
		final long idinfodetail = rs.getLong("idinfodetail");
		final String infoanswer = rs.getString("infoanswer");
		
		MonitorUserMobileInfoPK pk = new MonitorUserMobileInfoPK();
		pk.setIdmonitorusermobile(idmonitorusermobile);
		pk.setInfoid(infoid);
		pk.setIdinfodetail(idinfodetail);
		MonitorUserMobileInfo data = new MonitorUserMobileInfo();
		data.setMonitorUserMobileInfoPK(pk);
		data.setInfoanswer(infoanswer);
		return data;
	}

}
