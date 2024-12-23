package com.servlet.packinglist.mapper;

import com.servlet.packinglist.entity.PackingListDataDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryDataDetail implements RowMapper<PackingListDataDetail> {
    private String schemaSql;

    public QueryDataDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.idcustomer as idcustomer, ");
        sqlBuilder.append("data.city as city, data.attention as attention, data.flightnumber as flightnumber, data.awbnumber as awbnumber,  ");
        sqlBuilder.append("data.netto as netto, data.koli as koli, data.idpricelist as idpricelist,  ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama, ");
        sqlBuilder.append("cus.nama as cusNama, cus.alias as cusAlias ");
        sqlBuilder.append("from packinglist as data ");
        sqlBuilder.append("left join m_customer as cus on cus.id = data.idcustomer ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PackingListDataDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Long idcustomer = rs.getLong("idcustomer");
        final String cusNama = rs.getString("cusNama");
        final String cusAlias = rs.getString("cusAlias");
        final String city = rs.getString("city");
        final String attention = rs.getString("attention");
        final String flightnumber = rs.getString("flightnumber");
        final String awbnumber = rs.getString("awbnumber");
        final Double netto = rs.getDouble("netto");
        final Long koli = rs.getLong("koli");
        final Long idpricelist = rs.getLong("idpricelist");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        PackingListDataDetail data = new PackingListDataDetail();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setIdcustomer(idcustomer);
        data.setCity(city);
        data.setAttention(attention);
        data.setFlightnumber(flightnumber);
        data.setAwbnumber(awbnumber);
        data.setNetto(netto);
        data.setKoli(koli);
        data.setIdpricelist(idpricelist);
        data.setCustomerName(cusNama);
        data.setCustomerAlias(cusAlias);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
