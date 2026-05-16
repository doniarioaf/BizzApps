package com.servlet.journal.mapper;

import com.servlet.journal.entity.SaldoJournal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySaldo implements RowMapper<SaldoJournal> {
    private String schemaSql;

    public QuerySaldo() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.accountcode as accountcode,data.idvendor as idvendor,  ");
        sqlBuilder.append("SUM(data.credit) - SUM(data.debit) AS saldo ");
        sqlBuilder.append("from journal_detail as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public SaldoJournal mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Double saldo = rs.getDouble("saldo");
        final String accountcode = rs.getString("accountcode");
        final Long idvendor = rs.getLong("idvendor");
        SaldoJournal data = new SaldoJournal();
        data.setIdvendor(idvendor);
        data.setAccountCode(accountcode);
        data.setSaldo(saldo);
        return data;
    }
}
