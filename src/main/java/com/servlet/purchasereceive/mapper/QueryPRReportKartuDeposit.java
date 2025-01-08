package com.servlet.purchasereceive.mapper;

import com.servlet.deposit.entity.ReportKartuDeposit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPRReportKartuDeposit implements RowMapper<ReportKartuDeposit> {
    private String schemaSql;

    public QueryPRReportKartuDeposit() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.transactiondate as transactiondate, data.setor as setor, data.idvendor as idvendor ");
        sqlBuilder.append("from purchasereceive as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportKartuDeposit mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final String nodocument = rs.getString("nodocument");
        final Date transactiondate = rs.getDate("transactiondate");
        final Double setor = rs.getDouble("setor");
        ReportKartuDeposit data = new ReportKartuDeposit();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setDocumentNumber(nodocument);
        data.setDate(transactiondate);
        data.setAmount(setor);
        data.setType("PURCHASERECEIVE");

        return data;
    }
}
