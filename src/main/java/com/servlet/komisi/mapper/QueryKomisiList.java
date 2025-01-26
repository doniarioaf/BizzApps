package com.servlet.komisi.mapper;

import com.servlet.komisi.entity.KomisiList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryKomisiList implements RowMapper<KomisiList> {
    private String schemaSql;

    public QueryKomisiList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.note as note ");
        sqlBuilder.append("from komisi as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public KomisiList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final String note = rs.getString("note");
        KomisiList data = new KomisiList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setNote(note);
        return data;
    }
}
