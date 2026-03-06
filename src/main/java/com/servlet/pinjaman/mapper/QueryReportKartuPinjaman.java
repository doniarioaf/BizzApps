package com.servlet.pinjaman.mapper;

import com.servlet.pinjaman.entity.ReportKartuPinjaman;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryReportKartuPinjaman implements RowMapper<ReportKartuPinjaman> {
    private String schemaSql;

    public QueryReportKartuPinjaman() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id,data.nodocument as nodocument, data.amount as amount, data.date as date, data.idvendor as idvendor, data.catatan as catatan, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias  ");
        sqlBuilder.append("from pinjaman as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportKartuPinjaman mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        final String nodocument = rs.getString("nodocument");
        final Double amount = rs.getDouble("amount");
        final Date date = rs.getDate("date");
        final String catatan = rs.getString("catatan");

        ReportKartuPinjaman data = new ReportKartuPinjaman();
        data.setId(id);
        data.setIdvendor(idvendor != null?idvendor:0L);
        //karena idvendor di deposit sudah idvendorparent, dipasang disini biar ga pusing
        data.setIdvendorParent(idvendor != null?idvendor:0L);
        data.setVendorName(vennama);
        data.setVendorAlias(venalias);
        data.setDocumentNumber(nodocument);
        data.setAmount(amount);
        data.setDate(date);
        data.setType("PINJAMAN");
        data.setCatatan(catatan);
        return data;
    }
}
