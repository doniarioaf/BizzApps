package com.servlet.filedocument.mapper;

import com.servlet.filedocument.entity.FileDocumentDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryFileDocumentList implements RowMapper<FileDocumentDataList> {
    private String schemaSql;

    public QueryFileDocumentList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.iddata as iddata, data.menu as menu, data.filename as filename ");
        sqlBuilder.append("from files_document as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public FileDocumentDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long iddata = rs.getLong("iddata");
        final String menu = rs.getString("menu");
        final String filename = rs.getString("filename");
        FileDocumentDataList data = new FileDocumentDataList();
        data.setId(id);
        data.setIddata(iddata);
        data.setMenu(menu);
        data.setFilename(filename);
        return data;
    }
}
