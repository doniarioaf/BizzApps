package com.servlet.deposit.mapper;

import com.servlet.deposit.entity.ReportKartuDeposit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryReportKartuDeposit implements RowMapper<ReportKartuDeposit> {
    private String schemaSql;

    public QueryReportKartuDeposit() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id,data.nodocument as nodocument, data.amount as amount, data.depositdate as depositdate, data.idvendor as idvendor ");
        sqlBuilder.append("from deposit as data ");
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
        final Double amount = rs.getDouble("amount");
        final Date depositdate = rs.getDate("depositdate");
        ReportKartuDeposit data = new ReportKartuDeposit();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setDocumentNumber(nodocument);
        data.setAmount(amount);
        data.setDate(depositdate);
        data.setType("DEPOSIT");
        return data;
    }
}
