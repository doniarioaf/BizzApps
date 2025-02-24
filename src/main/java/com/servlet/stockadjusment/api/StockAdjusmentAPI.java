package com.servlet.stockadjusment.api;

import com.servlet.purchasereceive.entity.BodyPurchaseReceive;
import com.servlet.report.entity.ParamReportPembelian;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import com.servlet.stockadjusment.entity.BodyStockAdjusment;
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
@RequestMapping("/v1/stockadjusment")
@CrossOrigin(origins = "${value.cross_origin}")
public class StockAdjusmentAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping
    ResponseEntity<Response> getList(@RequestParam("from") Long from, @RequestParam("to") Long to, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "ALL");
        param.put("from", from);
        param.put("to", to);
        Response response = securityService.response(ConstansPermission.READ_STOCKADJUSMENT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("{id}")
    ResponseEntity<Response> getById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "DETAIL");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_STOCKADJUSMENT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/print/{id}")
    ResponseEntity<Response> getPrintById(@PathVariable long id,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "PRINT");
        param.put("id", id);
        Response response = securityService.response(ConstansPermission.READ_STOCKADJUSMENT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/printexcel/{id}")
    ResponseEntity<Response> getReportPembelian(HttpServletResponse response,@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORT_STOCK");
        param.put("id", id);
        Response response1 = securityService.response(ConstansPermission.READ_STOCKADJUSMENT,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }
    }

//    @GetMapping("/pricelist")
//    ResponseEntity<Response> getPriceList(@RequestParam("pricedate") Long pricedate, @RequestHeader(ConstansKey.AUTH) String authorization) {
//        HashMap<String, Object> param = new HashMap<String, Object>();
//        param.put("type", "PRICELIST");
//        param.put("pricedate", pricedate);
//        Response response = securityService.response(ConstansPermission.READ_STOCKADJUSMENT,param,authorization);
//        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
//    }

    @GetMapping("/template")
    ResponseEntity<Response> getTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_STOCKADJUSMENT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/getitems/{idproduct}/{idcategoryproduct}")
    ResponseEntity<Response> getItemsLastDocumentPR(@PathVariable long idproduct,@PathVariable long idcategoryproduct,@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "GETITEMS");
        param.put("idproduct", idproduct);
        param.put("idcategoryproduct", idcategoryproduct);
        Response response = securityService.response(ConstansPermission.READ_STOCKADJUSMENT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PostMapping
    ResponseEntity<Response> createObject(@RequestBody @Validated BodyStockAdjusment body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.CREATE_STOCKADJUSMENT,body,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @PutMapping("{id}")
    ResponseEntity<Response> updateObject(@PathVariable long id, @RequestBody @Validated BodyStockAdjusment body, @RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("body", body);
        Response response = securityService.response(ConstansPermission.EDIT_STOCKADJUSMENT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Response> deleteObject(@PathVariable long id, @RequestHeader(ConstansKey.AUTH) String authorization) {
        Response response = securityService.response(ConstansPermission.DELETE_STOCKADJUSMENT,id,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private void export(HttpServletResponse response, XSSFWorkbook workbook) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
