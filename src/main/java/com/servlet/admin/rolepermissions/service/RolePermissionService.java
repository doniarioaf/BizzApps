package com.servlet.admin.rolepermissions.service;

import java.util.Collection;
import java.util.List;

import com.servlet.admin.role.entity.RolePermissionData;
import com.servlet.admin.rolepermissions.entity.RolePermissions;
import com.servlet.admin.rolepermissions.entity.RolePermissionsPK;

public interface RolePermissionService {
	Object saveRolePermissions(RolePermissions rolePermissions);
	Object saveRolePermissionsList(List<RolePermissions> listrolePermissions);
	Collection<RolePermissionData> getListRolePermissions(long idrole);
	Object deleteAllrolePermissionsByListPK(List<RolePermissionsPK> listPK);
}
