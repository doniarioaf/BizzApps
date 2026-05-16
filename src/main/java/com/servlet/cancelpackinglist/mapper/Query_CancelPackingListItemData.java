package com.servlet.cancelpackinglist.mapper;

import com.servlet.cancelpackinglist.entity.CancelPackingListItemData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query_CancelPackingListItemData implements RowMapper<CancelPackingListItemData> {
    private String schemaSql;

    public Query_CancelPackingListItemData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idproduct as idproduct, data.idcategoryproduct as idcategoryproduct, data.qty as qty, ");
        sqlBuilder.append("prod.nama as prodnama, cprod.nama as cprodnama, cprod.size as cprodsize ");
        sqlBuilder.append("from cancel_packinglistitems as data ");
        sqlBuilder.append("left join m_product as prod on prod.id = data.idproduct ");
        sqlBuilder.append("left join m_category_product as cprod on cprod.id = data.idcategoryproduct ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CancelPackingListItemData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final Long qty = rs.getLong("qty");
        final String prodnama = rs.getString("prodnama");
        final String cprodnama = rs.getString("cprodnama");
        final String cprodsize = rs.getString("cprodsize");
        CancelPackingListItemData data = new CancelPackingListItemData();
        data.setIdproduct(idproduct);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setQty(qty);
        data.setNamaProduct(prodnama);
        data.setCategoryProductNama(cprodnama);
        data.setCategoryProductSize(cprodsize);
        return data;
    }
}
