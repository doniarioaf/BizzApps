package com.servlet.Process;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.servlet.BizzAppsBackEndApplication;
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
import com.servlet.admin.permission.service.PermissionService;
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
import com.servlet.mobile.customercallplan.entity.DownloadCustomerCallPlan;
import com.servlet.mobile.download.service.DownloadService;
import com.servlet.mobile.infoheader.entity.BodyInfoHeader;
import com.servlet.mobile.infoheader.service.InfoHeaderService;
import com.servlet.mobile.monitorusermobile.entity.BodyListPhoto;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.service.MonitorUserMobileService;
import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.mobile.project.service.ProjectService;
import com.servlet.mobile.usermobilecallplan.entity.DownloadUserMobileCallPlan;
import com.servlet.mobile.usermobilelocation.entity.BodyUserMobileLocation;
import com.servlet.mobile.usermobilelocation.service.UserMobileLocationService;
import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.service.ReportService;
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
	@Autowired
	PermissionService permissionService;
	@Autowired
	ReportService reportService;
	
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
//				val = branchservice.saveBranch(branch);
			}else if(codepermission.equals(ConstansPermission.EDIT_BRANCH)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyBranch branch = (BodyBranch) param.get("BodyBranch");
				long id = (long) param.get("id");
				val.setData(branchservice.updateBranch(id, branch));
//				val = branchservice.updateBranch(id, branch);
			}else if(codepermission.equals(ConstansPermission.CREATE_COMPANY)) {
				BodyCompany body = (BodyCompany) data;
				val.setData(companyService.saveCompany(body));
//				val = companyService.saveCompany(body);
			}else if(codepermission.equals(ConstansPermission.EDIT_COMPANY)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCompany body = (BodyCompany) param.get("BodyCompany");
				long id = (long) param.get("id");
				val.setData(companyService.updateCompany(id, body));
//				val = companyService.updateCompany(id, body);
			}else if(codepermission.equals(ConstansPermission.DELETE_COMPANY)) {
				long id = (long) data;
				val.setData(companyService.deleteCompany(id));
//				val = companyService.deleteCompany(id);
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
//				val = roleService.saveRole(body,auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_ROLE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyRole body = (BodyRole) param.get("BodyRole");
				long id = (long) param.get("id");
				val.setData(roleService.updateRole(id, body));
//				val = roleService.updateRole(id, body);
			}else if(codepermission.equals(ConstansPermission.CREATE_USER)) {
				BodyUserApps body = (BodyUserApps) data;
				ReturnData valReturn = userAppsService.saveUserApps(body,auth.getIdcompany(),auth.getIdbranch());
				val.setSuccess(valReturn.isSuccess());
				val.setHttpcode(HttpStatus.BAD_REQUEST.value());
				val.setValidations(valReturn.getValidations());
				
				val.setData(userAppsService.saveUserApps(body,auth.getIdcompany(),auth.getIdbranch()));
//				val = userAppsService.saveUserApps(body,auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_USER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyUserApps body = (BodyUserApps) param.get("BodyUserApps");
				long id = (long) param.get("id");
				val.setData(userAppsService.editUserApps(id, body));
//				val = userAppsService.editUserApps(id, body);
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
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMERTYPE)) {
				BodyCustomerType body = (BodyCustomerType) data;
				val.setData(customerTypeService.saveCustomerType(body, auth.getIdcompany(),auth.getIdbranch()));
//				val = customerTypeService.saveCustomerType(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMERTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCustomerType body = (BodyCustomerType) param.get("BodyCustomerType");
				long id = (long) param.get("id");
				val.setData(customerTypeService.updateCustomerType(id, body, auth.getIdcompany(),auth.getIdbranch()));
//				val = customerTypeService.updateCustomerType(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMER)) {
				BodyCustomer body = (BodyCustomer) data;
				val.setData(customerService.saveCustomer(body, auth.getIdcompany(),auth.getIdbranch()));
//				val = customerService.saveCustomer(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCustomer body = (BodyCustomer) param.get("BodyCustomer");
				long id = (long) param.get("id");
				val.setData(customerService.updateCustomer(id, body, auth.getIdcompany(),auth.getIdbranch()));
//				val = customerService.updateCustomer(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_PRODUCTTYPE)) {
				BodyProductType body = (BodyProductType) data;
				val.setData(productTypeService.saveProductType(body, auth.getIdcompany(),auth.getIdbranch()));
//				val = productTypeService.saveProductType(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_PRODUCTTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProductType body = (BodyProductType) param.get("BodyProductType");
				long id = (long) param.get("id");
				val.setData(productTypeService.updateProductType(id, body, auth.getIdcompany(),auth.getIdbranch()));
//				val = productTypeService.updateProductType(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_PRODUCT)) {
				BodyProduct body = (BodyProduct) data;
				val.setData(productService.saveProduct(body, auth.getIdcompany(),auth.getIdbranch()));
//				val = productService.saveProduct(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_PRODUCT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProduct body = (BodyProduct) param.get("BodyProduct");
				long id = (long) param.get("id");
				val.setData(productService.updateProduct(id, body, auth.getIdcompany(),auth.getIdbranch()));
//				val = productService.updateProduct(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_CALLPLAN)) {
				BodyCallPlan body = (BodyCallPlan) data;
				val.setData(callPlanService.saveCallPlan(body, auth.getIdcompany(),auth.getIdbranch()));
//				val = callPlanService.saveCallPlan(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_CALLPLAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCallPlan body = (BodyCallPlan) param.get("BodyCallPlan");
				long id = (long) param.get("id");
				val.setData(callPlanService.updateCallPlan(id, body, auth.getIdcompany(),auth.getIdbranch()));
//				val = callPlanService.updateCallPlan(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_PROJECT)) {
				BodyProject body = (BodyProject) data;
				val.setData(projectService.saveProject(body, auth.getIdcompany(),auth.getIdbranch()));
//				val = projectService.saveProject(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_PROJECT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProject body = (BodyProject) param.get("BodyProject");
				long id = (long) param.get("id");
				val.setData(projectService.updateProject(id, body, auth.getIdcompany(),auth.getIdbranch()));
//				val = projectService.updateProject(id, body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.CREATE_INFO)) {
				BodyInfoHeader body = (BodyInfoHeader) data;
				
				ReturnData valReturn = infoHeaderService.saveInfoHeader(body, auth.getIdcompany(),auth.getIdbranch());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}				
