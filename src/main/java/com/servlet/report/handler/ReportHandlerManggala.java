package com.servlet.report.handler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.servlet.address.entity.District;
import com.servlet.address.entity.DistrictData;
import com.servlet.address.repo.DistrictRepo;
import com.servlet.address.service.DistrictService;
import com.servlet.asset.entity.Asset;
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
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.PenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.PenerimaanPengeluaranData;
import com.servlet.penerimaankasbank.service.PenerimaanKasBankService;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;
import com.servlet.pengluarankasbank.service.PengeluaranKasBankService;
import com.servlet.report.entity.ManggalaStatusInvoice;
import com.servlet.report.entity.Manggala_BodyReportBongkarMuatDanDepo;
import com.servlet.report.entity.ParamReportManggala;
import com.servlet.report.entity.ReportSummaryKegiatanTructTemplate;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.service.ReportServiceManggala;
import com.servlet.shared.ConstantReportName;
import com.servlet.shared.GlobalFunc;
import com.servlet.suratjalan.entity.SuratJalanData;
import com.servlet.suratjalan.service.SuratJalanService;
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
			rowcount = 5;
			font.setBold(false);
			font.setFontHeight(9);
			
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
						createCell(rowData, columnCount++, checkNull(wodata.getPorttujuanname(),""), style,sheet);
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
	public ReportWorkBookExcel getReportKasBank(ParamReportManggala body, long idcompany, long idbranch) {
		// TODO Auto-generated method stub
		ReportWorkBookExcel data = new ReportWorkBookExcel();
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFDataFormat format = workbook.createDataFormat();
		
		XSSFSheet sheet = workbook.createSheet("Laporan Kas Bank");
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
        
		Timestamp tsDateBeforeFrom = null;
		try {
			tsDateBeforeFrom = GlobalFunc.addDays(new Timestamp(body.getFromDate()) ,-1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Date dtDateBeforeFrom = new Date(tsDateBeforeFrom.getTime());
		
		long idbank = body.getIdbank() != null?body.getIdbank():0;
		BankAccountData bankData = bankAccountService.getByIdForReport(idcompany, idbranch, idbank);
		double saldoAwalBank = 0.0;
		if(bankData != null) {
			if(bankData.getSaldoawal() != null) {
				saldoAwalBank = bankData.getSaldoawal();
			}
		}
		
		//56169461 = 01-Jan-70
		Double saldoPenerimaan = penerimaanKasBankService.summaryAmountPenerimaanByDate(idcompany, idbranch, new Date(56169461L), dtDateBeforeFrom, null,idbank);
		saldoPenerimaan = saldoPenerimaan != null? saldoPenerimaan:0.0;
		Double saldoPengeluaraan = pengeluaranKasBankService.summaryAmountPengeluaranByDate(idcompany, idbranch, new Date(56169461L), dtDateBeforeFrom, null,idbank);
		saldoPengeluaraan = saldoPengeluaraan != null ? saldoPengeluaraan:0.0;
		double totalSaldoAwal = saldoAwalBank + saldoPenerimaan - saldoPengeluaraan; 
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
        createCell(row, 2, "COA", style,sheet);
        createCell(row, 3, "No. WO yang dibayar", style,sheet);
        createCell(row, 4, "Nomor AJU", style,sheet);
        createCell(row, 5, "No. Invoice", style,sheet);
        createCell(row, 6, "Nama Customer", style,sheet);
        createCell(row, 7, "Keterangan", style,sheet);
        createCell(row, 8, "Uang Masuk", style,sheet);
        createCell(row, 9, "Uang Keluar", style,sheet);
        createCell(row, 10, "Saldo", style,sheet);
        
        List<PenerimaanPengeluaranData> list = penerimaanKasBankService.getPenerimaanPengeluaranData(idcompany, idbranch, new Date(body.getFromDate()), new Date(body.getToDate()), body.getIdbank());
        if(list != null && list.size() > 0) {
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
				styleAmount.setDataFormat(format.getFormat("#,###"));
			}else {
				styleAmount.setDataFormat(format.getFormat("#,###.##"));
			}
			
			
			createCell(rowDataSaldoAwal, columnCount++, totalSaldoAwal, styleAmount,sheet,7000);
			
			for(PenerimaanPengeluaranData datapenerimaan : list) {
				if(datapenerimaan.getPenerimaan_id() != null) {
					List<DetailPenerimaanKasBankData> listdetail = penerimaanKasBankService.getListDetailByIdReportKasBank(idcompany, idbranch, datapenerimaan.getPenerimaan_id());
					for(DetailPenerimaanKasBankData det : listdetail) {
						Row rowData = sheet.createRow(rowcount++);
						columnCount = 0;
						
						createCell(rowData, columnCount++, checkNullDate(datapenerimaan.getPenerimaan_receivedate(),""), style,sheet);
						createCell(rowData, columnCount++, datapenerimaan.getPenerimaan_nodocument(), style,sheet);
						createCell(rowData, columnCount++, det.getCoaname(), style,sheet);
						createCell(rowData, columnCount++, det.getNodocworkorder(), style,sheet);
						createCell(rowData, columnCount++, det.getNoaju(), style,sheet);
						createCell(rowData, columnCount++, det.getNodocinvoice(), style,sheet);
						createCell(rowData, columnCount++, datapenerimaan.getPenerimaan_receivefrom(), style,sheet);
						createCell(rowData, columnCount++, datapenerimaan.getPenerimaan_keterangan(), style,sheet);
						Double saldoUangMasuk = det.getAmount().doubleValue();//penerimaanKasBankService.summaryAmountPenerimaanByDate(idcompany, idbranch, null, null, datapenerimaan.getPenerimaan_id());
						saldoUangMasuk = saldoUangMasuk != null?saldoUangMasuk:0.0;
						compare = new BigDecimal(saldoUangMasuk).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldoUangMasuk));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat("#,###"));
						}else {
							styleAmount.setDataFormat(format.getFormat("#,###.##"));
						}
						createCell(rowData, columnCount++, saldoUangMasuk, styleAmount,sheet,7000);
						createCell(rowData, columnCount++, "", style,sheet);
						
						totalSaldoAwal = totalSaldoAwal + saldoUangMasuk.doubleValue();
						double saldo = totalSaldoAwal;
						compare = new BigDecimal(saldo).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldo));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat("#,###"));
						}else {
							styleAmount.setDataFormat(format.getFormat("#,###.##"));
						}
						createCell(rowData, columnCount++, saldo, styleAmount,sheet,7000);
						
					}
				}
				
				if(datapenerimaan.getPengeluaran_id() != null) {
					List<DetailPengeluaranKasBankData> listdetail = pengeluaranKasBankService.getListDetailById(idcompany, idbranch, datapenerimaan.getPengeluaran_id());
					for(DetailPengeluaranKasBankData det : listdetail) {
						Row rowData = sheet.createRow(rowcount++);
						columnCount = 0;
						
						createCell(rowData, columnCount++, checkNullDate(datapenerimaan.getPengeluaran_paymentdate(),""), style,sheet);
						createCell(rowData, columnCount++, datapenerimaan.getPengeluaran_nodocument(), style,sheet);
						createCell(rowData, columnCount++, datapenerimaan.getPengeluaran_coaName(), style,sheet);
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, datapenerimaan.getPengeluaran_paymentto(), style,sheet);
						createCell(rowData, columnCount++, datapenerimaan.getPengeluaran_keterangan(), style,sheet);
						Double saldoUangKeluar = det.getAmount().doubleValue();//pengeluaranKasBankService.summaryAmountPengeluaranByDate(idcompany, idbranch, null, null, datapenerimaan.getPengeluaran_id());
						saldoUangKeluar = saldoUangKeluar != null?saldoUangKeluar:0.0;
						compare = new BigDecimal(saldoUangKeluar).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldoUangKeluar));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat("#,###"));
						}else {
							styleAmount.setDataFormat(format.getFormat("#,###.##"));
						}
						createCell(rowData, columnCount++, "", style,sheet);
						createCell(rowData, columnCount++, saldoUangKeluar, styleAmount,sheet,7000);
						
						totalSaldoAwal = totalSaldoAwal - saldoUangKeluar.doubleValue();
						double saldo = totalSaldoAwal;
						
						compare = new BigDecimal(saldo).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(saldo));
						styleAmount = workbook.createCellStyle();
				        styleAmount.setFont(font);
						if(compare == 0) {
							styleAmount.setDataFormat(format.getFormat("#,###"));
						}else {
							styleAmount.setDataFormat(format.getFormat("#,###.##"));
						}
						createCell(rowData, columnCount++, saldo, styleAmount,sheet,7000);
						
						
					}
					
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
		template.setAssetOptions(assetService.getListAssetByAssetType(idcompany,idbranch,"KEPALA"));
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
        		Double uangLolo = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),body.getIdEmployee(),iduangLolo,null,body.getIdAsset());
        		
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
        		Double uangRepair = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),body.getIdEmployee(),iduangRepair,null,body.getIdAsset());
        		
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
        		Double uangJalan = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),body.getIdEmployee(),null,iduangJalan,body.getIdAsset());
        		
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
        		Double uangBongkarMuat = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),body.getIdEmployee(),null,iduangBongkarMuat,body.getIdAsset());
        		
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
        		Double uangKawalan = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),body.getIdEmployee(),null,iduangKawalan,body.getIdAsset());
        		
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
        		Double uangLainLain = pengeluaranKasBankService.summaryAmountPengeluaranForSummaryKegiatanTruck(idcompany,idbranch,sj.getIdworkorder(),sj.getIdcustomer(),body.getIdEmployee(),null,iduangLainLain,body.getIdAsset());
        		
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

}
