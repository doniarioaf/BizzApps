package com.servlet.invoice.mapper;

import com.servlet.invoice.entity.InvoiceDataDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryDataDetail implements RowMapper<InvoiceDataDetail> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.kurs as kurs, ");
        sqlBuilder.append("data.idpackinglist as idpackinglist, data.phone as phone, pl.date as pldate, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from invoice as data ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public InvoiceDataDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Date pldate = rs.getDate("pldate");

        final Double kurs = rs.getDouble("kurs");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String phone = rs.getString("phone");

        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        InvoiceDataDetail data = new InvoiceDataDetail();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setPackinglistdate(pldate);
        data.setKurs(kurs);
        data.setIdpackinglist(idpackinglist);
        data.setPhone(phone);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
