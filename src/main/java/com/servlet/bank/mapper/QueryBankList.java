package com.servlet.bank.mapper;

import com.servlet.bank.entity.BankDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryBankList implements RowMapper<BankDataList> {
    private String schemaSql;

    public QueryBankList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.bankname as bankname, data.accname as accname ");
        sqlBuilder.append("from m_bank as data ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public BankDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String bankname = rs.getString("bankname");
        final String accname = rs.getString("accname");
        BankDataList data = new BankDataList();
        data.setId(id);
        data.setBankname(bankname);
        data.setAccname(accname);
        return data;
    }
}
