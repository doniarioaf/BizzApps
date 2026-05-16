package com.servlet.pinjaman.mapper;

import com.servlet.chartofaccount.AccountCOAEnum;
import com.servlet.pinjaman.entity.VendorSisaPinjaman;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryVendorSisaPinjaman implements RowMapper<VendorSisaPinjaman> {
    private String schemaSql;

    public QueryVendorSisaPinjaman() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("SELECT " +
                "    v.id, " +
                "    v.nama, " +
                "    v.alias, " +
                "    COALESCE(j.balance,0) AS sisaPinjaman ");
        sqlBuilder.append(" from m_vendor as v ");
        sqlBuilder.append(" LEFT JOIN (SELECT jd.idvendor, SUM(jd.credit) - SUM(jd.debit) AS balance FROM journal_detail as jd WHERE jd.accountcode = '"+ AccountCOAEnum.PINJAMANVENDOR_LIABILITY.getAccCode()+"' GROUP BY jd.idvendor ) as j ON v.id = j.idvendor  ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public VendorSisaPinjaman mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final Double sisaPinjaman = rs.getDouble("sisaPinjaman");

        VendorSisaPinjaman data = new VendorSisaPinjaman();
        data.setIdvendor(id);
        data.setVendorName(nama);
        data.setVendorAlias(alias);
        data.setSisaPinjaman(sisaPinjaman);
        return data;
    }
}
