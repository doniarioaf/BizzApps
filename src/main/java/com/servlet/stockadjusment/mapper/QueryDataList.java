package com.servlet.stockadjusment.mapper;

import com.servlet.stockadjusment.entity.StockAdjusmentDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<StockAdjusmentDataList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.type as type, data.date as date ");
        sqlBuilder.append("from stock_adjusment as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public StockAdjusmentDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final String type = rs.getString("type");
        final Date date = rs.getDate("date");
        StockAdjusmentDataList data = new StockAdjusmentDataList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setType(type);
        data.setDate(date);
        return data;
    }
}
