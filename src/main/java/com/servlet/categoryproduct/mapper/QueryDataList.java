package com.servlet.categoryproduct.mapper;

import com.servlet.categoryproduct.entity.CategoryProductList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<CategoryProductList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.size as size,data.weight as weight ");
        sqlBuilder.append("from m_category_product as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CategoryProductList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String size = rs.getString("size");
        final String weight = rs.getString("weight");

        CategoryProductList data = new CategoryProductList();
        data.setId(id);
        data.setNama(nama);
        data.setSize(size);
        data.setWeight(weight);

        return data;
    }
}
