package com.servlet.cancelpackinglist.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCalculateQtyCPL implements RowMapper<Long> {
    private String schemaSql;

    public QueryCalculateQtyCPL() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("sum(data.qty) as total ");
        sqlBuilder.append("from cancel_packinglistitems as data ");

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
