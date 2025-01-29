package com.servlet.komisi.mapper;

import com.servlet.komisi.entity.KomisiDataReportKomisi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryKomisiReportKomisi implements RowMapper<KomisiDataReportKomisi> {
    private String schemaSql;

    public QueryKomisiReportKomisi() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idpurchasereceive as idpurchasereceive, data.koli as koli, data.komisiperkoli as komisiperkoli, data.subtotalkomisi as subtotalkomisi, ");
        sqlBuilder.append("komisi.id as id, komisi.nodocument as nodocument, komisi.date as date, ");
        sqlBuilder.append("ven.id as venId,ven.nama as venNama, ven.alias as venAlias, ven.idvendorbroker as venIdvendorbroker, ");
        sqlBuilder.append("pr.nodocument as prnodocument ");
        sqlBuilder.append("from komisi_item as data ");
        sqlBuilder.append("left join komisi as komisi on komisi.id = data.idkomisi ");
        sqlBuilder.append("left join purchasereceive as pr on pr.id = data.idpurchasereceive ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = pr.idvendor ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public KomisiDataReportKomisi mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long koli = rs.getLong("koli");
        final Double komisiperkoli = rs.getDouble("komisiperkoli");
        final Double subtotalkomisi = rs.getDouble("subtotalkomisi");
        final Long idkomisi = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final Long venIdvendorbroker = rs.getLong("venIdvendorbroker");
        final Long venId = rs.getLong("venId");
        final String prnodocument = rs.getString("prnodocument");


        KomisiDataReportKomisi data = new KomisiDataReportKomisi();
        data.setIdvendorbroker(venIdvendorbroker);
        data.setIdvendor(venId);
        data.setVendorname(venNama);
        data.setVendoralias(venAlias);
        data.setNodocumentPR(prnodocument);
        data.setDate(date);
        data.setKoli(koli);
        data.setKomisiperkoli(komisiperkoli);
        data.setSubtotalkomisi(subtotalkomisi);

        return data;
    }
}
