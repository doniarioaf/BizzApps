package com.servlet.inventori.mapper;

import com.servlet.inventori.entity.InventoriDataDetail;
import com.servlet.inventori.entity.ListInventoriData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryInventoriDetail implements RowMapper<InventoriDataDetail> {
    private String schemaSql;

    public QueryInventoriDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.sku as sku, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, data.deletedate as deletedate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama, userdelete.nama as deletenama ");
        sqlBuilder.append("from m_inventori as data ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");
        sqlBuilder.append("left join m_user_apps as userdelete on userdelete.id = data.deleteby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public InventoriDataDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String sku = rs.getString("sku");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final Timestamp deletedate = rs.getTimestamp("deletedate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final String deletenama = rs.getString("deletenama");

        InventoriDataDetail data = new InventoriDataDetail();
        data.setId(id);
        data.setNama(nama);
        data.setSku(sku);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setDeletedate(deletedate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        data.setDeletebyName(deletenama);
        return data;
    }
}
