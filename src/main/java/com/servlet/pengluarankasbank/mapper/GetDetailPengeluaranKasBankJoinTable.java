package com.servlet.pengluarankasbank.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;

public class GetDetailPengeluaranKasBankJoinTable implements RowMapper<DetailPengeluaranKasBankData>{
private String schemaSql;
	
	public GetDetailPengeluaranKasBankJoinTable() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idcoa as idcoa, data.catatan as catatan, data.amount as amount, data.idasset as idasset, coa.nama as coaname ");
		sqlBuilder.append("from detail_pengeluaran_kas_bank as data ");
		sqlBuilder.append("left join m_coa as coa on coa.id = data.idcoa ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	@Override
	public DetailPengeluaranKasBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idcoa = rs.getLong("idcoa");
		final String catatan = rs.getString("catatan");
		final Double amount = rs.getDouble("amount");
		final Long idasset = rs.getLong("idasset");
		final String coaname = rs.getString("coaname");
		
		DetailPengeluaranKasBankData data = new DetailPengeluaranKasBankData();
		data.setIdcoa(idcoa);
		data.setCoaName(coaname);
		data.setCatatan(catatan);
		data.setAmount(amount);
		data.setIdasset(idasset);
		data.setAssetName("");
		return data;
	}

}
