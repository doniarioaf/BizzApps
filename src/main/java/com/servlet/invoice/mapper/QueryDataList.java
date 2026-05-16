package com.servlet.invoice.mapper;

import com.servlet.invoice.entity.InvoiceDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<InvoiceDataList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.kurs as kurs, ");
        sqlBuilder.append("data.idpackinglist as idpackinglist, pl.nodocument as nodocumentpl, pl.date as pldate, ");
        sqlBuilder.append("cust.nama as custNama, cust.alias as custAlias ");
        sqlBuilder.append("from invoice as data ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");
        sqlBuilder.append("left join m_customer as cust on cust.id = pl.idcustomer ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public InvoiceDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Date pldate = rs.getDate("pldate");
        final Double kurs = rs.getDouble("kurs");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String nodocumentpl = rs.getString("nodocumentpl");
        final String custNama = rs.getString("custNama");
        final String custAlias = rs.getString("custAlias");
        InvoiceDataList data = new InvoiceDataList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setPackingListdate(pldate);
        data.setKurs(kurs);
        data.setIdpackinglist(idpackinglist);
        data.setNodocumentPackingList(nodocumentpl);
        data.setCustNama(custNama);
        data.setCustAlias(custAlias);
        return data;
    }
}
