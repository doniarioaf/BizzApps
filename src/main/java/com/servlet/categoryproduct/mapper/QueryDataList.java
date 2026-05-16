package com.servlet.categoryproduct.mapper;

import com.servlet.categoryproduct.entity.CategoryProductList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<CategoryProductList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.size as size, data.weightfromingram as weightfromingram, data.weighttoingram as weighttoingram, data.jumlahitemsperkoli as jumlahitemsperkoli, data.forcategory as forcategory ");
        sqlBuilder.append("from m_category_product as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CategoryProductList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String size = rs.getString("size");
        final Integer weightfromingram = rs.getInt("weightfromingram");
        final Integer weighttoingram = rs.getInt("weighttoingram");
        final Integer jumlahitemsperkoli = rs.getInt("jumlahitemsperkoli");
        final String forcategory = rs.getString("forcategory");


        CategoryProductList data = new CategoryProductList();
        data.setId(id);
        data.setNama(nama);
        data.setSize(size);
        data.setWeightfromingram(weightfromingram);
        data.setWeighttoingram(weighttoingram);
        data.setJumlahitemsperkoli(jumlahitemsperkoli);
        data.setForcategory(forcategory);
        return data;
    }
}
