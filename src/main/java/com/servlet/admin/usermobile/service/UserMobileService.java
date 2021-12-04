package com.servlet.admin.usermobile.service;

import java.util.Collection;
import java.util.List;

import com.servlet.admin.usermobile.entity.BodyUserMobile;
import com.servlet.admin.usermobile.entity.UserDetailMobile;
import com.servlet.admin.usermobile.entity.UserMobile;
import com.servlet.admin.usermobile.entity.UserMobileData;
import com.servlet.admin.usermobile.entity.UserMobileDataAuth;
import com.servlet.admin.usermobile.entity.UserMobileListData;
import com.servlet.admin.usermobile.entity.UserMobilePermission;
import com.servlet.shared.ReturnData;

public interface UserMobileService {
	Collection<UserMobilePermission> getListUserMobilePermission(long id);
	UserMobileData actionLogin(String username,String password);
	List<UserMobile> getUserLoginByUserName(String username);
	ReturnData saveUserMobile(BodyUserMobile usermobile,long idcompany,long idbranch);
	ReturnData editUserMobile(long id,BodyUserMobile usermobile);
	UserDetailMobile getDetailUserMobile(long id,long idcompany,long idbranch);
	List<UserMobileListData> getListAllUserMobile(long idcompany,long idbranch);
	List<UserMobileDataAuth> getUserLoginByUserNameMapper(String username,long idcompany,long idbranch);
	List<UserMobileListData> getListAllUserMobileForMonitoring(String listid,long idcompany,long idbranch);
}
