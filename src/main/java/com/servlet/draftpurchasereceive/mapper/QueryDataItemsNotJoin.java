package com.servlet.draftpurchasereceive.mapper;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveItemNotJoin;
import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveItemsDetailData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataItemsNotJoin implements RowMapper<DraftPurchaseReceiveItemNotJoin> {
    private String schemaSql;

    public QueryDataItemsNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, data.ekor as ekor, data.kilo as kilo, ");
        sqlBuilder.append("data.boxsequence as boxsequence, data.type as type ");
        sqlBuilder.append("from draft_purchasereceive_items as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public DraftPurchaseReceiveItemNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long ekor = rs.getLong("ekor");
        final Long kilo = rs.getLong("kilo");
        final Long boxsequence = rs.getLong("boxsequence");
        final String type = rs.getString("type");

        DraftPurchaseReceiveItemNotJoin data = new DraftPurchaseReceiveItemNotJoin();
        data.setBoxsequence(boxsequence);
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setEkor(ekor);
        data.setKilo(kilo);
        data.setType(type);
        return data;
    }
}
