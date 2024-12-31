package com.servlet.pelunasanhutang.mapper;

import com.servlet.pelunasanhutang.entity.PelunasanHutangDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanHutangDataList implements RowMapper<PelunasanHutangDataList> {
    private String schemaSql;

    public QueryPelunasanHutangDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idpurchasereceive as idpurchasereceive, data.idcargo as idcargo, ");
        sqlBuilder.append("data.date as date, data.amount as amount, ");
        sqlBuilder.append("pr.nodocument as nodocumentpr, ");
        sqlBuilder.append("venpr.nama as venprnama,venpr.alias as venpralias, ");
        sqlBuilder.append("cargo.invoicenumber as invoicenumbercargo, ");
        sqlBuilder.append("vencargo.nama as vencargonama,vencargo.alias as vencargoalias ");
        sqlBuilder.append("from pelunasanhutang as data ");
        sqlBuilder.append("left join purchasereceive as pr on pr.id = data.idpurchasereceive ");
        sqlBuilder.append("left join m_vendor as venpr on venpr.id = pr.idvendor ");

        sqlBuilder.append("left join cargo as cargo on cargo.id = data.idcargo ");
        sqlBuilder.append("left join m_vendor as vencargo on vencargo.id = cargo.idvendor ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanHutangDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long idcargo = rs.getLong("idcargo");
        final Date date = rs.getDate("date");
        final Double amount = rs.getDouble("amount");
        final String nodocumentpr = rs.getString("nodocumentpr");
        final String venprnama = rs.getString("venprnama");
        final String venpralias = rs.getString("venpralias");
        final String invoicenumbercargo = rs.getString("invoicenumbercargo");
        final String vencargonama = rs.getString("vencargonama");
        final String vencargoalias = rs.getString("vencargoalias");

        PelunasanHutangDataList data = new PelunasanHutangDataList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdpurchasereceive(idpurchasereceive);
        data.setIdcargo(idcargo);
        data.setDate(date);
        data.setAmount(amount);
        data.setNodocumentPR(nodocumentpr);
        data.setNodocumentCargo(invoicenumbercargo);
        data.setNamavendorPR(venprnama);
        data.setAliasvendorPR(venpralias);
        data.setNamavendorCargo(vencargonama);
        data.setAliasvendorCargo(vencargoalias);

        return data;
    }
}
