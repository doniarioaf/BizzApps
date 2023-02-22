package com.servlet.registrasiapps.service;

import java.util.List;

import com.servlet.registrasiapps.entity.RegistrasiAppsData;

public interface RegistrasiAppsService {
	List<RegistrasiAppsData> getListRegistrasiApps(Long idcompany,Long idbranch);
	boolean checkKeyApps(Long idcompany,Long idbranch,String keyApps);
}
