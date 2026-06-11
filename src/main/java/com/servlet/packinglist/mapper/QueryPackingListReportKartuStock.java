package com.servlet.packinglist.mapper;

import com.servlet.stockitems.entity.ReportKartuStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPackingListReportKartuStock implements RowMapper<ReportKartuStock> {
    private String schemaSql;

    public QueryPackingListReportKartuStock() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idpackinglist as idpackinglist, data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, ");
        sqlBuilder.append("sum(data.qty) as qty, pl.nodocument as nodocument, pl.date_stock as date, ");
        sqlBuilder.append("cus.nama as cusnama, cus.alias as cusalias ");
        sqlBuilder.append("from packinglist_item as data ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");
        sqlBuilder.append("left join m_customer as cus on cus.id = pl.idcustomer ");
        this.schemaSql = sqlBuilder.toString();
    }
    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportKartuStock mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final String cusnama = rs.getString("cusnama");
        final String cusalias = rs.getString("cusalias");

        ReportKartuStock data = new ReportKartuStock();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setDate(date);
        data.setQty(qty);
        data.setNodocument(nodocument);
        data.setKeterangan("");
        data.setCustomerName(cusnama);
        data.setCustomerAlias(cusalias);
        data.setType("PACKINGLIST");
        data.setIdpackinglist(idpackinglist);
        return data;
    }
}
