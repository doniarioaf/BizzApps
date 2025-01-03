package com.servlet.pelunasanhutang.mapper;

import com.servlet.pelunasanhutang.entity.PelunasanHutangReportStatusTagihanCargo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanHutangReportStatusTagihanCargo implements RowMapper<PelunasanHutangReportStatusTagihanCargo> {
    private String schemaSql;

    public QueryPelunasanHutangReportStatusTagihanCargo() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.notes as notes, data.idcargo as idcargo ");
        sqlBuilder.append("from pelunasanhutang as data ");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanHutangReportStatusTagihanCargo mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final String notes = rs.getString("notes");
        final Long idcargo = rs.getLong("idcargo");

        PelunasanHutangReportStatusTagihanCargo data = new PelunasanHutangReportStatusTagihanCargo();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setNotes(notes);
        data.setIdcargo(idcargo);
        return data;
    }
}
