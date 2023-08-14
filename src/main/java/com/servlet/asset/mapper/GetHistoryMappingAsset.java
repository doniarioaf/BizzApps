package com.servlet.asset.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.asset.entity.HistoryAssetMappingData;

public class GetHistoryMappingAsset implements RowMapper<HistoryAssetMappingData>{
	private String schemaSql;
	
	public GetHistoryMappingAsset() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.idasset as idasset, data.before as before, data.after as after, data.type as type, data.iduser as iduser, data.tanggal as tanggal, data.idassetmapping as idassetmapping, ");
		sqlBuilder.append("assetafter.kodeasset as kodeasset,assetafter.assettype as assettype,  ");
		sqlBuilder.append("assetafter.kepala_nama as kepala_nama,assetafter.buntut_nama as buntut_nama,  ");
		sqlBuilder.append("assetafter.sparepartkepala_nama as sparepartkepala_nama,assetafter.sparepartbuntut_nama as sparepartbuntut_nama,  ");
		sqlBuilder.append("pengeluarankasbank.nodocument as nodocument,data.idpengeluarankasbank as idpengeluarankasbank  ");
		sqlBuilder.append("from history_asset_mapping as data ");
		sqlBuilder.append("left join m_asset as assetafter on assetafter.id = data.after ");
		sqlBuilder.append("left join m_pengeluaran_kas_bank as pengeluarankasbank on pengeluarankasbank.id = data.idpengeluarankasbank ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public HistoryAssetMappingData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final Long idasset = rs.getLong("idasset");
		final Long before = rs.getLong("before");
		final Long after = rs.getLong("after");
		final String type = rs.getString("type");
		final Long iduser = rs.getLong("iduser");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		final String kodeasset = rs.getString("kodeasset");
		final String assettype = rs.getString("assettype");
		final String kepala_nama = rs.getString("kepala_nama");
		final String buntut_nama = rs.getString("buntut_nama");
		final String sparepartkepala_nama = rs.getString("sparepartkepala_nama");
		final String sparepartbuntut_nama = rs.getString("sparepartbuntut_nama");
		final Long idassetmapping = rs.getLong("idassetmapping");
		final Long idpengeluarankasbank = rs.getLong("idpengeluarankasbank");
		final String nodocument = rs.getString("nodocument");
		
		
		HistoryAssetMappingData data = new HistoryAssetMappingData();
		data.setId(id);
		data.setIdasset(idasset);
		data.setBefore(before);
		data.setAfter(after);
		data.setType(type);
		data.setIduser(iduser);
		data.setTanggal(tanggal);
		data.setKodeasset(kodeasset);
		data.setAssettype(assettype);
		data.setKepala_nama(kepala_nama);
		data.setBuntut_nama(buntut_nama);
		data.setSparepartkepala_nama(sparepartkepala_nama);
		data.setSparepartbuntut_nama(sparepartbuntut_nama);
		data.setIdassetmapping(idassetmapping);
		data.setIdpengeluarankasbank(idpengeluarankasbank);
		data.setNoDocPengeluaranKasBank(nodocument);
		return data;
	}

}
