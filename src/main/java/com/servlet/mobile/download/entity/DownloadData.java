package com.servlet.mobile.download.entity;

import java.util.List;

import com.servlet.mobile.infoheader.entity.InfoHeaderDetailData;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanDataMobile;

public class DownloadData {
	private List<InfoHeaderDetailData> listinfo;
//	private UserMobileCallPlanDataMobile listcallplan;
	public List<InfoHeaderDetailData> getListinfo() {
		return listinfo;
	}
	public void setListinfo(List<InfoHeaderDetailData> listinfo) {
		this.listinfo = listinfo;
	}
//	public UserMobileCallPlanDataMobile getListcallplan() {
//		return listcallplan;
//	}
//	public void setListcallplan(UserMobileCallPlanDataMobile listcallplan) {
//		this.listcallplan = listcallplan;
//	}
}
