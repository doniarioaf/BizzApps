package com.servlet.mappingstock.mapper;

import com.servlet.mappingstock.entity.MappingStockCategoryID;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataCategoryProductID implements RowMapper<MappingStockCategoryID> {
    private String schemaSql;

    public QueryDataCategoryProductID() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.categoryproductid as categoryproductid, data.categoryproductidmapping as categoryproductidmapping ");
        sqlBuilder.append("from mapping_stock as data ");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public MappingStockCategoryID mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long categoryproductid = rs.getLong("categoryproductid");
        final Long categoryproductidmapping = rs.getLong("categoryproductidmapping");

        MappingStockCategoryID data = new MappingStockCategoryID();
        data.setCategoryproductid(categoryproductid);
        data.setCategoryproductidmapping(categoryproductidmapping);

        return data;
    }
}
