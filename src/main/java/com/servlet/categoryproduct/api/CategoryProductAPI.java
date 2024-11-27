package com.servlet.categoryproduct.api;

import com.servlet.categoryproduct.entity.BodyCategoryProduct;
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
@RequestMapping("/v1/categoryproduct")
@CrossOrigin(origins = "${value.cross_origin}")
public class CategoryProductAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping
    ResponseEntity<Response> getList(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        Response response = securityService.response(ConstansPermission.READ_CATEGORYPRODUCT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("{id}")
    ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DETAIL");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_CATEGORYPRODUCT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyCategoryProduct body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.CREATE_CATEGORYPRODUCT,body,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Response> updateObject(@PathVariable long id,@RequestBody @Validated BodyCategoryProduct body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.EDIT_CATEGORYPRODUCT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Response> deleteObject(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.DELETE_CATEGORYPRODUCT,id,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
