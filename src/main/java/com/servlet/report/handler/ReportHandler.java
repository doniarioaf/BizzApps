package com.servlet.report.handler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.SlideLayout;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFSlideLayout;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.servlet.admin.usermobile.entity.UserMobileListData;
import com.servlet.admin.usermobile.service.UserMobileService;
import com.servlet.mobile.callplan.service.CallPlanService;
import com.servlet.mobile.infoheader.entity.InfoHeaderData;
import com.servlet.mobile.infoheader.service.InfoHeaderService;
import com.servlet.mobile.monitorusermobile.entity.DataMonitorForMaps;
import com.servlet.mobile.monitorusermobile.service.MonitorUserMobileService;
import com.servlet.mobile.monitorusermobileinfo.entity.DetailInfo;
import com.servlet.mobile.monitorusermobileinfo.service.MonitorUserMobileInfoService;
import com.servlet.mobile.project.service.ProjectService;
import com.servlet.report.entity.BodyGetMaps;
import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.entity.MonitoringData;
import com.servlet.report.entity.ReportToPDF;
import com.servlet.report.entity.ReportToPPT;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.entity.TemplateMaps;
import com.servlet.report.entity.TemplateReport;
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
	@Autowired
	private MonitorUserMobileService monitorUserMobileService;
	@Autowired
	private InfoHeaderService infoHeaderService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private CallPlanService callPlanService;
	
	@Override
	public ReportWorkBookExcel getReportMonitoringData(BodyReportMonitoring body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		HashMap<Long, InfoHeaderData> mapListInfoHeader = new HashMap<Long, InfoHeaderData>();
        List<InfoHeaderData> listInfoHeader = infoHeaderService.getAllListDataForReport(idcompany, idbranch);
        for(InfoHeaderData info : listInfoHeader) {
        	mapListInfoHeader.put(info.getId(), info);
        }
		
		List<UserMobileListData> listuser = userMobileService.getListAllUserMobileForMonitoring(body.getIdusermobile(), idcompany, idbranch);
		for(UserMobileListData user : listuser) {
			XSSFSheet sheet = workbook.createSheet(user.getNama());
			sheet.setDefaultColumnWidth(1000);
			Drawing<?> drawing = sheet.createDrawingPatriarch();
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
	        createCell(row, 1, "Project", style,sheet);
	        createCell(row, 2, "Tanggal", style,sheet);       
	        createCell(row, 3, "Check In Time", style,sheet);    
	        createCell(row, 4, "Check Out Time", style,sheet);
	        createCell(row, 5, "Photo 1", style,sheet);
	        createCell(row, 6, "Photo 2", style,sheet);
	        createCell(row, 7, "Photo 3", style,sheet);
	        createCell(row, 8, "Photo 4", style,sheet);
	        createCell(row, 9, "Photo 5", style,sheet);
	        createCell(row, 10, "Is Photo 1", style,sheet);
	        createCell(row, 11, "Is Photo 2", style,sheet);
	        createCell(row, 12, "Is Photo 3", style,sheet);
	        createCell(row, 13, "Is Photo 4", style,sheet);
	        createCell(row, 14, "Is Photo 5", style,sheet);
	        int rowHeader = 15;
	        
			List<MonitoringData> list = getListMonitoringData(user.getId(),body,idcompany,idbranch);
			List<Integer> listQuestionExistInMonitoring = new ArrayList<>();
			//Set Header Info, Mencari list Info header paling Banyak di monitoring
			int countInfo = 0;
			HashMap<Long, List<Long>> hashMonitorUserMobileInfo = new HashMap<Long, List<Long>>();
			//List<DetailInfo> listdetailInfo = monitorUserMobileInfoService.getDetailInfo(infoid,monitor.getIdmonitoring());
			if(list != null && list.size() > 0) {
				for(MonitoringData monitor : list) {
					List<Long> listInfo = monitorUserMobileInfoService.getListDistinctUserMobileInfo(monitor.getIdmonitoring());
					if(listInfo.size() > countInfo) {
						countInfo = listInfo.size(); 
					}
					for(Long infoid : listInfo) {
						if(listQuestionExistInMonitoring.indexOf(infoid.intValue()) == -1) {
							listQuestionExistInMonitoring.add(infoid.intValue());
						}
					}
					hashMonitorUserMobileInfo.put(monitor.getIdmonitoring(), listInfo);
					
				}
				
				int seqInfo = 1;
				for(Integer infoidMonitoring : listQuestionExistInMonitoring) {
	            	String question = "";
	            	if(mapListInfoHeader.get(infoidMonitoring.longValue()) != null) {
	            		InfoHeaderData infoHeaderData = mapListInfoHeader.get(infoidMonitoring.longValue());
	            		question = infoHeaderData.getQuestion();
	            	}
	            	createCell(row, rowHeader, question, style,sheet);
	            	rowHeader++;
	            	seqInfo++;
	            }
//				for(int idxInfo=0; idxInfo < countInfo; idxInfo++) {
//					createCell(row, rowHeader, "Info "+seqInfo, style,sheet);
//					rowHeader++;
//					seqInfo++;
//				}
			}
			//Set Header Info//
			
			font.setBold(false);
			if(list != null && list.size() > 0) {
				CellStyle styleMonitor = workbook.createCellStyle();
		        XSSFFont fontMonitor = workbook.createFont();
		        fontMonitor.setFontHeight(14);
		        styleMonitor.setFont(font);
		        rowcount = 2;
		        int rowphoto = 2;
				for(MonitoringData monitor : list) {
					rowphoto = rowcount;
					short s = 1000;
					Row rowMonitor = sheet.createRow(rowcount++);
					rowMonitor.setHeight(s);
					int columnCount = 0;
					createCell(rowMonitor, columnCount++, monitor.getNamacustomer()+"( "+monitor.getCustomercode()+" )", style,sheet);
					createCell(rowMonitor, columnCount++, callPlanService.getProjectNameByIdCallPlan(monitor.getIdcallplan(), idcompany, idbranch), style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getTanggal().toString(), style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getCheckintime(), style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getCheckouttime(), style,sheet);
					
					
					CreationHelper helper = workbook.getCreationHelper();
					
					// Create the drawing patriarch. This is the top level container for all shapes.
					
					// add a picture shape
					ClientAnchor anchor = helper.createClientAnchor();
					anchor.setAnchorType( ClientAnchor.AnchorType.MOVE_AND_RESIZE );
					
					Integer objIntPhoto1 = decodeToImageExcel(monitor.getPhoto1(),workbook);
					int widhtPhoto = 50;
					int heightPhoto = 50;
					if(objIntPhoto1 != null) {
						int kolom = columnCount++;
						Cell celPhoto = rowMonitor.createCell(kolom);
						celPhoto.setCellValue("");
						int columnIndex = celPhoto.getColumnIndex();
		                sheet.autoSizeColumn(columnIndex);
//		                workbook.getSheetAt(workbook.getSheetIndex(sheet)).autoSizeColumn(columnIndex);
						try {
							drawImageOnExcelSheet((XSSFSheet)sheet, rowphoto, kolom, widhtPhoto, heightPhoto, objIntPhoto1.intValue());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto2 = decodeToImageExcel(monitor.getPhoto2(),workbook);
					if(objIntPhoto2 != null) {
						int kolom = columnCount++;
						Cell celPhoto = rowMonitor.createCell(kolom);
						celPhoto.setCellValue("");
						int columnIndex = celPhoto.getColumnIndex();
		                sheet.autoSizeColumn(columnIndex);
						try {
							drawImageOnExcelSheet((XSSFSheet)sheet, rowphoto, kolom, widhtPhoto, heightPhoto, objIntPhoto2.intValue());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto3 = decodeToImageExcel(monitor.getPhoto3(),workbook);
					if(objIntPhoto3 != null) {
						int kolom = columnCount++;
						Cell celPhoto = rowMonitor.createCell(kolom);
						celPhoto.setCellValue("");
						int columnIndex = celPhoto.getColumnIndex();
		                sheet.autoSizeColumn(columnIndex);
						try {
							drawImageOnExcelSheet((XSSFSheet)sheet, rowphoto, kolom, widhtPhoto, heightPhoto, objIntPhoto3.intValue());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto4 = decodeToImageExcel(monitor.getPhoto4(),workbook);
					if(objIntPhoto4 != null) {
						int kolom = columnCount++;
						Cell celPhoto = rowMonitor.createCell(kolom);
						celPhoto.setCellValue("");
						int columnIndex = celPhoto.getColumnIndex();
		                sheet.autoSizeColumn(columnIndex);
						try {
							drawImageOnExcelSheet((XSSFSheet)sheet, rowphoto, kolom, widhtPhoto, heightPhoto, objIntPhoto4.intValue());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}
					
					Integer objIntPhoto5 = decodeToImageExcel(monitor.getPhoto5(),workbook);
					if(objIntPhoto5 != null) {
						int kolom = columnCount++;
						Cell celPhoto = rowMonitor.createCell(kolom);
						celPhoto.setCellValue("");
						int columnIndex = celPhoto.getColumnIndex();
		                sheet.autoSizeColumn(columnIndex);
						try {
							drawImageOnExcelSheet((XSSFSheet)sheet, rowphoto, kolom, widhtPhoto, heightPhoto, objIntPhoto5.intValue());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else {
						createCell(rowMonitor, columnCount++, "", style,sheet);
					}

					createCell(rowMonitor, columnCount++, monitor.getPhoto1() != null && !monitor.getPhoto1().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto2() != null && !monitor.getPhoto2().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto3() != null && !monitor.getPhoto3().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto4() != null && !monitor.getPhoto4().equals("")?"Y":"N", style,sheet);
					createCell(rowMonitor, columnCount++, monitor.getPhoto5() != null && !monitor.getPhoto5().equals("")?"Y":"N", style,sheet);
					
					//set Value Info
//					List<Long> listInfo = hashMonitorUserMobileInfo.get(monitor.getIdmonitoring());
					for(Integer infoidMonitoring : listQuestionExistInMonitoring) {
						String valInfo = "";
						List<DetailInfo> listdetailInfo = monitorUserMobileInfoService.getDetailInfo(infoidMonitoring.longValue(),monitor.getIdmonitoring());
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
//							valInfo = question+" \n "+answer;
							valInfo = answer;
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
	
	private static void drawImageOnExcelSheet(XSSFSheet sheet, int row, int col, 
			  int height, int width, int pictureIdx) throws Exception {

			  CreationHelper helper = sheet.getWorkbook().getCreationHelper();

			  Drawing drawing = sheet.createDrawingPatriarch();

			  ClientAnchor anchor = helper.createClientAnchor();
			  anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);

			  anchor.setCol1(col); //first anchor determines upper left position
			  anchor.setRow1(row);

			  anchor.setRow2(row); //second anchor determines bottom right position
			  anchor.setCol2(col);
			  anchor.setDx2(Units.toEMU(width)); //dx = left + wanted width
			  anchor.setDy2(Units.toEMU(height)); //dy= top + wanted height

			  drawing.createPicture(anchor, pictureIdx);

			 }
	
	private List<MonitoringData> getListMonitoringData(long idusermobile,BodyReportMonitoring body, long idcompany, long idbranch) {
		final StringBuilder sqlBuilder = new StringBuilder("select " + new getMonitoringData().schema());
		sqlBuilder.append(" where monitor.idusermobile = ? and monitor.idcompany = ? and monitor.idbranch = ? and monitor.tanggal >= '"+body.getFromdate()+"' and monitor.tanggal <= '"+body.getTodate()+"' ");
		if(body.getIdproject() > 0) {
			String selectIdCallPlan = "select id from m_call_plan where idproject = "+body.getIdproject()+" and idcompany = "+idcompany+" and idbranch = "+idbranch+" ";
//			String selectIdCustCallPlan = "select idcustomer from m_customer_call_plan where idcallplan in ("+selectIdCallPlan+") ";
			sqlBuilder.append(" and monitor.idcustomer in (select idcustomer from m_customer_project as mcp where mcp.idproject="+body.getIdproject()+" and mcp.idcompany="+idcompany+" and mcp.idbranch="+idbranch+" ) ");
			sqlBuilder.append(" and monitor.idcallplan in ("+selectIdCallPlan+") ");
		}
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
			byte[] imagebyte = Base64.getDecoder().decode(imageString.getBytes(StandardCharsets.UTF_8));
			
			ByteArrayInputStream bis = new ByteArrayInputStream(imagebyte);
			BufferedImage image = ImageIO.read(bis);
			
			int scaledWidth = 200;
            int scaledHeight = 200;
            
			// creates output image
	        BufferedImage outputImage = new BufferedImage(scaledWidth,
	        		scaledHeight, image.getType());
	        
	     // scales the input image to the output image
	        
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
	        g2d.dispose();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        
	     // writes to output file
	        ImageIO.write(outputImage, "png", baos);
	        byte[] imagebytev2 = baos.toByteArray();
	        
			bis.close();
			
			// write the image to a file
			File outputfile = new File("image.png");
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile))) {
//	            outputStream.write(imagebyte);
				outputStream.write(imagebytev2);
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
	
	private XSLFPictureData decodeToImagePPT(String imageString,XMLSlideShow ppt){
		if(imageString != null && !imageString.equals("")) {
			try {
			byte[] imagebyte = Base64.getDecoder().decode(imageString.getBytes(StandardCharsets.UTF_8));
			
			ByteArrayInputStream bis = new ByteArrayInputStream(imagebyte);
			BufferedImage image = ImageIO.read(bis);
			
			int scaledWidth = 200;
            int scaledHeight = 200;
            
			// creates output image
	        BufferedImage outputImage = new BufferedImage(scaledWidth,
	        		scaledHeight, image.getType());
	        
	     // scales the input image to the output image
	        
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
	        g2d.dispose();
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        
	     // writes to output file
	        ImageIO.write(outputImage, "png", baos);
	        byte[] imagebytev2 = baos.toByteArray();
	        
			bis.close();
			
			// write the image to a file
			File outputfile = new File("image.png");
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile))) {
//	            outputStream.write(imagebyte);
				outputStream.write(imagebytev2);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
			InputStream is = new FileInputStream(outputfile);
			byte[] bytes = IOUtils.toByteArray(is);
//			PictureData.PictureType.PNG
			XSLFPictureData pictureIdx = ppt.addPicture(bytes, PictureData.PictureType.PNG);
			
			return pictureIdx;
			}catch (IOException e) {
				// TODO: handle exception
				return null;
			} 
		}
		
		return null;
	}
	
	

	@Override
	public ReportToPDF getReportMonitoringDataPDF(BodyReportMonitoring body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
//		Rectangle pagesize = new Rectangle(1012, 864);
//		Document document = new Document(pagesize);
		Document document = new Document(PageSize.A0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        HashMap<Long, InfoHeaderData> mapListInfoHeader = new HashMap<Long, InfoHeaderData>();
        List<InfoHeaderData> listInfoHeader = infoHeaderService.getAllListDataForReport(idcompany, idbranch);
        for(InfoHeaderData info : listInfoHeader) {
        	mapListInfoHeader.put(info.getId(), info);
        }
        List<UserMobileListData> listuser = userMobileService.getListAllUserMobileForMonitoring(body.getIdusermobile(), idcompany, idbranch);
        // countInfo = jumlah info yang ada
        int countInfo = 0;
        HashMap<Long, List<MonitoringData>> hashMonitorUserMobile = new HashMap<Long, List<MonitoringData>>();
        HashMap<Long, List<Long>> hashMonitorUserMobileInfo = new HashMap<Long, List<Long>>();
        List<Integer> listQuestionExistInMonitoring = new ArrayList<>();
        for(UserMobileListData user : listuser) {
        	List<MonitoringData> list = getListMonitoringData(user.getId(),body,idcompany,idbranch);
			
			//Set Header Info, Mencari list Info header paling Banyak di monitoring
			if(list != null && list.size() > 0) {
				for(MonitoringData monitor : list) {
					List<Long> listInfo = monitorUserMobileInfoService.getListDistinctUserMobileInfo(monitor.getIdmonitoring());
					if(listInfo.size() > countInfo) {
						countInfo = listInfo.size(); 
					}
					for(Long infoid : listInfo) {
						if(listQuestionExistInMonitoring.indexOf(infoid.intValue()) == -1) {
							listQuestionExistInMonitoring.add(infoid.intValue());
						}
					}
					hashMonitorUserMobileInfo.put(monitor.getIdmonitoring(), listInfo);
				}
				
				hashMonitorUserMobile.put(user.getId(), list);
			}
			
			//Set Header Info//
        }
        
        
        PdfPTable table = new PdfPTable(11 + countInfo);
        try {
        	document.open();
        	document.add(Chunk.NEWLINE);
        	document.add(new Paragraph("Recommended books for Spring framework"));
        	table.setWidthPercentage(95);
        	table.setSpacingBefore(10);
            

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            int[] widthKolom = {};

            PdfPCell hcell;
            
            hcell = new PdfPCell(new Phrase("Sales", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 3);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Customer", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 3);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Project", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 3);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Tanggal", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 2);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Check In Time", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 3);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Check Out Time", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 3);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Photo 1", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 5);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Photo 2", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 5);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Photo 3", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 5);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Photo 4", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 5);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Photo 5", headFont));
            hcell.setBackgroundColor(BaseColor.CYAN);
            widthKolom = addElementWidth(widthKolom, 5);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            
            int seqInfo = 1;
            for(Integer infoidMonitoring : listQuestionExistInMonitoring) {
            	String question = "";
            	if(mapListInfoHeader.get(infoidMonitoring.longValue()) != null) {
            		InfoHeaderData infoHeaderData = mapListInfoHeader.get(infoidMonitoring.longValue());
            		question = infoHeaderData.getQuestion();
            	}
            	hcell = new PdfPCell(new Phrase(question, headFont));
            	hcell.setBackgroundColor(BaseColor.CYAN);
            	widthKolom = addElementWidth(widthKolom, 4);
            	hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            	table.addCell(hcell);
            	seqInfo++;
            	
            	
            }
//            for(int cellinfo=0; cellinfo < countInfo; cellinfo++) {
//            	hcell = new PdfPCell(new Phrase("Info "+seqInfo, headFont));
//            	hcell.setBackgroundColor(BaseColor.CYAN);
//                widthKolom = addElementWidth(widthKolom, 4);
//                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(hcell);
//                seqInfo++;
//            }
            
            table.setWidths(widthKolom);
            
            for(UserMobileListData user : listuser) {
            	List<MonitoringData> list = hashMonitorUserMobile.get(user.getId());//getListMonitoringData(user.getId(),body,idcompany,idbranch);
            	if(list != null && list.size() > 0) {
            		for(MonitoringData monitor : list) {
            			PdfPCell valueCell = new PdfPCell(new Phrase(user.getNama()));
            			valueCell.setPaddingLeft(2);
            			valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            			valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	valueCell = new PdfPCell(new Phrase(monitor.getNamacustomer()+" ( "+monitor.getCustomercode()+" ) "));
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	valueCell = new PdfPCell(new Phrase(callPlanService.getProjectNameByIdCallPlan(monitor.getIdcallplan(), idcompany, idbranch)));
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	valueCell = new PdfPCell(new Phrase(monitor.getTanggal().toString()));
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	valueCell = new PdfPCell(new Phrase(monitor.getCheckintime()));
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	valueCell = new PdfPCell(new Phrase(monitor.getCheckouttime() ));
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	if(monitor.getPhoto1() != null && !monitor.getPhoto1().equals("")) {
                    		Image imagephoto = Image.getInstance(Base64.getDecoder().decode(monitor.getPhoto1()));
                    		imagephoto.scalePercent(30, 30);
                    		valueCell = new PdfPCell(imagephoto);
                    	}else {
                    		valueCell = new PdfPCell(new Phrase("Photo 1"));
                    	}
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	if(monitor.getPhoto2() != null && !monitor.getPhoto2().equals("")) {
                    		Image imagephoto = Image.getInstance(Base64.getDecoder().decode(monitor.getPhoto2()));
                    		imagephoto.scalePercent(30, 30);
                    		valueCell = new PdfPCell(imagephoto);
                    	}else {
                    		valueCell = new PdfPCell(new Phrase("Photo 2"));
                    	}
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	if(monitor.getPhoto3() != null && !monitor.getPhoto3().equals("")) {
                    		Image imagephoto = Image.getInstance(Base64.getDecoder().decode(monitor.getPhoto3()));
                    		imagephoto.scalePercent(30, 30);
                    		valueCell = new PdfPCell(imagephoto);
                    	}else {
                    		valueCell = new PdfPCell(new Phrase("Photo 3"));
                    	}
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	if(monitor.getPhoto4() != null && !monitor.getPhoto4().equals("")) {
                    		Image imagephoto = Image.getInstance(Base64.getDecoder().decode(monitor.getPhoto4()));
                    		imagephoto.scalePercent(30, 30);
                    		valueCell = new PdfPCell(imagephoto);
                    	}else {
                    		valueCell = new PdfPCell(new Phrase("Photo 4"));
                    	}
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	if(monitor.getPhoto5() != null && !monitor.getPhoto5().equals("")) {
                    		Image imagephoto = Image.getInstance(Base64.getDecoder().decode(monitor.getPhoto5()));
                    		imagephoto.scalePercent(30, 30);
                    		valueCell = new PdfPCell(imagephoto);
                    	}else {
                    		valueCell = new PdfPCell(new Phrase("Photo 5"));
                    	}
                    	valueCell.setPaddingLeft(2);
                    	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    	table.addCell(valueCell);
                    	
                    	                                
//                        List<Long> listInfo = hashMonitorUserMobileInfo.get(monitor.getIdmonitoring());
                        for(Integer infoidMonitoring : listQuestionExistInMonitoring) {
                        	String valInfo = "";
                        	List<DetailInfo> listdetailInfo = monitorUserMobileInfoService.getDetailInfo(infoidMonitoring.longValue(),monitor.getIdmonitoring());
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
//    							valInfo = question+" \n "+answer;
    							valInfo = answer;
                        	}
                        	
                        	valueCell = new PdfPCell(new Phrase(valInfo));
                        	valueCell.setPaddingLeft(2);
                        	valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        	valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        	table.addCell(valueCell);
                        }
            		}
            	}    
            }
            
        }catch (DocumentException ex) {
        	LOGGER.error("Error occurred: {0}", ex);
//        	System.out.println("Error occurred: {0}"+ ex);
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ReportToPDF data = new ReportToPDF();
        data.setDocument(document);
        data.setTable(table);
//        data.setInputStream(new ByteArrayInputStream(out.toByteArray()));
		return data;
	}
	
	private int[] addElementWidth(int[] a, int e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}

	@Override
	public List<DataMonitorForMaps> getListDataMaps(BodyGetMaps body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		return monitorUserMobileService.getListDataMonitorMaps(body.getIdusermobile(), body.getTanggal(), idcompany, idbranch);
	}

	@Override
	public TemplateMaps getTemplateMaps(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		TemplateMaps data = new TemplateMaps();
		data.setUsermobileoptions(userMobileService.getListAllUserMobile(idcompany, idbranch));
		data.setProjectoptions(projectService.getAllListProject(idcompany, idbranch));
		return data;
	}

	@Override
	public TemplateReport getTemplateReport(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		TemplateReport data = new TemplateReport();
		data.setUserMobileOptions(userMobileService.getListAllUserMobileForMonitoring("ALL",idcompany, idbranch));
		data.setProjectoptions(projectService.getAllListProject(idcompany, idbranch));
		return data;
	}

	@Override
	public ReportToPPT getReportMonitoringDataPPT(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
//		File file = new File("C:/example1.pptx");
		ReportToPPT reportToPPT = new ReportToPPT();
	      FileInputStream inputstream;
		try {
			// creating an empty presentation
	        XMLSlideShow ppt = new XMLSlideShow();
	  
	        // creating the slide master object
	        XSLFSlideMaster slideMaster
	            = ppt.getSlideMasters().get(0);
	  
	        // select a layout from specified slideLayout list
	        XSLFSlideLayout slidelayout = slideMaster.getLayout(
	            SlideLayout.TITLE_AND_CONTENT);
	  
	        // creating a slide with title and content layout
	        XSLFSlide slide = ppt.createSlide(slidelayout);
	  
	        // selection of title place holder
	        XSLFTextShape title = slide.getPlaceholder(1);
	        
	        // clear the existing text in the slide
	        title.clearText();
	  
	        // adding new paragraph
	        XSLFTextParagraph paragraph
	            = title.addNewTextParagraph();
	  
//	        // formatting line 1
//	        XSLFTextRun line1 = paragraph.addNewTextRun();
//	        line1.setText("Formatted Bold");
//	        
//	        // making the text bold
//	        line1.setBold(true);
	        
	        // moving to the next line
//	        paragraph.addLineBreak();
	  
	        for(int i=1; i < 14; i++) {
	        	XSLFTextRun line1 = paragraph.addNewTextRun();
		        line1.setText("Formatted with Color ke "+i);
		        line1.setFontColor(Color.RED);
		        line1.setFontSize(20.0);
		        paragraph.addLineBreak();
	        }
	        
	        String a1 = "/9j/4AAQSkZJRgABAQEAkACQAAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAgMDAwYDAwYMCAcIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAz/wAARCADKAd0DASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDltU1OfWdTuLy5kaW5u5Wmlkbq7sSWJ+pJqCiiv9A0klZH8eNtu7CiiimIKKKKACiiigAooooAKKKKACiiigAooooAKKKKACt74Zf8jvZf9tP/AEW1YNb3wy/5Hey/7af+i2rDE/wZ+j/I2ofxY+qPWqKKK+OPpgooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigCXT7+XS7+G5gcxz28iyxuOqspyD+Yr9j6/Guv2Ur8c8WFrhX/j/APbD9L8PG/8AaF/h/wDbj+fOiiiv6bPwgKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAre+GX/I72X/bT/0W1YNb3wy/5Hey/wC2n/otqwxP8Gfo/wAjah/Fj6o9aooor44+mCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACv2Ur8a6/ZSvx3xZ/5hf+3/AP2w/SvDz/mI/wC3P/bj+fOiiiv6aPwkKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAre+GX/I72X/bT/wBFtWDW98Mv+R3sv+2n/otqwxP8Gfo/yNqH8WPqj1qiiivjj6YK+iP2ev8AgnF4t+N2g6brt1f6bofh/UV82OVn8+5kjz1WNeOcfxMD7V8713PgH47eN9BvNJ02x8ZeKrLToZo4o7WDVriOGNNw+UIHCge2K8jOaePnh7ZfUjCXVtX08vP1uejlk8JGtfGQco9k7a+Z7N+3L+xN4X/Zi+HOjaromo69e3d/qAtJhfTRNGF8p3JUJGpByo6k8V8wV9+/8FaGL/ArwoTyTq6kk9/9Hkr4CrxeBcdiMZlMa+Km5SvLV+TPT4rwlHDZhKlQjyxstPkfS37IH7Dtj8VPB1x448c6hJo3g+0DvGquInulT78jOQdkYwRkcnBxjGT1mkS/sm+MfEp0GHSta0ppWEMOrTXN2lu7HgEFpW2/WSMLzXon7VIPhX/gm1pFtpBaK0ay02GQxnrE2wkE+5xn1z71+fNeTlFLE597fGVcRUppTcYRhLlSStq+79f+G9HMZ0Mp9lhqdGE24qUnJXu30XZeh7Z+2L+xzefsw6za3Vpdvq3hjVWK2d4ygSRvjPlyY4zjkMOGAPAxXidffuhq/wC0R/wS/l/tBWuL/SNPm8l3cl2ktHOxtx7lVAP1NfAVe5wpmmIxVGrh8Y71aM3CT722fzPK4gwFGhVhWw6tTqxUku190FFFFfVnz4UUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFfspX411+ylfjviz/AMwv/b//ALYfpXh5/wAxH/bn/tx/PnRRRX9NH4SFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAVvfDL/AJHey/7af+i2rBre+GX/ACO9l/20/wDRbVhif4M/R/kbUP4sfVHrVFFFfHH0wVe8L/8AIzad/wBfUX/oYqjV7wv/AMjNp3/X1F/6GKzq/A/QuHxI+7v+Cs3/ACQjwn/2F1/9J5K+A6+/P+Cs3/JCPCf/AGF1/wDSeSvgOvh/Dj/kSQ/xS/M+p41/5GkvSP5H2f8AspftaeCPHvwJ/wCFXfEuaPT4I7f7Db3cxKwTwdUBkH+qdMDBOBwvOeKxJ/2RfgV4I1OXVtW+MdhquhQfOunWNxBLdyY52lomdmz0+WNT7ivkupLS1lvrqOCGN5ZpnCRoi5Z2JwAB3JNdz4V9lXqVcFiJ0Y1HeUVy2v1aunyt+X5HKs/9pShTxVGNSUFaLd726J2aufqB4B+JejfEP9j7xDqehaWNE8N2tjfWem25XaywRRsgZhzySGP88nNfl3X6EfG4R/ssf8E6bfw1OY49V1SxXTTEDgvNOS8+PoC+f/r1+e9eR4fYeEY4uvR/hyqNRvrdR6363vv3PR4wrSbw9Kp8cYLm8m+n4BRRRX6KfGBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV+ylfjXX7KV+O+LP/ML/ANv/APth+leHn/MR/wBuf+3H8+dFFFf00fhIUUUUAFFFFABRRRQAV9+f8E/v+CV+k+J/BLeOvjFamLR7yDfp+kzXUlniM4xcTujIy5/hXcODk9QKb/wTS/4J0W7afD8V/ihBDZ6FYx/bdL0+9G1JFUbvtU4bgRjGVU9ep4xnzj/gpN/wUTuf2n9ek8KeF5ZrPwFpk3UEo2syKeJXH/PMHlEPoGPOAv5fnWd43O8a8jyGfJGP8Wsvs/3Yv+b0f6s+8yzK8LleFWa5vHmcv4dJ/a/vS8vX/Ipf8FGf+Cd17+yf4jbxD4djuL74f6lLiGQkySaTI3SGVupU/wADnrwCd3LfLNfol/wTm/4KBaZ8WPC6/Bv4uPBqMGowfYNMv7/5kvoyNotZ2P8AHjhHPXoTuwW8D/4KGf8ABPzU/wBkTxe2qaSlxqHgPVJiLO6PzPYOefs8x9f7rfxD3Brq4a4ixWGxX9gZ67V18E+lWPRp/wA3ddfUwzzJsPWw/wDbGU/wn8UetN9v8Pb/ACPmmiiiv0Y+KCiiigAooooAK3vhl/yO9l/20/8ARbVg1vfDL/kd7L/tp/6LasMT/Bn6P8jah/Fj6o9aooor44+mCvqTTPi7+y1YG3l/4Vv41+0w7W3i5fG8YOcfbvX2r5borzMyyuGNUVOpOFr/AAScb3723O7BY+WGbcYRlf8AmipfdfY+4viz+3v8DvjnoNppninwh421SysZhPBHsjg2PtK5zHdqTwSOTivmv9pLxT8LfE1zpB+GXhvWvDscKyjUBqErOZySnl7czy9MPnp1HXt5jRXnZVwvhculF4ac0lf3XNuOv93bz9dTtzDPq+Ni1XjC7tryrm089z6g/Z0+OHwUvPgoPA/j/wANSacRKbiTVI0ef7RLjAl3x/vY2xxtAK4+pFdV4B8Xfsv/ALPWpN4h0fUNd8W6xasZLOK5tJmeFu2wPFFGCOzNkj1r41orDE8J0as6jVerGM3eUVP3XffdN69bM1o8Q1acYJ0qblBWUnHVdtmlp6HqH7U/7UesftP+OFv7xPsOlWQMen2CvuW3U9WY/wATtgZOOwA6V5fRRX0ODwdHCUY4fDx5YR0SPGxOJq4iq61Z3k92FFFFdRgFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABX7KV+NdfspX474s/wDML/2//wC2H6V4ef8AMR/25/7cfz50UUV/TR+EhRRRQAUUUUAFfcv/AATL/wCCccfjyKD4nfEi1S28J2Y+1aZY3fyLqO3nz5QekAxkA/fxn7v3qH/BMj/gmtJ8cr+08eeOrNo/Bts/mWFhKMHWnB+8w/54A/8AfZ4+7nOz/wAFcP24NXvfGeo/B7w7HLomgaII4dWeMhG1FjGrrEoX7sCqy8fxHqABz+X8QcQYjNcc+G8jnadv3tT+SOzUe8tbabXtvdx+9ybJ6OX4RZ3msbx/5dw/ml0b7R6+e/ZPlv8Agpb/AMFHZPj/AKjN4H8E3D23gawk2XFxEdh1l16dP+WAI+UfxYBPavjmiivucjyTCZTg44LBxtFfe31bfVv/AIC0PlM1zXEZjiZYrEu8n9yXZeSHRyNFIGUlWU5BBwQa/SP/AIJ9ft2aN+0x4Gb4N/F37PqV3fQfY7C7veV1aPHEUjdp1x8r9WwOdw5/NqpLW6ksbmOaGR4ZoWDxyIxVkYHIII5BB71x8TcNYbOcL7Ct7s46wmvihLo1+q6+tmdORZ5XyzEe1p6xekovaS7P9GfQP7ff7B2r/sd+O/Ot1n1DwXqshOm6gRkxHr5Evo4HQ9GHI6ED56r9T/8Agnp+0XD/AMFBvgd4i+GvxK0sa3caNaRrcXsmMX0LErG5PVZ0K53r6Bsg18Q/tyfsRa7+xr8RPs03m6j4X1N2bSNU28Sr18qTHCyqOo6MOR3A+e4U4prPEyyHOWli6fXpUVrqS87atfO26Xs8QZBSVCOb5Ym8PPp1g9mn5X0T+XZvw6iiiv0Q+LCiiigAre+GX/I72X/bT/0W1YNb3wy/5Hey/wC2n/otqwxP8Gfo/wAjah/Fj6o9aooor44+mCiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACv2Ur8a6/ZSvx3xZ/5hf+3/AP2w/SvDz/mI/wC3P/bj+fOiiiv6aPwkKKKKACtLwbqtnofi/Sr3UbIajp9neQz3VoxwLqJXDPGf95QR+NZtFTOKlFxfUqMnFqSP11/ZR/4KQaX+1R+0VpvgzwhoF3oPhfTdCnu5heRxpM8qNEiRosbMqxqGPfJ44GOfgP8A4Kdf8n2/EP8A6/IP/SWGvRf+CJX/ACeTc/8AYu3f/o2CvOv+CnX/ACfb8Q/+vyD/ANJYa/IuG8lwmVcYVcHgo2gsOnvdtuau23uz9FzvM8RmHDlPE4l3k6zXZWUXokeR+Dfhh4l+I3n/APCPeHdc137Ljzv7OsJbryc9N2xTjOD19K3P+GYfiV/0Tzxz/wCCG6/+N1+gH/BGLXJPCv7GfxD1SBI3n0/Wbm4QOPlcpYwMAcYOMj1715f/AMP3/HX/AEJXhP8A7+XH/wAXXq1OK88r5jicFleEhONCSTbnyvVX/wAzghw/lVHBUMVj8RKDqptJRvs7Hyf/AMMw/Er/AKJ545/8EN1/8brlvE/hPVfBOrvp+s6ZqGkX8YDPbXtu8EyA8glHAIz9K/Q39nX/AILIeMvjT8cvC3hS78J+GbO217UI7OWeF5zJErHkqC2M/WvO/wDgudGF/aS8Ntgbm0FQTjk4mkrfK+Kc2ecU8pzTDRpucXJOM+bYyx+QZcstnmOArymoSUWnG250X/BBb/ko/wAQ/wDsG2n/AKNkqx+1J/wU10Hxz8KPif8AC/xl4UuNS8RWmqajpelXsMcRtFEc7pb3D7mDJLGAPuqclc5GSBX/AOCC3/JR/iH/ANg20/8ARslfH37UP/JzHxE/7GfUv/SqWvCp5Hg8y4yxjxUbumqMotNpppLqvxR6ss1xOC4awyoPSbqRkmrpptnC0UUV+wH5uFFFFABW98Mv+R3sv+2n/otqwa3vhl/yO9l/20/9FtWGJ/gz9H+RtQ/ix9UetUUUV8cfTBRXpn7J/wAJ9L+LXxSki15pBoGhadca1qaRsVeaCBQTGpHI3MVBxzjOOcUmu/tHrrY1i2/4QfwDb6ZqFu1va28OiwxzabnhZEuECzNIOMl3IJ7CvNqY+X1h4ejDmcUnLW1rt2S7vRu2it11R3U8InRVerLlTbS0vdpJv5K6+/ROzPNKK99+K76b+y74N8H6HpWg+HtR8R61pMGu6vqerabDqJHnElLeJZlZEUAEMQuTkHINauqfADSf2gbT4W+JtE0+18NjxxqT6PrVnZDZb288RJaaBDkKGjR229AQB3OeH+36UYxr1YuNKTklL/Cm7tdE1F236XSubrK5STjCV5qKk15O2ifVq6uvWzdj5tor3Xxz8dvD/gX4yXWi6b4D8HTeC9Du3017W40qGe8voo32PM12w84SthipDgDIyDiu+8G/ADRfhR+1J8Q9JGn2GraRZeDbzW9GTU7WK9EOVieNikispZCWUEjkDnqairxB7Knz1qbV4ucVdapWun2a5k7arz0Z00MjlWrewpTTamoPeyb0TXdXTXT01PkyivobwJbW3xk/Zo+IWq+KfD3h/Tf+Ect4rjSNbsNGg0t5blnK/ZyYUSOUN8o27SRuz1wa6D4reCfFGi/Dz4ZS+A/h/Y6nb6h4UtLnUbm28E2mqGW5YHLSSvbyHeRgnJ7570S4gjGt9XlFKSlyu8rR+Hm+K3Z7WTuTTyV1KTrwk3Hl5tve+JR2v3d99rnyzRX0B8B2W4+Evxq8Ralofh6XxBosFg9t9r0GzZLCVp3jkCW7ReVHkDBAQdOmRSeJNDtviB+xheeL9c8PaLoeu6brMNppV9YabFpn9sROAZFaOJUjk28kMq8bSOzZ1nnfJWdOUNFOMG0/tTjFqysrr3ld77u1iY5O5UlUjLdTklbpC979npp0vpc8Aor631j4F+Hof2WZfCI0nT0+IOm+GIfG0l4bZReNG07mSAuBuIWEgbc4zg4rjv2NPEY8a6h4n03VtF8I6ha6F4Qvr6z8/wANac8qTwiMRyNJ5G+QjJzvZs55zWC4jhPD1sRShdU3Z3drrpJaPR9NtjSGRTdejQlPWra3Wzdvdeu6TTfqfPNFfQH7G+oQ/Gn9pDT7bxJo/hi+tYtMvSLePQbK2gYiFmDNHFEiswIBBYEjsayPHXhrTPGn7GnhPxRpum2FpqnhrVJ9D1qS1tUie6DgSQSylQC5CgLuOSSxrqnnPs8UsLVhZ+7dp3S5+e3RdYW9ZI46OA9tSlVpS0XNutXyKEntfpO//brPFqK+zPhJ8MfDWifF34QeB77w1ot5fjw5daxrf2vToZpLie4ieSOOUspLeVt4DZxkYrwD4mH4iaR4VkHiXwVBoOm3LrCbmTwNZ6Wd33gqzrbIysdp+6wJAPbNY4TPo4mv7GnFLZ6ytdOU4pxVnf4L+jRrVyp06XtJNvvZXs+SE9XfT40vVM8yor2b9irwvpnirxT42j1TTrHUo7TwbqV1At1bpMIZlEe2RQwOHGThhyM1558HbGHU/i74WtrmGK4t7jWLSKWKVA6So0yAqwPBBBIINej9fj7WrSt/DSfrdN/ocssFJYeniL6Tcl/4Dy//ACRzlFfW2i/CXwzq37ZvxR0q20rw82q6Zayv4W0S7hSPTri72LhTHwjYBJCEYOST93NeP/FLxt4m8GPZ6V4j8BeF9A1/TbpriO8bwzb2r3CYZSjRBBbzR5OQTGw4BB715+Ez5YqpGnRhq4xlZtJ2krqy6pbNrrsmdmJyh0FOVSWkZSjdK6vHv2v0/Q8por6W8X+L/wCy/wBi3wl4tg0LwSniDU9euLK5uv8AhE9LPmRIJCq7Db7BjaOQoPHWvm7UL19Sv5riQRLJcSNIwiiWKMEnJ2ooCqOeFUADoABXbl2PnilNuKioycd76xdn0Wnb8jkxmFjQUbSvzJPa2j+b1IqKKK9M4QooooAKKKKACiiigAooooAKKKKACiiigAr9lK/Guv2Ur8d8Wf8AmF/7f/8AbD9K8PP+Yj/tz/24/nzooor+mj8JCiiigAooooA+vv8AgiV/yeTc/wDYu3f/AKNgrzr/AIKdf8n2/EP/AK/IP/SWGvRP+CJbBf2yrjJAz4duwPf95BXr/wC2J/wST+I/7Qf7Snirxjo2teCbbTNcuI5YIr28uknQLDHGdwS3ZQcoejHjFfkeKznBZbxrVrY+oqcXQSTffmTt+B+i0MsxWO4Yp0sJBzkqrdl25WaH/BIn/kw34pf9hG9/9N8NfmnX6/fsd/skeI/2N/2RfiHoHie+0O9u9Ra81COTTJpZIljNmkeGMkcZ3ZjPYjBHNfkDXZwHjKGLzXNMThpc0JTg01s/dZz8XYarh8vwFCvHlkoyuu2qPW/2Df8Ak8j4c/8AYbg/nX0B/wAF0P8Ak4/w1/2Ah/6Okr5//YNP/GY/w5/7DcH86+/v+CkX/BOXxv8AthfFnSNe8M6p4VsbSw00Wcianc3EUjP5jNkCOGQYwR3/AArDiPM8LgOL8JicZNQgqU1d7atmuSYHEYzhvEUMNFyl7SOi9EeQ/wDBBb/ko/xD/wCwbaf+jZK+Pv2of+TmPiJ/2M+pf+lUtfpn/wAEzf2APGX7GXizxVf+KNS8M38OuWkFvANLuJ5WRkdmO7zIYwBhhjBNfmX+1Ac/tL/ET/sZtS/9KpafCuY4bHcV5hicHNTg4U7NbaKz/EniDBV8Jw/g6GJi4yUp6PzZw1FFFfqx+fhRRRQAVvfDL/kd7L/tp/6LasGt74Zf8jvZf9tP/RbVhif4M/R/kbUP4sfVHrVFFFfHH0x6b+yZ8V9L+E/xUeTXkc6Br2n3Gi6m6KWeCCcAGQAcnawUnHOM45xUuo/s0waZfarJL4++H40axgee2vY9ZinkvsAlEW2jLThzwCGQYyeuK8torzKuAn7eWIoT5XJJPRPa9mr7PVrqu60O+ljEqKoVY80U21q1q7J/J2XZ6aNXZ758VV039qLwV4P1zS9d8O6b4k0TSoNC1fTdV1KHTiwiJEdxE0zKjqVJLANkYAwa1da/aA0v4AWfwt8MaDf2viP/AIQXUW1jWbyzO+2uZ5WO+GBzjcFjd139CSD2NfN1Fcf9gUpRjRqS5qUXJqP+JNWb6pKTstPNuxt/akopzhG03FRb8lbZdG7K79bJXPdvHfwK8PePPjLda1pnjvwdB4K1y6fU3ubnVYYb2xikbe8LWrHzjKuWCgIQcDJGa73wb+0zpPjX9qT4ieJ4dUi0LTh4OvNN0Oa5uFtZWMaxCLYSQfMZlZlA+bn2r5MorOrw8q1P2deo5Wi4R0Widrt92+VK+i8tWdNDPHRre3pU0m5qb1dm1rZdldt9emuhv+Lvit4o+IFtHDr3iTX9bhhbfHHf6hNcqjdMgOxANez/ALQnhwfFrwT8LX0LWvCNx/ZHhC0sb1J/EunWkttOuSY2SadGBGRnivnmivQr5cnOnOhaHs22lbTVWeiaOKhmEoRqQq3kqiSeuuklLR69Ue+/s/8Ai5vgx8HPjLZ/8JFY6P4je005dPex1iIyzuJnL/Z5YZCJCFbny2OATmuouPEWkftd+FPhvqfibxhpmm6p4dv49J8Q2mraslut3aghxdxpI4BZlG1yBks3PQV8tUVwV+H41K0sUp8tVtPmSWnuKDXo0r76P0OmGcyjQWFcb00pK197vmXzTS1t5bM+rNC/bS0i7/bLl1i48P8Ah2PSNRu20STVzcXYl/s8nylYhrj7OF2hGP7oDAPfmuX/AGfX0D4SfHD4pWDa9o39lL4a1ax028N/EYb4MU8lUk3bXdlxwCTkEdq+e6KlcNYeFOVKlJqMoKD6/C9Hr2u/LU2jxBW9vGvOKbjPnXT1Wney+5Hs/wCwP4p0zwd+0Ta3ur6jYaVZrp14huLy4SCIM0LBRuYgZJ4A71p/sTeIPD2tw+K/Avi/VbHSNA8SQW14Li9uUghSe2nVwu5yAC6lh74rwWiurG5NDESqy52nNQV19lwk5Ra87v8AA87CY50FGKimlKUtevNGMWvRqP4s+n/gz8cNL8Wf8FDNQ8Walqmn6bo9w97DbXN3cLBAsKwNFB8zkAZVU/E14Xr/AMG9X8N6RNe3F54VkhtwCy2vijTLqY5IHyxRTs7df4VPr2rlKKrDZV9WqqeHlZckIWavpDmtZ3WvvPv0Lr49V1L20btzlPR21koprZ6e6rbHtf7Duu6fovjbxgmoalpml/2j4Q1Cxtnv7yK0ilnk8sJGHkZVyeep7H0rm/A/w5vfhz8SfCuq6pqHhZbGDW7Myva+JNPvGiUTKxZlhndgoAJLEYHc15xRWksvl7epVjLSokmrX2utHdd+zEscnhoYecfgcmnfrLl30f8AKu3U98+NnhDSPjd+0v8AEK+03xx4V0yWK7S40x7y9EVtqfyru8u5H7pSuOMtyT2wSND4nfEmaD9kN/DHjTxNpPizxTLq0c+jLbajFqk+lW6j940lxGzr83Khd5OG9BhfnOiuOORLlo05zvGlyW0V04JLSXRO2q3eqvbQ6JZu3Uq1lG0qnPfV2tO+662vp8na6Pb/ABt4s0u7/YK8F6PFqWnyava+IrqeexS4Q3MMZWTDtHncFORgkY5rxCiivSwOCjhlNRd+aUpf+BO9jz8TiXW5br4Ul9wUUUV3HMFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFfspX411+ylfjviz/zC/8Ab/8A7YfpXh5/zEf9uf8Atx/PnRRRX9NH4SFFFFABRRRQBp+EvGeseAdcj1PQtV1LRdShBEd3YXT208YPBw6EMM/Wuy/4bB+Lf/RUfiL/AOFJef8AxyvOqK5K2Bw1aXNWpxk/NJ/mdFLFV6S5ac2l5No7rXv2oviZ4p0e407U/iJ461HT7yMxXFtda9dTQzoeCrI0hDA+hFcLRRWlDDUaK5aMFFeSS/IirXq1XerJv1dyWxv59Lvobm2mlt7m3dZYpYnKPE6nKsrDkEEAgivQB+2B8WgP+So/EX/wpLz/AOOV51RU18Hh67TrQUrd0n+ZVLE1qWlKTj6No9Ef9r74syIVb4ofERlYYIPiS8wR/wB/K8+ubmS8uHlld5ZZWLu7sWZ2PJJJ6k0yiihhKFC/sYKN+yS/IKuJq1f4snK3dthRRRXSYBRRRQAVvfDL/kd7L/tp/wCi2rBre+GX/I72X/bT/wBFtWGJ/gz9H+RtQ/ix9UetUUUV8cfTBRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABX7KV+NdfspX474s/wDML/2//wC2H6V4ef8AMR/25/7cfz50UUV/TR+EhRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFb3wy/wCR3sv+2n/otqwa3vhl/wAjvZf9tP8A0W1YYn+DP0f5G1D+LH1R61RRRXxx9MFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAH2T+1D+zt4x+OXw6+Fs3hbR/7Uj0/QES4P2uCDyy0cJX/AFjrnOD0z0rN+NfgvU/h18MvgBousW32PU9P1Vo7iHzEk8tvPiONyEqeCOhrzb9rv4v+Hfih4S+HFtoWofbp9B0YWl+vkSxeRLsiG3LqA3KtyuRxVzxR8aPDWo/Cb4LaZDqW+98JXzTatH9nlH2RTMjZyVw/AP3Ca/OMHgcfGhhVNe6qlR8vJJSV1Vs277O+nurda9/rfrOE9tVa+J0Er8y5W7U/dStvp/M9np2968Jnb/wU48UH/qAD/wBF21cN8Avi5qX7YWv+Ivh94+jstat2sp7qwv8A7LHFc6fKjqoKlFA/jznGeMHIJqPw9+034Hsv26te8Yy65s8N3ukC1hvPsdwd8myAbdgTeOUbkrjiuf8ABPxK+GX7LlrruveF/E19448Y6rby2lmf7MlsbaxVyGLMJOTghehJOMYGSa8yGXVvY8roS9r7KioPlfuzV7+9a0baXu18z3KmOpOq5Rqx5Paty95aw5Y9L+9fW1k9T0L4E2/xBs/2G9Ni+HOP+Eji1udHx9nx5IkkD/8AHx8nXb7/AK18/ftXax8UZ9e0mw+KEmb21geexj22nyxyMFY5txg5MY+8c/L713Hgnx54A8b/ALH2n+BvEfjc+GNVg1aTUJHOkXN8cbnwPkAXkPnO7jHSvI/iz4I8H+EIrI+FvHH/AAmDTlxcL/Y0+n/ZQMbTmQndnJ6dNvvX0GS4X2eY1Z1qaUnUm03Rlez6qr8KT10+V9TxMyrxqYGnCjLRQV17SKXxN2cN5O1tfTsfTn/BOC+sLP4EeLU1RVfT77WIbCdW+6ROkcOD7EuB+NcZ+y98Cv8AhV/7TPiu+1pT/Z3w0WWUSPx50r5Fv+LISw99tcb8JvjBoXhP9kfx54buNSa18Q6vewT2ECwykyBGiJYOq7VI2nqwPFejfFn9sfwx41+HHhaCzdIta1zULC68XEWsgCpbbMhiV+cEqCNu75VweuK8/FYPHxxmKVGEuSvLkbs9EoxfMvJpzjf+ayNaNfCywFCNSa5qSlNLu+aXuv1fI7dkzstZ+GWl/EL/AIKIeI7rWraK+sPDuiw6l9mkUMk0ixRKoZTwQNxOPUCuR+Af7WerftK/F/8A4Qrxjpukah4Y8SpNFDZC1Uf2eVjaRNjdTgJjJ5zgjGKy9f8A2v8AQPDH7a+oeMNNlk1rwrq1hFp14Y4Xjd4/LQMVSQKcq6DqORnHXNR/D3xJ8E/2d/Gl3400PxPq/ie/tkk/sjRjpssBtmdSvzyuoVsAkZ4wCeGNcscvqrCqOJoTlP2MFStF+5NJ3/wPm5W27aLfod2IxkJYpyw1WKXtLz1XvRtG3+JaSVl1e3U6/wCANp4i+FH7OXxbs/B32241/QvE0lnYm3tBdTSeW0UZIj2sGO0N/Ce5qp8YbjWvF37KGjaz8TNPgsfG8evQxaXLNbrbXtxCWXdvjAG3jflcAfIpwDiuE+G/7Tlp4e/Zs+Itu+v3Wk+N/EesHUbQWizxyMXaIuyyoNqdH6sDj61F8Sfjj4a+OvwQ8LXuvaq1t8RPCdytu4kt5n/tS13Lli6qV3Yw3zEHKvx8wrohlmKWO9vUp/8AL2N5qL5/gjs/+fbeknstTkr43DvAOjTn/wAu6lo3Sjq3a6/mSd492kfRX7TP/C+v+Fjp/wAK3/5F37HHn/kHf6/Lb/8AX/P02+3pXwX4+1fVdd8b6td67J5usz3cjXz7UXdNuIfhML1z93j0r6Q/aa1D4R/tGfEdPEH/AAtf+xttnHafZ/8AhGL24+4WO7d8nXd0x26181+M9K03RPFF5a6Pqv8AbmmwSbbe/wDsrW32lcD5vLb5l5yMH0r0+DMMqFCMJ01GfLr+5lCW/wBqb0l8rHJxJiHVm3CbcL/8/IyW3SC1j11f6mZRRRX3J8oFfspX411+ylfjviz/AMwv/b//ALYfpXh5/wAxH/bn/tx/PnRRRX9NH4SFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAVvfDL/AJHey/7af+i2rBre+GX/ACO9l/20/wDRbVhif4M/R/kbUP4sfVHrVFFFfHH0wUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAV+ylfjXX7KV+O+LP/ML/wBv/wDth+leHn/MR/25/wC3H8+dFFFf00fhIUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABW98Mv+R3sv8Atp/6LasGt74Zf8jvZf8AbT/0W1YYn+DP0f5G1D+LH1R61RRRXxx9MFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFfspX411+ylfjviz/zC/wDb/wD7YfpXh5/zEf8Abn/tx+Wv7Rv/AASK+I2ifEfUJ/AunW3iTQL24ea2AvoLaa1ViTscTOgO3plSc+lcB/w6v+PP/Qif+VrTv/j9fsTRXJhfGLPaNKNJxpysrXcZXfraaX4HRX8NMpq1HUUpxv0TVl6Xi3+J+O3/AA6v+PP/AEIn/la07/4/R/w6v+PP/Qif+VrTv/j9fsTRW/8AxGnO/wDn1S/8Bn/8mY/8Qvyr/n5U++P/AMgfjt/w6v8Ajz/0In/la07/AOP0f8Or/jz/ANCJ/wCVrTv/AI/X7E0Uf8Rpzv8A59Uv/AZ//Jh/xC/Kv+flT74//IH47f8ADq/48/8AQif+VrTv/j9H/Dq/48/9CJ/5WtO/+P1+xNFH/Eac7/59Uv8AwGf/AMmH/EL8q/5+VPvj/wDIH47f8Or/AI8/9CJ/5WtO/wDj9H/Dq/48/wDQif8Ala07/wCP1+xNFH/Eac7/AOfVL/wGf/yYf8Qvyr/n5U++P/yB+O3/AA6v+PP/AEIn/la07/4/R/w6v+PP/Qif+VrTv/j9fsTRR/xGnO/+fVL/AMBn/wDJh/xC/Kv+flT74/8AyB+O3/Dq/wCPP/Qif+VrTv8A4/R/w6v+PP8A0In/AJWtO/8Aj9fsTRR/xGnO/wDn1S/8Bn/8mH/EL8q/5+VPvj/8gfjt/wAOr/jz/wBCJ/5WtO/+P0f8Or/jz/0In/la07/4/X7E0Uf8Rpzv/n1S/wDAZ/8AyYf8Qvyr/n5U++P/AMgfjt/w6v8Ajz/0In/la07/AOP0f8Or/jz/ANCJ/wCVrTv/AI/X7E0Uf8Rpzv8A59Uv/AZ//Jh/xC/Kv+flT74//IH47f8ADq/48/8AQif+VrTv/j9a3gX/AIJhfHLRvFVrc3Pgjy4Y9+5v7Z084yjAcCfPUiv1zoqZ+M2dzi4OlS1/uz/+TKh4Y5XGSkqlTTzj/wDIH5of8O/Pi7/0KX/lUsv/AI9R/wAO/Pi7/wBCl/5VLL/49X6X0V5n/EUs1/590/ul/wDJnf8A6g5f/PP74/8AyJ+aH/Dvz4u/9Cl/5VLL/wCPUf8ADvz4u/8AQpf+VSy/+PV+l9FH/EUs1/590/ul/wDJh/qDl/8APP74/wDyJ+aH/Dvz4u/9Cl/5VLL/AOPUf8O/Pi7/ANCl/wCVSy/+PV+l9FH/ABFLNf8An3T+6X/yYf6g5f8Azz++P/yJ+aH/AA78+Lv/AEKX/lUsv/j1H/Dvz4u/9Cl/5VLL/wCPV+l9FH/EUs1/590/ul/8mH+oOX/zz++P/wAifmh/w78+Lv8A0KX/AJVLL/49R/w78+Lv/Qpf+VSy/wDj1fpfRR/xFLNf+fdP7pf/ACYf6g5f/PP74/8AyJ+aH/Dvz4u/9Cl/5VLL/wCPUf8ADvz4u/8AQpf+VSy/+PV+l9FH/EUs1/590/ul/wDJh/qDl/8APP74/wDyJ+aH/Dvz4u/9Cl/5VLL/AOPUf8O/Pi7/ANCl/wCVSy/+PV+l9FH/ABFLNf8An3T+6X/yYf6g5f8Azz++P/yJ+aH/AA78+Lv/AEKX/lUsv/j1H/Dvz4u/9Cl/5VLL/wCPV+l9FH/EUs1/590/ul/8mH+oOX/zz++P/wAifmh/w78+Lv8A0KX/AJVLL/49R/w78+Lv/Qpf+VSy/wDj1fpfRR/xFLNf+fdP7pf/ACYf6g5f/PP74/8AyJ+aH/Dvz4u/9Cl/5VLL/wCPUf8ADvz4u/8AQpf+VSy/+PV+l9FH/EUs1/590/ul/wDJh/qDl/8APP74/wDyJ+aH/Dvz4u/9Cl/5VLL/AOPUf8O/Pi7/ANCl/wCVSy/+PV+l9FH/ABFLNf8An3T+6X/yYf6g5f8Azz++P/yJ+aH/AA78+Lv/AEKX/lUsv/j1H/Dvz4u/9Cl/5VLL/wCPV+l9FH/EUs1/590/ul/8mH+oOX/zz++P/wAifmh/w78+Lv8A0KX/AJVLL/49R/w78+Lv/Qpf+VSy/wDj1fpfRR/xFLNf+fdP7pf/ACYf6g5f/PP74/8AyJ+aH/Dvz4u/9Cl/5VLL/wCPUf8ADvz4u/8AQpf+VSy/+PV+l9FH/EUs1/590/ul/wDJh/qDl/8APP74/wDyJ+aH/Dvz4u/9Cl/5VLL/AOPUf8O/Pi7/ANCl/wCVSy/+PV+l9FH/ABFLNf8An3T+6X/yYf6g5f8Azz++P/yJ+aH/AA78+Lv/AEKX/lUsv/j1H/Dvz4u/9Cl/5VLL/wCPV+l9FH/EUs1/590/ul/8mH+oOX/zz++P/wAifmh/w78+Lv8A0KX/AJVLL/49R/w78+Lv/Qpf+VSy/wDj1fpfRR/xFLNf+fdP7pf/ACYf6g5f/PP74/8AyJ+aH/Dvz4u/9Cl/5VLL/wCPUf8ADvz4u/8AQpf+VSy/+PV+l9FH/EUs1/590/ul/wDJh/qDl/8APP74/wDyJ+aH/Dvz4u/9Cl/5VLL/AOPUf8O/Pi7/ANCl/wCVSy/+PV+l9FH/ABFLNf8An3T+6X/yYf6g5f8Azz++P/yJ8A/BL/gnB421XxvZS+LLKDQ9HtZllnBu4p5bhVIOxRGzAZ6ZJFff1FFfK5/xHjM3qRqYqy5dklZK++7b19T6DKMkw2WwcMPfXdvf9D//2Q==";
	        XSLFPictureData idx1 = decodeToImagePPT(a1, ppt);
	        XSLFPictureShape pic1 = slide.createPicture(idx1);
	        //Recangle(x-corrdinate,y-coordinate,width, height);
	        //y cordinat = atas bawah
	        //x cordinat = kiri kanan
	        pic1.setAnchor(new java.awt.Rectangle(350, 120, 130, 130));
	        
	        XSLFPictureShape pic2 = slide.createPicture(idx1);
	        //Recangle(x-corrdinate,y-coordinate,width, height);
	        pic2.setAnchor(new java.awt.Rectangle(500, 120, 130, 130));
	        
	        XSLFPictureShape pic3 = slide.createPicture(idx1);
	        //Recangle(x-corrdinate,y-coordinate,width, height);
	        pic3.setAnchor(new java.awt.Rectangle(350, 260, 130, 130));
	        
	        XSLFPictureShape pic4 = slide.createPicture(idx1);
	        //Recangle(x-corrdinate,y-coordinate,width, height);
	        pic4.setAnchor(new java.awt.Rectangle(500, 260, 130, 130));
	        
	        XSLFPictureShape pic5 = slide.createPicture(idx1);
	        //Recangle(x-corrdinate,y-coordinate,width, height);
	        pic5.setAnchor(new java.awt.Rectangle(350, 400, 130, 130));
	        
//	        XSLFSlide slide2 = ppt.createSlide(slidelayout);
//	     // selection of title place holder
//	        XSLFTextShape title2 = slide2.getPlaceholder(1);
//	        
//	        // clear the existing text in the slide
//	        title2.clearText();
//	        
//	        String a = "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";
//	        XSLFPictureData idx = decodeToImagePPT(a, ppt);
//	        
//	      //creating a slide with given picture on it
//	        XSLFPictureShape pic = slide2.createPicture(idx);
//	        //Recangle(x-corrdinate,y-coordinate,width, height);
//	        pic.setAnchor(new java.awt.Rectangle(300, 50, 130, 130));
	        
	        
	        // formatting line 3
//	        XSLFTextRun line3 = paragraph.addNewTextRun();
//	        line3.setText("Formatted with Underline");
//	        
//	        // underlining the text
//	        line3.setUnderlined(true);
//	        
//	        // setting color to the text
//	        line3.setFontColor(Color.GRAY);
//	        
//	        // moving to the next line
//	        paragraph.addLineBreak();
//	  
//	        // formatting line 4
//	        XSLFTextRun line4 = paragraph.addNewTextRun();
//	        line4.setText("Text Formatted with Strike");
//	        line4.setFontSize(12.0);
//	        
//	        // making the text italic
//	        line4.setItalic(true);
//	        
//	        // setting color to the text
//	        line4.setFontColor(java.awt.Color.BLUE);
//	        
//	        // strike through the text
//	        line4.setStrikethrough(true);
//	        
//	        // setting font size to the text
//	        line4.setFontSize(24.0);
//	        
//	        // moving to the next line
//	        paragraph.addLineBreak();
	        
	      //saving the changes 
	        reportToPPT.setPpt(ppt);
	      FileOutputStream out = new FileOutputStream("powerpoint.pptx");
	      ppt.write(out);
	      
	      System.out.println("Presentation edited successfully");
	      out.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	      
	      
		
		return reportToPPT;
	}

}
