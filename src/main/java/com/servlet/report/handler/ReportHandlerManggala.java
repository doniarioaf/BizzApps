package com.servlet.report.handler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servlet.address.entity.City;
import com.servlet.address.entity.District;
import com.servlet.address.entity.DistrictData;
import com.servlet.address.repo.DistrictRepo;
import com.servlet.address.service.CityService;
import com.servlet.address.service.DistrictService;
import com.servlet.asset.entity.Asset;
import com.servlet.asset.entity.AssetData;
import com.servlet.asset.entity.HistoryAssetMappingData;
import com.servlet.asset.repo.AssetRepo;
import com.servlet.asset.service.AssetService;
import com.servlet.bankaccount.entity.BankAccountData;
import com.servlet.bankaccount.service.BankAccountService;
import com.servlet.employeemanggala.entity.EmployeeManggala;
import com.servlet.employeemanggala.repo.EmployeeManggalaRepo;
import com.servlet.employeemanggala.service.EmployeeManggalaService;
import com.servlet.invoice.entity.InvoiceData;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.mapping.entity.MappingData;
import com.servlet.mapping.service.MappingService;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;
import com.servlet.penerimaankasbank.service.PenerimaanKasBankService;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;
import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;
import com.servlet.pengluarankasbank.service.PengeluaranKasBankService;
import com.servlet.report.entity.EntityHelperKasBank;
import com.servlet.report.entity.HistoryTruckTemplate;
import com.servlet.report.entity.ManggalaStatusInvoice;
import com.servlet.report.entity.Manggala_BodyReportBongkarMuatDanDepo;
import com.servlet.report.entity.ParamReportManggala;
import com.servlet.report.entity.ReportSummaryKegiatanTructTemplate;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.service.ReportServiceManggala;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.ConstantReportName;
import com.servlet.shared.GlobalFunc;
import com.servlet.suratjalan.entity.SuratJalanData;
import com.servlet.suratjalan.service.SuratJalanService;
import com.servlet.user.entity.UserPermissionData;
import com.servlet.user.service.UserAppsService;
import com.servlet.workorder.entity.DetailWorkOrderData;
import com.servlet.workorder.entity.ParamWoReport;
import com.servlet.workorder.entity.WorkOrderData;
import com.servlet.workorder.service.WorkOrderService;

