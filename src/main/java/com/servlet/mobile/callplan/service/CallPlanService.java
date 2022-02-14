package com.servlet.mobile.callplan.service;

import java.util.List;

import com.servlet.mobile.callplan.entity.BodyCallPlan;
import com.servlet.mobile.callplan.entity.CallPlanDetailData;
import com.servlet.mobile.callplan.entity.CallPlanListData;
import com.servlet.mobile.callplan.entity.TemplateDataCallPlan;
import com.servlet.mobile.project.entity.ProjectData;
import com.servlet.shared.ReturnData;

public interface CallPlanService {
	ReturnData saveCallPlan(BodyCallPlan callplan,long idcompany,long idbranch);
	ReturnData updateCallPlan(long id,BodyCallPlan callplan,long idcompany,long idbranch);
	List<CallPlanListData> getAllListCallPlan(long idcompany,long idbranch);
	CallPlanDetailData getCallPlanById(long id,long idcompany,long idbranch);
	TemplateDataCallPlan getTemplate(long idcompany,long idbranch);
	ReturnData deleteCallPlan(long id);
	CallPlanListData getCallByName(String nama,long idcompany,long idbranch);
	String getProjectNameByIdCallPlan(long id,long idcompany,long idbranch);
	ProjectData getProjectNumberByIdCallPlanName(String nama,long idcompany,long idbranch);
}
