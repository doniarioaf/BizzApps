package com.servlet.mobile.download.service;

import com.servlet.mobile.customercallplan.entity.DownloadCustomerCallPlan;
import com.servlet.mobile.download.entity.DownloadData;
import com.servlet.mobile.usermobilecallplan.entity.DownloadUserMobileCallPlan;

public interface DownloadService {
	DownloadData donwload(long iduser, long idcompany, long idbranch);
	DownloadUserMobileCallPlan donwloadUserMobileCallPlan(long iduser, long idcompany, long idbranch,long limit, long offset);
	DownloadCustomerCallPlan donwloadCustomerCallPlan(long iduser, long idcompany, long idbranch,long limit, long offset);
}
