package com.servlet.cancelpackinglist.api;

import com.servlet.cancelpackinglist.entity.BodyCancelPackingList;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cancelpackinglist")
@CrossOrigin(origins = "${value.cross_origin}")
public class CancelPackingListAPI {
    @Autowired
    SecurityService securityService;

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyCancelPackingList body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.CREATE_CANCELPACKINGLIST,body,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
