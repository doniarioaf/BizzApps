package com.servlet.penerimaankasbank.api;

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
import com.servlet.penerimaankasbank.entity.BodyPenerimaanKasBank;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import com.servlet.workorder.entity.BodySearch;

@RestController
@RequestMapping("/v1/penerimaankasbank")
@CrossOrigin(origins = "${value.cross_origin}")
public class PenerimaanKasBankApi {
	@Autowired
	SecurityService securityService;
	
	@GetMapping
	ResponseEntity<Response> getList(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "ALL");
		Response response = securityService.response(ConstansPermission.READ_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("{id}")
	ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "DETAIL");
		param.put("id", id);
		Response response = securityService.response(ConstansPermission.READ_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/template/{id}")
	ResponseEntity<Response> getByIdWithTemplate(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "TEMPLATE_ID");
		param.put("id", id);
		Response response = securityService.response(ConstansPermission.READ_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/template")
	ResponseEntity<Response> getTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "TEMPLATE");
		Response response = securityService.response(ConstansPermission.READ_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/searchwo")
	ResponseEntity<Response> searchWO(@RequestBody @Validated BodySearch body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "SEARCHWO");
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.CREATE_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/searchinvoice")
	ResponseEntity<Response> searchInvoice(@RequestBody @Validated com.servlet.invoice.entity.BodySearch body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "SEARCHINVOICE");
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.CREATE_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping
	ResponseEntity<Response> createObject(@RequestBody @Validated BodyPenerimaanKasBank body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "CREATE");
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.CREATE_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PutMapping("{id}")
	ResponseEntity<Response> updateObject(@PathVariable long id,@RequestBody @Validated BodyPenerimaanKasBank body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.EDIT_PENERIMAAN_KASBANK,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<Response> deleteObject(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.DELETE_PENERIMAAN_KASBANK,id,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
}