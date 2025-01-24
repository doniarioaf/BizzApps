package com.servlet.stockadjusment.mapper;

import com.servlet.stockadjusment.entity.StockAdjsumentDataItemNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataItemsNotJoin implements RowMapper<StockAdjsumentDataItemNotJoin> {
    private String schemaSql;

    public QueryDataItemsNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idcategoryproduct as idcategoryproduct, data.idproduct as idproduct, data.qty as qty, data.type as type, ");
        sqlBuilder.append("data.price as price, data.subtotalprice as subtotalprice ");
        sqlBuilder.append("from stock_adjusment_item as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public StockAdjsumentDataItemNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long idproduct = rs.getLong("idproduct");
        final Long qty = rs.getLong("qty");
        final String type = rs.getString("type");
        final Double price = rs.getDouble("price");
        final Double subtotalprice = rs.getDouble("subtotalprice");
        StockAdjsumentDataItemNotJoin data = new StockAdjsumentDataItemNotJoin();
        data.setIdcategoryproduct(idcategoryproduct);
        data.setIdproduct(idproduct);
        data.setQty(qty);
        data.setType(type);
        data.setPrice(price);
        data.setSubtotalprice(subtotalprice);
        return data;
    }
}
