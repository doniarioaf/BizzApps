package com.servlet.draftpurchasereceive.mapper;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<DraftPurchaseReceiveList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.idvendor as idvendor, ");
        sqlBuilder.append("data.smu as smu, ven.nama as venNama, ven.alias as venAlias ");
        sqlBuilder.append("from draft_purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public DraftPurchaseReceiveList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Long idvendor = rs.getLong("idvendor");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final String smu = rs.getString("smu");
        DraftPurchaseReceiveList data = new DraftPurchaseReceiveList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setIdvendor(idvendor);
        data.setVendorName(venNama);
        data.setVendorAlias(venAlias);
        data.setSmu(smu);
        return data;
    }
}
