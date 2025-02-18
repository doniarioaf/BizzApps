package com.servlet.invoice.mapper;

import com.servlet.invoice.entity.InvoiceData;
import com.servlet.invoice.entity.InvoiceDataReportLabaRugi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetInvoiceDataReportLabaRugi implements RowMapper<InvoiceDataReportLabaRugi> {
    private String schemaSql;

    public GetInvoiceDataReportLabaRugi() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.idwo as idwo, ");
        sqlBuilder.append("data.idinvoicetype as idinvoicetype, data.totalinvoice as totalinvoice, data.nilaippn as nilaippn,");
        sqlBuilder.append("data.ppn as ppn , data.nilaippn as nilaippn,data.notes1 as notes1,data.notes2 as notes2 , paraminvtype.codename as invoicertypename, ");
        sqlBuilder.append("data.nodocumentreimbursement as nodocumentreimbursement, data.nodocumentjasa as nodocumentjasa, data.nilaijasa as nilaijasa,data.nilaireimbursement as nilaireimbursement, data.nofakturpajak as nofakturpajak ");
        sqlBuilder.append("from m_invoice as data ");
        sqlBuilder.append("left join m_parameter as paraminvtype on paraminvtype.code = data.idinvoicetype and paraminvtype.grup = 'INVOICETYPE' ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public InvoiceDataReportLabaRugi mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String nodocument = rs.getString("nodocument");
        final Date tanggal = rs.getDate("tanggal");
        final String idinvoicetype = rs.getString("idinvoicetype");
        final Double totalinvoice = rs.getDouble("totalinvoice");
        final Double nilaippn = rs.getDouble("nilaippn");
        final Double ppn = rs.getDouble("ppn");
        final String notes1 = rs.getString("notes1");
        final String notes2 = rs.getString("notes2");
        final String invoicertypename = rs.getString("invoicertypename");
        final String nodocumentreimbursement = rs.getString("nodocumentreimbursement");
        final String nodocumentjasa = rs.getString("nodocumentjasa");
        final Double nilaijasa = rs.getDouble("nilaijasa");
        final Double nilaireimbursement = rs.getDouble("nilaireimbursement");
        final String nofakturpajak = rs.getString("nofakturpajak");
        final Long idwo = rs.getLong("idwo");


        InvoiceDataReportLabaRugi data = new InvoiceDataReportLabaRugi();
        data.setId(id);
        data.setIdwo(idwo);
        data.setNodocument(nodocument);
        data.setTanggal(tanggal);
        data.setIdinvoicetype(idinvoicetype);
        data.setTotalinvoice(totalinvoice);
        data.setNilaippn(nilaippn);
        data.setPpn(ppn);
        data.setNotes1(notes1);
        data.setNotes2(notes2);
        data.setNamainvoicetype(invoicertypename);
        data.setNodocumentreimbursement(nodocumentreimbursement);
        data.setNodocumentjasa(nodocumentjasa);
        data.setNilaijasa(nilaijasa);
        data.setNilaireimbursement(nilaireimbursement);
        data.setNofakturpajak(nofakturpajak);
        return data;
    }
}