@Service
public class ReportHandlerManggala implements ReportServiceManggala{
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private PenerimaanKasBankService penerimaanKasBankService;
	@Autowired
	private PengeluaranKasBankService pengeluaranKasBankService;
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private AssetService assetService;
	@Autowired
	private AssetRepo assetRepo;
	@Autowired
	private EmployeeManggalaService employeeManggalaService;
	@Autowired
	private EmployeeManggalaRepo employeeManggalaRepo;
	@Autowired
	private SuratJalanService suratJalanService;
	@Autowired
	private DistrictRepo districtRepo;
	@Autowired
	private MappingService mappingservice;
	@Autowired
	private UserAppsService userAppsService;
	@Autowired
	private CityService cityService;
	
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
        
        
        String dateFrom = "";
		try {
			dateFrom = GlobalFunc.getDateLongToString(body.getFromDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String dateThru = "";
		try {
			dateThru = GlobalFunc.getDateLongToString(body.getToDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Row rowTitle = sheet.createRow(1);
        createCell(rowTitle, 0, "Laporan Bongkar Muat Dan Depo", style,sheet);
        Row rowPeriode = sheet.createRow(2);
        createCell(rowPeriode, 0, "Periode", style,sheet);
        createCell(rowPeriode, 1, dateFrom+" s/d "+dateThru, style,sheet);
        
        int rowcount = 4;
        Row row = sheet.createRow(rowcount);
        
        createCell(row, 0, "No. WO", style,sheet);
        createCell(row, 1, "Tanggal WO", style,sheet);
        createCell(row, 2, "No. Surat Jalan", style,sheet);
        createCell(row, 3, "Nomor AJU", style,sheet);
        createCell(row, 4, "Nama Customer", style,sheet);
        createCell(row, 5, "Exportir", style,sheet);
        createCell(row, 6, "Importir", style,sheet);
        createCell(row, 7, "Jenis WO", style,sheet);
        createCell(row, 8, "Nama Barang", style,sheet);
        createCell(row, 9, "Origin Port", style,sheet);
        createCell(row, 10, "Destination Port", style,sheet);
        createCell(row, 11, "ETD", style,sheet);
        createCell(row, 12, "ETA", style,sheet);
        createCell(row, 13, "Ves/Voy", style,sheet);
        createCell(row, 14, "No. BL", style,sheet);
        createCell(row, 15, "Tanggal NPE/SPPB", style,sheet);
        createCell(row, 16, "No. Kontainer", style,sheet);
        createCell(row, 17, "Jenis Kontainer", style,sheet);
        createCell(row, 18, "Area Kirim (Kecamatan)", style,sheet);
        createCell(row, 19, "Depo", style,sheet);
        createCell(row, 20, "Tanggal bongkar/muat di pabrik", style,sheet);
        createCell(row, 21, "Lembur / Tidak", style,sheet);
        createCell(row, 22, "No. Mobil", style,sheet);
        createCell(row, 23, "Nama Supir", style,sheet);
        createCell(row, 24, "Status Mobil", style,sheet);
        createCell(row, 25, "Vendor", style,sheet);
        createCell(row, 26, "Status WO", style,sheet);
        
		HashMap<String, String> kodeposMappingKecamatan = new HashMap<String, String>();
		HashMap<Long, String> kodeposMappingCity = new HashMap<Long, String>();
		if(listWO != null && listWO.size() > 0) {
			rowcount = 5;
			font.setBold(false);
			font.setFontHeight(9);
			
//			CellStyle styleData = workbook.createCellStyle();
//	        XSSFFont fontData = workbook.createFont();
//	        fontData.setFontHeight(14);
//	        styleData.setFont(font);
	        
			for(WorkOrderData wodata : listWO) {
//				String districtName = "";
//				String cityName = "";
//				if(wodata.getKodeposCustomer() != null) {
//					String mapKodepos = kodeposMappingKecamatan.get(wodata.getKodeposCustomer());
//					if(mapKodepos == null) {
//						List<DistrictData> listDistrict = districtService.getListDistrictByPostalCode(new Long(wodata.getKodeposCustomer()).longValue());
//						if(listDistrict.size() > 0) {
//							DistrictData distData = listDistrict.get(0);
//							districtName = distData.getDis_name();
//							
//							
//							String mapCity = kodeposMappingCity.get(distData.getCity_id());
//							if(mapCity == null) {
//								City city = cityService.getById(distData.getCity_id());
//								if(city != null) {
//									cityName = city.getCity_name();
//									kodeposMappingCity.put(distData.getCity_id(), cityName);
//									cityName = ", "+city.getCity_name();
//								}
//							}else {
//								cityName = ", "+mapCity;
//							}
//							
//							districtName += cityName;
//							kodeposMappingKecamatan.put(wodata.getKodeposCustomer(), districtName);
//						}
//					}else {
//						districtName = mapKodepos;
//					}
//					
//				}
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
						createCell(rowData, columnCount++, checkNull(wodata.getNamacargo(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getPortasalname(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getPorttujuanname(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getEtd(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getEta(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getVoyagenumber(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getNobl(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getTanggalsppb_npe(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getNocontainer(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getPartainame(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull( (detailWO.getWoSuratJalan().getWarehouseKecamatan() != null? detailWO.getWoSuratJalan().getWarehouseKecamatan()+", "+detailWO.getWoSuratJalan().getWarehouseCity():"" ),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getDepo(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(detailWO.getWoSuratJalan().getTanggalkembali(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getLembur(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getNoPolisi(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getNamaSupir(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getKepemilikanmobil(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(detailWO.getWoSuratJalan().getVendormobilname(),""), style,sheet);
						createCell(rowData, columnCount++, checkNull(wodata.getStatus(),""), style,sheet);
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
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style,XSSFSheet sheet,int widthColumn) {
//        sheet.autoSizeColumn(columnCount);
        sheet.setColumnWidth(columnCount, widthColumn);
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
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
        	String textval = (String) value;
//        	int numberOfLines = textval.split("\n").length;
//        	row.setHeightInPoints((2+numberOfLines) * sheet.getDefaultRowHeightInPoints());
//        	style.setWrapText(true);
            cell.setCellValue(textval);
        }
        cell.setCellStyle(style);
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
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else {
        	String textval = (String) value;
//        	int numberOfLines = textval.split("\n").length;
//        	row.setHeightInPoints((2+numberOfLines) * sheet.getDefaultRowHeightInPoints());
//        	style.setWrapText(true);
            cell.setCellValue(textval);
        }
        cell.setCellStyle(style);
    }

	@Override
	public ReportWorkBookExcel getReportStatusInvoice(ManggalaStatusInvoice body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		ParamWoReport param = new ParamWoReport();
		param.setIdcompany(idcompany);
		param.setIdbranch(idbranch);
		param.setFromDate(body.getFromDate());
		param.setToDate(body.getToDate());
		param.setStatus(body.getStatus() != null?body.getStatus():"");
		param.setReportName(ConstantReportName.STATUSINVOICE);
		List<WorkOrderData> listWO = workOrderService.getListDataWoForReport(param);
		
		XSSFDataFormat format = workbook.createDataFormat();
//		format.getFormat("#,###.##");
		XSSFSheet sheet = workbook.createSheet("Laporan Status Invoice");
		sheet.setDefaultColumnWidth(1000);
		
		CellStyle style = workbook.createCellStyle();
		CellStyle styleAmount = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        styleAmount.setFont(font);
		
        String dateFrom = "";
		try {
			dateFrom = GlobalFunc.getDateLongToString(body.getFromDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String dateThru = "";
		try {
			dateThru = GlobalFunc.getDateLongToString(body.getToDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Row rowTitle = sheet.createRow(1);
        createCell(rowTitle, 0, "Laporan Status Invoice", style,sheet);
        Row rowPeriode = sheet.createRow(2);
        createCell(rowPeriode, 0, "Periode", style,sheet);
        createCell(rowPeriode, 1, dateFrom+" s/d "+dateThru, style,sheet);
        Row rowStatus = sheet.createRow(3);
        createCell(rowStatus, 0, "Status", style,sheet);
        createCell(rowStatus, 1, body.getStatus(), style,sheet);
        
        int rowcount = 5;
        Row row = sheet.createRow(rowcount);
        
        createCell(row, 0, "No. WO", style,sheet);
        createCell(row, 1, "Tanggal WO", style,sheet);
        createCell(row, 2, "Nomor AJU", style,sheet);
        createCell(row, 3, "Nama Customer", style,sheet);
        createCell(row, 4, "Jenis WO", style,sheet);
        createCell(row, 5, "Origin Port", style,sheet);
        createCell(row, 6, "Destination Port", style,sheet);
        createCell(row, 7, "ETD", style,sheet);
        createCell(row, 8, "ETA", style,sheet);
        createCell(row, 9, "No. BL", style,sheet);
        createCell(row, 10, "Tanggal NPE/SPPB", style,sheet);
        createCell(row, 11, "Jumlah Partai", style,sheet);
        createCell(row, 12, "Lembur/Tidak", style,sheet);
        createCell(row, 13, "Status WO", style,sheet);
        createCell(row, 14, "No. Invoice", style,sheet);
        createCell(row, 15, "Tanggal Invoice", style,sheet);
        createCell(row, 16, "Invoice Value", style,sheet);
        createCell(row, 17, "Tanggal Bayar", style,sheet);
        createCell(row, 18, "Bank", style,sheet);
        createCell(row, 19, "No Voucher", style,sheet);
        createCell(row, 20, "Lama Pembayaran", style,sheet);
        
        if(listWO != null && listWO.size() > 0) {
        	rowcount = 6;
			font.setBold(false);
			font.setFontHeight(9);
			
			for(WorkOrderData wodata : listWO) {
				List<DetailWorkOrderData> listDetailWO = workOrderService.getListContainerByIdWorkOrderForReportStatusInvoice(idcompany, idbranch, wodata.getId());
				List<InvoiceData> listinv = invoiceService.getListInvoiceByIdWo(idcompany, idbranch, wodata.getId());
				if(listinv != null && listinv.size() > 0) {
					for(InvoiceData inv : listinv) {
						Row rowData = sheet.createRow(rowcount++);
						int columnCount = 0;
						createCell(rowData, columnCount++, wodata.getNodocument(), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getTanggal(),""), style,sheet);
						createCell(rowData, columnCount++, wodata.getNoaju(), style,sheet);
						createCell(rowData, columnCount++, wodata.getNamaCustomer(), style,sheet);
						createCell(rowData, columnCount++, wodata.getJeniswo(), style,sheet);
						createCell(rowData, columnCount++, wodata.getPortasalname(), style,sheet);
						createCell(rowData, columnCount++, wodata.getPorttujuanname(), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getEtd(),""), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getEta(),""), style,sheet);
						createCell(rowData, columnCount++, wodata.getNobl(), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(wodata.getTanggalsppb_npe(),""), style,sheet);
						createCell(rowData, columnCount++, listDetailWO.size(), style,sheet);
						if(listDetailWO != null && listDetailWO.size() > 0) {
							//lembur
							String lembur = "-";
							if(listDetailWO.get(0).getWoSuratJalan().getLembur() != null) {
								if(listDetailWO.get(0).getWoSuratJalan().getLembur().equals("Y")) {
									lembur = "Yes";
								}else {
									lembur = "No";
								}
							}
							createCell(rowData, columnCount++, lembur, style,sheet);
						}else {
							//lembur
							createCell(rowData, columnCount++, "-", style,sheet);
						}
						
						createCell(rowData, columnCount++, wodata.getStatus(), style,sheet);
						createCell(rowData, columnCount++, inv.getNodocument(), style,sheet);
						createCell(rowData, columnCount++, checkNullDate(inv.getTanggal(),""), style,sheet);
						
						// -1 lebih rendah, 0  sama dengan, 1 lebih tinggi
						int compare = new BigDecimal(inv.getTotalinvoice()).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(inv.getTotalinvoice()));
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat("#,###"));
						}else {
							styleAmount.setDataFormat(format.getFormat("#,###.##"));
						}
						
						
						createCell(rowData, columnCount++, inv.getTotalinvoice(), styleAmount,sheet,7000);
						
						List<PenerimaanKasBankData> listpenerimaan = penerimaanKasBankService.getListByDetailIdInvoiceJoinBank(idcompany,idbranch,inv.getId());
						if(listpenerimaan != null && listpenerimaan.size() > 0) {
							PenerimaanKasBankData penerimaan = listpenerimaan.get(0);
							createCell(rowData, columnCount++, checkNullDate(penerimaan.getReceivedate(),""), style,sheet);
							createCell(rowData, columnCount++, penerimaan.getBankName(), style,sheet);
							createCell(rowData, columnCount++, penerimaan.getNodocument(), style,sheet);
							if(inv.getTanggal() != null && penerimaan.getReceivedate() != null) {
								createCell(rowData, columnCount++, GlobalFunc.getDiffDate(inv.getTanggal().getTime(), penerimaan.getReceivedate().getTime()), style,sheet);
							}else {
								createCell(rowData, columnCount++, "-", style,sheet);
							}
						}else {
							createCell(rowData, columnCount++, "-", style,sheet);
							createCell(rowData, columnCount++, "-", style,sheet);
							createCell(rowData, columnCount++, "-", style,sheet);
							createCell(rowData, columnCount++, "-", style,sheet);
						}
						
					}
				}else {
					Row rowData = sheet.createRow(rowcount++);
					int columnCount = 0;
					createCell(rowData, columnCount++, wodata.getNodocument(), style,sheet);
					createCell(rowData, columnCount++, checkNullDate(wodata.getTanggal(),""), style,sheet);
					createCell(rowData, columnCount++, wodata.getNoaju(), style,sheet);
					createCell(rowData, columnCount++, wodata.getNamaCustomer(), style,sheet);
					createCell(rowData, columnCount++, wodata.getJeniswo(), style,sheet);
					createCell(rowData, columnCount++, wodata.getPortasalname(), style,sheet);
					createCell(rowData, columnCount++, wodata.getPorttujuanname(), style,sheet);
					createCell(rowData, columnCount++, checkNullDate(wodata.getEtd(),""), style,sheet);
					createCell(rowData, columnCount++, checkNullDate(wodata.getEta(),""), style,sheet);
					createCell(rowData, columnCount++, wodata.getNobl(), style,sheet);
					createCell(rowData, columnCount++, checkNullDate(wodata.getTanggalsppb_npe(),""), style,sheet);
					createCell(rowData, columnCount++, listDetailWO.size(), style,sheet);
					if(listDetailWO != null && listDetailWO.size() > 0) {
						//lembur
						String lembur = "-";
						if(listDetailWO.get(0).getWoSuratJalan().getLembur() != null) {
							if(listDetailWO.get(0).getWoSuratJalan().getLembur().equals("Y")) {
								lembur = "Yes";
							}else {
								lembur = "No";
							}
						}
						createCell(rowData, columnCount++, lembur, style,sheet);
					}else {
						//lembur
						createCell(rowData, columnCount++, "-", style,sheet);
					}
					
					createCell(rowData, columnCount++, wodata.getStatus(), style,sheet);
					createCell(rowData, columnCount++, "-", style,sheet);
					createCell(rowData, columnCount++, "-", style,sheet);
					createCell(rowData, columnCount++, "-", style,sheet);
					createCell(rowData, columnCount++, "-", style,sheet);
					createCell(rowData, columnCount++, "-", style,sheet);
					createCell(rowData, columnCount++, "-", style,sheet);
					createCell(rowData, columnCount++, "-", style,sheet);
					
				}
				
			}
        	
        }
        
        data.setWorkbook(workbook);
		return data;
	}

	@Override
	public ReportWorkBookExcel getReportKasBank(ParamReportManggala body, long idcompany, long idbranch,long iduser) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFDataFormat format = workbook.createDataFormat();
		
		XSSFSheet sheet = workbook.createSheet("Laporan Kas Bank");
		sheet.setDefaultColumnWidth(1000);
		
		CellStyle style = workbook.createCellStyle();
		CellStyle styleAmount = workbook.createCellStyle();
		CellStyle styleInteger = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        styleAmount.setFont(font);
        
        String formatAmount = "#,###.00";
		
        String dateFrom = "";
		try {
			dateFrom = GlobalFunc.getDateLongToString(body.getFromDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String dateThru = "";
		try {
			dateThru = GlobalFunc.getDateLongToString(body.getToDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Timestamp tsDateBeforeFrom = null;
		try {
			tsDateBeforeFrom = GlobalFunc.addDays(new Timestamp(body.getFromDate()) ,-1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Date dtDateBeforeFrom = new Date(tsDateBeforeFrom.getTime());
		
		long idbank = body.getIdbank() != null?body.getIdbank():0;
		if(idbank != 0) {
			boolean checkFinanceJunior = checkFinanceJunior(iduser);
		}
		BankAccountData bankData = bankAccountService.getByIdForReport(idcompany, idbranch, idbank);
		double saldoAwalBank = 0.0;
		if(bankData != null) {
			boolean checkFinanceJunior = checkFinanceJunior(iduser);
			if(checkFinanceJunior) {
				if(!bankData.isShowfinancejunior()) {
					idbank = 0;
				}
			}
			
			if(bankData.getSaldoawal() != null && idbank != 0) {
				saldoAwalBank = bankData.getSaldoawal();
			}
		}
		
		double totalSaldoAwal = 0.0;
		if(idbank != 0) {
			//56169461 = 01-Jan-70
			Double saldoPenerimaan = penerimaanKasBankService.summaryAmountPenerimaanByDate(idcompany, idbranch, new Date(56169461L), dtDateBeforeFrom, null,idbank);
			saldoPenerimaan = saldoPenerimaan != null? saldoPenerimaan:0.0;
			Double saldoPengeluaraan = pengeluaranKasBankService.summaryAmountPengeluaranByDate(idcompany, idbranch, new Date(56169461L), dtDateBeforeFrom, null,idbank);
			saldoPengeluaraan = saldoPengeluaraan != null ? saldoPengeluaraan:0.0;
			totalSaldoAwal = saldoAwalBank + saldoPenerimaan - saldoPengeluaraan;
		}
		 
        Row rowTitle = sheet.createRow(1);
        createCell(rowTitle, 0, "Laporan Kas/Bank", style,sheet);
        Row rowPeriode = sheet.createRow(2);
        createCell(rowPeriode, 0, "Periode", style,sheet);
        createCell(rowPeriode, 1, dateFrom+" s/d "+dateThru, style,sheet);
        Row rowStatus = sheet.createRow(3);
        createCell(rowStatus, 0, "Bank", style,sheet);
        createCell(rowStatus, 1, bankData != null?bankData.getNamabank():"" , style,sheet);
        int rowcount = 5;
        Row row = sheet.createRow(rowcount);
        
        createCell(row, 0, "Tanggal Transaksi", style,sheet);
        createCell(row, 1, "No. Voucher", style,sheet);
        createCell(row, 2, "Transaksi", style,sheet);
        createCell(row, 3, "No. WO yang dibayar", style,sheet);
        createCell(row, 4, "Nomor AJU", style,sheet);
        createCell(row, 5, "No. Invoice", style,sheet);
        createCell(row, 6, "Nama Customer", style,sheet);
        createCell(row, 7, "Keterangan", style,sheet);
        createCell(row, 8, "Uang Masuk", style,sheet);
        createCell(row, 9, "Uang Keluar", style,sheet);
        createCell(row, 10, "Saldo", style,sheet);
        
//        List<PenerimaanPengeluaranData> list = penerimaanKasBankService.getPenerimaanPengeluaranData(idcompany, idbranch, new Date(body.getFromDate()), new Date(body.getToDate()), body.getIdbank());
//        if(list != null && list.size() > 0) {
        	rowcount = 6;
			font.setBold(false);
			font.setFontHeight(9);
			
			Row rowDataSaldoAwal = sheet.createRow(rowcount++);
			int columnCount = 0;
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "Saldo Awal", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			createCell(rowDataSaldoAwal, columnCount++, "", style,sheet);
			
			// -1 lebih rendah, 0  sama dengan, 1 lebih tinggi
			int compare = new BigDecimal(totalSaldoAwal).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(totalSaldoAwal));
			styleAmount = workbook.createCellStyle();
	        styleAmount.setFont(font);
			if(compare == 0) {
				styleAmount.setDataFormat(format.getFormat(formatAmount));
			}else {
				styleAmount.setDataFormat(format.getFormat(formatAmount));
			}
			
			
			createCell(rowDataSaldoAwal, columnCount++, totalSaldoAwal, styleAmount,sheet,7000);
			
			List<EntityHelperKasBank> listKasBankPenerimaan = penerimaanKasBankService.getDataReportKasBankPenerimaan(idcompany, idbranch, new Date(body.getFromDate()), new Date(body.getToDate()), body.getIdbank());
			List<EntityHelperKasBank> listKasBankPengenluaran = pengeluaranKasBankService.getDataReportKasBankPengeluaran(idcompany, idbranch, new Date(body.getFromDate()), new Date(body.getToDate()), body.getIdbank());
			
			List<EntityHelperKasBank> listKasBankSort = new ArrayList<EntityHelperKasBank>();
			if(listKasBankPenerimaan != null && listKasBankPenerimaan.size() > 0) {
				for(EntityHelperKasBank dataKasBank : listKasBankPenerimaan) {
					EntityHelperKasBank dataKasBankSort = new EntityHelperKasBank();
					if(dataKasBank.getPenerimaanid() != null && dataKasBank.getPenerimaanid() != 0) {
						dataKasBankSort.setPenerimaanid(dataKasBank.getPenerimaanid());
						dataKasBankSort.setTanggalTransaksi(dataKasBank.getPenerimaantanggalTransaksi());
						dataKasBankSort.setPenerimaantanggalTransaksi(dataKasBank.getPenerimaantanggalTransaksi());
						dataKasBankSort.setPenerimaannoVoucher(dataKasBank.getPenerimaannoVoucher());
						dataKasBankSort.setPenerimaancoa(dataKasBank.getPenerimaancoa());
						dataKasBankSort.setPenerimaannoWO(dataKasBank.getPenerimaannoWO());
						dataKasBankSort.setPenerimaannoAju(dataKasBank.getPenerimaannoAju());
						dataKasBankSort.setPenerimaannoInvoice(dataKasBank.getPenerimaannoInvoice());
						dataKasBankSort.setPenerimaannamaCustomer(dataKasBank.getPenerimaannamaCustomer());
						dataKasBankSort.setPenerimaanketerangan(dataKasBank.getPenerimaanketerangan());
						dataKasBankSort.setPenerimaanAmount(dataKasBank.getPenerimaanAmount());
						
						listKasBankSort.add(dataKasBankSort);
					}
				}
			}
			
			if(listKasBankPengenluaran != null && listKasBankPengenluaran.size() > 0) {
				for(EntityHelperKasBank dataKasBank : listKasBankPengenluaran) {
					EntityHelperKasBank dataKasBankSort = new EntityHelperKasBank();
					
					if(dataKasBank.getPengeluaranid() != null && dataKasBank.getPengeluaranid() != 0) {
						dataKasBankSort.setPengeluaranid(dataKasBank.getPengeluaranid());
						dataKasBankSort.setTanggalTransaksi(dataKasBank.getPengeluarantanggalTransaksi());
						dataKasBankSort.setPengeluaran_paymentto(dataKasBank.getPengeluaran_paymentto());
						dataKasBankSort.setPengeluaran_employeename(dataKasBank.getPengeluaran_employeename());
						dataKasBankSort.setPengeluaran_customername(dataKasBank.getPengeluaran_customername());
						dataKasBankSort.setPengeluaran_vendorname(dataKasBank.getPengeluaran_vendorname());
						dataKasBankSort.setPengeluarantanggalTransaksi(dataKasBank.getPengeluarantanggalTransaksi());
						dataKasBankSort.setPengeluarannoVoucher(dataKasBank.getPengeluarannoVoucher());
						dataKasBankSort.setPengeluarancoa(dataKasBank.getPengeluarancoa());
						dataKasBankSort.setPengeluarannoWO(dataKasBank.getPengeluarannoWO());
						dataKasBankSort.setPengeluarannoAju(dataKasBank.getPengeluarannoAju());
						dataKasBankSort.setPengeluarannoInvoice("");
						dataKasBankSort.setPengeluaranketerangan(dataKasBank.getPengeluaranketerangan());
						dataKasBankSort.setPengeluaranAmount(dataKasBank.getPengeluaranAmount());
						dataKasBankSort.setPengeluaran_invItemName(dataKasBank.getPengeluaran_invItemName());
						dataKasBankSort.setPengeluaran_payItemName(dataKasBank.getPengeluaran_payItemName());
						listKasBankSort.add(dataKasBankSort);

					}
				}
			}
			
			
			
			if(listKasBankSort != null && listKasBankSort.size() > 0) {
				Collections.sort(listKasBankSort);
				for(EntityHelperKasBank dataKasBank : listKasBankSort) {
					Row rowData = sheet.createRow(rowcount++);
					columnCount = 0;

					if(
						(dataKasBank.getPenerimaanid() != null && dataKasBank.getPenerimaanid() != 0)
						&&
						(dataKasBank.getPengeluaranid() != null && dataKasBank.getPengeluaranid() != 0)
					) {
						
						createCell(rowData, columnCount++, checkNullDate(dataKasBank.getPenerimaantanggalTransaksi(),""), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannoVoucher(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaancoa(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannoWO(), style,sheet);
						createCell(rowData, columnCount++, (dataKasBank.getPenerimaannoAju() != null && !dataKasBank.getPenerimaannoAju().equals("") ? new Integer(dataKasBank.getPenerimaannoAju()):""), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannoInvoice(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannamaCustomer(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaanketerangan(), style,sheet);
						Double saldoUangMasuk = dataKasBank.getPenerimaanAmount();
						saldoUangMasuk = saldoUangMasuk != null?saldoUangMasuk:0.0;
						
						compare = new BigDecimal(saldoUangMasuk).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldoUangMasuk));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, saldoUangMasuk, styleAmount,sheet,7000);
						createCell(rowData, columnCount++, "", style,sheet);
						
						totalSaldoAwal = totalSaldoAwal + saldoUangMasuk.doubleValue();
						double saldo = totalSaldoAwal;
						compare = new BigDecimal(saldo).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldo));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, saldo, styleAmount,sheet,7000);
						
						// ========== Pengeluaran ===============
						rowData = sheet.createRow(rowcount++);
						columnCount = 0;
						
						String pengeluaranNamaPaymentTo = "";
						if(dataKasBank.getPengeluaran_paymentto().equals("EMPLOYEE")) {
							pengeluaranNamaPaymentTo = dataKasBank.getPengeluaran_employeename();
						}else if(dataKasBank.getPengeluaran_paymentto().equals("CUSTOMER")) {
							pengeluaranNamaPaymentTo = dataKasBank.getPengeluaran_customername();
						}else if(dataKasBank.getPengeluaran_paymentto().equals("VENDOR")) {
							pengeluaranNamaPaymentTo = dataKasBank.getPengeluaran_vendorname();
						}
						
						createCell(rowData, columnCount++, checkNullDate(dataKasBank.getPengeluarantanggalTransaksi(),""), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPengeluarannoVoucher(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPengeluarancoa(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPengeluarannoWO(), style,sheet);
						
				        
						createCell(rowData, columnCount++, (dataKasBank.getPengeluarannoAju() != null && !dataKasBank.getPengeluarannoAju().equals("") ? new Integer(dataKasBank.getPengeluarannoAju()):""), style,sheet);
//						createCell(rowData, columnCount++, dataKasBank.getPengeluarannoAju(), style,sheet);
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, pengeluaranNamaPaymentTo, style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPengeluaranketerangan(), style,sheet);
						
						Double saldoUangKeluar = dataKasBank.getPengeluaranAmount();
						saldoUangKeluar = saldoUangKeluar != null?saldoUangKeluar:0.0;
						compare = new BigDecimal(saldoUangKeluar).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldoUangKeluar));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, saldoUangKeluar, styleAmount,sheet,7000);
						
						totalSaldoAwal = totalSaldoAwal - saldoUangKeluar.doubleValue();
						saldo = totalSaldoAwal;
						
						compare = new BigDecimal(saldo).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldo));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, saldo, styleAmount,sheet,7000);
						
					}else if(dataKasBank.getPenerimaanid() != null && dataKasBank.getPenerimaanid() != 0) {
						createCell(rowData, columnCount++, checkNullDate(dataKasBank.getPenerimaantanggalTransaksi(),""), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannoVoucher(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaancoa(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannoWO(), style,sheet);
//						createCell(rowData, columnCount++, dataKasBank.getPenerimaannoAju(), style,sheet);
						createCell(rowData, columnCount++, (dataKasBank.getPenerimaannoAju() != null && !dataKasBank.getPenerimaannoAju().equals("") ? new Integer(dataKasBank.getPenerimaannoAju()):""), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannoInvoice(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaannamaCustomer(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPenerimaanketerangan(), style,sheet);
						Double saldoUangMasuk = dataKasBank.getPenerimaanAmount();
						saldoUangMasuk = saldoUangMasuk != null?saldoUangMasuk:0.0;
						
						compare = new BigDecimal(saldoUangMasuk).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldoUangMasuk));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, saldoUangMasuk, styleAmount,sheet,7000);
						createCell(rowData, columnCount++, "", style,sheet);
						
						totalSaldoAwal = totalSaldoAwal + saldoUangMasuk.doubleValue();
						double saldo = totalSaldoAwal;
						compare = new BigDecimal(saldo).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldo));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, saldo, styleAmount,sheet,7000);
					}else if(dataKasBank.getPengeluaranid() != null && dataKasBank.getPengeluaranid() != 0) {
						String pengeluaranNamaPaymentTo = "";
						if(dataKasBank.getPengeluaran_paymentto().equals("EMPLOYEE")) {
							pengeluaranNamaPaymentTo = dataKasBank.getPengeluaran_employeename();
						}else if(dataKasBank.getPengeluaran_paymentto().equals("CUSTOMER")) {
							pengeluaranNamaPaymentTo = dataKasBank.getPengeluaran_customername();
						}else if(dataKasBank.getPengeluaran_paymentto().equals("VENDOR")) {
							pengeluaranNamaPaymentTo = dataKasBank.getPengeluaran_vendorname();
						}
						
						createCell(rowData, columnCount++, checkNullDate(dataKasBank.getPengeluarantanggalTransaksi(),""), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPengeluarannoVoucher(), style,sheet);
						
						String transaksiName = "";
						if(dataKasBank.getPengeluaran_invItemName() != null && !dataKasBank.getPengeluaran_invItemName().equals("")) {
							transaksiName = dataKasBank.getPengeluaran_invItemName(); 
						}else if(dataKasBank.getPengeluaran_payItemName() != null && !dataKasBank.getPengeluaran_payItemName().equals("")) {
							transaksiName = dataKasBank.getPengeluaran_payItemName(); 
						} 
						createCell(rowData, columnCount++, transaksiName, style,sheet);
//						createCell(rowData, columnCount++, dataKasBank.getPengeluarancoa(), style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPengeluarannoWO(), style,sheet);
//						createCell(rowData, columnCount++, dataKasBank.getPengeluarannoAju(), style,sheet);
						createCell(rowData, columnCount++, (dataKasBank.getPengeluarannoAju() != null && !dataKasBank.getPengeluarannoAju().equals("") ? new Integer(dataKasBank.getPengeluarannoAju()):""), style,sheet);
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, pengeluaranNamaPaymentTo, style,sheet);
						createCell(rowData, columnCount++, dataKasBank.getPengeluaranketerangan(), style,sheet);
						
						Double saldoUangKeluar = dataKasBank.getPengeluaranAmount();
						saldoUangKeluar = saldoUangKeluar != null?saldoUangKeluar:0.0;
						compare = new BigDecimal(saldoUangKeluar).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldoUangKeluar));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, saldoUangKeluar.doubleValue(), styleAmount,sheet,7000);
						
						totalSaldoAwal = totalSaldoAwal - saldoUangKeluar.doubleValue();
						double saldo = totalSaldoAwal;
						
						compare = new BigDecimal(saldo).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldo));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}else {
							styleAmount.setDataFormat(format.getFormat(formatAmount));
						}
						createCell(rowData, columnCount++, saldo, styleAmount,sheet,7000);
						
					}
				}
			}
        
        
        data.setWorkbook(workbook);
		return data;
	}

	@Override
	public ReportWorkBookExcel getReportLabaRugi(ParamReportManggala body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFDataFormat format = workbook.createDataFormat();
		
		XSSFSheet sheet = workbook.createSheet("Laporan Laba Rugi");
		sheet.setDefaultColumnWidth(1000);
		
		CellStyle style = workbook.createCellStyle();
		CellStyle styleAmount = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        styleAmount.setFont(font);
        
        
        
		
        String dateFrom = "";
		try {
			dateFrom = GlobalFunc.getDateLongToString(body.getFromDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String dateThru = "";
		try {
			dateThru = GlobalFunc.getDateLongToString(body.getToDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BankAccountData bankData = bankAccountService.getByIdForReport(idcompany, idbranch, body.getIdbank());
		
		Row rowTitle = sheet.createRow(1);
        createCell(rowTitle, 0, "Laporan Laba Rugi", style,sheet);
        Row rowPeriode = sheet.createRow(2);
        createCell(rowPeriode, 0, "Periode", style,sheet);
        createCell(rowPeriode, 1, dateFrom+" s/d "+dateThru, style,sheet);
        Row rowStatus = sheet.createRow(3);
        createCell(rowStatus, 0, "Bank", style,sheet);
        createCell(rowStatus, 1, bankData != null?bankData.getNamabank():"All" , style,sheet);
        int rowcount = 5;
        Row row = sheet.createRow(rowcount);
        
        createCell(row, 0, "No. WO", style,sheet);
        createCell(row, 1, "Tanggal WO", style,sheet);
        createCell(row, 2, "No. AJU", style,sheet);
        createCell(row, 3, "Nama Cutomer", style,sheet);
        createCell(row, 4, "Jenis WO", style,sheet);
        createCell(row, 5, "Port", style,sheet);
        createCell(row, 6, "Tanggal Invoice", style,sheet);
        createCell(row, 7, "Total Invoice", style,sheet);
        createCell(row, 8, "Invoice Paid", style,sheet);
        createCell(row, 9, "No. Invoice", style,sheet);
        createCell(row, 10, "Total Reimbursement Fee", style,sheet);
        createCell(row, 11, "Reimbursement Paid", style,sheet);
        createCell(row, 12, "Total Others Fee", style,sheet);
        createCell(row, 13, "Laba/Rugi", style,sheet);
        
        List<WorkOrderData> list = workOrderService.getListDataWoForReportLabaRugi(idcompany, idbranch, body);
        if(list != null && list.size() > 0) {
        	rowcount = 6;
			font.setBold(false);
			font.setFontHeight(9);
			
			Double totalAkhir = 0.0;
			for(WorkOrderData datawo : list) {
				Row rowData = sheet.createRow(rowcount++);
				int columnCount = 0;
				
				createCell(rowData, columnCount++, datawo.getNodocument(), style,sheet);
				createCell(rowData, columnCount++, checkNullDate(datawo.getTanggal(),""), style,sheet);
				createCell(rowData, columnCount++, datawo.getNoaju(), style,sheet);
				createCell(rowData, columnCount++, datawo.getNamaCustomer(), style,sheet);
				createCell(rowData, columnCount++, datawo.getJeniswo(), style,sheet);
				createCell(rowData, columnCount++, datawo.getPorttujuanname(), style,sheet);
				
				String tglInv = "";
				String noInv = "";
				
				List<InvoiceData> listinv = invoiceService.getListInvoiceByIdWo(idcompany,idbranch,datawo.getId());
				int count =0;
				double totalInvoice = 0.0;
				double totalInvoiceReimbursement = 0.0;
				if(listinv != null && listinv.size() > 0) {
					for(InvoiceData inv : listinv) {
						totalInvoice += inv.getTotalinvoice() != null?inv.getTotalinvoice().doubleValue():0.0;
						if(inv.getIdinvoicetype().equals("REIMBURSEMENT")) {
							totalInvoiceReimbursement += inv.getTotalinvoice() != null?inv.getTotalinvoice().doubleValue():0.0;
						}
						count++;
						if(count == listinv.size()){
							tglInv += checkNullDate(inv.getTanggal(),"");
							noInv += inv.getNodocument();
						}else {
							tglInv += checkNullDate(inv.getTanggal(),"")+",";
							noInv += inv.getNodocument()+",";
						}
					}
				}
				createCell(rowData, columnCount++, tglInv, style,sheet);
				
				int compare = new BigDecimal(totalInvoice).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(totalInvoice));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, totalInvoice, styleAmount,sheet,7000);
				
				Double invPaid = penerimaanKasBankService.summaryAmountPenerimaanByIdWO(idcompany, idbranch, new Date(body.getFromDate()), new Date(body.getToDate()), datawo.getId(), body.getIdbank(),"");
				invPaid = invPaid != null?invPaid:0.0;
				
				compare = new BigDecimal(invPaid).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(invPaid));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, invPaid, styleAmount,sheet,7000);
				
				createCell(rowData, columnCount++, noInv, style,sheet);
				
				compare = new BigDecimal(totalInvoiceReimbursement).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(totalInvoiceReimbursement));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, totalInvoiceReimbursement, styleAmount,sheet,7000);
				
				Double reimbursementPaid = penerimaanKasBankService.summaryAmountPenerimaanByIdWO(idcompany, idbranch, new Date(body.getFromDate()), new Date(body.getToDate()), datawo.getId(), body.getIdbank(),"REIMBURSEMENT");
				reimbursementPaid = reimbursementPaid != null?reimbursementPaid:0.0;
				compare = new BigDecimal(reimbursementPaid).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(reimbursementPaid));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, reimbursementPaid, styleAmount,sheet,7000);
				
				Double totalOthersFee = pengeluaranKasBankService.summaryAmountPengeluaranByIdWo(idcompany, idbranch, new Date(body.getFromDate()), new Date(body.getToDate()), datawo.getId(), body.getIdbank());
				createCell(rowData, columnCount++, totalOthersFee, styleAmount,sheet,7000);
				
				Double totalLabaRugi = invPaid - totalInvoiceReimbursement - totalOthersFee;
				totalAkhir += totalLabaRugi;
				createCell(rowData, columnCount++, totalLabaRugi, styleAmount,sheet,7000);
				
	        }
			Row rowData = sheet.createRow(rowcount++);
			createCell(rowData, 13, totalAkhir, styleAmount,sheet,7000);
        }
        
        data.setWorkbook(workbook);
		return data;
	}

	@Override
	public ReportSummaryKegiatanTructTemplate getSummaryKegiatanTructTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportSummaryKegiatanTructTemplate template = new ReportSummaryKegiatanTructTemplate();
		template.setAssetOptions(assetService.getListAssetByAssetType(idcompany,idbranch,"KEPALA",null));
		template.setDriverOptions(employeeManggalaService.getListEmployeeSupir(idcompany,idbranch));
		return template;
	}

	@Override
	public ReportWorkBookExcel getReportSummaryKegiatanTruck(ParamReportManggala body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFDataFormat format = workbook.createDataFormat();
		
		XSSFSheet sheet = workbook.createSheet("Laporan Summary Kegiatan Truck");
		sheet.setDefaultColumnWidth(1000);
		
		CellStyle style = workbook.createCellStyle();
		CellStyle styleAmount = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        styleAmount.setFont(font);
        
        String dateFrom = "";
		try {
			dateFrom = GlobalFunc.getDateLongToString(body.getFromDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String dateThru = "";
		try {
			dateThru = GlobalFunc.getDateLongToString(body.getToDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String asset = "All";
		if(body.getIdAsset() != null) {
			Asset assetOpt = assetRepo.getById(body.getIdAsset());
			if(assetOpt != null) {
				asset = assetOpt.getKepala_nopolisi();
			}
			
		}
		
		String driver = "All";
		if(body.getIdEmployee() != null) {
			EmployeeManggala empOpt = employeeManggalaRepo.getById(body.getIdEmployee());
			if(empOpt != null) {
				driver = empOpt.getNama();
			}
			
		}
		
		
		Row rowTitle = sheet.createRow(1);
        createCell(rowTitle, 0, "Laporan Summary Kegiatan Truck", style,sheet);
        Row rowPeriode = sheet.createRow(2);
        createCell(rowPeriode, 0, "Periode", style,sheet);
        createCell(rowPeriode, 1, dateFrom+" s/d "+dateThru, style,sheet);
        Row rowStatus = sheet.createRow(3);
        createCell(rowStatus, 0, "No Truck", style,sheet);
        createCell(rowStatus, 1, asset , style,sheet);
        Row rowDriver = sheet.createRow(4);
        createCell(rowDriver, 0, "Supir", style,sheet);
        createCell(rowDriver, 1, driver , style,sheet);
        
        int rowcount = 6;
        Row row = sheet.createRow(rowcount);
        
        //Laporan Summary Kegiatan Truck
                
        createCell(row, 0, "No.", style,sheet);
        createCell(row, 1, "Nomor WO", style,sheet);
        createCell(row, 2, "Tanggal", style,sheet);
        createCell(row, 3, "Nomor SJ", style,sheet);
        createCell(row, 4, "No Truck", style,sheet);
        createCell(row, 5, "Customer", style,sheet);
        createCell(row, 6, "Kecamatan", style,sheet);
        createCell(row, 7, "Supir", style,sheet);
        createCell(row, 8, "Uang Lolo (Rp)", style,sheet);
        createCell(row, 9, "Uang Repair/Demurrage (Rp)", style,sheet);
        createCell(row, 10, "Uang Jalan (Rp)", style,sheet);
        createCell(row, 11, "Uang Bongkar/Muat (Rp)", style,sheet);
        createCell(row, 12, "Uang Kawalan (Rp)", style,sheet);
        createCell(row, 13, "Lain-lain (Rp)", style,sheet);
        
        List<SuratJalanData> listSj = suratJalanService.getListSuratJalanForSummaryKegiatanTruck(idcompany,idbranch,body.getFromDate(),body.getToDate(),null,body.getIdAsset(),body.getIdEmployee());
        HashMap<String, Long> mapInvoiceItem = new HashMap<String, Long>();
        List<MappingData> listInvoiceitem = mappingservice.getListMapping(idcompany, idbranch, "INVOICEITEM");
        for(MappingData datamapping : listInvoiceitem) {
        	mapInvoiceItem.put(datamapping.getMappingcode(), datamapping.getIdmaster());
        }
        
        HashMap<String, Long> mapPaymentItem = new HashMap<String, Long>();
        List<MappingData> listPaymentitem = mappingservice.getListMapping(idcompany, idbranch, "PAYMENTITEM");
        for(MappingData datamapping : listPaymentitem) {
        	mapPaymentItem.put(datamapping.getMappingcode(), datamapping.getIdmaster());
        }
        HashMap<Long, String> mappingKecamatan = new HashMap<Long, String>();
        if(listSj != null && listSj.size() > 0) {
        	rowcount = 7;
			font.setBold(false);
			font.setFontHeight(9);
			
			int nourut = 1;
        	for(SuratJalanData sj : listSj) {
        		Row rowData = sheet.createRow(rowcount++);
    			int columnCount = 0;
    			
        		createCell(rowData, columnCount++, nourut, style,sheet);
        		createCell(rowData, columnCount++, sj.getNodocumentWO(), style,sheet);
        		createCell(rowData, columnCount++, checkNullDate(sj.getTanggalkembali(),""), style,sheet);
        		createCell(rowData, columnCount++, sj.getNodocument(), style,sheet);
        		createCell(rowData, columnCount++, sj.getNopolisi(), style,sheet);
        		createCell(rowData, columnCount++, sj.getNamacustomer(), style,sheet);
        		
        		String kecamatan = "";
        		if(sj.getWarehousekecamatan() != null && !sj.getWarehousekecamatan().equals("")) {
        			long idkec = new Long(sj.getWarehousekecamatan());
        			if(mappingKecamatan.get(idkec) == null) {
        				Optional<District> disOpt = districtRepo.findById(idkec);
            			if(disOpt.isPresent()) {
            				kecamatan = disOpt.get().getDis_name();
            				mappingKecamatan.put(idkec, kecamatan);
            			}
        			}else {
        				kecamatan = mappingKecamatan.get(idkec);
        			}
        			
        		}
        		createCell(rowData, columnCount++, kecamatan, style,sheet);
        		createCell(rowData, columnCount++, sj.getSupirname(), style,sheet);
        		
        		//summaryAmountPengeluaranForSummaryKegiatanTruck(Long idcompany,Long idbranch, Long idwo,Long idcustomer,Long idemployee, Long idinvoiceitem,Long idpaymentitem, Long idasset);
        		Long iduangLolo = mapInvoiceItem.get("UANGLOLO");
        		Double uangLolo = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),sj.getIdemployee_supir(),iduangLolo,null,body.getIdAsset());
        		
        		int compare = new BigDecimal(uangLolo).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(uangLolo));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, uangLolo, styleAmount,sheet,7000);
        		
        		Long iduangRepair = mapInvoiceItem.get("UANGREPAIR");
        		Double uangRepair = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),sj.getIdemployee_supir(),iduangRepair,null,body.getIdAsset());
        		
        		compare = new BigDecimal(uangRepair).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(uangRepair));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, uangRepair, styleAmount,sheet,7000);
				
        		
        		Long iduangJalan = mapPaymentItem.get("UANGJALAN");
        		Double uangJalan = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),sj.getIdemployee_supir(),null,iduangJalan,body.getIdAsset());
        		
        		compare = new BigDecimal(uangJalan).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(uangJalan));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, uangJalan, styleAmount,sheet,7000);
        		
        		Long iduangBongkarMuat = mapPaymentItem.get("UANGBONGKARMUAT");
        		Double uangBongkarMuat = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),sj.getIdemployee_supir(),null,iduangBongkarMuat,body.getIdAsset());
        		
        		compare = new BigDecimal(uangBongkarMuat).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(uangBongkarMuat));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, uangBongkarMuat, styleAmount,sheet,7000);
        		
        		Long iduangKawalan = mapPaymentItem.get("UANGKAWALAN");
        		Double uangKawalan = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),sj.getIdemployee_supir(),null,iduangKawalan,body.getIdAsset());
        		
        		compare = new BigDecimal(uangKawalan).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(uangKawalan));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, uangKawalan, styleAmount,sheet,7000);
        		
        		Long iduangLainLain = mapPaymentItem.get("UANGLAINLAIN");
        		Double uangLainLain = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),sj.getIdemployee_supir(),null,iduangLainLain,body.getIdAsset());
        		
        		compare = new BigDecimal(uangLainLain).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(uangLainLain));
				styleAmount = workbook.createCellStyle();
		        styleAmount.setFont(font);
				if(compare == 0) {
					styleAmount.setDataFormat(format.getFormat("#,###"));
				}else {
					styleAmount.setDataFormat(format.getFormat("#,###.##"));
				}
				createCell(rowData, columnCount++, uangLainLain, styleAmount,sheet,7000);
        		
        		nourut++;
            }
        }
        
        data.setWorkbook(workbook);
		return data;
	}

	@Override
	public HistoryTruckTemplate getHistoryTrucktTemplate(long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		HistoryTruckTemplate template = new HistoryTruckTemplate();
		List<AssetData> listSparepart = new ArrayList<AssetData>();
		listSparepart.addAll(assetService.getListAssetByAssetType(idcompany,idbranch,"SP_KEPALA",null));
		listSparepart.addAll(assetService.getListAssetByAssetType(idcompany,idbranch,"SP_BUNTUT",null));
		
		template.setAssetKepalaOptions(assetService.getListAssetByAssetType(idcompany,idbranch,"KEPALA",null));
		template.setSparepartAssetOptions(listSparepart);
		
		return template;
	}

	@Override
	public ReportWorkBookExcel getReportHistoryTruck(ParamReportManggala body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFDataFormat format = workbook.createDataFormat();
		
		XSSFSheet sheet = workbook.createSheet("Laporan History Truck");
		sheet.setDefaultColumnWidth(1000);
		
		CellStyle style = workbook.createCellStyle();
		CellStyle styleAmount = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        styleAmount.setFont(font);
        
        List<String> listExisting =  new ArrayList<String>();
        String dateFrom = "";
		try {
			dateFrom = GlobalFunc.getDateLongToString(body.getFromDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String dateThru = "";
		try {
			dateThru = GlobalFunc.getDateLongToString(body.getToDate(), "dd-MMM-yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String asset = "All";
		if(body.getIdAsset() != null) {
			Asset assetOpt = assetRepo.getById(body.getIdAsset());
			if(assetOpt != null) {
				asset = assetOpt.getKepala_nama()+" ("+ assetOpt.getKepala_nopolisi()+")";
			}
		}
		
		String sparepart = "All";
		if(body.getIdAssetSparepart() != null) {
			Asset assetOpt = assetRepo.getById(body.getIdAssetSparepart());
			if(assetOpt != null) {
				if(assetOpt.getAssettype().equals("SP_KEPALA")) {
					sparepart = assetOpt.getSparepartkepala_nama();
				}else if(assetOpt.getAssettype().equals("SP_BUNTUT")) {
					sparepart = assetOpt.getSparepartbuntut_nama();
				}
			}
		}
		
		
		Row rowTitle = sheet.createRow(1);
        createCell(rowTitle, 0, "Laporan History Truck", style,sheet);
        Row rowPeriode = sheet.createRow(2);
        createCell(rowPeriode, 0, "Periode", style,sheet);
        createCell(rowPeriode, 1, dateFrom+" s/d "+dateThru, style,sheet);
        Row rowStatus = sheet.createRow(3);
        createCell(rowStatus, 0, "Asset", style,sheet);
        createCell(rowStatus, 1, asset , style,sheet);
        Row rowDriver = sheet.createRow(4);
        createCell(rowDriver, 0, "Sparepart", style,sheet);
        createCell(rowDriver, 1, sparepart , style,sheet);
        
        int rowcount = 6;
        Row row = sheet.createRow(rowcount);
        
        //Laporan Summary Kegiatan Truck
                
        createCell(row, 0, "No Mobil", style,sheet);
        createCell(row, 1, "Kepala/Buntut", style,sheet);
        createCell(row, 2, "Tanggal", style,sheet);
        createCell(row, 3, "Jenis Biaya Maintenance Truck", style,sheet);
        createCell(row, 4, "Jenis Sparepart", style,sheet);
        createCell(row, 5, "Catatan", style,sheet);
        createCell(row, 6, "Harga", style,sheet);
        createCell(row, 7, "Keterangan", style,sheet);
        
        List<AssetData> listAssetKepala = assetService.getListAssetByAssetType(idcompany,idbranch,"KEPALA",body.getIdAsset());
        if(listAssetKepala != null && listAssetKepala.size() > 0) {
        	rowcount = 7;
			font.setBold(false);
			font.setFontHeight(9);
			
        	for(AssetData assetval : listAssetKepala) {
//        		Row rowData = sheet.createRow(rowcount++);
//    			int columnCount = 0;
    			
    			List<HistoryAssetMappingData> getAssetKepala = assetService.getListHistoryMappingReportHistoryTruck(idcompany,idbranch,assetval.getId(),assetval.getId(),body.getFromDate(),body.getToDate(),true,null);
    			HashMap<String, String> listExistKepalaBuntut = new HashMap<String, String>();
    			if(getAssetKepala != null && getAssetKepala.size() > 0) {
    				for(HistoryAssetMappingData history : getAssetKepala) {
    					Row rowData = sheet.createRow(rowcount++);
    	    			int columnCount = 0;
    							
    					createCell(rowData, columnCount++,history.getKepala_nama(), style,sheet);
                		createCell(rowData, columnCount++, "Kepala", style,sheet);
                		createCell(rowData, columnCount++, checkNullDate(new Date(history.getTanggal().getTime()),""), style,sheet);
                		
                		String jenisBiayaMaintenanceTruck = "";
                		String jenisSparePart = "";
                		String catatan = "";
                		Double harga = 0.0;
                		String keterangan = "";
                		
                		PengeluaranKasBankData pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, history.getIdpengeluarankasbank());
                		if(pengeluaranKasBankData != null) {
                			
//                			if(pengeluaranKasBankData != null) {
                				keterangan = pengeluaranKasBankData.getKeterangan();
                				
                				HashMap<String, Object> mapDetails = getDataDetailPengeluaran(history, pengeluaranKasBankData);
                				jenisBiayaMaintenanceTruck = (String) mapDetails.get("jenisBiayaMaintenanceTruck");
                        		jenisSparePart = (String) mapDetails.get("jenisSparePart");
                        		catatan = (String) mapDetails.get("catatan");
                        		harga = (Double) mapDetails.get("harga");
                				
                				createCell(rowData, columnCount++, jenisBiayaMaintenanceTruck, style,sheet);
                        		createCell(rowData, columnCount++, jenisSparePart, style,sheet);
                        		createCell(rowData, columnCount++, catatan, style,sheet);
                        		
                        		int compare = new BigDecimal(harga).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(harga));
                				styleAmount = workbook.createCellStyle();
                		        styleAmount.setFont(font);
                				if(compare == 0) {
                					styleAmount.setDataFormat(format.getFormat("#,###"));
                				}else {
                					styleAmount.setDataFormat(format.getFormat("#,###.##"));
                				}
                				createCell(rowData, columnCount++, harga, styleAmount,sheet,7000);
                				                				
                        		createCell(rowData, columnCount++, keterangan, style,sheet);
//                			}else {
//                				createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                			}
                		}else {
                			createCell(rowData, columnCount++, "", style,sheet);
                    		createCell(rowData, columnCount++, "", style,sheet);
                    		createCell(rowData, columnCount++, "", style,sheet);
                    		createCell(rowData, columnCount++, "", style,sheet);
                    		createCell(rowData, columnCount++, "", style,sheet);
                		}
                		
    				}
    			}else {
    				Row rowData = sheet.createRow(rowcount++);
        			int columnCount = 0;
    				createCell(rowData, columnCount++,assetval.getKepala_nama(), style,sheet);
            		createCell(rowData, columnCount++, "Kepala", style,sheet);
            		createCell(rowData, columnCount++, "", style,sheet);
            		createCell(rowData, columnCount++, "", style,sheet);
            		createCell(rowData, columnCount++, "", style,sheet);
            		createCell(rowData, columnCount++, "", style,sheet);
            		createCell(rowData, columnCount++, "", style,sheet);
            		createCell(rowData, columnCount++, "", style,sheet);
    			}
    			
    			List<HistoryAssetMappingData> listMapping = assetService.getListHistoryMappingReportHistoryTruck(idcompany,idbranch,assetval.getId(),body.getIdAssetSparepart(),body.getFromDate(),body.getToDate(),false,null);
    			if(listMapping != null && listMapping.size() > 0) {
    				for(HistoryAssetMappingData history : listMapping) {
    					if(history.getAfter() == assetval.getId()) {
    						continue;
    					}
    					Row rowData = sheet.createRow(rowcount++);
            			int columnCount = 0;
    					createCell(rowData, columnCount++,assetval.getKepala_nama(), style,sheet);
                		createCell(rowData, columnCount++, history.getKodeasset()+" - "+getStringName(history), style,sheet);
                		createCell(rowData, columnCount++, checkNullDate(new Date(history.getTanggal().getTime()),""), style,sheet);
                		
                		String jenisBiayaMaintenanceTruck = "";
                		String jenisSparePart = "";
                		String catatan = "";
                		Double harga = 0.0;
                		String keterangan = "";
                		
                		PengeluaranKasBankData pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, history.getIdpengeluarankasbank());
//                		if(pengeluaranKasBankData != null) {
                			
                			if(pengeluaranKasBankData != null) {
                				keterangan = pengeluaranKasBankData.getKeterangan();
                				
                				HashMap<String, Object> mapDetails = getDataDetailPengeluaran(history, pengeluaranKasBankData);
                				jenisBiayaMaintenanceTruck = (String) mapDetails.get("jenisBiayaMaintenanceTruck");
                        		jenisSparePart = (String) mapDetails.get("jenisSparePart");
                        		catatan = (String) mapDetails.get("catatan");
                        		harga = (Double) mapDetails.get("harga");
                				
                				createCell(rowData, columnCount++, jenisBiayaMaintenanceTruck, style,sheet);
                        		createCell(rowData, columnCount++, jenisSparePart, style,sheet);
                        		createCell(rowData, columnCount++, catatan, style,sheet);
                        		
                        		int compare = new BigDecimal(harga).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(harga));
                				styleAmount = workbook.createCellStyle();
                		        styleAmount.setFont(font);
                				if(compare == 0) {
                					styleAmount.setDataFormat(format.getFormat("#,###"));
                				}else {
                					styleAmount.setDataFormat(format.getFormat("#,###.##"));
                				}
                				createCell(rowData, columnCount++, harga, styleAmount,sheet,7000);
                				                				
                        		createCell(rowData, columnCount++, keterangan, style,sheet);
                			}else {
                				if(history.getAssettype().equals("BUNTUT") && listExistKepalaBuntut.get(assetval.getId()+"-"+history.getAfter()) == null) {
                					listExistKepalaBuntut.put(assetval.getId()+"-"+history.getAfter(), assetval.getId()+"-"+history.getAfter());
                					List<HistoryAssetMappingData> listMappingBuntut = assetService.getListHistoryMappingReportHistoryTruck(idcompany,idbranch,history.getAfter(),body.getIdAssetSparepart(),body.getFromDate(),body.getToDate(),false,assetval.getId());
                					if(listMappingBuntut != null && listMappingBuntut.size() > 0) {
                						HistoryAssetMappingData historyBuntut = listMappingBuntut.get(0);
                						boolean diffDate = false;
                						Date buntutTanggal1 = new Date(historyBuntut.getTanggal().getTime());
                    					Date buntutTanggal2 = new Date(history.getTanggal().getTime());
                    					if(buntutTanggal1.equals(buntutTanggal2)) {
                    						pengeluaranKasBankData = new PengeluaranKasBankData();
                    						pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, historyBuntut.getIdpengeluarankasbank());
                    						if(pengeluaranKasBankData != null) {
                    							keterangan = pengeluaranKasBankData.getKeterangan();
                    							
                    							HashMap<String, Object> mapDetails = getDataDetailPengeluaran(historyBuntut, pengeluaranKasBankData);
                    							jenisBiayaMaintenanceTruck = (String) mapDetails.get("jenisBiayaMaintenanceTruck");
                                        		jenisSparePart = (String) mapDetails.get("jenisSparePart");
                                        		catatan = (String) mapDetails.get("catatan");
                                        		harga = (Double) mapDetails.get("harga");
                                        		
                                				createCell(rowData, columnCount++, jenisBiayaMaintenanceTruck, style,sheet);
                                        		createCell(rowData, columnCount++, jenisSparePart, style,sheet);
                                        		createCell(rowData, columnCount++, catatan, style,sheet);
                                        		
                                        		int compare = new BigDecimal(harga).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(harga));
                                				styleAmount = workbook.createCellStyle();
                                		        styleAmount.setFont(font);
                                				if(compare == 0) {
                                					styleAmount.setDataFormat(format.getFormat("#,###"));
                                				}else {
                                					styleAmount.setDataFormat(format.getFormat("#,###.##"));
                                				}
                                				createCell(rowData, columnCount++, harga, styleAmount,sheet,7000);
                                				                				
                                        		createCell(rowData, columnCount++, keterangan, style,sheet);
                    						}
                    					}else {
                    						createCell(rowData, columnCount++, "", style,sheet);
                                    		createCell(rowData, columnCount++, "", style,sheet);
                                    		createCell(rowData, columnCount++, "", style,sheet);
                                    		createCell(rowData, columnCount++, "", style,sheet);
                                    		createCell(rowData, columnCount++, "", style,sheet);
                                    		
                                    		rowData = sheet.createRow(rowcount++);
                                    		columnCount = 0;
                                    		
                                    		createCell(rowData, columnCount++,assetval.getKepala_nama(), style,sheet);
                                    		createCell(rowData, columnCount++, historyBuntut.getKodeasset()+" - "+getStringName(historyBuntut), style,sheet);
                                    		createCell(rowData, columnCount++, checkNullDate(new Date(historyBuntut.getTanggal().getTime()),""), style,sheet);
                                    		
                                    		pengeluaranKasBankData = new PengeluaranKasBankData();
                                    		pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, historyBuntut.getIdpengeluarankasbank());
                                    		if(pengeluaranKasBankData != null) {
                                    			keterangan = pengeluaranKasBankData.getKeterangan();
                                    			
                                    			HashMap<String, Object> mapDetails = getDataDetailPengeluaran(historyBuntut, pengeluaranKasBankData);
                                				jenisBiayaMaintenanceTruck = (String) mapDetails.get("jenisBiayaMaintenanceTruck");
                                        		jenisSparePart = (String) mapDetails.get("jenisSparePart");
                                        		catatan = (String) mapDetails.get("catatan");
                                        		harga = (Double) mapDetails.get("harga");
                                        		
                                				
                                				createCell(rowData, columnCount++, jenisBiayaMaintenanceTruck, style,sheet);
                                        		createCell(rowData, columnCount++, jenisSparePart, style,sheet);
                                        		createCell(rowData, columnCount++, catatan, style,sheet);
                                        		
                                        		int compare = new BigDecimal(harga).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(harga));
                                				styleAmount = workbook.createCellStyle();
                                		        styleAmount.setFont(font);
                                				if(compare == 0) {
                                					styleAmount.setDataFormat(format.getFormat("#,###"));
                                				}else {
                                					styleAmount.setDataFormat(format.getFormat("#,###.##"));
                                				}
                                				createCell(rowData, columnCount++, harga, styleAmount,sheet,7000);
                                				                				
                                        		createCell(rowData, columnCount++, keterangan, style,sheet);
                                        		
                                    		}else {
                                    			createCell(rowData, columnCount++, "", style,sheet);
                                        		createCell(rowData, columnCount++, "", style,sheet);
                                        		createCell(rowData, columnCount++, "", style,sheet);
                                        		createCell(rowData, columnCount++, "", style,sheet);
                                        		createCell(rowData, columnCount++, "", style,sheet);
                                    		}
                                    		
                    					}
                					}else {
                						createCell(rowData, columnCount++, "", style,sheet);
                                		createCell(rowData, columnCount++, "", style,sheet);
                                		createCell(rowData, columnCount++, "", style,sheet);
                                		createCell(rowData, columnCount++, "", style,sheet);
                                		createCell(rowData, columnCount++, "", style,sheet);
                					}
                				}else {
                					createCell(rowData, columnCount++, "", style,sheet);
                            		createCell(rowData, columnCount++, "", style,sheet);
                            		createCell(rowData, columnCount++, "", style,sheet);
                            		createCell(rowData, columnCount++, "", style,sheet);
                            		createCell(rowData, columnCount++, "", style,sheet);
                				}
                				
                			}
                			
//                		}else {
                    		
//                			if(history.getAssettype().equals("BUNTUT")) {
//                				List<HistoryAssetMappingData> listMappingBuntut = assetService.getListHistoryMappingReportHistoryTruck(idcompany,idbranch,history.getAfter(),history.getAfter(),body.getFromDate(),body.getToDate(),false);
//                				if(listMappingBuntut != null && listMappingBuntut.size() > 0) {
//                					boolean flag = false;
//                					boolean diffDate = false;
//                					HistoryAssetMappingData historyBuntut = listMappingBuntut.get(0);
//                					Date buntutTanggal1 = new Date(historyBuntut.getTanggal().getTime());
//                					Date buntutTanggal2 = new Date(history.getTanggal().getTime());
//                					if(buntutTanggal1.equals(buntutTanggal2)) {
//                						pengeluaranKasBankData = new PengeluaranKasBankData();
//                						pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, historyBuntut.getIdpengeluarankasbank());
//                						if(pengeluaranKasBankData != null) {
////                							PengeluaranKasBankData pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, historyBuntut.getIdpengeluarankasbank());
//                							if(pengeluaranKasBankData != null) {
//                								keterangan = pengeluaranKasBankData.getKeterangan();
//                                				
//                                				HashMap<String, Object> mapDetails = getDataDetailPengeluaran(historyBuntut, pengeluaranKasBankData);
//                                				jenisBiayaMaintenanceTruck = (String) mapDetails.get("jenisBiayaMaintenanceTruck");
//                                        		jenisSparePart = (String) mapDetails.get("jenisSparePart");
//                                        		catatan = (String) mapDetails.get("catatan");
//                                        		harga = (Double) mapDetails.get("harga");
//                                				
//                                				createCell(rowData, columnCount++, jenisBiayaMaintenanceTruck, style,sheet);
//                                        		createCell(rowData, columnCount++, jenisSparePart, style,sheet);
//                                        		createCell(rowData, columnCount++, catatan, style,sheet);
//                                        		
//                                        		int compare = new BigDecimal(harga).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(harga));
//                                				styleAmount = workbook.createCellStyle();
//                                		        styleAmount.setFont(font);
//                                				if(compare == 0) {
//                                					styleAmount.setDataFormat(format.getFormat("#,###"));
//                                				}else {
//                                					styleAmount.setDataFormat(format.getFormat("#,###.##"));
//                                				}
//                                				createCell(rowData, columnCount++, harga, styleAmount,sheet,7000);
//                                				                				
//                                        		createCell(rowData, columnCount++, keterangan, style,sheet);
//                							}else {
//                								flag = true;
//                							}
//                						}else {
//                							flag = true;
//                						}
//                					}else {
//                						createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		
//                                		rowData = sheet.createRow(rowcount++);
//                                		columnCount = 0;
//                                		
//                                		createCell(rowData, columnCount++,assetval.getKepala_nama(), style,sheet);
//                                		createCell(rowData, columnCount++, historyBuntut.getKodeasset()+" - "+getStringName(historyBuntut), style,sheet);
//                                		createCell(rowData, columnCount++, checkNullDate(new Date(historyBuntut.getTanggal().getTime()),""), style,sheet);
//                                		pengeluaranKasBankData = new PengeluaranKasBankData();
//                                		pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, historyBuntut.getIdpengeluarankasbank());
//                                		if(pengeluaranKasBankData != null) {
////                                			PengeluaranKasBankData pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, historyBuntut.getIdpengeluarankasbank());
//                                			if(pengeluaranKasBankData != null) {
//                								keterangan = pengeluaranKasBankData.getKeterangan();
//                                				
//                                				HashMap<String, Object> mapDetails = getDataDetailPengeluaran(historyBuntut, pengeluaranKasBankData);
//                                				jenisBiayaMaintenanceTruck = (String) mapDetails.get("jenisBiayaMaintenanceTruck");
//                                        		jenisSparePart = (String) mapDetails.get("jenisSparePart");
//                                        		catatan = (String) mapDetails.get("catatan");
//                                        		harga = (Double) mapDetails.get("harga");
//                                				
//                                				createCell(rowData, columnCount++, jenisBiayaMaintenanceTruck, style,sheet);
//                                        		createCell(rowData, columnCount++, jenisSparePart, style,sheet);
//                                        		createCell(rowData, columnCount++, catatan, style,sheet);
//                                        		
//                                        		int compare = new BigDecimal(harga).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(harga));
//                                				styleAmount = workbook.createCellStyle();
//                                		        styleAmount.setFont(font);
//                                				if(compare == 0) {
//                                					styleAmount.setDataFormat(format.getFormat("#,###"));
//                                				}else {
//                                					styleAmount.setDataFormat(format.getFormat("#,###.##"));
//                                				}
//                                				createCell(rowData, columnCount++, harga, styleAmount,sheet,7000);
//                                				                				
//                                        		createCell(rowData, columnCount++, keterangan, style,sheet);
//                							}
//                                		}
//                					}
//                					
//                					if(flag) {
//                						createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                					}
//                				}else {
//                					createCell(rowData, columnCount++, "", style,sheet);
//                            		createCell(rowData, columnCount++, "", style,sheet);
//                            		createCell(rowData, columnCount++, "", style,sheet);
//                            		createCell(rowData, columnCount++, "", style,sheet);
//                            		createCell(rowData, columnCount++, "", style,sheet);
//                				}
//                			}else {
//                				createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                        		createCell(rowData, columnCount++, "", style,sheet);
//                			}
                			
                		}
                		
