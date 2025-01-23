package com.servlet.vendor.mapper;

import com.servlet.vendor.entity.ListVendorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryGetIdParent implements RowMapper<Long> {
    private String schemaSql;

    public QueryGetIdParent() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append(" data.idvendorparent as idvendorparent ");
        sqlBuilder.append(" from m_vendor as data ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idvendorparent = rs.getLong("idvendorparent");
        return idvendorparent;
    }
}
