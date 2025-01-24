package com.servlet.area.mapper;

import com.servlet.area.entity.AreaList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryListData implements RowMapper<AreaList> {
    private String schemaSql;

    public QueryListData() {
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
    public AreaList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");

        AreaList data = new AreaList();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        return data;
    }
}
