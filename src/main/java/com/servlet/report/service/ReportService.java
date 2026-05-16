package com.servlet.report.service;

import com.servlet.cancelpackinglist.entity.ParamReportCancelPackingList;
import com.servlet.pinjaman.entity.ParamReportKartuPinjaman;
import com.servlet.report.entity.*;

import java.text.ParseException;

public interface ReportService {
    ReportWorkBookExcel getExcelPackingListByID(long id, long idcompany, long idbranch, long iduser);
    ReportWorkBookExcel getExcelCancelPackingListByID(long id, long idcompany, long idbranch, long iduser);
    ReportWorkBookExcel getExcelInvoiceByID(long id, long idcompany, long idbranch, long iduser);
    ReportWorkBookExcel getExcelInvoiceByID2(long id, long idcompany, long idbranch, long iduser);
    ReportWorkBookExcel reportPembelian(long idcompany, long idbranch, ParamReportPembelian param);
    ReportWorkBookExcel reportStockUdangHidupMati(long idcompany, long idbranch, ParamReportStockUdangHidupMati param);
    ReportWorkBookExcel reportRekapStock(long idcompany, long idbranch, ParamReportRekapStock param);
    ReportWorkBookExcel reportStatusTagihanCargo(long idcompany, long idbranch, ParamReportStatusTagihanCargo param);
    ReportTemplate reportTemplateStatusTagihanCargo(long idcompany, long idbranch);
    ReportWorkBookExcel reportHutang(long idcompany, long idbranch, ParamReportHutang param);
    ReportTemplate reportTemplateReportHutang(long idcompany, long idbranch);
    ReportWorkBookExcel reportPiutang(long idcompany, long idbranch, ParamReportPiutang param);
    ReportTemplate reportTemplateReportPiutang(long idcompany, long idbranch);
    ReportWorkBookExcel reportPenjualan(long idcompany, long idbranch, ParamReportPenjualan param);
    ReportWorkBookExcel reportPelunasanPiutang(long idcompany, long idbranch, ParamReportPelunasanPiutang param);
    ReportWorkBookExcel reportKartuDeposit(long idcompany, long idbranch, ParamReportKartuDeposit param);
    ReportWorkBookExcel reportKartuPinjaman(long idcompany, long idbranch, ParamReportKartuPinjaman param);
    ReportTemplate reportTemplateReportKartuDeposit(long idcompany, long idbranch);
    ReportTemplate reportTemplateReportKartuPinjaman(long idcompany, long idbranch);
    ReportWorkBookExcel reportReportKartuStock(long idcompany, long idbranch, ParamReportKartuStock param);
    ReportTemplate reportTemplateReportKartuStock(long idcompany, long idbranch);
    ReportWorkBookExcel reportReportKomisi(long idcompany, long idbranch, ParamReportKomisi param);
    ReportTemplate reportTemplateReportKomisi(long idcompany, long idbranch);
    ReportWorkBookExcel reportUdangMati(long idcompany, long idbranch, long idstockadjusment);
    ReportWorkBookExcel reportReportCancelPackingList(long idcompany, long idbranch, ParamReportCancelPackingList param);
}
