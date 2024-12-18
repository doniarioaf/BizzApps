package com.servlet.purchasereceive.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryColumnIDVendor implements RowMapper<Long> {
    private String schemaSql;

    public QueryColumnIDVendor() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idvendor as idvendor ");
        sqlBuilder.append("from purchasereceive as data ");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idvendor = rs.getLong("idvendor");
        return idvendor;
    }
}
