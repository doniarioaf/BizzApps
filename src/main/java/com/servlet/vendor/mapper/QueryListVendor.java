package com.servlet.vendor.mapper;

import com.servlet.product.entity.ListProductData;
import com.servlet.vendor.entity.ListVendorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryListVendor implements RowMapper<ListVendorData> {
    private String schemaSql;

    public QueryListVendor() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.alias as alias,data.type as type ");
        sqlBuilder.append("from m_vendor as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ListVendorData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final String type = rs.getString("type");
        ListVendorData data = new ListVendorData();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        data.setType(type);

        return data;
    }
}
