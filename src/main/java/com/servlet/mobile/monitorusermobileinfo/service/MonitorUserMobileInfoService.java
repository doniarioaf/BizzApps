package com.servlet.mobile.monitorusermobileinfo.service;

import java.util.List;

import com.servlet.mobile.monitorusermobileinfo.entity.DetailInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfoPK;

public interface MonitorUserMobileInfoService {
	Object saveMonitorUserMobileInfoList(List<MonitorUserMobileInfo> list);
	List<MonitorUserMobileInfo> getListData(long idmonitoruser);
	Object deleteAllMonitorUserMobileInfoByListPK(List<MonitorUserMobileInfoPK> listPK) ;
	List<Long> getListDistinctUserMobileInfo(long idmonitoruser);
	List<DetailInfo> getDetailInfo(long infoid);
}
