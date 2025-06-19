package com.servlet.packinglist.mapper;

import com.servlet.packinglist.entity.PackingListDropDown;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDropDown implements RowMapper<PackingListDropDown> {
    private String schemaSql;

    public QueryDropDown() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date, data.idcustomer as idcustomer, ");
        sqlBuilder.append("data.city as city, data.attention as attention, data.flightnumber as flightnumber, data.awbnumber as awbnumber,  ");
        sqlBuilder.append("data.netto as netto, data.koli as koli, data.idpricelist as idpricelist,  ");
        sqlBuilder.append("cus.nama as cusNama, cus.alias as cusAlias, cus.address as cusAddress, cus.phonenumber as cusphonenumber ");
        sqlBuilder.append("from packinglist as data ");
        sqlBuilder.append("left join m_customer as cus on cus.id = data.idcustomer ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PackingListDropDown mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Long idcustomer = rs.getLong("idcustomer");
        final String cusNama = rs.getString("cusNama");
        final String cusAlias = rs.getString("cusAlias");
        final String cusAddress = rs.getString("cusAddress");
        final String city = rs.getString("city");
        final String attention = rs.getString("attention");
        final String flightnumber = rs.getString("flightnumber");
        final String awbnumber = rs.getString("awbnumber");
        final Double netto = rs.getDouble("netto");
        final Long koli = rs.getLong("koli");
        final String cusphonenumber = rs.getString("cusphonenumber");

        PackingListDropDown data = new PackingListDropDown();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setIdcustomer(idcustomer);
        data.setCustomerName(cusNama);
        data.setCustomerALias(cusAlias);
        data.setCustomerAddress(cusAddress);
        data.setCustomerPhone(cusphonenumber);
        data.setCity(city);
        data.setAttention(attention);
        data.setFlightnumber(flightnumber);
        data.setAwbnumber(awbnumber);
        data.setNetto(netto);
        data.setKoli(koli);

        return data;
    }
}
