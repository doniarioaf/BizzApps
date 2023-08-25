package com.servlet.shared;

public class ConstansCodeMessage {
	public static final String CODE_MESSAGE_SUCCESS = "SUCCESS";
	
	//Kode Jika Username Atau Password Salah
	public static final String CODE_MESSAGE_USERNAME_OR_PASSWORD_WRONG = "user.username.or.password.wrong";
	
	//Kode Jika ada error pada code (BackEnd)
	public static final String CODE_MESSAGE_INTERNAL_SERVER_ERROR = "internal.server.error";
	
	//token tidak dikenali
	public static final String CODE_MESSAGE_SECURITY_LOGIN_NOT_AUTHORIZED = "security.login.not.authorized";
	
	//token dikenali tapi token sudah expired atau ada orang lain login memakai user yang sama di tempat lain
	//jika mengandung code ini, disarankan balik ke menu login
	public static final String CODE_MESSAGE_SECURITY_TOKEN_PASSWORD = "security.token.password";
	
	//user tidak punya akses untuk hit api tersebut
	public static final String CODE_USER_NOT_HAVE_ACCESS = "user.does.not.have.access";
	
	//user belum mendapatkan role
	public static final String CODE_USER_NOT_REGISTRED_ROLE = "user.is.not.registered.role";
	
	public static final String DATA_VALIDATION = "data.validation";
	
	public static final String USERNAME_IS_EXIST = "username.is.exist";
	public static final String INFO_TYPE_NOT_EXIST = "info.type.not.exist";
	public static final String DATA_NOT_FOUND = "data.not.found";
	
	//company user tidak ditemukan
	public static final String USER_COMPANY_NOT_EXIST = "user.company.not.exist";
	
	//license company belum di setting
	public static final String COMPANY_LICENSE_NOT_EXIST = "company.license.not.exist";
	
	//license company sudah expired
	public static final String COMPANY_LICENSE_EXPIRED = "company.license.expired";
	
	//Mac Addreess
	public static final String MAC_ADDRESS_NOT_SAME = "mac.address.not.same";
	public static final String MAC_ADDRESS_NOT_FOUND = "mac.address.not.found";
	
	//jika code ini muncul, ini bukan error, hanya untuk info 
	//disarankan menampilkan alert di UI web ataupun UI android, sebagai pengingat
	public static final String COMPANY_LICENSE_ALERT_EXPIRED = "company.license.alert.expired";
	
	//code ini hanya untuk web
	public static final String LIMIT_CREATE_USER = "limit.create.user";
	
	public static final String FILE_EXCEL_FAILED = "file.excel.failed";
	
	public static final String CUSTOMERCODE_IS_EXIST = "customercode.is.exist";
	
//	//sukses, tapi mengandung catatan, disarankan untuk menampilkan alert catatan tersebut
//	public static final String SUCCESS_ALERT = "SUCCESS_ALERT";
	
	public static final String VALIDASI_GENERATE_DOC_NUMBER = "validation.generate.doc.number";
	
	public static final String VALIDASI_BANK_ACCOUNT_DATEOPEN_EMPTY = "validation.bankaccount.dateopen.empty";
	public static final String VALIDASI_BANK_ACCOUNT_DATEOPEN_NOTVALID = "validation.bankaccount.dateopen.notvalid";
	public static final String VALIDASI_BANK_ACCOUNT_NO_REK_EMPTY = "validation.bankaccount.norekening.empty";
	public static final String VALIDASI_BANK_ACCOUNT_NO_REK_NOTNUMBER = "validation.bankaccount.norekening.notnumber";
	public static final String VALIDASI_BANK_ACCOUNT_CABANG_EMPTY = "validation.bankaccount.cabang.empty";
	
	public static final String VALIDASI_EMPLOYEE_MANGGALA_NO_IDENTITAS_EXIST = "validation.employeemanggala.noidentitas.exist";
	
