package com.servlet.pengluarankasbank.mapper;

import com.servlet.pengluarankasbank.entity.PengeluaranReportLabaRugi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetPengeluaranReportLabaRugi implements RowMapper<PengeluaranReportLabaRugi> {
    private String schemaSql;

    public GetPengeluaranReportLabaRugi() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("pengeluaran.nodocument as nodocument, pengeluaran.keterangan as keterangan, data.amount as amount, bank.namabank as namabank, pengeluaran.paymentdate as paymentdate  ");
        sqlBuilder.append("from detail_pengeluaran_kas_bank as data ");
        sqlBuilder.append("left join m_pengeluaran_kas_bank as pengeluaran on pengeluaran.id = data.idpengeluarankasbank ");
        sqlBuilder.append("left join m_bank_account as bank on bank.id = pengeluaran.idbank ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PengeluaranReportLabaRugi mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String nodocument = rs.getString("nodocument");
        final String keterangan = rs.getString("keterangan");
        final Double amount = rs.getDouble("amount");
        final String namabank = rs.getString("namabank");
        final Date paymentdate = rs.getDate("paymentdate");

        PengeluaranReportLabaRugi data = new PengeluaranReportLabaRugi();
        data.setNoDocument(nodocument);
        data.setKeterangan(keterangan);
        data.setAmount(amount);
        data.setNamabank(namabank);
        data.setPaymentdate(paymentdate);
        return data;
    }
}
