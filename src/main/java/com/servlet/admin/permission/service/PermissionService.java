package com.servlet.admin.permission.service;

import java.util.List;

import com.servlet.admin.permission.entity.BodyPermission;
import com.servlet.admin.permission.entity.PermissionData;
import com.servlet.shared.ReturnData;

public interface PermissionService {
	List<PermissionData> getAllListPermission();
	ReturnData savePermission(BodyPermission permission);
}
