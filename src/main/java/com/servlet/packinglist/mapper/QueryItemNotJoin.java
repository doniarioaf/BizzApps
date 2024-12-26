package com.servlet.packinglist.mapper;

import com.servlet.packinglist.entity.PackingListDataItemDetail;
import com.servlet.packinglist.entity.PackingListItemData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryItemNotJoin implements RowMapper<PackingListItemData> {
    private String schemaSql;

    public QueryItemNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, ");
        sqlBuilder.append("data.qty as qty,data.price as price, data.brutoweight as brutoweight ,data.totalprice as totalprice, ");
        sqlBuilder.append("data.allowance as allowance,data.nettoweight as nettoweight, data.box as box ");
        sqlBuilder.append("from packinglist_item as data ");
        this.schemaSql = sqlBuilder.toString();
    }
    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PackingListItemData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");
        final Double price = rs.getDouble("price");
        final Double totalprice = rs.getDouble("totalprice");
        final Double brutoweight = rs.getDouble("brutoweight");
        final Double allowance = rs.getDouble("allowance");
        final Double nettoweight = rs.getDouble("nettoweight");
        final String box = rs.getString("box");
        PackingListItemData data = new PackingListItemData();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setTotalprice(totalprice);
        data.setBrutoweight(brutoweight);
        data.setAllowance(allowance);
        data.setNettoweight(nettoweight);
        data.setBox(box);
        data.setQty(qty);
        data.setPrice(price);
        return data;
    }
}
