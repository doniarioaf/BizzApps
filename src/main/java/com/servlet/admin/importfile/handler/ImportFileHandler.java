package com.servlet.admin.importfile.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

}
