package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveItemsNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryItemsNotJoin implements RowMapper<PurchaseReceiveItemsNotJoin> {
    private String schemaSql;

    public QueryItemsNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idcategoryproduct as idcategoryproduct, data.idproduct as idproduct, data.qty as qty, data.type as type ");
        sqlBuilder.append("from purchasereceive_item as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveItemsNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long idproduct = rs.getLong("idproduct");
        final Long qty = rs.getLong("qty");
        final String type = rs.getString("type");


        PurchaseReceiveItemsNotJoin data = new PurchaseReceiveItemsNotJoin();
        data.setIdcategoryproduct(idcategoryproduct);
        data.setIdproduct(idproduct);
        data.setQty(qty);
        data.setType(type);
        return data;
    }
}
