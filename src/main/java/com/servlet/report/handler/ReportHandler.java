package com.servlet.report.handler;

import com.servlet.admin.branch.entity.Branch;
import com.servlet.admin.branch.service.BranchService;
import com.servlet.cancelpackinglist.entity.ParamCalculateQtyCPL;
import com.servlet.cancelpackinglist.entity.ParamReportCancelPackingList;
import com.servlet.cancelpackinglist.entity.ParamSearchCancelPackingList;
import com.servlet.cancelpackinglist.entity.ReportCancelPackingList;
import com.servlet.cancelpackinglist.service.CancelPackingListService;
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
import com.servlet.draftpurchasereceive.entity.ParamCalculateQtyDPR;
import com.servlet.draftpurchasereceive.entity.ParamSearchDraftPurchaseReceive;
import com.servlet.draftpurchasereceive.service.DraftPurchaseReceiveService;
import com.servlet.historyapps.service.HistoryAppsService;
import com.servlet.invoice.entity.*;
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
import com.servlet.pinjaman.entity.ParamReportKartuPinjaman;
import com.servlet.pinjaman.entity.ParamReportKartuPinjamanList;
import com.servlet.pinjaman.entity.ParameterPinjaman;
import com.servlet.pinjaman.entity.ReportKartuPinjaman;
import com.servlet.pinjaman.service.PinjamanService;
import com.servlet.product.entity.ListProductData;
import com.servlet.product.entity.ParamProduct;
import com.servlet.product.service.ProductService;
import com.servlet.purchasereceive.entity.*;
import com.servlet.purchasereceive.service.PurchaseReceiveService;
import com.servlet.report.entity.*;
import com.servlet.report.service.ReportService;
import com.servlet.shared.GlobalFunc;
import com.servlet.stockadjusment.entity.ParamCalculateQtySA;
import com.servlet.stockadjusment.entity.PrintDataStockUdangMati;
import com.servlet.stockadjusment.entity.StockAdjsumentDataItem;
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
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.util.List;

@Service
public class ReportHandler implements ReportService {

    @Autowired
    PinjamanService pinjamanService;

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
    DraftPurchaseReceiveService draftPurchaseReceiveService;

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

    @Autowired
    CancelPackingListService cancelPackingListService;

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

            Double totalNettoHeader = 0.0;
            for(PackingListDataItemDetail item : print.getItems()){
//                totalNettoHeader += convertkg(item.getNettoweight());
                totalNettoHeader += item.getNettoweight();
            }

            createCell(row, 5, "Netto", style, sheet,columns);
            createCell(row, 6, totalNettoHeader , style, sheet,columns);

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
//                createCell(row, colomcount, convertkg(item.getNettoweight()), style, sheet,columns);
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
//            createCell(row, colomcount, totalweight, style, sheet,columns);
            createCell(row, colomcount, totalNettoHeader, style, sheet,columns);


            colomcount++;
            colomcount++;
            createCell(row, colomcount, totalprice, style, sheet,columns);

        }
        data.setWorkbook(workbook);
        return data;
    }

    private Double convertkg(Double value){
        Double totalnetto = value;
        totalnetto = GlobalFunc.convertGramToKG(totalnetto);
        totalnetto = GlobalFunc.pembulatanNilai(totalnetto,false,1);

        return totalnetto;
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

    private Integer decodeToImageExcel(String imageString,XSSFWorkbook workbook){
        if(imageString != null && !imageString.equals("")) {
            try {
                byte[] imagebyte = Base64.getDecoder().decode(imageString.getBytes(StandardCharsets.UTF_8));

                ByteArrayInputStream bis = new ByteArrayInputStream(imagebyte);
                BufferedImage image = ImageIO.read(bis);

                int scaledWidth = 1000;
                int scaledHeight = 1000;

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
                int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

                return pictureIdx;
            }catch (IOException e) {
                // TODO: handle exception
                return null;
            }
        }

        return null;
    }

    private static void drawImageOnExcelSheet(XSSFSheet sheet, int row, int col,
                                              int height, int width, int pictureIdx) throws Exception {

        CreationHelper helper = sheet.getWorkbook().getCreationHelper();

        Drawing drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

        anchor.setCol1(col); //first anchor determines upper left position
        anchor.setRow1(row);

        anchor.setRow2(row+2); //second anchor determines bottom right position
        anchor.setCol2(col+1);
        anchor.setDx2(Units.toEMU(width)); //dx = left + wanted width
        anchor.setDy2(Units.toEMU(height)); //dy= top + wanted height

        drawing.createPicture(anchor, pictureIdx);

    }

    private HashMap<String,String> getCodeAndCountryDest(String value){
        HashMap<String,String> maps = new HashMap<String,String>();
        String code = "";
        String destination = "";
        String customerAlias = value != null?value:"";
        String[] arrAlias = customerAlias.split("-");
        if(arrAlias.length > 1){
            code = arrAlias[0];
            destination = arrAlias[1];
        }else{
            code = customerAlias;
        }
        maps.put("code",code);
        maps.put("destination",destination);
        return maps;
    }

    @Override
    public ReportWorkBookExcel getExcelInvoiceByID2(long id, long idcompany, long idbranch, long iduser) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();


        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Invoice");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(15);

        ParamPrintInvoice paramPrint = new ParamPrintInvoice();
        paramPrint.setNamaMenu("PRINT");
        PrintInvoice invoice = invoiceService.getPrintDataByID(id,idcompany,idbranch,iduser,paramPrint);

        int fontHeight = 12;
        CellStyle styleTextPrinted = workbook.createCellStyle();
        XSSFFont fontTextPrinted = workbook.createFont();
        fontTextPrinted.setItalic(true);
        fontTextPrinted.setFontHeight(8);
        styleTextPrinted.setFont(fontTextPrinted);

        CellStyle styleTextDeclaration = workbook.createCellStyle();
        XSSFFont fontTextDeclaration = workbook.createFont();
        fontTextDeclaration.setFontHeight(9);
        styleTextDeclaration.setFont(fontTextDeclaration);

        CellStyle styleTextSalesInvoice = workbook.createCellStyle();
        XSSFFont fontTextSalesInvoice = workbook.createFont();
        fontTextSalesInvoice.setBold(true);
        fontTextSalesInvoice.setFontHeight(20);
        styleTextSalesInvoice.setFont(fontTextSalesInvoice);

        CellStyle style = workbook.createCellStyle();
        CellStyle styleNoBorder = workbook.createCellStyle();
        CellStyle styleBold = workbook.createCellStyle();
        CellStyle styleBoldItalic = workbook.createCellStyle();
        CellStyle styleBoldNoBorder = workbook.createCellStyle();
        CellStyle styleBoldItalicNoBorder = workbook.createCellStyle();
        CellStyle styleItalicNoBorder = workbook.createCellStyle();
        CellStyle styleAmount = workbook.createCellStyle();
        CellStyle styleAmountBorder = workbook.createCellStyle();
        CellStyle styleAmountColourBg = workbook.createCellStyle();
        CellStyle styleBoldItalicColourBg = workbook.createCellStyle();
        CellStyle styleBoldColourBg = workbook.createCellStyle();


        XSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontHeight(fontHeight);
        style.setFont(font);
        styleAmount.setFont(font);
        styleAmountBorder.setFont(font);
        styleAmountColourBg.setFont(font);


        XSSFFont fontBold = workbook.createFont();
        fontBold.setBold(true);
        fontBold.setFontHeight(fontHeight);
        styleBold.setFont(fontBold);
        styleBoldNoBorder.setFont(fontBold);
        styleBoldColourBg.setFont(fontBold);

        XSSFFont fontBoldItalic = workbook.createFont();
        fontBoldItalic.setBold(true);
        fontBoldItalic.setFontHeight(fontHeight);
        fontBoldItalic.setItalic(true);
        styleBoldItalicNoBorder.setFont(fontBoldItalic);
        styleBoldItalic.setFont(fontBoldItalic);
        styleBoldItalicColourBg.setFont(fontBoldItalic);

        XSSFFont fontItalic = workbook.createFont();
        fontItalic.setFontHeight(fontHeight);
        fontItalic.setItalic(true);
        styleItalicNoBorder.setFont(fontItalic);


        //nilai RGB adalah hexa dari #bcd6ed
        XSSFColor color = new XSSFColor(new java.awt.Color(188, 214, 237));

        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);

        styleAmountBorder.setBorderTop(BorderStyle.MEDIUM);
        styleAmountBorder.setBorderBottom(BorderStyle.MEDIUM);
        styleAmountBorder.setBorderLeft(BorderStyle.MEDIUM);
        styleAmountBorder.setBorderRight(BorderStyle.MEDIUM);

        styleBoldColourBg.setBorderTop(BorderStyle.MEDIUM);
        styleBoldColourBg.setBorderBottom(BorderStyle.MEDIUM);
        styleBoldColourBg.setBorderLeft(BorderStyle.MEDIUM);
        styleBoldColourBg.setBorderRight(BorderStyle.MEDIUM);

//        styleAmountColourBg.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
//        styleAmountColourBg.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ((XSSFCellStyle) styleBoldColourBg).setFillForegroundColor(color);
        styleBoldColourBg.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleBoldColourBg.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBoldColourBg.setAlignment(HorizontalAlignment.CENTER);


        styleBoldItalicColourBg.setBorderTop(BorderStyle.MEDIUM);
        styleBoldItalicColourBg.setBorderBottom(BorderStyle.MEDIUM);
        styleBoldItalicColourBg.setBorderLeft(BorderStyle.MEDIUM);
        styleBoldItalicColourBg.setBorderRight(BorderStyle.MEDIUM);

