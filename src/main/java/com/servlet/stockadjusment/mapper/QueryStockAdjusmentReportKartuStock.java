package com.servlet.stockadjusment.mapper;

import com.servlet.stockitems.entity.ReportKartuStock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryStockAdjusmentReportKartuStock implements RowMapper<ReportKartuStock> {
    private String schemaSql;

    public QueryStockAdjusmentReportKartuStock() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, ");
        sqlBuilder.append("sum(data.qty) as qty, data.type as type, sa.nodocument as nodocument, sa.date as date, sa.note as note ");
        sqlBuilder.append("from stock_adjusment_item as data ");
        sqlBuilder.append("left join stock_adjusment as sa on data.idstockadjusment = sa.id ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }


    @Override
    public ReportKartuStock mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");
        final String type = rs.getString("type");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final String note = rs.getString("note");
        ReportKartuStock data = new ReportKartuStock();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setDate(date);
        data.setQty(qty);
        data.setNodocument(nodocument);
        data.setKeterangan(note);
        String typeSA = "SA_H";
        if(type.equals("M")){
            typeSA = "SA_M";
        }
        data.setType(typeSA);
        return data;
    }
}
