package com.servlet.penerimaankasbank.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;

public class GetDetailPenerimaanKasBankData implements RowMapper<DetailPenerimaanKasBankData>{
	private String schemaSql;
	
	public GetDetailPenerimaanKasBankData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idpenerimaankasbank as idpenerimaankasbank, data.idcoa as idcoa, data.catatan as catatan, data.amount as amount, data.isdownpayment as isdownpayment, ");
		sqlBuilder.append("data.idinvoice as idinvoice, data.idworkorder as idworkorder ");
		sqlBuilder.append("from detail_penerimaan_kas_bank as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailPenerimaanKasBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
//		final Long idpenerimaankasbank = rs.getLong("idpenerimaankasbank");
		final Long idcoa = rs.getLong("idcoa");
		final String catatan = rs.getString("catatan");
		final Double amount = rs.getDouble("amount");
		final String isdownpayment = rs.getString("isdownpayment");
		final Long idinvoice = rs.getLong("idinvoice");
		final Long idworkorder = rs.getLong("idworkorder");
		
		DetailPenerimaanKasBankData data = new DetailPenerimaanKasBankData();
		data.setIdcoa(idcoa);
		data.setCatatan(catatan);
		data.setAmount(amount);
		data.setIsdownpayment(isdownpayment);
		data.setIdinvoice(idinvoice);
		data.setIdworkorder(idworkorder);
		return data;
	}

}
