package com.servlet.customer.mapper;

import com.servlet.customer.entity.ListCustomerData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCustomerList implements RowMapper<ListCustomerData> {
    private String schemaSql;

    public QueryCustomerList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.alias as alias, ");
        sqlBuilder.append("data.grup as grup, data.grupcode as grupcode ");
        sqlBuilder.append("from m_customer as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ListCustomerData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final String grup = rs.getString("grup");
        final String grupcode = rs.getString("grupcode");
        ListCustomerData data = new ListCustomerData();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        data.setGrup(grup);
        data.setGrupcode(grupcode);
        return data;
    }
}
