package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveGetPrice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPurchaseReceiveGetPrice implements RowMapper<PurchaseReceiveGetPrice> {
    private String schemaSql;

    public QueryPurchaseReceiveGetPrice() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.transactiondate as transactiondate, ");
        sqlBuilder.append("item.idproduct as idproduct, item.idcategoryproduct as idcategoryproduct, item.qty as qty, item.price as price ");
        sqlBuilder.append("from purchasereceive as data ");
        sqlBuilder.append("left join purchasereceive_item as item on data.id = item.idpurchasereceive ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveGetPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date transactiondate = rs.getDate("transactiondate");
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");
        final Double price = rs.getDouble("price");
        PurchaseReceiveGetPrice data = new PurchaseReceiveGetPrice();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setTransactiondate(transactiondate);
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setQty(qty);
        data.setPrice(price);

        return data;
    }
}
