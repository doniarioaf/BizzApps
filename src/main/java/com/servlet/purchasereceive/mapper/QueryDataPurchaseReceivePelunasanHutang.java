package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataPelunasanHutang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataPurchaseReceivePelunasanHutang implements RowMapper<PurchaseReceiveDataPelunasanHutang> {
    private String schemaSql;

    public QueryDataPurchaseReceivePelunasanHutang() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.totalprice as totalprice, data.outstanding as outstanding ");
        sqlBuilder.append("from purchasereceive as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveDataPelunasanHutang mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Double totalprice = rs.getDouble("totalprice");
        final Double outstanding = rs.getDouble("outstanding");
        PurchaseReceiveDataPelunasanHutang data = new PurchaseReceiveDataPelunasanHutang();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setTotalprice(totalprice);
        data.setOutstanding(outstanding.doubleValue() > 1?outstanding:0.0);

        return data;
    }
}
