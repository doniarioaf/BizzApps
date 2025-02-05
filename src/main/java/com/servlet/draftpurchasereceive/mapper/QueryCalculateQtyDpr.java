package com.servlet.draftpurchasereceive.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCalculateQtyDpr implements RowMapper<Long> {
    private String schemaSql;

    public QueryCalculateQtyDpr() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("sum(data.ekor) as total ");
        sqlBuilder.append("from draft_purchasereceive_items as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long total = rs.getLong("total");
        return total != null?total:0L;
    }
}
