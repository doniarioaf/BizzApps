package com.servlet.cargo.mapper;

import com.servlet.cargo.entity.CargoDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class QueryCargoDetail implements RowMapper<CargoDetail> {
    private String schemaSql;

    public QueryCargoDetail() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.idvendor as idvendor, data.date as date, data.invoicenumber as invoicenumber, ");
        sqlBuilder.append("data.smunumber as smunumber,data.awbnumber as awbnumber, data.koli as koli, data.grossamount as grossamount, ");
        sqlBuilder.append("data.ppnamount as ppnamount,data.ppn23amount as ppn23amount, data.netamount as netamount, data.outstanding as outstanding, ");
        sqlBuilder.append("ven.nama as venNama, ven.alias as venAlias, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from cargo as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");


        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CargoDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final Date date = rs.getDate("date");
        final String invoicenumber = rs.getString("invoicenumber");
        final String smunumber = rs.getString("smunumber");
        final String awbnumber = rs.getString("awbnumber");
        final Long koli = rs.getLong("koli");
        final Double grossamount = rs.getDouble("grossamount");
        final Double ppnamount = rs.getDouble("ppnamount");
        final Double ppn23amount = rs.getDouble("ppn23amount");
        final Double netamount = rs.getDouble("netamount");
        final Double outstanding = rs.getDouble("outstanding");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");
        CargoDetail data = new CargoDetail();
        data.setId(id);
        data.setIdvendor(idvendor);
        data.setVendorNama(venNama);
        data.setVendorAlias(venAlias);
        data.setInvoicenumber(invoicenumber);
        data.setDate(date);
        data.setSmunumber(smunumber);
        data.setAwbnumber(awbnumber);
        data.setKoli(koli);
        data.setGrossamount(grossamount);
        data.setPpnamount(ppnamount);
        data.setPpn23amount(ppn23amount);
        data.setNetamount(netamount);
        data.setOutstanding(outstanding);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);
        return data;
    }
}
