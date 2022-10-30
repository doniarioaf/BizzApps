package com.servlet.suratjalan.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.suratjalan.entity.SuratJalanData;

public class GetDataFullSuratJalan implements RowMapper<SuratJalanData>{
	private String schemaSql;
	
	public GetDataFullSuratJalan() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal,  ");
		sqlBuilder.append("data.idworkorder as idworkorder, data.idcustomer as idcustomer, data.keterangan as keterangan, ");
		sqlBuilder.append("data.idwarehouse as idwarehouse, data.catatan as catatan, data.nocantainer as nocantainer, data.status as status, ");
		sqlBuilder.append("wo.nodocument as nodocumentwo, wo.nobl as nobl, wo.noaju as noaju,wo.namacargo as namacargo, ");
		sqlBuilder.append("cust.customername as customername, paramstatus.codename as statusname, ");
		sqlBuilder.append("warehouse.nama as warehousename, warehouse.contactnumber as contactname, warehouse.contacthp as contacthp, warehouse.alamat as alamat, ");
		sqlBuilder.append("partai.name as partainame, detailworkorder.jumlahkoli as jumlahkoli, detailworkorder.jumlahkg as jumlahkg, ");
		sqlBuilder.append("data.kepemilikanmobil as kepemilikanmobil,data.idemployee_supir as idemployee_supir, data.idasset as idasset, data.idvendormobil as idvendormobil, data.lembur as lembur, ");
		sqlBuilder.append("paramkepemilikanmobil.codename as paramkepemilikanmobilname, emp.nama as supirname, ven.nama as vendormobilname,paramlembur.codename as lemburname, data.tanggalkembali as tanggalkembali ");
		
		sqlBuilder.append("from t_surat_jalan as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idworkorder ");
		sqlBuilder.append("left join m_warehouse as warehouse on warehouse.id = data.idwarehouse ");
		sqlBuilder.append("left join detail_work_order as detailworkorder on detailworkorder.idworkorder = data.idworkorder and detailworkorder.nocontainer = data.nocantainer  ");
		sqlBuilder.append("left join m_partai as partai on partai.id = detailworkorder.idpartai ");
		sqlBuilder.append("left join m_parameter as paramstatus on paramstatus.code = data.status and paramstatus.grup ='STATUS_SURATJALAN' ");
		sqlBuilder.append("left join m_parameter as paramkepemilikanmobil on paramkepemilikanmobil.code = data.kepemilikanmobil and paramkepemilikanmobil.grup ='KEPEMILIKAN_MOBIL_SURAT_JALAN' ");
		sqlBuilder.append("left join m_employee_manggala as emp on emp.id = data.idemployee_supir ");
		sqlBuilder.append("left join m_vendor as ven on ven.id = data.idvendormobil ");
		sqlBuilder.append("left join m_parameter as paramlembur on paramlembur.code = data.lembur and paramlembur.grup ='CHOOSE_YES_NO' ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public SuratJalanData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long id = rs.getLong("id");
		final String nodocument = rs.getString("nodocument");
		final Timestamp tanggal = rs.getTimestamp("tanggal");
		final Long idworkorder = rs.getLong("idworkorder");
		final Long idcustomer = rs.getLong("idcustomer");
		final String keterangan = rs.getString("keterangan");
		final Long idwarehouse = rs.getLong("idwarehouse");
		final String catatan = rs.getString("catatan");
		final String nocantainer = rs.getString("nocantainer");
		final String nodocumentwo = rs.getString("nodocumentwo");
		final String nobl = rs.getString("nobl");
		final String noaju = rs.getString("noaju");
		final String namacargo = rs.getString("namacargo");
		final String customername = rs.getString("customername");
		final String contactname = rs.getString("contactname");
		final String contacthp = rs.getString("contacthp");
		final String alamat = rs.getString("alamat");
		final String partainame = rs.getString("partainame");
		final String jumlahkoli = rs.getString("jumlahkoli");
		final String jumlahkg = rs.getString("jumlahkg");
		final String status = rs.getString("status");
		final String warehousename = rs.getString("warehousename");
		final String statusname = rs.getString("statusname");
		final String kepemilikanmobil = rs.getString("kepemilikanmobil");
		final String paramkepemilikanmobilname = rs.getString("paramkepemilikanmobilname");
		final Long idemployee_supir = rs.getLong("idemployee_supir");
		final String supirname = rs.getString("supirname");
		final Long idvendormobil = rs.getLong("idvendormobil");
		final String vendormobilname = rs.getString("vendormobilname");
		final String lembur = rs.getString("lembur");
		final String lemburname = rs.getString("lemburname");
		final Date tanggalkembali = rs.getDate("tanggalkembali");
		
		SuratJalanData data = new SuratJalanData();
		data.setId(id);
		data.setNodocument(nodocument);
		data.setTanggal(tanggal);
		data.setIdworkorder(idworkorder);
		data.setNodocumentWO(nodocumentwo);
		data.setNoblWO(nobl);
		data.setNoajuWO(noaju);
		data.setNamacargoWO(namacargo);
		data.setIdcustomer(idcustomer);
		data.setNamacustomer(customername);
		data.setKeterangan(keterangan);
		data.setIdwarehouse(idwarehouse);
		data.setWarehousecontactname(contactname);
		data.setWarehousecontactno(contacthp);
		data.setWarehouseaddress(alamat);
		data.setCatatan(catatan);
		data.setNocantainer(nocantainer);
		data.setContainerpartai(partainame);
		data.setContainerjumlahkoli(jumlahkoli);
		data.setContainerjumlahkg(jumlahkg);
		data.setStatus(status);
		data.setStatusname(statusname);
		data.setWarehousename(warehousename);
		data.setKepemilikanmobil(kepemilikanmobil);
		data.setKepemilikanmobilname(paramkepemilikanmobilname);
		data.setIdemployee_supir(idemployee_supir);
		data.setSupirname(supirname);
		data.setIdvendormobil(idvendormobil);
		data.setVendormobilname(vendormobilname);
		data.setLembur(lembur);
		data.setLemburname(lemburname);
		data.setTanggalkembali(tanggalkembali);
		
		return data;
	}

}
