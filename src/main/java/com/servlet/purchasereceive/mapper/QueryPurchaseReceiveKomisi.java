package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataKomisi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryPurchaseReceiveKomisi implements RowMapper<PurchaseReceiveDataKomisi> {
    private String schemaSql;

    public QueryPurchaseReceiveKomisi(Long idchargebox) {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.transactiondate as transactiondate, venbroker.id as venbrokerid, ");
        sqlBuilder.append("venbroker.nama as venbrokerNama, venbroker.alias as venbrokerAlias, prcharge.qty as koli, ");
        sqlBuilder.append("ven.id as venid, ven.nama as venNama, ven.alias as venAlias, ");
        sqlBuilder.append("venbroker.komisi as komisi, ");
        sqlBuilder.append("venbroker.bank as bank, venbroker.accountnobank as accountnobank, venbroker.accountnamebank as accountnamebank ");
        sqlBuilder.append("from purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");
        sqlBuilder.append("left join m_vendor as venbroker on ven.idvendorbroker = venbroker.id ");
        sqlBuilder.append("left join purchasereceive_charge as prcharge on prcharge.idpurchasereceive = data.id and prcharge.idcharge = "+idchargebox+" ");
        //

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }


    @Override
    public PurchaseReceiveDataKomisi mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date transactiondate = rs.getDate("transactiondate");
        final Long venbrokerid = rs.getLong("venbrokerid");
        final String venbrokerNama = rs.getString("venbrokerNama");
        final String venbrokerAlias = rs.getString("venbrokerAlias");
        final Long koli = rs.getLong("koli");
        final Double komisi = rs.getDouble("komisi");
        final Long venid = rs.getLong("venid");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final String bank = rs.getString("bank");
        final String accountnobank = rs.getString("accountnobank");
        final String accountnamebank = rs.getString("accountnamebank");

        PurchaseReceiveDataKomisi data = new PurchaseReceiveDataKomisi();
        data.setId(id);
        data.setIdvendorbroker(venbrokerid);
        data.setVendornamabroker(venbrokerNama);
        data.setVendoraliasbroker(venbrokerAlias);
        data.setIdvendor(venid);
        data.setVendornama(venNama);
        data.setVendoralias(venAlias);
        data.setNodocument(nodocument);
        data.setDate(transactiondate);
        data.setKoli(koli);
        data.setKomisi(komisi);
        double subtotal = 0;
        if(komisi != null && koli != null){
            subtotal = komisi.doubleValue() * koli.doubleValue();
        }
        data.setSubTotalkomisi(subtotal);
        data.setVendorbankbroker(bank);
        data.setVendoraccnobroker(accountnobank);
        data.setVendoraccnamebroker(accountnamebank);
        return data;
    }
}
