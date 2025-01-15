package com.servlet.invoice.mapper;

import com.servlet.stockitems.entity.ReportKartuStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryInvoiceReportKartuStock implements RowMapper<ReportKartuStock> {
    private String schemaSql;

    public QueryInvoiceReportKartuStock() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.nodocument as nodocument, data.date as date, ");
        sqlBuilder.append("pli.idproduct as idproduct, pli.idcategoryproduct as idcategoryproduct,pli.qty as qty ");
        sqlBuilder.append("from invoice as data ");
        sqlBuilder.append("left join packinglist_item as pli on pli.idpackinglist = data.idpackinglist ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportKartuStock mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");

        ReportKartuStock data = new ReportKartuStock();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setDate(date);
        data.setQty(qty);
        data.setNodocument(nodocument);
        data.setKeterangan("");
        data.setType("INVOICE");
        return data;
    }
}
