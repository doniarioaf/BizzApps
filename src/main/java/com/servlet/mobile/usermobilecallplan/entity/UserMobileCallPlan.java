package com.servlet.mobile.usermobilecallplan.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "m_user_mobile_call_plan", schema = "public")
public class UserMobileCallPlan {
	@EmbeddedId
    private UserMobileCallPlanPK userMobileCallPlanPK;

	public UserMobileCallPlanPK getUserMobileCallPlanPK() {
		return userMobileCallPlanPK;
	}

	public void setUserMobileCallPlanPK(UserMobileCallPlanPK userMobileCallPlanPK) {
		this.userMobileCallPlanPK = userMobileCallPlanPK;
	}

}
