package com.servlet.pelunasanpiutang.mapper;

import com.servlet.pelunasanpiutang.entity.PelunasanPiutangItemDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanPiutangItemDetail implements RowMapper<PelunasanPiutangItemDetail> {
    private String schemaSql;

    public QueryPelunasanPiutangItemDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idinvoice as idinvoice, data.biayabebanudangmati as biayabebanudangmati, data.biayabank as biayabank, data.pembayaran as pembayaran, ");
        sqlBuilder.append("data.metodepembayaran as metodepembayaran, ");
        sqlBuilder.append("inv.nodocument as invnodocument, inv.kurs as invkurs,inv.amount as invamount, inv.outstanding as invoutstanding, inv.date as invdate ");
        sqlBuilder.append("from pelunasanpiutang_item as data ");
        sqlBuilder.append("left join invoice as inv on inv.id = data.idinvoice ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanPiutangItemDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idinvoice = rs.getLong("idinvoice");
        final Double biayabebanudangmati = rs.getDouble("biayabebanudangmati");
        final Double biayabank = rs.getDouble("biayabank");
        final Double pembayaran = rs.getDouble("pembayaran");
        final String metodepembayaran = rs.getString("metodepembayaran");
        final String invnodocument = rs.getString("invnodocument");
        final Double invkurs = rs.getDouble("invkurs");
        final Double invamount = rs.getDouble("invamount");
        final Double invoutstanding = rs.getDouble("invoutstanding");
        final Date invdate = rs.getDate("invdate");

        PelunasanPiutangItemDetail data = new PelunasanPiutangItemDetail();
        data.setIdinvoice(idinvoice);
        data.setBiayabebanudangmati(biayabebanudangmati);
        data.setBiayabank(biayabank);
        data.setPembayaran(pembayaran);
        data.setMetodepembayaran(metodepembayaran);
        data.setNodocumentInvoice(invnodocument);
        data.setKursInvoice(invkurs);
        data.setAmountInvoice(invamount);
        data.setOutstandingInvoice(invoutstanding);
        data.setInvoiceDate(invdate);
        return data;
    }
}