//                		if(history.getAssettype().equals("BUNTUT")) {
//                			List<HistoryAssetMappingData> listMappingBuntut = assetService.getListHistoryMappingReportHistoryTruck(idcompany,idbranch,history.getAfter(),history.getAfter(),body.getFromDate(),body.getToDate(),false);
//                			if(listMappingBuntut != null && listMappingBuntut.size() > 0) {
//                				for(HistoryAssetMappingData historyBuntut : listMappingBuntut) {
//                					rowData = sheet.createRow(rowcount++);
//                        			columnCount = 0;
//                        			
//                        			createCell(rowData, columnCount++,assetval.getKepala_nama(), style,sheet);
//                            		createCell(rowData, columnCount++, historyBuntut.getKodeasset()+" - "+getStringName(historyBuntut), style,sheet);
//                            		createCell(rowData, columnCount++, checkNullDate(new Date(historyBuntut.getTanggal().getTime()),""), style,sheet);
//                            		
//                            		jenisBiayaMaintenanceTruck = "";
//                            		jenisSparePart = "";
//                            		catatan = "";
//                            		harga = 0.0;
//                            		keterangan = "";
//                            		if(historyBuntut.getIdpengeluarankasbank() != null) {
//                            			PengeluaranKasBankData pengeluaranKasBankData = pengeluaranKasBankService.getById(idcompany, idbranch, historyBuntut.getIdpengeluarankasbank());
//                            			if(pengeluaranKasBankData != null) {
//                            				keterangan = pengeluaranKasBankData.getKeterangan();
//                            				
//                            				HashMap<String, Object> mapDetails = getDataDetailPengeluaran(historyBuntut, pengeluaranKasBankData);
//                            				jenisBiayaMaintenanceTruck = (String) mapDetails.get("jenisBiayaMaintenanceTruck");
//                                    		jenisSparePart = (String) mapDetails.get("jenisSparePart");
//                                    		catatan = (String) mapDetails.get("catatan");
//                                    		harga = (Double) mapDetails.get("harga");
//                            				
//                            				createCell(rowData, columnCount++, jenisBiayaMaintenanceTruck, style,sheet);
//                                    		createCell(rowData, columnCount++, jenisSparePart, style,sheet);
//                                    		createCell(rowData, columnCount++, catatan, style,sheet);
//                                    		
//                                    		int compare = new BigDecimal(harga).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(harga));
//                            				styleAmount = workbook.createCellStyle();
//                            		        styleAmount.setFont(font);
//                            				if(compare == 0) {
//                            					styleAmount.setDataFormat(format.getFormat("#,###"));
//                            				}else {
//                            					styleAmount.setDataFormat(format.getFormat("#,###.##"));
//                            				}
//                            				createCell(rowData, columnCount++, harga, styleAmount,sheet,7000);
//                            				                				
//                                    		createCell(rowData, columnCount++, keterangan, style,sheet);
//                            			}else {
//                            				createCell(rowData, columnCount++, "", style,sheet);
//                                    		createCell(rowData, columnCount++, "", style,sheet);
//                                    		createCell(rowData, columnCount++, "", style,sheet);
//                                    		createCell(rowData, columnCount++, "", style,sheet);
//                                    		createCell(rowData, columnCount++, "", style,sheet);
//                            			}
//                            		}else {
//                            			createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                                		createCell(rowData, columnCount++, "", style,sheet);
//                            		}
//                            		
//                            		
//                				}
//                			}
//                		}
                		
                		
                		
