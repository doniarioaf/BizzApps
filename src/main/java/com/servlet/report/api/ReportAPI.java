package com.servlet.report.api;

import com.servlet.report.entity.*;
import com.servlet.security.service.SecurityService;
import com.servlet.shared.ConstansKey;
import com.servlet.shared.ConstansPermission;
import com.servlet.shared.Response;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/report")
@CrossOrigin(origins = "${value.cross_origin}")
public class ReportAPI {
    @Autowired
    SecurityService securityService;

    @GetMapping("/reportstockudanghidupmati")
    ResponseEntity<Response> getReportStockUdangHidupMati(@RequestParam("from") Long date, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportStockUdangHidupMati body = new ParamReportStockUdangHidupMati();
        body.setDate(date);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTUDANGHIDUPMATI");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_STOCKUDANGHIDUPMATI,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportrekapbarangmasuk")
    ResponseEntity<Response> getReportRekapanBarangMasuk(@RequestParam("from") Long date,@RequestParam("shownol") String shownol, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportRekapStock body = new ParamReportRekapStock();
        body.setDate(date);
        body.setShowNol(shownol);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTREKAPANBARANGMASUK");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_REKAPANBARANGMASUK,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reporstatustagihancargo")
    ResponseEntity<Response> getReportStatusTagihanCargo(@RequestParam("status") String status, @RequestParam("idvendors") String idvendors, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportStatusTagihanCargo body = new ParamReportStatusTagihanCargo();
        body.setFrom(from);
        body.setTo(to);
        body.setIdvendors(idvendors);
        body.setStatus(status);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTSTATUSTAGIHANCARGO");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_STATUSTAGIHANCARGO,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reporstatustagihancargo/template")
    ResponseEntity<Response> getReportStatusTagihanCargoTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTSTATUSTAGIHANCARGO_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_STATUSTAGIHANCARGO,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/reporthutang")
    ResponseEntity<Response> getReportHutang(@RequestParam("status") String status, @RequestParam("idvendors") String idvendors,@RequestParam("vendorttypes") String vendorttypes, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportHutang body = new ParamReportHutang();
        body.setFrom(from);
        body.setTo(to);
        body.setIdvendors(idvendors);
        body.setVendorType(vendorttypes);
        body.setStatus(status);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTHUTANG");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_HUTANG,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reporthutang/template")
    ResponseEntity<Response> getReportHutangTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTHUTANG_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_HUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/reportpiutang")
    ResponseEntity<Response> getReportPiutang(@RequestParam("status") String status, @RequestParam("idcustomer") String idcustomer,@RequestParam("grups") String grups, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportPiutang body = new ParamReportPiutang();
        body.setFrom(from);
        body.setTo(to);
        body.setListidcustomer(idcustomer);
        body.setListGroup(grups);
        body.setStatus(status);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTPIUTANG");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_PIUTANG,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportpiutang/template")
    ResponseEntity<Response> getReportPiutangTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTPIUTANG_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_PIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/reportpenjualan")
    ResponseEntity<Response> getReportPenjualan( @RequestParam("idcustomer") String idcustomer,@RequestParam("grups") String grups, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportPenjualan body = new ParamReportPenjualan();
        body.setFrom(from);
        body.setTo(to);
        body.setListidcustomer(idcustomer);
        body.setListGroup(grups);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTPENJUALAN");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_PENJUALAN,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportpenjualan/template")
    ResponseEntity<Response> getReportPenjualanTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTPENJUALAN_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_PENJUALAN,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }


    @GetMapping("/reportpelunasanpiutang")
    ResponseEntity<Response> getReportPelunasanPiutang(@RequestParam("status") String status, @RequestParam("idcustomer") String idcustomer,@RequestParam("grups") String grups, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportPelunasanPiutang body = new ParamReportPelunasanPiutang();
        body.setFrom(from);
        body.setTo(to);
        body.setListidcustomer(idcustomer);
        body.setListGroup(grups);
        body.setStatus(status);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTPELUNASANPIUTANG");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_PELUNASAN_PIUTANG,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportpelunasanpiutang/template")
    ResponseEntity<Response> getReportPelunasanPiutangTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTPELUNASANPIUTANG_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_PELUNASAN_PIUTANG,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/reportkartudeposit")
    ResponseEntity<Response> getReportKartuDeposit(@RequestParam("shownol") String shownol, @RequestParam("idvendors") String idvendors, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportKartuDeposit body = new ParamReportKartuDeposit();
        body.setFrom(from);
        body.setTo(to);
        body.setShowNol(shownol);
        body.setIdvendors(idvendors);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTKARTUDEPOSIT");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_KARTUDEPOSIT,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportkartudeposit/template")
    ResponseEntity<Response> getReportKartuDepositTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTKARTUDEPOSIT_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_KARTUDEPOSIT,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/reportkartustock")
    ResponseEntity<Response> getReportKartuStock( @RequestParam("idproducts") String idproducts,@RequestParam("idcategoryproducts") String idcategoryproducts, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportKartuStock body = new ParamReportKartuStock();
        body.setFrom(from);
        body.setTo(to);
        body.setListIdProduct(idproducts);
        body.setListIdCategoryProduct(idcategoryproducts);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTKARTUSTOCK");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_KARTUSTOCK,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportkartustock/template")
    ResponseEntity<Response> getReportKartuStockTemplate(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTKARTUSTOCK_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_KARTUSTOCK,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @GetMapping("/reportkomisi")
    ResponseEntity<Response> getReportKomisi( @RequestParam("idvendorbrokers") String idvendorbrokers, @RequestParam("from") Long from,@RequestParam("to") Long to, HttpServletResponse response, @RequestHeader(ConstansKey.AUTH) String authorization) throws IOException{
        ParamReportKomisi body = new ParamReportKomisi();
        body.setFrom(from);
        body.setTo(to);
        body.setListIdvendorbroker(idvendorbrokers);
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTKOMISI");
        param.put("body", body);
        Response response1 = securityService.response(ConstansPermission.READ_REPORT_KOMISI,param,authorization);
        if(response1.getHttpcode() == HttpStatus.OK.value()) {
            XSSFWorkbook workbook = (XSSFWorkbook) response1.getData();
            export(response, workbook);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(response1.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response1);
        }

    }

    @GetMapping("/reportkomisi/template")
    ResponseEntity<Response> getReportKomisi(@RequestHeader(ConstansKey.AUTH) String authorization) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("type", "REPORTKOMISI_TEMPLATE");
        Response response = securityService.response(ConstansPermission.READ_REPORT_KOMISI,param,authorization);
        return ResponseEntity.status(response.getHttpcode()).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private void export(HttpServletResponse response, XSSFWorkbook workbook) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
