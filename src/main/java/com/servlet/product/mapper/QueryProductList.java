package com.servlet.product.mapper;

import com.servlet.customer.entity.ListCustomerData;
import com.servlet.product.entity.ListProductData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryProductList implements RowMapper<ListProductData> {
    private String schemaSql;

    public QueryProductList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.sku as sku ");
        sqlBuilder.append("from m_product as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ListProductData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String sku = rs.getString("sku");

        ListProductData data = new ListProductData();
        data.setId(id);
        data.setNama(nama);
        data.setSku(sku);

        return data;
    }
}
