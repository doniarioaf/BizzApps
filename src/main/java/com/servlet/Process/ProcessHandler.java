package com.servlet.Process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import com.servlet.admin.customer.entity.BodyCustomer;
import com.servlet.admin.customer.service.CustomerService;
import com.servlet.admin.customertype.entity.BodyCustomerType;
import com.servlet.admin.customertype.service.CustomerTypeService;
import com.servlet.admin.importfile.service.ImportFileService;
import com.servlet.admin.permission.entity.BodyPermission;
import com.servlet.admin.permission.service.PermissionService;
import com.servlet.admin.product.entity.BodyProduct;
import com.servlet.admin.product.service.ProductService;
import com.servlet.admin.producttype.entity.BodyProductType;
import com.servlet.admin.producttype.service.ProductTypeService;
import com.servlet.admin.role.entity.BodyRole;
import com.servlet.admin.role.service.RoleService;
import com.servlet.admin.usermobile.entity.BodyUserMobile;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.bankaccount.entity.BodyBankAccount;
import com.servlet.bankaccount.service.BankAccountService;
//import com.servlet.coa.service.CoaService;
import com.servlet.customermanggala.entity.BodyCustomerManggala;
import com.servlet.customermanggala.entity.BodySearch;
import com.servlet.customermanggala.service.CustomerManggalaService;
import com.servlet.employeemanggala.entity.BodyEmployeeManggala;
import com.servlet.employeemanggala.service.EmployeeManggalaService;
import com.servlet.invoice.entity.BodyInvoice;
import com.servlet.invoice.entity.BodySearchInvoicePriceList;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.invoicetype.entity.BodyInvoiceType;
import com.servlet.invoicetype.service.InvoiceTypeService;
import com.servlet.mobile.callplan.entity.BodyCallPlan;
import com.servlet.mobile.callplan.service.CallPlanService;
import com.servlet.mobile.customercallplan.entity.DownloadCustomerCallPlan;
import com.servlet.mobile.download.service.DownloadService;
import com.servlet.mobile.infoheader.entity.BodyInfoHeader;
import com.servlet.mobile.infoheader.entity.BodyInfoHeaderUpdate;
import com.servlet.mobile.infoheader.service.InfoHeaderService;
import com.servlet.mobile.monitorusermobile.entity.BodyListPhoto;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.mobile.monitorusermobile.service.MonitorUserMobileService;
import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.mobile.project.service.ProjectService;
import com.servlet.mobile.usermobilecallplan.entity.DownloadUserMobileCallPlan;
import com.servlet.mobile.usermobilelocation.entity.BodyUserMobileLocation;
import com.servlet.mobile.usermobilelocation.service.UserMobileLocationService;
import com.servlet.parametermanggala.entity.BodyParameterManggala;
import com.servlet.parametermanggala.service.ParameterManggalaService;
import com.servlet.partai.entity.BodyPartai;
import com.servlet.partai.service.PartaiService;
import com.servlet.paymenttype.entity.BodyPaymentType;
import com.servlet.paymenttype.service.PaymentTypeService;
import com.servlet.penerimaankasbank.entity.BodyPenerimaanKasBank;
import com.servlet.penerimaankasbank.service.PenerimaanKasBankService;
import com.servlet.pengluarankasbank.entity.BodyPengeluaranKasBank;
import com.servlet.pengluarankasbank.service.PengeluaranKasBankService;
import com.servlet.port.entity.BodyPort;
import com.servlet.port.service.PortService;
import com.servlet.pricelist.entity.BodyPriceList;
import com.servlet.pricelist.entity.BodySearchPriceList;
import com.servlet.pricelist.entity.PriceListData;
import com.servlet.pricelist.service.PriceListService;
import com.servlet.report.entity.BodyGetMaps;
import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.entity.Manggala_BodyReportBongkarMuatDanDepo;
import com.servlet.report.service.ReportService;
import com.servlet.report.service.ReportServiceManggala;
import com.servlet.security.entity.AuthorizationData;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.ProcessReturn;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;
import com.servlet.suratjalan.entity.BodyStatusSuratJalan;
import com.servlet.suratjalan.entity.BodySuratJalan;
import com.servlet.suratjalan.service.SuratJalanService;
import com.servlet.user.entity.BodyUserApps;
import com.servlet.user.service.UserAppsService;
import com.servlet.vendor.entity.BodyVendor;
import com.servlet.vendor.service.VendorService;
import com.servlet.vendorcategory.entity.BodyVendorCategory;
import com.servlet.vendorcategory.service.VendorCategoryService;
import com.servlet.warehouse.entity.BodyWarehouse;
import com.servlet.warehouse.service.WarehouseService;
import com.servlet.workorder.entity.BodyWorkOrder;
import com.servlet.workorder.service.WorkOrderService;
import com.servlet.workordertype.entity.BodyWorkOrderType;
import com.servlet.workordertype.service.WorkOrderTypeService;


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
	@Autowired
	ImportFileService importFileService;
	@Autowired
	CustomerManggalaService customerManggalaService;
	@Autowired
	DistrictService districtService;
	@Autowired
	SubDistrictService subdistrictService;
	@Autowired
	PostalCodeService postalCodeService;
	@Autowired
	BankAccountService bankAccountService;
	@Autowired
	EmployeeManggalaService employeeManggalaService;
	@Autowired
	VendorCategoryService vendorCategoryService;
	@Autowired
	VendorService vendorService;
	@Autowired
	WorkOrderTypeService workOrderTypeService;
	@Autowired
	PartaiService partaiService;
	@Autowired
	PortService portService;
	@Autowired
	ParameterManggalaService parameterManggalaService;
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	InvoiceTypeService invoiceTypeService;
	@Autowired
	PriceListService priceListService;
	@Autowired
	PaymentTypeService paymentTypeService;
	@Autowired
	WorkOrderService workOrderService;
	@Autowired
	SuratJalanService suratJalanService;
	@Autowired
	ReportServiceManggala reportServiceManggala;
	@Autowired
	PenerimaanKasBankService penerimaanKasBankService;
	@Autowired
	PengeluaranKasBankService pengeluaranKasBankService;
	@Autowired
	InvoiceService invoiceService;
	
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
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMERTYPE)) {
				BodyCustomerType body = (BodyCustomerType) data;
				val.setData(customerTypeService.saveCustomerType(body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMERTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCustomerType body = (BodyCustomerType) param.get("BodyCustomerType");
				long id = (long) param.get("id");
				val.setData(customerTypeService.updateCustomerType(id, body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.DELETE_CUSTOMERTYPE)) {
				long id = (long) data;
				val.setData(customerTypeService.deleteCustomerType(id));
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMER)) {
				BodyCustomer body = (BodyCustomer) data;
				ReturnData valReturn = customerService.saveCustomer(body, auth.getIdcompany(),auth.getIdbranch());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
//				val.setData(customerService.saveCustomer(body, auth.getIdcompany(),auth.getIdbranch()));
				
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCustomer body = (BodyCustomer) param.get("BodyCustomer");
				long id = (long) param.get("id");
				
				ReturnData valReturn = customerService.updateCustomer(id, body, auth.getIdcompany(),auth.getIdbranch());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
//				val.setData(customerService.updateCustomer(id, body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.DELETE_CUSTOMER)) {
				long id = (long) data;
				val.setData(customerService.deleteCustomer(id));
			}else if(codepermission.equals(ConstansPermission.CREATE_PRODUCTTYPE)) {
				BodyProductType body = (BodyProductType) data;
				val.setData(productTypeService.saveProductType(body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.EDIT_PRODUCTTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProductType body = (BodyProductType) param.get("BodyProductType");
				long id = (long) param.get("id");
				val.setData(productTypeService.updateProductType(id, body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.CREATE_PRODUCT)) {
				BodyProduct body = (BodyProduct) data;
				val.setData(productService.saveProduct(body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.EDIT_PRODUCT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProduct body = (BodyProduct) param.get("BodyProduct");
				long id = (long) param.get("id");
				val.setData(productService.updateProduct(id, body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.CREATE_CALLPLAN)) {
				BodyCallPlan body = (BodyCallPlan) data;
				val.setData(callPlanService.saveCallPlan(body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.EDIT_CALLPLAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyCallPlan body = (BodyCallPlan) param.get("BodyCallPlan");
				long id = (long) param.get("id");
				val.setData(callPlanService.updateCallPlan(id, body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.DELETE_CALL_PLAN)) {
				long id = (long) data;
				val.setData(callPlanService.deleteCallPlan(id));
			}else if(codepermission.equals(ConstansPermission.CREATE_PROJECT)) {
				BodyProject body = (BodyProject) data;
				val.setData(projectService.saveProject(body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.EDIT_PROJECT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				BodyProject body = (BodyProject) param.get("BodyProject");
				long id = (long) param.get("id");
				val.setData(projectService.updateProject(id, body, auth.getIdcompany(),auth.getIdbranch()));
			}else if(codepermission.equals(ConstansPermission.DELETE_PROJECT)) {
				long id = (long) data;
				val.setData(projectService.deleteProject(id));
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
				BodyInfoHeaderUpdate body = (BodyInfoHeaderUpdate) param.get("BodyInfoHeader");
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
			}else if(codepermission.equals(ConstansPermission.DELETE_INFO)) {
				long id = (long) data;
				val.setData(infoHeaderService.deleteInfo(id));
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
			}else if(codepermission.equals(ConstansPermission.CREATE_UPLOAD_CUSTOMER_CALLPLAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				MultipartFile file = (MultipartFile) param.get("file");
				try {
					ReturnData valReturn = importFileService.importFileExcelCustomerCallPlan(file.getInputStream(), file,auth.getIdcompany(),auth.getIdbranch());
					if(valReturn.isSuccess()) {
						val.setData(valReturn.getId());
					}else {
						val.setSuccess(valReturn.isSuccess());
						val.setHttpcode(HttpStatus.BAD_REQUEST.value());
						val.setValidations(valReturn.getValidations());
						val.setData(null);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_BANK_ACCOUNT)) {
				BodyBankAccount param = (BodyBankAccount) data;
				ReturnData valReturn = bankAccountService.saveBankAccount(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}				
			}else if(codepermission.equals(ConstansPermission.EDIT_BANK_ACCOUNT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyBankAccount body  = (BodyBankAccount) param.get("body");
				ReturnData valReturn = bankAccountService.updateBankAccount(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_BANK_ACCOUNT)) {
				long id = (long) data;
				ReturnData valReturn = bankAccountService.deleteBankAccount(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMER_MANGGALA)) {
				BodyCustomerManggala param = (BodyCustomerManggala) data;
				ReturnData valReturn = customerManggalaService.saveCustomerManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMER_MANGGALA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyCustomerManggala body  = (BodyCustomerManggala) param.get("body");
				ReturnData valReturn = customerManggalaService.updateCustomerManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_CUSTOMER_MANGGALA)) {
				long id = (long) data;
				ReturnData valReturn = customerManggalaService.deleteCustomerManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_EMPLOYEE_MANGGALA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				
				if(type.equals("CREATE")) {
					BodyEmployeeManggala body = (BodyEmployeeManggala) param.get("body");
					ReturnData valReturn = employeeManggalaService.saveEmployeeManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), body);
					if(valReturn.isSuccess()) {
						val.setData(valReturn.getId());
					}else {
						val.setSuccess(valReturn.isSuccess());
						val.setHttpcode(HttpStatus.BAD_REQUEST.value());
						val.setValidations(valReturn.getValidations());
						val.setData(null);
					}
				}else if(type.equals("UPLOADIMAGE")) {
					MultipartFile file = (MultipartFile) param.get("body");
					Long id = (Long) param.get("id");
					ReturnData valReturn = employeeManggalaService.uploadImageEmployeeManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, file);
					if(valReturn.isSuccess()) {
						val.setData(valReturn.getId());
					}else {
						val.setSuccess(valReturn.isSuccess());
						val.setHttpcode(HttpStatus.BAD_REQUEST.value());
						val.setValidations(valReturn.getValidations());
						val.setData(null);
					}
				}
				
			}else if(codepermission.equals(ConstansPermission.EDIT_EMPLOYEE_MANGGALA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyEmployeeManggala body  = (BodyEmployeeManggala) param.get("body");
				ReturnData valReturn = employeeManggalaService.updateEmployeeManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_EMPLOYEE_MANGGALA)) {
				long id = (long) data;
				ReturnData valReturn = employeeManggalaService.deleteEmployeeManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_VENDOR_CATEGORY)) {
				BodyVendorCategory param = (BodyVendorCategory) data;
				ReturnData valReturn = vendorCategoryService.saveVendorCategory(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_VENDOR_CATEGORY)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyVendorCategory body  = (BodyVendorCategory) param.get("body");
				ReturnData valReturn = vendorCategoryService.updateVendorCategory(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_VENDOR_CATEGORY)) {
				long id = (long) data;
				ReturnData valReturn = vendorCategoryService.deleteVendorCategory(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_VENDOR)) {
				BodyVendor param = (BodyVendor) data;
				ReturnData valReturn = vendorService.saveVendor(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_VENDOR)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyVendor body  = (BodyVendor) param.get("body");
				ReturnData valReturn = vendorService.updateVendor(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_VENDOR)) {
				long id = (long) data;
				ReturnData valReturn = vendorService.deleteVendor(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_WORKORDERTYPE)) {
				BodyWorkOrderType param = (BodyWorkOrderType) data;
				ReturnData valReturn = workOrderTypeService.saveWorkOrderType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_WORKORDERTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyWorkOrderType body  = (BodyWorkOrderType) param.get("body");
				ReturnData valReturn = workOrderTypeService.updateWorkOrderType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_WORKORDERTYPE)) {
				long id = (long) data;
				ReturnData valReturn = workOrderTypeService.deleteWorkOrderType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PARTAI)) {
				BodyPartai param = (BodyPartai) data;
				ReturnData valReturn = partaiService.savePartai(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PARTAI)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPartai body  = (BodyPartai) param.get("body");
				ReturnData valReturn = partaiService.updatePartai(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PARTAI)) {
				long id = (long) data;
				ReturnData valReturn = partaiService.deletePartai(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PORT)) {
				BodyPort param = (BodyPort) data;
				ReturnData valReturn = portService.savePort(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PORT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPort body  = (BodyPort) param.get("body");
				ReturnData valReturn = portService.updatePort(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PORT)) {
				long id = (long) data;
				ReturnData valReturn = portService.deleteport(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PARAMETERMANGGALA)) {
				BodyParameterManggala param = (BodyParameterManggala) data;
				ReturnData valReturn = parameterManggalaService.saveParameterManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PARAMETERMANGGALA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyParameterManggala body  = (BodyParameterManggala) param.get("body");
				ReturnData valReturn = parameterManggalaService.updateParameterManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PARAMETERMANGGALA)) {
				long id = (long) data;
				ReturnData valReturn = parameterManggalaService.deleteParameterManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_WAREHOUSE)) {
				BodyWarehouse param = (BodyWarehouse) data;
				ReturnData valReturn = warehouseService.saveWarehouse(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_WAREHOUSE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyWarehouse body  = (BodyWarehouse) param.get("body");
				ReturnData valReturn = warehouseService.updateWarehouse(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_WAREHOUSE)) {
				long id = (long) data;
				ReturnData valReturn = warehouseService.deleteWarehouse(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_INVOICETYPE)) {
				BodyInvoiceType param = (BodyInvoiceType) data;
				ReturnData valReturn =  invoiceTypeService.saveInvoiceType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_INVOICETYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyInvoiceType body  = (BodyInvoiceType) param.get("body");
				ReturnData valReturn = invoiceTypeService.updateInvoiceType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_INVOICETYPE)) {
				long id = (long) data;
				ReturnData valReturn = invoiceTypeService.deleteInvoiceType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PRICELIST)) {
				BodyPriceList param = (BodyPriceList) data;
				ReturnData valReturn =  priceListService.savePriceList(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PRICELIST)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPriceList body  = (BodyPriceList) param.get("body");
				ReturnData valReturn = priceListService.updatePriceList(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PRICELIST)) {
				long id = (long) data;
				ReturnData valReturn = priceListService.deletePriceList(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PAYMENTTYPE)) {
				BodyPaymentType param = (BodyPaymentType) data;
				ReturnData valReturn =  paymentTypeService.savePaymentType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PAYMENTTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPaymentType body  = (BodyPaymentType) param.get("body");
				ReturnData valReturn = paymentTypeService.updatePaymentType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PAYMENTTYPE)) {
				long id = (long) data;
				ReturnData valReturn = paymentTypeService.deletePaymentType(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_WORKORDER)) {
				BodyWorkOrder param = (BodyWorkOrder) data;
				ReturnData valReturn =  workOrderService.saveWorkOrder(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_WORKORDER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyWorkOrder body  = (BodyWorkOrder) param.get("body");
				ReturnData valReturn = workOrderService.updateWorkOrder(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_WORKORDER)) {
				long id = (long) data;
				ReturnData valReturn = workOrderService.deleteWorkOrder(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_SURATJALAN)) {
				BodySuratJalan param = (BodySuratJalan) data;
				ReturnData valReturn =  suratJalanService.saveObject(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_SURATJALAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodySuratJalan body  = (BodySuratJalan) param.get("body");
				ReturnData valReturn = suratJalanService.updateObject(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_SURATJALAN)) {
				long id = (long) data;
				ReturnData valReturn = suratJalanService.deleteObject(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_STATUS_SURATJALAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyStatusSuratJalan body  = (BodyStatusSuratJalan) param.get("body");
				ReturnData valReturn = suratJalanService.updateStatus(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_DOCUMENT_WORKORDER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("UPLOADDOCUMENT")) {
					MultipartFile file = (MultipartFile) param.get("body");
					Long id = (Long) param.get("id");
					ReturnData valReturn = workOrderService.uploadDocumentWorkOrder(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, file);
					if(valReturn.isSuccess()) {
						val.setData(valReturn.getId());
					}else {
						val.setSuccess(valReturn.isSuccess());
						val.setHttpcode(HttpStatus.BAD_REQUEST.value());
						val.setValidations(valReturn.getValidations());
						val.setData(null);
					}
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_DOCUMENT_WORKORDER)) {
				long id = (long) data;
				ReturnData valReturn = workOrderService.deleteDocumentWorkOrder(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PENERIMAAN_KASBANK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("CREATE")) {
					BodyPenerimaanKasBank body = (BodyPenerimaanKasBank) param.get("body");
					ReturnData valReturn =  penerimaanKasBankService.saveData(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), body);
					if(valReturn.isSuccess()) {
						val.setData(valReturn.getId());
					}else {
						val.setSuccess(valReturn.isSuccess());
						val.setHttpcode(HttpStatus.BAD_REQUEST.value());
						val.setValidations(valReturn.getValidations());
						val.setData(null);
					}
				}else if(type.equals("SEARCHINVOICE")) {
					com.servlet.invoice.entity.BodySearch body = (com.servlet.invoice.entity.BodySearch) param.get("body");
					val.setData(invoiceService.getListSearchInvoice(auth.getIdcompany(),auth.getIdbranch(), body));
				}else if(type.equals("SEARCHWO")) {
					com.servlet.workorder.entity.BodySearch body = (com.servlet.workorder.entity.BodySearch) param.get("body");
					val.setData(workOrderService.getListSearchWO(auth.getIdcompany(),auth.getIdbranch(), body));
				}
				
			}else if(codepermission.equals(ConstansPermission.EDIT_PENERIMAAN_KASBANK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPenerimaanKasBank body  = (BodyPenerimaanKasBank) param.get("body");
				ReturnData valReturn = penerimaanKasBankService.updateData(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PENERIMAAN_KASBANK)) {
				long id = (long) data;
				ReturnData valReturn = penerimaanKasBankService.deleteData(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PENGELUARAN_KASBANK)) {
				BodyPengeluaranKasBank param = (BodyPengeluaranKasBank) data;
				ReturnData valReturn =  pengeluaranKasBankService.saveData(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PENGELUARAN_KASBANK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPengeluaranKasBank body  = (BodyPengeluaranKasBank) param.get("body");
				ReturnData valReturn = pengeluaranKasBankService.updateData(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PENGELUARAN_KASBANK)) {
				long id = (long) data;
				ReturnData valReturn = pengeluaranKasBankService.deleteData(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_INVOICE)) {
				BodyInvoice param = (BodyInvoice) data;
				ReturnData valReturn =  invoiceService.saveInvoice(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_INVOICE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyInvoice body  = (BodyInvoice) param.get("body");
				ReturnData valReturn = invoiceService.updateInvoice(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_INVOICE)) {
				long id = (long) data;
				ReturnData valReturn = invoiceService.deleteInvoice(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
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
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMERTYPE)) {
				String type = (String) data;
				if(type == "ALL") {
					val.setData(customerTypeService.getAllListCustomerType(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(customerTypeService.getCustomerTypeById(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMER)) {
				String type = (String) data;
				
				if(type.equals("ALL")) {
					val.setData(customerService.getAllListCustomer(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(customerService.customerTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(customerService.getCustomerById(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRODUCTTYPE)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(productTypeService.getAllListProductType(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(productTypeService.getProductTypeById(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRODUCT)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(productService.getAllListProduct(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(productService.getProductById(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_CALLPLAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
//				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(callPlanService.getAllListCallPlan(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(callPlanService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("SEARCHCUSTOMER")) {
					long idproject = (long) param.get("idproject");
					val.setData(customerService.getListCustomerByIdProject(idproject, auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = (long) param.get("id");//new Long(type).longValue();
					val.setData(callPlanService.getCallPlanById(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PROJECT)) {
				String type = (String) data;
				if(type.equals("ALL")) {
					val.setData(projectService.getAllListProject(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(projectService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(projectService.getProjectByIdDetail(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_INFO)) {
				String type = (String) data;
				if(type.equals("TEMPLATE")) {
					val.setData(infoHeaderService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("ALL")) {
					val.setData(infoHeaderService.getAllListDataWeb(auth.getIdcompany(), auth.getIdbranch()));
				}else {
					long id = new Long(type).longValue();
					val.setData(infoHeaderService.getDetailById(id, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_REPORT_MONITORING)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("TEMPLATE")) {
					val.setData(reportService.getTemplateReport(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("REPORT")) {
					BodyReportMonitoring body = (BodyReportMonitoring) param.get("body");
					if(body.getTypereport().equals("XLSX")) {
						val.setData(reportService.getReportMonitoringData(body, auth.getIdcompany(), auth.getIdbranch()).getWorkbook());
					}else if(body.getTypereport().equals("PPT")) {
						val.setData(reportService.getReportMonitoringDataPPT(body,auth.getIdcompany(), auth.getIdbranch()).getPpt());
					}else {
						val.setData(reportService.getReportMonitoringDataPDF(body, auth.getIdcompany(), auth.getIdbranch()));
					}
					
				}
			}else if(codepermission.equals(ConstansPermission.READ_MAPS_MONITORING)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("TEMPLATE")) {
					val.setData(userMobileService.getListAllUserMobileForMonitoring("ALL",auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("MAPS")) {
					BodyGetMaps body = (BodyGetMaps) param.get("body");
					val.setData(reportService.getListDataMaps(body, auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMER_MANGGALA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(customerManggalaService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(customerManggalaService.customerManggalaTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(customerManggalaService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE_DATA")) {
					long id = (long) param.get("id");
					val.setData(customerManggalaService.getTemplateWithDataById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("CUSTOMER_WAREHOUSE_DATA")) {
					long id = (long) param.get("id");
					val.setData(customerManggalaService.getListDetailInfoGudang(auth.getIdcompany(), auth.getIdbranch(),id));
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
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_BANK_ACCOUNT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(bankAccountService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(bankAccountService.getById(auth.getIdcompany(), auth.getIdbranch(),id,auth.getId()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_EMPLOYEE_MANGGALA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(employeeManggalaService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(employeeManggalaService.employeeManggalaTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(employeeManggalaService.getById(auth.getIdcompany(), auth.getIdbranch(),id,auth.getId()));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_VENDOR_CATEGORY)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(vendorCategoryService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(vendorCategoryService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_VENDOR)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(vendorService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(vendorService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(vendorService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_WORKORDERTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(workOrderTypeService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(workOrderTypeService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PARTAI)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(partaiService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(partaiService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PORT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(portService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(portService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PARAMETERMANGGALA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(parameterManggalaService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(parameterManggalaService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}if(type.equals("TEMPLATE")) {
					val.setData(parameterManggalaService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_WAREHOUSE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(warehouseService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(warehouseService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("SEARCH")) {
					String name = (String) param.get("name");
					val.setData(warehouseService.getListSearchWarehouse(auth.getIdcompany(), auth.getIdbranch(),name));
				}else if(type.equals("TEMPLATE")) {
					val.setData(warehouseService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_INVOICETYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(invoiceTypeService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(invoiceTypeService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(invoiceTypeService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_PRICELIST)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(priceListService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(priceListService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(priceListService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE_DATA")) {
					long id = (long) param.get("id");
					val.setData(priceListService.getDataWithTemplateById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("SEARCHDATA")) {
					BodySearchPriceList body = (BodySearchPriceList) param.get("body");
					val.setData(priceListService.getListSearch(auth.getIdcompany(), auth.getIdbranch(), body));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_PAYMENTTYPE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(paymentTypeService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(paymentTypeService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(paymentTypeService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_WORKORDER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(workOrderService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(workOrderService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(workOrderService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE_DATA")) {
					long id = (long) param.get("id");
					val.setData(workOrderService.getByIdForEdit(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("SEARCHDATACUSTOMER")) {
					BodySearch body = (BodySearch) param.get("body");
					val.setData(customerManggalaService.getListSearchCustomer(auth.getIdcompany(), auth.getIdbranch(), body));
				}else if(type.equals("GET_LIST_CONTAINER")) {
					long id = (long) param.get("id");
					String nocontainer = (String) param.get("nocontainer");
					val.setData(workOrderService.getListContainerByIdWorkOrder(auth.getIdcompany(), auth.getIdbranch(),id,nocontainer));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_SURATJALAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(suratJalanService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(suratJalanService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(suratJalanService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE_WITH_ID")) {
					long id = (long) param.get("id");
					val.setData(suratJalanService.getTemplateWithDataById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("PENANDAAN_SJ_TEMPLATE")) {
					val.setData(suratJalanService.getPenandaanSuratJalanTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_PRINT_SURATJALAN)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("PRINT")) {
					long id = (long) param.get("id");
					val.setData(suratJalanService.printById(auth.getIdcompany(), auth.getIdbranch(),id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_DOCUMENT_WORKORDER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				
				if(type.equals("DOCUMENT_DATA")) {
					long id = (long) param.get("id");
					val.setData(workOrderService.getDocumentWorkOrder(auth.getIdcompany(), auth.getIdbranch(),id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_REPORT_BONGKARMUATDEPO)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("REPORT")) {
					Manggala_BodyReportBongkarMuatDanDepo body = (Manggala_BodyReportBongkarMuatDanDepo) param.get("body");
					if(body.getTypeReport().equals("XLSX")) {
						val.setData(reportServiceManggala.getReportBongkarMuatDanDepo(body, auth.getIdcompany(), auth.getIdbranch()).getWorkbook());
					}
//					else if(body.getTypereport().equals("PPT")) {
//						val.setData(reportService.getReportMonitoringDataPPT(body,auth.getIdcompany(), auth.getIdbranch()).getPpt());
//					}else {
//						val.setData(reportService.getReportMonitoringDataPDF(body, auth.getIdcompany(), auth.getIdbranch()));
//					}
					
				}
			}else if(codepermission.equals(ConstansPermission.READ_PENERIMAAN_KASBANK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(penerimaanKasBankService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(penerimaanKasBankService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(penerimaanKasBankService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE_ID")) {
					long id = (long) param.get("id");
					val.setData(penerimaanKasBankService.getByIdWithTemplate(auth.getIdcompany(), auth.getIdbranch(),id));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_PENGELUARAN_KASBANK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(pengeluaranKasBankService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(pengeluaranKasBankService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(pengeluaranKasBankService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE_ID")) {
					long id = (long) param.get("id");
					val.setData(pengeluaranKasBankService.getByIdWithTemplate(auth.getIdcompany(), auth.getIdbranch(),id));
				}
				
			}else if(codepermission.equals(ConstansPermission.READ_INVOICE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(invoiceService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(invoiceService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(invoiceService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("DETAIL_TEMPLATE")) {
					long id = (long) param.get("id");
					val.setData(invoiceService.getByIdWithTemplate(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("SEARCHDATACUSTOMER")) {
					BodySearch body = (BodySearch) param.get("body");
					val.setData(customerManggalaService.getListSearchCustomer(auth.getIdcompany(), auth.getIdbranch(), body));
				}else if(type.equals("SEACRHWO")) {
					long idcustomer = (long) param.get("idcustomer");
					
					HashMap<String, Object> mapParam = new HashMap<String, Object>();
					mapParam.put("type", "INVOICE");
					mapParam.put("idcustomer", idcustomer);
					
					val.setData(workOrderService.getListWOByStatus(auth.getIdcompany(), auth.getIdbranch(),"OPEN", mapParam));
				}else if(type.equals("SEACRH_SURATJALAN")) {
					long idwo = (long) param.get("idwo");
					val.setData(suratJalanService.getListByIdWO(auth.getIdcompany(), auth.getIdbranch(),idwo));
				}else if(type.equals("SEACRH_PRICELIST")) {
					BodySearchInvoicePriceList body = (BodySearchInvoicePriceList) param.get("body");
					long idcustomer = body.getIdcustomer() == null?0:body.getIdcustomer();
					long idwarehouse = body.getIdwarehouse() == null?0:body.getIdwarehouse();
					String idinvoicetype = body.getIdinvoicetype();
					val.setData(priceListService.getListPriceListByIdCustomer(auth.getIdcompany(), auth.getIdbranch(),idcustomer,idwarehouse,idinvoicetype,body.getJalur()));
				}else if(type.equals("PRINTINVOICE")) {
					long id = (long) param.get("id");
					val.setData(invoiceService.printInvoice(auth.getIdcompany(), auth.getIdbranch(),id));
				}
				
			}
			
		}else if(auth.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {
			if(codepermission.equals(ConstansPermission.READ_INFO_MOBILE)) {
				String type = (String) data;
				if(type.equals("ALL_MOBILE")) {
					val.setData(infoHeaderService.getAllListDataMobile(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_DOWNLOAD_MOBILE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("DOWNLOAD")) {
					val.setData(downloadService.donwload(auth.getId(), auth.getIdcompany(), auth.getIdbranch()));
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
