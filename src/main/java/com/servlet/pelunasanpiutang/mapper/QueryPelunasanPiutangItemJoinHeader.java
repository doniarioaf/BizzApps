package com.servlet.pelunasanpiutang.mapper;

import com.servlet.pelunasanpiutang.entity.PelunasanPiutangItemJoinHeader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanPiutangItemJoinHeader implements RowMapper<PelunasanPiutangItemJoinHeader> {
    private String schemaSql;

    public QueryPelunasanPiutangItemJoinHeader() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("pelunasan.id as id, pelunasan.nodocument as nodocument, pelunasan.date as date,  ");
        sqlBuilder.append("data.idinvoice as idinvoice ");
        sqlBuilder.append("from pelunasanpiutang_item as data ");
        sqlBuilder.append("left join pelunasanpiutang as pelunasan on pelunasan.id = data.idpelunasanpiutang ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanPiutangItemJoinHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Long idinvoice = rs.getLong("idinvoice");
        PelunasanPiutangItemJoinHeader data = new PelunasanPiutangItemJoinHeader();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setIdinvoice(idinvoice);
        return data;
    }
}
