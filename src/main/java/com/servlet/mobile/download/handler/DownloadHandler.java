package com.servlet.mobile.download.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servlet.mobile.customercallplan.entity.DownloadCustomerCallPlan;
import com.servlet.mobile.customercallplan.service.CustomerCallPlanService;
import com.servlet.mobile.download.entity.DownloadData;
import com.servlet.mobile.download.service.DownloadService;
import com.servlet.mobile.infoheader.entity.InfoHeaderDetailData;
import com.servlet.mobile.infoheader.service.InfoHeaderService;
import com.servlet.mobile.usermobilecallplan.entity.DownloadUserMobileCallPlan;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanDataMobile;
import com.servlet.mobile.usermobilecallplan.service.UserMobileCallPlanService;

@Service
public class DownloadHandler implements DownloadService{
	@Autowired
	private InfoHeaderService infoHeaderService;
	@Autowired
	private UserMobileCallPlanService userMobileCallPlanService;
	@Autowired
	private CustomerCallPlanService customerCallPlanService;
	
	@Override
	public DownloadData donwload(long iduser, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
//		UserMobileCallPlanDataMobile datacallplan = userMobileCallPlanService.getDataForMobile(iduser, idcompany, idbranch);
		List<InfoHeaderDetailData> info = infoHeaderService.getAllListDataMobile(idcompany, idbranch);
		DownloadData data = new DownloadData();
//		data.setListcallplan(datacallplan);
		data.setListinfo(info);
		return data;
	}

	@Override
	public DownloadUserMobileCallPlan donwloadUserMobileCallPlan(long iduser, long idcompany, long idbranch, long limit,
			long offset) {
		// TODO Auto-generated method stub
		return userMobileCallPlanService.downloadUserMobileCallPlanByIdUserMobile(iduser, idcompany, idbranch, limit, offset);
	}

	@Override
	public DownloadCustomerCallPlan donwloadCustomerCallPlan(long iduser, long idcompany, long idbranch, long limit,
			long offset) {
		// TODO Auto-generated method stub
		return customerCallPlanService.getListCustomerCallPlanByIdUserPaging(iduser, idcompany, idbranch, limit, offset);
	}

}
