package com.servlet.pinjaman.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCalculateAmountPinjaman implements RowMapper<Double> {
    private String schemaSql;

    public QueryCalculateAmountPinjaman() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("sum(data.amount) as total ");
        sqlBuilder.append("from pinjaman as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Double totalamount = rs.getDouble("total");
        return totalamount != null?totalamount:0;
    }
}
