package com.servlet.mobile.download.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/download")
@CrossOrigin(origins = "${value.cross_origin}")
public class DownloadDataMobileApi {
	@Autowired
	SecurityService securityService;
	
	@GetMapping
	ResponseEntity<Response> getDownloadData(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "DOWNLOAD");
		Response response = securityService.response(ConstansPermission.READ_DOWNLOAD_MOBILE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/callplan")
	ResponseEntity<Response> getDownloadDataUserMobileCallPan(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam long limit,@RequestParam long offset) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "DOWNLOAD_CALL_PLAN");
		param.put("limit", limit);
		param.put("offset", offset);
		Response response = securityService.response(ConstansPermission.READ_DOWNLOAD_MOBILE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/customercallplan")
	ResponseEntity<Response> getDownloadDataUserMobileCustomerCallPan(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam long limit,@RequestParam long offset) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "DOWNLOAD_CUSTOMER_CALL_PLAN");
		param.put("limit", limit);
		param.put("offset", offset);
		Response response = securityService.response(ConstansPermission.READ_DOWNLOAD_MOBILE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
