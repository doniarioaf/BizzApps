package com.servlet.address.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/address")
@CrossOrigin(origins = "${value.cross_origin}")
public class AddressApi {
	@Autowired
	SecurityService securityService;
	
	@GetMapping("/districtall")
	ResponseEntity<Response> getListDistrict(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "ALL_DISTRICT");
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/subdistrictall")
	ResponseEntity<Response> getListSubDistrict(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "ALL_SUBDISTRICT");
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/districtbycity")
	ResponseEntity<Response> getListDistrictByCity(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam long cityid) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "DISTRICT_BY_CITY");
		param.put("cityid", cityid);
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/subdistrictbydistrict")
	ResponseEntity<Response> getListSubDistrictByDistrict(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam long districtid) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "SUBDISTRICT_BY_DISTRICT");
		param.put("districtid", districtid);
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/postalcodeall")
	ResponseEntity<Response> getListPostalCode(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "ALL_POSTAlCODE");
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/postalcodebypostalcode")
	ResponseEntity<Response> getListPostalCodeByPostalCode(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam long postalcode) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "POSTALCODE_BY_POSTALCODE");
		param.put("postalcode", postalcode);
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/postalcodebysubdistrict")
	ResponseEntity<Response> getListPostalCodeBySubDistrict(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam long subdistrictid) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "POSTALCODE_BY_SUBDSTRICT");
		param.put("subdistrictid", subdistrictid);
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/postalcodebycityandprovince")
	ResponseEntity<Response> getListPostalCodeByCityAndProvince(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam long cityid,@RequestParam long provid) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "POSTALCODE_BY_CITY_AND_PROVINCE");
		param.put("cityid", cityid);
		param.put("provid", provid);
		Response response = securityService.response(ConstansPermission.READ_ADDRESS,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
}
