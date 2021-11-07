package com.servlet.mobile.customercallplan.service;

import java.util.Collection;
import java.util.List;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlan;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanData;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanPK;

public interface CustomerCallPlanService {
	Object saveCustomerCallPlan(CustomerCallPlanPK customerCallPlanPK);
	Object saveCustomerCallPlanList(List<CustomerCallPlan> list);
	Collection<CustomerCallPlanData> getListCustomerCallPlan(long idcallplan);
	Object deleteAllCustomeCallPlanByListPK(List<CustomerCallPlanPK> listPK);
}
