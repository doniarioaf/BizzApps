package com.servlet.filedocument.mapper;

import com.servlet.filedocument.entity.FileDocumentData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryData implements RowMapper<FileDocumentData> {
    private String schemaSql;

    public QueryData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.iddata as iddata, data.menu as menu, data.filename as filename, ");
        sqlBuilder.append("data.filedocument as filedocument, data.filecontenttype as filecontenttype ");
        sqlBuilder.append("from files_document as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public FileDocumentData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long iddata = rs.getLong("iddata");
        final String menu = rs.getString("menu");
        final String filename = rs.getString("filename");
        final String filedocument = rs.getString("filedocument");
        final String filecontenttype = rs.getString("filecontenttype");
        FileDocumentData data = new FileDocumentData();
        data.setId(id);
        data.setIddata(iddata);
        data.setMenu(menu);
        data.setFilename(filename);
        data.setFiledocument(filedocument);
        data.setFilecontenttype(filecontenttype);
        return data;
    }
}
