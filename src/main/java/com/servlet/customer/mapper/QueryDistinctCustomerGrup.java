package com.servlet.customer.mapper;

import com.servlet.customer.entity.CustomerGrup;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDistinctCustomerGrup implements RowMapper<CustomerGrup> {
    private String schemaSql;

    public QueryDistinctCustomerGrup() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("distinct(data.grupcode) as grupcode, data.grup as grup ");
        sqlBuilder.append("from m_customer as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CustomerGrup mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String grup = rs.getString("grup");
        final String grupcode = rs.getString("grupcode");
        CustomerGrup data = new CustomerGrup();
        data.setGrup(grup);
        data.setGrupcode(grupcode);
        return data;
    }
}
