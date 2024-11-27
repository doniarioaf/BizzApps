package com.servlet.categoryproduct.mapper;

import com.servlet.categoryproduct.entity.CategoryProductDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryDataDetail implements RowMapper<CategoryProductDetail> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.size as size,data.weight as weight, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from m_category_product as data ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CategoryProductDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String size = rs.getString("size");
        final String weight = rs.getString("weight");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");

        CategoryProductDetail data = new CategoryProductDetail();
        data.setId(id);
        data.setNama(nama);
        data.setSize(size);
        data.setWeight(weight);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);

        return data;
    }
}
