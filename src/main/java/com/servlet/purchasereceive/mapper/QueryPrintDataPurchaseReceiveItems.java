package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PrintDataPurchaseReceiveItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPrintDataPurchaseReceiveItems implements RowMapper<PrintDataPurchaseReceiveItems> {
    private String schemaSql;

    public QueryPrintDataPurchaseReceiveItems() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idpurchasereceive as idpurchasereceive, data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, ");
        sqlBuilder.append("data.qty as qty, data.qtybonus as qtybonus,data.price as price, data.subtotalprice as subtotalprice, data.type as type, ");
        sqlBuilder.append("prod.nama as prodnama, ");
        sqlBuilder.append("cprod.nama as cprodnama, cprod.size as cprodsize, cprod.weightfromingram as cprodweightfromingram, cprod.weighttoingram as cprodweighttoingram ");
        sqlBuilder.append("from purchasereceive_item as data ");
        sqlBuilder.append("left join m_product as prod on prod.id = data.idproduct ");
        sqlBuilder.append("left join m_category_product as cprod on cprod.id = data.idcategoryproduct ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PrintDataPurchaseReceiveItems mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");
        final Long qtybonus = rs.getLong("qtybonus");
        final Double price = rs.getDouble("price");
        final Double subtotalprice = rs.getDouble("subtotalprice");
        final String type = rs.getString("type");
        final String prodnama = rs.getString("prodnama");
        final String cprodnama = rs.getString("cprodnama");
        final String cprodsize = rs.getString("cprodsize");
        final Long cprodweightfromingram = rs.getLong("cprodweightfromingram");
        final Long cprodweighttoingram = rs.getLong("cprodweighttoingram");

        PrintDataPurchaseReceiveItems data = new PrintDataPurchaseReceiveItems();
        data.setIdpurchasereceive(idpurchasereceive);
        data.setIdproduct(idproduct);
        data.setProductName(prodnama);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setCategoryProductName(cprodnama);
        data.setSize(cprodsize);
        data.setWeightto(cprodweighttoingram);
        data.setWeightfrom(cprodweightfromingram);
        data.setType(type);
        data.setQty(qty);
        data.setQtybonus(qtybonus);
        data.setPrice(price);
        data.setSubtotalprice(subtotalprice);
        return data;
    }
}
