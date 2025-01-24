package com.servlet.cargo.api;

import com.servlet.cargo.entity.BodyCargo;
import com.servlet.cargo.entity.ParamCargoSearch;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/cargo")
@CrossOrigin(origins = "${value.cross_origin}")
public class CargoAPI {
    @Autowired
    SecurityService securityService;

    @PostMapping("/list")
    ResponseEntity<Response> getList(@RequestBody @Validated ParamCargoSearch body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        param.put("paramsearch", body);
        Response response = securityService.response(ConstansPermission.READ_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/template")
    ResponseEntity<Response> getTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("{id}")
    ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DETAIL");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyCargo body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "CREATE");
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.CREATE_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Response> updateObject(@PathVariable long id, @RequestBody @Validated BodyCargo body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.EDIT_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Response> deleteObject(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.DELETE_CARGO,id,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping("/file/{id}")
    ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "UPLOADFILE");
        param.put("body", file);
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.CREATE_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/downloadfile/{iddoc}")
    ResponseEntity<Response> getDownloadFile(@PathVariable long iddoc,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DOWNLOADFILE");
        param.put("id", iddoc);
        Response response = securityService.response(ConstansPermission.READ_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping("/deletefile/{iddoc}")
    ResponseEntity<Response> deleteFile(@PathVariable long iddoc, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DELETEFILE");
        param.put("id", iddoc);
        Response response = securityService.response(ConstansPermission.CREATE_CARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
