package com.servlet.Process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.servlet.address.service.DistrictService;
import com.servlet.address.service.PostalCodeService;
import com.servlet.address.service.SubDistrictService;
import com.servlet.admin.branch.entity.BodyBranch;
import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.entity.BranchData;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.admin.company.entity.BodyCompany;
import com.servlet.admin.company.entity.BodyCompanyy;
import com.servlet.admin.company.service.CompanyService;
import com.servlet.admin.permission.entity.BodyPermission;
import com.servlet.admin.permission.service.PermissionService;
import com.servlet.admin.role.entity.BodyRole;
import com.servlet.admin.role.service.RoleService;
import com.servlet.admin.usermobile.entity.BodyUserMobile;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.security.entity.AuthorizationData;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.ProcessReturn;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.user.entity.BodyUserApps;
import com.servlet.user.service.UserAppsService;

@Service
public class ProcessHandler implements ProcessService{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessHandler.class);
	
	@Autowired
	ProcessService processservice;
	@Autowired
	BranchService branchservice;
	@Autowired
	CompanyService companyService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserAppsService userAppsService;
	@Autowired
	UserMobileService userMobileService;
	@Autowired
	PermissionService permissionService;
	@Autowired
	DistrictService districtService;
	@Autowired
	SubDistrictService subdistrictService;
	@Autowired
	PostalCodeService postalCodeService;
	
	@Override
	public ProcessReturn ProcessingFunction(String codepermission,Object data,String authorization) {
		
		// TODO Auto-generated constructor stub
//		Object val = null;
		ProcessReturn val = new ProcessReturn();
		val.setHttpcode(HttpStatus.OK.value());
		val.setSuccess(true);
		val.setValidations(new ArrayList<ValidationDataMessage>());
//		val.setMessageCode("");
//		val.setMessage("");
		
		Gson gson = new Gson();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		String decryption = aesEncryptionDecryption.decrypt(authorization);
		AuthorizationData auth = gson.fromJson(decryption, AuthorizationData.class);
		if(auth.getTypelogin().equals(ConstansKey.TYPE_WEB)) {
			if(codepermission.equals(ConstansPermission.CREATE_BRANCH)) {
				BodyBranch branch = (BodyBranch) data;
				val.setData(branchservice.saveBranch(branch));
			}else if(codepermission.equals(ConstansPermission.EDIT_BRANCH)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyBranch branch = (BodyBranch) param.get("BodyBranch");
				long id = (long) param.get("id");
				val.setData(branchservice.updateBranch(id, branch));
			}else if(codepermission.equals(ConstansPermission.DELETE_BRANCH)) {
				long id = (long) data;
				val.setData(branchservice.deleteBranch(id));
			}else if(codepermission.equals(ConstansPermission.CREATE_COMPANY)) {
				BodyCompany body = (BodyCompany) data;
				val.setData(companyService.saveCompany(body));
			}else if(codepermission.equals(ConstansPermission.EDIT_COMPANY)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCompany body = (BodyCompany) param.get("BodyCompany");
				long id = (long) param.get("id");
				val.setData(companyService.updateCompany(id, body));
			}else if(codepermission.equals(ConstansPermission.DELETE_COMPANY)) {
				long id = (long) data;
				val.setData(companyService.deleteCompany(id));
			}else if(codepermission.equals(ConstansPermission.DELETE_COMPANYY)) {
				BodyCompanyy body = (BodyCompanyy) data;
				ReturnData valReturn = companyService.updateCompanyy(body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_ACTIVATED_COMPANY)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				long id = (long) param.get("id");
				if(type.equals("ACTIVATED")) {
//					val = companyService.activatedCompany(id);
					val.setData(companyService.activatedCompany(id));
				}else if(type.equals("UNACTIVATED")) {
					val.setData(companyService.unActivatedCompany(id));
//					val = companyService.unActivatedCompany(id);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_ROLE)) {
				BodyRole body = (BodyRole) data;
				val.setData(roleService.saveRole(body,auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.EDIT_ROLE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyRole body = (BodyRole) param.get("BodyRole");
				long id = (long) param.get("id");
				val.setData(roleService.updateRole(id, body));
			}else if(codepermission.equals(ConstansPermission.DELETE_ROLE)) {
				long id = (long) data;
				val.setData(roleService.deleteRole(id));
			}else if(codepermission.equals(ConstansPermission.CREATE_USER)) {
				BodyUserApps body = (BodyUserApps) data;
				ReturnData valReturn = userAppsService.saveUserApps(body,auth.getIdcompany(),auth.getIdbranch());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
//				val.setData(userAppsService.saveUserApps(body,auth.getIdcompany(),auth.getIdbranch()));
//				val = userAppsService.saveUserApps(body,auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_USER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyUserApps body = (BodyUserApps) param.get("BodyUserApps");
				long id = (long) param.get("id");
				val.setData(userAppsService.editUserApps(id, body));
//				val = userAppsService.editUserApps(id, body);
			}else if(codepermission.equals(ConstansPermission.DELETE_USER)) {
				long id = (long) data;
				val.setData(userAppsService.deleteUserApss(id));
			}else if(codepermission.equals(ConstansPermission.CREATE_USER_MOBILE)) {
				BodyUserMobile body = (BodyUserMobile) data;
				ReturnData valReturn = userMobileService.saveUserMobile(body,auth.getIdcompany(),auth.getIdbranch());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_USER_MOBILE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyUserMobile body = (BodyUserMobile) param.get("BodyUserMobile");
				long id = (long) param.get("id");
				
				ReturnData valReturn = userMobileService.editUserMobile(id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_USER_MOBILE)) {
				long id = (long) data;
				val.setData(userMobileService.deleteUserMobile(id));
			}else if(codepermission.equals(ConstansPermission.CREATE_MAINTENANCE)) {
				BodyPermission body = (BodyPermission) data;
				ReturnData valReturn = permissionService.savePermission(body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}	
			}else if(codepermission.equals(ConstansPermission.LOGOUT)) {
				ReturnData valReturn = userAppsService.logout(auth.getId());
				val.setSuccess(valReturn.isSuccess());
				val.setData(null);
			}
		}
		
		
		return val;
	}

	@Override
	public ProcessReturn ProcessingReadFunction(String codepermission, Object data, String authorization) {
		// TODO Auto-generated method stub
//		Object val = null;
		
		ProcessReturn val = new ProcessReturn();
		val.setHttpcode(HttpStatus.OK.value());
		val.setSuccess(true);
		val.setValidations(new ArrayList<ValidationDataMessage>());
//		val.setMessageCode("");
//		val.setMessage("");
		val.setData(null);
		
		Gson gson = new Gson();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		String decryption = aesEncryptionDecryption.decrypt(authorization);
		AuthorizationData auth = gson.fromJson(decryption, AuthorizationData.class);
		if(auth.getTypelogin().equals(ConstansKey.TYPE_WEB)) {
			if(codepermission.equals(ConstansPermission.READ_ROLE)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(roleService.getAllListRole(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(permissionService.getAllListPermission());
				}else {
					long id = new Long(type).longValue();
					val.setData(roleService.getRoleDetail(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_USER)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(userAppsService.getListAllUser(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")){
					val.setData(userAppsService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(userAppsService.getDetailUserApps(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_USER_MOBILE)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(userMobileService.getListAllUserMobile(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(userMobileService.getTemplateUserMobile(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(userMobileService.getDetailUserMobile(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_BRANCH)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(new ArrayList<Branch>(branchservice.getListBranchActiveJdbc()));
				}else if(type.equals("getlistbranchnotexistincompany")) {
					val.setData(new ArrayList<BranchData>(branchservice.getAllListBranchNotExistInCompany()));
				}else {
					long id = new Long(type).longValue();
					val.setData(branchservice.getBranchByID(id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_COMPANY)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(companyService.getListCompanyActive());
				}else if(type.equals("TEMPLATE")) {
					val.setData(companyService.getTemplateCompany());
				}else {
					long id = new Long(type).longValue();
					val.setData(companyService.getCompanyAndCompanyBranchByID(id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_ADDRESS)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL_DISTRICT")) {
					val.setData(districtService.getListDistrict());
				}else if(type.equals("DISTRICT_BY_CITY")) {
					long cityid = (long) param.get("cityid");
					val.setData(districtService.getListDistrictByCity(cityid));
				}else if(type.equals("ALL_SUBDISTRICT")) {
					val.setData(subdistrictService.getListSubDistrict());
				}else if(type.equals("SUBDISTRICT_BY_DISTRICT")) {
					long districtid = (long) param.get("districtid");
					val.setData(subdistrictService.getListSubDistrictByDistrictId(districtid));
				}else if(type.equals("ALL_POSTAlCODE")) {
					val.setData(postalCodeService.getListPostalCode());
				}else if(type.equals("POSTALCODE_BY_POSTALCODE")) {
					long postalcode = (long) param.get("postalcode");
					val.setData(postalCodeService.getListPostalCodeByPostalCode(postalcode));
				}else if(type.equals("POSTALCODE_BY_SUBDSTRICT")) {
					long subdistrictid = (long) param.get("subdistrictid");
					val.setData(postalCodeService.getListPostalCodeByPostalCodeBySubDistrictId(subdistrictid));
				}else if(type.equals("POSTALCODE_BY_CITY_AND_PROVINCE")) {
					long cityid = (long) param.get("cityid");
					long provid = (long) param.get("provid");
					val.setData(postalCodeService.getListPostalCodeByPostalCodeByCityAndProvince(cityid, provid));
				}else if(type.equals("DISTRICT_BY_POSTALCODE")) {
					long postalcode = (long) param.get("postalcode");
					val.setData(districtService.getListDistrictByPostalCode(postalcode));
				}else if(type.equals("POSTALCODE_BY_DISTRICT")) {
					long districtid = (long) param.get("districtid");
					val.setData(postalCodeService.getListPostalCodeByPostalCodeByDistrictId(districtid));
				}
				
			}else if(auth.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {}
				
		}
		return val;
	}

	

}
