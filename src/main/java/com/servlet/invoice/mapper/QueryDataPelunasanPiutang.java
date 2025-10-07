package com.servlet.invoice.mapper;

import com.servlet.invoice.entity.InvoiceDataList;
import com.servlet.invoice.entity.InvoiceDataPelunasanPiutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataPelunasanPiutang implements RowMapper<InvoiceDataPelunasanPiutang> {
    private String schemaSql;

    public QueryDataPelunasanPiutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.kurs as kurs, ");
        sqlBuilder.append("data.outstanding as outstanding, data.amount as amount, ");
        sqlBuilder.append("cust.nama as custNama, cust.alias as custAlias, pl.nodocument as nodocumentpl ");
        sqlBuilder.append("from invoice as data ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");
        sqlBuilder.append("left join m_customer as cust on cust.id = pl.idcustomer ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public InvoiceDataPelunasanPiutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Double kurs = rs.getDouble("kurs");
        final String custNama = rs.getString("custNama");
        final String custAlias = rs.getString("custAlias");
        final Double outstanding = rs.getDouble("outstanding");
        final Double amount = rs.getDouble("amount");
        final String nodocumentpl = rs.getString("nodocumentpl");

        InvoiceDataPelunasanPiutang data = new InvoiceDataPelunasanPiutang();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setKurs(kurs);
        data.setCustomerName(custNama);
        data.setCustomerAlias(custAlias);
        data.setOutstanding(outstanding > 1?outstanding:0.0);
        data.setAmount(amount);
        data.setNodocumentPL(nodocumentpl);
        return data;
    }
}
