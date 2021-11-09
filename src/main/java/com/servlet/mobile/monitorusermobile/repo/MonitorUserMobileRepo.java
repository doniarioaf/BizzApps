package com.servlet.mobile.monitorusermobile.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.monitorusermobile.entity.MonitorUserMobile;

@Repository("MonitorUserMobileRepo")
public interface MonitorUserMobileRepo extends JpaRepository<MonitorUserMobile, Long>{

}
