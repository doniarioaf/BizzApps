package com.servlet.packinglist.mapper;

import com.servlet.packinglist.entity.PackingListDataItemDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryItemDataDetail implements RowMapper<PackingListDataItemDetail> {
    private String schemaSql;

    public QueryItemDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, ");
        sqlBuilder.append("data.qty as qty,data.price as price, data.brutoweight as brutoweight ,data.totalprice as totalprice, ");
        sqlBuilder.append("data.allowance as allowance,data.nettoweight as nettoweight, data.box as box, ");
        sqlBuilder.append("prod.nama as prodnama, ");
        sqlBuilder.append("cprod.nama as cprodnama, cprod.size as cprodsize, cprod.weightfromingram as cprodweightfromingram, cprod.weighttoingram as cprodweighttoingram ");
        sqlBuilder.append("from packinglist_item as data ");
        sqlBuilder.append("left join m_product as prod on prod.id = data.idproduct ");
        sqlBuilder.append("left join m_category_product as cprod on cprod.id = data.idcategoryproduct ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PackingListDataItemDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");
        final Double price = rs.getDouble("price");
        final Double totalprice = rs.getDouble("totalprice");
        final Double brutoweight = rs.getDouble("brutoweight");
        final Double allowance = rs.getDouble("allowance");
        final Double nettoweight = rs.getDouble("nettoweight");
        final Long box = rs.getLong("box");
        final String prodnama = rs.getString("prodnama");
        final String cprodnama = rs.getString("cprodnama");
        final String cprodsize = rs.getString("cprodsize");
        final Long cprodweightfromingram = rs.getLong("cprodweightfromingram");
        final Long cprodweighttoingram = rs.getLong("cprodweighttoingram");
        PackingListDataItemDetail data = new PackingListDataItemDetail();
        data.setIdproduct(idproduct);
        data.setProductName(prodnama);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setCategoryProductName(cprodnama);
        data.setTotalprice(totalprice);
        data.setBrutoweight(brutoweight);
        data.setAllowance(allowance);
        data.setNettoweight(nettoweight);
        data.setBox(box);
        data.setQty(qty);
        data.setPrice(price);
        data.setCategoryProductName(cprodnama);
        data.setCategoryProductSize(cprodsize);
        data.setCategoryProductFromGr(cprodweightfromingram);
        data.setCategoryProductThruGr(cprodweighttoingram);
        return data;
    }
}
