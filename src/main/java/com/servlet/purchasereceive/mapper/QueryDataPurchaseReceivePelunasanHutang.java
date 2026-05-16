package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataPelunasanHutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataPurchaseReceivePelunasanHutang implements RowMapper<PurchaseReceiveDataPelunasanHutang> {
    private String schemaSql;

    public QueryDataPurchaseReceivePelunasanHutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.totalprice as totalprice, data.outstanding as outstanding, data.transactiondate as transactiondate, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias ");
        sqlBuilder.append("from purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveDataPelunasanHutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        final Double totalprice = rs.getDouble("totalprice");
        final Double outstanding = rs.getDouble("outstanding");
        final Date transactiondate = rs.getDate("transactiondate");

        PurchaseReceiveDataPelunasanHutang data = new PurchaseReceiveDataPelunasanHutang();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setVendorName(vennama);
        data.setVendorAlias(venalias);
        data.setTotalprice(totalprice);
        data.setOutstanding(outstanding.doubleValue() > 1?outstanding:0.0);
        data.setTransactiondate(transactiondate);

        return data;
    }
}
