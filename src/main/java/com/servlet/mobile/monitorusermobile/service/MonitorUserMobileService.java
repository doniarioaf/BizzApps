package com.servlet.mobile.monitorusermobile.service;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.shared.ReturnData;

public interface MonitorUserMobileService {
	ReturnData saveMonitorUserMobile(BodyMonitorUserMobile body,long iduser,long idcompany,long idbranch);
	ReturnData editMonitorUserMobile(BodyMonitorUserMobile body,long idmonitor,long iduser,long idcompany,long idbranch);
}
