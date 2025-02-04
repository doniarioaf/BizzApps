package com.servlet.report.handler;

import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.cargo.entity.CargoDataReportStatusTagihanCargo;
import com.servlet.cargo.entity.ParamCargoSearch;
import com.servlet.cargo.service.CargoService;
import com.servlet.categoryproduct.entity.CategoryProductList;
import com.servlet.categoryproduct.entity.ParamTemplate;
import com.servlet.categoryproduct.service.CategoryProductService;
import com.servlet.charge.entity.ChargeList;
import com.servlet.charge.service.ChargeService;
import com.servlet.customer.entity.CustomerForReport;
import com.servlet.customer.entity.CustomerGrup;
import com.servlet.customer.service.CustomerService;
import com.servlet.deposit.entity.ParamList;
import com.servlet.deposit.entity.ReportKartuDeposit;
import com.servlet.deposit.service.DepositService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.entity.InvoiceDataReportPelunasanPiutang;
import com.servlet.invoice.entity.InvoiceDataReportPiutang;
import com.servlet.invoice.entity.ParamSearchInvoice;
import com.servlet.invoice.entity.PrintInvoice;
import com.servlet.invoice.service.InvoiceService;
import com.servlet.komisi.entity.KomisiDataReportKomisi;
import com.servlet.komisi.entity.ParamKomisiReportKomisi;
import com.servlet.komisi.service.KomisiService;
import com.servlet.mappingstock.entity.MappingStockList;
import com.servlet.mappingstock.entity.ParamSearchMappingStock;
import com.servlet.mappingstock.service.MappingStockService;
import com.servlet.packinglist.entity.PackingListDataItemDetail;
import com.servlet.packinglist.entity.ParamCalculateQtyPL;
import com.servlet.packinglist.entity.ParamSearchPackingList;
import com.servlet.packinglist.entity.PrintPackingList;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.pelunasanhutang.entity.FilterParamPelunasanHutang;
import com.servlet.pelunasanhutang.entity.PelunasanHutangReportHutang;
import com.servlet.pelunasanhutang.entity.PelunasanHutangReportStatusTagihanCargo;
import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;
import com.servlet.pelunasanhutang.service.PelunasanHutangService;
import com.servlet.pelunasanpiutang.entity.FilterParamPelunasanPiutang;
import com.servlet.pelunasanpiutang.entity.ReportPelunasanPiutang;
import com.servlet.pelunasanpiutang.service.PelunasanPiutangService;
import com.servlet.product.entity.ListProductData;
import com.servlet.product.entity.ParamProduct;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.*;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.report.entity.*;
import com.servlet.report.service.ReportService;
import com.servlet.shared.GlobalFunc;
import com.servlet.stockadjusment.entity.ParamCalculateQtySA;
import com.servlet.stockadjusment.service.StockAdjusmentService;
import com.servlet.stockitems.entity.ParamCalculateQty;
import com.servlet.stockitems.entity.ReportKartuStock;
import com.servlet.stockitems.service.StockItemService;
import com.servlet.user.entity.UserListData;
import com.servlet.user.service.UserAppsService;
import com.servlet.vendor.entity.ParamVendor;
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

    @Autowired
    CargoService cargoService;

    @Autowired
    PelunasanHutangService pelunasanHutangService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PelunasanPiutangService pelunasanPiutangService;

    @Autowired
    DepositService depositService;

    @Autowired
    HistoryAppsService historyAppsService;

    @Autowired
    UserAppsService userAppsService;
    @Autowired
    ProductService productService;

    @Autowired
    KomisiService komisiService;

    @Override
    public ReportWorkBookExcel getExcelPackingListByID(long id, long idcompany, long idbranch,long iduser) {
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

        PrintPackingList print = packingListService.getPrintData(id,idcompany,idbranch,null,null);
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

            int rowcount = 0;
            Row row = sheet.createRow(rowcount);

            Long countEdit = historyAppsService.countByActionAndMenu(idcompany,idbranch,"EDIT","PackingList");
            UserListData user = userAppsService.getUserByID(iduser);
            String namaUser = "";
            if (user != null) {
                namaUser = user.getNama();
            }

            String transDate = "";
            try {
                transDate = GlobalFunc.getDateLongToString(new Date().getTime(), "dd MMMM yyyy HH:mm:ss");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            createCell(row, 6, "Edit : "+countEdit+", Dicetak Oleh : "+namaUser+", "+transDate, style, sheet,columns);

            rowcount = 2;
            row = sheet.createRow(rowcount);

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
    public ReportWorkBookExcel getExcelInvoiceByID(long id, long idcompany, long idbranch, long iduser) {
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

        PrintInvoice print = invoiceService.getPrintDataByID(id,idcompany,idbranch,null,null);
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

            int rowcount = 0;
            Row row = sheet.createRow(rowcount);

            Long countEdit = historyAppsService.countByActionAndMenu(idcompany,idbranch,"EDIT","Invoice");
            UserListData user = userAppsService.getUserByID(iduser);
            String namaUser = "";
            if (user != null) {
                namaUser = user.getNama();
            }

            String transDate = "";
            try {
                transDate = GlobalFunc.getDateLongToString(new Date().getTime(), "dd MMMM yyyy HH:mm:ss");
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            createCell(row, 7, "Edit : "+countEdit+", Dicetak Oleh : "+namaUser+", "+transDate, style, sheet,columns);

            rowcount = 2;
            row = sheet.createRow(rowcount);
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

            styleAmount = workbook.createCellStyle();
            if(GlobalFunc.checkIsDecimal(print.getKurs())) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.##"));
            }
            createCell(row, 7, "Rp "+print.getKurs(), styleAmount, sheet,columns);

            double totalInIdr = round((print.getKurs()*totalPrice),2);
            styleAmount = workbook.createCellStyle();
            if(GlobalFunc.checkIsDecimal(totalInIdr)) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.##"));
            }
            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, 0, "a/c "+print.getBankAccNoCompany(), style, sheet,columns);
            createCell(row, 6, "Total In IDR", style, sheet,columns);
            createCell(row, 7, "Rp "+new BigDecimal(totalInIdr), styleAmount, sheet,columns);

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
            createCell(row, colomcount, "Catatan 1", style, sheet,columns);

            colomcount++;
            createCell(row, colomcount, "Catatan 2", style, sheet,columns);

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

                            styleAmount = workbook.createCellStyle();
                            if(GlobalFunc.checkIsDecimal(det.getPrice())) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }

                            createCell(row, colomcount, det.getPrice(), styleAmount, sheet,columns);

                            styleAmount = workbook.createCellStyle();
                            if(GlobalFunc.checkIsDecimal(det.getSubtotalprice())) {
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

                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(totalSubtotal)) {
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

                            styleAmount = workbook.createCellStyle();
                            if(GlobalFunc.checkIsDecimal(det.getPrice())) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }

                            colomcount++;
                            createCell(row, colomcount, det.getPrice(), styleAmount, sheet,columns);

                            styleAmount = workbook.createCellStyle();
                            if(GlobalFunc.checkIsDecimal(det.getSubtotalprice())) {
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

                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(value.getSetor())) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount = kolomIdxSetor;
                    createCell(row, colomcount, value.getSetor(), styleAmount, sheet,columns);

                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(subtotalBiaya)) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, subtotalBiaya, styleAmount, sheet,columns);

                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(value.getTotalprice())) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, value.getTotalprice(), styleAmount, sheet,columns);

                    Double transfer = value.getTotalprice() - value.getSetor();
                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(transfer)) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, transfer, styleAmount, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, value.getNotes(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, value.getNotes2(), style, sheet,columns);

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

        HashMap<Long, Long> calculateStockByIdCPMappingStockKolamTerakhir = new HashMap<>();
        HashMap<Long, Long> calculateStockByIdCPMappingStockUdangMati = new HashMap<>();
        HashMap<Long, Long> calculateStockByIdCPMappingStockUdangMasuk = new HashMap<>();
        if(listMapping != null && listMapping.size() > 0){
            for(MappingStockList mapp : listMapping){
                Long stockKolamTerakhir1 = stockKolamTerakhirByIDcategory.get(mapp.getCategoryproductid());
                if(stockKolamTerakhir1 == null){
                    stockKolamTerakhir1 = 0L;
                }
                Long stockKolamTerakhir2 = stockKolamTerakhirByIDcategory.get(mapp.getCategoryproductidmapping());
                if(stockKolamTerakhir2 == null){
                    stockKolamTerakhir2 = 0L;
                }
                Long stockKolamTerakhir = stockKolamTerakhir1.longValue() +  stockKolamTerakhir2.longValue();

                Long stockUdangMati1 = stockUdangMatiByIDcategory.get(mapp.getCategoryproductid());
                if(stockUdangMati1 == null){
                    stockUdangMati1 = 0L;
                }
                Long stockUdangMati2 = stockUdangMatiByIDcategory.get(mapp.getCategoryproductidmapping());
                if(stockUdangMati2 == null){
                    stockUdangMati2 = 0L;
                }
                Long stockUdangMati = stockUdangMati1.longValue() +  stockUdangMati2.longValue();

                Long stockUdangMasuk1 = stockUdangMasukByIDcategory.get(mapp.getCategoryproductid());
                if(stockUdangMasuk1 == null){
                    stockUdangMasuk1 = 0L;
                }
                Long stockUdangMasuk2 = stockUdangMasukByIDcategory.get(mapp.getCategoryproductidmapping());
                if(stockUdangMasuk2 == null){
                    stockUdangMasuk2 = 0L;
                }
                Long stockUdangMasuk = stockUdangMasuk1.longValue() +  stockUdangMasuk2.longValue();


                if(calculateStockByIdCPMappingStockKolamTerakhir.get(mapp.getCategoryproductidmapping()) != null){
                    Long tempStockKolamTerakhir = stockKolamTerakhir.longValue() + calculateStockByIdCPMappingStockKolamTerakhir.get(mapp.getCategoryproductidmapping()).longValue();
                    calculateStockByIdCPMappingStockKolamTerakhir.put(mapp.getCategoryproductidmapping(),tempStockKolamTerakhir);

                    Long tempStockUdangMati = stockUdangMati.longValue() + calculateStockByIdCPMappingStockUdangMati.get(mapp.getCategoryproductidmapping()).longValue();
                    calculateStockByIdCPMappingStockUdangMati.put(mapp.getCategoryproductidmapping(),tempStockUdangMati);

                    Long tempStockUdangMasuk = stockUdangMasuk.longValue() + calculateStockByIdCPMappingStockUdangMasuk.get(mapp.getCategoryproductidmapping()).longValue();
                    calculateStockByIdCPMappingStockUdangMasuk.put(mapp.getCategoryproductidmapping(),tempStockUdangMasuk);
                }else{
                    calculateStockByIdCPMappingStockKolamTerakhir.put(mapp.getCategoryproductidmapping(),stockKolamTerakhir);
                    calculateStockByIdCPMappingStockUdangMati.put(mapp.getCategoryproductidmapping(),stockUdangMati);
                    calculateStockByIdCPMappingStockUdangMasuk.put(mapp.getCategoryproductidmapping(),stockUdangMasuk);
                }
            }
        }
        HashMap<Long,Long> done = new HashMap<>();
        HashMap<Long,Long> cekIDCPMappingKembar = new HashMap<>();
        if(listMapping != null && listMapping.size() > 0){
            for(MappingStockList mapp : listMapping){
                done.put(mapp.getCategoryproductid(),mapp.getCategoryproductidmapping());
                done.put(mapp.getCategoryproductidmapping(),mapp.getCategoryproductidmapping());
                CategoryProductList cp = cpByIDcategory.get(mapp.getCategoryproductidmapping());
                if(cp != null){
                    if(cekIDCPMappingKembar.get(mapp.getCategoryproductidmapping()) != null){
                        continue;
                    }
                    cekIDCPMappingKembar.put(mapp.getCategoryproductidmapping(),mapp.getCategoryproductidmapping());

                    Long stockKolamTerakhir = calculateStockByIdCPMappingStockKolamTerakhir.get(mapp.getCategoryproductidmapping()).longValue();
                    grandTotalStockKolamTerakhir += stockKolamTerakhir.longValue();

                    Long stockUdangMati = calculateStockByIdCPMappingStockUdangMati.get(mapp.getCategoryproductidmapping()).longValue();
                    grandTotalUdangMati += stockUdangMati.longValue();

                    Long stockUdangMasuk = calculateStockByIdCPMappingStockUdangMasuk.get(mapp.getCategoryproductidmapping()).longValue();
                    grandTotalUdangMasuk += stockUdangMasuk.longValue();

                    Long totalEkor = stockKolamTerakhir.longValue() + stockUdangMati.longValue() + stockUdangMasuk.longValue();
                    grandTotalTotalEkor += totalEkor.longValue();

                    Double totalKoli = 0.0;
                    if(cp.getJumlahitemsperkoli() != null && cp.getJumlahitemsperkoli().intValue() > 0){
                        totalKoli = totalEkor.doubleValue() / cp.getJumlahitemsperkoli().doubleValue();
                    }
                    BigDecimal bgKoli = new BigDecimal(totalKoli).setScale(0,RoundingMode.UP);
                    grandTotalTotalKoli += bgKoli.longValue();

                    colomcount = 0;
                    rowcount++;
                    row = sheet.createRow(rowcount);
                    createCell(row, colomcount, cp.getSize(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, cp.getWeightfromingram()+" - "+cp.getWeighttoingram(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, cp.getJumlahitemsperkoli(), style, sheet,columns);


                    colomcount++;
                    createCell(row, colomcount, stockKolamTerakhir, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, stockUdangMati, style, sheet,columns);


                    colomcount++;
                    createCell(row, colomcount, stockUdangMasuk, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, totalEkor, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, bgKoli.longValue(), style, sheet,columns);
                }

            }
        }
        for(CategoryProductList cp : listCP){
            if(done.get(cp.getId().longValue()) != null){
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

        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setVendorTypes("'UDANG'");
        List<VendorDataForTemplate> listvendor = vendorService.getListDropdown (idcompany,idbranch,paramVendor);
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

    @Override
    public ReportWorkBookExcel reportStatusTagihanCargo(long idcompany, long idbranch, ParamReportStatusTagihanCargo param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Status Tagihan Cargo");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

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
        String namaVendor = "";
        ParamVendor paramvendor =  new ParamVendor();
        if(param.getIdvendors().equals("ALL")){
            paramvendor.setVendorTypes("'CARGO','UPI'");
        }else{
            paramvendor.setListIdVendor(param.getIdvendors());
        }

        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(idcompany,idbranch,paramvendor);
        List<String> list = new ArrayList<>();
        HashMap<Long, VendorDataForTemplate> mapVendor = new HashMap<>();
        for(VendorDataForTemplate ven : getListVendor){
            mapVendor.put(ven.getId(), ven);
            list.add(ven.getAlias());
        }
        if(!param.getIdvendors().equals("ALL")){
            for(String nama :list){
                if(namaVendor == ""){
                    namaVendor = nama;
                } else{
                    namaVendor= namaVendor+","+nama;
                }
            }
        }else{
            namaVendor = "ALL";
        }

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Status Tagihan Cargo", style, sheet,columns);

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
        createCell(row, 0, "Vendor", style, sheet,columns);
        createCell(row, 1, namaVendor, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Vendor", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Tanggal", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Invoice", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No SMU", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No AWB", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Koli", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Gross Invoice", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "PPN", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "PPN23", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Net Invoice", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Status Pembayaran", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Doc Pelunasan", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Keterangan", style, sheet,columns);

        HashMap<Long,Integer[]> mapsIdCargoRowColomIndex = new HashMap<>();
        for(VendorDataForTemplate ven : getListVendor){
            ParamCargoSearch paramCargo = new ParamCargoSearch();
            paramCargo.setFrom(param.getFrom());
            paramCargo.setTo(param.getTo());
            paramCargo.setStatus(param.getStatus());
            paramCargo.setIdvendor(ven.getId());
            List<CargoDataReportStatusTagihanCargo> listCargo = cargoService.getListCargoReportStatusTagihanCargo(idcompany,idbranch,paramCargo);
            long totalKoliPerVendor = 0L;
            Double totalGrossInvoicePerVendor = 0.0;
            Double totalPPNPerVendor = 0.0;
            Double totalPPN23PerVendor = 0.0;
            Double totalNetInvoicePerVendor = 0.0;
            if(listCargo != null && listCargo.size() > 0){
                for(CargoDataReportStatusTagihanCargo cargo : listCargo){
                    colomcount = 0;
                    rowcount++;
                    row = sheet.createRow(rowcount);
                    createCell(row, colomcount, ven.getAlias(), style, sheet,columns);

                    String transDate = "";
                    try {
                        transDate = GlobalFunc.getDateLongToString(cargo.getDate().getTime(), "dd-MMMM-yyyy");
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    colomcount++;
                    createCell(row, colomcount, transDate, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, cargo.getInvoicenumber(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, cargo.getSmunumber(), style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, cargo.getAwbnumber(), style, sheet,columns);

                    totalKoliPerVendor += cargo.getKoli().longValue();
                    colomcount++;
                    createCell(row, colomcount, cargo.getKoli(), style, sheet,columns);

                    totalGrossInvoicePerVendor += cargo.getGrossamount().doubleValue();

                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(cargo.getGrossamount())) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, cargo.getGrossamount(), styleAmount, sheet,columns);

                    totalPPNPerVendor += cargo.getPpnamount().doubleValue();
                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(cargo.getPpnamount())) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, cargo.getPpnamount(), styleAmount, sheet,columns);

                    totalPPN23PerVendor += cargo.getPpn23amount().doubleValue();
                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(cargo.getPpn23amount())) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, cargo.getPpn23amount(), styleAmount, sheet,columns);

                    totalNetInvoicePerVendor += cargo.getNetamount().doubleValue();
                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(cargo.getNetamount())) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, cargo.getNetamount(), styleAmount, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, (cargo.getOutstanding() >= 1?"BELUM LUNAS":"LUNAS"), style, sheet,columns);

                    colomcount++;
                    Integer[] rowcol = {rowcount,colomcount};
                    mapsIdCargoRowColomIndex.put(cargo.getId(),rowcol);
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);
                }

                colomcount = 0;
                rowcount++;
                row = sheet.createRow(rowcount);
                createCell(row, colomcount, "Total", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, totalKoliPerVendor, style, sheet,columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(totalGrossInvoicePerVendor)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, totalGrossInvoicePerVendor, styleAmount, sheet,columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(totalPPNPerVendor)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, totalPPNPerVendor, styleAmount, sheet,columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(totalPPN23PerVendor)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, totalPPN23PerVendor, styleAmount, sheet,columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(totalNetInvoicePerVendor)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, totalNetInvoicePerVendor, styleAmount, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                //per vendor baru space 1 baris
                rowcount++;
            }
        }

        String idcargos = "";
        for (HashMap.Entry<Long, Integer[]> entry : mapsIdCargoRowColomIndex.entrySet()) {
            if(idcargos.equals("")){
                idcargos = entry.getKey().toString();
            } else{
                idcargos= idcargos+","+entry.getKey().toString();
            }
        }

        FilterParamPelunasanHutang paramPH = new FilterParamPelunasanHutang();
        paramPH.setListIdCargo(idcargos);
        List<PelunasanHutangReportStatusTagihanCargo> listPH = pelunasanHutangService.getListReportStatusTagihanCargo(idcompany,idbranch,paramPH);
        String noDocPH = "";
        String notesPH = "";
        long idcargo = 0L;
        for(PelunasanHutangReportStatusTagihanCargo ph : listPH){
                if(idcargo == 0L){
                    noDocPH = ph.getNodocument();
                    notesPH = ph.getNotes();
                    idcargo = ph.getIdcargo().longValue();
                } else if(idcargo == ph.getIdcargo().longValue()){
                    if(noDocPH.equals("")){
                        noDocPH = ph.getNodocument();
                        notesPH = ph.getNotes();
                    }else{
                        noDocPH = noDocPH+","+ph.getNodocument();
                        notesPH = notesPH+","+ph.getNotes();
                    }
                }else{
                    Integer[]  maps = mapsIdCargoRowColomIndex.get(idcargo);
                    row = sheet.getRow(maps[0].intValue());
                    colomcount = maps[1].intValue();
                    createCell(row, colomcount, noDocPH, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, notesPH, style, sheet,columns);

                    idcargo = ph.getIdcargo().longValue();
                    noDocPH = ph.getNodocument();
                    notesPH = ph.getNotes();
                }
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportTemplate reportTemplateStatusTagihanCargo(long idcompany, long idbranch) {
        ReportTemplate data = new ReportTemplate();

        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setVendorTypes("'CARGO','UPI'");
        data.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch,paramVendor));
        return data;
    }

    @Override
    public ReportWorkBookExcel reportHutang(long idcompany, long idbranch, ParamReportHutang param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Status Tagihan Cargo");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

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

        boolean isMixingVendor = false;

        String[] arrVendor = param.getVendorType().split(",");
        String namaVendorType = "";
        boolean isVendorUdang = false;
        boolean isVendorCargo = false;
        boolean isVendorUPI = false;
        if(arrVendor.length == 1){
            namaVendorType = arrVendor[0];
            if(arrVendor[0].equals("UDANG")){
                isVendorUdang = true;
            }else if(arrVendor[0].equals("CARGO") || arrVendor[0].equals("UPI")){
                isVendorCargo = true;
                isVendorUPI = true;
            }else if(arrVendor[0].equals("ALL")){
                isVendorUdang = true;
                isVendorCargo = true;
                isVendorUPI = true;
            }
        }else if(arrVendor.length > 0){
            for(int i=0; i < arrVendor.length; i++){
                if(namaVendorType == ""){
                    namaVendorType = arrVendor[i];
                } else{
                    namaVendorType= namaVendorType+","+arrVendor[i];
                }

                if(arrVendor[i].equals("UDANG")){
                    isVendorUdang = true;
                }else if(arrVendor[i].equals("CARGO") || arrVendor[i].equals("UPI")){
                    isVendorCargo = true;
                    isVendorUPI = true;
                }else if(arrVendor[i].equals("ALL")){
                    isVendorUdang = true;
                    isVendorCargo = true;
                    isVendorUPI = true;
                }
            }
        }
        if(isVendorUdang && (isVendorCargo || isVendorUPI)){
            isMixingVendor = true;
        }else if(isVendorUdang && isVendorCargo && isVendorUPI){
            isMixingVendor = true;
        }

        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);
        fontBold.setFontHeight(fontHeight);
        styleBold.setFont(fontBold);
        String namaVendor = "";
        ParamVendor paramvendor =  new ParamVendor();
        if(!(isVendorUdang && isVendorCargo && isVendorUPI)){
            paramvendor.setVendorTypes("'"+param.getVendorType()+"'");
        }
        if(!param.getIdvendors().equals("ALL")){
            paramvendor.setListIdVendor(param.getIdvendors());
        }
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(idcompany,idbranch,paramvendor);
        List<String> list = new ArrayList<>();
        HashMap<Long, VendorDataForTemplate> mapVendor = new HashMap<>();
        for(VendorDataForTemplate ven : getListVendor){
            mapVendor.put(ven.getId(), ven);
            list.add(ven.getNama());
        }
        if(!param.getIdvendors().equals("ALL")){
            for(String nama :list){
                if(namaVendor == ""){
                    namaVendor = nama;
                } else{
                    namaVendor= namaVendor+","+nama;
                }
            }
        }else{
            namaVendor = "ALL";
        }

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Hutang", style, sheet,columns);

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
        createCell(row, 0, "Vendor Type", style, sheet,columns);
        createCell(row, 1, namaVendorType, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Vendor", style, sheet,columns);
        createCell(row, 1, namaVendor, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Vendor", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Vendor Type", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Tanggal Document", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Document", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Invoice Amount", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Pembayaran", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Outstanding", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Doc Pembayaran", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Keterangan", style, sheet,columns);

        HashMap<Long,Integer[]> mapsIdCargoRowColomIndex = new HashMap<>();
        for(VendorDataForTemplate ven : getListVendor){
            List<ReportPelunasanHutangDocumentHutang> listHutang  = new ArrayList<>();
            if(isMixingVendor){
                ParamCargoSearch paramCargoSearch = new ParamCargoSearch();
                paramCargoSearch.setFrom(param.getFrom());
                paramCargoSearch.setTo(param.getTo());
                paramCargoSearch.setStatus(param.getStatus());
                paramCargoSearch.setIdvendor(ven.getId());
                List<ReportPelunasanHutangDocumentHutang> listCargo = cargoService.getListCargoReportHutang(idcompany,idbranch,paramCargoSearch);

                FilterParamPurchaseReceive paramPurchaseReceive = new FilterParamPurchaseReceive();
                paramPurchaseReceive.setFrom(param.getFrom());
                paramPurchaseReceive.setTo(param.getTo());
                paramPurchaseReceive.setIdvendor(ven.getId());
                paramPurchaseReceive.setStatus(param.getStatus());
                List<ReportPelunasanHutangDocumentHutang> listPR = purchaseReceiveService.getListPRReportHutang(idcompany,idbranch,paramPurchaseReceive);

                if(listCargo != null && listCargo.size() > 0){
                    listHutang.addAll(listCargo);
                }
                if(listPR != null && listPR.size() > 0){
                    listHutang.addAll(listPR);
                }
                Collections.sort(listHutang);
                if(listHutang != null && listHutang.size() > 0) {
                    HashMap<String, Object> map = createCellReportHutang(param, idcompany, idbranch, ven, listHutang, rowcount, workbook, format, row, sheet, style, styleAmount, columns);
                    Integer mapInt = (Integer) map.get("rowcount");
                    rowcount = mapInt.intValue();
                    rowcount++;
                }
            } else if(isVendorCargo || isVendorUPI){
                ParamCargoSearch paramCargoSearch = new ParamCargoSearch();
                paramCargoSearch.setFrom(param.getFrom());
                paramCargoSearch.setTo(param.getTo());
                paramCargoSearch.setStatus(param.getStatus());
                paramCargoSearch.setIdvendor(ven.getId());
                paramCargoSearch.setOrderBy("date");
                List<ReportPelunasanHutangDocumentHutang> listCargo = cargoService.getListCargoReportHutang(idcompany,idbranch,paramCargoSearch);
                if(listCargo != null && listCargo.size() > 0) {
                    HashMap<String, Object> map = createCellReportHutang(param, idcompany, idbranch, ven, listCargo, rowcount, workbook, format, row, sheet, style, styleAmount, columns);
                    Integer mapInt = (Integer) map.get("rowcount");
                    rowcount = mapInt.intValue();
                    rowcount++;
                }

            }else if(isVendorUdang){
                FilterParamPurchaseReceive paramPurchaseReceive = new FilterParamPurchaseReceive();
                paramPurchaseReceive.setFrom(param.getFrom());
                paramPurchaseReceive.setTo(param.getTo());
                paramPurchaseReceive.setIdvendor(ven.getId());
                paramPurchaseReceive.setStatus(param.getStatus());
                paramPurchaseReceive.setOrderBy("transactiondate");

                List<ReportPelunasanHutangDocumentHutang> listPR = purchaseReceiveService.getListPRReportHutang(idcompany,idbranch,paramPurchaseReceive);
                if(listPR != null && listPR.size() > 0) {
                    HashMap<String, Object> map = createCellReportHutang(param, idcompany, idbranch, ven, listPR, rowcount, workbook, format, row, sheet, style, styleAmount, columns);
                    Integer mapInt = (Integer) map.get("rowcount");
                    rowcount = mapInt.intValue();
                    rowcount++;
                }

            }
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportTemplate reportTemplateReportHutang(long idcompany, long idbranch) {
        ReportTemplate data = new ReportTemplate();

        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setVendorTypes("");
        data.setVendorOpt(vendorService.getListDropdown(idcompany,idbranch,paramVendor));
        return data;
    }

    @Override
    public ReportWorkBookExcel reportPiutang(long idcompany, long idbranch, ParamReportPiutang param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Piutang");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

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

        List<CustomerGrup> listCustGrup = customerService.getListCustomerGrup(idcompany,idbranch);
        HashMap<String,String> mappingGrop = new HashMap<>();
        for(CustomerGrup grup : listCustGrup){
            mappingGrop.put(grup.getGrupcode(), grup.getGrup());
        }
        String[] arrCustomerGrup = param.getListGroup().split(",");
        String namaGrup = "";
        String listGrupCode = param.getListGroup();
        if(arrCustomerGrup.length == 1){
            if(!arrCustomerGrup[0].equals("ALL")){
                namaGrup =  mappingGrop.get(arrCustomerGrup[0]) ;
                listGrupCode = "'"+arrCustomerGrup[0]+"'";
            }else{
                listGrupCode = "";
                namaGrup = "ALL";
            }

        }else if(arrCustomerGrup.length > 0){
            for(int i=0; i < arrCustomerGrup.length; i++){
                String grupCode = arrCustomerGrup[i];
                if(namaGrup == ""){
                    namaGrup = mappingGrop.get(grupCode);
                    listGrupCode = "'"+grupCode+"'";
                } else{
                    namaGrup= namaGrup+","+mappingGrop.get(grupCode);
                    listGrupCode = listGrupCode+ "'"+grupCode+"'";
                }
            }
        }

        String customerName = "ALL";
        String[] arrCustomer = param.getListidcustomer().split(",");
        if(!param.getListidcustomer().equals("ALL")){
            customerName = "";
            List<CustomerForReport> listcust = customerService.getListCustomerForReport(idcompany,idbranch,param.getListidcustomer());
            for(CustomerForReport cust : listcust){
                if(customerName == ""){
                    customerName = cust.getNama();
                } else{
                    customerName= customerName+","+cust.getNama();
                }
            }
        }

        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);
        fontBold.setFontHeight(fontHeight);
        styleBold.setFont(fontBold);

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Piutang", style, sheet,columns);

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
        createCell(row, 0, "Customer Grup", style, sheet,columns);
        createCell(row, 1, namaGrup, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Customer", style, sheet,columns);
        createCell(row, 1, customerName, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Status", style, sheet,columns);
        createCell(row, 1, param.getStatus(), style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Customer Name", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Customer Grup", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Tanggal Dokumen", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Dokumen", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Flight Number", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "AWB", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Koli", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Invoice Amount ($)", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Kurs", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Invoice Amount (Rp)", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Oustanding (Rp)", style, sheet,columns);

        ParamSearchInvoice paramInv = new ParamSearchInvoice();
        paramInv.setFrom(param.getFrom());
        paramInv.setTo(param.getTo());
        if(!param.getListidcustomer().equals("ALL")){
            paramInv.setIdcustomer(Long.parseLong(param.getListidcustomer()));
        }
        if(!param.getStatus().equals("ALL")){
            paramInv.setStatus(param.getStatus());
        }
        if(!param.getListGroup().equals("ALL")){
            paramInv.setListGroup(listGrupCode);
        }
        List<InvoiceDataReportPiutang> listinv = invoiceService.getListInvoiceReportPiutang(idcompany,idbranch,paramInv);
        long totalKoli = 0L;
        double totalInvAmount = 0;
        double totalInvAmountRp = 0;
        double totalOutstandingAmountRp = 0;
        if(listinv != null && listinv.size() > 0) {
            for (InvoiceDataReportPiutang inv : listinv) {
                colomcount = 0;
                rowcount++;
                row = sheet.createRow(rowcount);
                createCell(row, colomcount, inv.getCustomerName(), style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getCustomerGrup(), style, sheet, columns);

                String tanggalDoc = "";
                try {
                    tanggalDoc = GlobalFunc.getDateLongToString(inv.getDate().getTime(), "dd-MMMM-yyyy");
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                colomcount++;
                createCell(row, colomcount, tanggalDoc, style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getNoDocument(), style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getFlightnumber(), style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getAwb(), style, sheet, columns);

                totalKoli = totalKoli + inv.getKoli().longValue();
                colomcount++;
                createCell(row, colomcount, inv.getKoli(), style, sheet, columns);

                totalInvAmount = totalInvAmount + inv.getInvoiceAmount().doubleValue();
                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(inv.getInvoiceAmount())) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, inv.getInvoiceAmount(), styleAmount, sheet, columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(inv.getKurs())) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, inv.getKurs(), styleAmount, sheet, columns);

                double invAmountRp = inv.getInvoiceAmount().doubleValue() * inv.getKurs().doubleValue();
                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(invAmountRp)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));

                }
                totalInvAmountRp = totalInvAmountRp + invAmountRp;
                colomcount++;
                createCell(row, colomcount, invAmountRp, styleAmount, sheet, columns);

                double oustandingAmountRp = inv.getOutstanding().doubleValue() * inv.getKurs().doubleValue();
                if(inv.getOutstanding().doubleValue() >= 1){
                    totalOutstandingAmountRp = totalOutstandingAmountRp + oustandingAmountRp;
                }
                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(oustandingAmountRp)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                if(inv.getOutstanding().doubleValue() < 1){
                    createCell(row, colomcount, 0, style, sheet, columns);
                }else{
                    createCell(row, colomcount, oustandingAmountRp, styleAmount, sheet, columns);
                }

            }

            colomcount = 0;
            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, colomcount, "Total", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, totalKoli, style, sheet, columns);

            styleAmount = workbook.createCellStyle();
            if(GlobalFunc.checkIsDecimal(totalInvAmount)) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.##"));
            }
            colomcount++;
            createCell(row, colomcount, totalInvAmount, styleAmount, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            styleAmount = workbook.createCellStyle();
            if(GlobalFunc.checkIsDecimal(totalInvAmountRp)) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.##"));
            }
            colomcount++;
            createCell(row, colomcount, totalInvAmountRp, styleAmount, sheet, columns);

            styleAmount = workbook.createCellStyle();
            if(GlobalFunc.checkIsDecimal(totalOutstandingAmountRp)) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.##"));
            }
            colomcount++;
            createCell(row, colomcount, totalOutstandingAmountRp, styleAmount, sheet, columns);
        }
        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportTemplate reportTemplateReportPiutang(long idcompany, long idbranch) {
        ReportTemplate data = new ReportTemplate();
        data.setCustomerOpt(customerService.getListAll(idcompany,idbranch));
        data.setCustomerGrupOpt(customerService.getListCustomerGrup(idcompany,idbranch));
        return data;
    }

    @Override
    public ReportWorkBookExcel reportPenjualan(long idcompany, long idbranch, ParamReportPenjualan param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Piutang");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

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

        List<CustomerGrup> listCustGrup = customerService.getListCustomerGrup(idcompany,idbranch);
        HashMap<String,String> mappingGrop = new HashMap<>();
        for(CustomerGrup grup : listCustGrup){
            mappingGrop.put(grup.getGrupcode(), grup.getGrup());
        }
        String[] arrCustomerGrup = param.getListGroup().split(",");
        String namaGrup = "";
        String listGrupCode = param.getListGroup();
        if(arrCustomerGrup.length == 1){
            if(!arrCustomerGrup[0].equals("ALL")){
                namaGrup =  mappingGrop.get(arrCustomerGrup[0]) ;
                listGrupCode = "'"+arrCustomerGrup[0]+"'";
            }else{
                listGrupCode = "";
                namaGrup = "ALL";
            }

        }else if(arrCustomerGrup.length > 0){
            for(int i=0; i < arrCustomerGrup.length; i++){
                String grupCode = arrCustomerGrup[i];
                if(namaGrup == ""){
                    namaGrup = mappingGrop.get(grupCode);
                    listGrupCode = "'"+grupCode+"'";
                } else{
                    namaGrup= namaGrup+","+mappingGrop.get(grupCode);
                    listGrupCode = listGrupCode + "'"+grupCode+"'";
                }
            }
        }

        String customerName = "ALL";
        String[] arrCustomer = param.getListidcustomer().split(",");
        if(!param.getListidcustomer().equals("ALL")){
            customerName = "";
            List<CustomerForReport> listcust = customerService.getListCustomerForReport(idcompany,idbranch,param.getListidcustomer());
            for(CustomerForReport cust : listcust){
                if(customerName == ""){
                    customerName = cust.getNama();
                } else{
                    customerName= customerName+","+cust.getNama();
                }
            }
        }

        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);
        fontBold.setFontHeight(fontHeight);
        styleBold.setFont(fontBold);

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Penjualan", style, sheet,columns);

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
        createCell(row, 0, "Customer Grup", style, sheet,columns);
        createCell(row, 1, namaGrup, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Customer", style, sheet,columns);
        createCell(row, 1, customerName, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Tanggal Dokumen", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Customer Name", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Customer Grup", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Dokumen", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Flight Number", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "AWB", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Koli", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Invoice Amount ($)", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Kurs", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Invoice Amount (Rp)", style, sheet,columns);

        ParamSearchInvoice paramInv = new ParamSearchInvoice();
        paramInv.setFrom(param.getFrom());
        paramInv.setTo(param.getTo());
        if(!param.getListidcustomer().equals("ALL")){
            paramInv.setIdcustomer(Long.parseLong(param.getListidcustomer()));
        }
        if(!param.getListGroup().equals("ALL")){
            paramInv.setListGroup(listGrupCode);
        }

        List<InvoiceDataReportPiutang> listinv = invoiceService.getListInvoiceReportPiutang(idcompany,idbranch,paramInv);
        long totalKoli = 0L;
        double totalInvAmount = 0;
        double totalInvAmountRp = 0;
        if(listinv != null && listinv.size() > 0) {
            for (InvoiceDataReportPiutang inv : listinv) {
                colomcount = 0;
                rowcount++;
                row = sheet.createRow(rowcount);

                String tanggalDoc = "";
                try {
                    tanggalDoc = GlobalFunc.getDateLongToString(inv.getDate().getTime(), "dd-MMMM-yyyy");
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                createCell(row, colomcount, tanggalDoc, style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getCustomerName(), style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getCustomerGrup(), style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getNoDocument(), style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getFlightnumber(), style, sheet, columns);

                colomcount++;
                createCell(row, colomcount, inv.getAwb(), style, sheet, columns);

                totalKoli = totalKoli + inv.getKoli().longValue();
                colomcount++;
                createCell(row, colomcount, inv.getKoli(), style, sheet, columns);

                totalInvAmount = totalInvAmount + inv.getInvoiceAmount().doubleValue();
                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(inv.getInvoiceAmount())) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, inv.getInvoiceAmount(), styleAmount, sheet, columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(inv.getKurs())) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, inv.getKurs(), styleAmount, sheet, columns);

                double invAmountRp = inv.getInvoiceAmount().doubleValue() * inv.getKurs().doubleValue();
                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(invAmountRp)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));

                }
                totalInvAmountRp = totalInvAmountRp + invAmountRp;
                colomcount++;
                createCell(row, colomcount, invAmountRp, styleAmount, sheet, columns);
            }

            colomcount = 0;
            rowcount++;
            row = sheet.createRow(rowcount);
            createCell(row, colomcount, "Total", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            colomcount++;
            createCell(row, colomcount, totalKoli, style, sheet, columns);

            styleAmount = workbook.createCellStyle();
            if(GlobalFunc.checkIsDecimal(totalInvAmount)) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.##"));
            }
            colomcount++;
            createCell(row, colomcount, totalInvAmount, styleAmount, sheet, columns);

            colomcount++;
            createCell(row, colomcount, "", style, sheet, columns);

            styleAmount = workbook.createCellStyle();
            if(GlobalFunc.checkIsDecimal(totalInvAmountRp)) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.##"));
            }
            colomcount++;
            createCell(row, colomcount, totalInvAmountRp, styleAmount, sheet, columns);
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel reportPelunasanPiutang(long idcompany, long idbranch, ParamReportPelunasanPiutang param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Piutang");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(20);

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

        List<CustomerGrup> listCustGrup = customerService.getListCustomerGrup(idcompany,idbranch);
        HashMap<String,String> mappingGrop = new HashMap<>();
        for(CustomerGrup grup : listCustGrup){
            mappingGrop.put(grup.getGrupcode(), grup.getGrup());
        }
        String[] arrCustomerGrup = param.getListGroup().split(",");
        String namaGrup = "";
        String listGrupCode = param.getListGroup();
        if(arrCustomerGrup.length == 1){
            if(!arrCustomerGrup[0].equals("ALL")){
                namaGrup =  mappingGrop.get(arrCustomerGrup[0]) ;
                listGrupCode = "'"+arrCustomerGrup[0]+"'";
            }else{
                listGrupCode = "";
                namaGrup = "ALL";
            }

        }else if(arrCustomerGrup.length > 0){
            for(int i=0; i < arrCustomerGrup.length; i++){
                String grupCode = arrCustomerGrup[i];
                if(namaGrup == ""){
                    namaGrup = mappingGrop.get(grupCode);
                    listGrupCode = "'"+grupCode+"'";
                } else{
                    namaGrup= namaGrup+","+mappingGrop.get(grupCode);
                    listGrupCode = listGrupCode+ "'"+grupCode+"'";
                }
            }
        }

        String customerName = "ALL";
        String[] arrCustomer = param.getListidcustomer().split(",");
        if(!param.getListidcustomer().equals("ALL")){
            customerName = "";
            List<CustomerForReport> listcust = customerService.getListCustomerForReport(idcompany,idbranch,param.getListidcustomer());
            for(CustomerForReport cust : listcust){
                if(customerName == ""){
                    customerName = cust.getNama();
                } else{
                    customerName= customerName+","+cust.getNama();
                }
            }
        }

        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);
        fontBold.setFontHeight(fontHeight);
        styleBold.setFont(fontBold);

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Pelunasan Piutang", style, sheet,columns);

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
        createCell(row, 0, "Customer Grup", style, sheet,columns);
        createCell(row, 1, namaGrup, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Customer", style, sheet,columns);
        createCell(row, 1, customerName, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Status", style, sheet,columns);
        createCell(row, 1, param.getStatus(), style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Customer Name", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Customer Grup", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Tanggal Dokumen", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Dokumen", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Flight Number", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "AWB", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Koli", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Invoice Amount ($)", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Kurs", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Invoice Amount (Rp)", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Pembayaran($)", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Outstanding($)", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Document Pembayaran", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Metode Pembayaran", style, sheet,columns);

        ParamSearchInvoice paramInv = new ParamSearchInvoice();
        paramInv.setFrom(param.getFrom());
        paramInv.setTo(param.getTo());
        if(!param.getListidcustomer().equals("ALL")){
            paramInv.setIdcustomer(Long.parseLong(param.getListidcustomer()));
        }
        if(!param.getStatus().equals("ALL")){
            paramInv.setStatus(param.getStatus());
        }
        if(!param.getListGroup().equals("ALL")){
            paramInv.setListGroup(listGrupCode);
        }
        List<InvoiceDataReportPelunasanPiutang> listinv = invoiceService.getListInvoiceReportPelunasanPiutang(idcompany,idbranch,paramInv);

        if(listinv != null && listinv.size() > 0) {
            String listIdInvoice = listinv.toString().replaceAll("\\[","");
            listIdInvoice = listIdInvoice.replaceAll("\\]","");
            FilterParamPelunasanPiutang paramPP = new FilterParamPelunasanPiutang();
            paramPP.setFrom(param.getFrom());
            paramPP.setTo(param.getTo());
            paramPP.setListIdInvoice(listIdInvoice);
            List<ReportPelunasanPiutang> listPP = pelunasanPiutangService.getReportPelunasanPiutang(idcompany,idbranch,paramPP);
            HashMap<Long,List<ReportPelunasanPiutang>> grupByIdInvoice = new HashMap<>();
            if(listPP != null && listPP.size() > 0){
                List<ReportPelunasanPiutang> listPPTemp = new ArrayList<>();
                for(ReportPelunasanPiutang pp : listPP){
                    if(grupByIdInvoice.get(pp.getIdinvoice()) == null){
                        listPPTemp = new ArrayList<>();
                        listPPTemp.add(pp);
                        grupByIdInvoice.put(pp.getIdinvoice(),listPPTemp);
                    }else{
                        listPPTemp = new ArrayList<>();
                        listPPTemp = grupByIdInvoice.get(pp.getIdinvoice());
                        listPPTemp.add(pp);
                        grupByIdInvoice.put(pp.getIdinvoice(),listPPTemp);
                    }
                }
            }

            for(InvoiceDataReportPelunasanPiutang inv : listinv){
                colomcount = 0;
                rowcount++;
                row = sheet.createRow(rowcount);
                createCell(row, colomcount, inv.getCustomerName(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, inv.getCustomerGrup(), style, sheet,columns);

                String tanggalDoc = "";
                try {
                    tanggalDoc = GlobalFunc.getDateLongToString(inv.getDate().getTime(), "dd-MMMM-yyyy");
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                colomcount++;
                createCell(row, colomcount, tanggalDoc, style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, inv.getNoDocument(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, inv.getFlightnumber(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, inv.getAwb(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, inv.getKoli(), style, sheet,columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(inv.getInvoiceAmount())) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, inv.getInvoiceAmount(), styleAmount, sheet,columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(inv.getKurs())) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, inv.getKurs(), styleAmount, sheet,columns);

                double invAmountRp = inv.getInvoiceAmount().doubleValue() * inv.getKurs().doubleValue();
                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(invAmountRp)) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));

                }
                colomcount++;
                createCell(row, colomcount, invAmountRp, styleAmount, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                Double invAmount = inv.getInvoiceAmount();
                invAmount = round(invAmount,2);
                List<ReportPelunasanPiutang> listPembayaran = grupByIdInvoice.get(inv.getId());
                if(listPembayaran != null && listPembayaran.size() > 0){
                    for(ReportPelunasanPiutang pembayaran : listPembayaran){
                        colomcount = 0;
                        rowcount++;
                        row = sheet.createRow(rowcount);
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        tanggalDoc = "";
                        try {
                            tanggalDoc = GlobalFunc.getDateLongToString(pembayaran.getDate().getTime(), "dd-MMMM-yyyy");
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        colomcount++;
                        createCell(row, colomcount, tanggalDoc, style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);


                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        //Pembayaran($)
                        Double amountPembayaran = pembayaran.getBiayabebanudangmati().doubleValue()+pembayaran.getBiayabank().doubleValue()+ pembayaran.getPembayaran().doubleValue();
                        amountPembayaran = round(amountPembayaran,2);
                        styleAmount = workbook.createCellStyle();
                        if(GlobalFunc.checkIsDecimal(amountPembayaran)) {
                            styleAmount.setDataFormat(format.getFormat("#,###"));
                        }else {
                            styleAmount.setDataFormat(format.getFormat("#,###.##"));

                        }
                        colomcount++;
                        createCell(row, colomcount, amountPembayaran, styleAmount, sheet,columns);

                        invAmount = invAmount - amountPembayaran;
                        if(invAmount >= 1){
                            styleAmount = workbook.createCellStyle();
                            if(GlobalFunc.checkIsDecimal(invAmount)) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));

                            }
                            colomcount++;
                            createCell(row, colomcount, invAmount, styleAmount, sheet,columns);
                        }else{
                            colomcount++;
                            createCell(row, colomcount, 0, style, sheet,columns);
                        }


                        colomcount++;
                        createCell(row, colomcount, pembayaran.getNodocument(), style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, pembayaran.getMetodepembayaran(), style, sheet,columns);
                    }
                }
                rowcount++;
            }


        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel reportKartuDeposit(long idcompany, long idbranch, ParamReportKartuDeposit param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Kartu Deposit");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

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

        String namaVendor = "";
        ParamVendor paramvendor =  new ParamVendor();
        if(param.getIdvendors().equals("ALL")){
            paramvendor.setVendorTypes("'UDANG'");
        }else{
            paramvendor.setListIdVendor(param.getIdvendors());
        }
        List<String> list = new ArrayList<>();
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(idcompany,idbranch,paramvendor);
        List<String> idvendors = new ArrayList<>();
        for(VendorDataForTemplate ven : getListVendor){
            list.add(ven.getNama()+" ("+ven.getAlias()+")");
            idvendors.add(ven.getId().toString());
        }
        String listIdVendor = idvendors.toString().replaceAll("\\[","");
        listIdVendor = listIdVendor.replaceAll("\\]","");
        if(!param.getIdvendors().equals("ALL")){
            for(String nama :list){
                if(namaVendor == ""){
                    namaVendor = nama;
                } else{
                    namaVendor= namaVendor+","+nama;
                }
            }
        }else{
            namaVendor = "ALL";
        }

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Kartu Deposit", style, sheet,columns);

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
        createCell(row, 0, "Vendor", style, sheet,columns);
        createCell(row, 1, namaVendor, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Show 0?", style, sheet,columns);
        createCell(row, 1, param.getShowNol(), style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Vendor", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Tanggal", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Saldo Awal", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Deposit Keluar", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Deposit Masuk", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Saldo Akhir", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Document Number", style, sheet,columns);

        if(getListVendor != null && getListVendor.size() > 0){
            List<ReportKartuDeposit> listKartuDeposit = new ArrayList<>();
            ParamList paramdp = new ParamList();
            paramdp.setFrom(param.getFrom());
            paramdp.setTo(param.getTo());
            paramdp.setListIdVendor(listIdVendor);
            List<ReportKartuDeposit> listdp = depositService.getListReportKartuDeposit(idcompany,idbranch,paramdp);

            FilterParamPurchaseReceive paramPR = new FilterParamPurchaseReceive();
            paramPR.setFrom(param.getFrom());
            paramPR.setTo(param.getTo());
            paramPR.setListIdVendor(listIdVendor);
            List<ReportKartuDeposit> listSetorPR = purchaseReceiveService.getListPrReportKartuDeposit(idcompany,idbranch,paramPR);

            if(listdp != null && listdp.size() > 0){
                listKartuDeposit.addAll(listdp);
            }

            if(listSetorPR != null && listSetorPR.size() > 0){
                listKartuDeposit.addAll(listSetorPR);
            }
            if(listKartuDeposit != null && listKartuDeposit.size() > 0){
                Collections.sort(listKartuDeposit);
            }
            HashMap<Long, List<ReportKartuDeposit>> grupByVendor = new HashMap<>();
            HashMap<Long, Double> grupByVendorMasuk = new HashMap<>();
            HashMap<Long, Double> grupByVendorKeluar = new HashMap<>();
            List<ReportKartuDeposit> listKDTemp = new ArrayList<>();
            if(listKartuDeposit != null && listKartuDeposit.size() > 0){
                for(ReportKartuDeposit kd : listKartuDeposit){
                    if(grupByVendor.get(kd.getIdvendor()) == null){
                        listKDTemp = new ArrayList<>();
                        listKDTemp.add(kd);
                        grupByVendor.put(kd.getIdvendor(),listKDTemp);
                    }else{
                        listKDTemp = new ArrayList<>();
                        listKDTemp = grupByVendor.get(kd.getIdvendor());
                        listKDTemp.add(kd);
                        grupByVendor.put(kd.getIdvendor(),listKDTemp);
                    }

                    if(param.getShowNol().equals("NO")){
                        if(kd.getType().equals("DEPOSIT")){
                            if(grupByVendorMasuk.get(kd.getIdvendor()) == null){
                                grupByVendorMasuk.put(kd.getIdvendor(),kd.getAmount());
                            }else {
                                Double amt = kd.getAmount().doubleValue() + grupByVendorMasuk.get(kd.getIdvendor()).doubleValue();
                                grupByVendorMasuk.put(kd.getIdvendor(),amt);
                            }
                        }else{
                            if(grupByVendorKeluar.get(kd.getIdvendor()) == null){
                                grupByVendorKeluar.put(kd.getIdvendor(),kd.getAmount());
                            }else {
                                Double amt = kd.getAmount().doubleValue() + grupByVendorKeluar.get(kd.getIdvendor()).doubleValue();
                                grupByVendorKeluar.put(kd.getIdvendor(),amt);
                            }
                        }
                    }

                }
            }

            for(VendorDataForTemplate ven : getListVendor){
                Double saldoAwal = depositService.calculateSaldoDepositByIdVendorAndBeforeDate(idcompany,idbranch, ven.getId(), param.getFrom());
                Double saldo = saldoAwal;
                if(param.getShowNol().equals("NO")){
                    Double tempSaldo = saldoAwal;
                    Double saldomasuk = grupByVendorMasuk.get(ven.getId());
                    Double saldokeluar = grupByVendorKeluar.get(ven.getId());
                    if(saldomasuk != null){
                        tempSaldo = tempSaldo.doubleValue() + saldomasuk.doubleValue();
                    }
                    if(saldokeluar != null){
                        tempSaldo = tempSaldo.doubleValue() - saldokeluar.doubleValue();
                    }
                    if(tempSaldo.doubleValue() < 1){
                        continue;
                    }
                }
                colomcount = 0;
                rowcount++;
                row = sheet.createRow(rowcount);
                createCell(row, colomcount, ven.getNama()+" ("+ven.getAlias()+")", style, sheet,columns);

                String transDate = "";
                try {
                    transDate = GlobalFunc.getDateLongToString(param.getFrom(), "dd-MMMM-yyyy");
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                colomcount++;
                createCell(row, colomcount, transDate, style, sheet,columns);

                if(saldoAwal.doubleValue() > 1){
                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(saldoAwal)) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, saldoAwal, styleAmount, sheet,columns);
                }else{
                    colomcount++;
                    createCell(row, colomcount, 0, style, sheet,columns);
                }


                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                List<ReportKartuDeposit> listKD = grupByVendor.get(ven.getId());
                if(listKD != null && listKD.size() > 0){
                    for(ReportKartuDeposit kd : listKD){
                        colomcount = 0;
                        rowcount++;
                        row = sheet.createRow(rowcount);
                        createCell(row, colomcount, ven.getAlias(), style, sheet,columns);

                        transDate = "";
                        try {
                            transDate = GlobalFunc.getDateLongToString(kd.getDate().getTime(), "dd-MMMM-yyyy");
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        colomcount++;
                        createCell(row, colomcount, transDate, style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        styleAmount = workbook.createCellStyle();
                        if(GlobalFunc.checkIsDecimal(kd.getAmount())) {
                            styleAmount.setDataFormat(format.getFormat("#,###"));
                        }else {
                            styleAmount.setDataFormat(format.getFormat("#,###.##"));
                        }
                        if(kd.getType().equals("DEPOSIT")){
                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);

                            saldo = saldo + kd.getAmount();
                        }else{
                            colomcount++;
                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);
                            saldo = saldo - kd.getAmount();
                        }


                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, kd.getDocumentNumber(), style, sheet,columns);
                    }

                    colomcount = 0;
                    rowcount++;
                    row = sheet.createRow(rowcount);
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    styleAmount = workbook.createCellStyle();
                    if(GlobalFunc.checkIsDecimal(saldo)) {
                        styleAmount.setDataFormat(format.getFormat("#,###"));
                    }else {
                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
                    }
                    colomcount++;
                    createCell(row, colomcount, saldo, styleAmount, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);
                }

                rowcount++;
            }
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportTemplate reportTemplateReportKartuDeposit(long idcompany, long idbranch) {
        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setVendorTypes("'UDANG'");
        ReportTemplate data = new ReportTemplate();
        data.setVendorOpt(vendorService.getListDropdown (idcompany,idbranch,paramVendor));
        return data;
    }

    @Override
    public ReportWorkBookExcel reportReportKartuStock(long idcompany, long idbranch, ParamReportKartuStock param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Kartu Stock");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

//        List<ListProductData> listPd = productService.getListAll(idcompany,idbranch);
        String namaCabang = "";
        Branch branch = branchService.getBranchByID(idbranch);
        String idProducts = "";
        if(branch != null){
            namaCabang = branch.getNama();
        }
        String namaProduct = "";

        if(param.getListIdProduct().equals("ALL")){
            namaProduct = "All";
        }else{
            idProducts = param.getListIdProduct();
        }
        ParamProduct paramProduct = new ParamProduct();
        paramProduct.setListIdProduct(idProducts);
        List<ListProductData> listProd = productService.getListAll(idcompany,idbranch,paramProduct);
        if(!param.getListIdProduct().equals("ALL")){
            for(ListProductData prod : listProd){
                if(namaProduct == ""){
                    namaProduct = prod.getNama();
                } else{
                    namaProduct= namaProduct+","+prod.getNama();
                }
            }
        }

        String idCategoryProducts = "";
        String namaCP = "";
        if(param.getListIdCategoryProduct().equals("ALL")){
            namaCP = "All";
        }else{
            ParamSearchMappingStock paramMS =new ParamSearchMappingStock();
            paramMS.setListIdCategoryProduct(param.getListIdCategoryProduct());
            List<Long> listCPID = mappingStockService.getCategoryProducts(idcompany,idbranch,paramMS);
            if(listCPID != null && listCPID.size() > 0){
                idCategoryProducts = listCPID.toString().replaceAll("\\[","");
                idCategoryProducts = idCategoryProducts.replaceAll("\\]","");
                idCategoryProducts = idCategoryProducts+","+param.getListIdCategoryProduct();
            }else{
                idCategoryProducts = param.getListIdCategoryProduct();
            }

        }

        ParamTemplate paramCP = new ParamTemplate();
        if(!param.getListIdCategoryProduct().equals("ALL")){
            paramCP.setListidcategoryproduct(param.getListIdCategoryProduct());
        }
        List<CategoryProductList> listCP = categoryProductService.getDataForTemplate(idcompany,idbranch,paramCP);

        if(!param.getListIdCategoryProduct().equals("ALL")){
            for(CategoryProductList cp : listCP){
                if(namaCP == ""){
                    namaCP = cp.getSize();
                } else{
                    namaCP= namaCP+","+cp.getSize();
                }
            }
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
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Kartu Stock", style, sheet,columns);

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
        createCell(row, 0, "Product", style, sheet,columns);
        createCell(row, 1, namaProduct, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Ukuran", style, sheet,columns);
        createCell(row, 1, namaCP, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);


        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Produk", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Ukuran", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Tanggal", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Qty", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Qty In", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Qty Out", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Document", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Nama", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Keterangan", style, sheet,columns);

        String listIdProduct = "";
        if(!param.getListIdProduct().equals("ALL")){
            listIdProduct = param.getListIdProduct();
        }
        ParamCalculateQtySA paramSA = new ParamCalculateQtySA();
        paramSA.setDateFrom(param.getFrom());
        paramSA.setDateThru(param.getTo());
        paramSA.setListidproduct(listIdProduct);
        paramSA.setListidcategoryproduct(idCategoryProducts);
        List<ReportKartuStock> itemsSA = stockAdjusmentService.getListReportKartuStock(idcompany,idbranch,paramSA);

        FilterParamPurchaseReceive paramPR = new FilterParamPurchaseReceive();
        paramPR.setFrom(param.getFrom());
        paramPR.setTo(param.getTo());
        paramPR.setListIdProduct(listIdProduct);
        paramPR.setListIdCategoryProduct(idCategoryProducts);
        paramPR.setType("H");
        List<ReportKartuStock> itemsPR = purchaseReceiveService.getListPrReportKartuStock(idcompany,idbranch,paramPR);

        ParamSearchPackingList paramPL = new ParamSearchPackingList();
        paramPL.setFrom(param.getFrom());
        paramPL.setTo(param.getTo());
        paramPL.setListIdProduct(listIdProduct);
        paramPL.setListIdCategoryProduct(idCategoryProducts);
        List<ReportKartuStock> itemsPL = packingListService.getListReportKartuStock(idcompany,idbranch,paramPL);

        List<ReportKartuStock> listItems = new ArrayList<>();
        if(itemsSA != null && itemsSA.size() > 0){
            listItems.addAll(itemsSA);
        }
        if(itemsPR != null && itemsPR.size() > 0){
            listItems.addAll(itemsPR);
        }
        if(itemsPL != null && itemsPL.size() > 0){
            listItems.addAll(itemsPL);
        }
        Collections.sort(listItems);

        List<MappingStockList> listMapping = mappingStockService.getListAll(idcompany,idbranch);
        HashMap<Long, Long> mapMapStock = new HashMap<>();
        HashMap<Long, List<String>> mapMapStockByIDMapping = new HashMap<>();
        for(MappingStockList val : listMapping){
            mapMapStock.put(val.getCategoryproductid(), val.getCategoryproductidmapping());
            List<String> tempList = new ArrayList<>();
            if(mapMapStockByIDMapping.get(val.getCategoryproductidmapping()) != null){
                tempList = new ArrayList<>();
                tempList = mapMapStockByIDMapping.get(val.getCategoryproductidmapping());
                tempList.add(Long.toString(val.getCategoryproductid()));
                mapMapStockByIDMapping.put(val.getCategoryproductidmapping(), tempList);
            }else{
                tempList = new ArrayList<>();
                tempList.add(Long.toString(val.getCategoryproductid()));
                mapMapStockByIDMapping.put(val.getCategoryproductidmapping(), tempList);
            }
        }

        HashMap<String, List<ReportKartuStock>> mapsGrupByIdProdAndCP = new HashMap<>();
        if(listItems != null && listItems.size() > 0){
            for(ReportKartuStock val : listItems){
                    String key = val.getIdproduct()+"-"+ val.getIdcategoryproduct();
                    Long idMapping = mapMapStock.get(val.getIdcategoryproduct());
                    if(idMapping != null){
                        key = val.getIdproduct()+"-"+ idMapping;
                    }
                    List<ReportKartuStock> tempList =  new ArrayList<>();
                    if(mapsGrupByIdProdAndCP.get(key) != null){
                        tempList = new ArrayList<>();
                        tempList =  mapsGrupByIdProdAndCP.get(key);
                        tempList.add(val);
                        mapsGrupByIdProdAndCP.put(key,tempList);
                    }else{
                        tempList = new ArrayList<>();
                        tempList.add(val);
                        mapsGrupByIdProdAndCP.put(key,tempList);
                    }

            }
        }
        Long dateMinus1 = 0L;
        try {
            dateMinus1 = GlobalFunc.addDays(param.getFrom(),-1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //56169461 = 01-Jan-70
        Long satuJan70 = 56169461L;

        if(listProd != null && listProd.size() > 0){
            for(ListProductData val : listProd){

                for(CategoryProductList valCp : listCP){
                    String namaProduk = val.getNama();
                    String key = val.getId()+"-"+ valCp.getId();
                    String size = valCp.getSize();
                    List<ReportKartuStock> tempList = mapsGrupByIdProdAndCP.get(key);
                    if(tempList != null){
                        for(ReportKartuStock valKS : tempList){

                            //Stock Awal (From)
                            if(!namaProduk.equals("")){
                                List<String> listIdCPMapping = mapMapStockByIDMapping.get(valCp.getId());
                                String idcategorys = "";
                                if(listIdCPMapping != null){
                                    idcategorys = listIdCPMapping.toString().replaceAll("\\[","");
                                    idcategorys = idcategorys.replaceAll("\\]","");
                                }

                                ParamCalculateQtyPR paramCalcPR = new ParamCalculateQtyPR();
                                paramCalcPR.setDateFrom(satuJan70);
                                paramCalcPR.setDateThru(dateMinus1);
                                paramCalcPR.setIdproduct(val.getId());
                                if(listIdCPMapping != null){
                                    paramCalcPR.setListidcategoryproduct(idcategorys);
                                }else{
                                    paramCalcPR.setIdcategoryproduct(valKS.getIdcategoryproduct());
                                }


                                ParamCalculateQtySA paramCalcSA = new ParamCalculateQtySA();
                                paramCalcSA.setDateFrom(satuJan70);
                                paramCalcSA.setDateThru(dateMinus1);
                                paramCalcSA.setIdproduct(val.getId());
                                if(listIdCPMapping != null){
                                    paramCalcSA.setListidcategoryproduct(idcategorys);
                                }else{
                                    paramCalcSA.setIdcategoryproduct(valKS.getIdcategoryproduct());
                                }

                                ParamCalculateQtyPL paramCalcPL = new ParamCalculateQtyPL();
                                paramCalcPL.setDateFrom(satuJan70);
                                paramCalcPL.setDateThru(dateMinus1);
                                paramCalcPL.setIdproduct(val.getId());
                                if(listIdCPMapping != null){
                                    paramCalcPL.setListidcategoryproduct(idcategorys);
                                }else{
                                    paramCalcPL.setIdcategoryproduct(valKS.getIdcategoryproduct());
                                }

                                ParamCalculateQty paramQty = new ParamCalculateQty();
                                paramQty.setParamCalculateQtyPR(paramCalcPR);
                                paramQty.setParamCalculateQtySA(paramCalcSA);
                                paramQty.setParamCalculateQtyPL(paramCalcPL);

                                Long stockMinus1DateFrom = stockItemService.calculateQty(idcompany,idbranch,paramQty);

                                colomcount = 0;
                                rowcount++;
                                row = sheet.createRow(rowcount);
                                createCell(row, colomcount, namaProduk, style, sheet,columns);

                                colomcount++;
                                createCell(row, colomcount, size, style, sheet,columns);

                                String tanggal = "";
                                try {
                                    tanggal = GlobalFunc.getDateLongToString(param.getFrom(), "dd-MMMM-yyyy");
                                } catch (ParseException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                colomcount++;
                                createCell(row, colomcount, tanggal, style, sheet,columns);

                                colomcount++;
                                createCell(row, colomcount, stockMinus1DateFrom, style, sheet,columns);

                                String qtyIn = "";
                                String qtyOut = "";
                                colomcount++;
                                createCell(row, colomcount, qtyIn, style, sheet,columns);

                                colomcount++;
                                createCell(row, colomcount, qtyOut, style, sheet,columns);

                                colomcount++;
                                createCell(row, colomcount, "", style, sheet,columns);

                                colomcount++;
                                createCell(row, colomcount, "", style, sheet,columns);

                                colomcount++;
                                createCell(row, colomcount, "", style, sheet,columns);

                                namaProduk = "";
                                size = "";
                            }

                            colomcount = 0;
                            rowcount++;
                            row = sheet.createRow(rowcount);
                            createCell(row, colomcount, namaProduk, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, size, style, sheet,columns);

                            String tanggal = "";
                            try {
                                tanggal = GlobalFunc.getDateLongToString(valKS.getDate().getTime(), "dd-MMMM-yyyy");
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            colomcount++;
                            createCell(row, colomcount, tanggal, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            String qtyIn = "";
                            String qtyOut = "";
                            if(valKS.getType().equals("PACKINGLIST") || valKS.getType().equals("SA_M")){
                                qtyOut = valKS.getQty().toString();
                            }else if(valKS.getType().equals("SA_H") || valKS.getType().equals("PR")){
                                qtyIn = valKS.getQty().toString();
                            }
                            colomcount++;
                            createCell(row, colomcount, qtyIn, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, qtyOut, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, valKS.getNodocument(), style, sheet,columns);

                            String nama = "";
                            if(valKS.getVendorAlias() != null){
                                nama = valKS.getVendorAlias();
                            }else if(valKS.getCustomerAlias() != null){
                                nama = valKS.getCustomerAlias();
                            }
                            colomcount++;
                            createCell(row, colomcount, nama, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, valKS.getKeterangan(), style, sheet,columns);
                        }

                        //Stock Akhir (Thru)
                            List<String> listIdCPMapping = mapMapStockByIDMapping.get(valCp.getId());
                            String idcategorys = "";
                            if(listIdCPMapping != null){
                                listIdCPMapping.add(valCp.getId().toString());
                                idcategorys = listIdCPMapping.toString().replaceAll("\\[","");
                                idcategorys = idcategorys.replaceAll("\\]","");
                            }
                            ParamCalculateQtyPR paramCalcPR = new ParamCalculateQtyPR();
                            paramCalcPR.setDateFrom(satuJan70);
                            paramCalcPR.setDateThru(param.getTo());
                            paramCalcPR.setIdproduct(val.getId());
                            if(listIdCPMapping != null){
                                paramCalcPR.setListidcategoryproduct(idcategorys);
                            }else{
                                paramCalcPR.setIdcategoryproduct(valCp.getId());
                            }

                            ParamCalculateQtySA paramCalcSA = new ParamCalculateQtySA();
                            paramCalcSA.setDateFrom(satuJan70);
                            paramCalcSA.setDateThru(param.getTo());
                            paramCalcSA.setIdproduct(val.getId());
                            if(listIdCPMapping != null){
                                paramCalcSA.setListidcategoryproduct(idcategorys);
                            }else{
                                paramCalcSA.setIdcategoryproduct(valCp.getId());
                            }

                            ParamCalculateQtyPL paramCalcPL = new ParamCalculateQtyPL();
                            paramCalcPL.setDateFrom(satuJan70);
                            paramCalcPL.setDateThru(param.getTo());
                            paramCalcPL.setIdproduct(val.getId());
                            if(listIdCPMapping != null){
                                paramCalcPL.setListidcategoryproduct(idcategorys);
                            }else{
                                paramCalcPL.setIdcategoryproduct(valCp.getId());
                            }

                            ParamCalculateQty paramQty = new ParamCalculateQty();
                            paramQty.setParamCalculateQtyPR(paramCalcPR);
                            paramQty.setParamCalculateQtySA(paramCalcSA);
                            paramQty.setParamCalculateQtyPL(paramCalcPL);
                            Long stockThru = stockItemService.calculateQty(idcompany,idbranch,paramQty);

                            colomcount = 0;
                            rowcount++;
                            row = sheet.createRow(rowcount);
                            createCell(row, colomcount, namaProduk, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, size, style, sheet,columns);

                            String tanggal = "";
                            try {
                                tanggal = GlobalFunc.getDateLongToString(param.getTo(), "dd-MMMM-yyyy");
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            colomcount++;
                            createCell(row, colomcount, tanggal, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, stockThru, style, sheet,columns);

                            String qtyIn = "";
                            String qtyOut = "";
                            colomcount++;
                            createCell(row, colomcount, qtyIn, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, qtyOut, style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            rowcount++;


                    }
                }
            }
        }
        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportTemplate reportTemplateReportKartuStock(long idcompany, long idbranch) {
        ReportTemplate data = new ReportTemplate();
        data.setProductOpt(productService.getListAll(idcompany,idbranch));
        ParamTemplate paramCP = new ParamTemplate();
        paramCP.setShowOnlyCpMapping(true);
        data.setCategoryProductOpt(categoryProductService.getDataForTemplate(idcompany,idbranch,paramCP));
        return data;
    }

    @Override
    public ReportWorkBookExcel reportReportKomisi(long idcompany, long idbranch, ParamReportKomisi param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Komisi");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

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

        String namaVendor = "";
        ParamVendor paramvendor =  new ParamVendor();
        paramvendor.setVendorTypes("'BROKER'");
        if(!param.getListIdvendorbroker().equals("ALL")){
            paramvendor.setListIdVendor(param.getListIdvendorbroker());
        }
        List<String> list = new ArrayList<>();
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(idcompany,idbranch,paramvendor);
        List<String> idvendors = new ArrayList<>();
        for(VendorDataForTemplate ven : getListVendor){
            list.add(ven.getAlias());
            idvendors.add(ven.getId().toString());
        }
        String listIdVendor = idvendors.toString().replaceAll("\\[","");
        listIdVendor = listIdVendor.replaceAll("\\]","");
        if(!param.getListIdvendorbroker().equals("ALL")){
            for(String nama :list){
                if(namaVendor == ""){
                    namaVendor = nama;
                } else{
                    namaVendor= namaVendor+","+nama;
                }
            }
        }else{
            namaVendor = "ALL";
        }

        int rowcount = 2;
        Row row = sheet.createRow(rowcount);
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Komisi", style, sheet,columns);

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
        createCell(row, 0, "Broker", style, sheet,columns);
        createCell(row, 1, namaVendor, style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "No", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Nama Broker", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Vendor", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Document", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Tanggal", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Koli", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Komisi Per Koli", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Subtotal Komisi", style, sheet,columns);

        ParamKomisiReportKomisi paramKomisi = new ParamKomisiReportKomisi();
        paramKomisi.setFrom(param.getFrom());
        paramKomisi.setTo(param.getTo());
        if(!param.getListIdvendorbroker().equals("ALL")){
            paramKomisi.setListIdvendor(listIdVendor);
        }
        List<KomisiDataReportKomisi> listkomisi = komisiService.getListReportKomisi(idcompany,idbranch,paramKomisi);
        HashMap<Long,List<KomisiDataReportKomisi>> grupByIdVendorBroker = new HashMap<>();
        if(listkomisi != null && listkomisi.size() > 0){
            for(KomisiDataReportKomisi kom : listkomisi){
                if(grupByIdVendorBroker.get(kom.getIdvendorbroker()) == null){
                    List<KomisiDataReportKomisi> temp = new ArrayList<>();
                    temp.add(kom);
                    grupByIdVendorBroker.put(kom.getIdvendorbroker(),temp);
                }else{
                    List<KomisiDataReportKomisi> temp = new ArrayList<>();
                    temp = grupByIdVendorBroker.get(kom.getIdvendorbroker());
                    temp.add(kom);
                    grupByIdVendorBroker.put(kom.getIdvendorbroker(),temp);
                }
            }

            for(VendorDataForTemplate ven : getListVendor){
                List<KomisiDataReportKomisi> listKomPerVendor = grupByIdVendorBroker.get(ven.getId());
                if(listKomPerVendor != null){
                    int no = 1;
                    double totalSubTotalKomisi = 0.0;
                    for(KomisiDataReportKomisi kom : listKomPerVendor){
                        colomcount = 0;
                        rowcount++;
                        row = sheet.createRow(rowcount);
                        createCell(row, colomcount, no, style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, ven.getAlias(), style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, kom.getVendoralias(), style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, kom.getNodocumentPR(), style, sheet,columns);

                        String transDate = "";
                        try {
                            transDate = GlobalFunc.getDateLongToString(kom.getDate().getTime(), "dd-MMMM-yyyy");
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        colomcount++;
                        createCell(row, colomcount, transDate, style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, kom.getKoli(), style, sheet,columns);

                        if(kom.getKomisiperkoli().doubleValue() > 1){
                            styleAmount = workbook.createCellStyle();
                            if(GlobalFunc.checkIsDecimal(kom.getKomisiperkoli())) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }
                            colomcount++;
                            createCell(row, colomcount, kom.getKomisiperkoli(), styleAmount, sheet,columns);
                        }else{
                            colomcount++;
                            createCell(row, colomcount, 0, style, sheet,columns);
                        }

                        if(kom.getSubtotalkomisi().doubleValue() > 1){
                            styleAmount = workbook.createCellStyle();
                            if(GlobalFunc.checkIsDecimal(kom.getSubtotalkomisi())) {
                                styleAmount.setDataFormat(format.getFormat("#,###"));
                            }else {
                                styleAmount.setDataFormat(format.getFormat("#,###.##"));
                            }
                            colomcount++;
                            createCell(row, colomcount, kom.getSubtotalkomisi(), styleAmount, sheet,columns);
                        }else{
                            colomcount++;
                            createCell(row, colomcount, 0, style, sheet,columns);
                        }
                        totalSubTotalKomisi += kom.getSubtotalkomisi().doubleValue();
                        no++;
                    }

                    colomcount = 0;
                    rowcount++;
                    row = sheet.createRow(rowcount);
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "Total", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    if(totalSubTotalKomisi > 1){
                        styleAmount = workbook.createCellStyle();
                        if(GlobalFunc.checkIsDecimal(totalSubTotalKomisi)) {
                            styleAmount.setDataFormat(format.getFormat("#,###"));
                        }else {
                            styleAmount.setDataFormat(format.getFormat("#,###.##"));
                        }
                        colomcount++;
                        createCell(row, colomcount, totalSubTotalKomisi, styleAmount, sheet,columns);
                    }else{
                        colomcount++;
                        createCell(row, colomcount, 0, style, sheet,columns);
                    }
                    rowcount++;

                }
            }
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportTemplate reportTemplateReportKomisi(long idcompany, long idbranch) {
        ParamVendor paramvendor =  new ParamVendor();
        paramvendor.setVendorTypes("'BROKER'");
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(idcompany,idbranch,paramvendor);
        ReportTemplate data = new ReportTemplate();
        data.setVendorOpt(getListVendor);
        return data;
    }

    private HashMap<String,Object> createCellReportHutang(ParamReportHutang param, Long idcompany, Long idbranch,VendorDataForTemplate ven,List<ReportPelunasanHutangDocumentHutang> listHutang, int rowcount,XSSFWorkbook workbook,XSSFDataFormat format ,Row row, XSSFSheet sheet, CellStyle style, CellStyle styleAmount,List<Integer> columns){
        HashMap<String,Object> mapp = new HashMap<>();
        if(listHutang != null && listHutang.size() > 0){
            for(ReportPelunasanHutangDocumentHutang hutang : listHutang){
                Double outstanding = hutang.getAmountInvoice();

                int colomcount = 0;
                rowcount++;
                row = sheet.createRow(rowcount);
                createCell(row, colomcount, ven.getAlias(), style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, ven.getType(), style, sheet,columns);

                String transDate = "";
                try {
                    transDate = GlobalFunc.getDateLongToString(hutang.getDate().getTime(), "dd-MMMM-yyyy");
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                colomcount++;
                createCell(row, colomcount, transDate, style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, hutang.getNodocument(), style, sheet,columns);

                styleAmount = workbook.createCellStyle();
                if(GlobalFunc.checkIsDecimal(hutang.getAmountInvoice())) {
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                }else {
                    styleAmount.setDataFormat(format.getFormat("#,###.##"));
                }
                colomcount++;
                createCell(row, colomcount, hutang.getAmountInvoice(), styleAmount, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, hutang.getAmountInvoice(), styleAmount, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                colomcount++;
                createCell(row, colomcount, "", style, sheet,columns);

                FilterParamPelunasanHutang paramPelunasanHutang = new FilterParamPelunasanHutang();
                paramPelunasanHutang.setFrom(param.getFrom());
                paramPelunasanHutang.setTo(param.getTo());
                if(hutang.getDocType().equals("CARGO")){
                    paramPelunasanHutang.setIdcargo(hutang.getIddoc());
                }else{
                    paramPelunasanHutang.setIdpurchasereceive(hutang.getIddoc());
                }
                List<PelunasanHutangReportHutang> listPembayaran = pelunasanHutangService.getListReportHutang(idcompany,idbranch,paramPelunasanHutang);
                if(listPembayaran != null && listPembayaran.size() > 0){
                    for(PelunasanHutangReportHutang ph : listPembayaran){
                        colomcount = 0;
                        rowcount++;
                        row = sheet.createRow(rowcount);
                        createCell(row, colomcount, "", style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", style, sheet,columns);

                        String transPHDate = "";
                        try {
                            transPHDate = GlobalFunc.getDateLongToString(ph.getDate().getTime(), "dd-MMMM-yyyy");
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        colomcount++;
                        createCell(row, colomcount, transPHDate, style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, ph.getNodocument(), style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, "", styleAmount, sheet,columns);

                        styleAmount = workbook.createCellStyle();
                        if(GlobalFunc.checkIsDecimal(ph.getAmount())) {
                            styleAmount.setDataFormat(format.getFormat("#,###"));
                        }else {
                            styleAmount.setDataFormat(format.getFormat("#,###.##"));
                        }

                        colomcount++;
                        createCell(row, colomcount, ph.getAmount(), style, sheet,columns);

                        outstanding = outstanding - ph.getAmount();
                        styleAmount = workbook.createCellStyle();
                        if(GlobalFunc.checkIsDecimal(outstanding)) {
                            styleAmount.setDataFormat(format.getFormat("#,###"));
                        }else {
                            styleAmount.setDataFormat(format.getFormat("#,###.##"));
                        }
                        colomcount++;
                        createCell(row, colomcount, outstanding, styleAmount, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, ph.getNodocument(), style, sheet,columns);

                        colomcount++;
                        createCell(row, colomcount, ph.getNotes(), style, sheet,columns);
                    }
                }
            }
        }
        mapp.put("rowcount",rowcount);
        return mapp;
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
