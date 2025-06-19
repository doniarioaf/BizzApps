package com.servlet.penerimaankasbank.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTotalAmountCustomerBaru9995 implements RowMapper<Double> {
    private String schemaSql;

    public GetTotalAmountCustomerBaru9995() {
        // TODO Auto-generated constructor stub
        final StringBuilder sqlBuilder = new StringBuilder(400);
//		sqlBuilder.append("sum(data.amount) as total ");
        sqlBuilder.append("sum(data.nilaireimbursement) as totalnilaireimbursement, sum(data.nilaippn) as totalnilaippn, sum(data.nilaijasa) as totalnilaijasa ");
        sqlBuilder.append("from detail_penerimaan_kas_bank as data ");
        sqlBuilder.append("left join m_penerimaan_kas_bank as penerimaan on penerimaan.id = data.idpenerimaankasbank ");

        this.schemaSql = sqlBuilder.toString();
    }

    public String schema() {
        return this.schemaSql;
    }

    @Override
    public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
        // TODO Auto-generated method stub
        final Double totalnilaireimbursement = rs.getDouble("totalnilaireimbursement");
        final Double totalnilaippn = rs.getDouble("totalnilaippn");
        final Double totalnilaijasa = rs.getDouble("totalnilaijasa");

        Double totalamount = 0.0;
        if(totalnilaireimbursement != null){
            totalamount = totalamount + totalnilaireimbursement.doubleValue();
        }

        if(totalnilaippn != null){
            totalamount = totalamount + totalnilaippn.doubleValue();
        }

        if(totalnilaijasa != null){
            totalamount = totalamount + totalnilaijasa.doubleValue();
        }
        return totalamount != null?totalamount:0;
    }
}
