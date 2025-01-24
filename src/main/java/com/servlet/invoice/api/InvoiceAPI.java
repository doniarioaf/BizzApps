package com.servlet.invoice.api;

import com.servlet.invoice.entity.BodyInvoice;
import com.servlet.invoice.entity.ParamSearchInvoice;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/invoice")
@CrossOrigin(origins = "${value.cross_origin}")
public class InvoiceAPI {

    @Autowired
    SecurityService securityService;

    @PostMapping("/list")
    ResponseEntity<Response> getList(@RequestBody @Validated ParamSearchInvoice body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        param.put("paramsearch", body);
        Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/template")
    ResponseEntity<Response> getTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("{id}")
    ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DETAIL");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/print/{id}")
    ResponseEntity<Response> getPrintById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "PRINT");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/getpackinglist/{idpackinglist}")
    ResponseEntity<Response> getPackingListById(@PathVariable long idpackinglist,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "GET_PACKINGLIST");
        param.put("id", idpackinglist);
        Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/catatdownload/{id}")
    ResponseEntity<Response> catatDownloadPrint(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DOWNLOAD_PRINTPDF");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyInvoice body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.CREATE_INVOICE,body,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Response> updateObject(@PathVariable long id, @RequestBody @Validated BodyInvoice body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.EDIT_INVOICE,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Response> deleteObject(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.DELETE_INVOICE,id,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/printexcel/{idinvoice}")
    ResponseEntity<Response> getPrintExcel(@PathVariable long idinvoice,HttpServletResponse response,@RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "PRINTEXCEL");
        param.put("id", idinvoice);
        Response response1 = securityService.response(ConstansPermission.READ_INVOICE,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    private void export(HttpServletResponse response, XSSFWorkbook workbook) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
