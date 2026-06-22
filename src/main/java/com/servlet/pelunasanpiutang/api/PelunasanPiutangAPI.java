package com.servlet.pelunasanpiutang.api;

import com.servlet.pelunasanpiutang.entity.BodyPelunasanPiutang;
import com.servlet.pelunasanpiutang.entity.FilterParamPelunasanPiutang;
import com.servlet.purchasereceive.entity.BodyPurchaseReceive;
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
@RequestMapping("/v1/pelunasanpiutang")
@CrossOrigin(origins = "${value.cross_origin}")
public class PelunasanPiutangAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping("{id}")
    ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DETAIL");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/template")
    ResponseEntity<Response> getTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
    @PostMapping("/pelunasanpiutanglist")
    ResponseEntity<Response> getPelunasanPiutangList(@RequestBody @Validated FilterParamPelunasanPiutang body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "PELUNASANPIUTANG_LIST");
        param.put("paramsearch", body);
        Response response = securityService.response(ConstansPermission.READ_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping("/piutanglist")
    ResponseEntity<Response> getPiutangList(@RequestBody @Validated FilterParamPelunasanPiutang body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "PIUTANG_LIST");
        param.put("paramsearch", body);
        Response response = securityService.response(ConstansPermission.READ_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/bayar/listinvoice/{listid}")
    ResponseEntity<Response> getHutangPRId(@PathVariable String listid,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "BAYAR_LIST_INVOICE");
        param.put("id", listid);

        Response response = securityService.response(ConstansPermission.READ_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyPelunasanPiutang body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "CREATE");
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.CREATE_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Response> updateObject(@PathVariable long id, @RequestBody @Validated BodyPelunasanPiutang body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.EDIT_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Response> deleteObject(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.DELETE_PELUNASANPIUTANG,id,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping("/file/{id}")
    ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "UPLOADFILE");
        param.put("body", file);
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.CREATE_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/downloadfile/{idph}")
    ResponseEntity<Response> getDownloadFile(@PathVariable long idph,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DOWNLOADFILE");
        param.put("id", idph);
        Response response = securityService.response(ConstansPermission.READ_PELUNASANPIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
