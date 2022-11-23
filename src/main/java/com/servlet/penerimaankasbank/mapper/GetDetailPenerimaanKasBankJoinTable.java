package com.servlet.penerimaankasbank.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.penerimaankasbank.entity.DetailPenerimaanKasBankData;

public class GetDetailPenerimaanKasBankJoinTable implements RowMapper<DetailPenerimaanKasBankData>{
	private String schemaSql;
	
	public GetDetailPenerimaanKasBankJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idpenerimaankasbank as idpenerimaankasbank, data.idcoa as idcoa, data.catatan as catatan, data.amount as amount, data.isdownpayment as isdownpayment, ");
		sqlBuilder.append("data.idinvoice as idinvoice, data.idworkorder as idworkorder, coa.nama as coaname, wo.nodocument as wonodocument, ");
		sqlBuilder.append("inv.nodocument as invnodocument ");
		sqlBuilder.append("from detail_penerimaan_kas_bank as data ");
		sqlBuilder.append("left join m_coa as coa on coa.id = data.idcoa ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idworkorder ");
		sqlBuilder.append("left join m_invoice as inv on inv.id = data.idinvoice ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailPenerimaanKasBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idcoa = rs.getLong("idcoa");
		final String catatan = rs.getString("catatan");
		final Double amount = rs.getDouble("amount");
		final String isdownpayment = rs.getString("isdownpayment");
		final Long idinvoice = rs.getLong("idinvoice");
		final Long idworkorder = rs.getLong("idworkorder");
		final String coaname = rs.getString("coaname");
		final String wonodocument = rs.getString("wonodocument");
		final String invnodocument = rs.getString("invnodocument");
		
		
		DetailPenerimaanKasBankData data = new DetailPenerimaanKasBankData();
		data.setIdcoa(idcoa);
		data.setCoaname(coaname);
		data.setCatatan(catatan);
		data.setAmount(amount);
		data.setIsdownpayment(isdownpayment);
		data.setIdinvoice(idinvoice);
		data.setIdworkorder(idworkorder);
		data.setNodocinvoice(invnodocument);
		data.setNodocworkorder(wonodocument);
		return data;
	}

}
