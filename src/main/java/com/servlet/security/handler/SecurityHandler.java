package com.servlet.security.handler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.servlet.Process.ProcessService;
import com.servlet.admin.company.entity.Company;
import com.servlet.admin.company.service.CompanyService;
import com.servlet.admin.logs.entity.LogsActivity;
import com.servlet.admin.logs.service.LogsService;
import com.servlet.admin.usermobile.entity.ReturnLoginMobile;
import com.servlet.admin.usermobile.entity.UserMobile;
import com.servlet.admin.usermobile.entity.UserMobileDataAuth;
import com.servlet.admin.usermobile.entity.UserMobilePermission;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.security.entity.AuthorizationChecking;
import com.servlet.security.entity.AuthorizationData;
import com.servlet.security.entity.AuthorizationEntity;
import com.servlet.security.entity.LicenseData;
import com.servlet.security.entity.SecurityLicenseData;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.AESEncryptionDecryptionLicense;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.GlobalFunc;
import com.servlet.shared.ProcessReturn;
import com.servlet.shared.Response;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.user.entity.ReturnLoginApps;
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
	@Autowired
	CompanyService companyService;
	@Autowired
	LogsService logsService;
	
	@Override
	public Response response(String codepermission, Object data, String authorization) {
		Response value = new Response();
		if(authorization.equals("loginweb") || authorization.equals("loginmobile")) {
			if(authorization.equals("loginmobile")) {
				ReturnLoginMobile mobile = (ReturnLoginMobile) data;
				if(mobile.getReturnData() != null) {
					ReturnData returndata = mobile.getReturnData();
					if(returndata.isSuccess()) {
						value.setSuccess(true);
						value.setMessagecode(ConstansCodeMessage.CODE_MESSAGE_SUCCESS);
						value.setMessage("SUCCESS");
						value.setData(mobile.getUserMobileData());
						value.setHttpcode(HttpStatus.OK.value());
						value.setValidations(returndata.getValidations());
						
						setLogs(mobile.getIdcompany(), mobile.getIdbranch(), mobile.getUsername(), "loginmobile", "SUCCESS");
					}else {
						value.setSuccess(false);
						value.setMessagecode(ConstansCodeMessage.DATA_VALIDATION);
						value.setMessage("Data Validasi");
						value.setData(null);
						value.setHttpcode(HttpStatus.UNAUTHORIZED.value());
						value.setValidations(returndata.getValidations());
						
						if(returndata.getValidations().size() > 0) {
							setLogs(mobile.getIdcompany(), mobile.getIdbranch(), mobile.getUsername(), "loginmobile", "Failed ("+ returndata.getValidations().get(0).getMessage() +")");
						}else {
							setLogs(mobile.getIdcompany(), mobile.getIdbranch(), mobile.getUsername(), "loginmobile", "Failed");
						}
						
					}
				}else {
					value.setSuccess(false);
					value.setMessagecode(ConstansCodeMessage.CODE_MESSAGE_USERNAME_OR_PASSWORD_WRONG);
					value.setMessage("Username Or Password Wrong");
					value.setData(null);
					value.setHttpcode(HttpStatus.UNAUTHORIZED.value());
					
					if(!mobile.getUsername().equals("")) {
						setLogs(mobile.getIdcompany(), mobile.getIdbranch(), mobile.getUsername(), "loginmobile", "Failed (Username Or Password Wrong)");
					}
				}
			}else if(authorization.equals("loginweb")) {
				ReturnLoginApps web = (ReturnLoginApps) data;
				if(web.getReturnData() != null) {
					ReturnData returndata = web.getReturnData();
					if(returndata.isSuccess()) {
						value.setSuccess(true);
						value.setMessagecode(ConstansCodeMessage.CODE_MESSAGE_SUCCESS);
						value.setMessage("SUCCESS");
						value.setData(web.getUserData());
						value.setHttpcode(HttpStatus.OK.value());
						value.setValidations(returndata.getValidations());
						
						setLogs(web.getIdcompany(), web.getIdbranch(), web.getUsername(), "loginweb", "SUCCESS");
					}else {
						value.setSuccess(false);
						value.setMessagecode(ConstansCodeMessage.DATA_VALIDATION);
						value.setMessage("Data Validasi");
						value.setData(null);
						value.setHttpcode(HttpStatus.UNAUTHORIZED.value());
						value.setValidations(returndata.getValidations());
						
						if(returndata.getValidations().size() > 0) {
							setLogs(web.getIdcompany(), web.getIdbranch(), web.getUsername(), "loginweb", "Failed ("+ returndata.getValidations().get(0).getMessage() +")");
						}else {
							setLogs(web.getIdcompany(), web.getIdbranch(), web.getUsername(), "loginweb", "Failed");
						}
					}
					
				}else {
					value.setSuccess(false);
					value.setMessagecode(ConstansCodeMessage.CODE_MESSAGE_USERNAME_OR_PASSWORD_WRONG);
					value.setMessage("Username Or Password Wrong");
					value.setData(null);
					value.setHttpcode(HttpStatus.UNAUTHORIZED.value());
					
					if(!web.getUsername().equals("")) {
						setLogs(web.getIdcompany(), web.getIdbranch(), web.getUsername(), "loginweb", "Failed (Username Or Password Wrong)");
					}
				}
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
			if(auth.isIsvalid() || codepermission.equals(ConstansPermission.DELETE_COMPANYY) || codepermission.equals(ConstansPermission.LOGOUT)) {
				Gson gson = new Gson();
				AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
				
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
						if(!codepermission.equals(ConstansPermission.DELETE_COMPANYY) && !auth.getUsername().equals("")) {
							setLogs(auth.getIdcompany(), auth.getIdbranch(), auth.getUsername(), codepermission, "SUCCESS");
						}
						
					}else  {
						ProcessReturn tempdata = processservice.ProcessingFunction(codepermission, data,authorization);
						if(tempdata.isSuccess()) {
							value.setData(tempdata.getData());
							value.setValidations(tempdata.getValidations());
							
							if(!codepermission.equals(ConstansPermission.DELETE_COMPANYY) && !auth.getUsername().equals("")) {
								setLogs(auth.getIdcompany(), auth.getIdbranch(), auth.getUsername(), codepermission, "SUCCESS");
							}
						}else {
							value.setSuccess(tempdata.isSuccess());
							value.setMessagecode(ConstansCodeMessage.DATA_VALIDATION);
							value.setMessage("Terkena Data Validasi");
							value.setHttpcode(tempdata.getHttpcode());
							value.setValidations(tempdata.getValidations());
							
							if(!codepermission.equals(ConstansPermission.DELETE_COMPANYY) && !auth.getUsername().equals("")) {
								if(tempdata.getValidations().size() > 0) {
									setLogs(auth.getIdcompany(), auth.getIdbranch(), auth.getUsername(), codepermission, "Failed ("+tempdata.getValidations().get(0).getMessage()+")");
								}else {
									setLogs(auth.getIdcompany(), auth.getIdbranch(), auth.getUsername(), codepermission, "Failed");
								}
							}
							
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
					
					if(!codepermission.equals(ConstansPermission.DELETE_COMPANYY)) {
						setLogs(auth.getIdcompany(), auth.getIdbranch(), auth.getUsername(), codepermission, "Failed (Internal Server Error)");
					}
					
					
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
	
	private boolean setLogs(long idcompany,long idbranch,String username,String activity,String msg) {
		LogsActivity table = new LogsActivity();
		table.setIdcompany(idcompany);
		table.setIdbranch(idbranch);
		table.setUsername(username);
		table.setActivity(activity);
		table.setMessage(msg);
		logsService.saveLogs(table);
		return true;
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
		
		String tempUser = "";
		long idcompany = 0;
		long idbranch = 0;
		try {
			String decryption = aesEncryptionDecryption.decrypt(authorization);
			if(decryption != null) {
				auth.setIsvalid(true);
				AuthorizationData data = gson.fromJson(decryption, AuthorizationData.class);
				tempUser = data.getUsername();
				idcompany = data.getIdcompany();
				idbranch = data.getIdbranch();
				SecurityLicenseData license = checkLicense(data.getIdcompany(), null, null);
//				String tokepassword = aesEncryptionDecryption.decrypt(data.getPasswordtoken());
				if(license.getReturnData().isSuccess()) {
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
				List<ValidationDataMessage> validations = license.getReturnData().getValidations();
				if(validations.size() > 0) {
					ValidationDataMessage valmsg = validations.get(0);
					auth.setMessageCode(valmsg.getMessageCode());
					auth.setMessage(valmsg.getMessage());
				}
			}
			if(!auth.isIsvalid()) {
				setLogs(data.getIdcompany(), data.getIdbranch(), data.getUsername(), codepermission, auth.getMessage());
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
		
		auth.setUsername(tempUser);
		auth.setIdcompany(idcompany);
		auth.setIdbranch(idbranch);
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

	@Override
	public SecurityLicenseData checkLicense(long idcompany,Long jumlahuserweb, Long jumlahusermobile) {
		// TODO Auto-generated method stub
		SecurityLicenseData dataSecurity = new SecurityLicenseData();
		Gson gson = new Gson();
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		LicenseData data = null;
		Company company = companyService.getCompanyByID(idcompany);
		if(company != null) {
			String license = company.getLicense();
			if(license != null && !license.equals("")) {
				AESEncryptionDecryptionLicense aesEncryptionDecryption = new AESEncryptionDecryptionLicense();
				try {
					String decryption = aesEncryptionDecryption.decrypt(license);
					data = new LicenseData();
					data = gson.fromJson(decryption, LicenseData.class);
					
					Timestamp ts = new Timestamp(new Date().getTime());
					if(ts.after(data.getExpired())) {
						ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.COMPANY_LICENSE_EXPIRED,"Company License Sudah Expired");
						validations.add(msg);
					}else {
						Timestamp tanggalExpired = GlobalFunc.setFormatDate(data.getExpired(), "yyyy-MM-dd");
						
						//ada alert 7 hari sebelum expired habis
						Timestamp add7 = GlobalFunc.addDays(ts, 7);
						boolean flagAlertDate = false;
						if(add7.after(data.getExpired()) ) {
							flagAlertDate = true;
						}else if(add7.equals(data.getExpired()) ) {
							flagAlertDate = true;
						}
						if(flagAlertDate) {
							ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.COMPANY_LICENSE_ALERT_EXPIRED,"License habis pada tanggal "+tanggalExpired);
							validations.add(msg);
						}
						if(jumlahuserweb != null) {
							long userweb = jumlahuserweb.longValue();
							if(userweb > data.getLimituserweb()) {
								ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.LIMIT_CREATE_USER,"Tidak Bisa Membuat User, Limit Sudah Habis");
								validations.add(msg);
							}
						}
						
						if(jumlahusermobile != null) {
							long usermobile = jumlahusermobile.longValue();
							if(usermobile > data.getLimitusermobile()) {
								ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.LIMIT_CREATE_USER,"Tidak Bisa Membuat User, Limit Sudah Habis");
								validations.add(msg);
							}
						}
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.COMPANY_LICENSE_NOT_EXIST,"Company License Belum di setting");
				validations.add(msg);
			}
		}else {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.USER_COMPANY_NOT_EXIST,"Company User Tidak ditemukan");
			validations.add(msg);
		}
		ReturnData datareturn = new ReturnData();
		datareturn.setId(0);
		datareturn.setSuccess(validations.size() > 0?false:true);
		if(validations.size() == 1) {
			if(validations.get(0).getMessageCode().equals(ConstansCodeMessage.COMPANY_LICENSE_ALERT_EXPIRED)) {
				datareturn.setSuccess(true);
			}
		}
		datareturn.setValidations(validations);
		
		dataSecurity.setReturnData(datareturn);
		dataSecurity.setLicenseData(data);
		return dataSecurity;
	}

}
