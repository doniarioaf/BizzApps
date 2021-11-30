package com.servlet.report.handler;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.servlet.BizzAppsBackEndApplication;
import com.servlet.admin.usermobile.entity.UserMobileListData;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.entity.MonitoringData;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.mapper.getMonitoringData;
import com.servlet.report.service.ReportService;

@Service
public class ReportHandler implements ReportService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportHandler.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserMobileService userMobileService;
	
	@Override
	public ReportWorkBookExcel getReportMonitoringData(BodyReportMonitoring body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		List<UserMobileListData> listuser = userMobileService.getListAllUserMobileForMonitoring(body.getIdusermobile(), idcompany, idbranch);
		LOGGER.info("listuser "+listuser.size());
		for(UserMobileListData user : listuser) {
			XSSFSheet sheet = workbook.createSheet(user.getNama());
			int rowcount = 0;
			Row rowNamaUser = sheet.createRow(rowcount);
			
			CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        
	        createCell(rowNamaUser, 0, "Nama ", style,sheet);
	        createCell(rowNamaUser, 1, user.getNama(), style,sheet);  
	        
	        rowcount = 1;
	        Row row = sheet.createRow(rowcount);
	        
	        createCell(row, 0, "Customer", style,sheet);      
	        createCell(row, 1, "Tanggal", style,sheet);       
	        createCell(row, 2, "Check In Time", style,sheet);    
	        createCell(row, 3, "Check Out Time", style,sheet);
	        createCell(row, 4, "Photo 1", style,sheet);
	        createCell(row, 5, "Photo 2", style,sheet);
	        createCell(row, 6, "Photo 3", style,sheet);
	        createCell(row, 6, "Photo 4", style,sheet);
	        createCell(row, 7, "Photo 5", style,sheet);
	        createCell(row, 8, "Is Photo 1", style,sheet);
	        createCell(row, 9, "Is Photo 2", style,sheet);
	        createCell(row, 10, "Is Photo 3", style,sheet);
	        createCell(row, 11, "Is Photo 4", style,sheet);
	        createCell(row, 12, "Is Photo 5", style,sheet);
	        
	        
			List<MonitoringData> list = getListMonitoringData(user.getId(),body,idcompany,idbranch);
			LOGGER.info("listMonitoringData "+list.size());
			if(list != null && list.size() > 0) {
				CellStyle styleMonitor = workbook.createCellStyle();
		        XSSFFont fontMonitor = workbook.createFont();
		        fontMonitor.setFontHeight(14);
		        styleMonitor.setFont(font);
		        
				for(MonitoringData monitor : list) {
					Row rowMonitor = sheet.createRow(rowcount++);
					int columnCount = 0;
					createCell(row, columnCount++, monitor.getNamacustomer(), style,sheet);
					createCell(row, columnCount++, monitor.getTanggal().toString(), style,sheet);
					createCell(row, columnCount++, monitor.getCheckintime(), style,sheet);
					createCell(row, columnCount++, monitor.getCheckouttime(), style,sheet);
					createCell(row, columnCount++, monitor.getPhoto1(), style,sheet);
					createCell(row, columnCount++, monitor.getPhoto2(), style,sheet);
					createCell(row, columnCount++, monitor.getPhoto3(), style,sheet);
					createCell(row, columnCount++, monitor.getPhoto4(), style,sheet);
					createCell(row, columnCount++, monitor.getPhoto5(), style,sheet);

					createCell(row, columnCount++, monitor.getPhoto1() != null && !monitor.getPhoto1().equals("")?"Y":"N", style,sheet);
					createCell(row, columnCount++, monitor.getPhoto2() != null && !monitor.getPhoto2().equals("")?"Y":"N", style,sheet);
					createCell(row, columnCount++, monitor.getPhoto3() != null && !monitor.getPhoto3().equals("")?"Y":"N", style,sheet);
					createCell(row, columnCount++, monitor.getPhoto4() != null && !monitor.getPhoto4().equals("")?"Y":"N", style,sheet);
					createCell(row, columnCount++, monitor.getPhoto5() != null && !monitor.getPhoto5().equals("")?"Y":"N", style,sheet);

				}
			}
			
		}
		
		data.setWorkbook(workbook);
		return data;
	}
	
	private List<MonitoringData> getListMonitoringData(long idusermobile,BodyReportMonitoring body, long idcompany, long idbranch) {
		final StringBuilder sqlBuilder = new StringBuilder("select " + new getMonitoringData().schema());
		sqlBuilder.append(" where monitor.idusermobile = ? and monitor.idcompany = ? and monitor.idbranch = ? ");// and monitor.tanggal >= '"+body.getFromdate()+"' and monitor.tanggal <= '"+body.getTodate()+"' ");
		sqlBuilder.append(" order by monitor.idusermobile");
		final Object[] queryParameters = new Object[] { idusermobile,idcompany,idbranch };
		return this.jdbcTemplate.query(sqlBuilder.toString(), new getMonitoringData(), queryParameters);
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

}
