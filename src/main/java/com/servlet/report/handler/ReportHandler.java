package com.servlet.report.handler;

import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.charge.entity.ChargeList;
import com.servlet.charge.service.ChargeService;
import com.servlet.invoice.entity.PrintInvoice;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.mappingstock.entity.MappingStockList;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.packinglist.entity.PackingListDataItemDetail;
import com.servlet.packinglist.entity.ParamCalculateQtyPL;
import com.servlet.packinglist.entity.PrintPackingList;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.purchasereceive.entity.ParamCalculateQtyPR;
import com.servlet.purchasereceive.entity.PrintDataPurchaseReceive;
import com.servlet.purchasereceive.entity.PrintDataPurchaseReceiveItems;
import com.servlet.purchasereceive.entity.PurchaseReceiveChargeNotJoin;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.report.entity.ParamReportPembelian;
import com.servlet.report.entity.ParamReportRekapStock;
import com.servlet.report.entity.ParamReportStockUdangHidupMati;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.service.ReportService;
import com.servlet.shared.GlobalFunc;
import com.servlet.stockadjusment.entity.ParamCalculateQtySA;
import com.servlet.stockadjusment.service.StockAdjusmentService;
import com.servlet.stockitems.entity.ParamCalculateQty;
import com.servlet.stockitems.service.StockItemService;
import com.servlet.vendor.entity.VendorDataForTemplate;
import com.servlet.vendor.service.VendorService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Service
public class ReportHandler implements ReportService {

    @Autowired
    PackingListService packingListService;

    @Autowired
    BranchService branchService;

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    CategoryProductService categoryProductService;

    @Autowired
    ChargeService chargeService;

    @Autowired
    PurchaseReceiveService purchaseReceiveService;

    @Autowired
    StockItemService stockItemService;

    @Autowired
    StockAdjusmentService stockAdjusmentService;

    @Autowired
    MappingStockService mappingStockService;

    @Autowired
    VendorService vendorService;

    @Override
    public ReportWorkBookExcel getExcelPackingListByID(long id, long idcompany, long idbranch) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Packing List");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = new ArrayList<>();
        columns.add(5000); //0
        columns.add(5000); //1
        columns.add(5000); //2
        columns.add(5000); //3
        columns.add(5000); //4
        columns.add(5000); //5
        columns.add(5000); //6

        PrintPackingList print = packingListService.getPrintData(id,idcompany,idbranch);
        if(print != null) {
            int fontHeight = 12;
            CellStyle style = workbook.createCellStyle();
            CellStyle styleBold = workbook.createCellStyle();
            CellStyle styleAmount = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(false);
            font.setFontHeight(fontHeight);
            style.setFont(font);
            styleAmount.setFont(font);

            XSSFFont fontBold = workbook.createFont();
            fontBold.setBold(true);
            fontBold.setFontHeight(fontHeight);
            styleBold.setFont(fontBold);

            int rowcount = 2;
            Row row = sheet.createRow(rowcount);


            CellRangeAddress companyNameCellRangeAddress = new CellRangeAddress(2, 2, 0, 5);
            sheet.addMergedRegion(companyNameCellRangeAddress);
            Cell compnayname = createCell(row, 0, print.getCompanyName(), styleBold, sheet,columns);
            CellUtil.setVerticalAlignment(compnayname, VerticalAlignment.CENTER);
            CellUtil.setAlignment(compnayname, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, companyNameCellRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), companyNameCellRangeAddress, sheet);

