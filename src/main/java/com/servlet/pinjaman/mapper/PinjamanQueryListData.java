package com.servlet.pinjaman.mapper;

import com.servlet.deposit.entity.DepositList;
import com.servlet.pinjaman.entity.PinjamanList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PinjamanQueryListData implements RowMapper<PinjamanList> {
    private String schemaSql;

    public PinjamanQueryListData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id,data.nodocument as nodocument, data.idvendor as idvendor, data.amount as amount, data.date as date, data.isactive as isactive, ");
        sqlBuilder.append("ven.nama as venNama, ven.alias as venAlias ");
        sqlBuilder.append("from pinjaman as data ");
        sqlBuilder.append("left join m_vendor as ven on data.idvendor = ven.id ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PinjamanList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final Double amount = rs.getDouble("amount");
        final Date date = rs.getDate("date");
        final String nodocument = rs.getString("nodocument");
        final Boolean isactive = rs.getBoolean("isactive");

        PinjamanList data = new PinjamanList();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setVendorName(venNama);
        data.setVendorAlias(venAlias);
        data.setAmount(amount);
        data.setDate(date);
        data.setNodocument(nodocument);
        data.setIsactive(isactive);
        return data;
    }
}
