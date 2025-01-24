package com.servlet.invoice.mapper;

import com.servlet.invoice.entity.PrintInvoice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPrintInvoice implements RowMapper<PrintInvoice> {
    private String schemaSql;

    public QueryPrintInvoice() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.kurs as kurs, ");
        sqlBuilder.append("data.idpackinglist as idpackinglist, data.phone as phone ");
        sqlBuilder.append("from invoice as data ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PrintInvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Double kurs = rs.getDouble("kurs");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String phone = rs.getString("phone");
        PrintInvoice data = new PrintInvoice();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setKurs(kurs);
        data.setIdpackinglist(idpackinglist);
        data.setPhone(phone);
        return data;
    }
}
