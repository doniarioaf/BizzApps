package com.servlet.purchasereceive.mapper;

import com.servlet.purchasereceive.entity.PurchaseReceiveDataDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryDataDetail implements RowMapper<PurchaseReceiveDataDetail> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.idvendor as idvendor, ");
        sqlBuilder.append("data.transactiondate as transactiondate, data.koli as koli, data.notes as notes, ");
        sqlBuilder.append("data.bank as bank, data.accountnobank as accountnobank, data.accountnamebank as accountnamebank, ");
        sqlBuilder.append("data.totalprice as totalprice, data.setor as setor, data.iddeposit as iddeposit,data.isdefaultvaluesetor as isdefaultvaluesetor, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");

        sqlBuilder.append("from purchasereceive as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PurchaseReceiveDataDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Long idvendor = rs.getLong("idvendor");
        final Date transactiondate = rs.getDate("transactiondate");
        final String koli = rs.getString("koli");
        final String notes = rs.getString("notes");
        final String bank = rs.getString("bank");
        final String accountnobank = rs.getString("accountnobank");
        final String accountnamebank = rs.getString("accountnamebank");
        final Double totalprice = rs.getDouble("totalprice");
        final Double setor = rs.getDouble("setor");
        final Long iddeposit = rs.getLong("iddeposit");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final boolean isdefaultvaluesetor = rs.getBoolean("isdefaultvaluesetor");

        PurchaseReceiveDataDetail data = new PurchaseReceiveDataDetail();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdvendor(idvendor);
        data.setVendorname(vennama);
        data.setVendoralias(venalias);
        data.setTransactiondate(transactiondate);
        data.setKoli(koli);
        data.setNotes(notes);
        data.setBank(bank);
        data.setAccountnobank(accountnobank);
        data.setAccountnamebank(accountnamebank);
        data.setTotalprice(totalprice);
        data.setSetor(setor);
        data.setIddeposit(iddeposit);
        data.setIsdefaultvaluesetor(isdefaultvaluesetor);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
