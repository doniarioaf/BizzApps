package com.servlet.mappingstock.mapper;

import com.servlet.categoryproduct.entity.CategoryProductDetail;
import com.servlet.mappingstock.entity.MappingStockDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryDataDetail implements RowMapper<MappingStockDetail> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.categoryproductid as categoryproductid, data.categoryproductidmapping as categoryproductidmapping, ");
        sqlBuilder.append("cp.nama as namacp, cpmapping.nama as namacpmapping, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from mapping_stock as data ");
        sqlBuilder.append("left join m_category_product as cp on cp.id = data.categoryproductid ");
        sqlBuilder.append("left join m_category_product as cpmapping on cpmapping.id = data.categoryproductidmapping ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public MappingStockDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long categoryproductid = rs.getLong("categoryproductid");
        final Long categoryproductidmapping = rs.getLong("categoryproductidmapping");
        final String namacp = rs.getString("namacp");
        final String namacpmapping = rs.getString("namacpmapping");

        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");

        MappingStockDetail data = new MappingStockDetail();
        data.setCategoryproductid(categoryproductid);
        data.setCategoryproductnama(namacp);
        data.setCategoryproductidmapping(categoryproductidmapping);
        data.setCategoryproductmappingnama(namacpmapping);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
