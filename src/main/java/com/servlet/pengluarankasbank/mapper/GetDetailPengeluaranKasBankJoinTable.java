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
		sqlBuilder.append("data.idcoa as idcoa, data.catatan as catatan, data.amount as amount, data.idasset as idasset, coa.nama as coaname, data.idinvoiceitem as idinvoiceitem, invtype.nama as invtypenama, ");
		sqlBuilder.append("asset.kepala_nama as kepala_nama, asset.buntut_nama as buntut_nama  ");
		sqlBuilder.append("from detail_pengeluaran_kas_bank as data ");
		sqlBuilder.append("left join m_coa as coa on coa.id = data.idcoa ");
		sqlBuilder.append("left join m_invoice_type as invtype on invtype.id = data.idinvoiceitem ");
		sqlBuilder.append("left join m_asset as asset on asset.id = data.idasset ");
		
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
		final String invtypenama = rs.getString("invtypenama");
		final Long idinvoiceitem = rs.getLong("idinvoiceitem");
		final String kepala_nama = rs.getString("kepala_nama");
		final String buntut_nama = rs.getString("buntut_nama");
		
		
		DetailPengeluaranKasBankData data = new DetailPengeluaranKasBankData();
		data.setIdcoa(idcoa);
		data.setCoaName(coaname);
		data.setCatatan(catatan);
		data.setAmount(amount);
		data.setIdasset(idasset);
		data.setAssetName("");
		data.setIdinvoiceitem(idinvoiceitem);
		data.setInvoiceitemName(invtypenama);
		data.setAssetNameBuntut(buntut_nama);
		data.setAssetNameKepala(kepala_nama);
		return data;
	}

}
