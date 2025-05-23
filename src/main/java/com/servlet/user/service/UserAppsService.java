package com.servlet.user.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.servlet.admin.userbranch.entity.UserBranchData;
import com.servlet.shared.ReturnData;
import com.servlet.user.entity.*;

public interface UserAppsService {
	List<UserApps> getListLogin(HashMap<String, Object> hashparam);
	Collection<UserPermissionData> getListUserPermission(long id);
	ReturnLoginApps actionLogin(String username,String password,long idbranch);
	ReturnData loginGetListBranch(String username,String password);
	List<UserApps> getUserLoginByUserName(String username);
	ReturnData saveUserApps(BodyUserApps userapps,long idcompany,long idbranch);
	ReturnData editUserApps(long id,BodyUserApps userapps);
	UserDetailData getDetailUserApps(long id,long idcompany,long idbranch);
	List<UserListData> getListAllUser(long idcompany,long idbranch);
	ReturnData deleteUserApss(long id);
	TemplateInternalUser getTemplate(long idcompany,long idbranch);
	ReturnData logout(long id);
	UserListData getUserByID(long iduser);
	ReturnData editPass(BodyEditPass bodyEditPass);
	ReturnData changePassword(long id,BodyEditPass bodyEditPass);
	ReturnData changePasswordUser(long id,BodyEditPass bodyEditPass);
}
