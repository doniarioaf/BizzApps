package com.servlet.penerimaankasbank.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.report.entity.EntityHelperKasBank;

public class GetDataReportKasBankMapper implements RowMapper<EntityHelperKasBank>{
	private String schemaSql;
	
	public GetDataReportKasBankMapper() {
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("mpenerimaan.id as penerimaanid,  ");
		sqlBuilder.append("mpenerimaan.receivedate as penerimaantanggaltransaksi, ");
		sqlBuilder.append("mpenerimaan.nodocument as penerimaannodocument, ");
		sqlBuilder.append("coapenerimaan.nama as penerimaancoanama, coapenerimaan.code as penerimaancoacode, ");
		sqlBuilder.append("workorderpenerimaan.nodocument as penerimaannodocumentwo, workorderinv.nodocument as penerimaannodocumentwoinv, ");
		sqlBuilder.append("workorderpenerimaan.noaju as penerimaannoaju, workorderinv.noaju as penerimaannoajuinv,  ");
		sqlBuilder.append("invoicepenerimaan.nodocument as penerimaannodocumentinv, ");
		sqlBuilder.append("mpenerimaan.receivefrom as penerimaanreceivefrom,  ");
		sqlBuilder.append("mpenerimaan.keterangan as penerimaanketerangan, ");
		sqlBuilder.append("dpenerimaan.amount as penerimaanamount, dpenerimaan.penyesuaian as penyesuaianamount,  ");
		sqlBuilder.append("dpenerimaan.nilaijasa as penerimaannilaijasa, dpenerimaan.nilaireimbursement as penerimaannilaireimbursement, dpenerimaan.nilaibuktipotong as penerimaannilaibuktipotong,  ");
		sqlBuilder.append("dpenerimaan.nobuktipotong as penerimaannobuktipotong, dpenerimaan.tanggalbuktipotong as penerimaantanggalbuktipotong, dpenerimaan.nilaippn as penerimaannilaippn,  ");
		sqlBuilder.append("mpenerimaan.idreceivetype as idreceivetype, cust.customername as customername , emp.nama as employeeName, vendor.nama as vendorname, ");
		sqlBuilder.append("mbank.namabank as namabank, mbank.norekening as banknorekening ");
		sqlBuilder.append("from detail_penerimaan_kas_bank as dpenerimaan ");
		sqlBuilder.append("left join m_penerimaan_kas_bank as mpenerimaan on mpenerimaan.id = dpenerimaan.idpenerimaankasbank ");
		sqlBuilder.append("left join m_coa as coapenerimaan on coapenerimaan.id = dpenerimaan.idcoa ");
		sqlBuilder.append("left join m_workorder as workorderpenerimaan on workorderpenerimaan.id = dpenerimaan.idworkorder ");
		sqlBuilder.append("left join m_invoice as invoicepenerimaan on invoicepenerimaan.id = dpenerimaan.idinvoice ");
		sqlBuilder.append("left join m_workorder as workorderinv on workorderinv.id = invoicepenerimaan.idwo ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = mpenerimaan.idcustomer ");
		sqlBuilder.append("left join m_employee_manggala as emp on emp.id = mpenerimaan.idemployee ");
		sqlBuilder.append("left join m_vendor as vendor on vendor.id = mpenerimaan.idvendor ");
		sqlBuilder.append("left join m_bank_account as mbank on mpenerimaan.idbank = mbank.id ");

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
		
		final Long penerimaanid = rs.getLong("penerimaanid");
//		final Long pengeluaranid = rs.getLong("pengeluaranid");
		
		final Date penerimaantanggaltransaksi = rs.getDate("penerimaantanggaltransaksi");
//		final Date pengeluarantanggaltransaksi = rs.getDate("pengeluarantanggaltransaksi");
		
		final String penerimaannodocument = rs.getString("penerimaannodocument");
//		final String pengeluarannodocument = rs.getString("pengeluarannodocument");
		
		final String penerimaancoanama = rs.getString("penerimaancoanama");
		final String penerimaancoacode = rs.getString("penerimaancoacode");

//		final String pengeluarancoanama = rs.getString("pengeluarancoanama");

		final String penerimaannodocumentwo = rs.getString("penerimaannodocumentwo");
		final String penerimaannodocumentwoinv = rs.getString("penerimaannodocumentwoinv");
//		final String pengeluarannodocumentwo = rs.getString("pengeluarannodocumentwo");
		
		final String penerimaannoaju = rs.getString("penerimaannoaju");
		final String penerimaannoajuinv = rs.getString("penerimaannoajuinv");
//		final String pengeluarannoaju = rs.getString("pengeluarannoaju");
		
		final String penerimaannodocumentinv = rs.getString("penerimaannodocumentinv");
		final String pengeluarannodocumentinv = "";
		
		final String penerimaanreceivefrom = rs.getString("penerimaanreceivefrom");
		
//		final String pengeluaranpaymentto = rs.getString("pengeluaranpaymentto");
//		final String pengeluarancustomername = rs.getString("pengeluarancustomername");
//		final String pengeluaranempname = rs.getString("pengeluaranempname");
//		final String pengeluaranvendorname = rs.getString("pengeluaranvendorname");
		
		final String penerimaanketerangan = rs.getString("penerimaanketerangan");
//		final String pengeluaranketerangan = rs.getString("pengeluaranketerangan");
		
		final Double penerimaanamount = rs.getDouble("penerimaanamount");
		final Double penyesuaianamount = rs.getDouble("penyesuaianamount");
		final String customername = rs.getString("customername");
		final String idreceivetype = rs.getString("idreceivetype");
		final String employeeName = rs.getString("employeeName");
		final String vendorname = rs.getString("vendorname");

		final Double penerimaannilaijasa = rs.getDouble("penerimaannilaijasa");
		final Double penerimaannilaireimbursement = rs.getDouble("penerimaannilaireimbursement");
		final Double penerimaannilaibuktipotong = rs.getDouble("penerimaannilaibuktipotong");
		final String penerimaannobuktipotong = rs.getString("penerimaannobuktipotong");
		final Date penerimaantanggalbuktipotong = rs.getDate("penerimaantanggalbuktipotong");
		final Double penerimaannilaippn = rs.getDouble("penerimaannilaippn");
		final String namabank = rs.getString("namabank");
		final String banknorekening = rs.getString("banknorekening");

//		final Double pengeluaranamount = rs.getDouble("pengeluaranamount");

		
		EntityHelperKasBank data = new EntityHelperKasBank();
		data.setPenerimaanid(penerimaanid);
		data.setPenerimaantanggalTransaksi(penerimaantanggaltransaksi);
		data.setPenerimaannoVoucher(penerimaannodocument);
		data.setPenerimaancoa(penerimaancoanama);
		data.setPenerimaannoWO(penerimaannodocumentwo != null?penerimaannodocumentwo:penerimaannodocumentwoinv);
		data.setPenerimaannoAju(penerimaannoaju != null?penerimaannoaju:penerimaannoajuinv);
		data.setPenerimaannoInvoice(penerimaannodocumentinv);
		data.setPenerimaannamaCustomer(customername);
		data.setPenerimaanIdReceiveType(idreceivetype);
		data.setPenerimaanEmployeename(employeeName);
		data.setPenerimaanVendorname(vendorname);
		data.setPenerimaanketerangan(penerimaanketerangan);
		data.setPenerimaanAmount(penerimaanamount);
		data.setPenerimaanPenyesuain(penyesuaianamount);
		data.setPenerimaancoaCode(penerimaancoacode != null?penerimaancoacode:"");
		data.setPenerimaannilaijasa(penerimaannilaijasa);
		data.setPenerimaannilaireimbursement(penerimaannilaireimbursement);
		data.setPenerimaannilaibuktipotong(penerimaannilaibuktipotong);
		data.setPenerimaannobuktipotong(penerimaannobuktipotong);
		data.setPenerimaantanggalbuktipotong(penerimaantanggalbuktipotong);
		data.setPenerimaannilaippn(penerimaannilaippn);
		data.setPenerimaannamabank(namabank);
		data.setPenerimaanbanknorek(banknorekening);
		
		data.setPengeluaranid(0L);
		data.setPengeluarantanggalTransaksi(null);
		data.setPengeluarannoVoucher(null);
		data.setPengeluarancoa(null);
		data.setPengeluarannoWO(null);
		data.setPengeluarannoAju(null);
		data.setPengeluarannoInvoice("");
		data.setPengeluaran_paymentto(null);
		data.setPengeluaran_customername(null);
		data.setPengeluaran_vendorname(null);
		data.setPengeluaran_employeename(null);
		data.setPengeluaranAmount(0.0);
		data.setPengeluarannamabank("");
		data.setPengeluaranbanknorek("");
		
		return data;
	}

}
