package com.servlet.draftpurchasereceive.mapper;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveItemsDetailData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataItemsDetail implements RowMapper<DraftPurchaseReceiveItemsDetailData> {
    private String schemaSql;

    public QueryDataItemsDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, data.ekor as ekor, data.kilo as kilo, ");
        sqlBuilder.append("data.boxsequence as boxsequence, data.type as type, ");
        sqlBuilder.append("cp.nama as cpnama, cp.size as cpsize, cp.weightfromingram as cpweightfromingram, cp.weighttoingram as cpweighttoingram ");
        sqlBuilder.append("from draft_purchasereceive_items as data ");
        sqlBuilder.append("left join m_category_product as cp on cp.id = data.idcategoryproduct ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public DraftPurchaseReceiveItemsDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long ekor = rs.getLong("ekor");
        final Double kilo = rs.getDouble("kilo");
        final Long boxsequence = rs.getLong("boxsequence");
        final String type = rs.getString("type");
        final String cpnama = rs.getString("cpnama");
        final String cpsize = rs.getString("cpsize");
        final Long cpweightfromingram = rs.getLong("cpweightfromingram");
        final Long cpweighttoingram = rs.getLong("cpweighttoingram");

        DraftPurchaseReceiveItemsDetailData data = new DraftPurchaseReceiveItemsDetailData();
        data.setBoxsequence(boxsequence);
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setNamacategoryproduct(cpnama);
        data.setSizecategoryproduct(cpsize);
        data.setWeightfromingramcategoryproduct(cpweightfromingram);
        data.setWeighttoingramcategoryproduct(cpweighttoingram);
        data.setEkor(ekor);
        data.setKilo(kilo);
        data.setType(type);
        return data;
    }
}
