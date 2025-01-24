package com.servlet.vendor.mapper;

import com.servlet.vendor.entity.VendorCategoryProductNotIncludeData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryVendorCategoryProductNotInclude implements RowMapper<VendorCategoryProductNotIncludeData> {
    private String schemaSql;

    public QueryVendorCategoryProductNotInclude() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idvendor as idvendor, data.idcategoryproduct as idcategoryproduct, ");
        sqlBuilder.append("cp.nama as cpNama, cp.size as cpsize ");
        sqlBuilder.append("from vendor_categoryproduct_not_include as data ");
        sqlBuilder.append("left join m_category_product as cp on cp.id = data.idcategoryproduct ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public VendorCategoryProductNotIncludeData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idvendor = rs.getLong("idvendor");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");
        final String cpNama = rs.getString("cpNama");
        final String cpsize = rs.getString("cpsize");
        VendorCategoryProductNotIncludeData data = new VendorCategoryProductNotIncludeData();
        data.setIdvendor(idvendor);
        data.setIdcategoryproduct(idcategoryproduct);
        data.setCategoryproductName(cpNama);
        data.setCategoryproductSize(cpsize);
        return data;
    }
}
