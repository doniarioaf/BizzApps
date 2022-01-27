package com.servlet.mobile.monitorusermobile.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/monitorusermobile")
@CrossOrigin(origins = "${value.cross_origin}")
public class MonitorUserMobileApi {
	@Autowired
	SecurityService securityService;
	
	@PostMapping
	ResponseEntity<Response> createMonitorUserMobile(@RequestBody @Validated BodyMonitorUserMobile body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_MONITOR_USER_MOBILE,body,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping
	ResponseEntity<Response> getTime() {
		Response response = new Response();
		response.setData(new Date().getTime());
		response.setMessage("");
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
