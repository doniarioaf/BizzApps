package com.servlet.draftpurchasereceive.mapper;

import com.servlet.draftpurchasereceive.entity.PrintDataDraftPR;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class QueryPrintDraftPR implements RowMapper<PrintDataDraftPR> {

    private String schemaSql;

    public QueryPrintDraftPR() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.idvendor as idvendor, ");
        sqlBuilder.append("data.arriveltime as arriveltime, data.receivetime as receivetime, data.totalekor as totalekor, data.totalkg as totalkg, data.persentase as persentase, ");
        sqlBuilder.append("data.flightno as flightno, data.notes1 as notes1, data.notes2 as notes2,data.box as box, ");
        sqlBuilder.append("data.smu as smu, ven.nama as venNama, ven.alias as venAlias ");
        sqlBuilder.append("from draft_purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PrintDataDraftPR mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Time arriveltime = rs.getTime("arriveltime");
        final Time receivetime = rs.getTime("receivetime");
        final Long totalekor = rs.getLong("totalekor");
        final Double totalkg = rs.getDouble("totalkg");
        final Double persentase = rs.getDouble("persentase");
        final Long idvendor = rs.getLong("idvendor");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final String smu = rs.getString("smu");
        final String flightno = rs.getString("flightno");
        final String notes1 = rs.getString("notes1");
        final String notes2 = rs.getString("notes2");
        final Long box = rs.getLong("box");
        PrintDataDraftPR data = new PrintDataDraftPR();
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
        data.setFlightno(flightno);
        data.setNotes1(notes1);
        data.setNotes2(notes2);
        data.setBox(box);
        return data;
    }
}
