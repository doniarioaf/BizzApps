package com.servlet.cargo.mapper;

import com.servlet.cargo.entity.CargoPelunasanHutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCargoPelunasanHutang implements RowMapper<CargoPelunasanHutang> {
    private String schemaSql;

    public QueryCargoPelunasanHutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.idvendor as idvendor, data.date as date, data.invoicenumber as invoicenumber, ");
        sqlBuilder.append("data.smunumber as smunumber,data.awbnumber as awbnumber, data.koli as koli, data.grossamount as grossamount, ");
        sqlBuilder.append("data.ppnamount as ppnamount,data.ppn23amount as ppn23amount, data.netamount as netamount, data.outstanding as outstanding, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias ");
        sqlBuilder.append("from cargo as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CargoPelunasanHutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        final Date date = rs.getDate("date");
        final String invoicenumber = rs.getString("invoicenumber");
        final String smunumber = rs.getString("smunumber");
        final String awbnumber = rs.getString("awbnumber");
        final Long koli = rs.getLong("koli");
        final Double grossamount = rs.getDouble("grossamount");
        final Double ppnamount = rs.getDouble("ppnamount");
        final Double ppn23amount = rs.getDouble("ppn23amount");
        final Double netamount = rs.getDouble("netamount");
        final Double outstanding = rs.getDouble("outstanding");
        CargoPelunasanHutang data = new CargoPelunasanHutang();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setVendorName(vennama);
        data.setVendorAlias(venalias);
        data.setInvoicenumber(invoicenumber);
        data.setDate(date);
        data.setSmunumber(smunumber);
        data.setAwbnumber(awbnumber);
        data.setKoli(koli);
        data.setGrossamount(grossamount);
        data.setPpnamount(ppnamount);
        data.setPpn23amount(ppn23amount);
        data.setNetamount(netamount);
        data.setOutstanding(outstanding);
        return data;
    }
}
