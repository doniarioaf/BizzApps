package com.servlet.admin.db.api;

import com.servlet.admin.db.DbData;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/db")
@CrossOrigin(origins = "${value.cross_origin}")
public class DBApi {
    @Autowired
    SecurityService securityService;

    @GetMapping("/backup-db")
    ResponseEntity<Resource>  downloadBackupDB(HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException {
        HashMap<String, Object> param = new HashMap<String, Object>();
        Response response1 = securityService.response(ConstansPermission.CREATE_FILE_BACKUP_DB,param,authorization);
        DbData data = (DbData) response1.getData();
        String fileName = data.getFileName();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(Files.size(data.getPath()))
                .body(data.getResource());
    }
}
