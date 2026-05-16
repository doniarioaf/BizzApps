package com.servlet.journal.api;

import com.servlet.journal.entity.BodyMigrasi;
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
@RequestMapping("/v1/journal")
@CrossOrigin(origins = "${value.cross_origin}")
public class JournalApi {

    @Autowired
    SecurityService securityService;


    @PostMapping("/integrasi")
    ResponseEntity<Response> prosesIntegrasi(@RequestBody @Validated BodyMigrasi body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.CREATE_INTEGRASI,body,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
