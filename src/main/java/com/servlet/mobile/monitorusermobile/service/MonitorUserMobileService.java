package com.servlet.mobile.monitorusermobile.service;
import java.util.List;

import com.servlet.mobile.monitorusermobile.entity.BodyListPhoto;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.entity.ReturnListData;
import com.servlet.shared.ReturnData;

public interface MonitorUserMobileService {
	List<ReturnListData> saveMonitorUserMobile(BodyMonitorUserMobile body,long iduser,long idcompany,long idbranch);
	ReturnData editMonitorUserMobile(BodyMonitorUserMobile body,long idmonitor,long iduser,long idcompany,long idbranch);
	List<ReturnListData> savePhotoMonitorUserMobile(BodyListPhoto body,long iduser,long idcompany,long idbranch);
}
