package com.servlet.cargo.mapper;

import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCargoReportHutang implements RowMapper<ReportPelunasanHutangDocumentHutang> {
    private String schemaSql;

    public QueryCargoReportHutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.date as date, data.invoicenumber as invoicenumber, ");
        sqlBuilder.append("data.netamount as netamount ");
        sqlBuilder.append("from cargo as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportPelunasanHutangDocumentHutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Date date = rs.getDate("date");
        final String invoicenumber = rs.getString("invoicenumber");
        final Double netamount = rs.getDouble("netamount");
        ReportPelunasanHutangDocumentHutang data = new ReportPelunasanHutangDocumentHutang();
        data.setIddoc(id);
        data.setDate(date);
        data.setNodocument(invoicenumber);
        data.setAmountInvoice(netamount);
        data.setDocType("CARGO");
        return data;
    }
}
