package com.servlet.admin.importfile.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.servlet.admin.customer.service.CustomerService;
import com.servlet.admin.importfile.entity.DataColumnFileCustomerCallPlan;
import com.servlet.admin.importfile.entity.ReturnDataCustomerCallPlan;
import com.servlet.admin.importfile.service.ImportFileService;
import com.servlet.mobile.project.entity.BodyProject;
import com.servlet.shared.ConstansCodeMessage;
import com.servlet.shared.ReturnData;
import com.servlet.shared.ValidationDataMessage;

@Service
public class ImportFileHandler implements ImportFileService{
	@Autowired
	private CustomerService customerService;

	@Override
	public ReturnData importFileExcelCustomerCallPlan(InputStream is, MultipartFile file,long idcompany, long idbranch) {
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
					ReturnDataCustomerCallPlan data = validasiExcelFileCustomerCallPlan(sheet,idcompany,idbranch);
					if(data.getValidations().size() > 0) {
						validations.addAll(data.getValidations());
					}else {
						for (Map.Entry<String, String> set :
							data.getMapDistinctCallPlan().entrySet()) {
				 
				            // Printing all elements of a Map
				            System.out.println("CallPlan | "+set.getKey() + " = "
				                               + set.getValue());
				        }
						
						for (Map.Entry<String, BodyProject> set :
							data.getMapDistinctProject().entrySet()) {
				 
				            // Printing all elements of a Map
				            System.out.println("Project | "+set.getKey() + " = "
				                               + set.getValue().getNama());
				        }
						
						for(DataColumnFileCustomerCallPlan datafile : data.getListDataFile()) {
							System.out.println("Data File "+datafile.getNama());
						}
					}
					
					
//					Iterator<Row> rows = sheet.iterator();
//					
//					int rowNumber = 0;
//					 while (rows.hasNext()) {
//						 Row currentRow = rows.next();
//						 
//						// skip header
//				        if (rowNumber == 0) {
//				          rowNumber++;
//				          continue;
//				        }
//				        Iterator<Cell> cellsInRow = currentRow.iterator();
//				        int cellIdx = 0;
//				        while (cellsInRow.hasNext()) {
//				        	Cell currentCell = cellsInRow.next();
//				        	switch (cellIdx) {
//				        	case 0:
////				                tutorial.setId((long) currentCell.getNumericCellValue());
//				                break;
//
//				              case 1:
////				                tutorial.setTitle(currentCell.getStringCellValue());
//				                break;
//
//				              case 2:
////				                tutorial.setDescription(currentCell.getStringCellValue());
//				                break;
//
//				              case 3:
////				                tutorial.setPublished(currentCell.getBooleanCellValue());
//				                break;
//
//				              default:
//				                break;
//				              }
//				        	cellIdx++;
//				        	}
//				        }
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
	
	private ReturnDataCustomerCallPlan validasiExcelFileCustomerCallPlan(Sheet sheet,long idcompany, long idbranch){
		List<ValidationDataMessage> validations = new ArrayList<ValidationDataMessage>();
		List<DataColumnFileCustomerCallPlan> listDataFile = new ArrayList<DataColumnFileCustomerCallPlan>();
		Iterator<Row> rows = sheet.iterator();
		String message = "";
		int rowNumber = 0;
		boolean flagheader = true;
		HashMap<String, String> mapCustomerCode = new HashMap<String, String>();
		HashMap<String, String> mapCustomerCodeExistInDB = new HashMap<String, String>();
		HashMap<String, String> mapDistinctCallPlan = new HashMap<String, String>();
		HashMap<String, BodyProject> mapDistinctProject = new HashMap<String, BodyProject>();
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
	        	String projectNumberDistinct = "";
        		String projectNameDistinct = "";
        		
        		String custCodeCheck = "";
        		String custNameCheck = "";
        		String callplanNameCheck = "";
	        	while (cellsInRow.hasNext()) {
	        		Cell currentCell = cellsInRow.next();
	        		
	        		message = "";
	        		
	        		switch (cellIdx) {
	        		case 0:
	        			String callplanName = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	        			callplanNameCheck = callplanName;
	        			String callplanNameNoSpace = callplanName.replaceAll(" ", "");
	        			datafile.setCallplanName(callplanName);
	        			if(callplanNameNoSpace.equals("")) {
	        				message = "Call Plan Tidak Boleh Kosong";
	        			}else {
	        				if(mapDistinctCallPlan.get(callplanNameNoSpace) == null) {
	        					mapDistinctCallPlan.put(callplanNameNoSpace, callplanName);
	        				}
	        			}
	                    break;

	                  case 1:
	                	  String projectNumber = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  projectNumberDistinct = projectNumber;
	                	  String projectNumberNoSpace = projectNumber.replaceAll(" ", "");
	                	  datafile.setProjectNumber(projectNumber);
	                	  if(projectNumberNoSpace.equals("")) {
		        				message = "Project Number Tidak Boleh Kosong";
	                	  }
	                    break;

	                  case 2:
	                	  String projectName = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  projectNameDistinct = projectName;
	                	 String projectNameNoSpace = projectName.replaceAll(" ", "");
	                	 datafile.setProjectName(projectName);
	                	 if(projectNameNoSpace.equals("")) {
		        				message = "Project Name Tidak Boleh Kosong";
	                	  }
	                    break;

	                  case 3:
	                	  String customerCode = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String customerCodeNoSpace = customerCode.replaceAll(" ", "");
	                	  datafile.setCustomerCode(customerCode);
	                	  if(customerCodeNoSpace.equals("")) {
//		        				message = "Customer Code Tidak Boleh Kosong";
	                	  }else {
//	                		  if(mapCustomerCode.get(customerCode) != null) {
//	                			  message = "Cust Code Tidak Boleh Sama";
//	                		  }else 
	                		  if(mapCustomerCodeExistInDB.get(customerCode) != null){
	                			  message = "Cust Code ("+customerCode+") Sudah Terdaftar Di Datababse";
	                		  }else {
	                			  boolean flagCustCodeNotExistInDB = customerService.getCustomerByCustomerCode(customerCode, idcompany, idbranch) == null?true:false;
	                			  if(flagCustCodeNotExistInDB) {
	                				  custCodeCheck = customerCode;
//	                				  mapCustomerCode.put(customerCode, customerCode);
	                			  }else {
	                				  
	                				  //dimasukan ke hash, karena jika menemukan kode yg sama, tidak perlu cek ke db lagi
	                				  mapCustomerCodeExistInDB.put(customerCode, customerCode);
	                				  message = "Cust Code ("+customerCode+") Sudah Terdaftar Di Datababse";
	                			  }
	                			  
	                		  }
	                	  }
	                	  
	                    break;
	                    
	                  case 4:
	                	  String nama = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  datafile.setNama(nama);
	                	  String namaNoSpace = nama.replaceAll(" ", "");
	                	  custNameCheck = nama;
	                	  if(namaNoSpace.equals("")) {
		        				message = "Nama Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 5:
	                	  String contactPerson = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String contactPersonNoSpace = contactPerson.replaceAll(" ", "");
	                	  datafile.setContactPerson(contactPerson);
	                	  if(contactPersonNoSpace.equals("")) {
		        				message = "Contact Person Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 6:
	                	  String contactNumber = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String contactNumberNoSpace = contactNumber.replaceAll(" ", "");
	                	  datafile.setContactNumber(contactNumber);
	                	  if(contactNumberNoSpace.equals("")) {
		        				message = "Contact Number Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 7:
	                	  String address = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String addressNoSpace = address.replaceAll(" ", "");
	                	  datafile.setAddress(address);
	                	  if(addressNoSpace.equals("")) {
		        				message = "Address Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 8:
	                	  String provinsi = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String provinsiNoSpace = provinsi.replaceAll(" ", "");
	                	  datafile.setProvinsi(provinsi);
	                	  if(provinsiNoSpace.equals("")) {
		        				message = "provinsi Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 9:
	                	  String city = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String cityNoSpace = city.replaceAll(" ", "");
	                	  datafile.setCity(city);
	                	  if(cityNoSpace.equals("")) {
		        				message = "city Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 10:
	                	  String area = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String areaNoSpace = area.replaceAll(" ", "");
	                	  datafile.setArea(area);
	                	  if(areaNoSpace.equals("")) {
		        				message = "area Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 11:
	                	  String subArea = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  String subAreaNoSpace = subArea.replaceAll(" ", "");
	                	  datafile.setSubArea(subArea);
	                	  if(subAreaNoSpace.equals("")) {
		        				message = "subArea Tidak Boleh Kosong";
	                	  }
	                   break;
	                   
	                  case 12:
	                	  String latitude = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  datafile.setLatitude(latitude);
	                   break;
	                   
	                  case 13:
	                	  String longitude = getValueColumn(currentCell);//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
	                	  datafile.setLongitude(longitude);
	                   break;

		              default:
		                break;
		              }
		        	cellIdx++;
		        	
		        	if(!projectNameDistinct.equals("") && !projectNumberDistinct.equals("")) {
		        		String projectNumberDistinctNoSpace = projectNumberDistinct.replaceAll(" ", "");
		        		if(mapDistinctProject.get(projectNumberDistinctNoSpace) == null) {
		        			BodyProject bodyproject = new BodyProject();
		        			bodyproject.setDescription(projectNameDistinct);
		        			bodyproject.setNama(projectNameDistinct);
		        			bodyproject.setProjectnumber(projectNumberDistinct);
		        			mapDistinctProject.put(projectNumberDistinctNoSpace, bodyproject);
		        			
		        			projectNameDistinct = "";
		        			projectNumberDistinct = "";
		        		}
		        	}
		        	
		        	if(!custCodeCheck.equals("") && !custNameCheck.equals("")) {
		        		String mapsCustGet = mapCustomerCode.get(custCodeCheck);
		        		if(mapsCustGet != null) {
		        			if(!mapsCustGet.equals(custNameCheck)) {
		        				message = "Cust Code ("+custCodeCheck+") Tidak Boleh Sama";
		        			}
		        		}else {
		        			mapCustomerCode.put(custCodeCheck, custNameCheck);
		        		}
		        		custCodeCheck = "";
		        		custNameCheck = "";
		        	}
		        	if(!message.equals("")) {
		        		ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.FILE_EXCEL_FAILED,message);
						validations.add(msg);
						break;
		        	}
	        	}
	        	if(!callplanNameCheck.equals("")) {
	        		listDataFile.add(datafile);
	        		callplanNameCheck = "";
	        	}
	        	
	        }else {
	        	message = "Template Tidak Sesuai, Cek Kembali";
				ValidationDataMessage msg = new ValidationDataMessage(ConstansCodeMessage.FILE_EXCEL_FAILED,message);
				validations.add(msg);
	        }
	        
	        if(validations.size() > 0) {
	        	break;
	        }
		}
		
		ReturnDataCustomerCallPlan data = new ReturnDataCustomerCallPlan();
		data.setValidations(validations);
		data.setListDataFile(listDataFile);
		data.setMapDistinctProject(mapDistinctProject);
		data.setMapDistinctCallPlan(mapDistinctCallPlan);
		return data;
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
	
	private String getValueColumn(Cell currentCell){
		String value = "";
		//currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
		if(currentCell.getCellType() == CellType.STRING) {
			value = currentCell.getStringCellValue() != null?currentCell.getStringCellValue():"";
		}else if(currentCell.getCellType() == CellType.NUMERIC) {
			value = String.valueOf(currentCell.getNumericCellValue()) != null ?String.valueOf(currentCell.getNumericCellValue()):"";
		}
		return value;
	}
	
	//Cell currentCell

}
