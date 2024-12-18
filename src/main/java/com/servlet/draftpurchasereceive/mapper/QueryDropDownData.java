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
        sqlBuilder.append("data.smu as smu ");
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
        DraftPurchaseReceiveDropDownList data = new DraftPurchaseReceiveDropDownList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setSmu(smu);
        return data;
    }
}
