package com.servlet.report.api;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.service.ReportService;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;

@RestController
@RequestMapping("/v1/report")
@CrossOrigin(origins = "${value.cross_origin}")
public class ReportApi {
	
	@Autowired
	SecurityService securityService;
	@Autowired
	ReportService reportService;
	
	@GetMapping("/monitoring/template")
	ResponseEntity<Response> getListTemplateMonitoring(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "TEMPLATE");
		Response response = securityService.response(ConstansPermission.READ_REPORT_MONITORING,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/monitoring")
	ResponseEntity<Response> getReportMonitoringExcel(HttpServletResponse response,@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam String idusermobile,@RequestParam String from,@RequestParam String thru) throws IOException {
		BodyReportMonitoring body = new BodyReportMonitoring();
		body.setIdusermobile(idusermobile);
		body.setFromdate(from);
		body.setTodate(thru);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "REPORT");
		param.put("body", body);
		
		Response response1 = securityService.response(ConstansPermission.READ_REPORT_MONITORING,param,authorization);
		if(response1.getHttpcode() == HttpStatus.OK.value()) {
			XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
			export(response, workbook);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
		}
		
	}
	
	public void export(HttpServletResponse response,XSSFWorkbook workbook) throws IOException {
//        writeHeaderLine();
//        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
