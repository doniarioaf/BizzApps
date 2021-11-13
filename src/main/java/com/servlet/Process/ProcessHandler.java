package com.servlet.Process;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.servlet.admin.branch.entity.BodyBranch;
import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.entity.BranchData;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.admin.company.entity.BodyCompany;
import com.servlet.admin.company.service.CompanyService;
import com.servlet.admin.customer.entity.BodyCustomer;
import com.servlet.admin.customer.service.CustomerService;
import com.servlet.admin.customertype.entity.BodyCustomerType;
import com.servlet.admin.customertype.service.CustomerTypeService;
import com.servlet.admin.product.entity.BodyProduct;
import com.servlet.admin.product.service.ProductService;
import com.servlet.admin.producttype.entity.BodyProductType;
import com.servlet.admin.producttype.service.ProductTypeService;
import com.servlet.admin.role.entity.BodyRole;
import com.servlet.admin.role.service.RoleService;
import com.servlet.admin.usermobile.entity.BodyUserMobile;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.mobile.callplan.entity.BodyCallPlan;
import com.servlet.mobile.callplan.service.CallPlanService;
import com.servlet.mobile.download.service.DownloadService;
import com.servlet.mobile.infoheader.entity.BodyInfoHeader;
import com.servlet.mobile.infoheader.service.InfoHeaderService;
import com.servlet.mobile.monitorusermobile.entity.BodyListPhoto;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.service.MonitorUserMobileService;
import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.mobile.project.service.ProjectService;
import com.servlet.mobile.usermobilelocation.entity.BodyUserMobileLocation;
import com.servlet.mobile.usermobilelocation.service.UserMobileLocationService;
import com.servlet.security.entity.AuthorizationData;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.user.entity.BodyUserApps;
import com.servlet.user.service.UserAppsService;


@Service
public class ProcessHandler implements ProcessService{
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
	CustomerTypeService customerTypeService;
	@Autowired
	CustomerService customerService;
	@Autowired
	ProductTypeService productTypeService;
	@Autowired
	ProductService productService;
	@Autowired
	CallPlanService callPlanService;
	@Autowired
	ProjectService projectService;
	@Autowired
	InfoHeaderService infoHeaderService;
	@Autowired
	MonitorUserMobileService monitorUserMobileService;
	@Autowired
	DownloadService downloadService;
	@Autowired
	UserMobileLocationService userMobileLocationService;
	
