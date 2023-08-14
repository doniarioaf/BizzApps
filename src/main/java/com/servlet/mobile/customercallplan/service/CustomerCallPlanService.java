package com.servlet.mobile.customercallplan.service;

import java.util.Collection;
import java.util.List;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlan;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanPK;
import com.servlet.mobile.customercallplan.entity.DownloadCustomerCallPlan;

public interface CustomerCallPlanService {
	Object saveCustomerCallPlan(CustomerCallPlanPK customerCallPlanPK);
	Object saveCustomerCallPlanList(List<CustomerCallPlan> list);
	Collection<CustomerCallPlanData> getListCustomerCallPlan(long idcallplan);
	Object deleteAllCustomeCallPlanByListPK(List<CustomerCallPlanPK> listPK);
	List<CustomerCallPlanData> getListCustomerCallPlanByIdUser(long idusermobile,long idcompany,long idbranch);
	DownloadCustomerCallPlan getListCustomerCallPlanByIdUserPaging(long idusermobile,long idcompany,long idbranch,long limit,long offset);
	boolean getCustCallPlanByPK(CustomerCallPlanPK custcallplanPK);
}
