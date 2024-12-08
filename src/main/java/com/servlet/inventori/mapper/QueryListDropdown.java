package com.servlet.inventori.mapper;

import com.servlet.inventori.entity.ListDropdownData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryListDropdown implements RowMapper<ListDropdownData> {
    private String schemaSql;

    public QueryListDropdown() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama ");
        sqlBuilder.append("from m_inventori as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ListDropdownData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        ListDropdownData data = new ListDropdownData();
        data.setId(id);
        data.setNama(nama);

        return data;
    }
}