	public static final String VALIDASI_WORKORDERTYPE_CODE_EXIST = "validation.workordertype.code.exist";
	
	public static final String VALIDASI_PARAMETERMANGGALA_PARAMNAME_EXIST = "validation.parametermanggala.paramname.exist";
	
	public static final String VALIDASI_WAREHOUSE_NOT_EXIST = "validation.warehouse.not.exist";
	public static final String VALIDASI_WAREHOUSE_NOT_ACTIVE = "validation.warehouse.not.active";
	public static final String VALIDASI_WAREHOUSE_USING_OTHERCUSTOMER = "validation.warehouse.using.othercustomer";
	public static final String VALIDASI_DELETE_WAREHOUSE_USING_OTHERCUSTOMER = "validation.delete.warehouse.using.othercustomer";
	public static final String VALIDASI_ACTIVATION_WAREHOUSE_USING_OTHERCUSTOMER = "validation.activation.warehouse.using.othercustomer";
	
	public static final String VALIDASI_INVOICETYPE_NAME_EXISTS = "validation.invoicetype.name.exist";
	public static final String VALIDASI_PRICELIST_CUSTOMER_EXISTS = "validation.pricelist.customer.exist";
	
	public static final String VALIDASI_VENDORCATEGORY_USINGVENDOR = "validation.vendorcategory.usingvendor";
	public static final String VALIDASI_PAYMENTTYPE_NAME_EXISTS = "validation.paymenttype.name.exist";
	
	public static final String VALIDASI_WORK_ORDER_INVOICENO_NOT_EMPTY = "validation.workorder.invoiceno.not.empty";
	public static final String VALIDASI_DOCUMENT_INCORRECT_FORMAT = "validation.document.incorrect.format";
	public static final String VALIDASI_DOCUMENT_MAX_SIZE_OVER_LIMIT = "validation.document.max.size.overlimit";
	
	public static final String VALIDASI_PENERIMAANKASBANK_WO_STATUS_NOT_OPEN = "validation.penerimaankasbank.wo.status.not.open";
	public static final String VALIDASI_PENERIMAANKASBANK_WO_NOT_ACTIVE = "validation.penerimaankasbank.wo.not.active";
	
	public static final String VALIDASI_CUSTOMER_NOT_FOUND = "validation.customer.not.found";
	public static final String VALIDASI_CUSTOMER_NOT_ACTIVE = "validation.customer.not.active";
	public static final String VALIDASI_WO_NOT_FOUND = "validation.wo.not.found";
	public static final String VALIDASI_WO_NOT_ACTIVE = "validation.wo.not.active";
	public static final String VALIDASI_WO_NOT_AVAILABLE = "validation.wo.status.not.available";
	public static final String VALIDASI_SJ_NOT_FOUND = "validation.sj.not.found";
	public static final String VALIDASI_SJ_NOT_ACTIVE = "validation.sj.not.active";
	public static final String VALIDASI_SJ_NOT_AVAILABLE = "validation.sj.status.not.available";
	
	public static final String VALIDASI_INVOICE_ALREADY_PAYMENT_EDIT = "validation.invoice.already.payment.edit";
	public static final String VALIDASI_INVOICE_ALREADY_PAYMENT_DELETE = "validation.invoice.already.payment.delete";
	public static final String VALIDASI_ASSET_KODEASSET_ISEXIST = "validation.asset.kodeasset.isexist";
	public static final String VALIDASI_PENERIMAAN_DATE_CLOSEBOOK = "validation.penerimaan.date.closebook";
	public static final String VALIDASI_PENGELUARAN_DATE_CLOSEBOOK = "validation.pengeluaran.date.closebook";
	public static final String VALIDASI_INVOICE_WO_EXIST_REIMBURSEMENT = "validation.invoice.wo.exist.reimbursement";
	public static final String VALIDASI_SAVE_BANK_FINANCE_JUNIOR = "validation.save.bank.financejunior";
	
}
