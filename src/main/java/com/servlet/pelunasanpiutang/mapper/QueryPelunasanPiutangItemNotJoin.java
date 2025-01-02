package com.servlet.pelunasanpiutang.mapper;

import com.servlet.pelunasanpiutang.entity.PelunasanPiutangItemDetail;
import com.servlet.pelunasanpiutang.entity.PelunasanPiutangItemNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanPiutangItemNotJoin implements RowMapper<PelunasanPiutangItemNotJoin> {
    private String schemaSql;

    public QueryPelunasanPiutangItemNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idinvoice as idinvoice, data.biayabebanudangmati as biayabebanudangmati, data.biayabank as biayabank, data.pembayaran as pembayaran, ");
        sqlBuilder.append("data.metodepembayaran as metodepembayaran ");
        sqlBuilder.append("from pelunasanpiutang_item as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanPiutangItemNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idinvoice = rs.getLong("idinvoice");
        final Double biayabebanudangmati = rs.getDouble("biayabebanudangmati");
        final Double biayabank = rs.getDouble("biayabank");
        final Double pembayaran = rs.getDouble("pembayaran");
        final String metodepembayaran = rs.getString("metodepembayaran");
        PelunasanPiutangItemNotJoin data = new PelunasanPiutangItemNotJoin();
        data.setIdinvoice(idinvoice);
        data.setBiayabebanudangmati(biayabebanudangmati);
        data.setBiayabank(biayabank);
        data.setPembayaran(pembayaran);
        data.setMetodepembayaran(metodepembayaran);

        return data;
    }
}
