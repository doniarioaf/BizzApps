package com.servlet.security.service;

import com.servlet.security.entity.AuthorizationEntity;
import com.servlet.shared.Response;

public interface SecurityService {
	AuthorizationEntity checking(String codepermission,String authorization);
	Response response(String codepermission,Object data,String authorization);
	String passwordToken(String username,String password);
}
