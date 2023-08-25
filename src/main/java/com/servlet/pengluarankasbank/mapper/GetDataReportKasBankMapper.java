package com.servlet.pengluarankasbank.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.report.entity.EntityHelperKasBank;

public class GetDataReportKasBankMapper implements RowMapper<EntityHelperKasBank>{
private String schemaSql;
	
	public GetDataReportKasBankMapper() {
		final StringBuilder sqlBuilder = new StringBuilder(400);
		
		sqlBuilder.append("mpengeluaran.id as pengeluaranid, ");
		sqlBuilder.append("mpengeluaran.paymentdate as pengeluarantanggaltransaksi, ");
		sqlBuilder.append("mpengeluaran.nodocument as pengeluarannodocument, ");
		sqlBuilder.append("coapengeluaran.nama as pengeluarancoanama, ");
		sqlBuilder.append("workorderpengeluaran.nodocument as pengeluarannodocumentwo, ");
		sqlBuilder.append("workorderpengeluaran.noaju as pengeluarannoaju, ");
		sqlBuilder.append("mpengeluaran.paymentto as pengeluaranpaymentto, ");
		sqlBuilder.append("cust.customername as pengeluarancustomername, emp.nama as pengeluaranempname, vendor.nama as pengeluaranvendorname, ");
		sqlBuilder.append("mpengeluaran.keterangan as pengeluaranketerangan, ");
		sqlBuilder.append("dpengeluaran.amount as pengeluaranamount ");
		
		sqlBuilder.append("from detail_pengeluaran_kas_bank as dpengeluaran ");
		sqlBuilder.append("left join m_pengeluaran_kas_bank as mpengeluaran on mpengeluaran.id = dpengeluaran.idpengeluarankasbank ");
		sqlBuilder.append("left join m_coa as coapengeluaran on coapengeluaran.id = dpengeluaran.idcoa ");
		sqlBuilder.append("left join m_workorder as workorderpengeluaran on workorderpengeluaran.id = mpengeluaran.idwo ");
		
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = mpengeluaran.idcustomer ");
		sqlBuilder.append("left join m_employee_manggala as emp on emp.id = mpengeluaran.idemployee ");
		sqlBuilder.append("left join m_vendor as vendor on vendor.id = mpengeluaran.idvendor ");

		
//		sqlBuilder.append("mpenerimaan.id as penerimaanid, mpengeluaran.id as pengeluaranid, ");
//		sqlBuilder.append("mpenerimaan.receivedate as penerimaantanggaltransaksi, mpengeluaran.paymentdate as pengeluarantanggaltransaksi, ");
//		sqlBuilder.append("mpenerimaan.nodocument as penerimaannodocument, mpengeluaran.nodocument as pengeluarannodocument, ");
//		sqlBuilder.append("coapenerimaan.nama as penerimaancoanama, coapengeluaran.nama as pengeluarancoanama, ");
//		sqlBuilder.append("workorderpenerimaan.nodocument as penerimaannodocumentwo, workorderpengeluaran.nodocument as pengeluarannodocumentwo, ");
//		sqlBuilder.append("workorderpenerimaan.noaju as penerimaannoaju, workorderpengeluaran.noaju as pengeluarannoaju, ");
//		sqlBuilder.append("invoicepenerimaan.nodocument as penerimaannodocumentinv, ");
//		sqlBuilder.append("mpenerimaan.receivefrom as penerimaanreceivefrom, mpengeluaran.paymentto as pengeluaranpaymentto, ");
//		sqlBuilder.append("cust.customername as pengeluarancustomername, emp.nama as pengeluaranempname, vendor.nama as pengeluaranvendorname, ");
//		sqlBuilder.append("mpenerimaan.keterangan as penerimaanketerangan, mpengeluaran.keterangan as pengeluaranketerangan, ");
//		sqlBuilder.append("dpenerimaan.amount as penerimaanamount, dpengeluaran.amount as pengeluaranamount ");
//		
//		sqlBuilder.append("from detail_penerimaan_kas_bank as dpenerimaan ");
//		sqlBuilder.append("full join detail_pengeluaran_kas_bank as dpengeluaran on dpenerimaan.idbranch = dpengeluaran.idbranch ");
//		sqlBuilder.append("left join m_penerimaan_kas_bank as mpenerimaan on mpenerimaan.id = dpenerimaan.idpenerimaankasbank ");
//		sqlBuilder.append("left join m_pengeluaran_kas_bank as mpengeluaran on mpengeluaran.id = dpengeluaran.idpengeluarankasbank ");
//		sqlBuilder.append("left join m_coa as coapenerimaan on coapenerimaan.id = mpenerimaan.idcoa ");
//		sqlBuilder.append("left join m_coa as coapengeluaran on coapengeluaran.id = mpengeluaran.idcoa ");
//		sqlBuilder.append("left join m_workorder as workorderpengeluaran on workorderpengeluaran.id = mpengeluaran.idwo ");
//		sqlBuilder.append("left join m_workorder as workorderpenerimaan on workorderpenerimaan.id = dpenerimaan.idworkorder ");
//		sqlBuilder.append("left join m_invoice as invoicepenerimaan on invoicepenerimaan.id = dpenerimaan.idinvoice ");
//		
//		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = mpengeluaran.idcustomer ");
//		sqlBuilder.append("left join m_employee_manggala as emp on emp.id = mpengeluaran.idemployee ");
//		sqlBuilder.append("left join m_vendor as vendor on vendor.id = mpengeluaran.idvendor ");
		
		this.schemaSql = sqlBuilder.toString();
		
	}
	
	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public EntityHelperKasBank mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
//		final Long penerimaanid = rs.getLong("penerimaanid");
		final Long pengeluaranid = rs.getLong("pengeluaranid");
		
//		final Date penerimaantanggaltransaksi = rs.getDate("penerimaantanggaltransaksi");
		final Date pengeluarantanggaltransaksi = rs.getDate("pengeluarantanggaltransaksi");
		
//		final String penerimaannodocument = rs.getString("penerimaannodocument");
		final String pengeluarannodocument = rs.getString("pengeluarannodocument");
		
//		final String penerimaancoanama = rs.getString("penerimaancoanama");
		final String pengeluarancoanama = rs.getString("pengeluarancoanama");
		
//		final String penerimaannodocumentwo = rs.getString("penerimaannodocumentwo");
		final String pengeluarannodocumentwo = rs.getString("pengeluarannodocumentwo");
		
//		final String penerimaannoaju = rs.getString("penerimaannoaju");
		final String pengeluarannoaju = rs.getString("pengeluarannoaju");
		
//		final String penerimaannodocumentinv = rs.getString("penerimaannodocumentinv");
		final String pengeluarannodocumentinv = "";
		
//		final String penerimaanreceivefrom = rs.getString("penerimaanreceivefrom");
		
