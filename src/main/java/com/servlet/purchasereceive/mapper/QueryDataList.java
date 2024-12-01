package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<PurchaseReceiveDataList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idvendor as idvendor, data.transactiondate as transactiondate, ");
        sqlBuilder.append("ven.nama as venNama, ven.alias as venAlias ");
        sqlBuilder.append("from purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Long idvendor = rs.getLong("idvendor");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final Date transactiondate = rs.getDate("transactiondate");

        PurchaseReceiveDataList data = new PurchaseReceiveDataList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdvendor(idvendor);
        data.setVendorName(venNama);
        data.setVendorAlias(venAlias);
        data.setTransactiondate(transactiondate);

        return data;
    }
}
