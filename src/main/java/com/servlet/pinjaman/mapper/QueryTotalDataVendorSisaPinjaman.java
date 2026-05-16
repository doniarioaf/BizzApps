package com.servlet.pinjaman.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryTotalDataVendorSisaPinjaman implements RowMapper<Long> {
    private String schemaSql;

    public QueryTotalDataVendorSisaPinjaman() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("select COUNT(*) as total ");
        sqlBuilder.append(" from m_vendor as v ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long totalamount = rs.getLong("total");
        return totalamount != null?totalamount:0;
    }
}
