package com.servlet.user.api;

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
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import com.servlet.user.entity.BodyUserApps;
import com.servlet.user.service.UserAppsService;

@RestController
@RequestMapping("/v1/userapps")
@CrossOrigin(origins = "${value.cross_origin}")
public class UserApi {
	@Autowired
	SecurityService securityService;
	@Autowired
	UserAppsService userAppsService;
	
	@GetMapping("{id}")
	ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_USER,new Long(id).toString(),authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping
	ResponseEntity<Response> getListAllUserApps(@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_USER,"ALL",authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping
	ResponseEntity<Response> createUser(@RequestBody @Validated BodyUserApps body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_USER,body,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping("{id}")
	ResponseEntity<Response> updateBranch(@PathVariable long id,@RequestBody @Validated BodyUserApps body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("BodyUserApps", body);
		Response response = securityService.response(ConstansPermission.EDIT_USER,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<Response> deleteRole(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.DELETE_USER,id,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}

}
