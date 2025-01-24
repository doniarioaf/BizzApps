package com.servlet.invoice.mapper;

import com.servlet.invoice.entity.InvoiceDataReportPelunasanPiutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryInvoiceReportPelunasanPiutang implements RowMapper<InvoiceDataReportPelunasanPiutang> {
    private String schemaSql;

    public QueryInvoiceReportPelunasanPiutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.kurs as kurs, ");
        sqlBuilder.append(" data.amount as amount, ");
        sqlBuilder.append("pl.idcustomer as idcustomer, pl.flightnumber as flightnumber,pl.awbnumber as awbnumber,pl.koli as koli, ");
        sqlBuilder.append(" cust.nama as custNama, cust.alias as custAlias, cust.grup as custGrup ");
        sqlBuilder.append("from invoice as data ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");
        sqlBuilder.append("left join m_customer as cust on cust.id = pl.idcustomer ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public InvoiceDataReportPelunasanPiutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idcustomer = rs.getLong("idcustomer");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Double kurs = rs.getDouble("kurs");
        final String custNama = rs.getString("custNama");
        final String custAlias = rs.getString("custAlias");
        final String custGrup = rs.getString("custGrup");
        final String flightnumber = rs.getString("custGrup");
        final String awbnumber = rs.getString("custGrup");
        final Long koli = rs.getLong("koli");
        final Double amount = rs.getDouble("amount");
        InvoiceDataReportPelunasanPiutang data = new InvoiceDataReportPelunasanPiutang();
        data.setId(id);
        data.setIdcustomer(idcustomer);
        data.setCustomerName(custNama);
        data.setCustomerAlias(custAlias);
        data.setCustomerGrup(custGrup);
        data.setDate(date);
        data.setNoDocument(nodocument);
        data.setFlightnumber(flightnumber);
        data.setAwb(awbnumber);
        data.setKoli(koli);
        data.setInvoiceAmount(amount);
        data.setKurs(kurs);
        return data;
    }
}
