package com.servlet.penerimaankasbank.mapper;

import com.servlet.penerimaankasbank.entity.PenerimaanKasBankInvoice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDataPenerimaanKasBankInvoice implements RowMapper<PenerimaanKasBankInvoice> {
    private String schemaSql;

    public GetDataPenerimaanKasBankInvoice() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("det.idinvoice as idinvoice, data.id as id, data.nodocument as nodocument ");
        sqlBuilder.append("from m_penerimaan_kas_bank as data ");
        sqlBuilder.append("left join detail_penerimaan_kas_bank as det on det.idpenerimaankasbank = data.id ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public PenerimaanKasBankInvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final Long idinvoice = rs.getLong("idinvoice");
        final String nodocument = rs.getString("nodocument");
        PenerimaanKasBankInvoice data = new PenerimaanKasBankInvoice();
        data.setId(id);
        data.setNodocument(nodocument);
        data.setIdinvoice(idinvoice);
        return data;
    }
}
