package com.servlet.invoice.api;

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

import com.servlet.customermanggala.entity.BodySearch;
import com.servlet.invoice.entity.BodyInvoice;
import com.servlet.invoice.entity.BodySearchInvoicePriceList;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/invoice")
@CrossOrigin(origins = "${value.cross_origin}")
public class InvoiceApi {
	@Autowired
	SecurityService securityService;
	
	@GetMapping
	ResponseEntity<Response> getList(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "ALL");
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/template")
	ResponseEntity<Response> getListTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "TEMPLATE");
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("{id}")
	ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "DETAIL");
		param.put("id", id);
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/printinvoice/{id}")
	ResponseEntity<Response> printInvoice(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "PRINTINVOICE");
		param.put("id", id);
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/searchwo/{idcustomer}")
	ResponseEntity<Response> searchWorkOrderByCustomer(@PathVariable long idcustomer,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "SEACRHWO");
		param.put("idcustomer", idcustomer);
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/searchsj/{idwo}")
	ResponseEntity<Response> searchSuratjalanByWO(@PathVariable long idwo,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "SEACRH_SURATJALAN");
		param.put("idwo", idwo);
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/searchpricelist")
	ResponseEntity<Response> searchPriceList(@RequestBody @Validated BodySearchInvoicePriceList body,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "SEACRH_PRICELIST");
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/template/{id}")
	ResponseEntity<Response> getByIdWithTemplate(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "DETAIL_TEMPLATE");
		param.put("id", id);
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping("/searchcustomer")
	ResponseEntity<Response> searchData(@RequestBody @Validated BodySearch body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "SEARCHDATACUSTOMER");
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@PostMapping
	ResponseEntity<Response> createObject(@RequestBody @Validated BodyInvoice body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
//		param.put("type", "CREATE");
//		param.put("body", body);
		Response response = securityService.response(ConstansPermission.CREATE_INVOICE,body,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	
	@PutMapping("{id}")
	ResponseEntity<Response> updateObject(@PathVariable long id,@RequestBody @Validated BodyInvoice body, @RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.EDIT_INVOICE,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@DeleteMapping("{id}")
	ResponseEntity<Response> deleteObject(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
		Response response = securityService.response(ConstansPermission.DELETE_INVOICE,id,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
