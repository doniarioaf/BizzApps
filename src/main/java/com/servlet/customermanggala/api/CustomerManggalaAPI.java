package com.servlet.customermanggala.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/customermanggala")
@CrossOrigin(origins = "${value.cross_origin}")
public class CustomerManggalaAPI {
	@Autowired
	SecurityService securityService;
	
	@GetMapping("/template")
	ResponseEntity<Response> getListBranchActive(@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_CUSTOMER_MANGGALA,"TEMPLATE",authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
