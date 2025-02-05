package com.servlet.deposit.mapper;

import com.servlet.deposit.entity.ReportKartuDeposit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryReportKartuDeposit implements RowMapper<ReportKartuDeposit> {
    private String schemaSql;

    public QueryReportKartuDeposit() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(10);
        sqlBuilder.append("data.id as id,data.nodocument as nodocument, data.amount as amount, data.depositdate as depositdate, data.idvendor as idvendor, ");
        sqlBuilder.append("ven.nama as vennama, ven.alias as venalias  ");
        sqlBuilder.append("from deposit as data ");
        sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendor ");
        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public ReportKartuDeposit mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idvendor = rs.getLong("idvendor");
        final String vennama = rs.getString("vennama");
        final String venalias = rs.getString("venalias");
        final String nodocument = rs.getString("nodocument");
        final Double amount = rs.getDouble("amount");
        final Date depositdate = rs.getDate("depositdate");
        ReportKartuDeposit data = new ReportKartuDeposit();
        data.setId(id);
        data.setIdvendor(idvendor != null?idvendor:0L);
        //karena idvendor di deposit sudah idvendorparent, dipasang disini biar ga pusing
        data.setIdvendorParent(idvendor != null?idvendor:0L);
        data.setVendorName(vennama);
        data.setVendorAlias(venalias);
        data.setDocumentNumber(nodocument);
        data.setAmount(amount);
        data.setDate(depositdate);
        data.setType("DEPOSIT");
        return data;
    }
}
