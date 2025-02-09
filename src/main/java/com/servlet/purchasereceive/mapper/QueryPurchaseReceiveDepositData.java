package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveDepositData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPurchaseReceiveDepositData implements RowMapper<PurchaseReceiveDepositData> {
    private String schemaSql;

    public QueryPurchaseReceiveDepositData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.iddeposit as iddeposit, dep.nodocument as nodocument, dep.amount as amount, dep.depositdate as depositdate ");
        sqlBuilder.append("from purchasereceive_deposit as data ");
        sqlBuilder.append("left join deposit as dep on dep.id = data.iddeposit ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveDepositData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long iddeposit = rs.getLong("iddeposit");
        final String nodocument = rs.getString("nodocument");
        final Double amount = rs.getDouble("amount");
        final Date depositdate = rs.getDate("depositdate");
        PurchaseReceiveDepositData data = new PurchaseReceiveDepositData();
        data.setIddeposit(iddeposit);
        data.setNoDocumentDeposit(nodocument);
        data.setAmount(amount);
        data.setDate(depositdate);
        return data;
    }
}
