package com.servlet.stockadjusment.mapper;

import com.servlet.stockadjusment.entity.StockAdjsumentDataDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryDataDetail implements RowMapper<StockAdjsumentDataDetail> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.pricedate as pricedate,data.note as note, data.type as type, data.idpricelist as idpricelist, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from stock_adjusment as data ");
        sqlBuilder.append("left join pricelist as pl on pl.id = data.idpricelist ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public StockAdjsumentDataDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Date pricedate = rs.getDate("pricedate");
        final String note = rs.getString("note");
        final String type = rs.getString("type");
        final Long idpricelist = rs.getLong("idpricelist");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");

        StockAdjsumentDataDetail data = new StockAdjsumentDataDetail();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setPricedate(pricedate);
        data.setNote(note);
        data.setType(type);
        data.setIdpricelist(idpricelist);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
