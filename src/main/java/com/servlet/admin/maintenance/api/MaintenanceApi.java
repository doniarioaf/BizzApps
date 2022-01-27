package com.servlet.admin.maintenance.api;

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

import com.servlet.admin.permission.entity.BodyPermission;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/maintenance")
@CrossOrigin(origins = "${value.cross_origin}")
public class MaintenanceApi {
	@Autowired
	SecurityService securityService;
	
	@PostMapping("/addpermission")
	ResponseEntity<Response> createPermission(@RequestBody @Validated BodyPermission body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_MAINTENANCE,body,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
}
