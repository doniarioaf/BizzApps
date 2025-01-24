package com.servlet.pelunasanhutang.mapper;

import com.servlet.pelunasanhutang.entity.PelunasanHutangDataNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanHutangDataNotJoin implements RowMapper<PelunasanHutangDataNotJoin> {
    private String schemaSql;

    public QueryPelunasanHutangDataNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idpurchasereceive as idpurchasereceive, data.idcargo as idcargo, ");
        sqlBuilder.append("data.date as date, data.amount as amount ");
        sqlBuilder.append("from pelunasanhutang as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanHutangDataNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long idcargo = rs.getLong("idcargo");
        final Date date = rs.getDate("date");
        final Double amount = rs.getDouble("amount");
        PelunasanHutangDataNotJoin data = new PelunasanHutangDataNotJoin();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdpurchasereceive(idpurchasereceive);
        data.setIdcargo(idcargo);
        data.setDate(date);
        data.setAmount(amount);
        return data;
    }
}
