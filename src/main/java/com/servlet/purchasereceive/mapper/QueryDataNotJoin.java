package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataNotJoin implements RowMapper<PurchaseReceiveDataList> {
    private String schemaSql;

    public QueryDataNotJoin() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idvendor as idvendor, data.transactiondate as transactiondate ");
        sqlBuilder.append("from purchasereceive as data ");

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
        final Date transactiondate = rs.getDate("transactiondate");

        PurchaseReceiveDataList data = new PurchaseReceiveDataList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdvendor(idvendor);
        data.setVendorName("");
        data.setVendorAlias("");
        data.setTransactiondate(transactiondate);

        return data;
    }
}
