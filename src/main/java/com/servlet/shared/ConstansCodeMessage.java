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
	
	//jika code ini muncul, ini bukan error, hanya untuk info 
	//disarankan menampilkan alert di UI web ataupun UI android, sebagai pengingat
	public static final String COMPANY_LICENSE_ALERT_EXPIRED = "company.license.alert.expired";
	
	//code ini hanya untuk web
	public static final String LIMIT_CREATE_USER = "limit.create.user";
	
	public static final String FILE_EXCEL_FAILED = "file.excel.failed";
	
//	//sukses, tapi mengandung catatan, disarankan untuk menampilkan alert catatan tersebut
//	public static final String SUCCESS_ALERT = "SUCCESS_ALERT";
}
