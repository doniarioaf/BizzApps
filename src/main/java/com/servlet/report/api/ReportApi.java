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
import org.springframework.core.io.InputStreamResource;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.servlet.report.entity.BodyGetMaps;
import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.entity.ReportToPDF;
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
	
	@GetMapping("/monitoring/maps/template")
	ResponseEntity<Response> getListTemplateMaps(@RequestHeader(ConstansKey.AUTH) String authorization) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "TEMPLATE");
		Response response = securityService.response(ConstansPermission.READ_MAPS_MONITORING,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/monitoring/maps")
	ResponseEntity<Response> getListMaps(@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam String idusermobile,@RequestParam String tanggal) {
		BodyGetMaps body = new BodyGetMaps();
		body.setIdusermobile(new Long(idusermobile).longValue());
		body.setTanggal(tanggal);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "MAPS");
		param.put("body", body);
		Response response = securityService.response(ConstansPermission.READ_MAPS_MONITORING,param,authorization);
		return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	@GetMapping("/monitoring")
	ResponseEntity<Response> getReportMonitoringExcel(HttpServletResponse response,@RequestHeader(ConstansKey.AUTH) String authorization,@RequestParam String idusermobile,@RequestParam String from,@RequestParam String thru,@RequestParam String type) throws IOException {
		BodyReportMonitoring body = new BodyReportMonitoring();
		body.setIdusermobile(idusermobile);
		body.setFromdate(from);
		body.setTodate(thru);
		body.setTypereport(type);
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("type", "REPORT");
		param.put("body", body);
		
		Response response1 = securityService.response(ConstansPermission.READ_REPORT_MONITORING,param,authorization);
		if(response1.getHttpcode() == HttpStatus.OK.value()) {
			if(type.equals("XLSX")) {
				XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
				export(response, workbook);
				return ResponseEntity.ok().build();
			}else {
				//PDF
				ReportToPDF pdf = (ReportToPDF) response1.getData();
				exportToPdf(response,pdf.getDocument(),pdf.getTable());
				return ResponseEntity.ok().build();
			}
			
		}else {
			return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
		}
		
	}
	
	public void export(HttpServletResponse response,XSSFWorkbook workbook) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
	
	public void exportToPdf(HttpServletResponse response,Document document,PdfPTable table) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        try {
			PdfWriter.getInstance(document, outputStream);
			document.open();
            document.add(table);

            document.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        workbook.write(outputStream);
//        workbook.close();
         
        outputStream.close();
         
    }
	
}
