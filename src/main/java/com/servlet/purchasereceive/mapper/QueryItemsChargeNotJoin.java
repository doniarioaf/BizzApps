package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveChargeNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryItemsChargeNotJoin implements RowMapper<PurchaseReceiveChargeNotJoin> {
    private String schemaSql;

    public QueryItemsChargeNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idcharge as idcharge, data.qty as qty, ");
        sqlBuilder.append("data.price as price, data.subtotalprice as subtotalprice ");
        sqlBuilder.append("from purchasereceive_charge as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveChargeNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idcharge = rs.getLong("idcharge");
        final Long qty = rs.getLong("qty");
        final Double price = rs.getDouble("price");
        final Double subtotalprice = rs.getDouble("subtotalprice");
        PurchaseReceiveChargeNotJoin data = new PurchaseReceiveChargeNotJoin();
        data.setIdcharge(idcharge);
        data.setQty(qty);
        data.setPrice(price);
        data.setSubtotalprice(subtotalprice);
        return data;
    }
}
