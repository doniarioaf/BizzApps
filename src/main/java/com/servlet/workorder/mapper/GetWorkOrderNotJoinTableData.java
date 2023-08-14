package com.servlet.workorder.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.workorder.entity.WorkOrderData;

public class GetWorkOrderNotJoinTableData implements RowMapper<WorkOrderData>{
	private String schemaSql;
	
	public GetWorkOrderNotJoinTableData() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal, data.idcustomer as idcustomer,data.namacargo as namacargo, data.isactive as isactive, ");
		sqlBuilder.append("data.status as status, data.jeniswo as jeniswo, data.modatransportasi as modatransportasi, data.etd as etd,data.eta as eta, ");
		sqlBuilder.append("data.portasal as portasal, data.porttujuan as porttujuan, data.jalur as jalur, data.noaju as noaju, data.nopen as nopen, data.tanggalnopen as tanggalnopen, ");
		sqlBuilder.append("data.nobl as nobl, data.tanggalbl as tanggalbl, data.pelayaran as pelayaran, data.importir as importir, data.eksportir as eksportir, data.qq as qq, ");
		sqlBuilder.append("data.voyagenumber as voyagenumber, data.tanggalsppb_npe as tanggalsppb_npe, data.depo as depo, data.invoiceno as invoiceno, data.idvendordepo as idvendordepo ");
		sqlBuilder.append("from m_workorder as data ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public WorkOrderData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Date tanggal = rs.getDate("tanggal");
		final Long idcustomer = rs.getLong("idcustomer");
		final String namacargo = rs.getString("namacargo");
		final boolean isactive = rs.getBoolean("isactive");
		final String status = rs.getString("status");
		final String jeniswo = rs.getString("jeniswo");
		final String modatransportasi = rs.getString("modatransportasi");
		final Date etd = rs.getDate("etd");
		final Date eta = rs.getDate("eta");
		final Long portasal = rs.getLong("portasal");
		final Long porttujuan = rs.getLong("porttujuan");
		final String jalur = rs.getString("jalur");
		final String noaju = rs.getString("noaju");
		final String nopen = rs.getString("nopen");
		final Date tanggalnopen = rs.getDate("tanggalnopen");
		final String nobl = rs.getString("nobl");
		final Date tanggalbl = rs.getDate("tanggalbl");
		final Long pelayaran = rs.getLong("pelayaran");
		final Long importir = rs.getLong("importir");
		final Long eksportir = rs.getLong("eksportir");
		final Long qq = rs.getLong("qq");
		final String voyagenumber = rs.getString("voyagenumber");
		final Date tanggalsppb_npe = rs.getDate("tanggalsppb_npe");
		final String depo = rs.getString("depo");
		final String invoiceno = rs.getString("invoiceno");
		final Long idvendordepo = rs.getLong("idvendordepo");
		
		
		WorkOrderData data = new WorkOrderData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setIdcustomer(idcustomer);
		data.setNamacargo(namacargo);
		data.setIsactive(isactive);
		data.setStatus(status);
		data.setJeniswo(jeniswo);
		data.setModatransportasi(modatransportasi);
		data.setEtd(etd);
		data.setEta(eta);
		data.setPortasal(portasal);
		data.setPorttujuan(porttujuan);
		data.setJalur(jalur);
		data.setNoaju(noaju);
		data.setNopen(nopen);
		data.setTanggalnopen(tanggalnopen);
		data.setNobl(nobl);
		data.setTanggalbl(tanggalbl);
		data.setPelayaran(pelayaran);
		data.setImportir(importir);
		data.setEksportir(eksportir);
		data.setQq(qq);
		data.setVoyagenumber(voyagenumber);
		data.setTanggalsppb_npe(tanggalsppb_npe);
		data.setDepo(depo);
		data.setInvoiceno(invoiceno);
		data.setIdvendordepo(idvendordepo);
		return data;
	}

}
