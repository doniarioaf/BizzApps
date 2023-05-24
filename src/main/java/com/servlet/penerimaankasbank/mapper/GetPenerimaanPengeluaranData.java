package com.servlet.penerimaankasbank.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.servlet.penerimaankasbank.entity.PenerimaanPengeluaranData;

public class GetPenerimaanPengeluaranData implements RowMapper<PenerimaanPengeluaranData>{
	private String schemaSql;
	
	public GetPenerimaanPengeluaranData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as penerimaanid, data.nodocument as penerimaannodocument, data.receivedate as penerimaanreceivedate, data.receivefrom as penerimaanreceivefrom, data.idcoa as penerimaanidcoa, ");
		sqlBuilder.append("data.idbank as penerimaanidbank, data.keterangan as penerimaanketerangan, coa.nama as penerimaancoaname, ");
		
		sqlBuilder.append("pengeluaran.id as pengeluaranid, pengeluaran.nodocument as pengeluarannodocument, pengeluaran.paymentdate as pengeluaranpaymentdate, pengeluaran.idcoa as pengeluaranidcoa, coapengeluaran.nama as pengeluarancoanama, ");
		sqlBuilder.append("pengeluaran.idbank as pengeluaranidbank, pengeluaran.paymentto as pengeluaranpaymentto, pengeluaran.keterangan as pengeluaranketerangan, ");
		sqlBuilder.append("cust.customername as pengeluarancustomername, emp.nama as pengeluaranempname, vendor.nama as pengeluaranvendorname ");
		
		sqlBuilder.append("from m_penerimaan_kas_bank as data ");
		sqlBuilder.append("full join m_pengeluaran_kas_bank as pengeluaran on data.receivedate = pengeluaran.paymentdate ");
		sqlBuilder.append("left join m_coa as coa on coa.id = data.idcoa ");
		sqlBuilder.append("left join m_coa as coapengeluaran on coapengeluaran.id = pengeluaran.idcoa ");
		
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = pengeluaran.idcustomer ");
		sqlBuilder.append("left join m_employee_manggala as emp on emp.id = pengeluaran.idemployee ");
		sqlBuilder.append("left join m_vendor as vendor on vendor.id = pengeluaran.idvendor ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public PenerimaanPengeluaranData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long penerimaanid = rs.getLong("penerimaanid");
		final String penerimaannodocument = rs.getString("penerimaannodocument");
		final Date penerimaanreceivedate = rs.getDate("penerimaanreceivedate");
		final String penerimaanreceivefrom = rs.getString("penerimaanreceivefrom");
		final Long penerimaanidcoa = rs.getLong("penerimaanidcoa");
		final Long penerimaanidbank = rs.getLong("penerimaanidbank");
		final String penerimaanketerangan = rs.getString("penerimaanketerangan");
		final String penerimaancoaname = rs.getString("penerimaancoaname");
		final String namabank = "";//rs.getString("namabank");
		
		final Long pengeluaranid = rs.getLong("pengeluaranid");
		final String pengeluarannodocument = rs.getString("pengeluarannodocument");
		final Date pengeluaranpaymentdate = rs.getDate("pengeluaranpaymentdate");
		final Long pengeluaranidcoa = rs.getLong("pengeluaranidcoa");
		final String pengeluarancoanama = rs.getString("pengeluarancoanama");
		final Long pengeluaranidbank = rs.getLong("pengeluaranidbank");
		final String pengeluaranpaymentto = rs.getString("pengeluaranpaymentto");
		final String pengeluaranketerangan = rs.getString("pengeluaranketerangan");
		final String pengeluarancustomername = rs.getString("pengeluarancustomername");
		final String pengeluaranempname = rs.getString("pengeluaranempname");
		final String pengeluaranvendorname = rs.getString("pengeluaranvendorname");
		
		
		PenerimaanPengeluaranData data = new PenerimaanPengeluaranData();
		data.setPenerimaan_id(penerimaanid);
		data.setPenerimaan_nodocument(penerimaannodocument);
		data.setPenerimaan_receivedate(penerimaanreceivedate);
		data.setPenerimaan_receivefrom(penerimaanreceivefrom);
		data.setPenerimaan_idcoa(penerimaanidcoa);
		data.setPenerimaan_coaName(penerimaancoaname);
		data.setPenerimaan_idbank(penerimaanidbank);
		data.setPenerimaan_bankName("");
		data.setPenerimaan_keterangan(penerimaanketerangan);
		
		data.setPengeluaran_id(pengeluaranid);
		data.setPengeluaran_nodocument(pengeluarannodocument);
		data.setPengeluaran_paymentdate(pengeluaranpaymentdate);
		data.setPengeluaran_idcoa(pengeluaranidcoa);
		data.setPengeluaran_coaName(pengeluarancoanama);
		data.setPengeluaran_idbank(pengeluaranidbank);
		data.setPengeluaran_bankName("");
		data.setPengeluaran_paymentto(pengeluaranpaymentto);
		data.setPengeluaran_keterangan(pengeluaranketerangan);
		data.setPengeluaran_customername(pengeluarancustomername);
		data.setPengeluaran_employeename(pengeluaranempname);
		data.setPengeluaran_vendorname(pengeluaranvendorname);
		
		return data;
	}

}
