package com.servlet.mobile.upload.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.servlet.mobile.monitorusermobile.entity.BodyMonitorUserMobile;
import com.servlet.mobile.usermobilelocation.entity.BodyUserMobileLocation;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/upload")
@CrossOrigin(origins = "${value.cross_origin}")
public class UploadApi {
	@Autowired
	SecurityService securityService;
	
	@PostMapping("/location")
	ResponseEntity<Response> createUploadDataLocation(@RequestBody @Validated BodyUserMobileLocation body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_LOCATION_MOBILE,body,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/monitorusermobile")
	ResponseEntity<Response> createMonitorUserMobile(@RequestBody @Validated BodyMonitorUserMobile body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_MONITOR_USER_MOBILE,body,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
}
