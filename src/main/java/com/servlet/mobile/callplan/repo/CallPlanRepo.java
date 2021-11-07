package com.servlet.mobile.callplan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.callplan.entity.CallPlan;

@Repository("CallPlanRepo")
public interface CallPlanRepo extends JpaRepository<CallPlan, Long>{

}
