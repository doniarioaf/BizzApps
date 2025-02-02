package com.servlet.pricelist.mapper;

import com.servlet.pricelist.entity.PriceListDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryDataDetail implements RowMapper<PriceListDetail> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.pricedate as pricedate,data.pricedatethru as pricedatethru,data.notes as notes, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama, ");
        sqlBuilder.append("data.idcustomer as idcustomer, cust.nama as custnama, cust.alias as custalias ");
        sqlBuilder.append("from pricelist as data ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");
        sqlBuilder.append("left join m_customer as cust on cust.id = data.idcustomer ");

        this.schemaSql = sqlBuilder.toString();
    }
    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PriceListDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Date pricedate = rs.getDate("pricedate");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        final Date pricedatethru = rs.getDate("pricedatethru");
        final String notes = rs.getString("notes");
        final Long idcustomer = rs.getLong("idcustomer");
        final String custnama = rs.getString("custnama");
        final String custalias = rs.getString("custalias");

        PriceListDetail data = new PriceListDetail();
        data.setId(id);
        data.setPricedate(pricedate);
        data.setPricedatethru(pricedatethru);
        data.setNotes(notes);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        data.setIdcustomer(idcustomer);
        data.setCustomerName(custnama);
        data.setCustomerAlias(custalias);
        return data;
    }
}
