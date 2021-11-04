package com.servlet.login.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.login.entity.BodyLogin;
import com.servlet.login.entity.BodyLoginMobile;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.AESEncryptionDecryption;
import com.servlet.shared.Response;
import com.servlet.user.service.UserAppsService;



@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "${value.cross_origin}")
public class LoginApi {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginApi.class);
	
	@Autowired
	UserAppsService userappsservice;
	@Autowired
	SecurityService securityService;
	@Autowired
	UserMobileService userMobileService;
	
	@PostMapping("/login")
	ResponseEntity<Response> getLogin(@RequestBody @Validated BodyLogin bodylogin){
		Response response = securityService.response("login",userappsservice.actionLogin(bodylogin.getUser(), bodylogin.getPassword()),"login");
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/usermobile/login")
	ResponseEntity<Response> getLoginMobile(@RequestBody @Validated BodyLoginMobile bodylogin){
		Response response = securityService.response("login",userMobileService.actionLogin(bodylogin.getUser(), bodylogin.getPassword(),bodylogin.getImei()),"login");
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/jdhaiieuwolashey")
	ResponseEntity<Response> genaretePassword(@RequestBody @Validated BodyLogin bodylogin){
		AESEncryptionDecryption aesEncryptionDecryption = new AESEncryptionDecryption();
		String encryptedPassToken = aesEncryptionDecryption.encrypt(bodylogin.getPassword());
		Response response = new Response();
		response.setMessage(encryptedPassToken);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
