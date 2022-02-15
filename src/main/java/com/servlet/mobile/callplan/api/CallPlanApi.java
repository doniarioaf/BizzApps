package com.servlet.mobile.callplan.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.servlet.mobile.callplan.entity.BodyCallPlan;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/callplan")
@CrossOrigin(origins = "${value.cross_origin}")
public class CallPlanApi {
	@Autowired
	SecurityService securityService;
	
	
	@GetMapping("{id}")
	ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("type", "DETAIL");
		Response response = securityService.response(ConstansPermission.READ_CALLPLAN,param.toString(),authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping
	ResponseEntity<Response> getListCallPlan(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "ALL");
		Response response = securityService.response(ConstansPermission.READ_CALLPLAN,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/template")
	ResponseEntity<Response> getTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "TEMPLATE");
		Response response = securityService.response(ConstansPermission.READ_CALLPLAN,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/template/searchcustomer/{idproject}")
	ResponseEntity<Response> getTemplateSearcCustomer(@PathVariable long idproject,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("idproject", idproject);
		param.put("type", "SEARCHCUSTOMER");
		Response response = securityService.response(ConstansPermission.READ_CALLPLAN,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping
	ResponseEntity<Response> createCallPlan(@RequestBody @Validated BodyCallPlan body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_CALLPLAN,body,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping("{id}")
	ResponseEntity<Response> updateCallPlan(@PathVariable long id,@RequestBody @Validated BodyCallPlan body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("BodyCallPlan", body);
		Response response = securityService.response(ConstansPermission.EDIT_CALLPLAN,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<Response> deleteCallPlan(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.DELETE_CALL_PLAN,id,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
}
