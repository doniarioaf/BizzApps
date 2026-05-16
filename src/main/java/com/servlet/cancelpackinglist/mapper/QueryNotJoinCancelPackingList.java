package com.servlet.cancelpackinglist.mapper;

import com.servlet.cancelpackinglist.entity.QueryNotJoinCancelPackingListData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryNotJoinCancelPackingList implements RowMapper<QueryNotJoinCancelPackingListData> {
    private String schemaSql;

    public QueryNotJoinCancelPackingList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.idpackinglist as idpackinglist, data.nodocument as nodocument, ");
        sqlBuilder.append("data.keterangan as keterangan, data.datecancel as datecancel ");
        sqlBuilder.append("from cancel_packinglist as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public QueryNotJoinCancelPackingListData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String nodocument = rs.getString("nodocument");
        final String keterangan = rs.getString("keterangan");
        final Date datecancel = rs.getDate("datecancel");

        QueryNotJoinCancelPackingListData data = new QueryNotJoinCancelPackingListData();
        data.setId(id);
        data.setIdpackinglist(idpackinglist);
        data.setNodocument(nodocument);
        data.setKeterangan(keterangan);
        data.setDatecancel(datecancel);
        return data;
    }
}
