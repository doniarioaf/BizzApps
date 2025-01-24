package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveInventoriNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryItemsInventoriNotJoin implements RowMapper<PurchaseReceiveInventoriNotJoin> {
    private String schemaSql;

    public QueryItemsInventoriNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idinventori as idinventori, data.qty as qty, ");
        sqlBuilder.append("data.price as price, data.subtotalprice as subtotalprice ");
        sqlBuilder.append("from purchasereceive_inventori as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveInventoriNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idinventori = rs.getLong("idinventori");
        final Long qty = rs.getLong("qty");
        final Double price = rs.getDouble("price");
        final Double subtotalprice = rs.getDouble("subtotalprice");
        PurchaseReceiveInventoriNotJoin data = new PurchaseReceiveInventoriNotJoin();
        data.setIdinventori(idinventori);
        data.setQty(qty);
        data.setPrice(price);
        data.setSubtotalprice(subtotalprice);
        return data;
    }
}
