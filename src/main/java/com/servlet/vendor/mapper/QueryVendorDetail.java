package com.servlet.vendor.mapper;

import com.servlet.vendor.entity.VendorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryVendorDetail implements RowMapper<VendorData> {
    private String schemaSql;

    public QueryVendorDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.alias as alias,data.type as type, ");
        sqlBuilder.append("data.bank as bank, data.accountnobank as accountnobank, data.accountnamebank as accountnamebank, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, data.deletedate as deletedate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama, userdelete.nama as deletenama ");
        sqlBuilder.append("from m_vendor as data ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");
        sqlBuilder.append("left join m_user_apps as userdelete on userdelete.id = data.deleteby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public VendorData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final String type = rs.getString("type");
        final String bank = rs.getString("bank");
        final String accountnobank = rs.getString("accountnobank");
        final String accountnamebank = rs.getString("accountnamebank");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final Timestamp deletedate = rs.getTimestamp("deletedate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final String deletenama = rs.getString("deletenama");

        VendorData data = new VendorData();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        data.setType(type);
        data.setBank(bank);
        data.setAccountnobank(accountnobank);
        data.setAccountnamebank(accountnamebank);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setDeletedate(deletedate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        data.setDeletebyName(deletenama);
        return data;
    }
}
