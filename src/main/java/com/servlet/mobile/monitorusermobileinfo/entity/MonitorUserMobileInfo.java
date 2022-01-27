package com.servlet.mobile.monitorusermobileinfo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_monitor_user_mobile_info", schema = "public")
public class MonitorUserMobileInfo {
	
	@EmbeddedId
    private MonitorUserMobileInfoPK monitorUserMobileInfoPK;
	
	@Column(name = "infoanswer", insertable=true, updatable=true)
	private String infoanswer;

	public String getInfoanswer() {
		return infoanswer;
	}

	public void setInfoanswer(String infoanswer) {
		this.infoanswer = infoanswer;
	}

	public MonitorUserMobileInfoPK getMonitorUserMobileInfoPK() {
		return monitorUserMobileInfoPK;
	}

	public void setMonitorUserMobileInfoPK(MonitorUserMobileInfoPK monitorUserMobileInfoPK) {
		this.monitorUserMobileInfoPK = monitorUserMobileInfoPK;
	}
}
