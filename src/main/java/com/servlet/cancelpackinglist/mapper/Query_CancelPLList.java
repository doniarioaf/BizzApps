package com.servlet.cancelpackinglist.mapper;

import com.servlet.cancelpackinglist.entity.CancelPLList;
import com.servlet.cancelpackinglist.entity.QueryNotJoinCancelPackingListData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query_CancelPLList implements RowMapper<CancelPLList> {
    private String schemaSql;

    public Query_CancelPLList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.idpackinglist as idpackinglist, data.nodocument as nodocument, ");
        sqlBuilder.append("data.keterangan as keterangan, data.datecancel as datecancel, ");
        sqlBuilder.append("pl.nodocument as nodocumentPL ");
        sqlBuilder.append("from cancel_packinglist as data ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CancelPLList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String nodocument = rs.getString("nodocument");
        final String nodocumentPL = rs.getString("nodocumentPL");
        final String keterangan = rs.getString("keterangan");
        final Date datecancel = rs.getDate("datecancel");

        CancelPLList data = new CancelPLList();
        data.setId(id);
        data.setIdpackinglist(idpackinglist);
        data.setNodocument(nodocument);
        data.setKeterangan(keterangan);
        data.setDatecancel(datecancel);
        data.setNodocumentPL(nodocumentPL);

        return data;
    }
}
