package com.servlet.stockadjusment.mapper;

import com.servlet.stockadjusment.entity.PrintDataStockUdangMati;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPrintDataStockUdangMati implements RowMapper<PrintDataStockUdangMati> {
    private String schemaSql;

    public QueryPrintDataStockUdangMati() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.note as note, data.type as type, ");
        sqlBuilder.append("usercreate.nama as createdname ");
        sqlBuilder.append("from stock_adjusment as data ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PrintDataStockUdangMati mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final String note = rs.getString("note");
        final String type = rs.getString("type");
        final String createdname = rs.getString("createdname");

        PrintDataStockUdangMati data = new PrintDataStockUdangMati();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setNote(note);
        data.setType(type);
        data.setCreatedbyName(createdname);

        return data;
    }
}
