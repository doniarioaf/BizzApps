package com.servlet.mobile.usermobilecallplan.service;

import java.util.Collection;
import java.util.List;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlan;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanData;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanPK;

public interface UserMobileCallPlanService {
	Object saveUserMobileCallPlan(UserMobileCallPlanPK userMobileCallPlanPK);
	Object saveUserMobileCallPlanList(List<UserMobileCallPlan> list);
	Collection<UserMobileCallPlanData> getListUserMobileCallPlan(long idusermobile);
	Object deleteAllUserMobileCallPlanByListPK(List<UserMobileCallPlanPK> listPK);
}
