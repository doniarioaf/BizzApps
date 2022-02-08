package com.servlet.admin.importfile.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.servlet.admin.importfile.entity.DataColumnFileCustomerCallPlan;
import com.servlet.admin.importfile.service.ImportFileService;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class ImportFileHandler implements ImportFileService{

	@Override
	public ReturnData importFileExcelCustomerCallPlan(InputStream is, MultipartFile file) {
		// TODO Auto-generated method stub
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		String message = "Mohon Masukan File Excel!";
		String sheetname = "customercallplan";
		String Type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		if(Type.equals(file.getContentType())) {
			try {
				Workbook workbook = new XSSFWorkbook(is);
				Sheet sheet = workbook.getSheet(sheetname);
				if(sheet == null) {
					message = "Nama Sheet Harus customercallplan";
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.FILE_EXCEL_FAILED,message);
					validations.add(msg);
				}else {
					List<ValidationDataMessage> validationsFile = validasiExcelFile(sheet);
					if(validationsFile.size() > 0) {
						validations.addAll(validationsFile);
					}
					Iterator<Row> rows = sheet.iterator();
					
					int rowNumber = 0;
					 while (rows.hasNext()) {
						 Row currentRow = rows.next();
						 
						// skip header
				        if (rowNumber == 0) {
				          rowNumber++;
				          continue;
				        }
				        Iterator<Cell> cellsInRow = currentRow.iterator();
				        int cellIdx = 0;
				        while (cellsInRow.hasNext()) {
				        	Cell currentCell = cellsInRow.next();
				        	switch (cellIdx) {
				        	case 0:
//				                tutorial.setId((long) currentCell.getNumericCellValue());
				                break;

				              case 1:
//				                tutorial.setTitle(currentCell.getStringCellValue());
				                break;

				              case 2:
//				                tutorial.setDescription(currentCell.getStringCellValue());
				                break;

				              case 3:
//				                tutorial.setPublished(currentCell.getBooleanCellValue());
				                break;

				              default:
				                break;
				              }
				        	cellIdx++;
				        	}
				        }
				}
				
				 }catch (IOException e) {
					message = "File Gagal Import";
					ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.FILE_EXCEL_FAILED,message);
					validations.add(msg);
				 }
		}else {
			ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.FILE_EXCEL_FAILED,message);
			validations.add(msg);
		}
		ReturnData data = new ReturnData();
		data.setId(validations.size() > 0?0:1);
		data.setSuccess(validations.size() > 0?false:true);
		data.setValidations(validations);
		return data;
	}
	
	private List<ValidationDataMessage> validasiExcelFile(Sheet sheet){
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		List<DataColumnFileCustomerCallPlan> listDataFile = new ArrayList<DataColumnFileCustomerCallPlan>();
		Iterator<Row> rows = sheet.iterator();
		String message = "";
		int rowNumber = 0;
		boolean flagheader = true;
		while (rows.hasNext()) {
			Row currentRow = rows.next();
			
			// skip header
	        if (rowNumber == 0) {
	        	flagheader = validasiHeaderExcelFile(currentRow);
	          rowNumber++;
	          continue;
	        }
	        if(flagheader) {
	        	Iterator<Cell> cellsInRow = currentRow.iterator();
	        	int cellIdx = 0;
	        	DataColumnFileCustomerCallPlan datafile = new DataColumnFileCustomerCallPlan();
	        	while (cellsInRow.hasNext()) {
	        		Cell currentCell = cellsInRow.next();
	        		message = "";
	        		switch (cellIdx) {
	        		case 0:
	        			String callplanName = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	        			String callplanNameNoSpace = callplanName.replaceAll(" ", "");
	        			datafile.setCallplanName(callplanName);
	        			if(callplanNameNoSpace.equals("")) {
	        				message = "Call Plan Tidak Boleh Kosong";
	        			}
	                    break;

	                  case 1:
	                	  String projectNumber = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String projectNumberNoSpace = projectNumber.replaceAll(" ", "");
	                	  datafile.setProjectNumber(projectNumber);
	                	  if(projectNumberNoSpace.equals("")) {
		        				message = "Project Number Tidak Boleh Kosong";
	                	  }
	                    break;

	                  case 2:
	                	 String projectName = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	 String projectNameNoSpace = projectName.replaceAll(" ", "");
	                	 datafile.setProjectName(projectName);
	                	 if(projectNameNoSpace.equals("")) {
		        				message = "Project Name Tidak Boleh Kosong";
	                	  }
	                    break;

	                  case 3:
	                	  String customerCode = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String customerCodeNoSpace = customerCode.replaceAll(" ", "");
	                	  datafile.setCustomerCode(customerCode);
	                	  if(customerCodeNoSpace.equals("")) {
		        				message = "Customer Code Tidak Boleh Kosong";
	                	  }
	                	  
	                    break;
	                    
	                  case 4:
	                	  String nama = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  datafile.setNama(nama);
	                	  String namaNoSpace = nama.replaceAll(" ", "");
	                	  if(namaNoSpace.equals("")) {
		        				message = "Nama Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 5:
	                	  String contactPerson = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String contactPersonNoSpace = contactPerson.replaceAll(" ", "");
	                	  datafile.setContactPerson(contactPerson);
	                	  if(contactPersonNoSpace.equals("")) {
		        				message = "Contact Person Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 6:
	                	  String contactNumber = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String contactNumberNoSpace = contactNumber.replaceAll(" ", "");
	                	  datafile.setContactNumber(contactNumber);
	                	  if(contactNumberNoSpace.equals("")) {
		        				message = "Contact Number Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 7:
	                	  String address = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String addressNoSpace = address.replaceAll(" ", "");
	                	  datafile.setAddress(address);
	                	  if(addressNoSpace.equals("")) {
		        				message = "Address Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 8:
	                	  String provinsi = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String provinsiNoSpace = provinsi.replaceAll(" ", "");
	                	  datafile.setProvinsi(provinsi);
	                	  if(provinsiNoSpace.equals("")) {
		        				message = "provinsi Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 9:
	                	  String city = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String cityNoSpace = city.replaceAll(" ", "");
	                	  datafile.setCity(city);
	                	  if(cityNoSpace.equals("")) {
		        				message = "city Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 10:
	                	  String area = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String areaNoSpace = area.replaceAll(" ", "");
	                	  datafile.setArea(area);
	                	  if(areaNoSpace.equals("")) {
		        				message = "area Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 11:
	                	  String subArea = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String subAreaNoSpace = subArea.replaceAll(" ", "");
	                	  datafile.setSubArea(subArea);
	                	  if(subAreaNoSpace.equals("")) {
		        				message = "subArea Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 12:
	                	  String latitude = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  datafile.setLatitude(latitude);
	                   break;
	                   
	                  case 13:
	                	  String longitude = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  datafile.setLongitude(longitude);
	                   break;

		              default:
		                break;
		              }
		        	cellIdx++;
		        	
		        	if(!message.equals("")) {
		        		ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.FILE_EXCEL_FAILED,message);
						validations.add(msg);
						break;
		        	}
	        	}
	        	listDataFile.add(datafile);
	        }else {
	        	message = "Template Tidak Sesuai, Cek Kembali";
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.FILE_EXCEL_FAILED,message);
				validations.add(msg);
	        }
	        
	        if(validations.size() > 0) {
	        	break;
	        }
		}
		return validations;
	}
	
	private boolean validasiHeaderExcelFile(Row currentRow){
		boolean flag = true;
		Iterator<Cell> cellsInRow = currentRow.iterator();
        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
        	Cell currentCell = cellsInRow.next();
        	switch (cellIdx) {
        	case 0:
        		flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("callplanName".toLowerCase()):false;
                break;

              case 1:
            	flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("ProjectNumber".toLowerCase()):false;
                break;

              case 2:
            	flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Project Name".toLowerCase()):false;
                break;

              case 3:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("customerCode".toLowerCase()):false;
                break;
                
              case 4:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Nama".toLowerCase()):false;
               break;
               
              case 5:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Contact Person".toLowerCase()):false;
               break;
               
              case 6:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Contact Number".toLowerCase()):false;
               break;
               
              case 7:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Address".toLowerCase()):false;
               break;
               
              case 8:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Provinsi".toLowerCase()):false;
               break;
               
              case 9:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("City".toLowerCase()):false;
               break;
               
              case 10:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Area".toLowerCase()):false;
               break;
               
              case 11:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("SubArea".toLowerCase()):false;
               break;
               
              case 12:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Latitude".toLowerCase()):false;
               break;
               
              case 13:
            	  flag = flag ?currentCell.getStringCellValue().toLowerCase().equals("Longitude".toLowerCase()):false;
               break;

              default:
                break;
              }
        	cellIdx++;
        	}
		return flag;
	}

}