            rowcount++;
            row = sheet.createRow(rowcount);
            CellRangeAddress address1CellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 5);
            sheet.addMergedRegion(address1CellRangeAddress);
            Cell addrees1 = createCell(row, 0, print.getAddress1(), style, sheet,columns);
            CellUtil.setVerticalAlignment(addrees1, VerticalAlignment.CENTER);
            CellUtil.setAlignment(addrees1, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, address1CellRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), address1CellRangeAddress, sheet);

            rowcount++;
            row = sheet.createRow(rowcount);
            CellRangeAddress address2CellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 5);
            sheet.addMergedRegion(address2CellRangeAddress);
            Cell addrees2 = createCell(row, 0, print.getAddress2(), style, sheet,columns);
            CellUtil.setVerticalAlignment(addrees2, VerticalAlignment.CENTER);
            CellUtil.setAlignment(addrees2, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, address2CellRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), address2CellRangeAddress, sheet);

            rowcount++;
            row = sheet.createRow(rowcount);
            CellRangeAddress address3CellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 5);
            sheet.addMergedRegion(address3CellRangeAddress);
            Cell addrees3 = createCell(row, 0, print.getAddress3(), style, sheet,columns);
            CellUtil.setVerticalAlignment(addrees3, VerticalAlignment.CENTER);
            CellUtil.setAlignment(addrees3, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, address3CellRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), address3CellRangeAddress, sheet);

            rowcount++;
            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "No.PackingList", style, sheet,columns);
            createCell(row, 1, print.getNodocument(), style, sheet,columns);

            createCell(row, 5, "Fligh No", style, sheet,columns);
            createCell(row, 6, print.getFlightnumber(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "To", style, sheet,columns);
            createCell(row, 1, print.getCustomerName(), style, sheet,columns);

            createCell(row, 5, "AWB", style, sheet,columns);
            createCell(row, 6, print.getAwbnumber(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "ATTN", style, sheet,columns);
            createCell(row, 1, print.getAttention(), style, sheet,columns);

            createCell(row, 5, "Netto", style, sheet,columns);
            createCell(row, 6, print.getNetto(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);

            createCell(row, 5, "Collie", style, sheet,columns);
            createCell(row, 6, print.getKoli(), style, sheet,columns);

            rowcount++;
            rowcount++;
            row = sheet.createRow(rowcount);
            CellRangeAddress aliasCustRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 5);
            sheet.addMergedRegion(aliasCustRangeAddress);
            Cell aliascust = createCell(row, 0, print.getCustomerAlias(), style, sheet,columns);
            CellUtil.setVerticalAlignment(aliascust, VerticalAlignment.CENTER);
            CellUtil.setAlignment(aliascust, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, aliasCustRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), aliasCustRangeAddress, sheet);

            rowcount++;
            row = sheet.createRow(rowcount);
            CellRangeAddress namaCustRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 5);
            sheet.addMergedRegion(namaCustRangeAddress);
            String tanggal = "";
            try{
                tanggal = GlobalFunc.getDateLongToString(print.getDate().getTime(), "dd-MMMM-yyyy");
            }catch (ParseException e){
                e.printStackTrace();
            }
            Cell namacust = createCell(row, 0, "P.LIST EXPORT "+print.getCustomerName()+" "+ tanggal, style, sheet,columns);
            CellUtil.setVerticalAlignment(namacust, VerticalAlignment.CENTER);
            CellUtil.setAlignment(namacust, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, namaCustRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), namaCustRangeAddress, sheet);

            rowcount++;
            row = sheet.createRow(rowcount);
            int colomcount = 0;
            createCell(row, colomcount, "BOX", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "SIZE", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "GRAM", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "PIECES", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "WEIGHT(KG)", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "PRICE", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "TOTAL", style, sheet,columns);

            int totalqty = 0;
            double totalweight = 0;
            double totalprice = 0;
            for(PackingListDataItemDetail item : print.getItems()){
                totalqty += item.getQty().intValue();
                totalweight += item.getNettoweight().doubleValue();
                totalprice += item.getTotalprice().doubleValue();

                rowcount++;
                row = sheet.createRow(rowcount);
                colomcount = 0;
                createCell(row, colomcount, item.getBox(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getCategoryProductSize(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getCategoryProductFromGr()+"-"+item.getCategoryProductThruGr(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getQty(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getNettoweight(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getPrice(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getTotalprice(), style, sheet,columns);
            }

            rowcount++;
            row = sheet.createRow(rowcount);
            colomcount = 0;
            colomcount++;
            colomcount++;
            colomcount++;
            createCell(row, colomcount, totalqty, style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, totalweight, style, sheet,columns);

            colomcount++;
            colomcount++;
            createCell(row, colomcount, totalprice, style, sheet,columns);

        }
        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel getExcelInvoiceByID(long id, long idcompany, long idbranch) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Invoice");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = new ArrayList<>();
        columns.add(10000); //0
        columns.add(10000); //1
        columns.add(5000); //2
        columns.add(5000); //3
        columns.add(5000); //4
        columns.add(5000); //5
        columns.add(5000); //6
        columns.add(5000); //6

        PrintInvoice print = invoiceService.getPrintDataByID(id,idcompany,idbranch);
        if(print != null){
            int fontHeight = 12;
            CellStyle style = workbook.createCellStyle();
            CellStyle styleBold = workbook.createCellStyle();
            CellStyle styleAmount = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(false);
            font.setFontHeight(fontHeight);
            style.setFont(font);
            styleAmount.setFont(font);

            XSSFFont fontBold = workbook.createFont();
            fontBold.setBold(true);
            fontBold.setFontHeight(fontHeight);
            styleBold.setFont(fontBold);

            int rowcount = 2;
            Row row = sheet.createRow(rowcount);
            createCell(row, 0, print.getCompanyName(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, print.getAddress1(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, print.getAddress2(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, print.getAddress3(), style, sheet,columns);
            createCell(row, 7, "SALES INVOICE", style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "Customer", style, sheet,columns);
            createCell(row, 1, print.getPackinglist().getCustomerName(), style, sheet,columns);

            createCell(row, 6, "Invoice Number", style, sheet,columns);
            createCell(row, 7, print.getNodocument(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "Address", style, sheet,columns);
            createCell(row, 1, print.getPackinglist().getCustomerAddress(), style, sheet,columns);
            String tanggal = "";
            try{
                tanggal = GlobalFunc.getDateLongToString(print.getDate().getTime(), "dd-MMMM-yyyy");
            }catch (ParseException e){
                e.printStackTrace();
            }

            createCell(row, 6, "Invoice Date", style, sheet,columns);
            createCell(row, 7, tanggal, style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "Phone", style, sheet,columns);
            createCell(row, 1, print.getPhone(), style, sheet,columns);

            createCell(row, 6, "Flight Number", style, sheet,columns);
            createCell(row, 7, print.getPackinglist().getFlightnumber(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "Attn", style, sheet,columns);
            createCell(row, 1, print.getPackinglist().getAttention(), style, sheet,columns);

            createCell(row, 6, "AWB", style, sheet,columns);
            createCell(row, 7, print.getPackinglist().getAwbnumber(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 6, "Collie", style, sheet,columns);
            createCell(row, 7, print.getPackinglist().getKoli(), style, sheet,columns);

            rowcount++;
            rowcount++;
            row = sheet.createRow(rowcount);
            int colomcount = 0;
            createCell(row, colomcount, "No", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Description", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Size", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Gram", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Qty(Pcs)", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Weight", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Unit Price", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Total", style, sheet,columns);

            Double totalPrice = 0.00;
            for(PackingListDataItemDetail item : print.getPackinglist().getItems()){
                totalPrice += item.getTotalprice();

                rowcount++;
                row = sheet.createRow(rowcount);
                colomcount = 0;
                createCell(row, colomcount, item.getBox(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getProductName(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getCategoryProductSize(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getCategoryProductFromGr()+"-"+item.getCategoryProductThruGr(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getQty(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, item.getNettoweight(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "$"+item.getPrice(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "$"+item.getTotalprice(), style, sheet,columns);
            }
            rowcount++;
            row = sheet.createRow(rowcount);

//            int compare = new BigDecimal(totalPrice).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(totalPrice));
//            styleAmount = workbook.createCellStyle();
//            if(compare == 0) {
//                styleAmount.setDataFormat(format.getFormat("#,###"));
//            }else {
//                styleAmount.setDataFormat(format.getFormat("#,###.##"));
//            }
            totalPrice = round(totalPrice,2);
            createCell(row, 0, "Bank Account:", style, sheet,columns);
            createCell(row, 6, "Total In USD", style, sheet,columns);
            createCell(row, 7, "$"+totalPrice, style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, print.getBankCompany(), style, sheet,columns);
            createCell(row, 6, "Kurs", style, sheet,columns);
            createCell(row, 7, "Rp "+print.getKurs(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "a/c "+print.getBankAccNoCompany(), style, sheet,columns);
            createCell(row, 6, "Total In IDR", style, sheet,columns);
            createCell(row, 7, "Rp "+round((print.getKurs()*totalPrice),2), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, print.getBankAccNameCompany(), style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            CellRangeAddress regardsRangeAddress = new CellRangeAddress(rowcount, rowcount, 6, 7);
            sheet.addMergedRegion(regardsRangeAddress);
            Cell regards = createCell(row, 6, "Regards,", style, sheet,columns);
            CellUtil.setVerticalAlignment(regards, VerticalAlignment.CENTER);
            CellUtil.setAlignment(regards, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, regardsRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), regardsRangeAddress, sheet);

            rowcount++;
            rowcount++;
            rowcount++;
            row = sheet.createRow(rowcount);
            CellRangeAddress garisRangeAddress = new CellRangeAddress(rowcount, rowcount, 6, 7);
            sheet.addMergedRegion(garisRangeAddress);
            Cell garis = createCell(row, 6, "__________________", style, sheet,columns);
            CellUtil.setVerticalAlignment(garis, VerticalAlignment.CENTER);
            CellUtil.setAlignment(garis, HorizontalAlignment.CENTER);
            RegionUtil.setBorderRight(BorderStyle.THIN, garisRangeAddress, sheet);
            RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), garisRangeAddress, sheet);
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel reportPembelian(long idcompany, long idbranch, ParamReportPembelian param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Pembelian");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(500);

        String namaCabang = "";
        Branch branch = branchService.getBranchByID(idbranch);
        if(branch != null){
            namaCabang = branch.getNama();
        }

        List<CategoryProductList> listCP = categoryProductService.getDataForTemplate(idcompany,idbranch,null);
        if(listCP != null && listCP.size() > 0){
            int fontHeight = 12;
            CellStyle style = workbook.createCellStyle();
            CellStyle styleBold = workbook.createCellStyle();
            CellStyle styleAmount = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(false);
            font.setFontHeight(fontHeight);
            style.setFont(font);
            styleAmount.setFont(font);

            XSSFFont fontBold = workbook.createFont();
            fontBold.setBold(true);
            fontBold.setFontHeight(fontHeight);
            styleBold.setFont(fontBold);

            int rowcount = 2;
            Row row = sheet.createRow(rowcount);
            createCell(row, 0, "Laporan Pembelian", style, sheet,columns);

            String dateFrom = "";
            try {
                dateFrom = GlobalFunc.getDateLongToString(param.getFrom(), "dd-MMMM-yyyy");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String dateThru = "";
            try {
                dateThru = GlobalFunc.getDateLongToString(param.getTo(), "dd-MMMM-yyyy");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "Periode", style, sheet,columns);
            createCell(row, 1, dateFrom+" s/d "+dateThru, style, sheet,columns);

            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "Cabang", style, sheet,columns);
            createCell(row, 1, namaCabang, style, sheet,columns);

            int colomcount = 0;
            rowcount++;
            rowcount++;
            row = sheet.createRow(rowcount);

            createCell(row, colomcount, "Tanggal Transaksi", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "No Dokumen", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Vendor", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Area", style, sheet,columns);

            colomcount++;
            int koliIdxColomn = colomcount;
            createCell(row, colomcount, "Koli", style, sheet,columns);

            HashMap<Long,Integer> mapsCPcolumn = new HashMap<Long, Integer>();
            for(CategoryProductList cp :listCP){
                colomcount++;
                mapsCPcolumn.put(cp.getId(),colomcount);
                createCell(row, colomcount, "Qty"+cp.getSize(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "QtyBns"+cp.getSize(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "Harga"+cp.getSize(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "subTotal"+cp.getSize(), style, sheet,columns);
            }

            colomcount++;
            int totalQtyEkorIdxColumn = colomcount;
            createCell(row, colomcount, "Total Qty Ekor", style, sheet,columns);
            colomcount++;
            createCell(row, colomcount, "Total KG", style, sheet,columns);
            colomcount++;
            createCell(row, colomcount, "Subtotal Udang", style, sheet,columns);

            List<ChargeList> listBiaya = chargeService.getListCharge(idcompany,idbranch);
            HashMap<Long,Integer> mapsBiayacolumn = new HashMap<Long, Integer>();
            Long idbox = 0L;
            Long idOngkos = 0L;
            if(listBiaya != null && listBiaya.size() > 0){
                for(ChargeList biaya : listBiaya){
                    if(biaya.getNama().equals("BOX")){
                        idbox = biaya.getId();
                    }
                    if(biaya.getNama().equals("ONGKOS")){
                        idOngkos = biaya.getId();
                    }
                    colomcount++;
                    mapsBiayacolumn.put(biaya.getId(),colomcount);
                    createCell(row, colomcount, "Qty"+biaya.getNama(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "Hrg"+biaya.getNama(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "Subtotal"+biaya.getNama(), style, sheet,columns);
                }
            }

            colomcount++;
            int kolomIdxSetor = colomcount;
            createCell(row, colomcount, "Setor", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "SubTotal Biaya", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Total", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Transfer", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Catatan", style, sheet,columns);

            HashMap<String, Object> getData = purchaseReceiveService.getDataForReport(idcompany,idbranch,param);
            List<PrintDataPurchaseReceive> listPR = (List<PrintDataPurchaseReceive>) getData.get("listPR");
            List<PrintDataPurchaseReceiveItems> listItems = (List<PrintDataPurchaseReceiveItems>) getData.get("listItems");
            List<PurchaseReceiveChargeNotJoin> listBiayaPr = (List<PurchaseReceiveChargeNotJoin>) getData.get("listBiaya");


            HashMap<Long,List<PrintDataPurchaseReceiveItems>> grupByIdPR = new HashMap<>();
            List<PrintDataPurchaseReceiveItems> listItemsTemp = new ArrayList<>();
            for(PrintDataPurchaseReceiveItems item :listItems){
                    if(grupByIdPR.get(item.getIdpurchasereceive()) != null){
                        listItemsTemp.add(item);
                        grupByIdPR.put(item.getIdpurchasereceive(), listItemsTemp);
                    }else{
                        listItemsTemp = new ArrayList<>();
                        listItemsTemp.clear();
                        listItemsTemp.add(item);
                        grupByIdPR.put(item.getIdpurchasereceive(), listItemsTemp);
                    }
            }

            HashMap<Long,List<PurchaseReceiveChargeNotJoin>> grupChargeByIdPR = new HashMap<>();
            List<PurchaseReceiveChargeNotJoin> listChargeTemp = new ArrayList<>();
            for(PurchaseReceiveChargeNotJoin item :listBiayaPr){
                if(grupChargeByIdPR.get(item.getIdpurchasereceive()) != null){
                    listChargeTemp.add(item);
                    grupChargeByIdPR.put(item.getIdpurchasereceive(), listChargeTemp);
                }else{
                    listChargeTemp = new ArrayList<>();
                    listChargeTemp.clear();
                    listChargeTemp.add(item);
                    grupChargeByIdPR.put(item.getIdpurchasereceive(), listChargeTemp);
                }
            }

            if(listPR != null && listPR.size() > 0){
                for(PrintDataPurchaseReceive value : listPR){
                    colomcount = 0;
                    rowcount++;
                    row = sheet.createRow(rowcount);
                    String transDate = "";
                    try {
                        transDate = GlobalFunc.getDateLongToString(value.getTransactiondate().getTime(), "dd-MMMM-yyyy");
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    createCell(row, colomcount, transDate, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, value.getNodocument(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, value.getVendorAlias(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, value.getNamaArea(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "Koli", style, sheet,columns);

                    List<PrintDataPurchaseReceiveItems> listItemsPR = grupByIdPR.get(value.getId());
                    int totalQty = 0;
                    double totalSubtotal = 0.0;
                    double totalBeratInGram = 0.0;
                    if(listItemsPR != null && listItemsPR.size() > 0){
                        for(PrintDataPurchaseReceiveItems det : listItemsPR){
                            Double fromgram = det.getWeightfrom() != null?det.getWeightfrom().doubleValue():0.0;
                            Double togram = det.getWeightto() != null?det.getWeightto().doubleValue():0.0;
                            Double beratAvg = (fromgram+togram) / 2;
                            Double berat = beratAvg * det.getQty().doubleValue();
                            totalBeratInGram += berat.doubleValue();

                            totalQty += det.getQty().intValue();
                            totalSubtotal += det.getSubtotalprice().doubleValue();
                            colomcount = mapsCPcolumn.get(det.getIdcategoryproduct()).intValue();
                            createCell(row, colomcount, det.getQty(), style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, det.getQtybonus(), style, sheet,columns);

                            colomcount++;
                            int compare = new BigDecimal(det.getPrice()).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(det.getPrice()));
                            styleAmount = workbook.createCellStyle();
                            if(compare == 0) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }

                            createCell(row, colomcount, det.getPrice(), styleAmount, sheet,columns);

                            compare = new BigDecimal(det.getSubtotalprice()).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(det.getSubtotalprice()));
                            styleAmount = workbook.createCellStyle();
                            if(compare == 0) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }

                            colomcount++;
                            createCell(row, colomcount, det.getSubtotalprice(), styleAmount, sheet,columns);
                        }
                    }

                    colomcount = totalQtyEkorIdxColumn;
                    createCell(row, colomcount, totalQty, style, sheet,columns);
                    colomcount++;
                    createCell(row, colomcount, (totalBeratInGram / 1000.0), style, sheet,columns);

                    int compare = new BigDecimal(totalSubtotal).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(totalSubtotal));
                    styleAmount = workbook.createCellStyle();
                    if(compare == 0) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, totalSubtotal, styleAmount, sheet,columns);

                    List<PurchaseReceiveChargeNotJoin> listChargePR = grupChargeByIdPR.get(value.getId());
                    long qtyBox = 0;
                    Double subtotalBiaya = 0.0;
                    if(listChargePR != null && listChargePR.size() > 0){
                        for(PurchaseReceiveChargeNotJoin det : listChargePR){
                            if(det.getIdcharge() == idbox.longValue()){
                                qtyBox = det.getQty();
                            }
                            subtotalBiaya += det.getSubtotalprice();


                            colomcount = mapsBiayacolumn.get(det.getIdcharge()).intValue();
                            createCell(row, colomcount, det.getQty(), style, sheet,columns);

                            compare = new BigDecimal(det.getPrice()).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(det.getPrice()));
                            styleAmount = workbook.createCellStyle();
                            if(compare == 0) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }

                            colomcount++;
                            createCell(row, colomcount, det.getPrice(), styleAmount, sheet,columns);

                            compare = new BigDecimal(det.getSubtotalprice()).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(det.getSubtotalprice()));
                            styleAmount = workbook.createCellStyle();
                            if(compare == 0) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }
                            colomcount++;
                            createCell(row, colomcount, det.getSubtotalprice(), styleAmount, sheet,columns);
                        }
                    }

                    //Kolom Koli
                    colomcount = koliIdxColomn;
                    createCell(row, colomcount, qtyBox, style, sheet,columns);
                    //

                    compare = new BigDecimal(value.getSetor()).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(value.getSetor()));
                    styleAmount = workbook.createCellStyle();
                    if(compare == 0) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount = kolomIdxSetor;
                    createCell(row, colomcount, value.getSetor(), styleAmount, sheet,columns);

                    compare = new BigDecimal(subtotalBiaya).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(subtotalBiaya));
                    styleAmount = workbook.createCellStyle();
                    if(compare == 0) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, subtotalBiaya, styleAmount, sheet,columns);

                    compare = new BigDecimal(value.getTotalprice()).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(value.getTotalprice()));
                    styleAmount = workbook.createCellStyle();
                    if(compare == 0) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, value.getTotalprice(), styleAmount, sheet,columns);

                    Double transfer = value.getTotalprice() - value.getSetor();
                    compare = new BigDecimal(transfer).round(new MathContext(3, RoundingMode.UP)).compareTo(new BigDecimal(transfer));
                    styleAmount = workbook.createCellStyle();
                    if(compare == 0) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, transfer, styleAmount, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, value.getNotes(), style, sheet,columns);

                }
            }

        }
        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel reportStockUdangHidupMati(long idcompany, long idbranch, ParamReportStockUdangHidupMati param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Stock Udang Hidup & Mati");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(8);

        String namaCabang = "";
        Branch branch = branchService.getBranchByID(idbranch);
        if(branch != null){
            namaCabang = branch.getNama();
        }
        int fontHeight = 12;
        CellStyle style = workbook.createCellStyle();
        CellStyle styleBold = workbook.createCellStyle();
        CellStyle styleAmount = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(fontHeight);
        style.setFont(font);
        styleAmount.setFont(font);

        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);
        fontBold.setFontHeight(fontHeight);
        styleBold.setFont(fontBold);

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Stock Udang Hidup & Mati", style, sheet,columns);

        String dateFrom = "";
        try {
            dateFrom = GlobalFunc.getDateLongToString(param.getDate(), "dd-MMMM-yyyy");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Tanggal", style, sheet,columns);
        createCell(row, 1, dateFrom, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "UKURAN", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "GRAM", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "PATOKAN PER KOLI", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "STOK KOLAM TERAKHIR", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "UDANG MATI", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "UDANG MASUK", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "TOTAL EKOR", style, sheet,columns);
        colomcount++;
        createCell(row, colomcount, "TOTAL KOLI", style, sheet,columns);


        //caraStock by CP
        Long dateMinus1 = 0L;
        try {
            dateMinus1 = GlobalFunc.addDays(param.getDate(),-1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<CategoryProductList> listCP = categoryProductService.getDataForTemplate(idcompany,idbranch,null);
        HashMap<Long,Long> stockKolamTerakhirByIDcategory = new HashMap<>();
        HashMap<Long,Long> stockUdangMatiByIDcategory = new HashMap<>();
        HashMap<Long,Long> stockUdangMasukByIDcategory = new HashMap<>();
        HashMap<Long,CategoryProductList> cpByIDcategory = new HashMap<>();

        //56169461 = 01-Jan-70
        Long satuJan70 = 56169461L;
        for(CategoryProductList cp : listCP){
            cpByIDcategory.put(cp.getId(), cp);
            ParamCalculateQtyPR paramPR = new ParamCalculateQtyPR();
            paramPR.setDateFrom(satuJan70);
            paramPR.setDateThru(dateMinus1);
            paramPR.setIdcategoryproduct(cp.getId());

            ParamCalculateQtySA paramSA = new ParamCalculateQtySA();
            paramSA.setDateFrom(satuJan70);
            paramSA.setDateThru(dateMinus1);
            paramSA.setIdcategoryproduct(cp.getId());

            ParamCalculateQtyPL paramPL = new ParamCalculateQtyPL();
            paramPL.setDateFrom(satuJan70);
            paramPL.setDateThru(dateMinus1);
            paramPL.setIdcategoryproduct(cp.getId());

            ParamCalculateQty paramQty = new ParamCalculateQty();
            paramQty.setParamCalculateQtyPR(paramPR);
            paramQty.setParamCalculateQtySA(paramSA);
            paramQty.setParamCalculateQtyPL(paramPL);
            Long stockKolamTerakhir = stockItemService.calculateQty(idcompany,idbranch,paramQty);
            stockKolamTerakhirByIDcategory.put(cp.getId(),stockKolamTerakhir);

            ParamCalculateQtySA paramSAUdangMati = new ParamCalculateQtySA();
            paramSAUdangMati.setDateFrom(param.getDate());
            paramSAUdangMati.setDateThru(param.getDate());
            paramSAUdangMati.setIdcategoryproduct(cp.getId());
            Long stockUdangMati = stockAdjusmentService.calculateQtySA(idcompany,idbranch,"M",paramSAUdangMati);
            stockUdangMatiByIDcategory.put(cp.getId(),stockUdangMati);

            ParamCalculateQtyPR paramUdangMasuk = new ParamCalculateQtyPR();
            paramUdangMasuk.setDateFrom(param.getDate());
            paramUdangMasuk.setDateThru(param.getDate());
            paramUdangMasuk.setIdcategoryproduct(cp.getId());
            Long stockUdangMasuk = purchaseReceiveService.calculateQtyPr(idcompany,idbranch,paramUdangMasuk);
            stockUdangMasukByIDcategory.put(cp.getId(),stockUdangMasuk);
        }
        Long grandTotalStockKolamTerakhir = 0L;
        Long grandTotalUdangMati = 0L;
        Long grandTotalUdangMasuk = 0L;
        Long grandTotalTotalEkor = 0L;
        Long grandTotalTotalKoli = 0L;

        List<MappingStockList> listMapping = mappingStockService.getListAll(idcompany,idbranch);
        HashMap<Long,Long> done = new HashMap<>();
        if(listMapping != null && listMapping.size() > 0){
            for(MappingStockList mapp : listMapping){
                done.put(mapp.getCategoryproductid(),mapp.getCategoryproductidmapping());
                done.put(mapp.getCategoryproductidmapping(),mapp.getCategoryproductidmapping());
                CategoryProductList cp = cpByIDcategory.get(mapp.getCategoryproductidmapping());
                if(cp != null){
                    colomcount = 0;
                    rowcount++;
                    row = sheet.createRow(rowcount);
                    createCell(row, colomcount, cp.getSize(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, cp.getWeightfromingram()+" - "+cp.getWeighttoingram(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, cp.getJumlahitemsperkoli(), style, sheet,columns);

                    Long stockKolamTerakhir1 = stockKolamTerakhirByIDcategory.get(mapp.getCategoryproductid());
                    if(stockKolamTerakhir1 == null){
                        stockKolamTerakhir1 = 0L;
                    }
                    Long stockKolamTerakhir2 = stockKolamTerakhirByIDcategory.get(mapp.getCategoryproductidmapping());
                    if(stockKolamTerakhir2 == null){
                        stockKolamTerakhir2 = 0L;
                    }
                    Long stockKolamTerakhir = stockKolamTerakhir1.longValue() +  stockKolamTerakhir2.longValue();
                    grandTotalStockKolamTerakhir += stockKolamTerakhir.longValue();
                    colomcount++;
                    createCell(row, colomcount, stockKolamTerakhir, style, sheet,columns);

                    Long stockUdangMati1 = stockUdangMatiByIDcategory.get(mapp.getCategoryproductid());
                    if(stockUdangMati1 == null){
                        stockUdangMati1 = 0L;
                    }
                    Long stockUdangMati2 = stockUdangMatiByIDcategory.get(mapp.getCategoryproductidmapping());
                    if(stockUdangMati2 == null){
                        stockUdangMati2 = 0L;
                    }
                    Long stockUdangMati = stockUdangMati1.longValue() +  stockUdangMati2.longValue();
                    grandTotalUdangMati += stockUdangMati.longValue();
                    colomcount++;
                    createCell(row, colomcount, stockUdangMati, style, sheet,columns);

                    Long stockUdangMasuk1 = stockUdangMasukByIDcategory.get(mapp.getCategoryproductid());
                    if(stockUdangMasuk1 == null){
                        stockUdangMasuk1 = 0L;
                    }
                    Long stockUdangMasuk2 = stockUdangMasukByIDcategory.get(mapp.getCategoryproductidmapping());
                    if(stockUdangMasuk2 == null){
                        stockUdangMasuk2 = 0L;
                    }
                    Long stockUdangMasuk = stockUdangMasuk1.longValue() +  stockUdangMasuk2.longValue();
                    grandTotalUdangMasuk += stockUdangMasuk.longValue();
                    colomcount++;
                    createCell(row, colomcount, stockUdangMasuk, style, sheet,columns);

                    Long totalEkor = stockKolamTerakhir.longValue() + stockUdangMati.longValue() + stockUdangMasuk.longValue();
                    grandTotalTotalEkor += totalEkor.longValue();
                    colomcount++;
                    createCell(row, colomcount, totalEkor, style, sheet,columns);

                    Double totalKoli = 0.0;
                    if(cp.getJumlahitemsperkoli() != null && cp.getJumlahitemsperkoli().intValue() > 0){
                        totalKoli = totalEkor.doubleValue() / cp.getJumlahitemsperkoli().doubleValue();
                    }
                    BigDecimal bgKoli = new BigDecimal(totalKoli).setScale(0,RoundingMode.UP);
                    grandTotalTotalKoli += bgKoli.longValue();
                    colomcount++;
                    createCell(row, colomcount, bgKoli.longValue(), style, sheet,columns);
                }

            }
        }
        for(CategoryProductList cp : listCP){
            if(done.get(cp.getId()) != null){
                continue;
            }
            colomcount = 0;
            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, colomcount, cp.getSize(), style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, cp.getWeightfromingram()+" - "+cp.getWeighttoingram(), style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, cp.getJumlahitemsperkoli(), style, sheet,columns);

            Long stockKolamTerakhir = stockKolamTerakhirByIDcategory.get(cp.getId());
            grandTotalStockKolamTerakhir += stockKolamTerakhir.longValue();
            colomcount++;
            createCell(row, colomcount, stockKolamTerakhir, style, sheet,columns);

            Long stockUdangMati = stockUdangMatiByIDcategory.get(cp.getId());
            grandTotalUdangMati += stockUdangMati.longValue();
            colomcount++;
            createCell(row, colomcount, stockUdangMati, style, sheet,columns);

            Long stockUdangMasuk = stockUdangMasukByIDcategory.get(cp.getId());
            grandTotalUdangMasuk += stockUdangMasuk.longValue();
            colomcount++;
            createCell(row, colomcount, stockUdangMasuk, style, sheet,columns);

            Long totalEkor = stockKolamTerakhir.longValue() + stockUdangMati.longValue() + stockUdangMasuk.longValue();
            grandTotalTotalEkor += totalEkor.longValue();
            colomcount++;
            createCell(row, colomcount, totalEkor, style, sheet,columns);

            Double totalKoli = 0.0;
            if(cp.getJumlahitemsperkoli() != null && cp.getJumlahitemsperkoli().intValue() > 0){
                totalKoli = totalEkor.doubleValue() / cp.getJumlahitemsperkoli().doubleValue();
            }
            BigDecimal bgKoli = new BigDecimal(totalKoli).setScale(0,RoundingMode.UP);
            grandTotalTotalKoli += bgKoli.longValue();
            colomcount++;
            createCell(row, colomcount, bgKoli.longValue(), style, sheet,columns);
        }
        colomcount = 0;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "TOTAL", style, sheet,columns);

        colomcount++;
        colomcount++;
        colomcount++;
        createCell(row, colomcount, grandTotalStockKolamTerakhir, style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, grandTotalUdangMati, style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, grandTotalUdangMasuk, style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, grandTotalTotalEkor, style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, grandTotalTotalKoli, style, sheet,columns);

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel reportRekapStock(long idcompany, long idbranch, ParamReportRekapStock param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Rekapan Barang Masuk Harian");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(100);

        String namaCabang = "";
        Branch branch = branchService.getBranchByID(idbranch);
        if(branch != null){
            namaCabang = branch.getNama();
        }
        int fontHeight = 12;
        CellStyle style = workbook.createCellStyle();
        CellStyle styleBold = workbook.createCellStyle();
        CellStyle styleAmount = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(fontHeight);
        style.setFont(font);
        styleAmount.setFont(font);

        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);
        fontBold.setFontHeight(fontHeight);
        styleBold.setFont(fontBold);

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Rekapan Barang Masuk Harian", style, sheet,columns);

        String dateFrom = "";
        try {
            dateFrom = GlobalFunc.getDateLongToString(param.getDate(), "dd-MMMM-yyyy");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Tanggal", style, sheet,columns);
        createCell(row, 1, dateFrom, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "UKURAN", style, sheet,columns);

        colomcount++;
        int gramIdxKolom = colomcount;
        createCell(row, colomcount, "GRAM", style, sheet,columns);

        List<VendorDataForTemplate> listvendor = vendorService.getListDropdown(idcompany,idbranch);
        List<CategoryProductList> listCP = categoryProductService.getDataForTemplate(idcompany,idbranch,null);

        HashMap<Long,Integer> mapsVendorIdxColumn = new HashMap<>();
        for(VendorDataForTemplate vendor : listvendor){
            colomcount++;
            mapsVendorIdxColumn.put(vendor.getId(), colomcount);
            createCell(row, colomcount, vendor.getNama(), style, sheet,columns);
        }

        colomcount++;
        int totalIdxKolom = colomcount;
        createCell(row, colomcount, "TOTAL", style, sheet,columns);

        HashMap<Long,Long> mapsTotalEkorPerVendor = new HashMap<>();
        HashMap<Long,Double> mapsTotalKgPerVendor = new HashMap<>();
        HashMap<Long,Long> mapsTotalKoliPerVendor = new HashMap<>();
        long grandTotalEkor = 0L;
        Double grandTotalKg = 0.0;
        Long grandTotalKoli = 0L;
        for(CategoryProductList cp : listCP){
            rowcount++;
            row = sheet.createRow(rowcount);
            colomcount = 0;
            createCell(row, colomcount, cp.getSize(), style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, cp.getWeightfromingram()+"-"+cp.getWeighttoingram(), style, sheet,columns);

            Double avgGram = 0.0;
            int fromInGram = 0;
            int toInGram = 0;
            if(cp.getWeightfromingram() != null){
                fromInGram = cp.getWeightfromingram().intValue();
            }
            if(cp.getWeighttoingram() != null){
                toInGram = cp.getWeighttoingram().intValue();
            }
            int gram = fromInGram + toInGram;
            avgGram = Double.valueOf(gram) / 2;

            long totalPerCategory = 0L;
            for (HashMap.Entry<Long, Integer> entry : mapsVendorIdxColumn.entrySet()) {
                    Long idvendor = entry.getKey();
                    int idxcolumn = entry.getValue().intValue();
                ParamCalculateQtyPR paramPR = new ParamCalculateQtyPR();
                paramPR.setDateFrom(param.getDate());
                paramPR.setDateThru(param.getDate());
                paramPR.setIdcategoryproduct(cp.getId());
                paramPR.setIdvendor(idvendor);
                Long stock = purchaseReceiveService.calculateQtyPr(idcompany,idbranch,paramPR);
                Long tempTotalPerVendor = mapsTotalEkorPerVendor.get(idvendor);
                if(tempTotalPerVendor != null){
                    tempTotalPerVendor += stock.longValue();
                    mapsTotalEkorPerVendor.put(idvendor,tempTotalPerVendor);
                }else{
                    mapsTotalEkorPerVendor.put(idvendor,stock);
                }
                totalPerCategory += stock.longValue();
                createCell(row, idxcolumn, stock, style, sheet,columns);

                Double tempTotalKgPerVendor = mapsTotalKgPerVendor.get(idvendor);
                if(tempTotalKgPerVendor != null){
                    tempTotalKgPerVendor += avgGram.doubleValue() * stock.doubleValue();
                    mapsTotalKgPerVendor.put(idvendor,tempTotalKgPerVendor);
                }else{
                    mapsTotalKgPerVendor.put(idvendor,avgGram.doubleValue() * stock.doubleValue());
                }

                Double totalKoli = 0.0;
                if(cp.getJumlahitemsperkoli() != null && cp.getJumlahitemsperkoli().intValue() > 0){
                    totalKoli = stock.doubleValue() / cp.getJumlahitemsperkoli().doubleValue();
                }
                BigDecimal bgKoli = new BigDecimal(totalKoli).setScale(0,RoundingMode.UP);

                Long tempTotalKoliPerVendor = mapsTotalKoliPerVendor.get(idvendor);
                if(tempTotalKoliPerVendor != null){
                    tempTotalKoliPerVendor += bgKoli.longValue();
                    mapsTotalKoliPerVendor.put(idvendor,tempTotalKoliPerVendor);
                }else{
                    mapsTotalKoliPerVendor.put(idvendor,bgKoli.longValue());
                }
            }
            createCell(row, totalIdxKolom, totalPerCategory, style, sheet,columns);

            grandTotalEkor += totalPerCategory;
            grandTotalKg += avgGram.doubleValue() * totalPerCategory;
            Double totalKoli = 0.0;
            if(cp.getJumlahitemsperkoli() != null && cp.getJumlahitemsperkoli().intValue() > 0){
                totalKoli = totalPerCategory / cp.getJumlahitemsperkoli().doubleValue();
            }
            BigDecimal bgKoli = new BigDecimal(totalKoli).setScale(0,RoundingMode.UP);
            grandTotalKoli += bgKoli.longValue();

        }

        rowcount++;
        row = sheet.createRow(rowcount);
        colomcount = 0;
        createCell(row, colomcount, "Total Ekor", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "", style, sheet,columns);
        for (HashMap.Entry<Long, Long> entry : mapsTotalEkorPerVendor.entrySet()) {
            Long idvendor = entry.getKey();
            Long totalEkor = entry.getValue();
            int idxcolumn = mapsVendorIdxColumn.get(idvendor).intValue();
            createCell(row, idxcolumn, totalEkor, style, sheet,columns);
        }
        createCell(row, totalIdxKolom, grandTotalEkor, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        colomcount = 0;
        createCell(row, colomcount, "Total Kg", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "", style, sheet,columns);
        for (HashMap.Entry<Long, Double> entry : mapsTotalKgPerVendor.entrySet()) {
            Long idvendor = entry.getKey();
            Double totalKg = entry.getValue();
            int idxcolumn = mapsVendorIdxColumn.get(idvendor).intValue();
            createCell(row, idxcolumn, convertGramToKg(totalKg), style, sheet,columns);
        }
        createCell(row, totalIdxKolom, convertGramToKg(grandTotalKg), style, sheet,columns);

        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        colomcount = 0;
        createCell(row, colomcount, "Koli", style, sheet,columns);
        colomcount++;
        createCell(row, colomcount, "", style, sheet,columns);
        for (HashMap.Entry<Long, Long> entry : mapsTotalKoliPerVendor.entrySet()) {
            Long idvendor = entry.getKey();
            Long totalKoli = entry.getValue();
            int idxcolumn = mapsVendorIdxColumn.get(idvendor).intValue();
            createCell(row, idxcolumn, totalKoli, style, sheet,columns);
        }
        createCell(row, totalIdxKolom, grandTotalKoli, style, sheet,columns);


        data.setWorkbook(workbook);
        return data;
    }

    private List<Integer> getWidthColumns(int size){
        List<Integer> columns = new ArrayList<>();
        int defaultwidth = 5000;
        for(int i=0;i < size;i++){
            columns.add(defaultwidth);
        }
        return columns;
    }

    private double convertGramToKg(double value) {
        return value / 1000.0;
    }
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private Cell createCell(Row row, int columnCount, Object value, CellStyle style,XSSFSheet sheet,List<Integer> widthcols) {
//        sheet.autoSizeColumn(columnCount);
        sheet.setColumnWidth(columnCount, widthcols.get(columnCount));
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

        return cell;
    }
}