		final String pengeluaranpaymentto = rs.getString("pengeluaranpaymentto");
		final String pengeluarancustomername = rs.getString("pengeluarancustomername");
		final String pengeluaranempname = rs.getString("pengeluaranempname");
		final String pengeluaranvendorname = rs.getString("pengeluaranvendorname");
		
//		final String penerimaanketerangan = rs.getString("penerimaanketerangan");
		final String pengeluaranketerangan = rs.getString("pengeluaranketerangan");
		
//		final Double penerimaanamount = rs.getDouble("penerimaanamount");
		final Double pengeluaranamount = rs.getDouble("pengeluaranamount");
		
		EntityHelperKasBank data = new EntityHelperKasBank();
		data.setPenerimaanid(0L);
		data.setPenerimaantanggalTransaksi(null);
		data.setPenerimaannoVoucher(null);
		data.setPenerimaancoa(null);
		data.setPenerimaannoWO(null);
		data.setPenerimaannoAju(null);
		data.setPenerimaannoInvoice(null);
		data.setPenerimaannamaCustomer(null);
		data.setPenerimaanketerangan(null);
		data.setPenerimaanAmount(0.0);
		
		data.setPengeluaranid(pengeluaranid);
		data.setPengeluarantanggalTransaksi(pengeluarantanggaltransaksi);
		data.setPengeluarannoVoucher(pengeluarannodocument);
		data.setPengeluarancoa(pengeluarancoanama);
		data.setPengeluarannoWO(pengeluarannodocumentwo);
		data.setPengeluarannoAju(pengeluarannoaju);
		data.setPengeluarannoInvoice("");
		data.setPengeluaran_paymentto(pengeluaranpaymentto);
		data.setPengeluaran_customername(pengeluarancustomername);
		data.setPengeluaran_vendorname(pengeluaranvendorname);
		data.setPengeluaran_employeename(pengeluaranempname);
		data.setPengeluaranAmount(pengeluaranamount);
		data.setPengeluaranketerangan(pengeluaranketerangan);
		return data;
	}
}
