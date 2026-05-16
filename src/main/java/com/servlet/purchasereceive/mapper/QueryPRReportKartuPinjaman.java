package com.servlet.purchasereceive.mapper;

import com.servlet.deposit.entity.ReportKartuDeposit;
import com.servlet.pinjaman.entity.ReportKartuPinjaman;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPRReportKartuPinjaman implements RowMapper<ReportKartuPinjaman> {
    private String schemaSql;

    public QueryPRReportKartuPinjaman() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.transactiondate as transactiondate, data.setor_pinjaman as setor_pinjaman, data.idvendor as idvendor, ");
        sqlBuilder.append("ven.idvendorparent as idvendorparent, ven.nama as vennama, ven.alias as venalias  ");
        sqlBuilder.append("from purchasereceive as data ");
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
        final String nodocument = rs.getString("nodocument");
        final Date transactiondate = rs.getDate("transactiondate");
        final Double setor_pinjaman = rs.getDouble("setor_pinjaman");
        final Long idvendorparent = rs.getLong("idvendorparent");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");

        ReportKartuPinjaman data = new ReportKartuPinjaman();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setVendorName(vennama);
        data.setVendorAlias(venalias);
        data.setIdvendorParent(idvendorparent != null?idvendorparent:0L);
        data.setDocumentNumber(nodocument);
        data.setDate(transactiondate);
        data.setAmount(setor_pinjaman);

        data.setType("PURCHASERECEIVE");

        return data;
    }
}
