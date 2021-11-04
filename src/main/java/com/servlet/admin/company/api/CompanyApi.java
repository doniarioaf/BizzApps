package com.servlet.admin.company.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servlet.admin.company.entity.BodyCompany;
import com.servlet.admin.company.service.CompanyService;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/company")
@CrossOrigin(origins = "${value.cross_origin}")
public class CompanyApi {
	@Autowired
	SecurityService securityService;
	@Autowired
	CompanyService companyService;
	
	@GetMapping("{id}")
	ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_COMPANY,companyService.getCompanyAndCompanyBranchByID(id),authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/getlistcompanyactive")
	ResponseEntity<Response> getListCompanyActive(@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_COMPANY,companyService.getListCompanyActive(),authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping
	ResponseEntity<Response> getListCompany(@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_COMPANY,companyService.getAllListCompany(),authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping
	ResponseEntity<Response> createCompany(@RequestBody @Validated BodyCompany company, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_COMPANY,company,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping("{id}")
	ResponseEntity<Response> updateCompany(@PathVariable long id,@RequestBody @Validated BodyCompany company, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("BodyCompany", company);
		Response response = securityService.response(ConstansPermission.EDIT_COMPANY,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
