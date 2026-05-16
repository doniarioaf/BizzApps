package com.servlet.purchasereceive.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCheckIdCategoryProduct implements RowMapper<Long> {
    private String schemaSql;

    public QueryCheckIdCategoryProduct() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append(" items.idcategoryproduct as idcategoryproduct ");
        sqlBuilder.append("from purchasereceive_item as items ");
        sqlBuilder.append("left join purchasereceive as data on data.id = items.idpurchasereceive ");

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
