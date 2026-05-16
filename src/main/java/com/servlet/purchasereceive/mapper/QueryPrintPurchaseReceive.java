package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PrintDataPurchaseReceive;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryPrintPurchaseReceive implements RowMapper<PrintDataPurchaseReceive> {
    private String schemaSql;

    public QueryPrintPurchaseReceive() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(20);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idvendor as idvendor, data.outstanding as outstanding, ");
        sqlBuilder.append("data.transactiondate as transactiondate, data.koli as koli, data.notes as notes, data.notes2 as notes2, ");
        sqlBuilder.append("data.bank as bank, data.accountnobank as accountnobank, data.accountnamebank as accountnamebank, ");
        sqlBuilder.append("data.totalprice as totalprice, data.setor as setor,data.setor_pinjaman as setor_pinjaman, data.iddeposit as iddeposit, data.createddate as createddate, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias, ven.bank as venbank, ven.accountnobank as venaccountnobank, ven.accountnamebank as venaccountnamebank, ");
        sqlBuilder.append("dep.amount as depamount, dpr.smu as nosmu,dpr.flightno as flightno, ");
        sqlBuilder.append("area.nama as namaarea, area.alias as aliasarea ");
        sqlBuilder.append("from purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");
        sqlBuilder.append("left join deposit as dep on dep.id = data.iddeposit ");
        sqlBuilder.append("left join draft_purchasereceive as dpr on dpr.id = data.iddraftpurchasereceive ");
        sqlBuilder.append("left join m_area as area on area.id = data.idarea ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PrintDataPurchaseReceive mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Long idvendor = rs.getLong("idvendor");
        final Date transactiondate = rs.getDate("transactiondate");
        final String koli = rs.getString("koli");
        final String notes = rs.getString("notes");
        final String notes2 = rs.getString("notes2");
        final String bank = rs.getString("bank");
        final String accountnobank = rs.getString("accountnobank");
        final String accountnamebank = rs.getString("accountnamebank");
        final Double totalprice = rs.getDouble("totalprice");
        final Double setor = rs.getDouble("setor");
        final Double setor_pinjaman = rs.getDouble("setor_pinjaman");
        final Double outstanding = rs.getDouble("outstanding");

        final Long iddeposit = rs.getLong("iddeposit");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        final String venbank = rs.getString("venbank");
        final String venaccountnobank = rs.getString("venaccountnobank");
        final String venaccountnamebank = rs.getString("venaccountnamebank");
        final Double depamount = rs.getDouble("depamount");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final String nosmu = rs.getString("nosmu");
        final String flightno = rs.getString("flightno");

        final String namaarea = rs.getString("namaarea");
        final String aliasarea = rs.getString("aliasarea");

        PrintDataPurchaseReceive data = new PrintDataPurchaseReceive();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdvendor(idvendor);
        data.setVendorNama(vennama);
        data.setVendorAlias(venalias);
        data.setVendorBank(venbank);
        data.setVendorAccNo(venaccountnobank);
        data.setVendorAccNameBank(venaccountnamebank);
        data.setTransactiondate(transactiondate);
        data.setKoli(koli);
        data.setNotes(notes);
        data.setNotes2(notes2);
        data.setBank(bank);
        data.setAccountnobank(accountnobank);
        data.setAccountnamebank(accountnamebank);
        data.setTotalprice(totalprice);
        data.setSetor(setor);
        data.setSetorPinjaman(setor_pinjaman);
        data.setNilaitransfer(outstanding);
        data.setIddeposit(iddeposit);
        data.setDepositAmount(depamount);
        data.setCreateddate(createddate);
        data.setNoSMU(nosmu);
        data.setAliasArea(aliasarea);
        data.setNamaArea(namaarea);
        data.setFlightno(flightno);
        return data;
    }
}
