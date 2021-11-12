package com.servlet.mobile.usermobilelocation.service;
import com.servlet.mobile.usermobilelocation.entity.BodyUserMobileLocation;
import com.servlet.shared.ReturnData;

public interface UserMobileLocationService {
	ReturnData saveUserMobileLocation(BodyUserMobileLocation body,long idusermobile,long idcompany,long idbranch);
}
