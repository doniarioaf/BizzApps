package com.servlet.bank.api;

import com.servlet.bank.entity.BodyBank;
import com.servlet.cargo.entity.BodyCargo;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/bank")
@CrossOrigin(origins = "${value.cross_origin}")
public class BankAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping
    ResponseEntity<Response> getList(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        Response response = securityService.response(ConstansPermission.READ_BANK,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyBank body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "CREATE");
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.CREATE_BANK,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