	@Override
	public Object ProcessingFunction(String codepermission,Object data,String authorization) {
		// TODO Auto-generated constructor stub
		Object val = null;
		Gson gson = new Gson();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		String decryption = aesEncryptionDecryption.decrypt(authorization);
		AuthorizationData auth = gson.fromJson(decryption, AuthorizationData.class);
		if(auth.getTypelogin().equals(ConstansKey.TYPE_WEB)) {
			if(codepermission.equals(ConstansPermission.CREATE_BRANCH)) {
				BodyBranch branch = (BodyBranch) data;
				val = branchservice.saveBranch(branch);
			}else if(codepermission.equals(ConstansPermission.EDIT_BRANCH)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyBranch branch = (BodyBranch) param.get("BodyBranch");
				long id = (long) param.get("id");
				val = branchservice.updateBranch(id, branch);
			}else if(codepermission.equals(ConstansPermission.CREATE_COMPANY)) {
				BodyCompany body = (BodyCompany) data;
				val = companyService.saveCompany(body);
			}else if(codepermission.equals(ConstansPermission.EDIT_COMPANY)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCompany body = (BodyCompany) param.get("BodyCompany");
				long id = (long) param.get("id");
				val = companyService.updateCompany(id, body);
			}else if(codepermission.equals(ConstansPermission.CREATE_ROLE)) {
				BodyRole body = (BodyRole) data;
				val = roleService.saveRole(body,auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_ROLE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyRole body = (BodyRole) param.get("BodyRole");
				long id = (long) param.get("id");
				val = roleService.updateRole(id, body);
			}else if(codepermission.equals(ConstansPermission.CREATE_USER)) {
				BodyUserApps body = (BodyUserApps) data;
				val = userAppsService.saveUserApps(body,auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_USER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyUserApps body = (BodyUserApps) param.get("BodyUserApps");
				long id = (long) param.get("id");
				val = userAppsService.editUserApps(id, body);
			}else if(codepermission.equals(ConstansPermission.CREATE_USER_MOBILE)) {
				BodyUserMobile body = (BodyUserMobile) data;
				val = userMobileService.saveUserMobile(body,auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_USER_MOBILE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyUserMobile body = (BodyUserMobile) param.get("BodyUserMobile");
				long id = (long) param.get("id");
				val = userMobileService.editUserMobile(id, body);
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMERTYPE)) {
				BodyCustomerType body = (BodyCustomerType) data;
				val = customerTypeService.saveCustomerType(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMERTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCustomerType body = (BodyCustomerType) param.get("BodyCustomerType");
				long id = (long) param.get("id");
				val = customerTypeService.updateCustomerType(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMER)) {
				BodyCustomer body = (BodyCustomer) data;
				val = customerService.saveCustomer(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCustomer body = (BodyCustomer) param.get("BodyCustomer");
				long id = (long) param.get("id");
				val = customerService.updateCustomer(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_PRODUCTTYPE)) {
				BodyProductType body = (BodyProductType) data;
				val = productTypeService.saveProductType(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_PRODUCTTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProductType body = (BodyProductType) param.get("BodyProductType");
				long id = (long) param.get("id");
				val = productTypeService.updateProductType(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_PRODUCT)) {
				BodyProduct body = (BodyProduct) data;
				val = productService.saveProduct(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_PRODUCT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProduct body = (BodyProduct) param.get("BodyProduct");
				long id = (long) param.get("id");
				val = productService.updateProduct(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_CALLPLAN)) {
				BodyCallPlan body = (BodyCallPlan) data;
				val = callPlanService.saveCallPlan(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_CALLPLAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCallPlan body = (BodyCallPlan) param.get("BodyCallPlan");
				long id = (long) param.get("id");
				val = callPlanService.updateCallPlan(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_PROJECT)) {
				BodyProject body = (BodyProject) data;
				val = projectService.saveProject(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_PROJECT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProject body = (BodyProject) param.get("BodyProject");
				long id = (long) param.get("id");
				val = projectService.updateProject(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_INFO)) {
				BodyInfoHeader body = (BodyInfoHeader) data;
				val = infoHeaderService.saveInfoHeader(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_INFO)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyInfoHeader body = (BodyInfoHeader) param.get("BodyInfoHeader");
				long id = (long) param.get("id");
				val = infoHeaderService.updateInfoHeader(id, body, auth.getIdcompany(),auth.getIdbranch());
			}
		}else if(auth.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {
			if(codepermission.equals(ConstansPermission.CREATE_MONITOR_USER_MOBILE) ) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type  = (String) param.get("type");
				if(type.equals("monitorusermobile")) {
					BodyMonitorUserMobile body = (BodyMonitorUserMobile) param.get("BodyMonitorUserMobile");
					val = monitorUserMobileService.saveMonitorUserMobile(body, auth.getId(), auth.getIdcompany(),auth.getIdbranch());
				}else if(type.equals("photomonitorusermobile")) {
					BodyListPhoto body = (BodyListPhoto) param.get("BodyListPhoto");
					val = monitorUserMobileService.savePhotoMonitorUserMobile(body,auth.getId(), auth.getIdcompany(),auth.getIdbranch());
				}
				
			}else if(codepermission.equals(ConstansPermission.CREATE_LOCATION_MOBILE) ) {
				BodyUserMobileLocation body = (BodyUserMobileLocation) data;
				val = userMobileLocationService.saveUserMobileLocation(body, auth.getId(), auth.getIdcompany(),auth.getIdbranch());
			}
		}
		
		
		return val;
	}

	@Override
	public Object ProcessingReadFunction(String codepermission, Object data, String authorization) {
		// TODO Auto-generated method stub
		Object val = null;
		Gson gson = new Gson();
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		String decryption = aesEncryptionDecryption.decrypt(authorization);
		AuthorizationData auth = gson.fromJson(decryption, AuthorizationData.class);
		if(auth.getTypelogin().equals(ConstansKey.TYPE_WEB)) {
			if(codepermission.equals(ConstansPermission.READ_ROLE)) {
				String type = (String) data;
				if(type == "ALL") {
					val = roleService.getAllListRole(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = roleService.getRoleDetail(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_USER)) {
				String type = (String) data;
				if(type == "ALL") {
					val = userAppsService.getListAllUser(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = userAppsService.getDetailUserApps(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_USER_MOBILE)) {
				String type = (String) data;
				if(type == "ALL") {
					val = userMobileService.getListAllUserMobile(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = userMobileService.getDetailUserMobile(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_BRANCH)) {
				String type = (String) data;
				if(type == "ALL") {
					val = new ArrayList<Branch>(branchservice.getListBranchActiveJdbc());
				}else if(type == "getlistbranchnotexistincompany") {
					val = new ArrayList<BranchData>(branchservice.getAllListBranchNotExistInCompany());
				}else {
					long id = new Long(type).longValue();
					val = branchservice.getBranchByID(id);
				}
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMERTYPE)) {
				String type = (String) data;
				if(type == "ALL") {
					val = customerTypeService.getAllListCustomerType(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = customerTypeService.getCustomerTypeById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMER)) {
				String type = (String) data;
				if(type == "ALL") {
					val = customerService.getAllListCustomer(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = customerService.getCustomerById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRODUCTTYPE)) {
				String type = (String) data;
				if(type == "ALL") {
					val = productTypeService.getAllListProductType(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = productTypeService.getProductTypeById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRODUCT)) {
				String type = (String) data;
				if(type == "ALL") {
					val = productService.getAllListProduct(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = productService.getProductById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_CALLPLAN)) {
				String type = (String) data;
				if(type == "ALL") {
					val = callPlanService.getAllListCallPlan(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = callPlanService.getCallPlanById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_PROJECT)) {
				String type = (String) data;
				if(type == "ALL") {
					val = projectService.getAllListProject(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = projectService.getProjectById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_INFO)) {
				String type = (String) data;
				if(type == "ALL") {
					val = infoHeaderService.getAllListData(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val = infoHeaderService.getDetailById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}
		}else if(auth.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {
			if(codepermission.equals(ConstansPermission.READ_INFO_MOBILE)) {
				String type = (String) data;
				if(type == "ALL_MOBILE") {
					val = infoHeaderService.getAllListDataMobile(auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_DOWNLOAD_MOBILE)) {
				String type = (String) data;
				if(type == "ALL") {
					val = downloadService.donwload(auth.getId(), auth.getIdcompany(), auth.getIdbranch());
				}
			}
		}
		return val;
	}

	

}
