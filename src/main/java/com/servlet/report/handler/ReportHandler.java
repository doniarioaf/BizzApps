package com.servlet.report.handler;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
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
import com.servlet.mobile.monitorusermobileinfo.entity.DetailInfo;
import com.servlet.mobile.monitorusermobileinfo.entity.MonitorUserMobileInfo;
import com.servlet.mobile.monitorusermobileinfo.service.MonitorUserMobileInfoService;
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
	@Autowired
	private MonitorUserMobileInfoService monitorUserMobileInfoService;
	
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
	        createCell(row, 7, "Photo 4", style,sheet);
	        createCell(row, 8, "Photo 5", style,sheet);
	        createCell(row, 9, "Is Photo 1", style,sheet);
	        createCell(row, 10, "Is Photo 2", style,sheet);
	        createCell(row, 11, "Is Photo 3", style,sheet);
	        createCell(row, 12, "Is Photo 4", style,sheet);
	        createCell(row, 13, "Is Photo 5", style,sheet);
	        int rowHeader = 14;
	        
			List<MonitoringData> list = getListMonitoringData(user.getId(),body,idcompany,idbranch);
			
			//Set Header Info, Mencari list Info header paling Banyak di monitoring
			int countInfo = 0;
			HashMap<Long, List<Long>> hashMonitorUserMobileInfo = new HashMap<Long, List<Long>>();
			if(list != null && list.size() > 0) {
				List<Long> listInfoIdExist = new ArrayList<Long>();
				for(MonitoringData monitor : list) {
					List<Long> listInfo = monitorUserMobileInfoService.getListDistinctUserMobileInfo(monitor.getIdmonitoring());
					if(listInfo.size() > countInfo) {
						countInfo = listInfo.size(); 
					}
					hashMonitorUserMobileInfo.put(monitor.getIdmonitoring(), listInfo);
					
				}
				
				int seqInfo = 1;
				for(int idxInfo=0; idxInfo < countInfo; idxInfo++) {
					createCell(row, rowHeader, "Info "+seqInfo, style,sheet);
					rowHeader++;
					seqInfo++;
				}
			}
			//Set Header Info//
			
			font.setBold(false);
			if(list != null && list.size() > 0) {
				CellStyle styleMonitor = workbook.createCellStyle();
		        XSSFFont fontMonitor = workbook.createFont();
		        fontMonitor.setFontHeight(14);
		        styleMonitor.setFont(font);
		        rowcount = 2;
				for(MonitoringData monitor : list) {
					
					Row rowMonitor = sheet.createRow(rowcount++);
					int columnCount = 0;
					createCell(rowMonitor, columnCount++, monitor.getNamacustomer(), style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getTanggal().toString(), style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getCheckintime(), style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getCheckouttime(), style,sheet);
					
					
					CreationHelper helper = workbook.getCreationHelper();
					
					// Create the drawing patriarch. This is the top level container for all shapes.
					Drawing<?> drawing = sheet.createDrawingPatriarch();
					// add a picture shape
					ClientAnchor anchor = helper.createClientAnchor();
					
					Integer objIntPhoto1 = decodeToImageExcel(monitor.getPhoto1(),workbook);
					if(objIntPhoto1 != null) {
						int kolom = columnCount++;
						anchor.setCol1(kolom);
						anchor.setRow1(rowcount);
						Picture pict = drawing.createPicture(anchor, objIntPhoto1.intValue());
						
//						//get the picture width in px
//						  int pictWidthPx = pict.getImageDimension().width;
//						  //get the picture height in px
//						  int pictHeightPx = pict.getImageDimension().height;
//						  
//						//get column width of column in px
//						 float columnWidthPx = sheet.getColumnWidthInPixels(kolom);

						// auto-size picture relative to its top-left corner
						pict.resize();
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto2 = decodeToImageExcel(monitor.getPhoto2(),workbook);
					if(objIntPhoto2 != null) {
						anchor.setCol1(columnCount++);
						anchor.setRow1(rowcount);
						Picture pict = drawing.createPicture(anchor, objIntPhoto2.intValue());

						// auto-size picture relative to its top-left corner
						pict.resize();
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto3 = decodeToImageExcel(monitor.getPhoto3(),workbook);
					if(objIntPhoto3 != null) {
						anchor.setCol1(columnCount++);
						anchor.setRow1(rowcount);
						Picture pict = drawing.createPicture(anchor, objIntPhoto3.intValue());

						// auto-size picture relative to its top-left corner
						pict.resize();
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto4 = decodeToImageExcel(monitor.getPhoto4(),workbook);
					if(objIntPhoto4 != null) {
						anchor.setCol1(columnCount++);
						anchor.setRow1(rowcount);
						
						Picture pict = drawing.createPicture(anchor, objIntPhoto4.intValue());

						// auto-size picture relative to its top-left corner
						pict.resize();
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto5 = decodeToImageExcel(monitor.getPhoto5(),workbook);
					if(objIntPhoto5 != null) {
						anchor.setCol1(columnCount++);
						anchor.setRow1(rowcount);
						Picture pict = drawing.createPicture(anchor, objIntPhoto5.intValue());

						// auto-size picture relative to its top-left corner
						pict.resize();
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}

					createCell(rowMonitor, columnCount++, monitor.getPhoto1() != null && !monitor.getPhoto1().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto2() != null && !monitor.getPhoto2().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto3() != null && !monitor.getPhoto3().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto4() != null && !monitor.getPhoto4().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto5() != null && !monitor.getPhoto5().equals("")?"Y":"N", style,sheet);
					
					//set Value Info
					List<Long> listInfo = hashMonitorUserMobileInfo.get(monitor.getIdmonitoring());
					for(long infoid : listInfo) {
						String valInfo = "";
						List<DetailInfo> listdetailInfo = monitorUserMobileInfoService.getDetailInfo(infoid);
						if(listdetailInfo != null && listdetailInfo.size() > 0) {
							String question = "";
							String answer = "";
							for(DetailInfo detail : listdetailInfo) {
								if(question.equals("")) {
									question = detail.getQuestion();
								}
								if(detail.getAnswer().toUpperCase().equals("TEXT")) {
									answer = detail.getInfoAnswer();
								}else {
									answer += detail.getAnswer()+" \n ";
								}
							}
							valInfo = question+" \n "+answer;
						}
						createCell(rowMonitor, columnCount++, valInfo, style,sheet);
					}
					
					//set Value Info//
				}
			}
			
		}
		
		data.setWorkbook(workbook);
		return data;
	}
	
	private List<MonitoringData> getListMonitoringData(long idusermobile,BodyReportMonitoring body, long idcompany, long idbranch) {
		final StringBuilder sqlBuilder = new StringBuilder("select " + new getMonitoringData().schema());
		sqlBuilder.append(" where monitor.idusermobile = ? and monitor.idcompany = ? and monitor.idbranch = ? and monitor.tanggal >= '"+body.getFromdate()+"' and monitor.tanggal <= '"+body.getTodate()+"' ");
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
	
	private Integer decodeToImageExcel(String imageString,XSSFWorkbook workbook){
		if(imageString != null && !imageString.equals("")) {
			try {
				
			
			byte[] imagebyte = Base64.getDecoder().decode(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imagebyte);
			BufferedImage image = ImageIO.read(bis);
			bis.close();
			
			// write the image to a file
			File outputfile = new File("image.png");
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile))) {
	            outputStream.write(imagebyte);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			InputStream is = new FileInputStream(outputfile);
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			
			return pictureIdx;
			}catch (IOException e) {
				// TODO: handle exception
				return null;
			}
		}
		
		return null;
	}

}
