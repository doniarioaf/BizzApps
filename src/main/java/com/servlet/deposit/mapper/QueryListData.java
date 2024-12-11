package com.servlet.deposit.mapper;

import com.servlet.deposit.entity.DepositList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryListData implements RowMapper<DepositList> {
    private String schemaSql;

    public QueryListData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.idvendor as idvendor, data.amount as amount, data.depositdate as depositdate, ");
        sqlBuilder.append("ven.nama as venNama, ven.alias as venAlias ");
        sqlBuilder.append("from deposit as data ");
        sqlBuilder.append("left join m_vendor as ven on data.idvendor = ven.id ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public DepositList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final Double amount = rs.getDouble("amount");
        final Date depositdate = rs.getDate("depositdate");

        DepositList data = new DepositList();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setVendorName(venNama);
        data.setVendorAlias(venAlias);
        data.setAmount(amount);
        data.setDepositdate(depositdate);
        return data;
    }
}
