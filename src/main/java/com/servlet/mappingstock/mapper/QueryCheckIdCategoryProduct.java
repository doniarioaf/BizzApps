package com.servlet.mappingstock.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCheckIdCategoryProduct implements RowMapper<Long> {
    private String schemaSql;

    public QueryCheckIdCategoryProduct() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append(" items.categoryproductid as idcategoryproduct ");
        sqlBuilder.append("from mapping_stock as items ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        return idcategoryproduct;
    }
}
