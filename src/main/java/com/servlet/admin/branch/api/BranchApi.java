package com.servlet.admin.branch.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.servlet.admin.branch.entity.BodyBranch;
import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.Response;
import com.servlet.user.entity.UserPermissionData;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;

@RestController
@RequestMapping("/v1/branch")
@CrossOrigin(origins = "${value.cross_origin}")
public class BranchApi {
	@Autowired
	SecurityService securityService;
	@Autowired
	BranchService branchservice;
	
	@GetMapping("{id}")
	ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		//List<UserPermissionData> listp = new ArrayList<UserPermissionData>(userappsservice.getListUserPermission(userapps.getId()));
		Response response = securityService.response(ConstansPermission.READ_BRANCH,new Long(id).toString(),authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/getlistbranchactive")
	ResponseEntity<Response> getListBranchActive(@RequestHeader(ConstansKey.AUTH) String authorization) {
//		Response response = securityService.response(ConstansPermission.READ_BRANCH,branchservice.getListBranchActive(),authorization);
		Response response = securityService.response(ConstansPermission.READ_BRANCH,"ALL",authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping
	ResponseEntity<Response> getListBranch(@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_BRANCH,branchservice.getAllListBranch(),authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping
	ResponseEntity<Response> createBranch(@RequestBody @Validated BodyBranch branch, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.CREATE_BRANCH,branch,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping("{id}")
	ResponseEntity<Response> updateBranch(@PathVariable long id,@RequestBody @Validated BodyBranch branch, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("BodyBranch", branch);
		Response response = securityService.response(ConstansPermission.EDIT_BRANCH,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/getlistbranchnotexistincompany")
	ResponseEntity<Response> getListBranchNotExistInCompany(@RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.READ_BRANCH,"getlistbranchnotexistincompany",authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<Response> deleteBranch(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.DELETE_BRANCH,id,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	

}
