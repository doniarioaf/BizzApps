package com.servlet.mobile.customercallplan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlan;
import com.servlet.mobile.customercallplan.entity.CustomerCallPlanPK;

@Repository("CustomerCallPlanRepo")
public interface CustomerCallPlanRepo extends JpaRepository<CustomerCallPlan, CustomerCallPlanPK>{

}
