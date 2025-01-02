package com.servlet.pelunasanpiutang.mapper;

import com.servlet.pelunasanpiutang.entity.PelunasanPiutangList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPelunasanPiutangList implements RowMapper<PelunasanPiutangList> {
    private String schemaSql;

    public QueryPelunasanPiutangList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.kurs as kurs, ");
        sqlBuilder.append("inv.id as invid, inv.nodocument as invnodocument, inv.kurs as invkurs, inv.amount as invamount , ");
        sqlBuilder.append("cust.nama as custNama, cust.alias as custAlias ");
        sqlBuilder.append("from pelunasanpiutang as data ");
        sqlBuilder.append("left join (select item.idpelunasanpiutang, item.idinvoice FROM pelunasanpiutang_item as item ORDER BY item.idinvoice desc LIMIT 1) as items on items.idpelunasanpiutang = data.id ");
        sqlBuilder.append("left join invoice as inv on inv.id = items.idinvoice ");
        sqlBuilder.append("left join packinglist as pl on pl.id = inv.idpackinglist ");
        sqlBuilder.append("left join m_customer as cust on cust.id = pl.idcustomer ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PelunasanPiutangList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Double kurs = rs.getDouble("kurs");

        final Long invid = rs.getLong("invid");
        final String invnodocument = rs.getString("invnodocument");
        final Double invkurs = rs.getDouble("invkurs");
        final Double invamount = rs.getDouble("invamount");
        final String custNama = rs.getString("custNama");
        final String custAlias = rs.getString("custAlias");
        PelunasanPiutangList data = new PelunasanPiutangList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setKurs(kurs);

        data.setIdinvoice(invid);
        data.setNodocumentInvoice(invnodocument);
        data.setKursInvoice(invkurs);
        data.setAmountInvoice(invamount);
        data.setCustomerName(custNama);
        data.setCustomerAlias(custAlias);
        return data;
    }
}
