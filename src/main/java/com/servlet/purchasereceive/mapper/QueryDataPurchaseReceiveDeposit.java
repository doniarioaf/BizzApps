package com.servlet.purchasereceive.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataPurchaseReceiveDeposit implements RowMapper<Long> {
    private String schemaSql;

    public QueryDataPurchaseReceiveDeposit() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("iddeposit as iddeposit ");
        sqlBuilder.append("from purchasereceive_deposit as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long iddeposit = rs.getLong("iddeposit");
        return iddeposit != null?iddeposit:0L;
    }
}