//        styleAmountColourBg.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
//        styleAmountColourBg.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ((XSSFCellStyle) styleBoldItalicColourBg).setFillForegroundColor(color);
        styleBoldItalicColourBg.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styleBoldItalicColourBg.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBoldItalicColourBg.setAlignment(HorizontalAlignment.CENTER);


        CreationHelper helper = workbook.getCreationHelper();
        //https://www.base64-image.de/
        String imgBase64 = "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";
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setAnchorType( ClientAnchor.AnchorType.MOVE_AND_RESIZE );
        Integer logoheader = decodeToImageExcel(imgBase64,workbook);
        int widhtPhoto = 4000;
        int heightPhoto = 4000;
        if(logoheader != null) {
            Row rowPhoto = sheet.createRow(2);
            Cell celPhoto = rowPhoto.createCell(0);
            celPhoto.setCellValue("");
            int columnIndex = celPhoto.getColumnIndex();
            sheet.autoSizeColumn(columnIndex);
            try {
                drawImageOnExcelSheet((XSSFSheet)sheet, 2, 0, widhtPhoto, heightPhoto, logoheader.intValue());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        int rowcount = 10;
        int columncount = 0;
        Row row = sheet.createRow(rowcount);

        CellRangeAddress salesinvoiceCellRangeAddress = new CellRangeAddress(2, 2, 5, 8);
        sheet.addMergedRegion(salesinvoiceCellRangeAddress);
        Row rowSalesInv = sheet.createRow(2);
        Cell salesinv = createCell(rowSalesInv, 5, "Sales Invoice", styleTextSalesInvoice, sheet,columns);
        CellUtil.setVerticalAlignment(salesinv, VerticalAlignment.CENTER);
        CellUtil.setAlignment(salesinv, HorizontalAlignment.CENTER);

        CellRangeAddress toCellRangeAddress = new CellRangeAddress(3, 7, 5, 8);
        Row row3 = sheet.createRow(3);
        createCell(row3, 5, "To", styleBoldItalicNoBorder, sheet,columns);
        createCell(row3, 6, invoice.getPackinglist().getVendorName(), styleBoldItalicNoBorder, sheet,columns);

        Row row4 = sheet.createRow(4);
        createCell(row4, 5, invoice.getPackinglist().getVendorAddress1(), styleNoBorder, sheet,columns);

        Row row5 = sheet.createRow(5);
        createCell(row5, 5, invoice.getPackinglist().getVendorAddress2(), styleNoBorder, sheet,columns);

        Row row6 = sheet.createRow(6);
        createCell(row6, 5, "NPWP  :", styleNoBorder, sheet,columns);
        createCell(row6, 6, invoice.getPackinglist().getVendorNpwp(), styleNoBorder, sheet,columns);

        Row row7 = sheet.createRow(7);
        createCell(row7, 5, "Phone  :", styleNoBorder, sheet,columns);
        createCell(row7, 6, invoice.getPackinglist().getVendorPhone(), styleNoBorder, sheet,columns);

        CellRangeAddress companyNameCellRangeAddress = new CellRangeAddress(2, 2, 2, 4);
        sheet.addMergedRegion(companyNameCellRangeAddress);
        createCell(rowSalesInv, 2, invoice.getCompanyName(), styleBoldNoBorder, sheet,columns);

        CellRangeAddress address1CellRangeAddress = new CellRangeAddress(3, 3, 2, 4);
        sheet.addMergedRegion(address1CellRangeAddress);
        createCell(row3, 2, invoice.getAddress1(), styleNoBorder, sheet,columns);

        CellRangeAddress address2CellRangeAddress = new CellRangeAddress(4, 4, 2, 4);
        sheet.addMergedRegion(address2CellRangeAddress);
        createCell(row4, 2, invoice.getAddress2(), styleNoBorder, sheet,columns);

        CellRangeAddress address3CellRangeAddress = new CellRangeAddress(5, 5, 2, 4);
        sheet.addMergedRegion(address3CellRangeAddress);
        createCell(row5, 2, invoice.getAddress3(), styleNoBorder, sheet,columns);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, toCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, toCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, toCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, toCellRangeAddress, sheet);

        //Header dan value attn,code,Flight No.,abw, Packing List No.
//        CellRangeAddress attnCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 1);
//        sheet.addMergedRegion(attnCellRangeAddress);
//        Cell attn = createCell(row, 0, "Attn.", style, sheet,columns);
//        CellUtil.setVerticalAlignment(attn, VerticalAlignment.CENTER);
//        CellUtil.setAlignment(attn, HorizontalAlignment.CENTER);
//        attn.setCellStyle(styleBoldItalicColourBg);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, attnCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, attnCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, attnCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, attnCellRangeAddress, sheet);


//        CellRangeAddress codeCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 2, 3);
//        sheet.addMergedRegion(codeCellRangeAddress);
//        Cell code = createCell(row, 2, "Code", style, sheet,columns);
//        code.setCellStyle(styleBoldItalicColourBg);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, codeCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, codeCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, codeCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, codeCellRangeAddress, sheet);

//        columncount = 4;
//        createCell(row, columncount, "Flight No.", styleBoldItalicColourBg, sheet,columns);

//        CellRangeAddress abwCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 5, 6);
//        sheet.addMergedRegion(abwCellRangeAddress);
//        Cell abw = createCell(row, 5, "AWB", style, sheet,columns);
//        abw.setCellStyle(styleBoldItalicColourBg);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, abwCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, abwCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, abwCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, abwCellRangeAddress, sheet);

//        CellRangeAddress packinglistnoCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 7, 8);
//        sheet.addMergedRegion(packinglistnoCellRangeAddress);
//        Cell plno = createCell(row, 7, "Packing List No.", style, sheet,columns);
//        plno.setCellStyle(styleBoldItalicColourBg);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, packinglistnoCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, packinglistnoCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, packinglistnoCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, packinglistnoCellRangeAddress, sheet);

//        rowcount++;
//        row = sheet.createRow(rowcount);
//        columncount = 0;
//        CellRangeAddress valueattnCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 1);
//        sheet.addMergedRegion(valueattnCellRangeAddress);
//        Cell valueattn = createCell(row, 0, invoice.getPackinglist().getAttention(), style, sheet,columns);
//        CellUtil.setVerticalAlignment(valueattn, VerticalAlignment.CENTER);
//        CellUtil.setAlignment(valueattn, HorizontalAlignment.CENTER);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valueattnCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valueattnCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valueattnCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valueattnCellRangeAddress, sheet);


//        CellRangeAddress valuecodeCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 2, 3);
//        sheet.addMergedRegion(valuecodeCellRangeAddress);
//        Cell valuecode = createCell(row, 2, getCodeAndCountryDest(invoice.getPackinglist().getCustomerAlias()).get("code"), style, sheet,columns);
//        CellUtil.setVerticalAlignment(valuecode, VerticalAlignment.CENTER);
//        CellUtil.setAlignment(valuecode, HorizontalAlignment.CENTER);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valuecodeCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valuecodeCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valuecodeCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valuecodeCellRangeAddress, sheet);

//        columncount = 4;
//        Cell valueflight = createCell(row, columncount, invoice.getPackinglist().getFlightnumber(), style, sheet,columns);
//        CellUtil.setVerticalAlignment(valueflight, VerticalAlignment.CENTER);
//        CellUtil.setAlignment(valueflight, HorizontalAlignment.CENTER);

//        CellRangeAddress valueabwCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 5, 6);
//        sheet.addMergedRegion(valueabwCellRangeAddress);
//        Cell valueabw = createCell(row, 5, invoice.getPackinglist().getAwbnumber()+" "+invoice.getPackinglist().getCodeGrupcustomer(), style, sheet,columns);
//        CellUtil.setVerticalAlignment(valueabw, VerticalAlignment.CENTER);
//        CellUtil.setAlignment(valueabw, HorizontalAlignment.CENTER);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valueabwCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valueabwCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valueabwCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valueabwCellRangeAddress, sheet);

//        CellRangeAddress valuepackinglistnoCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 7, 8);
//        sheet.addMergedRegion(valuepackinglistnoCellRangeAddress);
//        Cell valueplno = createCell(row, 7, invoice.getPackinglist().getNodocument(), style, sheet,columns);
//        CellUtil.setVerticalAlignment(valueplno, VerticalAlignment.CENTER);
//        CellUtil.setAlignment(valueplno, HorizontalAlignment.CENTER);
//        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valuepackinglistnoCellRangeAddress, sheet);
//        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valuepackinglistnoCellRangeAddress, sheet);
//        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valuepackinglistnoCellRangeAddress, sheet);
//        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valuepackinglistnoCellRangeAddress, sheet);
        //End


        //Header dan value CountryOfOrigin,CountryOfFinalDest,Collie,netto, Packing List Date.
        rowcount++;
        row = sheet.createRow(rowcount);
        columncount = 0;

        CellRangeAddress countryOfOriginCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 1);
        sheet.addMergedRegion(countryOfOriginCellRangeAddress);
        Cell countryOfOrigin = createCell(row, 0, "Invoice Number", style, sheet,columns);
        countryOfOrigin.setCellStyle(styleBoldItalicColourBg);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, countryOfOriginCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, countryOfOriginCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, countryOfOriginCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, countryOfOriginCellRangeAddress, sheet);


        CellRangeAddress countryOfFinalDestCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 2, 3);
        sheet.addMergedRegion(countryOfFinalDestCellRangeAddress);
        Cell countryOfFinalDest = createCell(row, 2, "Invoice Date", style, sheet,columns);
        countryOfFinalDest.setCellStyle(styleBoldItalicColourBg);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, countryOfFinalDestCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, countryOfFinalDestCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, countryOfFinalDestCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, countryOfFinalDestCellRangeAddress, sheet);

        columncount = 4;
        createCell(row, columncount, "Boxes", styleBoldItalicColourBg, sheet,columns);

        CellRangeAddress nettoCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 5, 6);
        sheet.addMergedRegion(nettoCellRangeAddress);
        Cell netto = createCell(row, 5, "Netto Kg", style, sheet,columns);
        netto.setCellStyle(styleBoldItalicColourBg);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, nettoCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, nettoCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, nettoCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, nettoCellRangeAddress, sheet);

        CellRangeAddress packinglistdateCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 7, 8);
        sheet.addMergedRegion(packinglistdateCellRangeAddress);
        Cell pldate = createCell(row, 7, "Packing List Date", style, sheet,columns);
        pldate.setCellStyle(styleBoldItalicColourBg);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, packinglistdateCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, packinglistdateCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, packinglistdateCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, packinglistdateCellRangeAddress, sheet);

        rowcount++;
        row = sheet.createRow(rowcount);
        columncount = 0;
        CellRangeAddress valuecountryOfOriginCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 1);
        sheet.addMergedRegion(valuecountryOfOriginCellRangeAddress);
        Cell valuecountryOfOrigin = createCell(row, 0, invoice.getNodocument(), style, sheet,columns);
        CellUtil.setVerticalAlignment(valuecountryOfOrigin, VerticalAlignment.CENTER);
        CellUtil.setAlignment(valuecountryOfOrigin, HorizontalAlignment.CENTER);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valuecountryOfOriginCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valuecountryOfOriginCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valuecountryOfOriginCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valuecountryOfOriginCellRangeAddress, sheet);

        String invDate = "";
        try {
            invDate = GlobalFunc.getDateLongToString(invoice.getPackinglist().getDate().getTime(), "dd MMMM yyyy");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CellRangeAddress valuecountryOfFinalDestCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 2, 3);
        sheet.addMergedRegion(valuecountryOfFinalDestCellRangeAddress);
        Cell valuecountryOfFinalDest = createCell(row, 2, invDate, style, sheet,columns);
        CellUtil.setVerticalAlignment(valuecountryOfFinalDest, VerticalAlignment.CENTER);
        CellUtil.setAlignment(valuecountryOfFinalDest, HorizontalAlignment.CENTER);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valuecountryOfFinalDestCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valuecountryOfFinalDestCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valuecountryOfFinalDestCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valuecountryOfFinalDestCellRangeAddress, sheet);

        columncount = 4;
        Cell valuekoli = createCell(row, columncount, invoice.getPackinglist().getKoli(), style, sheet,columns);
        CellUtil.setVerticalAlignment(valuekoli, VerticalAlignment.CENTER);
        CellUtil.setAlignment(valuekoli, HorizontalAlignment.CENTER);

        CellRangeAddress valuenettoCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 5, 6);
        sheet.addMergedRegion(valuenettoCellRangeAddress);

        styleAmount = workbook.createCellStyle();
        styleAmount.setBorderTop(BorderStyle.MEDIUM);
        styleAmount.setBorderBottom(BorderStyle.MEDIUM);
        styleAmount.setBorderLeft(BorderStyle.MEDIUM);
        styleAmount.setBorderRight(BorderStyle.MEDIUM);
        Double valnetto = 0.0;//convertkg(invoice.getPackinglist().getNetto());
        for(PackingListDataItemDetail item : invoice.getPackinglist().getItems()){
            valnetto += convertkg(item.getNettoweight());
        }
        if(GlobalFunc.checkIsDecimal(valnetto)) {
            styleAmount.setDataFormat(format.getFormat("#,###"));
        }
