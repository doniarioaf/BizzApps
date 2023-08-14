package com.servlet.pengluarankasbank.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.servlet.pengluarankasbank.entity.DetailPengeluaranKasBankData;

public class GetDetailPengeluaranKasBankData implements RowMapper<DetailPengeluaranKasBankData>{
	private String schemaSql;
	
	public GetDetailPengeluaranKasBankData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idpengeluarankasbank as idpengeluarankasbank,data.idcoa as idcoa, data.catatan as catatan, data.amount as amount, data.idasset as idasset, data.idinvoiceitem as idinvoiceitem, invtype.nama as invtypenama, ");
		sqlBuilder.append("data.idpaymentitem as idpaymentitem, data.idassetsparepart as idassetsparepart, data.sparepartassettype as sparepartassettype ");
		sqlBuilder.append("from detail_pengeluaran_kas_bank as data ");
		sqlBuilder.append("left join m_invoice_type as invtype on invtype.id = data.idinvoiceitem ");
		
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
		final Long idinvoiceitem = rs.getLong("idinvoiceitem");
		final Long idpengeluarankasbank = rs.getLong("idpengeluarankasbank");
		final String invtypenama = rs.getString("invtypenama");
		final Long idpaymentitem = rs.getLong("idpaymentitem");
		final Long idassetsparepart = rs.getLong("idassetsparepart");
		final String sparepartassettype = rs.getString("sparepartassettype");
		
		DetailPengeluaranKasBankData data = new DetailPengeluaranKasBankData();
		data.setIdcoa(idcoa);
		data.setCoaName("");
		data.setCatatan(catatan);
		data.setAmount(amount);
		data.setIdasset(idasset);
		data.setAssetName("");
		data.setIdinvoiceitem(idinvoiceitem);
		data.setInvoiceitemName(invtypenama);
		data.setIdpengeluarankasbank(idpengeluarankasbank);
		data.setIdpaymentitem(idpaymentitem);
		data.setIdassetsparepart(idassetsparepart);
		data.setSparepartassettype(sparepartassettype);
		return data;
	}

}
