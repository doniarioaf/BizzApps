package com.servlet.customer.mapper;

import com.servlet.customer.entity.CustomerData;
import com.servlet.customer.entity.ListCustomerData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryCustomerDetail implements RowMapper<CustomerData> {
    private String schemaSql;

    public QueryCustomerDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.alias as alias, data.address as address, ");
        sqlBuilder.append("data.bank as bank, data.banknumber as banknumber, data.accountbankname as accountbankname, ");
        sqlBuilder.append("data.grup as grup, data.grupcode as grupcode, data.phonenumber as phonenumber,data.attention as attention, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, data.deletedate as deletedate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama, userdelete.nama as deletenama ");
        sqlBuilder.append("from m_customer as data ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");
        sqlBuilder.append("left join m_user_apps as userdelete on userdelete.id = data.deleteby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CustomerData mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final String bank = rs.getString("bank");
        final String banknumber = rs.getString("banknumber");
        final String accountbankname = rs.getString("accountbankname");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final Timestamp deletedate = rs.getTimestamp("deletedate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final String deletenama = rs.getString("deletenama");
        final String address = rs.getString("address");
        final String grup = rs.getString("grup");
        final String grupcode = rs.getString("grupcode");
        final String phonenumber = rs.getString("phonenumber");
        final String attention = rs.getString("attention");

        CustomerData data = new CustomerData();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        data.setBank(bank);
        data.setBanknumber(banknumber);
        data.setAccountbankname(accountbankname);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setDeletedate(deletedate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        data.setDeletebyName(deletenama);
        data.setAddress(address);
        data.setGrup(grup);
        data.setGrupcode(grupcode);
        data.setPhonenumber(phonenumber);
        data.setAttention(attention);
        return data;
    }
}
