package com.servlet.draftpurchasereceive.mapper;

import com.servlet.stockitems.entity.ReportKartuStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDPRReportKartuStock implements RowMapper<ReportKartuStock> {
    private String schemaSql;

    public QueryDPRReportKartuStock() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idcategoryproduct as idcategoryproduct, data.idproduct as idproduct, sum(data.ekor) as qty, ");
        sqlBuilder.append("pr.nodocument as nodocument, pr.date as date, pr.notes1 as notes1, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias  ");
        sqlBuilder.append("from draft_purchasereceive_items as data ");
        sqlBuilder.append("left join draft_purchasereceive as pr on pr.id = data.iddraftpurchasereceive ");
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
        final Date transactiondate = rs.getDate("date");
        final String notes = rs.getString("notes1");
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
        data.setType("DPR");

        return data;
    }
}
