package com.servlet.mobile.customercallplan.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_customer_call_plan", schema = "public")
public class CustomerCallPlan {
	@EmbeddedId
    private CustomerCallPlanPK customerCallPlanPK;

	public CustomerCallPlanPK getCustomerCallPlanPK() {
		return customerCallPlanPK;
	}

	public void setCustomerCallPlanPK(CustomerCallPlanPK customerCallPlanPK) {
		this.customerCallPlanPK = customerCallPlanPK;
	}
}
