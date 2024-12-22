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
        sqlBuilder.append("data.categoryproductid as categoryproductid, data.amount as amount, data.allowance as allowance, cp.nama as categoryproductnama, ");
        sqlBuilder.append("data.idproduct as idproduct, mp.nama as productnama ");
        sqlBuilder.append("from pricelistitem as data ");
        sqlBuilder.append("left join m_category_product as cp on cp.id = data.categoryproductid ");
        sqlBuilder.append("left join m_product as mp on mp.id = data.idproduct ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PriceListItemData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long categoryproductid = rs.getLong("categoryproductid");
        final Long idproduct = rs.getLong("idproduct");
        final String productnama = rs.getString("productnama");
        final Double amount = rs.getDouble("amount");
        final Double allowance = rs.getDouble("allowance");
        final String categoryproductnama = rs.getString("categoryproductnama");

        PriceListItemData data = new PriceListItemData();
        data.setIdproduct(idproduct);
        data.setProductName(productnama);
        data.setCategoryproductid(categoryproductid);
        data.setCategoryproductidName(categoryproductnama);
        data.setAmount(amount);
        data.setAllowance(allowance);
        return data;
    }
}
