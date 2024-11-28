package com.servlet.pricelist.api;

import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/pricelist")
@CrossOrigin(origins = "${value.cross_origin}")
public class PriceAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping
    ResponseEntity<Response> getList(@RequestParam("from") Long from,@RequestParam("to") Long to, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        param.put("from", from);
        param.put("to", to);
        Response response = securityService.response(ConstansPermission.READ_PRICELIST,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
