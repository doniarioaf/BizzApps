package com.servlet.Process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.servlet.area.entity.BodyArea;
import com.servlet.area.service.AreaService;
import com.servlet.cargo.entity.BodyCargo;
import com.servlet.cargo.entity.ParamCargoSearch;
import com.servlet.cargo.service.CargoService;
import com.servlet.categoryproduct.entity.BodyCategoryProduct;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.customer.entity.BodyCustomer;
import com.servlet.customer.service.CustomerService;
import com.servlet.deposit.entity.BodyDeposit;
import com.servlet.deposit.entity.ParamList;
import com.servlet.deposit.service.DepositService;
import com.servlet.draftpurchasereceive.entity.BodyDraftPurchaseReceive;
import com.servlet.draftpurchasereceive.entity.ParamSearchDraftPurchaseReceive;
import com.servlet.draftpurchasereceive.service.DraftPurchaseReceiveService;
import com.servlet.inventori.entity.BodyInventori;
import com.servlet.inventori.service.InventoriService;
import com.servlet.invoice.entity.BodyInvoice;
import com.servlet.invoice.entity.ParamSearchInvoice;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.mappingstock.entity.BodyMappingStock;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.packinglist.entity.BodyPackingList;
import com.servlet.packinglist.entity.ParamSearchPackingList;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.parameterclient.entity.BodyParameterClient;
import com.servlet.parameterclient.service.ParameterClientService;
import com.servlet.pelunasanhutang.entity.BodyPelunasanHutang;
import com.servlet.pelunasanhutang.entity.FilterParamPelunasanHutang;
import com.servlet.pelunasanhutang.service.PelunasanHutangService;
import com.servlet.pelunasanpiutang.entity.BodyPelunasanPiutang;
import com.servlet.pelunasanpiutang.entity.FilterParamPelunasanPiutang;
import com.servlet.pelunasanpiutang.service.PelunasanPiutangService;
import com.servlet.pricelist.entity.BodyPriceList;
import com.servlet.pricelist.service.PriceService;
import com.servlet.product.entity.BodyProduct;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.BodyPurchaseReceive;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.report.entity.*;
import com.servlet.report.service.ReportService;
import com.servlet.stockadjusment.entity.BodyStockAdjusment;
import com.servlet.stockadjusment.service.StockAdjusmentService;
import com.servlet.vendor.entity.BodyVendor;
import com.servlet.vendor.service.VendorService;
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
import org.springframework.web.multipart.MultipartFile;

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

	@Autowired
	ParameterClientService parameterClientService;

	@Autowired
	CustomerService customerService;
	@Autowired
	ProductService productService;
	@Autowired
	VendorService vendorService;
	@Autowired
	InventoriService inventoriService;
	@Autowired
	CategoryProductService categoryProductService;
	@Autowired
	MappingStockService mappingStockService;
	@Autowired
	PriceService priceService;
	@Autowired
	PurchaseReceiveService purchaseReceiveService;

	@Autowired
	DepositService depositService;

	@Autowired
	AreaService areaService;

	@Autowired
	DraftPurchaseReceiveService draftPurchaseReceiveService;
	@Autowired
	StockAdjusmentService stockAdjusmentService;

	@Autowired
	PackingListService packingListService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	ReportService reportService;
	@Autowired
	PelunasanHutangService pelunasanHutangService;
	@Autowired
	CargoService cargoService;

	@Autowired
	PelunasanPiutangService pelunasanPiutangService;
	
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
			}else if(codepermission.equals(ConstansPermission.CREATE_PARAMETERCLIENT)) {
				BodyParameterClient param = (BodyParameterClient) data;
				ReturnData valReturn = parameterClientService.saveParameterManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(), param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PARAMETERCLIENT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyParameterClient body  = (BodyParameterClient) param.get("body");
				ReturnData valReturn = parameterClientService.updateParameterManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id, body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PARAMETERCLIENT)) {
				long id = (long) data;
				ReturnData valReturn = parameterClientService.deleteParameterManggala(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),id);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_CUSTOMER)) {
				BodyCustomer param = (BodyCustomer) data;
				ReturnData valReturn = customerService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_CUSTOMER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyCustomer body  = (BodyCustomer) param.get("body");
				ReturnData valReturn = customerService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_CUSTOMER)) {
				long id = (long) data;
				ReturnData valReturn = customerService.delete(id,auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_PRODUCT)) {
				BodyProduct param = (BodyProduct) data;
				ReturnData valReturn = productService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PRODUCT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyProduct body  = (BodyProduct) param.get("body");
				ReturnData valReturn = productService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PRODUCT)) {
				long id = (long) data;
				ReturnData valReturn = productService.delete(id,auth.getId());
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
				ReturnData valReturn = vendorService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
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
				ReturnData valReturn = vendorService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
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
				ReturnData valReturn = vendorService.delete(id,auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.CREATE_INVENTORI)) {
				BodyInventori param = (BodyInventori) data;
				ReturnData valReturn = inventoriService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_INVENTORI)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyInventori body  = (BodyInventori) param.get("body");
				ReturnData valReturn = inventoriService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_INVENTORI)) {
				long id = (long) data;
				ReturnData valReturn = inventoriService.delete(id,auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_CATEGORYPRODUCT)) {
				BodyCategoryProduct param = (BodyCategoryProduct) data;
				ReturnData valReturn = categoryProductService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_CATEGORYPRODUCT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyCategoryProduct body  = (BodyCategoryProduct) param.get("body");
				ReturnData valReturn = categoryProductService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_CATEGORYPRODUCT)) {
				long id = (long) data;
				ReturnData valReturn = categoryProductService.delete(id,auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_MAPPINGSTOCK)) {
				BodyMappingStock param = (BodyMappingStock) data;
				ReturnData valReturn = mappingStockService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_MAPPINGSTOCK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyMappingStock body  = (BodyMappingStock) param.get("body");
				ReturnData valReturn = mappingStockService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_MAPPINGSTOCK)) {
				long id = (long) data;
				ReturnData valReturn = mappingStockService.delete(id,auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_PRICELIST)) {
				BodyPriceList param = (BodyPriceList) data;
				ReturnData valReturn = priceService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
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
				ReturnData valReturn = priceService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
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
				ReturnData valReturn = priceService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_PURCHASERECEIVE)) {
				BodyPurchaseReceive param = (BodyPurchaseReceive) data;
				ReturnData valReturn = purchaseReceiveService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PURCHASERECEIVE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPurchaseReceive body  = (BodyPurchaseReceive) param.get("body");
				ReturnData valReturn = purchaseReceiveService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PURCHASERECEIVE)) {
				long id = (long) data;
				ReturnData valReturn = purchaseReceiveService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_DEPOSIT)) {
				BodyDeposit param = (BodyDeposit) data;
				ReturnData valReturn = depositService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_DEPOSIT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyDeposit body  = (BodyDeposit) param.get("body");
				ReturnData valReturn = depositService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_DEPOSIT)) {
				long id = (long) data;
				ReturnData valReturn = depositService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_AREA)) {
				BodyArea param = (BodyArea) data;
				ReturnData valReturn = areaService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_AREA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyArea body  = (BodyArea) param.get("body");
				ReturnData valReturn = areaService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_AREA)) {
				long id = (long) data;
				ReturnData valReturn = areaService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}
			else if(codepermission.equals(ConstansPermission.CREATE_DRAFTPURCHASERECEIVE)) {
				BodyDraftPurchaseReceive param = (BodyDraftPurchaseReceive) data;
				ReturnData valReturn = draftPurchaseReceiveService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_DRAFTPURCHASERECEIVE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyDraftPurchaseReceive body  = (BodyDraftPurchaseReceive) param.get("body");
				ReturnData valReturn = draftPurchaseReceiveService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_DRAFTPURCHASERECEIVE)) {
				long id = (long) data;
				ReturnData valReturn = draftPurchaseReceiveService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_STOCKADJUSMENT)) {
				BodyStockAdjusment param = (BodyStockAdjusment) data;
				ReturnData valReturn = stockAdjusmentService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_STOCKADJUSMENT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyStockAdjusment body  = (BodyStockAdjusment) param.get("body");
				ReturnData valReturn = stockAdjusmentService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_STOCKADJUSMENT)) {
				long id = (long) data;
				ReturnData valReturn = stockAdjusmentService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_PACKINGLIST)) {
				BodyPackingList param = (BodyPackingList) data;
				ReturnData valReturn = packingListService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PACKINGLIST)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPackingList body  = (BodyPackingList) param.get("body");
				ReturnData valReturn = packingListService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PACKINGLIST)) {
				long id = (long) data;
				ReturnData valReturn = packingListService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_INVOICE)) {
				BodyInvoice param = (BodyInvoice) data;
				ReturnData valReturn = invoiceService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
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
				ReturnData valReturn = invoiceService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
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
				ReturnData valReturn = invoiceService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}
			else if(codepermission.equals(ConstansPermission.CREATE_PELUNASANHUTANG)) {
				BodyPelunasanHutang param = (BodyPelunasanHutang) data;
				ReturnData valReturn = pelunasanHutangService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PELUNASANHUTANG)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPelunasanHutang body  = (BodyPelunasanHutang) param.get("body");
				ReturnData valReturn = pelunasanHutangService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PELUNASANHUTANG)) {
				long id = (long) data;
				ReturnData valReturn = pelunasanHutangService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_CARGO)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				ReturnData valReturn = new ReturnData();
				if(type.equals("CREATE")) {
					BodyCargo body = (BodyCargo) param.get("body");
					valReturn = cargoService.save(auth.getIdcompany(), auth.getIdbranch(), auth.getId(), body);
				}else if(type.equals("UPLOADFILE")) {
					long id = (long) param.get("id");
					MultipartFile file = (MultipartFile) param.get("body");
					valReturn = cargoService.uploadFileDoc(id,file,auth.getIdcompany(), auth.getIdbranch(), auth.getId());
				}
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_CARGO)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyCargo body  = (BodyCargo) param.get("body");
				ReturnData valReturn = cargoService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_CARGO)) {
				long id = (long) data;
				ReturnData valReturn = cargoService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.CREATE_PELUNASANPIUTANG)) {
				BodyPelunasanPiutang param = (BodyPelunasanPiutang) data;
				ReturnData valReturn = pelunasanPiutangService.save(auth.getIdcompany(),auth.getIdbranch(),auth.getId(),param);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.EDIT_PELUNASANPIUTANG)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				long id  = (long) param.get("id");
				BodyPelunasanPiutang body  = (BodyPelunasanPiutang) param.get("body");
				ReturnData valReturn = pelunasanPiutangService.update(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId(),body);
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}else if(codepermission.equals(ConstansPermission.DELETE_PELUNASANPIUTANG)) {
				long id = (long) data;
				ReturnData valReturn = pelunasanPiutangService.delete(id,auth.getIdcompany(),auth.getIdbranch(),auth.getId());
				if(valReturn.isSuccess()) {
					val.setData(valReturn.getId());
				}else {
					val.setSuccess(valReturn.isSuccess());
					val.setHttpcode(HttpStatus.BAD_REQUEST.value());
					val.setValidations(valReturn.getValidations());
					val.setData(null);
				}
			}

			else if(codepermission.equals(ConstansPermission.LOGOUT)) {
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
					val.setData(roleService.getAllListRole(auth.getIdcompany()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(permissionService.getAllListPermission());
				}else {
					long id = new Long(type).longValue();
					val.setData(roleService.getRoleDetail(id, auth.getIdcompany()));
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
				
			}else if(codepermission.equals(ConstansPermission.READ_PARAMETERCLIENT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(parameterClientService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(parameterClientService.getById(auth.getIdcompany(), auth.getIdbranch(),id));
				}if(type.equals("TEMPLATE")) {
					val.setData(parameterClientService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_CUSTOMER)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(customerService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(customerService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRODUCT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(productService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(productService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_VENDOR)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(vendorService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(vendorService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(vendorService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_INVENTORI)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(inventoriService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(inventoriService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_CATEGORYPRODUCT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(categoryProductService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(categoryProductService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_MAPPINGSTOCK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(mappingStockService.getListAll(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(mappingStockService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(mappingStockService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PRICELIST)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					Long from = (Long) param.get("from");
					Long to = (Long) param.get("to");
					val.setData(priceService.getListAll(auth.getIdcompany(), auth.getIdbranch(),from,to));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(priceService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(priceService.getTemplateData(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_PURCHASERECEIVE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					Long from = (Long) param.get("from");
					Long to = (Long) param.get("to");
					val.setData(purchaseReceiveService.getListAll(auth.getIdcompany(), auth.getIdbranch(),from,to));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(purchaseReceiveService.getDetail(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE")) {
					val.setData(purchaseReceiveService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("SEARCHBYVENDOR")) {
					Long idvendor = (Long) param.get("idvendor");
					val.setData(purchaseReceiveService.searchDataByVendor(auth.getIdcompany(), auth.getIdbranch(),idvendor));
				}else if(type.equals("PRINT_NOTA")) {
					long id = (long) param.get("id");
					String printtype = (String) param.get("printtype");
					val.setData(purchaseReceiveService.printNotaPurchaseReceive(auth.getIdcompany(), auth.getIdbranch(),auth.getId(),id,printtype));
				}else if(type.equals("DOWNLOAD_PRINT_NOTA")) {
					long id = (long) param.get("id");
					val.setData(purchaseReceiveService.catatDownload(id,auth.getIdcompany(), auth.getIdbranch(),auth.getId()));
				}else if(type.equals("GETITEMDRAFT")) {
					long id = (long) param.get("iddraft");
					val.setData(draftPurchaseReceiveService.getListItemsByID(id));
				}
			}else if(codepermission.equals(ConstansPermission.READ_DEPOSIT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					ParamList paramlist = (ParamList) param.get("param");
					val.setData(depositService.getList(auth.getIdcompany(), auth.getIdbranch(),paramlist));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(depositService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(depositService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
			}else if(codepermission.equals(ConstansPermission.READ_AREA)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					val.setData(areaService.getList(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(areaService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}
			else if(codepermission.equals(ConstansPermission.READ_DRAFTPURCHASERECEIVE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				ParamSearchDraftPurchaseReceive paramsearch = (ParamSearchDraftPurchaseReceive) param.get("paramsearch");
				if(type.equals("ALL")) {
					val.setData(draftPurchaseReceiveService.getList(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(draftPurchaseReceiveService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(draftPurchaseReceiveService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("SEARCHBYVENDOR")) {
					long idvendor = (long) param.get("idvendor");
					val.setData(draftPurchaseReceiveService.getTemplateByIdVendor(auth.getIdcompany(), auth.getIdbranch(),idvendor));
				}
			}
			else if(codepermission.equals(ConstansPermission.READ_REPORT_PURCHASERECEIVE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("REPORT_PEMBELIAN")) {
					ParamReportPembelian body = (ParamReportPembelian) param.get("body");
					val.setData(reportService.reportPembelian(auth.getIdcompany(), auth.getIdbranch(),body).getWorkbook());
				}else if(type.equals("REPORT_TEMPLATE")) {
					val.setData(purchaseReceiveService.getReportTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_STOCKADJUSMENT)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("ALL")) {
					Long from = (Long) param.get("from");
					Long to = (Long) param.get("to");
					val.setData(stockAdjusmentService.getListAll(auth.getIdcompany(), auth.getIdbranch(),from,to));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(stockAdjusmentService.getDetail(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("TEMPLATE")) {
					val.setData(stockAdjusmentService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("PRICELIST")) {
					long pricedate = (long) param.get("pricedate");
					val.setData(priceService.getDataPriceByDate(auth.getIdcompany(), auth.getIdbranch(),pricedate));
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_PACKINGLIST)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				ParamSearchPackingList paramsearch = (ParamSearchPackingList) param.get("paramsearch");
				if(type.equals("ALL")) {
					val.setData(packingListService.getList(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(packingListService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("PRINT")) {
					long id = (long) param.get("id");
					val.setData(packingListService.getPrintData(id,auth.getIdcompany(), auth.getIdbranch(),auth.getId()));
				}else if(type.equals("DOWNLOAD_PRINTPDF")) {
					long id = (long) param.get("id");
					val.setData(packingListService.catatDownload(id,auth.getIdcompany(), auth.getIdbranch(),auth.getId()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(packingListService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("PRICELIST")) {
					long pricedate = (long) param.get("pricedate");
					val.setData(priceService.getDataPriceByDate(auth.getIdcompany(), auth.getIdbranch(),pricedate));
				}else if(type.equals("PRINTEXCEL")) {
					long id = (long) param.get("id");
					val.setData(reportService.getExcelPackingListByID(id,auth.getIdcompany(), auth.getIdbranch()).getWorkbook());
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_INVOICE)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				ParamSearchInvoice paramsearch = (ParamSearchInvoice) param.get("paramsearch");
				if(type.equals("ALL")) {
					val.setData(invoiceService.getList(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(invoiceService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("TEMPLATE")) {
					val.setData(invoiceService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("GET_PACKINGLIST")) {
					long id = (long) param.get("id");
					val.setData(packingListService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("PRINT")) {
					long id = (long) param.get("id");
					val.setData(invoiceService.getPrintDataByID(id,auth.getIdcompany(), auth.getIdbranch(), auth.getId()));
				}else if(type.equals("DOWNLOAD_PRINTPDF")) {
					long id = (long) param.get("id");
					val.setData(invoiceService.catatDownload(id,auth.getIdcompany(), auth.getIdbranch(), auth.getId()));
				}else if(type.equals("PRINTEXCEL")) {
					long id = (long) param.get("id");
					val.setData(reportService.getExcelInvoiceByID(id,auth.getIdcompany(), auth.getIdbranch()).getWorkbook());
				}
			}
			else if(codepermission.equals(ConstansPermission.READ_REPORT_STOCKUDANGHIDUPMATI)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("REPORTUDANGHIDUPMATI")) {
					ParamReportStockUdangHidupMati body = (ParamReportStockUdangHidupMati) param.get("body");
					val.setData(reportService.reportStockUdangHidupMati(auth.getIdcompany(), auth.getIdbranch(),body).getWorkbook());
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_REPORT_REKAPANBARANGMASUK)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("REPORTREKAPANBARANGMASUK")) {
					ParamReportRekapStock body = (ParamReportRekapStock) param.get("body");
					val.setData(reportService.reportRekapStock(auth.getIdcompany(), auth.getIdbranch(),body).getWorkbook());
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_REPORT_STATUSTAGIHANCARGO)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("REPORTSTATUSTAGIHANCARGO")) {
					ParamReportStatusTagihanCargo body = (ParamReportStatusTagihanCargo) param.get("body");
					val.setData(reportService.reportStatusTagihanCargo(auth.getIdcompany(), auth.getIdbranch(),body).getWorkbook());
				}else if(type.equals("REPORTSTATUSTAGIHANCARGO_TEMPLATE")) {
					val.setData(reportService.reportTemplateStatusTagihanCargo(auth.getIdcompany(), auth.getIdbranch()));
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_REPORT_HUTANG)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				if(type.equals("REPORTHUTANG")) {
					ParamReportHutang body = (ParamReportHutang) param.get("body");
					val.setData(reportService.reportHutang(auth.getIdcompany(), auth.getIdbranch(),body).getWorkbook());
				}else if(type.equals("REPORTHUTANG_TEMPLATE")) {
					val.setData(reportService.reportTemplateReportHutang(auth.getIdcompany(), auth.getIdbranch()));
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_PELUNASANHUTANG)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				FilterParamPelunasanHutang paramsearch = (FilterParamPelunasanHutang) param.get("paramsearch");
				if(type.equals("ALL")) {
					val.setData(pelunasanHutangService.getList(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("HUTANG_LIST")) {
					val.setData(pelunasanHutangService.getListHutang(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("DETAIL_HUTANG_PR")) {
					long id = (long) param.get("id");
					val.setData(pelunasanHutangService.getDetailHutangPR(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("DETAIL_HUTANG_CARGO")) {
					long id = (long) param.get("id");
					val.setData(pelunasanHutangService.getDetailHutangCargo(auth.getIdcompany(), auth.getIdbranch(),id));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(pelunasanHutangService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_PELUNASANPIUTANG)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				FilterParamPelunasanPiutang paramsearch = (FilterParamPelunasanPiutang) param.get("paramsearch");
				if(type.equals("TEMPLATE")) {
					val.setData(pelunasanPiutangService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("PIUTANG_LIST")) {
					val.setData(invoiceService.getListInvoicePelunasanPiutang(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("BAYAR_LIST_INVOICE")) {
					String listID = (String) param.get("id");
					val.setData(invoiceService.getListInvoicePelunasanPiutangByListID(auth.getIdcompany(), auth.getIdbranch(),listID));
				}else if(type.equals("PELUNASANPIUTANG_LIST")) {
					val.setData(pelunasanPiutangService.getPelunasanPiutangList(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(pelunasanPiutangService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}

			else if(codepermission.equals(ConstansPermission.READ_CARGO)) {
				HashMap<String, Object> param = (HashMap<String, Object>) data;
				String type = (String) param.get("type");
				ParamCargoSearch paramsearch = (ParamCargoSearch) param.get("paramsearch");
				if(type.equals("ALL")) {
					val.setData(cargoService.getList(auth.getIdcompany(), auth.getIdbranch(),paramsearch));
				}else if(type.equals("TEMPLATE")) {
					val.setData(cargoService.getTemplate(auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DETAIL")) {
					long id = (long) param.get("id");
					val.setData(cargoService.getDetail(id,auth.getIdcompany(), auth.getIdbranch()));
				}else if(type.equals("DOWNLOADFILE")) {
					long id = (long) param.get("id");
					val.setData(cargoService.downloadFile(id,auth.getIdcompany(), auth.getIdbranch()));
				}
			}

			else if(auth.getTypelogin().equals(ConstansKey.TYPE_MOBILE)) {}
				
		}
		return val;
	}

	

}
