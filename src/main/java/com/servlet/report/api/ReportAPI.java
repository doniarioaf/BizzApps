package com.servlet.report.api;

import com.servlet.report.entity.ParamReportRekapStock;
import com.servlet.report.entity.ParamReportStatusTagihanCargo;
import com.servlet.report.entity.ParamReportStockUdangHidupMati;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/report")
@CrossOrigin(origins = "${value.cross_origin}")
public class ReportAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping("/reportstockudanghidupmati")
    ResponseEntity<Response> getReportStockUdangHidupMati(@RequestParam("from") Long date, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportStockUdangHidupMati body = new ParamReportStockUdangHidupMati();
        body.setDate(date);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTUDANGHIDUPMATI");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_STOCKUDANGHIDUPMATI,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportrekapbarangmasuk")
    ResponseEntity<Response> getReportRekapanBarangMasuk(@RequestParam("from") Long date, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportRekapStock body = new ParamReportRekapStock();
        body.setDate(date);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTREKAPANBARANGMASUK");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_REKAPANBARANGMASUK,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reporstatustagihancargo")
    ResponseEntity<Response> getReportStatusTagihanCargo(@RequestParam("status") String status, @RequestParam("idvendors") String idvendors, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportStatusTagihanCargo body = new ParamReportStatusTagihanCargo();
        body.setFrom(from);
        body.setTo(to);
        body.setIdvendors(idvendors);
        body.setStatus(status);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTSTATUSTAGIHANCARGO");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_STATUSTAGIHANCARGO,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reporstatustagihancargo/template")
    ResponseEntity<Response> getReportStatusTagihanCargoTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTSTATUSTAGIHANCARGO_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_STATUSTAGIHANCARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private void export(HttpServletResponse response, XSSFWorkbook workbook) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
