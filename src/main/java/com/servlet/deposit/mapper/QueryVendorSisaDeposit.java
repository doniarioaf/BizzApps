package com.servlet.deposit.mapper;

import com.servlet.chartofaccount.AccountCOAEnum;
import com.servlet.deposit.entity.VendorSisaDeposit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryVendorSisaDeposit implements RowMapper<VendorSisaDeposit> {
    private String schemaSql;

    public QueryVendorSisaDeposit() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("SELECT " +
                "    v.id, " +
                "    v.nama, " +
                "    v.alias, " +
                "    COALESCE(j.balance,0) AS sisaDeposit ");
        sqlBuilder.append(" from m_vendor as v ");
        sqlBuilder.append(" LEFT JOIN (SELECT jd.idvendor, SUM(jd.credit) - SUM(jd.debit) AS balance FROM journal_detail as jd WHERE jd.accountcode = '"+AccountCOAEnum.DEPOSITVENDOR_ASSET.getAccCode()+"' GROUP BY jd.idvendor ) as j ON v.id = j.idvendor  ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public VendorSisaDeposit mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final Double sisaDeposit = rs.getDouble("sisaDeposit");

        VendorSisaDeposit data = new VendorSisaDeposit();
        data.setIdvendor(id);
        data.setVendorName(nama);
        data.setVendorAlias(alias);
        data.setSisaDeposit(sisaDeposit);
        return data;
    }
}
