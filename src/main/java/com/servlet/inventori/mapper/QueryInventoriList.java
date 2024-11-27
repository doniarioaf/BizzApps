package com.servlet.inventori.mapper;

import com.servlet.inventori.entity.ListInventoriData;
import com.servlet.product.entity.ListProductData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryInventoriList implements RowMapper<ListInventoriData> {
    private String schemaSql;

    public QueryInventoriList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.sku as sku ");
        sqlBuilder.append("from m_inventori as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ListInventoriData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String sku = rs.getString("sku");

        ListInventoriData data = new ListInventoriData();
        data.setId(id);
        data.setNama(nama);
        data.setSku(sku);

        return data;
    }
}
