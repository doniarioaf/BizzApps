package com.servlet.mappingstock.mapper;

import com.servlet.customer.entity.ListCustomerData;
import com.servlet.mappingstock.entity.MappingStockList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<MappingStockList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.categoryproductid as categoryproductid, data.categoryproductidmapping as categoryproductidmapping, ");
        sqlBuilder.append("cp.nama as namacp, cpmapping.nama as namacpmapping ");
        sqlBuilder.append("from mapping_stock as data ");
        sqlBuilder.append("left join m_category_product as cp on cp.id = data.categoryproductid ");
        sqlBuilder.append("left join m_category_product as cpmapping on cpmapping.id = data.categoryproductidmapping ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public MappingStockList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long categoryproductid = rs.getLong("categoryproductid");
        final Long categoryproductidmapping = rs.getLong("categoryproductidmapping");
        final String namacp = rs.getString("namacp");
        final String namacpmapping = rs.getString("namacpmapping");

        MappingStockList data = new MappingStockList();
        data.setCategoryproductid(categoryproductid);
        data.setCategoryproductnama(namacp);
        data.setCategoryproductidmapping(categoryproductidmapping);
        data.setCategoryproductmappingnama(namacpmapping);
        return data;
    }
}
