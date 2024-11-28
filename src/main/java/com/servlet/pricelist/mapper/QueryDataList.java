package com.servlet.pricelist.mapper;

import com.servlet.pricelist.entity.PriceListData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<PriceListData> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.pricedate as pricedate ");
        sqlBuilder.append("from pricelist as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }
    @Override
    public PriceListData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Date pricedate = rs.getDate("pricedate");
        PriceListData data = new PriceListData();
        data.setId(id);
        data.setPricedate(pricedate);

        return data;
    }
}
