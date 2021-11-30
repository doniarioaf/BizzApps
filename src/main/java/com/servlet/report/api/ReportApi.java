package com.servlet.report.api;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	
	@GetMapping("/monitoring")
	ResponseEntity exportToExcel(HttpServletResponse response) throws IOException {

	     DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	     String currentDateTime = dateFormatter.format(new Date());
//	     
	     String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
        BodyReportMonitoring body = new BodyReportMonitoring();
        body.setIdusermobile(0);
        body.setFromdate("2021-11-09");
        body.setTodate("2021-11-09");
		XSSFWorkbook workbook = reportService.getReportMonitoringData(body, 1, 1).getWorkbook();
		
		//writeHeaderLine
//		XSSFSheet sheet = workbook.createSheet("Users");
//		Row row = sheet.createRow(0);
//		
//		CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight(16);
//        style.setFont(font);
//         
//        createCell(row, 0, "User ID", style,sheet);      
//        createCell(row, 1, "E-mail", style,sheet);       
//        createCell(row, 2, "Full Name", style,sheet);    
//        createCell(row, 3, "Roles", style,sheet);
//        createCell(row, 4, "Enabled", style,sheet);
//        
//		
//        writeDataLines(sheet,workbook);
        export(response, workbook);
        return ResponseEntity.ok().build();
	}
	
	private ByteArrayResource export(String filename,XSSFWorkbook workbook1) throws IOException {
	      byte[] bytes = new byte[1024];
	      try (XSSFWorkbook workbook = workbook1) {
	          FileOutputStream fos = write(workbook, filename);
	          fos.write(bytes);
	          fos.flush();
//	          fos.close();
	      }
	      

	      return new ByteArrayResource(bytes);
	  }
	
	private FileOutputStream write(final XSSFWorkbook workbook, final String filename) throws IOException {
	      FileOutputStream fos = new FileOutputStream(filename);
	      workbook.write(fos);
//	      fos.close();
	      return fos;
	  }  
	
	private void writeDataLines(XSSFSheet sheet,XSSFWorkbook workbook) {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (int i=0; i<100; i++) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, "Id - "+i, style,sheet);
            createCell(row, columnCount++, "email - "+i, style,sheet);
            createCell(row, columnCount++, "FullName - "+i, style,sheet);
            createCell(row, columnCount++, "Roles - "+i, style,sheet);
            createCell(row, columnCount++, "Enabled - "+i, style,sheet);
             
        }
    }
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style,XSSFSheet sheet) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
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
