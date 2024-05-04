package com.servlet.penerimaankasbank.mapper;

import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;
import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankDataLabaRugi;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDataDetailPenerimaanReportLabaRugi implements RowMapper<DetailPenerimaanKasBankDataLabaRugi> {
    private String schemaSql;

    public GetDataDetailPenerimaanReportLabaRugi() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
        sqlBuilder.append("data.counter as counter, data.idpenerimaankasbank as idpenerimaankasbank, data.idcoa as idcoa, data.catatan as catatan, data.amount as amount, data.isdownpayment as isdownpayment, ");
        sqlBuilder.append("data.idinvoice as idinvoice, data.idworkorder as idworkorder, penerimaan.nodocument as penerimaannodoc, penerimaan.receivedate as penerimaanreceivedate, penerimaan.receivefrom as penerimaanreceivefrom, data.penyesuaian as penyesuaian ");
        sqlBuilder.append("from detail_penerimaan_kas_bank as data ");
        sqlBuilder.append("left join m_penerimaan_kas_bank as penerimaan on penerimaan.id = data.idpenerimaankasbank ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public DetailPenerimaanKasBankDataLabaRugi mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        final Long idpenerimaankasbank = rs.getLong("idpenerimaankasbank");
        final Long counter = rs.getLong("counter");
        final Long idcoa = rs.getLong("idcoa");
        final String catatan = rs.getString("catatan");
        final Double amount = rs.getDouble("amount");
        final String isdownpayment = rs.getString("isdownpayment");
        final Long idinvoice = rs.getLong("idinvoice");
        final Long idworkorder = rs.getLong("idworkorder");
        final String penerimaannodoc = rs.getString("penerimaannodoc");
        final Date receivedate = rs.getDate("penerimaanreceivedate");
        final String penerimaanreceivefrom = rs.getString("penerimaanreceivefrom");
        final Double penyesuaian = rs.getDouble("penyesuaian");


        DetailPenerimaanKasBankDataLabaRugi data = new DetailPenerimaanKasBankDataLabaRugi();
        data.setIdpenerimaankasbank(idpenerimaankasbank);
        data.setIdcoa(idcoa);
        data.setCatatan(catatan);
        data.setAmount(amount);
        data.setIsdownpayment(isdownpayment);
        data.setIdinvoice(idinvoice);
        data.setIdworkorder(idworkorder);
        data.setCounter(counter);
        data.setNodocpenerimaan(penerimaannodoc);
        data.setTanggalpenerimaan(receivedate);
        data.setReceivefrom(penerimaanreceivefrom);
        data.setPenyesuaian(penyesuaian);
        return data;
    }
}
