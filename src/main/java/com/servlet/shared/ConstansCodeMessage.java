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
}
