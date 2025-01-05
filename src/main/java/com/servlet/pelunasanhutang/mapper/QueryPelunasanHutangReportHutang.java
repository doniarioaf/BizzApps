package com.servlet.pelunasanhutang.mapper;

import com.servlet.pelunasanhutang.entity.PelunasanHutangReportHutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanHutangReportHutang implements RowMapper<PelunasanHutangReportHutang> {
    private String schemaSql;

    public QueryPelunasanHutangReportHutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, ");
        sqlBuilder.append("data.date as date, data.notes as notes,data.amount as amount ");
        sqlBuilder.append("from pelunasanhutang as data ");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanHutangReportHutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final String notes = rs.getString("notes");
        final Double amount = rs.getDouble("amount");
        PelunasanHutangReportHutang data = new PelunasanHutangReportHutang();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setNotes(notes);
        data.setAmount(amount);
        return data;
    }
}
