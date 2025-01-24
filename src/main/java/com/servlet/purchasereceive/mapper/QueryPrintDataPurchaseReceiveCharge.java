package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PrintDataPurchaseReceiveCharge;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPrintDataPurchaseReceiveCharge implements RowMapper<PrintDataPurchaseReceiveCharge> {
    private String schemaSql;

    public QueryPrintDataPurchaseReceiveCharge() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idpurchasereceive as idpurchasereceive, data.idcharge as idcharge, ");
        sqlBuilder.append("data.qty as qty, data.price as price, data.subtotalprice as subtotalprice, ");
        sqlBuilder.append("charge.nama as chargenama ");
        sqlBuilder.append("from purchasereceive_charge as data ");
        sqlBuilder.append("left join m_charge as charge on charge.id = data.idcharge ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PrintDataPurchaseReceiveCharge mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long idcharge = rs.getLong("idcharge");
        final Long qty = rs.getLong("qty");
        final Double price = rs.getDouble("price");
        final Double subtotalprice = rs.getDouble("subtotalprice");
        final String chargenama = rs.getString("chargenama");
        PrintDataPurchaseReceiveCharge data = new PrintDataPurchaseReceiveCharge();
        data.setIdpurchasereceive(idpurchasereceive);
        data.setIdcharge(idcharge);
        data.setChargename(chargenama);
        data.setQty(qty);
        data.setPrice(price);
        data.setSubtotalprice(subtotalprice);
        return data;
    }
}