//        			}
    			}
        		
        	}
        }
        
        data.setWorkbook(workbook);
		return data;
	}

	
	private HashMap<String, Object> getDataDetailPengeluaran(HistoryAssetMappingData history,PengeluaranKasBankData pengeluaranKasBankData){
		String jenisBiayaMaintenanceTruck = "";
		String jenisSparePart = "";
		String catatan = "";
		Double harga = 0.0;
		
		List<DetailPengeluaranKasBankData> listDet = pengeluaranKasBankData.getDetails(); 
		if(listDet != null && listDet.size() > 0) {
			DetailPengeluaranKasBankData det = listDet.get(0);
			if(det.getSparepartassettype() != null) {
				if(det.getAssetsparepartNameKepala() != null && !det.getAssetsparepartNameKepala().equals("")) {
					jenisBiayaMaintenanceTruck = det.getAssetsparepartNameKepala();
				}else {
					jenisBiayaMaintenanceTruck = det.getAssetsparepartNameBuntut();
				}
			}
			jenisSparePart = det.getSparepartassettypeName() != null ?det.getSparepartassettypeName():"";
			catatan = det.getCatatan();
			harga = det.getAmount();
			
//			System.out.println("det toString = "+det.toString());
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("jenisBiayaMaintenanceTruck", jenisBiayaMaintenanceTruck);
		map.put("jenisSparePart", jenisSparePart);
		map.put("catatan", catatan);
		map.put("harga", harga);
		
		
		return map;
	}
	
	private String getStringName(HistoryAssetMappingData history){
		String val  = "";
		if(history.getBuntut_nama() != null && !history.getBuntut_nama().equals("")) {
			val = history.getBuntut_nama(); 
		}else if(history.getSparepartbuntut_nama() != null && !history.getSparepartbuntut_nama().equals("")) {
			val = history.getSparepartbuntut_nama(); 
		}else if(history.getSparepartkepala_nama() != null && !history.getSparepartkepala_nama().equals("")) {
			val = history.getSparepartkepala_nama(); 
		}
		return val;
	}
	
	private boolean checkFinanceJunior(Long iduser) {
		boolean flagpermission = false;
		List<UserPermissionData> listPermission =  new ArrayList<UserPermissionData>(userAppsService.getListUserPermission(iduser));
		if(listPermission != null && listPermission.size() > 0) {
			for(UserPermissionData permissiondata : listPermission) {
				if(permissiondata.getPermissioncode().equals("SUPERUSER")) {
//					flagpermission = true;
					break;
				}else if(permissiondata.getPermissioncode().equals(ConstansPermission.READ_FINANCING_JUNIOR)) {
					flagpermission = true;
					break;
				}
			}
		}
		return flagpermission;
	}
}
