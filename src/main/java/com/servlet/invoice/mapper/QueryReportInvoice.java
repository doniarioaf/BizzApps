package com.servlet.invoice.mapper;

import com.servlet.invoice.entity.ReportInvoice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryReportInvoice implements RowMapper<ReportInvoice> {
    private String schemaSql;

    public QueryReportInvoice() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, wo.noaju as noaju, data.nilaireimbursement as nilaireimbursement, data.nilaippn as nilaippn, ");
        sqlBuilder.append("data.nilaijasa as nilaijasa, data.totalinvoice as totalinvoice, data.nofakturpajak as nofakturpajak, cust.customername as customername, ");
        sqlBuilder.append("penerimaan.id as idpenerimaan, penerimaan.receivedate as penerimaanreceivedate, bank.namabank as namabank, detpenerimaan.nilaijasa as detpenerimaannilaijasa, ");
        sqlBuilder.append("detpenerimaan.nilaibuktipotong as detpenerimaannilaibuktipotong, detpenerimaan.nobuktipotong as detpenerimaannobuktipotong, detpenerimaan.tanggalbuktipotong as detpenerimaantanggalbuktipotong, detpenerimaan.penyesuaian as detpenerimaanpenyesuaian, detpenerimaan.keterangan_penyesuaian as detpenerimaanketerangan_penyesuaian ");
        sqlBuilder.append("from m_invoice as data ");
        sqlBuilder.append("left join m_workorder as wo on wo.id = data.idwo ");
        sqlBuilder.append("left join detail_penerimaan_kas_bank as detpenerimaan on detpenerimaan.idinvoice = data.id ");
        sqlBuilder.append("left join m_penerimaan_kas_bank as penerimaan on detpenerimaan.idpenerimaankasbank = penerimaan.id ");
        sqlBuilder.append("left join m_bank_account as bank on bank.id = penerimaan.idbank ");
        sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportInvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date tanggal = rs.getDate("tanggal");
        final String noaju = rs.getString("noaju");
        final Double nilaireimbursement = rs.getDouble("nilaireimbursement");
        final Double nilaippn = rs.getDouble("nilaippn");
        final Double nilaijasa = rs.getDouble("nilaijasa");
        final Double totalinvoice = rs.getDouble("totalinvoice");
        final Long idpenerimaan = rs.getLong("idpenerimaan");
        final Date penerimaanreceivedate = rs.getDate("penerimaanreceivedate");
        final String namabank = rs.getString("namabank");
        final Double detpenerimaannilaijasa = rs.getDouble("detpenerimaannilaijasa");
        final Double detpenerimaannilaibuktipotong = rs.getDouble("detpenerimaannilaibuktipotong");
        final String detpenerimaannobuktipotong = rs.getString("detpenerimaannobuktipotong");
        final Date detpenerimaantanggalbuktipotong = rs.getDate("detpenerimaantanggalbuktipotong");
        final Double detpenerimaanpenyesuaian = rs.getDouble("detpenerimaanpenyesuaian");
        final String detpenerimaanketerangan_penyesuaian = rs.getString("detpenerimaanketerangan_penyesuaian");
        final String customername = rs.getString("customername");
        final String nofakturpajak = rs.getString("nofakturpajak");


        ReportInvoice data = new ReportInvoice();
        data.setIdinvoice(id);
        data.setTanggalInvoice(tanggal);
        data.setNoInvoice(nodocument);
        data.setAju(noaju);
        data.setNilaireimbursement(nilaireimbursement);
        data.setNilaippn(nilaippn);
        data.setNilaijasa(nilaijasa);
        data.setTotalinvoice(totalinvoice);
        data.setCustomerName(customername);
        data.setNofakturpajak(nofakturpajak);

        data.setIdpenerimaan(idpenerimaan);
        data.setTanggalPelunasan(penerimaanreceivedate);
        data.setBankName(namabank);
        data.setPelunasanjasa(detpenerimaannilaijasa);
        data.setNilaibuktipotong(detpenerimaannilaibuktipotong);
        data.setNobuktipotong(detpenerimaannobuktipotong);
        data.setTanggalbuktipotong(detpenerimaantanggalbuktipotong);
        data.setPenyesuaian(detpenerimaanpenyesuaian);
        data.setKetpenyesuaian(detpenerimaanketerangan_penyesuaian);

        return data;
    }
}
