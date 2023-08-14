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
		sqlBuilder.append("data.idcoa as idcoa, data.catatan as catatanp, data.amount as amount, data.idasset as idasset, coa.nama as coaname, data.idinvoiceitem as idinvoiceitem, invtype.nama as invtypenama, ");
		sqlBuilder.append("asset.kepala_nama as kepala_nama, asset.buntut_nama as buntut_nama,  ");
		
		sqlBuilder.append("data.idpaymentitem as idpaymentitem, paymenttype.nama as paymentitemname,");
		sqlBuilder.append("data.idassetsparepart as idassetsparepart, assetsparepart.sparepartkepala_nama as sparepartkepala_nama, assetsparepart.sparepartbuntut_nama as sparepartbuntut_nama, ");
		sqlBuilder.append("data.sparepartassettype as sparepartassettype, paramspareparttype.codename as spareparttypename ");
		
		sqlBuilder.append("from detail_pengeluaran_kas_bank as data ");
		sqlBuilder.append("left join m_coa as coa on coa.id = data.idcoa ");
		sqlBuilder.append("left join m_invoice_type as invtype on invtype.id = data.idinvoiceitem ");
		sqlBuilder.append("left join m_asset as asset on asset.id = data.idasset ");
		
		sqlBuilder.append("left join m_payment_type as paymenttype on paymenttype.id = data.idpaymentitem ");
		sqlBuilder.append("left join m_asset as assetsparepart on assetsparepart.id = data.idassetsparepart ");
		sqlBuilder.append("left join m_parameter as paramspareparttype on paramspareparttype.code = data.sparepartassettype and paramspareparttype.grup = 'JENIS_SPAREPART' ");
		
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	@Override
	public DetailPengeluaranKasBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idcoa = rs.getLong("idcoa");
		final String catatan = rs.getString("catatanp");
		final Double amount = rs.getDouble("amount");
		final Long idasset = rs.getLong("idasset");
		final String coaname = rs.getString("coaname");
		final String invtypenama = rs.getString("invtypenama");
		final Long idinvoiceitem = rs.getLong("idinvoiceitem");
		final String kepala_nama = rs.getString("kepala_nama");
		final String buntut_nama = rs.getString("buntut_nama");
		final Long idpaymentitem = rs.getLong("idpaymentitem");
		final String paymentitemname = rs.getString("paymentitemname");
		final Long idassetsparepart = rs.getLong("idassetsparepart");
		final String sparepartkepala_nama = rs.getString("sparepartkepala_nama");
		final String sparepartbuntut_nama = rs.getString("sparepartbuntut_nama");
		final String spareparttypename = rs.getString("spareparttypename");
		final String sparepartassettype = rs.getString("sparepartassettype");
		
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
		data.setIdpaymentitem(idpaymentitem);
		data.setPaymentitemName(paymentitemname);
		data.setIdassetsparepart(idassetsparepart);
		data.setAssetsparepartNameKepala(sparepartkepala_nama);
		data.setAssetsparepartNameBuntut(sparepartbuntut_nama);
		data.setSparepartassettypeName(spareparttypename);
		data.setSparepartassettype(sparepartassettype);
		return data;
	}

}
