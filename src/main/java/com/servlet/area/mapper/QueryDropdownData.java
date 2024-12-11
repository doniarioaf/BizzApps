package com.servlet.area.mapper;

import com.servlet.area.entity.AreaDropdownList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDropdownData implements RowMapper<AreaDropdownList> {
    private String schemaSql;

    public QueryDropdownData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.alias as alias ");
        sqlBuilder.append("from m_area as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public AreaDropdownList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");

        AreaDropdownList data = new AreaDropdownList();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        return data;
    }
}
