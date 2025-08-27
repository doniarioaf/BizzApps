package com.servlet.cancelpackinglist.mapper;

import com.servlet.cancelpackinglist.entity.CancelPackingListData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Query_CancelPackingListData implements RowMapper<CancelPackingListData> {
    private String schemaSql;

    public Query_CancelPackingListData() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id, data.idpackinglist as idpackinglist, data.nodocument as nodocument, ");
        sqlBuilder.append("data.keterangan as keterangan, data.datecancel as datecancel, ");
        sqlBuilder.append("pl.nodocument as nodocumentPL, ");
        sqlBuilder.append("pl.idcustomer as idcustomer, cus.nama as cusNama, cus.alias as cusAlias, ");
        sqlBuilder.append("pl.idvendor as idvendor, ven.nama as venNama, ven.alias as venAlias, ");
        sqlBuilder.append("data.createddate as createddate, data.modifieddate as modifieddate, ");
        sqlBuilder.append("usercreate.nama as createdname, usermodified.nama as modifiednama ");
        sqlBuilder.append("from cancel_packinglist as data ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");
        sqlBuilder.append("left join m_customer as cus on pl.idcustomer = cus.id ");
        sqlBuilder.append("left join m_vendor as ven on pl.idvendor = ven.id ");
        sqlBuilder.append("left join m_user_apps as usercreate on usercreate.id = data.createdby ");
        sqlBuilder.append("left join m_user_apps as usermodified on usermodified.id = data.modifiedby ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public CancelPackingListData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String nodocument = rs.getString("nodocument");
        final String nodocumentPL = rs.getString("nodocumentPL");
        final String keterangan = rs.getString("keterangan");
        final Date datecancel = rs.getDate("datecancel");
        final Long idcustomer = rs.getLong("idcustomer");
        final String cusNama = rs.getString("cusNama");
        final String cusAlias = rs.getString("cusAlias");
        final Long idvendor = rs.getLong("idvendor");
        final String venNama = rs.getString("venNama");
        final String venAlias = rs.getString("venAlias");
        final Timestamp createddate = rs.getTimestamp("createddate");
        final Timestamp modifieddate = rs.getTimestamp("modifieddate");
        final String createdname = rs.getString("createdname");
        final String modifiednama = rs.getString("modifiednama");

        CancelPackingListData data = new CancelPackingListData();
        data.setId(id);
        data.setIdpackinglist(idpackinglist);
        data.setNodocument(nodocument);
        data.setKeterangan(keterangan);
        data.setDatecancel(datecancel);
        data.setIdcustomer(idcustomer);
        data.setCustomerName(cusNama);
        data.setCustomerAlias(cusAlias);
        data.setIdvendor(idvendor);
        data.setVendorName(venNama);
        data.setVendorAlias(venAlias);
        data.setNodocumentPL(nodocumentPL);
        data.setCreateddate(createddate);
        data.setModifieddate(modifieddate);
        data.setCreatedbyName(createdname);
        data.setModifiedbyName(modifiednama);

        return data;
    }
}
