package com.servlet.cancelpackinglist.mapper;

import com.servlet.cancelpackinglist.entity.ReportCancelPackingList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query_ReportCancelPackingList implements RowMapper<ReportCancelPackingList> {
    private String schemaSql;

    public Query_ReportCancelPackingList() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(20);
        sqlBuilder.append("data.id as id, data.idpackinglist as idpackinglist, data.nodocument as nodocument, ");
        sqlBuilder.append("data.keterangan as keterangan, data.datecancel as datecancel, ");
        sqlBuilder.append("cplitems.idproduct as idproduct, cplitems.idcategoryproduct as idcategoryproduct, cplitems.qty as qty, ");
        sqlBuilder.append("prod.nama as prodnama, cprod.nama as cprodnama, cprod.size as cprodsize, ");
        sqlBuilder.append("pl.nodocument as nodocumentPL, ");
        sqlBuilder.append("cus.nama as cusName, cus.alias as cusAlias, ");
        sqlBuilder.append("ven.nama as venName, ven.alias as venAlias ");
        sqlBuilder.append("from cancel_packinglist as data ");
        sqlBuilder.append("left join cancel_packinglistitems as cplitems on cplitems.idcancelpackinglist = data.id ");
        sqlBuilder.append("left join m_product as prod on prod.id = cplitems.idproduct ");
        sqlBuilder.append("left join m_category_product as cprod on cprod.id = cplitems.idcategoryproduct ");
        sqlBuilder.append("left join packinglist as pl on pl.id = data.idpackinglist ");
        sqlBuilder.append("left join m_customer as cus on cus.id = pl.idcustomer ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = pl.idvendor ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportCancelPackingList mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idpackinglist = rs.getLong("idpackinglist");
        final String nodocument = rs.getString("nodocument");
        final String nodocumentPL = rs.getString("nodocumentPL");
        final String keterangan = rs.getString("keterangan");
        final Date datecancel = rs.getDate("datecancel");
        final String cusName = rs.getString("cusName");
        final String cusAlias = rs.getString("cusAlias");
        final String venName = rs.getString("venName");
        final String venAlias = rs.getString("venAlias");
        final String prodnama = rs.getString("prodnama");
        final String cprodnama = rs.getString("cprodnama");
        final String cprodsize = rs.getString("cprodsize");
        final Long qty = rs.getLong("qty");
        final Long idproduct = rs.getLong("idproduct");
        final Long idcategoryproduct = rs.getLong("idcategoryproduct");

        ReportCancelPackingList data = new ReportCancelPackingList();
        data.setIdcancel(id);
        data.setIdpackinglist(idpackinglist);
        data.setNoDocumentCPL(nodocument);
        data.setNoDocumentPL(nodocumentPL);
        data.setKeterangan(keterangan);
        data.setTanggalcancel(datecancel);
        data.setCustomerName(cusName);
        data.setCustomerAlias(cusAlias);
        data.setVendorName(venName);
        data.setVendorAlias(venAlias);
        data.setIdProduct(idproduct);
        data.setProductName(prodnama);
        data.setIdcategoryProduct(idcategoryproduct);
        data.setCategoryProductName(cprodnama);
        data.setCategoryProductSize(cprodsize);
        data.setQtyMati(qty);
        data.setQtyPL(0L);
        return data;
    }
}
