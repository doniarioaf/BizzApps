package com.servlet.version;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${value.cross_origin}")
public class versionApi {

//    @Value("${app.version}")
//    private String version;

    @GetMapping("/version")
    public Map<String, String> getVersion() {
        Map<String, String> response = new HashMap<>();
        String version = "25.08.2026";
        response.put("version", version);
        return response;
    }
}
