package com.servlet.report.handler;

import com.servlet.packinglist.entity.PackingListDataItemDetail;
import com.servlet.packinglist.entity.PrintPackingList;
import com.servlet.packinglist.service.PackingListService;
import com.servlet.report.entity.ReportWorkBookExcel;
import com.servlet.report.service.ReportService;
import com.servlet.shared.GlobalFunc;
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

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportHandler implements ReportService {

    @Autowired
    PackingListService packingListService;

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
