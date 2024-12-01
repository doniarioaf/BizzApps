package com.servlet.purchasereceive.api;

import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/purchasereceive")
@CrossOrigin(origins = "${value.cross_origin}")
public class PurchaseReceiveAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping
    ResponseEntity<Response> getList(@RequestParam("from") Long from, @RequestParam("to") Long to, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        param.put("from", from);
        param.put("to", to);
        Response response = securityService.response(ConstansPermission.READ_PURCHASERECEIVE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/template")
    ResponseEntity<Response> getTemplate(@RequestParam("pricedate") Long pricedate,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "TEMPLATE");
        param.put("pricedate", pricedate);
        Response response = securityService.response(ConstansPermission.READ_PURCHASERECEIVE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/searchpricelist")
    ResponseEntity<Response> getPriceList(@RequestParam("pricedate") Long pricedate,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "SEARCHPRICELIST");
        param.put("pricedate", pricedate);
        Response response = securityService.response(ConstansPermission.READ_PURCHASERECEIVE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
