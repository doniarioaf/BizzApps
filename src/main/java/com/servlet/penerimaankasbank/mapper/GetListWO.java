package com.servlet.penerimaankasbank.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetListWO implements RowMapper<Long> {

    private String schemaSql;

    public GetListWO() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("inv.idwo as idwo ");
        sqlBuilder.append("from detail_penerimaan_kas_bank as data ");
        sqlBuilder.append("left join m_penerimaan_kas_bank as penerimaan on penerimaan.id = data.idpenerimaankasbank ");
        sqlBuilder.append("left join m_invoice as inv on inv.id = data.idinvoice ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idwo = rs.getLong("idwo");
        return idwo;
    }
}
