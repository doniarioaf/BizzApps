package com.servlet.report.handler;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.DistrictData;
import com.servlet.address.service.DistrictService;
import com.servlet.report.entity.Manggala_BodyReportBongkarMuatDanDepo;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.service.ReportServiceManggala;
import com.servlet.shared.ConstantReportName;
import com.servlet.workorder.entity.DetailWorkOrderData;
import com.servlet.workorder.entity.ParamWoReport;
import com.servlet.workorder.entity.WorkOrderData;
import com.servlet.workorder.service.WorkOrderService;

@Service
public class ReportHandlerManggala implements ReportServiceManggala{
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private DistrictService districtService;
	
	@Override
	public ReportWorkBookExcel getReportBongkarMuatDanDepo(Manggala_BodyReportBongkarMuatDanDepo body, long idcompany,
			long idbranch) {
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		ParamWoReport param = new ParamWoReport();
		param.setIdcompany(idcompany);
		param.setIdbranch(idbranch);
		param.setFromDate(body.getFromDate());
		param.setToDate(body.getToDate());
		param.setReportName(ConstantReportName.BONGKARMUATDEPO);
		List<WorkOrderData> listWO = workOrderService.getListDataWoForReport(param);
		
		XSSFSheet sheet = workbook.createSheet("Bongkar Muat Dan Depo");
		sheet.setDefaultColumnWidth(1000);
		
		CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        
        int rowcount = 1;
        Row row = sheet.createRow(rowcount);
        
        createCell(row, 0, "No. WO", style,sheet);
        createCell(row, 1, "Tanggal WO", style,sheet);
        createCell(row, 2, "No. Surat Jalan", style,sheet);
        createCell(row, 3, "Nomor AJU", style,sheet);
        createCell(row, 4, "Nama Customer", style,sheet);
        createCell(row, 5, "Exportir", style,sheet);
        createCell(row, 6, "Importir", style,sheet);
        createCell(row, 7, "Jenis WO", style,sheet);
        createCell(row, 8, "Origin Port", style,sheet);
        createCell(row, 9, "Destination Port", style,sheet);
        createCell(row, 10, "ETD", style,sheet);
        createCell(row, 11, "ETA", style,sheet);
        createCell(row, 12, "Ves/Voy", style,sheet);
        createCell(row, 13, "No. BL", style,sheet);
        createCell(row, 14, "Tanggal NPE/SPPB", style,sheet);
        createCell(row, 15, "No. Kontainer", style,sheet);
        createCell(row, 16, "Jenis Kontainer", style,sheet);
        createCell(row, 17, "Area Kirim (Kecamatan)", style,sheet);
        createCell(row, 18, "Depo", style,sheet);
        createCell(row, 19, "Tanggal bongkar/muat di pabrik", style,sheet);
        createCell(row, 20, "Lembur / Tidak", style,sheet);
        createCell(row, 21, "No. Mobil", style,sheet);
        createCell(row, 22, "Nama Supir", style,sheet);
        createCell(row, 23, "Status Mobil", style,sheet);
        createCell(row, 24, "Status WO", style,sheet);
        
		HashMap<String, String> kodeposMappingKecamatan = new HashMap<String, String>();
		if(listWO != null && listWO.size() > 0) {
			rowcount = 2;
			font.setBold(false);
			font.setFontHeight(10);
			
//			CellStyle styleData = workbook.createCellStyle();
//	        XSSFFont fontData = workbook.createFont();
//	        fontData.setFontHeight(14);
//	        styleData.setFont(font);
	        
			for(WorkOrderData wodata : listWO) {
				String districtName = "";
				if(wodata.getKodeposCustomer() != null) {
					String mapKodepos = kodeposMappingKecamatan.get(wodata.getKodeposCustomer());
					if(mapKodepos == null) {
						List<DistrictData> listDistrict = districtService.getListDistrictByPostalCode(new Long(wodata.getKodeposCustomer()).longValue());
						if(listDistrict.size() > 0) {
							districtName = listDistrict.get(0).getDis_name();
							kodeposMappingKecamatan.put(wodata.getKodeposCustomer(), districtName);
						}
					}else {
						districtName = mapKodepos;
					}
					
				}
				List<DetailWorkOrderData> listCont = workOrderService.getListContainerByIdWorkOrderForReport(idcompany,idbranch,wodata.getId());
				if(listCont != null && listCont.size() > 0) {
					for(DetailWorkOrderData detailWO : listCont) {
						Row rowData = sheet.createRow(rowcount++);
						int columnCount = 0;
						createCell(rowData, columnCount++, wodata.getNodocument(), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getTanggal(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getNodocument(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getNoaju(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getNamaCustomer(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getEksportirname(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getImportirname(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getJeniswoCodeName(),wodata.getJeniswo()), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getPortasalname(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getPorttujuan(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getEtd(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getEta(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getVoyagenumber(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getNobl(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getTanggalsppb_npe(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getNocontainer(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getPartainame(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(districtName,""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getDepo(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(detailWO.getWoSuratJalan().getTanggalkembali(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getLembur(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getNoPolisi(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getNamaSupir(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getKepemilikanmobil(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getStatusCodeName(),""), style,sheet);
					}
				}
			}
		}
		
		data.setWorkbook(workbook);
		return data;
	}
	
	private Object checkNull(Object value,Object defaultval) {
		return value != null?value:defaultval;
	}
	private Object checkNullDate(Date value,Object defaultval) {
		return value != null?convertDate(value,"dd-MMM-yyyy"):defaultval;
	}
	private String convertDate(Date value,String format) {
		java.util.Date dt = new java.util.Date(value.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
//		sdf.applyPattern(NEW_FORMAT);
		return sdf.format(dt);
	}
	
	
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style,XSSFSheet sheet) {
        sheet.autoSizeColumn(columnCount);
        
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof Date) {
            cell.setCellValue((java.util.Date) value);
        }else if (value instanceof Timestamp) {
            cell.setCellValue((Timestamp) value);
        }else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }else {
        	String textval = (String) value;
        	int numberOfLines = textval.split("\n").length;
        	row.setHeightInPoints((2+numberOfLines) * sheet.getDefaultRowHeightInPoints());
        	style.setWrapText(true);
            cell.setCellValue(textval);
        }
        cell.setCellStyle(style);
    }

}
