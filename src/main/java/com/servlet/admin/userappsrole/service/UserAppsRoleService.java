package com.servlet.admin.userappsrole.service;

import java.util.Collection;
import java.util.List;

import com.servlet.admin.userappsrole.entity.UserAppsRole;
import com.servlet.admin.userappsrole.entity.UserAppsRoleData;
import com.servlet.admin.userappsrole.entity.UserAppsRolePK;

public interface UserAppsRoleService {
	Object saveUserAppsRole(UserAppsRolePK userAppsRolePK);
	Object saveUserAppsRoleList(List<UserAppsRole> listUserAppsRole);
	Collection<UserAppsRoleData> getListUserAppsRole(long iduserapps);
	Object deleteAllUserAppsRolePKPK(List<UserAppsRolePK> listPK);
}
