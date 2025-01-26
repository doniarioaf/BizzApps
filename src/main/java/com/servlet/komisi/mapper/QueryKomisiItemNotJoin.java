package com.servlet.komisi.mapper;

import com.servlet.komisi.entity.KomisiItemNotJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryKomisiItemNotJoin implements RowMapper<KomisiItemNotJoin> {
    private String schemaSql;

    public QueryKomisiItemNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.idpurchasereceive as idpurchasereceive, data.koli as koli, data.komisiperkoli as komisiperkoli, data.subtotalkomisi as subtotalkomisi ");
        sqlBuilder.append("from komisi_item as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public KomisiItemNotJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long idpurchasereceive = rs.getLong("idpurchasereceive");
        final Long koli = rs.getLong("koli");
        final Double komisiperkoli = rs.getDouble("komisiperkoli");
        final Double subtotalkomisi = rs.getDouble("subtotalkomisi");
        KomisiItemNotJoin data = new KomisiItemNotJoin();
        data.setIdpurchasereceive(idpurchasereceive);
        data.setKoli(koli);
        data.setKomisiperkoli(komisiperkoli);
        data.setSubtotalkomisi(subtotalkomisi);
        return data;
    }
}
