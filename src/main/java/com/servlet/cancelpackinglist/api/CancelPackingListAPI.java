package com.servlet.cancelpackinglist.api;

import com.servlet.cancelpackinglist.entity.BodyCancelPackingList;
import com.servlet.cancelpackinglist.entity.ParamSearchCancelPackingList;
import com.servlet.packinglist.entity.ParamSearchPackingList;
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
@RequestMapping("/v1/cancelpackinglist")
@CrossOrigin(origins = "${value.cross_origin}")
public class CancelPackingListAPI {
    @Autowired
    SecurityService securityService;

    @PostMapping("/list")
    ResponseEntity<Response> getList(@RequestBody @Validated ParamSearchCancelPackingList body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        param.put("paramsearch", body);
        Response response = securityService.response(ConstansPermission.READ_CANCELPACKINGLIST,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyCancelPackingList body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.CREATE_CANCELPACKINGLIST,body,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
