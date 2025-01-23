package com.servlet.vendor.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryGetId implements RowMapper<Long> {
    private String schemaSql;

    public QueryGetId() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append(" data.id as id ");
        sqlBuilder.append(" from m_vendor as data ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        return id;
    }
}
