package com.servlet.vendor.mapper;

import com.servlet.vendor.entity.VendorDataForTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryListForDropdownList implements RowMapper<VendorDataForTemplate> {
    private String schemaSql;

    public QueryListForDropdownList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.nama as nama, data.alias as alias,data.type as type, ");
        sqlBuilder.append("data.bank as bank, data.accountnobank as accountnobank, data.accountnamebank as accountnamebank, ");
        sqlBuilder.append("data.pricebox as pricebox, data.priceongkos as priceongkos, data.idarea as idarea ");
        sqlBuilder.append("from m_vendor as data ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }
    @Override
    public VendorDataForTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nama = rs.getString("nama");
        final String alias = rs.getString("alias");
        final String type = rs.getString("type");
        final String bank = rs.getString("bank");
        final String accountnobank = rs.getString("accountnobank");
        final String accountnamebank = rs.getString("accountnamebank");
        final Double pricebox = rs.getDouble("pricebox");
        final Double priceongkos = rs.getDouble("priceongkos");
        final Long idarea = rs.getLong("idarea");


        VendorDataForTemplate data = new VendorDataForTemplate();
        data.setId(id);
        data.setNama(nama);
        data.setAlias(alias);
        data.setType(type);
        data.setBank(bank);
        data.setAccountnobank(accountnobank);
        data.setAccountnamebank(accountnamebank);
        data.setPricebox(pricebox);
        data.setPriceongkos(priceongkos);
        data.setIdarea(idarea);
        return data;
    }
}
