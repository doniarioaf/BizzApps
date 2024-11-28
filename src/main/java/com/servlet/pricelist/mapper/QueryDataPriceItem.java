package com.servlet.pricelist.mapper;

import com.servlet.pricelist.entity.PriceListItemData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataPriceItem implements RowMapper<PriceListItemData> {
    private String schemaSql;

    public QueryDataPriceItem() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.categoryproductid as categoryproductid, data.amount as amount, cp.nama as categoryproductnama ");
        sqlBuilder.append("from pricelistitem as data ");
        sqlBuilder.append("left join m_category_product as cp on cp.id = data.categoryproductid ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PriceListItemData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long categoryproductid = rs.getLong("categoryproductid");
        final Double amount = rs.getDouble("amount");
        final String categoryproductnama = rs.getString("categoryproductnama");

        PriceListItemData data = new PriceListItemData();
        data.setCategoryproductid(categoryproductid);
        data.setCategoryproductidName(categoryproductnama);
        data.setAmount(amount);
        return data;
    }
}
