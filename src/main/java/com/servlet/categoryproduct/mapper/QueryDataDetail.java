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
        sqlBuilder.append("data.id as id, data.nama as nama, data.size as size,data.weightfromingram as weightfromingram, ");
        sqlBuilder.append("data.weighttoingram as weighttoingram, data.jumlahitemsperkoli as jumlahitemsperkoli, data.forcategory as forcategory, data.sequence as sequence, ");
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
        final Integer weightfromingram = rs.getInt("weightfromingram");
        final Integer weighttoingram = rs.getInt("weighttoingram");
        final Integer jumlahitemsperkoli = rs.getInt("jumlahitemsperkoli");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final String forcategory = rs.getString("forcategory");
        final Integer sequence = rs.getInt("sequence");



        CategoryProductDetail data = new CategoryProductDetail();
        data.setId(id);
        data.setNama(nama);
        data.setSize(size);
        data.setWeightfromingram(weightfromingram);
        data.setWeighttoingram(weighttoingram);
        data.setJumlahitemsperkoli(jumlahitemsperkoli);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        data.setForcategory(forcategory);
        data.setSequence(sequence);

        return data;
    }
}
