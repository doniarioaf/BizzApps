package com.servlet.report.handler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
	public ReportToPPT getReportMonitoringDataPPT(BodyReportMonitoring body,long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportToPPT reportToPPT = new ReportToPPT();
		XMLSlideShow ppt = new XMLSlideShow();
		List<UserMobileListData> listuser = userMobileService.getListAllUserMobileForMonitoring(body.getIdusermobile(), idcompany, idbranch);
		for(UserMobileListData user : listuser) {
			List<MonitoringData> list = getListMonitoringData(user.getId(),body,idcompany,idbranch);
			if(list != null && list.size() > 0) {
				for(MonitoringData monitor : list) {
					XSLFSlideMaster slideMaster = ppt.getSlideMasters().get(0);
		  
					XSLFSlideLayout slidelayout = slideMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
		  
					XSLFSlide slide = ppt.createSlide(slidelayout);
					
					XSLFTextShape titleslide = slide.getPlaceholder(0);  
					titleslide.setText(""); 
		  
					XSLFTextShape bodyslide = slide.getPlaceholder(1);
		        
					bodyslide.clearText();
					
					XSLFTextParagraph paragraph = bodyslide.addNewTextParagraph();
					
					XSLFTextRun line = paragraph.addNewTextRun();
					line.setText("User : "+monitor.getNamauser());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("Customer : "+monitor.getNamacustomer());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("Project : "+callPlanService.getProjectNameByIdCallPlan(monitor.getIdcallplan(), idcompany, idbranch));
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("Contact Number : "+monitor.getPhone());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("Contact Person : "+monitor.getContactperson());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("Customer Code : "+monitor.getCustomercode());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("Provinsi : "+monitor.getProvinsi());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("City : "+monitor.getCity());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("Area : "+monitor.getAreaname());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        line = paragraph.addNewTextRun();
					line.setText("SubArea : "+monitor.getSubarename());
					line.setFontColor(Color.BLACK);
					line.setFontSize(20.0);
			        paragraph.addLineBreak();
			        
			        int width = 130;
			        int height = 130;
			        int photoke = 1;
			        if(monitor.getPhoto1() != null && !monitor.getPhoto1().equals("")) {
			        	XSLFPictureData idx1 = decodeToImagePPT(monitor.getPhoto1(), ppt);
			        	setPicturePPT(photoke,slide,idx1,width,height);
			        	photoke++;
			        }
			        
			        if(monitor.getPhoto2() != null && !monitor.getPhoto2().equals("")) {
			        	XSLFPictureData idx1 = decodeToImagePPT(monitor.getPhoto2(), ppt);
			        	setPicturePPT(photoke,slide,idx1,width,height);
			        	photoke++;
			        }
			        
			        if(monitor.getPhoto3() != null && !monitor.getPhoto3().equals("")) {
			        	XSLFPictureData idx1 = decodeToImagePPT(monitor.getPhoto3(), ppt);
			        	setPicturePPT(photoke,slide,idx1,width,height);
			        	photoke++;
			        }
			        
			        if(monitor.getPhoto4() != null && !monitor.getPhoto4().equals("")) {
			        	XSLFPictureData idx1 = decodeToImagePPT(monitor.getPhoto4(), ppt);
			        	setPicturePPT(photoke,slide,idx1,width,height);
			        	photoke++;
			        }
			        
			        if(monitor.getPhoto5() != null && !monitor.getPhoto5().equals("")) {
			        	XSLFPictureData idx1 = decodeToImagePPT(monitor.getPhoto5(), ppt);
			        	setPicturePPT(photoke,slide,idx1,width,height);
			        	photoke++;
			        }
		  
				}
			}
		}
	      
		reportToPPT.setPpt(ppt);
		return reportToPPT;
	}
	
	private void setPicturePPT(int photoke,XSLFSlide slide,XSLFPictureData idx1,int width,int height) {
		//Recangle(x-corrdinate,y-coordinate,width, height);
        //y cordinat = atas bawah
        //x cordinat = kiri kanan
		if(photoke == 1) {
			XSLFPictureShape pic1 = slide.createPicture(idx1);
	        pic1.setAnchor(new Rectangle(400, 120, width, height));
		}else if(photoke == 2) {
			XSLFPictureShape pic2 = slide.createPicture(idx1);
	        pic2.setAnchor(new Rectangle(550, 120, width, height));
		}else if(photoke == 3) {
			XSLFPictureShape pic3 = slide.createPicture(idx1);
	        pic3.setAnchor(new Rectangle(400, 260, width, height));
		}else if(photoke == 4) {
			XSLFPictureShape pic4 = slide.createPicture(idx1);
	        pic4.setAnchor(new Rectangle(550, 260, width, height));
		}else if(photoke == 5) {
			XSLFPictureShape pic5 = slide.createPicture(idx1);
	        pic5.setAnchor(new Rectangle(400, 400, width, height));
		}
		
	}

}
