package com.servlet.purchasereceive.mapper;

import com.servlet.pelunasanhutang.entity.ReportPelunasanHutangDocumentHutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPurchaseReceiveReportHutang implements RowMapper<ReportPelunasanHutangDocumentHutang> {
    private String schemaSql;

    public QueryPurchaseReceiveReportHutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.transactiondate as date, data.nodocument as nodocument, ");
        sqlBuilder.append("data.totalprice as totalprice, data.setor as setor ");
        sqlBuilder.append("from purchasereceive as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportPelunasanHutangDocumentHutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Date date = rs.getDate("date");
        final String nodocument = rs.getString("nodocument");
        final Double totalprice = rs.getDouble("totalprice");
        final Double setor = rs.getDouble("setor");
        ReportPelunasanHutangDocumentHutang data = new ReportPelunasanHutangDocumentHutang();
        data.setIddoc(id);
        data.setDate(date);
        data.setNodocument(nodocument);
        data.setAmountInvoice(totalprice.doubleValue() - setor.doubleValue());
        data.setDocType("PURCHASERECEIVE");
        return data;
    }
}