//				val = infoHeaderService.saveInfoHeader(body, auth.getIdcompany(),auth.getIdbranch());
			}else if(codepermission.equals(ConstansPermission.EDIT_INFO)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyInfoHeader body = (BodyInfoHeader) param.get("BodyInfoHeader");
				long id = (long) param.get("id");
				
				ReturnData valReturn = infoHeaderService.updateInfoHeader(id, body, auth.getIdcompany(),auth.getIdbranch());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}				
			}
		}else if(auth.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {
			if(codepermission.equals(ConstansPermission.CREATE_MONITOR_USER_MOBILE) ) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type  = (String) param.get("type");
				if(type.equals("monitorusermobile")) {
					BodyMonitorUserMobile body = (BodyMonitorUserMobile) param.get("BodyMonitorUserMobile");
					val.setData(monitorUserMobileService.saveMonitorUserMobile(body, auth.getId(), auth.getIdcompany(),auth.getIdbranch()));
//					val = monitorUserMobileService.saveMonitorUserMobile(body, auth.getId(), auth.getIdcompany(),auth.getIdbranch());
				}else if(type.equals("photomonitorusermobile")) {
					BodyListPhoto body = (BodyListPhoto) param.get("BodyListPhoto");
					val.setData(monitorUserMobileService.savePhotoMonitorUserMobile(body,auth.getId(), auth.getIdcompany(),auth.getIdbranch()));
//					val = monitorUserMobileService.savePhotoMonitorUserMobile(body,auth.getId(), auth.getIdcompany(),auth.getIdbranch());
				}
				
			}else if(codepermission.equals(ConstansPermission.CREATE_LOCATION_MOBILE) ) {
				BodyUserMobileLocation body = (BodyUserMobileLocation) data;
				val.setData(userMobileLocationService.saveUserMobileLocation(body, auth.getId(), auth.getIdcompany(),auth.getIdbranch()));
//				val = userMobileLocationService.saveUserMobileLocation(body, auth.getId(), auth.getIdcompany(),auth.getIdbranch());
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
//					val = roleService.getAllListRole(auth.getIdcompany(), auth.getIdbranch());
				}else if(type.equals("TEMPLATE")) {
					val.setData(permissionService.getAllListPermission());
//					val = permissionService.getAllListPermission();
				}else {
					long id = new Long(type).longValue();
					val.setData(roleService.getRoleDetail(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = roleService.getRoleDetail(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_USER)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(userAppsService.getListAllUser(auth.getIdcompany(), auth.getIdbranch()));
//					val = userAppsService.getListAllUser(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(userAppsService.getDetailUserApps(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = userAppsService.getDetailUserApps(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_USER_MOBILE)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(userMobileService.getListAllUserMobile(auth.getIdcompany(), auth.getIdbranch()));
//					val = userMobileService.getListAllUserMobile(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(userMobileService.getDetailUserMobile(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = userMobileService.getDetailUserMobile(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_BRANCH)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(new ArrayList<Branch>(branchservice.getListBranchActiveJdbc()));
//					val = new ArrayList<Branch>(branchservice.getListBranchActiveJdbc());
				}else if(type.equals("getlistbranchnotexistincompany")) {
					val.setData(new ArrayList<BranchData>(branchservice.getAllListBranchNotExistInCompany()));
//					val = new ArrayList<BranchData>(branchservice.getAllListBranchNotExistInCompany());
				}else {
					long id = new Long(type).longValue();
					val.setData(branchservice.getBranchByID(id));
//					val = branchservice.getBranchByID(id);
				}
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMERTYPE)) {
				String type = (String) data;
				if(type == "ALL") {
					val.setData(customerTypeService.getAllListCustomerType(auth.getIdcompany(), auth.getIdbranch()));
//					val = customerTypeService.getAllListCustomerType(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(customerTypeService.getCustomerTypeById(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = customerTypeService.getCustomerTypeById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMER)) {
				String type = (String) data;
				
				if(type.equals("ALL")) {
					val.setData(customerService.getAllListCustomer(auth.getIdcompany(), auth.getIdbranch()));
//					val = customerService.getAllListCustomer(auth.getIdcompany(), auth.getIdbranch());
				}else if(type.equals("TEMPLATE")) {
					val.setData(customerService.customerTemplate(auth.getIdcompany(), auth.getIdbranch()));
//					val = customerService.customerTemplate(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(customerService.getCustomerById(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = customerService.getCustomerById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRODUCTTYPE)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(productTypeService.getAllListProductType(auth.getIdcompany(), auth.getIdbranch()));
//					val = productTypeService.getAllListProductType(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(productTypeService.getProductTypeById(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = productTypeService.getProductTypeById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRODUCT)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(productService.getAllListProduct(auth.getIdcompany(), auth.getIdbranch()));
//					val = productService.getAllListProduct(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(productService.getProductById(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = productService.getProductById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_CALLPLAN)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(callPlanService.getAllListCallPlan(auth.getIdcompany(), auth.getIdbranch()));
//					val = callPlanService.getAllListCallPlan(auth.getIdcompany(), auth.getIdbranch());
				}else if(type.equals("TEMPLATE")) {
					val.setData(callPlanService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(callPlanService.getCallPlanById(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = callPlanService.getCallPlanById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_PROJECT)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(projectService.getAllListProject(auth.getIdcompany(), auth.getIdbranch()));
//					val = projectService.getAllListProject(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(projectService.getProjectById(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = projectService.getProjectById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_INFO)) {
				String type = (String) data;
				if(type.equals("TEMPLATE")) {
					val.setData(infoHeaderService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("ALL")) {
					val.setData(infoHeaderService.getAllListData(auth.getIdcompany(), auth.getIdbranch()));
//					val = infoHeaderService.getAllListData(auth.getIdcompany(), auth.getIdbranch());
				}else {
					long id = new Long(type).longValue();
					val.setData(infoHeaderService.getDetailById(id, auth.getIdcompany(), auth.getIdbranch()));
//					val = infoHeaderService.getDetailById(id, auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_REPORT_MONITORING)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("TEMPLATE")) {
					val.setData(userMobileService.getListAllUserMobileForMonitoring("ALL",auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("REPORT")) {
					BodyReportMonitoring body = (BodyReportMonitoring) param.get("body");
					if(body.getTypereport().equals("XLSX")) {
						val.setData(reportService.getReportMonitoringData(body, auth.getIdcompany(), auth.getIdbranch()).getWorkbook());
					}else {
						val.setData(reportService.getReportMonitoringDataPDF(body, auth.getIdcompany(), auth.getIdbranch()));
					}
					
				}
			}
		}else if(auth.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {
			if(codepermission.equals(ConstansPermission.READ_INFO_MOBILE)) {
				String type = (String) data;
				if(type.equals("ALL_MOBILE")) {
					val.setData(infoHeaderService.getAllListDataMobile(auth.getIdcompany(), auth.getIdbranch()));
//					val = infoHeaderService.getAllListDataMobile(auth.getIdcompany(), auth.getIdbranch());
				}
			}else if(codepermission.equals(ConstansPermission.READ_DOWNLOAD_MOBILE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("DOWNLOAD")) {
					val.setData(downloadService.donwload(auth.getId(), auth.getIdcompany(), auth.getIdbranch()));
//					val = downloadService.donwload(auth.getId(), auth.getIdcompany(), auth.getIdbranch());
				}else if(type.equals("DOWNLOAD_CALL_PLAN")) {
					long limit = (long) param.get("limit");
					long offset = (long) param.get("offset");
					DownloadUserMobileCallPlan dataval = downloadService.donwloadUserMobileCallPlan(auth.getId(), auth.getIdcompany(), auth.getIdbranch(),limit,offset);
					val.setData(dataval);
				}else if(type.equals("DOWNLOAD_CUSTOMER_CALL_PLAN")) {
					long limit = (long) param.get("limit");
					long offset = (long) param.get("offset");
					DownloadCustomerCallPlan dataval = downloadService.donwloadCustomerCallPlan(auth.getId(), auth.getIdcompany(), auth.getIdbranch(),limit,offset);
					val.setData(dataval);
				}
			}
		}
		return val;
	}

	

}
