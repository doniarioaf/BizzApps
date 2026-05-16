package com.servlet.purchasereceive.mapper;

import com.servlet.stockitems.entity.ReportKartuStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPRReportKartuStock implements RowMapper<ReportKartuStock> {
    private String schemaSql;

    public QueryPRReportKartuStock() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idcategoryproduct as idcategoryproduct, data.idproduct as idproduct, data.qty as qty, ");
        sqlBuilder.append("pr.nodocument as nodocument, pr.transactiondate as transactiondate, pr.notes as notes, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias  ");
        sqlBuilder.append("from purchasereceive_item as data ");
        sqlBuilder.append("left join purchasereceive as pr on pr.id = data.idpurchasereceive ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = pr.idvendor ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportKartuStock mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long idproduct = rs.getLong("idproduct");
        final Long qty = rs.getLong("qty");
        final String nodocument = rs.getString("nodocument");
        final Date transactiondate = rs.getDate("transactiondate");
        final String notes = rs.getString("notes");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        ReportKartuStock data = new ReportKartuStock();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setDate(transactiondate);
        data.setQty(qty);
        data.setNodocument(nodocument);
        data.setKeterangan(notes);
        data.setVendorName(vennama);
        data.setVendorAlias(venalias);
        data.setType("PR");

        return data;
    }
}
