package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PrintDataPurchaseReceiveInventori;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPrintDataPurchaseReceiveInventori implements RowMapper<PrintDataPurchaseReceiveInventori> {
    private String schemaSql;

    public QueryPrintDataPurchaseReceiveInventori() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idpurchasereceive as idpurchasereceive, data.idinventori as idinventori, ");
        sqlBuilder.append("data.qty as qty, data.price as price, data.subtotalprice as subtotalprice, ");
        sqlBuilder.append("inventori.nama as inventorinama ");
        sqlBuilder.append("from purchasereceive_inventori as data ");
        sqlBuilder.append("left join m_inventori as inventori on inventori.id = data.idinventori ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PrintDataPurchaseReceiveInventori mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long idinventori = rs.getLong("idinventori");
        final Long qty = rs.getLong("qty");
        final Double price = rs.getDouble("price");
        final Double subtotalprice = rs.getDouble("subtotalprice");
        final String inventorinama = rs.getString("inventorinama");
        PrintDataPurchaseReceiveInventori data = new PrintDataPurchaseReceiveInventori();
        data.setIdpurchasereceive(idpurchasereceive);
        data.setIdinventori(idinventori);
        data.setInventoriname(inventorinama);
        data.setQty(qty);
        data.setPrice(price);
        data.setSubtotalprice(subtotalprice);
        return data;
    }
}
