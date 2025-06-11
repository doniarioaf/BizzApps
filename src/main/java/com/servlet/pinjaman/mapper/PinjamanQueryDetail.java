package com.servlet.pinjaman.mapper;

import com.servlet.pinjaman.entity.PinjamanDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PinjamanQueryDetail implements RowMapper<PinjamanDetail> {
    private String schemaSql;

    public PinjamanQueryDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id,data.nodocument as nodocument, data.idvendor as idvendor, data.amount as amount, data.date as date,data.isactive as isactive, ");
        sqlBuilder.append("ven.nama as venNama, ven.alias as venAlias, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from pinjaman as data ");
        sqlBuilder.append("left join m_vendor as ven on data.idvendor = ven.id ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PinjamanDetail mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final String nodocument = rs.getString("nodocument");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final Double amount = rs.getDouble("amount");
        final Date date = rs.getDate("date");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final Boolean isactive = rs.getBoolean("isactive");

        PinjamanDetail data = new PinjamanDetail();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setNodocument(nodocument);
        data.setVendorName(venNama);
        data.setVendorAlias(venAlias);
        data.setAmount(amount);
        data.setDate(date);
        data.setIsactive(isactive);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
