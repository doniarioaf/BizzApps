package com.servlet.packinglist.mapper;

import com.servlet.packinglist.entity.PackingListDataList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDataList implements RowMapper<PackingListDataList> {
    private String schemaSql;

    public QueryDataList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.date as date,data.date_stock as date_stock, data.idcustomer as idcustomer, data.isalreadyupdateprice as isalreadyupdateprice, ");
        sqlBuilder.append("data.city as city, cus.nama as cusNama, cus.alias as cusAlias ");
        sqlBuilder.append("from packinglist as data ");
        sqlBuilder.append("left join m_customer as cus on cus.id = data.idcustomer ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PackingListDataList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date date = rs.getDate("date");
        final Date dateStock = rs.getDate("date_stock");
        final Long idcustomer = rs.getLong("idcustomer");
        final String cusNama = rs.getString("cusNama");
        final String cusAlias = rs.getString("cusAlias");
        final String city = rs.getString("city");
        final Boolean isalreadyupdateprice = rs.getBoolean("isalreadyupdateprice");

        PackingListDataList data = new PackingListDataList();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setDate(date);
        data.setDatestock(dateStock);
        data.setIdcustomer(idcustomer);
        data.setCustomerNama(cusNama);
        data.setCustomerAlias(cusAlias);
        data.setCity(city);
        data.setIsalreadyupdateprice(isalreadyupdateprice);
        return data;
    }
}
