package com.servlet.mobile.monitorusermobileinfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfoPK;

@Repository("MonitorUserMobileInfoRepo")
public interface MonitorUserMobileInfoRepo extends JpaRepository<MonitorUserMobileInfo, MonitorUserMobileInfoPK>{

}
