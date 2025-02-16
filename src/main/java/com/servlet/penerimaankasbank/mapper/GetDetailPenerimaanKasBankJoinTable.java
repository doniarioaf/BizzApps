package com.servlet.penerimaankasbank.mapper;

import java.sql.Date;
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
		sqlBuilder.append("data.idinvoice as idinvoice, data.idworkorder as idworkorder, coa.nama as coaname, coa.code as coacode, wo.nodocument as wonodocument, ");
		sqlBuilder.append("inv.nodocument as invnodocument, wo.noaju as wonoaju, data.penyesuaian as penyesuaian, data.keterangan_penyesuaian as keterangan_penyesuaian, ");
		sqlBuilder.append("data.nilaijasa as nilaijasa, data.nilaireimbursement as nilaireimbursement, data.nilaibuktipotong as nilaibuktipotong, data.nobuktipotong as nobuktipotong, data.tanggalbuktipotong as tanggalbuktipotong, data.nilaippn as nilaippn, ");
		sqlBuilder.append("inv.totalinvoice as invtotalinvoice, inv.nilaippn as invnilaippn, inv.nilaijasa as invnilaijasa, inv.nilaireimbursement as invnilaireimbursement ");
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
		final String coacode = rs.getString("coacode");
		final String wonodocument = rs.getString("wonodocument");
		final String invnodocument = rs.getString("invnodocument");
		final String wonoaju = rs.getString("wonoaju");
		final Double penyesuaian = rs.getDouble("penyesuaian");
		final String keterangan_penyesuaian = rs.getString("keterangan_penyesuaian");

		final Double nilaijasa = rs.getDouble("nilaijasa");
		final Double nilaireimbursement = rs.getDouble("nilaireimbursement");
		final Double nilaibuktipotong = rs.getDouble("nilaibuktipotong");
		final String nobuktipotong = rs.getString("nobuktipotong");
		final Date tanggalbuktipotong = rs.getDate("tanggalbuktipotong");
		final Double nilaippn = rs.getDouble("nilaippn");

		final Double invtotalinvoice = rs.getDouble("invtotalinvoice");
		final Double invnilaippn = rs.getDouble("invnilaippn");
		final Double invnilaijasa = rs.getDouble("invnilaijasa");
		final Double invnilaireimbursement = rs.getDouble("invnilaireimbursement");


		DetailPenerimaanKasBankData data = new DetailPenerimaanKasBankData();
		data.setIdcoa(idcoa);
		data.setCoaname(coaname);
		data.setCoacode(coacode);
		data.setCatatan(catatan);
		data.setAmount(amount);
		data.setIsdownpayment(isdownpayment);
		data.setIdinvoice(idinvoice);
		data.setIdworkorder(idworkorder);
		data.setNodocinvoice(invnodocument);
		data.setNodocworkorder(wonodocument);
		data.setNoaju(wonoaju);
		data.setPenyesuaian(penyesuaian);
		data.setKeterangan_penyesuaian(keterangan_penyesuaian);
		data.setNilaijasa(nilaijasa);
		data.setNilaireimbursement(nilaireimbursement);
		data.setNilaibuktipotong(nilaibuktipotong);
		data.setNobuktipotong(nobuktipotong);
		data.setTanggalbuktipotong(tanggalbuktipotong);
		data.setNilaippn(nilaippn);

		data.setInvtotalinvoice(invtotalinvoice);
		data.setInvnilaippn(invnilaippn);
		data.setInvnilaijasa(invnilaijasa);
		data.setInvnilaireimbursement(invnilaireimbursement);
		return data;
	}

}
