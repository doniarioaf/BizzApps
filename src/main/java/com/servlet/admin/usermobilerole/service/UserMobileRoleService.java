package com.servlet.admin.usermobilerole.service;

import java.util.Collection;
import java.util.List;
import com.servlet.admin.usermobilerole.entity.UserMobileRole;
import com.servlet.admin.usermobilerole.entity.UserMobileRoleData;
import com.servlet.admin.usermobilerole.entity.UserMobileRolePK;

public interface UserMobileRoleService {
	Object saveUserMobileRole(UserMobileRolePK userMobileRolePK);
	Object saveUserMobileRoleList(List<UserMobileRole> listUserMobileRole);
	Collection<UserMobileRoleData> getListUserMobileRole(long idusermobile);
	Object deleteAllUserMobileRolePKPK(List<UserMobileRolePK> listPK);
}
