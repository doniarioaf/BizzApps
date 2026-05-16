package com.servlet.cancelpackinglist.mapper;

import com.servlet.stockitems.entity.ReportKartuStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryCancelPackingListReportKartuStock implements RowMapper<ReportKartuStock> {
    private String schemaSql;

    public QueryCancelPackingListReportKartuStock() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("cpl.idpackinglist as idpackinglist, data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, cpl.keterangan as keterangan, ");
        sqlBuilder.append("sum(data.qty) as qty, cpl.nodocument as nodocument, cpl.datecancel as date, ");
        sqlBuilder.append("cus.nama as cusnama, cus.alias as cusalias ");
        sqlBuilder.append("from cancel_packinglistitems as data ");
        sqlBuilder.append("left join cancel_packinglist as cpl on cpl.id = data.idcancelpackinglist ");
        sqlBuilder.append("left join packinglist as pl on pl.id = cpl.idpackinglist ");
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
        final String keterangan = rs.getString("keterangan");


        ReportKartuStock data = new ReportKartuStock();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setDate(date);
        data.setQty(qty);
        data.setNodocument(nodocument);
        data.setKeterangan(keterangan);
        data.setCustomerName(cusnama);
        data.setCustomerAlias(cusalias);
        data.setType("CANCELPACKINGLIST");
        data.setIdpackinglist(idpackinglist);
        return data;
    }
}
