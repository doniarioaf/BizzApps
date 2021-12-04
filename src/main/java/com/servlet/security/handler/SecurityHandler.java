package com.servlet.security.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.servlet.Process.ProcessService;
import com.servlet.admin.usermobile.entity.UserMobile;
import com.servlet.admin.usermobile.entity.UserMobileDataAuth;
import com.servlet.admin.usermobile.entity.UserMobilePermission;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.security.entity.AuthorizationChecking;
import com.servlet.security.entity.AuthorizationData;
import com.servlet.security.entity.AuthorizationEntity;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ProcessReturn;
import com.servlet.shared.Response;
import com.servlet.user.entity.UserApps;
import com.servlet.user.entity.UserPermissionData;
import com.servlet.user.service.UserAppsService;


@Service
public class SecurityHandler implements SecurityService{
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHandler.class);
	@Autowired
	UserAppsService userappsservice;
	@Autowired
	ProcessService processservice;
	@Autowired
	UserMobileService usermobileservice;
	
	@Override
	public Response response(String codepermission, Object data, String authorization) {
		Response value = new Response();
		if(authorization.equals("login")) {
			if(data != null) {
				value.setSuccess(true);
				value.setMessagecode(ConstansCodeMessage.CODE_MESSAGE_SUCCESS);
				value.setMessage("SUCCESS");
				value.setData(data);
				value.setHttpcode(HttpStatus.OK.value());
			}else {
				value.setSuccess(false);
				value.setMessagecode(ConstansCodeMessage.CODE_MESSAGE_USERNAME_OR_PASSWORD_WRONG);
				value.setMessage("Username Or Password Wrong");
				value.setData(data);
				value.setHttpcode(HttpStatus.UNAUTHORIZED.value());
			}
			
		}else if(codepermission.equals("check")) {
			AuthorizationChecking auth = checkingUser(authorization);
			value.setSuccess(auth.isIsvalid());
			value.setMessagecode(auth.getMessageCode());
			value.setMessage(auth.getMessage());
			value.setData(null);
			value.setHttpcode(HttpStatus.OK.value());
		}else if(authorization != null && !authorization.equals("")){
			AuthorizationEntity auth = checking(codepermission,authorization);
			if(auth.isIsvalid()) {
				String[] arcode = codepermission.split("_");
				String action = "";
				if(arcode.length > 0) {
					action = arcode[0];
				}
				
				value.setSuccess(auth.isIsvalid());
				value.setMessagecode("SUCCESS");
				value.setMessage("SUCCESS");
				value.setHttpcode(HttpStatus.OK.value());
				
				try {
					if(action.equals("READ")) {
						ProcessReturn tempdata = processservice.ProcessingReadFunction(codepermission, data,authorization);
						if(tempdata.getData() != null) {
							value.setData(tempdata.getData());
						}else {
							value.setData(data);
						}
						
					}else  {
						ProcessReturn tempdata = processservice.ProcessingFunction(codepermission, data,authorization);
						if(tempdata.isSuccess()) {
							value.setData(tempdata.getData());
							value.setValidations(tempdata.getValidations());
						}else {
							value.setSuccess(tempdata.isSuccess());
							value.setMessagecode(ConstansCodeMessage.DATA_VALIDATION);
							value.setMessage("Terkena Data Validasi");
							value.setHttpcode(tempdata.getHttpcode());
							value.setValidations(tempdata.getValidations());
						}
						
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					value.setSuccess(false);
					value.setMessagecode(ConstansCodeMessage.CODE_MESSAGE_INTERNAL_SERVER_ERROR);
					value.setMessage("Internal Server Error");
					value.setData(null);
					value.setHttpcode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				}
			}else {
				value.setSuccess(auth.isIsvalid());
				value.setMessagecode(auth.getMessageCode());
				value.setMessage(auth.getMessage());
				value.setData(null);
				if(auth.getMessageCode().equals(ConstansCodeMessage.CODE_USER_NOT_HAVE_ACCESS) || auth.getMessageCode().equals(ConstansCodeMessage.CODE_USER_NOT_REGISTRED_ROLE) || auth.getMessageCode().equals("security.api.authorized") || auth.getMessageCode().equals("security.login.authorized")) {
					value.setHttpcode(HttpStatus.UNAUTHORIZED.value());
				}else {
					value.setHttpcode(HttpStatus.UNAUTHORIZED.value());
				}
			}
			
		}else {
			value.setSuccess(false);
			value.setMessagecode("400");
			value.setMessage("Bad Request");
			value.setData(data);
			value.setHttpcode(HttpStatus.BAD_REQUEST.value());
		}
		
		return value;
	}
	
	private boolean checkPasswordToken(String dbPasswordToken,String passwordToken) {
		Gson gson = new Gson();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		String tempPasswordToken = aesEncryptionDecryption.decrypt(passwordToken);
		String tempDbToken = aesEncryptionDecryption.decrypt(dbPasswordToken);
		AuthorizationData data = gson.fromJson(tempDbToken, AuthorizationData.class);
		String tempDbPasswordToken = aesEncryptionDecryption.decrypt(data.getPasswordtoken());
		return tempPasswordToken.equals(tempDbPasswordToken);
	}
	
	private AuthorizationChecking checkingUser(String authorization) {
		AuthorizationChecking auth = new AuthorizationChecking();
		Gson gson = new Gson();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		try {
			String decryption = aesEncryptionDecryption.decrypt(authorization);
			if(decryption != null) {
				auth.setIsvalid(true);
				AuthorizationData data = gson.fromJson(decryption, AuthorizationData.class);
				String tokepassword = aesEncryptionDecryption.decrypt(data.getPasswordtoken());
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("username", data.getUsername());
				param.put("password", data.getPassword());
				
				List<UserApps> list = userappsservice.getUserLoginByUserName(data.getUsername());
				
				UserApps userapps = null;
				for(UserApps arruserapps:list) {
					String passwordDB = aesEncryptionDecryption.decrypt(arruserapps.getPassword());
					if(data.getPassword().equals(passwordDB)) {
						userapps = arruserapps;
						break;
					}
				}
				
				if(userapps != null) {
					if(!checkPasswordToken(userapps.getToken(),data.getPasswordtoken())){
						auth.setIsvalid(false);
						auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_TOKEN_PASSWORD);
						auth.setMessage("Login Not Authorized");
					}
				}else {
					auth.setIsvalid(false);
					auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_LOGIN_NOT_AUTHORIZED);
					auth.setMessage("Login Not Authorized");
				}
				
				
			}
			
		}catch (Exception e) {
			
//			LOGGER.info("AuthorizationEntity "+e.toString());
			// TODO: handle exception
		}
		return auth;
	}

	@Override
	public AuthorizationEntity checking(String codepermission, String authorization) {
		// TODO Auto-generated method stub
		AuthorizationEntity auth = new AuthorizationEntity();
		Gson gson = new Gson();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		try {
			String decryption = aesEncryptionDecryption.decrypt(authorization);
			if(decryption != null) {
				auth.setIsvalid(true);
				AuthorizationData data = gson.fromJson(decryption, AuthorizationData.class);
				String tokepassword = aesEncryptionDecryption.decrypt(data.getPasswordtoken());
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("username", data.getUsername());
				param.put("password", data.getPassword());
				if(data.getTypelogin().equals(ConstansKey.TYPE_WEB)) {
					List<UserApps> list = userappsservice.getUserLoginByUserName(data.getUsername());				
					UserApps userapps = null;
					for(UserApps arruserapps:list) {
						String passwordDB = aesEncryptionDecryption.decrypt(arruserapps.getPassword());
						if(data.getPassword().equals(passwordDB)) {
							userapps = arruserapps;
							break;
						}
					}
					
					if(userapps != null) {
					if(list.size() == 0) {
						auth.setIsvalid(false);
						auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_LOGIN_NOT_AUTHORIZED);
						auth.setMessage("Login Not Authorized");
					}else if(!checkPasswordToken(userapps.getToken(),data.getPasswordtoken())){
						auth.setIsvalid(false);
						auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_TOKEN_PASSWORD);
						auth.setMessage("Login Not Authorized");
					}else {
						boolean flagpermission = false;
						String[] arcode = codepermission.split("_");
						String action = "";
						if(arcode.length > 0) {
							action = arcode[0];
						}
						
							List<UserPermissionData> listp = new ArrayList<UserPermissionData>(userappsservice.getListUserPermission(userapps.getId()));
							if(listp != null && listp.size() > 0) {
								for(UserPermissionData permissiondata : listp) {
									if(permissiondata.getPermissioncode().equals("SUPERUSER")) {
										flagpermission = true;
										break;
									}else if(permissiondata.getPermissioncode().equals("READ_SUPERUSER") && action.toUpperCase().equals("READ")) {
										flagpermission = true;
										break;
									}else if(permissiondata.getPermissioncode().equals(codepermission)) {
										flagpermission = true;
										break;
									}
								}
								if(!flagpermission) {
									auth.setIsvalid(false);
									auth.setMessageCode(ConstansCodeMessage.CODE_USER_NOT_HAVE_ACCESS);
									auth.setMessage("user does not have access");
								}
							}else {
								auth.setIsvalid(false);
								auth.setMessageCode(ConstansCodeMessage.CODE_USER_NOT_REGISTRED_ROLE);
								auth.setMessage("user is not registered in any role");
							}
							
						}
						
					}else {
						auth.setIsvalid(false);
						auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_LOGIN_NOT_AUTHORIZED);
						auth.setMessage("Login Not Authorized");
					}
					
				}else if(data.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {
					List<UserMobileDataAuth> list = usermobileservice.getUserLoginByUserNameMapper(data.getUsername(),data.getIdcompany(),data.getIdbranch()); 
					UserMobileDataAuth usermobile = null;
					for(UserMobileDataAuth user : list) {
						String passwordDB = aesEncryptionDecryption.decrypt(user.getPassword());
						if(passwordDB.equals(data.getPassword())) {
							usermobile = user;
							break;
						}
					}
					if(usermobile != null) {
						if(list.size() == 0) {
							auth.setIsvalid(false);
							auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_LOGIN_NOT_AUTHORIZED);
							auth.setMessage("Login Not Authorized");
						}else if(!checkPasswordToken(usermobile.getToken(),data.getPasswordtoken())){
							auth.setIsvalid(false);
							auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_TOKEN_PASSWORD);
							auth.setMessage("Login Not Authorized");
						}else {
							boolean flagpermission = false;
							String[] arcode = codepermission.split("_");
							String action = "";
							if(arcode.length > 0) {
								action = arcode[0];
							}
							
							List<UserMobilePermission> listp = new ArrayList<UserMobilePermission>(usermobileservice.getListUserMobilePermission(usermobile.getId()));
							if(listp != null && listp.size() > 0) {
								for(UserMobilePermission permissiondata : listp) {
									if(permissiondata.getPermissioncode().equals("SUPERUSER")) {
										flagpermission = true;
										break;
									}else if(permissiondata.getPermissioncode().equals("READ_SUPERUSER") && action.toUpperCase().equals("READ")) {
										flagpermission = true;
										break;
									}else if(permissiondata.getPermissioncode().equals(codepermission)) {
										flagpermission = true;
										break;
									}
								}
								if(!flagpermission) {
									auth.setIsvalid(false);
									auth.setMessageCode(ConstansCodeMessage.CODE_USER_NOT_HAVE_ACCESS);
									auth.setMessage("user does not have access");
								}
							}else {
								auth.setIsvalid(false);
								auth.setMessageCode(ConstansCodeMessage.CODE_USER_NOT_REGISTRED_ROLE);
								auth.setMessage("user is not registered in any role");
							}
						
						}
					}else {
						auth.setIsvalid(false);
						auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_LOGIN_NOT_AUTHORIZED);
						auth.setMessage("Login Not Authorized");
					}
				}
				
				
			}else {
				auth.setIsvalid(false);
				auth.setMessageCode(ConstansCodeMessage.CODE_MESSAGE_SECURITY_LOGIN_NOT_AUTHORIZED);
				auth.setMessage("Not Authorized");
			}
		}catch (Exception e) {
			
//			LOGGER.info("AuthorizationEntity "+e.toString());
			// TODO: handle exception
		}
		return auth;
	}

	@Override
	public String passwordToken(String username, String password) {
		// TODO Auto-generated method stub
		int min = 1000;  
		int max = 99999;
		int randomnumber = (int)(Math.random()*(max-min+1)+min);
		String value = randomnumber+password;
		return value;
	}

}