//        else {
//            styleAmount.setDataFormat(format.getFormat("#,###.000"));
//        }
        Cell valuenetto = createCell(row, 5, valnetto, styleAmount, sheet,columns);
        CellUtil.setVerticalAlignment(valuenetto, VerticalAlignment.CENTER);
        CellUtil.setAlignment(valuenetto, HorizontalAlignment.CENTER);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valuenettoCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valuenettoCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valuenettoCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valuenettoCellRangeAddress, sheet);

        CellRangeAddress valuepackinglistdateCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 7, 8);
        sheet.addMergedRegion(valuepackinglistdateCellRangeAddress);

        String transDate = "";
        try {
            transDate = GlobalFunc.getDateLongToString(invoice.getPackinglist().getDate().getTime(), "dd MMMM yyyy");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Cell valuepldate = createCell(row, 7, transDate, style, sheet,columns);
        CellUtil.setVerticalAlignment(valuepldate, VerticalAlignment.CENTER);
        CellUtil.setAlignment(valuepldate, HorizontalAlignment.CENTER);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, valuepackinglistdateCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, valuepackinglistdateCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, valuepackinglistdateCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, valuepackinglistdateCellRangeAddress, sheet);
        //End



        rowcount++;
        row = sheet.createRow(rowcount);
        columncount = 0;
        createCell(row, columncount, "No", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "Description Of Goods", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "Size", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "Gram", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "Qty", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "Weight Kg", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "No. Of Boxes", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "Price", styleBoldColourBg, sheet,columns);

        columncount++;
        createCell(row, columncount, "Total", styleBoldColourBg, sheet,columns);

        int no = 1;
        long totalQty = 0;
        double totalWeightKg = 0.0;
        double totalPrice = 0.0;
        for(PackingListDataItemDetail item : invoice.getPackinglist().getItems()){
            totalQty += item.getQty();
            totalWeightKg += item.getNettoweight();
            totalPrice += item.getTotalprice();

            rowcount++;
            row = sheet.createRow(rowcount);
            columncount = 0;
            Cell cellno = createCell(row, columncount, no, style, sheet,columns);
            CellUtil.setVerticalAlignment(cellno, VerticalAlignment.CENTER);
            CellUtil.setAlignment(cellno, HorizontalAlignment.CENTER);

            columncount++;
            Cell cellprodname = createCell(row, columncount, item.getProductName(), style, sheet,columns);
            CellUtil.setVerticalAlignment(cellprodname, VerticalAlignment.CENTER);
            CellUtil.setAlignment(cellprodname, HorizontalAlignment.CENTER);

            columncount++;
            Cell cellprodsize = createCell(row, columncount, item.getCategoryProductSize(), style, sheet,columns);
            CellUtil.setVerticalAlignment(cellprodsize, VerticalAlignment.CENTER);
            CellUtil.setAlignment(cellprodsize, HorizontalAlignment.CENTER);

            columncount++;
            Cell cellgr = createCell(row, columncount, item.getCategoryProductFromGr()+" - "+item.getCategoryProductThruGr(), style, sheet,columns);
            CellUtil.setVerticalAlignment(cellgr, VerticalAlignment.CENTER);
            CellUtil.setAlignment(cellgr, HorizontalAlignment.CENTER);

            columncount++;
            Cell cellqty = createCell(row, columncount, item.getQty(), style, sheet,columns);
            CellUtil.setVerticalAlignment(cellqty, VerticalAlignment.CENTER);
            CellUtil.setAlignment(cellqty, HorizontalAlignment.CENTER);

            styleAmount = workbook.createCellStyle();
            styleAmount.setBorderTop(BorderStyle.MEDIUM);
            styleAmount.setBorderBottom(BorderStyle.MEDIUM);
            styleAmount.setBorderLeft(BorderStyle.MEDIUM);
            styleAmount.setBorderRight(BorderStyle.MEDIUM);
            Double valnettowight = convertkg(item.getNettoweight());
            if(GlobalFunc.checkIsDecimal(valnettowight)) {
                styleAmount.setDataFormat(format.getFormat("#,###"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.0"));
            }
            columncount++;
            Cell cellnettowieght  = createCell(row, columncount, valnettowight, styleAmount, sheet,columns);
            CellUtil.setVerticalAlignment(cellnettowieght, VerticalAlignment.CENTER);
            CellUtil.setAlignment(cellnettowieght, HorizontalAlignment.CENTER);

            columncount++;
            Cell cellbox = createCell(row, columncount, item.getBox(), style, sheet,columns);
            CellUtil.setVerticalAlignment(cellbox, VerticalAlignment.CENTER);
            CellUtil.setAlignment(cellbox, HorizontalAlignment.CENTER);

            styleAmount = workbook.createCellStyle();
            styleAmount.setBorderTop(BorderStyle.MEDIUM);
            styleAmount.setBorderBottom(BorderStyle.MEDIUM);
            styleAmount.setBorderLeft(BorderStyle.MEDIUM);
            styleAmount.setBorderRight(BorderStyle.MEDIUM);
            if(GlobalFunc.checkIsDecimal(item.getPrice())) {
                styleAmount.setDataFormat(format.getFormat("#,###.00"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.00"));
            }
            columncount++;
            Cell cellprice = createCell(row, columncount, item.getPrice(), styleAmount, sheet,columns);


            styleAmount = workbook.createCellStyle();
            styleAmount.setBorderTop(BorderStyle.MEDIUM);
            styleAmount.setBorderBottom(BorderStyle.MEDIUM);
            styleAmount.setBorderLeft(BorderStyle.MEDIUM);
            styleAmount.setBorderRight(BorderStyle.MEDIUM);
            if(GlobalFunc.checkIsDecimal(item.getTotalprice())) {
                styleAmount.setDataFormat(format.getFormat("#,###.0"));
            }else {
                styleAmount.setDataFormat(format.getFormat("#,###.0"));
            }
            columncount++;
            createCell(row, columncount, item.getTotalprice(), styleAmount, sheet,columns);

            no++;
        }

        rowcount++;
        row = sheet.createRow(rowcount);
        CellRangeAddress grandTotalCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 3);
        sheet.addMergedRegion(grandTotalCellRangeAddress);
        Cell grandTotal = createCell(row, 0, "Total", styleBoldItalic, sheet,columns);
        CellUtil.setVerticalAlignment(grandTotal, VerticalAlignment.CENTER);
        CellUtil.setAlignment(grandTotal, HorizontalAlignment.CENTER);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, grandTotalCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, grandTotalCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, grandTotalCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, grandTotalCellRangeAddress, sheet);

        Cell celltotalQty = createCell(row, 4, totalQty, style, sheet,columns);
        CellUtil.setVerticalAlignment(celltotalQty, VerticalAlignment.CENTER);
        CellUtil.setAlignment(celltotalQty, HorizontalAlignment.CENTER);

        styleAmount = workbook.createCellStyle();
        styleAmount.setBorderTop(BorderStyle.MEDIUM);
        styleAmount.setBorderBottom(BorderStyle.MEDIUM);
        styleAmount.setBorderLeft(BorderStyle.MEDIUM);
        styleAmount.setBorderRight(BorderStyle.MEDIUM);

//        Double valtotalWeightKg = convertGramToKG(invoice.getPackinglist().getNetto());
        if(GlobalFunc.checkIsDecimal(valnetto)) {
            styleAmount.setDataFormat(format.getFormat("#,###"));
        }else {
            styleAmount.setDataFormat(format.getFormat("#,###.0"));
        }
        Cell totalWeight = createCell(row, 5, valnetto, styleAmount, sheet,columns);
        CellUtil.setVerticalAlignment(totalWeight, VerticalAlignment.CENTER);
        CellUtil.setAlignment(totalWeight, HorizontalAlignment.CENTER);

        Cell totalNoOfBox = createCell(row, 6, invoice.getPackinglist().getItems().size(), style, sheet,columns);
        CellUtil.setVerticalAlignment(totalNoOfBox, VerticalAlignment.CENTER);
        CellUtil.setAlignment(totalNoOfBox, HorizontalAlignment.CENTER);

        CellStyle customStyle = style;
        customStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
        createCell(row, 7, "USD", customStyle, sheet,columns);

        styleAmount = workbook.createCellStyle();
        styleAmount.setBorderTop(BorderStyle.MEDIUM);
        styleAmount.setBorderBottom(BorderStyle.MEDIUM);
        styleAmount.setBorderLeft(BorderStyle.MEDIUM);
        styleAmount.setBorderRight(BorderStyle.MEDIUM);
        styleAmount.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        if(GlobalFunc.checkIsDecimal(totalPrice)) {
            styleAmount.setDataFormat(format.getFormat("#,###.0"));
        }else {
            styleAmount.setDataFormat(format.getFormat("#,###.0"));
        }
        createCell(row, 8, totalPrice, styleAmount, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        CellRangeAddress kursCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 3);
        sheet.addMergedRegion(kursCellRangeAddress);
        Cell kurs = createCell(row, 0, "Kurs", styleBoldItalic, sheet,columns);
        CellUtil.setVerticalAlignment(kurs, VerticalAlignment.CENTER);
        CellUtil.setAlignment(kurs, HorizontalAlignment.CENTER);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, kursCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, kursCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, kursCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, kursCellRangeAddress, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), kursCellRangeAddress, sheet);

        CellRangeAddress kurs2CellRangeAddress = new CellRangeAddress(rowcount, rowcount, 4, 6);
        sheet.addMergedRegion(kurs2CellRangeAddress);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, kurs2CellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, kurs2CellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, kurs2CellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, kurs2CellRangeAddress, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.WHITE.getIndex(), kurs2CellRangeAddress, sheet);

        createCell(row, 7, "IDR", customStyle, sheet,columns);
        styleAmount = workbook.createCellStyle();
        styleAmount.setBorderTop(BorderStyle.MEDIUM);
        styleAmount.setBorderBottom(BorderStyle.MEDIUM);
        styleAmount.setBorderLeft(BorderStyle.MEDIUM);
        styleAmount.setBorderRight(BorderStyle.MEDIUM);
        styleAmount.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        if(GlobalFunc.checkIsDecimal(invoice.getKurs())) {
            styleAmount.setDataFormat(format.getFormat("#,###.0"));
        }else {
            styleAmount.setDataFormat(format.getFormat("#,###.0"));
        }
        createCell(row, 8, invoice.getKurs(), styleAmount, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        CellRangeAddress grandTotalIDRRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 3);
        sheet.addMergedRegion(grandTotalIDRRangeAddress);
        Cell grandtotalinidr = createCell(row, 0, "Total In IDR", styleBoldItalic, sheet,columns);
        CellUtil.setVerticalAlignment(grandtotalinidr, VerticalAlignment.CENTER);
        CellUtil.setAlignment(grandtotalinidr, HorizontalAlignment.CENTER);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, grandTotalIDRRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, grandTotalIDRRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, grandTotalIDRRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, grandTotalIDRRangeAddress, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.WHITE.getIndex(), grandTotalIDRRangeAddress, sheet);

        CellRangeAddress grandTotal2IDRRangeAddress = new CellRangeAddress(rowcount, rowcount, 4, 6);
        sheet.addMergedRegion(grandTotal2IDRRangeAddress);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, grandTotal2IDRRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, grandTotal2IDRRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, grandTotal2IDRRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, grandTotal2IDRRangeAddress, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.WHITE.getIndex(), grandTotal2IDRRangeAddress, sheet);

        createCell(row, 7, "IDR", customStyle, sheet,columns);
        styleAmount = workbook.createCellStyle();
        styleAmount.setBorderTop(BorderStyle.MEDIUM);
        styleAmount.setBorderBottom(BorderStyle.MEDIUM);
        styleAmount.setBorderLeft(BorderStyle.MEDIUM);
        styleAmount.setBorderRight(BorderStyle.MEDIUM);
        styleAmount.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        Double totalPriceInIDR = totalPrice * invoice.getKurs();
        totalPriceInIDR = GlobalFunc.jumlahDesimal(totalPriceInIDR.doubleValue(),2);

        if(GlobalFunc.checkIsDecimal(totalPriceInIDR)) {
            styleAmount.setDataFormat(format.getFormat("#,###.0"));
        }else {
            styleAmount.setDataFormat(format.getFormat("#,###.0"));
        }
        createCell(row, 8, totalPriceInIDR, styleAmount, sheet,columns);

        rowcount++;
        int lastrow = rowcount + 4;
        CellRangeAddress remarksCellRangeAddress = new CellRangeAddress(rowcount, lastrow, 0, 5);
        CellRangeAddress printedCellRangeAddress = new CellRangeAddress(rowcount, lastrow, 6, 8);

