package com.servlet.deposit.mapper;

import com.servlet.deposit.entity.DepositDataNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataNotJoin implements RowMapper<DepositDataNotJoin> {
    private String schemaSql;

    public QueryDataNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.idvendor as idvendor, data.amount as amount, data.depositdate as depositdate ");
        sqlBuilder.append("from deposit as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public DepositDataNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final Double amount = rs.getDouble("amount");
        final Date depositdate = rs.getDate("depositdate");

        DepositDataNotJoin data = new DepositDataNotJoin();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setAmount(amount);
        data.setDepositdate(depositdate);
        return data;
    }
}
