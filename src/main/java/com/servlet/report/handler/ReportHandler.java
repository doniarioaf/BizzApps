package com.servlet.report.handler;

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
import java.util.ArrayList;
import java.util.Arrays;
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
import com.servlet.mobile.monitorusermobileinfo.entity.DetailInfo;
import com.servlet.mobile.monitorusermobileinfo.service.MonitorUserMobileInfoService;
import com.servlet.report.entity.BodyReportMonitoring;
import com.servlet.report.entity.MonitoringData;
import com.servlet.report.entity.ReportToPDF;
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

	@Override
	public ReportToPDF getReportMonitoringDataPDF(BodyReportMonitoring body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
//		Rectangle pagesize = new Rectangle(1012, 864);
//		Document document = new Document(pagesize);
		Document document = new Document(PageSize.A0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        
        
        List<UserMobileListData> listuser = userMobileService.getListAllUserMobileForMonitoring(body.getIdusermobile(), idcompany, idbranch);
        // countInfo = jumlah info yang ada
        int countInfo = 0;
        HashMap<Long, List<MonitoringData>> hashMonitorUserMobile = new HashMap<Long, List<MonitoringData>>();
        HashMap<Long, List<Long>> hashMonitorUserMobileInfo = new HashMap<Long, List<Long>>();
        for(UserMobileListData user : listuser) {
        	List<MonitoringData> list = getListMonitoringData(user.getId(),body,idcompany,idbranch);
			
			//Set Header Info, Mencari list Info header paling Banyak di monitoring
			if(list != null && list.size() > 0) {
				for(MonitoringData monitor : list) {
					List<Long> listInfo = monitorUserMobileInfoService.getListDistinctUserMobileInfo(monitor.getIdmonitoring());
					if(listInfo.size() > countInfo) {
						countInfo = listInfo.size(); 
					}
					hashMonitorUserMobileInfo.put(monitor.getIdmonitoring(), listInfo);
				}
				
				hashMonitorUserMobile.put(user.getId(), list);
			}
			
			//Set Header Info//
        }
        
        
        PdfPTable table = new PdfPTable(10 + countInfo);
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
            for(int cellinfo=0; cellinfo < countInfo; cellinfo++) {
            	hcell = new PdfPCell(new Phrase("Info "+seqInfo, headFont));
            	hcell.setBackgroundColor(BaseColor.CYAN);
                widthKolom = addElementWidth(widthKolom, 4);
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);
                seqInfo++;
            }
            
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
                    	
                    	valueCell = new PdfPCell(new Phrase(monitor.getNamacustomer()));
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

}