//        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Remarks", styleNoBorder, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Bank Account:", styleItalicNoBorder, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, invoice.getBankCompany(), styleItalicNoBorder, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "a/c "+invoice.getBankAccNoCompany(), styleItalicNoBorder, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, invoice.getBankAccNameCompany(), styleItalicNoBorder, sheet,columns);


        CellRangeAddress printedTextCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 6, 8);
        sheet.addMergedRegion(printedTextCellRangeAddress);
        String currdate = "";
        try {
            currdate = GlobalFunc.getDateLongToString(new Date().getTime(), "dd MMMM yyyy HH:mm:ss");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Cell printed = createCell(row, 6, "Printed By : "+invoice.getNamaUser()+", "+currdate, styleTextPrinted, sheet,columns);
        CellUtil.setVerticalAlignment(printed, VerticalAlignment.CENTER);
        CellUtil.setAlignment(printed, HorizontalAlignment.RIGHT);

        rowcount++;
        row = sheet.createRow(rowcount);
        CellRangeAddress declarationTextCellRangeAddress = new CellRangeAddress(rowcount, rowcount, 0, 8);
        sheet.addMergedRegion(declarationTextCellRangeAddress);
        Cell declaration = createCell(row, 0, "Declaration : We declare that this invoice shows actual price of goods described and that all particulars are true and correct", styleTextDeclaration, sheet,columns);
        CellUtil.setVerticalAlignment(declaration, VerticalAlignment.CENTER);
        CellUtil.setAlignment(declaration, HorizontalAlignment.LEFT);

        rowcount++;
        row = sheet.createRow(rowcount);
        Cell editprinted = createCell(row, 8, "Edit:"+invoice.getCountEdit()+" Print:"+(invoice.getCountPrint()+1), styleTextPrinted, sheet,columns);
        CellUtil.setVerticalAlignment(editprinted, VerticalAlignment.TOP);
        CellUtil.setAlignment(editprinted, HorizontalAlignment.RIGHT);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, declarationTextCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, declarationTextCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, declarationTextCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, declarationTextCellRangeAddress, sheet);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, remarksCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, remarksCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, remarksCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, remarksCellRangeAddress, sheet);

        RegionUtil.setBorderTop(BorderStyle.MEDIUM, printedCellRangeAddress, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, printedCellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, printedCellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, printedCellRangeAddress, sheet);

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
                createCell(row, colomcount, "QtyNota"+cp.getSize(), style, sheet,columns);

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
                            createCell(row, colomcount, det.getQtynota(), style, sheet,columns);

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

        //kalau ada perubahan perhitungan atau tambah kolom, sesuaikan pdf nya di service printStockUdangHidupMati di StockAdjusmentHandler

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
            ParamCalculateQtyDPR paramPR = new ParamCalculateQtyDPR();
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

            ParamCalculateQtyCPL paramCPL = new ParamCalculateQtyCPL();
            paramCPL.setDateFrom(satuJan70);
            paramCPL.setDateThru(dateMinus1);
            paramCPL.setIdcategoryproduct(cp.getId());

            ParamCalculateQty paramQty = new ParamCalculateQty();
            paramQty.setParamCalculateQtyDPR(paramPR);
            paramQty.setParamCalculateQtySA(paramSA);
            paramQty.setParamCalculateQtyPL(paramPL);
            paramQty.setParamCalculateQtyCPL(paramCPL);
            Long stockKolamTerakhir = stockItemService.calculateQty(idcompany,idbranch,paramQty);
            stockKolamTerakhirByIDcategory.put(cp.getId(),stockKolamTerakhir);

            ParamCalculateQtySA paramSAUdangMati = new ParamCalculateQtySA();
            paramSAUdangMati.setDateFrom(param.getDate());
            paramSAUdangMati.setDateThru(param.getDate());
            paramSAUdangMati.setIdcategoryproduct(cp.getId());
            Long stockUdangMati = stockAdjusmentService.calculateQtySA(idcompany,idbranch,"M",paramSAUdangMati);

            ParamCalculateQtyCPL paramCalculateQtyCPL = new ParamCalculateQtyCPL();
            paramCalculateQtyCPL.setDateFrom(param.getDate());
            paramCalculateQtyCPL.setDateThru(param.getDate());
            paramCalculateQtyCPL.setIdcategoryproduct(cp.getId());
            paramCalculateQtyCPL.setType("M");
            Long stockUdangMatiCPL = cancelPackingListService.calculateQtyCPL(idcompany,idbranch,paramCalculateQtyCPL);

            stockUdangMati = stockUdangMati.longValue() + stockUdangMatiCPL.longValue();
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
//                if(mapp.getCategoryproductidmapping() == 24){
//                    System.out.println("mapp.getCategoryproductid() "+mapp.getCategoryproductid());
//                    System.out.println("mapp.getCategoryproductidmapping() "+mapp.getCategoryproductidmapping());
//                    System.out.println("stockKolamTerakhir1 "+stockKolamTerakhir1);
//                    System.out.println("stockKolamTerakhir2 "+stockKolamTerakhir2);
//                }
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
//                if(mapp.getCategoryproductidmapping() == 24){
//                    System.out.println("stockKolamTerakhir "+stockKolamTerakhir);
//                    System.out.println("calculateStockByIdCPMappingStockKolamTerakhir "+calculateStockByIdCPMappingStockKolamTerakhir.get(mapp.getCategoryproductidmapping()));
//                }
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

        HashMap<String, Long> stockPerVendorPerCategory = new HashMap<>();
        HashMap<Long, Boolean> vendorIsShow = new HashMap<>();
        if(param.getShowNol().equals("N")){
            for(VendorDataForTemplate vendor : listvendor){
                Long idvendor = vendor.getId();
                long totalStockPerVendorAllCategory = 0L;
                for(CategoryProductList cp : listCP) {
                    ParamCalculateQtyDPR paramPR = new ParamCalculateQtyDPR();
                    paramPR.setDateFrom(param.getDate());
                    paramPR.setDateThru(param.getDate());
                    paramPR.setIdcategoryproduct(cp.getId());
                    paramPR.setIdvendor(idvendor);
                    Long stock = draftPurchaseReceiveService.calculateQtyDpr(idcompany, idbranch, paramPR);
                    totalStockPerVendorAllCategory = totalStockPerVendorAllCategory + stock.longValue();
                    stockPerVendorPerCategory.put(cp.getId()+"-"+idvendor, stock);
                }
                if(totalStockPerVendorAllCategory > 0){
                    vendorIsShow.put(idvendor,true);
                }else{
                    vendorIsShow.put(idvendor,false);
                }
            }
        }

        HashMap<Long,Integer> mapsVendorIdxColumn = new HashMap<>();
        for(VendorDataForTemplate vendor : listvendor){
            if(param.getShowNol().equals("N")){
                if(vendorIsShow.get(vendor.getId()).booleanValue()){
                    colomcount++;
                    mapsVendorIdxColumn.put(vendor.getId(), colomcount);
                    createCell(row, colomcount, vendor.getNama(), style, sheet,columns);
                }
            }else{
                colomcount++;
                mapsVendorIdxColumn.put(vendor.getId(), colomcount);
                createCell(row, colomcount, vendor.getNama(), style, sheet,columns);
            }

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
                Long stock = 0L;
                if(param.getShowNol().equals("N")){
                    stock = stockPerVendorPerCategory.get(cp.getId()+"-"+idvendor);
                }else{
                    ParamCalculateQtyDPR paramPR = new ParamCalculateQtyDPR();
                    paramPR.setDateFrom(param.getDate());
                    paramPR.setDateThru(param.getDate());
                    paramPR.setIdcategoryproduct(cp.getId());
                    paramPR.setIdvendor(idvendor);
                    stock = draftPurchaseReceiveService.calculateQtyDpr(idcompany,idbranch,paramPR);
                }

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
        //ini list vendorparent
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(idcompany,idbranch,paramvendor);
        List<String> idvendors = new ArrayList<>();
        for(VendorDataForTemplate ven : getListVendor){
            list.add(ven.getNama()+" ("+ven.getAlias()+")");
            idvendors.add(ven.getId().toString());
        }
        String listIdVendor = idvendors.toString().replaceAll("\\[","");
        listIdVendor = listIdVendor.replaceAll("\\]","");

        List<Long> listIdParentAndSubIdParent = vendorService.getListSubIdParentByListIdParent(idcompany,idbranch,listIdVendor);
        for(VendorDataForTemplate ven : getListVendor){
            listIdParentAndSubIdParent.add(ven.getId());
        }
        String listIdVendorSubParent = listIdParentAndSubIdParent.toString().replaceAll("\\[","");
        listIdVendorSubParent = listIdVendorSubParent.replaceAll("\\]","");

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
        createCell(row, colomcount, "Deposit Masuk", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Deposit Keluar", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Saldo Akhir", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Document Number", style, sheet,columns);

        if(getListVendor != null && getListVendor.size() > 0){
            List<ReportKartuDeposit> listKartuDeposit = new ArrayList<>();
            ParamList paramdp = new ParamList();
            paramdp.setFrom(param.getFrom());
            paramdp.setTo(param.getTo());
            paramdp.setListIdVendor(listIdVendorSubParent);
            List<ReportKartuDeposit> listdp = depositService.getListReportKartuDeposit(idcompany,idbranch,paramdp);

            FilterParamPurchaseReceive paramPR = new FilterParamPurchaseReceive();
            paramPR.setFrom(param.getFrom());
            paramPR.setTo(param.getTo());
            paramPR.setListIdVendor(listIdVendorSubParent);
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

            HashMap<Long, List<ReportKartuDeposit>> grupByVendorParent = new HashMap<>();
            HashMap<Long, Double> grupByVendorMasuk = new HashMap<>();
            HashMap<Long, Double> grupByVendorKeluar = new HashMap<>();
            if(listKartuDeposit != null && listKartuDeposit.size() > 0) {
                for (VendorDataForTemplate ven : getListVendor) {
                    List<ReportKartuDeposit> listKDTemp = new ArrayList<>();
                    for (ReportKartuDeposit kd : listKartuDeposit) {
                        if (ven.getId().longValue() == kd.getIdvendor().longValue() || ven.getId().longValue() == kd.getIdvendorParent().longValue()) {
                            listKDTemp.add(kd);
                        }
                        if(param.getShowNol().equals("NO")){
                            if(kd.getType().equals("DEPOSIT")){
                                if(grupByVendorMasuk.get(kd.getIdvendor()) == null){
                                    grupByVendorMasuk.put(kd.getIdvendor(),kd.getAmount());
                                }else {
                                    Double amt = (kd.getAmount() != null?kd.getAmount().doubleValue():0.0) + grupByVendorMasuk.get(kd.getIdvendor()).doubleValue();
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
                    grupByVendorParent.put(ven.getId(), listKDTemp);
                }
            }


//            HashMap<Long, List<ReportKartuDeposit>> grupByVendor = new HashMap<>();
//            HashMap<Long, Double> grupByVendorMasuk = new HashMap<>();
//            HashMap<Long, Double> grupByVendorKeluar = new HashMap<>();
//            List<ReportKartuDeposit> listKDTemp = new ArrayList<>();
//            if(listKartuDeposit != null && listKartuDeposit.size() > 0){
//                for(ReportKartuDeposit kd : listKartuDeposit){
//                    if(grupByVendor.get(kd.getIdvendor()) == null){
//                        listKDTemp = new ArrayList<>();
//                        listKDTemp.add(kd);
//                        grupByVendor.put(kd.getIdvendor(),listKDTemp);
//                    }else{
//                        listKDTemp = new ArrayList<>();
//                        listKDTemp = grupByVendor.get(kd.getIdvendor());
//                        listKDTemp.add(kd);
//                        grupByVendor.put(kd.getIdvendor(),listKDTemp);
//                    }
//
//                    if(param.getShowNol().equals("NO")){
//                        if(kd.getType().equals("DEPOSIT")){
//                            if(grupByVendorMasuk.get(kd.getIdvendor()) == null){
//                                grupByVendorMasuk.put(kd.getIdvendor(),kd.getAmount());
//                            }else {
//                                Double amt = kd.getAmount().doubleValue() + grupByVendorMasuk.get(kd.getIdvendor()).doubleValue();
//                                grupByVendorMasuk.put(kd.getIdvendor(),amt);
//                            }
//                        }else{
//                            if(grupByVendorKeluar.get(kd.getIdvendor()) == null){
//                                grupByVendorKeluar.put(kd.getIdvendor(),kd.getAmount());
//                            }else {
//                                Double amt = kd.getAmount().doubleValue() + grupByVendorKeluar.get(kd.getIdvendor()).doubleValue();
//                                grupByVendorKeluar.put(kd.getIdvendor(),amt);
//                            }
//                        }
//                    }
//
//                }
//            }
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

                List<ReportKartuDeposit> listKD = grupByVendorParent.get(ven.getId());
                if(listKD != null && listKD.size() > 0){
                    for(ReportKartuDeposit kd : listKD){
                        colomcount = 0;
                        rowcount++;
                        row = sheet.createRow(rowcount);
                        createCell(row, colomcount,kd.getVendorName() +" ("+kd.getVendorAlias()+")", style, sheet,columns);

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
                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            saldo = saldo + kd.getAmount();
                        }else{
                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);
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
//            for(VendorDataForTemplate ven : getListVendor){
//                Double saldoAwal = depositService.calculateSaldoDepositByIdVendorAndBeforeDate(idcompany,idbranch, ven.getId(), param.getFrom());
//                Double saldo = saldoAwal;
//                if(param.getShowNol().equals("NO")){
//                    Double tempSaldo = saldoAwal;
//                    Double saldomasuk = grupByVendorMasuk.get(ven.getId());
//                    Double saldokeluar = grupByVendorKeluar.get(ven.getId());
//                    if(saldomasuk != null){
//                        tempSaldo = tempSaldo.doubleValue() + saldomasuk.doubleValue();
//                    }
//                    if(saldokeluar != null){
//                        tempSaldo = tempSaldo.doubleValue() - saldokeluar.doubleValue();
//                    }
//                    if(tempSaldo.doubleValue() < 1){
//                        continue;
//                    }
//                }
//                colomcount = 0;
//                rowcount++;
//                row = sheet.createRow(rowcount);
//                createCell(row, colomcount, ven.getNama()+" ("+ven.getAlias()+")", style, sheet,columns);
//
//                String transDate = "";
//                try {
//                    transDate = GlobalFunc.getDateLongToString(param.getFrom(), "dd-MMMM-yyyy");
//                } catch (ParseException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                colomcount++;
//                createCell(row, colomcount, transDate, style, sheet,columns);
//
//                if(saldoAwal.doubleValue() > 1){
//                    styleAmount = workbook.createCellStyle();
//                    if(GlobalFunc.checkIsDecimal(saldoAwal)) {
//                        styleAmount.setDataFormat(format.getFormat("#,###"));
//                    }else {
//                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
//                    }
//                    colomcount++;
//                    createCell(row, colomcount, saldoAwal, styleAmount, sheet,columns);
//                }else{
//                    colomcount++;
//                    createCell(row, colomcount, 0, style, sheet,columns);
//                }
//
//
//                colomcount++;
//                createCell(row, colomcount, "", style, sheet,columns);
//
//                colomcount++;
//                createCell(row, colomcount, "", style, sheet,columns);
//
//                colomcount++;
//                createCell(row, colomcount, "", style, sheet,columns);
//
//                colomcount++;
//                createCell(row, colomcount, "", style, sheet,columns);
//
//                List<ReportKartuDeposit> listKD = grupByVendor.get(ven.getId());
//                if(listKD != null && listKD.size() > 0){
//                    for(ReportKartuDeposit kd : listKD){
//                        colomcount = 0;
//                        rowcount++;
//                        row = sheet.createRow(rowcount);
//                        createCell(row, colomcount, ven.getAlias(), style, sheet,columns);
//
//                        transDate = "";
//                        try {
//                            transDate = GlobalFunc.getDateLongToString(kd.getDate().getTime(), "dd-MMMM-yyyy");
//                        } catch (ParseException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                        colomcount++;
//                        createCell(row, colomcount, transDate, style, sheet,columns);
//
//                        colomcount++;
//                        createCell(row, colomcount, "", style, sheet,columns);
//
//                        styleAmount = workbook.createCellStyle();
//                        if(GlobalFunc.checkIsDecimal(kd.getAmount())) {
//                            styleAmount.setDataFormat(format.getFormat("#,###"));
//                        }else {
//                            styleAmount.setDataFormat(format.getFormat("#,###.##"));
//                        }
//                        if(kd.getType().equals("DEPOSIT")){
//                            colomcount++;
//                            createCell(row, colomcount, "", style, sheet,columns);
//
//                            colomcount++;
//                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);
//
//                            saldo = saldo + kd.getAmount();
//                        }else{
//                            colomcount++;
//                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);
//
//                            colomcount++;
//                            createCell(row, colomcount, "", style, sheet,columns);
//                            saldo = saldo - kd.getAmount();
//                        }
//
//
//                        colomcount++;
//                        createCell(row, colomcount, "", style, sheet,columns);
//
//                        colomcount++;
//                        createCell(row, colomcount, kd.getDocumentNumber(), style, sheet,columns);
//                    }
//
//                    colomcount = 0;
//                    rowcount++;
//                    row = sheet.createRow(rowcount);
//                    createCell(row, colomcount, "", style, sheet,columns);
//
//                    colomcount++;
//                    createCell(row, colomcount, "", style, sheet,columns);
//
//                    colomcount++;
//                    createCell(row, colomcount, "", style, sheet,columns);
//
//                    colomcount++;
//                    createCell(row, colomcount, "", style, sheet,columns);
//
//                    colomcount++;
//                    createCell(row, colomcount, "", style, sheet,columns);
//
//                    styleAmount = workbook.createCellStyle();
//                    if(GlobalFunc.checkIsDecimal(saldo)) {
//                        styleAmount.setDataFormat(format.getFormat("#,###"));
//                    }else {
//                        styleAmount.setDataFormat(format.getFormat("#,###.##"));
//                    }
//                    colomcount++;
//                    createCell(row, colomcount, saldo, styleAmount, sheet,columns);
//
//                    colomcount++;
//                    createCell(row, colomcount, "", style, sheet,columns);
//                }
//
//                rowcount++;
//            }
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel reportKartuPinjaman(long idcompany, long idbranch, ParamReportKartuPinjaman param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Kartu Pinjaman");
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
        //ini list vendorparent
        List<VendorDataForTemplate> getListVendor = vendorService.getListDropdown(idcompany,idbranch,paramvendor);
        List<String> idvendors = new ArrayList<>();
        for(VendorDataForTemplate ven : getListVendor){
            list.add(ven.getNama()+" ("+ven.getAlias()+")");
            idvendors.add(ven.getId().toString());
        }
        String listIdVendor = idvendors.toString().replaceAll("\\[","");
        listIdVendor = listIdVendor.replaceAll("\\]","");

        List<Long> listIdParentAndSubIdParent = vendorService.getListSubIdParentByListIdParent(idcompany,idbranch,listIdVendor);
        for(VendorDataForTemplate ven : getListVendor){
            listIdParentAndSubIdParent.add(ven.getId());
        }
        String listIdVendorSubParent = listIdParentAndSubIdParent.toString().replaceAll("\\[","");
        listIdVendorSubParent = listIdVendorSubParent.replaceAll("\\]","");

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
        createCell(row, 0, "Laporan Kartu Pinjaman", style, sheet,columns);

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
        createCell(row, colomcount, "Pinjaman Masuk", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Pinjaman Keluar", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Saldo Akhir", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Document Number", style, sheet,columns);

        if(getListVendor != null && getListVendor.size() > 0) {
            List<ReportKartuPinjaman> listKartuPinjaman = new ArrayList<>();
            ParamReportKartuPinjamanList paramPinjaman = new ParamReportKartuPinjamanList();
            paramPinjaman.setFrom(param.getFrom());
            paramPinjaman.setTo(param.getTo());
            paramPinjaman.setListIdVendor(listIdVendorSubParent);
            List<ReportKartuPinjaman> listpinjaman = pinjamanService.getListReportKartuPinjaman(idcompany, idbranch, paramPinjaman);

            FilterParamPurchaseReceive paramPR = new FilterParamPurchaseReceive();
            paramPR.setFrom(param.getFrom());
            paramPR.setTo(param.getTo());
            paramPR.setListIdVendor(listIdVendorSubParent);
            List<ReportKartuPinjaman> listSetorPinjamanPR = purchaseReceiveService.getListPrReportKartuPinjaman(idcompany, idbranch, paramPR);

            if(listpinjaman != null && listpinjaman.size() > 0){
                listKartuPinjaman.addAll(listpinjaman);
            }

            if(listSetorPinjamanPR != null && listSetorPinjamanPR.size() > 0){
                listKartuPinjaman.addAll(listSetorPinjamanPR);
            }
            if(listKartuPinjaman != null && listKartuPinjaman.size() > 0){
                Collections.sort(listKartuPinjaman);
            }

            HashMap<Long, List<ReportKartuPinjaman>> grupByVendorParent = new HashMap<>();
            HashMap<Long, Double> grupByVendorMasuk = new HashMap<>();
            HashMap<Long, Double> grupByVendorKeluar = new HashMap<>();
            if(listKartuPinjaman != null && listKartuPinjaman.size() > 0) {
                for (VendorDataForTemplate ven : getListVendor) {
                    List<ReportKartuPinjaman> listKDTemp = new ArrayList<>();
                    for (ReportKartuPinjaman kd : listKartuPinjaman) {
                        if (ven.getId().longValue() == kd.getIdvendor().longValue() || ven.getId().longValue() == kd.getIdvendorParent().longValue()) {
                            listKDTemp.add(kd);
                        }
                        if(param.getShowNol().equals("NO")){
                            if(kd.getType().equals("PINJAMAN")){
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
                    grupByVendorParent.put(ven.getId(), listKDTemp);
                }
            }

            for(VendorDataForTemplate ven : getListVendor){
                ParameterPinjaman paramPinjamanCalc = new ParameterPinjaman();
                paramPinjamanCalc.setDate(new java.sql.Date(param.getFrom()));
                paramPinjamanCalc.setIdvendor(ven.getId());
                paramPinjamanCalc.setOperatorPerbandingan("<");
                Double saldoAwal = pinjamanService.calculateSisaPinjamanByIdVendor(idcompany,idbranch,ven.getId(),paramPinjamanCalc.getDate());
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
//                    System.out.println("=== "+ven.getNama()+" ====");
//                    System.out.println("saldomasuk "+saldomasuk);
//                    System.out.println("saldokeluar "+saldokeluar);
//                    System.out.println("tempSaldo "+tempSaldo);
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

                List<ReportKartuPinjaman> listKD = grupByVendorParent.get(ven.getId());
                if(listKD != null && listKD.size() > 0){
                    for(ReportKartuPinjaman kd : listKD){
                        colomcount = 0;
                        rowcount++;
                        row = sheet.createRow(rowcount);
                        createCell(row, colomcount, kd.getVendorName()+" ("+kd.getVendorAlias()+")", style, sheet,columns);

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
                        if(kd.getType().equals("PINJAMAN")){
                            colomcount++;
                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            saldo = saldo + kd.getAmount();
                        }else{
                            colomcount++;
                            createCell(row, colomcount, "", style, sheet,columns);

                            colomcount++;
                            createCell(row, colomcount, kd.getAmount(), styleAmount, sheet,columns);

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
        paramVendor.setOnlyParent("Y");
        ReportTemplate data = new ReportTemplate();
        data.setVendorOpt(vendorService.getListDropdown (idcompany,idbranch,paramVendor));
        return data;
    }

    @Override
    public ReportTemplate reportTemplateReportKartuPinjaman(long idcompany, long idbranch) {
        ParamVendor paramVendor = new ParamVendor();
        paramVendor.setVendorTypes("'UDANG'");
        paramVendor.setOnlyParent("Y");
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
        paramCP.setForcategory("CUSTOMER");
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

        ParamSearchDraftPurchaseReceive paramDPR = new ParamSearchDraftPurchaseReceive();
        paramDPR.setFrom(param.getFrom());
        paramDPR.setTo(param.getTo());
        paramDPR.setListIdProduct(listIdProduct);
        paramDPR.setListIdCategoryProduct(idCategoryProducts);
        paramDPR.setType("H");
//        List<ReportKartuStock> itemsPR = purchaseReceiveService.getListPrReportKartuStock(idcompany,idbranch,paramPR);
        List<ReportKartuStock> itemsDPR = draftPurchaseReceiveService.getListDprReportKartuStock(idcompany,idbranch,paramDPR);

        ParamSearchPackingList paramPL = new ParamSearchPackingList();
        paramPL.setFrom(param.getFrom());
        paramPL.setTo(param.getTo());
        paramPL.setListIdProduct(listIdProduct);
        paramPL.setListIdCategoryProduct(idCategoryProducts);
        List<ReportKartuStock> itemsPL = packingListService.getListReportKartuStock(idcompany,idbranch,paramPL);

        ParamSearchCancelPackingList paramCPL = new ParamSearchCancelPackingList();
        paramCPL.setFrom(param.getFrom());
        paramCPL.setTo(param.getTo());
        paramCPL.setListIdProduct(listIdProduct);
        paramCPL.setListIdCategoryProduct(idCategoryProducts);
        List<ReportKartuStock> tempitemsCPL = cancelPackingListService.getListReportKartuStock(idcompany,idbranch,paramCPL);

        List<ReportKartuStock> itemsCPL = new ArrayList<>();
        if(tempitemsCPL != null && tempitemsCPL.size() > 0){
            for(ReportKartuStock cpl : tempitemsCPL){
                ParamCalculateQtyPL paramCalc = new ParamCalculateQtyPL();
                paramCalc.setIdpackinglist(cpl.getIdpackinglist());
                paramCalc.setIdproduct(cpl.getIdproduct());
                paramCalc.setIdcategoryproduct(cpl.getIdcategoryproduct());
                //qtyInPackingList = summary qty yang ada di PL
                Long qtyInPackingList = packingListService.calculateQtyPLByIdPackingList(idcompany,idbranch,paramCalc);

                //qtyInPackingList akan dikurangi dengan Qty di CPL , qty di CPL adalah udang mati
                Long qtySisa = qtyInPackingList.longValue() - cpl.getQty().longValue();
                ReportKartuStock newCPL = new ReportKartuStock();
                newCPL = cpl;
                newCPL.setQtypackinglistcancel(qtySisa);
                itemsCPL.add(newCPL);
            }
        }
        List<ReportKartuStock> listItems = new ArrayList<>();
        if(itemsSA != null && itemsSA.size() > 0){
            listItems.addAll(itemsSA);
        }
        if(itemsDPR != null && itemsDPR.size() > 0){
            listItems.addAll(itemsDPR);
        }
        if(itemsPL != null && itemsPL.size() > 0){
            listItems.addAll(itemsPL);
        }
        if(itemsCPL != null && itemsCPL.size() > 0){
            listItems.addAll(itemsCPL);
        }
        Collections.sort(listItems);

        List<MappingStockList> listMapping = mappingStockService.getListAll(idcompany,idbranch);
        HashMap<Long, Long> mapMapStock = new HashMap<>();
        HashMap<Long, List<String>> mapMapStockByIDMapping = new HashMap<>();
        //getCategoryproductid = Vendor
        //getCategoryproductidmapping = Customer
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

                                ParamCalculateQtyDPR paramCalcPR = new ParamCalculateQtyDPR();
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

                                ParamCalculateQtyCPL paramCalcCPL = new ParamCalculateQtyCPL();
                                paramCalcCPL.setDateFrom(satuJan70);
                                paramCalcCPL.setDateThru(dateMinus1);
                                paramCalcCPL.setIdproduct(val.getId());
                                if(listIdCPMapping != null){
                                    paramCalcCPL.setListidcategoryproduct(idcategorys);
                                }else{
                                    paramCalcCPL.setIdcategoryproduct(valKS.getIdcategoryproduct());
                                }

                                ParamCalculateQty paramQty = new ParamCalculateQty();
                                paramQty.setParamCalculateQtyDPR(paramCalcPR);
                                paramQty.setParamCalculateQtySA(paramCalcSA);
                                paramQty.setParamCalculateQtyPL(paramCalcPL);
                                paramQty.setParamCalculateQtyCPL(paramCalcCPL);

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
                            }else if(valKS.getType().equals("SA_H") || valKS.getType().equals("DPR")){
                                qtyIn = valKS.getQty().toString();
                            }else if(valKS.getType().equals("CANCELPACKINGLIST")){
                                qtyIn = valKS.getQtypackinglistcancel().toString();
                                if(valKS.getQty().longValue() > 0){
                                    qtyOut = valKS.getQty().toString();
                                }
                            }

                            styleAmount = workbook.createCellStyle();
                            styleAmount.setDataFormat(format.getFormat("#,###"));
                            colomcount++;
                            if(!qtyIn.equals("")){
                                createCell(row, colomcount, Integer.valueOf(qtyIn), styleAmount, sheet,columns);
                            }else{
                                createCell(row, colomcount, qtyIn, style, sheet,columns);
                            }

                            colomcount++;
                            if(!qtyOut.equals("")){
                                createCell(row, colomcount, Integer.valueOf(qtyOut), styleAmount, sheet,columns);
                            }else{
                                createCell(row, colomcount, qtyOut, style, sheet,columns);
                            }


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
                            ParamCalculateQtyDPR paramCalcPR = new ParamCalculateQtyDPR();
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

                            ParamCalculateQtyCPL paramCalcCPL = new ParamCalculateQtyCPL();
                            paramCalcCPL.setDateFrom(satuJan70);
                            paramCalcCPL.setDateThru(param.getTo());
                            paramCalcCPL.setIdproduct(val.getId());
                            if(listIdCPMapping != null){
                                paramCalcCPL.setListidcategoryproduct(idcategorys);
                            }else{
                                paramCalcCPL.setIdcategoryproduct(valCp.getId());
                            }

                            ParamCalculateQty paramQty = new ParamCalculateQty();
                            paramQty.setParamCalculateQtyDPR(paramCalcPR);
                            paramQty.setParamCalculateQtySA(paramCalcSA);
                            paramQty.setParamCalculateQtyPL(paramCalcPL);
                            paramQty.setParamCalculateQtyCPL(paramCalcCPL);
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
        paramCP.setForcategory("CUSTOMER");
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

    @Override
    public ReportWorkBookExcel reportUdangMati(long idcompany, long idbranch, long idstockadjusment) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Udang Mati");
        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(30);

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
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Udang Mati", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Cabang", style, sheet,columns);
        createCell(row, 1, namaCabang, style, sheet,columns);

        int colomcount = 0;
        rowcount++;
        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "Waktu", style, sheet,columns);

        PrintDataStockUdangMati dataStock = stockAdjusmentService.getPrintData(idcompany,idbranch,0L,idstockadjusment,"EXCEL");

        ParamTemplate paramCP = new ParamTemplate();
        paramCP.setShowOnlyCpMapping(true);
        List<CategoryProductList> listCP = dataStock.getListcp();
        HashMap<Long,Integer> placeColumnCP = new HashMap<>();
        if(listCP != null && listCP.size() > 0){
            for(CategoryProductList cp:listCP){
                colomcount++;
                createCell(row, colomcount, cp.getSize(), style, sheet,columns);
                placeColumnCP.put(cp.getId(),colomcount);
            }
            //9999 = TOTAL
            colomcount++;
            createCell(row, colomcount, "TOTAL", style, sheet,columns);
            placeColumnCP.put(9999L,colomcount);
        }

        HashMap<String,Integer> placeRowTime = new HashMap<>();
        colomcount = 0;

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "00:00-02:00", style, sheet,columns);
        placeRowTime.put("00:00-02:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "02:00-04:00", style, sheet,columns);
        placeRowTime.put("02:00-04:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "04:00-06:00", style, sheet,columns);
        placeRowTime.put("04:00-06:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "06:00-08:00", style, sheet,columns);
        placeRowTime.put("06:00-08:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "08:00-10:00", style, sheet,columns);
        placeRowTime.put("08:00-10:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "10:00-12:00", style, sheet,columns);
        placeRowTime.put("10:00-12:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "12:00-14:00", style, sheet,columns);
        placeRowTime.put("12:00-14:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "14:00-16:00", style, sheet,columns);
        placeRowTime.put("14:00-16:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "16:00-18:00", style, sheet,columns);
        placeRowTime.put("16:00-18:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "18:00-20:00", style, sheet,columns);
        placeRowTime.put("18:00-20:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "20:00-22:00", style, sheet,columns);
        placeRowTime.put("20:00-22:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "22:00-24:00", style, sheet,columns);
        placeRowTime.put("22:00-24:00",rowcount);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, colomcount, "TOTAL", style, sheet,columns);
        placeRowTime.put("TOTAL",rowcount);

        if(dataStock.getItems() != null && dataStock.getItems().size() > 0){
            for(StockAdjsumentDataItem item : dataStock.getItems()){
                Integer rowIdx = placeRowTime.get(item.getStocktime());
                Integer colIdx = placeColumnCP.get(item.getIdcategoryproduct());
                if(rowIdx != null && colIdx != null){
                    row = sheet.getRow(rowIdx.intValue());
                    colomcount = colIdx.intValue();

                    styleAmount = workbook.createCellStyle();
                    styleAmount.setDataFormat(format.getFormat("#,###"));
                    createCell(row, colomcount, item.getQty(), styleAmount, sheet,columns);
                }
            }
        }

        data.setWorkbook(workbook);
        return data;
    }

    @Override
    public ReportWorkBookExcel reportReportCancelPackingList(long idcompany, long idbranch, ParamReportCancelPackingList param) {
        ReportWorkBookExcel data = new ReportWorkBookExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFDataFormat format = workbook.createDataFormat();

        XSSFSheet sheet = workbook.createSheet("Laporan Cancel Packing List");
//        sheet.setDefaultColumnWidth(1000);
        List<Integer> columns = getWidthColumns(3000);
        int customColumWitdh = 8000;
        int customColumWitdhCustomer = 12000;
        int customColumWitdhVendor = 12000;

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
        createCell(row, 0, "PT Sumber Berlian Samudra", style, sheet,columns);

        rowcount++;
        row = sheet.createRow(rowcount);
        createCell(row, 0, "Laporan Cancel Packing List", style, sheet,columns);

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
        createCellCustomWidthColumn(row, colomcount, "No Dokumen Cancel PL", style, sheet,customColumWitdh);

        colomcount++;
        createCell(row, colomcount, "Tanggal Dokumen", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "No Dokumen PL", style, sheet,columns);

        colomcount++;
        createCellCustomWidthColumn(row, colomcount, "Customer", style, sheet,customColumWitdhCustomer);

        colomcount++;
        createCellCustomWidthColumn(row, colomcount, "Vendor", style, sheet,customColumWitdhVendor);

        colomcount++;
        createCell(row, colomcount, "Product", style, sheet,columns);

        colomcount++;
        createCellCustomWidthColumn(row, colomcount, "Category Product", style, sheet,customColumWitdh);

        colomcount++;
        createCell(row, colomcount, "Qty PL", style, sheet,columns);

        colomcount++;
        createCell(row, colomcount, "Qty Mati", style, sheet,columns);


        ParamReportCancelPackingList paramCPL = new ParamReportCancelPackingList();
        paramCPL.setFrom(param.getFrom());
        paramCPL.setTo(param.getTo());

        List<ReportCancelPackingList> listData = cancelPackingListService.getReportCancelPackingList(idcompany,idbranch,paramCPL);
        if(listData != null && listData.size() > 0) {
            String noDocCPL = "";
            for(ReportCancelPackingList dataCPL : listData){
                ParamCalculateQtyPL paramCalc = new ParamCalculateQtyPL();
                paramCalc.setIdpackinglist(dataCPL.getIdpackinglist());
                paramCalc.setIdproduct(dataCPL.getIdProduct());
                paramCalc.setIdcategoryproduct(dataCPL.getIdcategoryProduct());
                Long qtyInPackingList = packingListService.calculateQtyPLByIdPackingList(idcompany,idbranch,paramCalc);

                colomcount = 0;

                if(noDocCPL.equals(dataCPL.getNoDocumentCPL())){
                    rowcount++;
                    row = sheet.createRow(rowcount);
                    createCellCustomWidthColumn(row, colomcount, "", style, sheet,10000);

                    String tanggalDoc = "";
//                    try {
//                        tanggalDoc = GlobalFunc.getDateLongToString(dataCPL.getTanggalcancel().getTime(), "dd-MMMM-yyyy");
//                    } catch (ParseException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
                    colomcount++;
                    createCell(row, colomcount, tanggalDoc, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, "", style, sheet,columns);

                    colomcount++;
                    createCellCustomWidthColumn(row, colomcount, "", style, sheet,customColumWitdhCustomer);

                    colomcount++;
                    createCellCustomWidthColumn(row, colomcount, "", style, sheet,customColumWitdhVendor);

                    colomcount++;
                    createCell(row, colomcount, dataCPL.getProductName(), style, sheet,columns);

                    colomcount++;
                    createCellCustomWidthColumn(row, colomcount, dataCPL.getCategoryProductName()+" ("+dataCPL.getCategoryProductSize()+")", style, sheet,customColumWitdh);

                    styleAmount = workbook.createCellStyle();
                    styleAmount.setDataFormat(format.getFormat("#,###"));

                    colomcount++;
                    createCell(row, colomcount, Integer.valueOf(qtyInPackingList.intValue()), styleAmount, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, dataCPL.getQtyMati().intValue(), styleAmount, sheet,columns);
                }else{
                    if(noDocCPL.equals("")){
                        rowcount++;
                    }else{
                        rowcount++;
                        rowcount++;
                    }

                    row = sheet.createRow(rowcount);
                    createCellCustomWidthColumn(row, colomcount, dataCPL.getNoDocumentCPL(), style, sheet,customColumWitdh);

                    String tanggalDoc = "";
                    try {
                        tanggalDoc = GlobalFunc.getDateLongToString(dataCPL.getTanggalcancel().getTime(), "dd-MMMM-yyyy");
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    colomcount++;
                    createCell(row, colomcount, tanggalDoc, style, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, dataCPL.getNoDocumentPL(), style, sheet,columns);

                    colomcount++;
                    createCellCustomWidthColumn(row, colomcount, dataCPL.getCustomerName(), style, sheet,customColumWitdhCustomer);

                    colomcount++;
                    createCellCustomWidthColumn(row, colomcount, dataCPL.getVendorName(), style, sheet,customColumWitdhVendor);

                    colomcount++;
                    createCell(row, colomcount, dataCPL.getProductName(), style, sheet,columns);

                    colomcount++;
                    createCellCustomWidthColumn(row, colomcount, dataCPL.getCategoryProductName()+" ("+dataCPL.getCategoryProductSize()+")", style, sheet,customColumWitdh);

                    styleAmount = workbook.createCellStyle();
                    styleAmount.setDataFormat(format.getFormat("#,###"));

                    colomcount++;
                    createCell(row, colomcount, Integer.valueOf(qtyInPackingList.intValue()), styleAmount, sheet,columns);

                    colomcount++;
                    createCell(row, colomcount, dataCPL.getQtyMati().intValue(), styleAmount, sheet,columns);
                }
                noDocCPL = dataCPL.getNoDocumentCPL();
            }
        }

        data.setWorkbook(workbook);
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

    private Cell createCellCustomWidthColumn(Row row, int columnCount, Object value, CellStyle style,XSSFSheet sheet,int widthColumns) {
//        sheet.autoSizeColumn(columnCount);
        sheet.setColumnWidth(columnCount, widthColumns);
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
