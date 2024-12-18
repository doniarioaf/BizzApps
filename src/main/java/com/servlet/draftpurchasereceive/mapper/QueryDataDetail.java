package com.servlet.draftpurchasereceive.mapper;

import com.servlet.draftpurchasereceive.entity.DraftPurchaseReceiveDetailData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;

public class QueryDataDetail implements RowMapper<DraftPurchaseReceiveDetailData> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.idvendor as idvendor, ");
        sqlBuilder.append("data.arriveltime as arriveltime, data.receivetime as receivetime, data.totalekor as totalekor, data.totalkg as totalkg, data.persentase as persentase, ");
        sqlBuilder.append("data.smu as smu, ven.nama as venNama, ven.alias as venAlias, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from draft_purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public DraftPurchaseReceiveDetailData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Time arriveltime = rs.getTime("arriveltime");
        final Time receivetime = rs.getTime("receivetime");
        final Long totalekor = rs.getLong("totalekor");
        final Long totalkg = rs.getLong("totalkg");
        final Double persentase = rs.getDouble("persentase");
        final Long idvendor = rs.getLong("idvendor");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final String smu = rs.getString("smu");

        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");

        DraftPurchaseReceiveDetailData data = new DraftPurchaseReceiveDetailData();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setIdvendor(idvendor);
        data.setVendorName(venNama);
        data.setVendorAlias(venAlias);
        data.setArriveltime(arriveltime);
        data.setReceivetime(receivetime);
        data.setSmu(smu);
        data.setTotalekor(totalekor);
        data.setTotalkg(totalkg);
        data.setPersentase(persentase);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
