package com.servlet.draftpurchasereceive.mapper;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveDropDownList;
import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDropDownData implements RowMapper<DraftPurchaseReceiveDropDownList> {
    private String schemaSql;

    public QueryDropDownData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, ");
        sqlBuilder.append("data.smu as smu, data.flightno as flightno, data.notes1 as notes1, data.notes2 as notes2 ");
        sqlBuilder.append("from draft_purchasereceive as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }


    @Override
    public DraftPurchaseReceiveDropDownList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final String smu = rs.getString("smu");
        final String flightno = rs.getString("flightno");
        final String notes1 = rs.getString("notes1");
        final String notes2 = rs.getString("notes2");
        DraftPurchaseReceiveDropDownList data = new DraftPurchaseReceiveDropDownList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setSmu(smu);
        data.setFlightno(flightno);
        data.setNotes1(notes1);
        data.setNotes2(notes2);
        return data;
    }
}
