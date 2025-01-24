package com.servlet.pelunasanpiutang.mapper;

import com.servlet.pelunasanpiutang.entity.ReportPelunasanPiutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataReportPelunasanPiutang implements RowMapper<ReportPelunasanPiutang> {
    private String schemaSql;

    public QueryDataReportPelunasanPiutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idinvoice as idinvoice, data.biayabebanudangmati as biayabebanudangmati, data.biayabank as biayabank, data.pembayaran as pembayaran, ");
        sqlBuilder.append("data.metodepembayaran as metodepembayaran, ");
        sqlBuilder.append("pp.nodocument as nodocument, pp.date as date,pp.kurs as kurs ");
        sqlBuilder.append("from pelunasanpiutang_item as data ");
        sqlBuilder.append("left join pelunasanpiutang as pp on pp.id = data.idpelunasanpiutang ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportPelunasanPiutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idinvoice = rs.getLong("idinvoice");
        final Double biayabebanudangmati = rs.getDouble("biayabebanudangmati");
        final Double biayabank = rs.getDouble("biayabank");
        final Double pembayaran = rs.getDouble("pembayaran");
        final String metodepembayaran = rs.getString("metodepembayaran");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Double kurs = rs.getDouble("kurs");
        ReportPelunasanPiutang data = new ReportPelunasanPiutang();
        data.setIdinvoice(idinvoice);
        data.setBiayabebanudangmati(biayabebanudangmati);
        data.setBiayabank(biayabank);
        data.setPembayaran(pembayaran);
        data.setMetodepembayaran(metodepembayaran);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setKurs(kurs);

        return data;
    }
}
