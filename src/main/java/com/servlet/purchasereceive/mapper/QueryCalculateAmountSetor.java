package com.servlet.purchasereceive.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCalculateAmountSetor implements RowMapper<Double> {
    private String schemaSql;

    public QueryCalculateAmountSetor() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("sum(data.setor) as total ");
        sqlBuilder.append("from purchasereceive as data ");

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
