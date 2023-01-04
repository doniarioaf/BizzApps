package com.servlet.pengluarankasbank.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.pengluarankasbank.entity.PengeluaranKasBankData;

public class GetPengeluaranKasBankData implements RowMapper<PengeluaranKasBankData>{
	private String schemaSql;
	
	public GetPengeluaranKasBankData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.paymentdate as paymentdate, data.paymentto as paymentto, data.idcoa as idcoa, ");
		sqlBuilder.append("data.idbank as idbank, data.keterangan as keterangan, data.isactive as isactive,data.idwo as idwo, data.idcustomer as idcustomer, data.idvendor as idvendor, data.idemployee as idemployee, ");
		sqlBuilder.append("cust.customername as customername, emp.nama as empnama, vendor.nama as vendornama ");
		sqlBuilder.append("from m_pengeluaran_kas_bank as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		sqlBuilder.append("left join m_employee_manggala as emp on emp.id = data.idemployee ");
		sqlBuilder.append("left join m_vendor as vendor on vendor.id = data.idvendor ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}


	@Override
	public PengeluaranKasBankData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Date paymentdate = rs.getDate("paymentdate");
		final String paymentto = rs.getString("paymentto");
		final Long idcoa = rs.getLong("idcoa");
		final Long idbank = rs.getLong("idbank");
		final String keterangan = rs.getString("keterangan");
		final boolean isactive = rs.getBoolean("isactive");
		final Long idwo = rs.getLong("idwo");
		final Long idcustomer = rs.getLong("idcustomer");
		final String customername = rs.getString("customername");
		final Long idvendor = rs.getLong("idvendor");
		final String vendornama = rs.getString("vendornama");
		final Long idemployee = rs.getLong("idemployee");
		final String empnama = rs.getString("empnama");
		
		PengeluaranKasBankData data = new PengeluaranKasBankData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setPaymentdate(paymentdate);
		data.setPaymentto(paymentto);
		data.setIdcoa(idcoa);
		data.setIdbank(idbank);
		data.setKeterangan(keterangan);
		data.setIsactive(isactive);
		data.setIdwo(idwo);
		data.setIdcustomer(idcustomer);
		data.setCustomerName(customername);
		data.setIdvendor(idvendor);
		data.setVendorName(vendornama);
		data.setIdemployee(idemployee);
		data.setEmployeeName(empnama);
		return data;
	}

}
