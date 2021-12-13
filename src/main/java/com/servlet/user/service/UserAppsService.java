package com.servlet.user.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.servlet.shared.ReturnData;
import com.servlet.user.entity.BodyUserApps;
import com.servlet.user.entity.ReturnLoginApps;
import com.servlet.user.entity.TemplateInternalUser;
import com.servlet.user.entity.UserApps;
import com.servlet.user.entity.UserDetailData;
import com.servlet.user.entity.UserListData;
import com.servlet.user.entity.UserPermissionData;

public interface UserAppsService {
	List<UserApps> getListLogin(HashMap<String, Object> hashparam);
	Collection<UserPermissionData> getListUserPermission(long id);
	ReturnLoginApps actionLogin(String username,String password);
	List<UserApps> getUserLoginByUserName(String username);
	ReturnData saveUserApps(BodyUserApps userapps,long idcompany,long idbranch);
	ReturnData editUserApps(long id,BodyUserApps userapps);
	UserDetailData getDetailUserApps(long id,long idcompany,long idbranch);
	List<UserListData> getListAllUser(long idcompany,long idbranch);
	ReturnData deleteUserApss(long id);
	TemplateInternalUser getTemplate(long idcompany,long idbranch);
}
