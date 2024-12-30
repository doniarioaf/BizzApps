package com.servlet.pelunasanhutang.mapper;

import com.servlet.pelunasanhutang.entity.PelunasanHutangDataDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryPelunasanHutangDataDetail implements RowMapper<PelunasanHutangDataDetail> {

    private String schemaSql;

    public QueryPelunasanHutangDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idpurchasereceive as idpurchasereceive, data.idcargo as idcargo, ");
        sqlBuilder.append("data.date as date, data.amount as amount, data.notes as notes, ");
        sqlBuilder.append("pr.nodocument as nodocumentpr, ");
        sqlBuilder.append("venpr.nama as venprnama,venpr.alias as venpralias, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from pelunasanhutang as data ");
        sqlBuilder.append("left join purchasereceive as pr on pr.id = data.idpurchasereceive ");
        sqlBuilder.append("left join m_vendor as venpr on venpr.id = pr.idvendor ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanHutangDataDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long idcargo = rs.getLong("idcargo");
        final Date date = rs.getDate("date");
        final Double amount = rs.getDouble("amount");
        final String notes = rs.getString("notes");
        final String nodocumentpr = rs.getString("nodocumentpr");
        final String venprnama = rs.getString("venprnama");
        final String venpralias = rs.getString("venpralias");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        PelunasanHutangDataDetail data = new PelunasanHutangDataDetail();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdpurchasereceive(idpurchasereceive);
        data.setIdcargo(idcargo);
        data.setDate(date);
        data.setAmount(amount);
        data.setNotes(notes);
        data.setNodocumentPR(nodocumentpr);
        data.setNamavendorPR(venprnama);
        data.setAliasvendorPR(venpralias);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
