package com.servlet.mobile.usermobilecallplan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlan;
import com.servlet.mobile.usermobilecallplan.entity.UserMobileCallPlanPK;

@Repository("UserMobileCallPlanRepo")
public interface UserMobileCallPlanRepo extends JpaRepository<UserMobileCallPlan, UserMobileCallPlanPK>{

}
