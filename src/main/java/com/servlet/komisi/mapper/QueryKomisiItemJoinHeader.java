package com.servlet.komisi.mapper;

import com.servlet.komisi.entity.KomisiItemJoinHeader;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryKomisiItemJoinHeader implements RowMapper<KomisiItemJoinHeader> {
    private String schemaSql;

    public QueryKomisiItemJoinHeader() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idpurchasereceive as idpurchasereceive, data.koli as koli, data.komisiperkoli as komisiperkoli, data.subtotalkomisi as subtotalkomisi, ");
        sqlBuilder.append("komisi.id as id, komisi.nodocument as nodocument, komisi.date as date ");
        sqlBuilder.append("from komisi_item as data ");
        sqlBuilder.append("left join komisi as komisi on komisi.id = data.idkomisi ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public KomisiItemJoinHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long koli = rs.getLong("koli");
        final Double komisiperkoli = rs.getDouble("komisiperkoli");
        final Double subtotalkomisi = rs.getDouble("subtotalkomisi");
        KomisiItemJoinHeader data = new KomisiItemJoinHeader();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setIdpurchasereceive(idpurchasereceive);
        data.setKoli(koli);
        data.setKomisiperkoli(komisiperkoli);
        data.setSubtotalkomisi(subtotalkomisi);
        return data;
    }
}
