package com.servlet.admin.role.service;

import java.util.List;

import com.servlet.admin.role.entity.BodyRole;
import com.servlet.admin.role.entity.Role;
import com.servlet.admin.role.entity.RoleDetail;
import com.servlet.shared.ReturnData;

public interface RoleService {
	List<Role> getAllListRole(long idcompany, long idbranch);
	ReturnData saveRole(BodyRole role,long idcompany,long idbranch);
	ReturnData updateRole(long id,BodyRole role);
	RoleDetail getRoleDetail(long id,long idcompany, long idbranch);
}
