package com.servlet.suratjalan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.suratjalan.entity.PrintData;

public class GetPrintDataSuratJalan implements RowMapper<PrintData>{
private String schemaSql;
	
	public GetPrintDataSuratJalan() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.id as id, data.nodocument as nodocument, data.tanggal as tanggal,  ");
		sqlBuilder.append("data.idworkorder as idworkorder, data.idcustomer as idcustomer, data.keterangan as keterangan, ");
		sqlBuilder.append("data.idwarehouse as idwarehouse, data.catatan as catatan, data.nocantainer as nocantainer, data.status as status, ");
		sqlBuilder.append("wo.jeniswo as jeniswo ,wo.nodocument as nodocumentwo, wo.nobl as nobl, wo.noaju as noaju,wo.namacargo as namacargo, vendordepowo.nama as wodepo, ");
		sqlBuilder.append("cust.customername as customername,cust.customertype as customertype, paramstatus.codename as statusname, cust.alamat as custalamat,cust.alamat2 as custalamat2,cust.alamat3 as custalamat3, cust.provinsi as custprovinsi, cust.kota as custkota, cust.kodepos as custkodepos, ");
		sqlBuilder.append("warehouse.nama as warehousename, warehouse.contactnumber as contactname, warehouse.contacthp as contacthp, warehouse.alamat as alamat, warehouse.alamat2 as alamat2, warehouse.alamat3 as alamat3, warehouse.ancerancer as ancerancer, warehouse.note as warehousenote, ");
		sqlBuilder.append("partai.name as partainame, detailworkorder.jumlahkoli as jumlahkoli, detailworkorder.jumlahkg as jumlahkg, paramwotype.codename as wotypename, detailworkorder.noseal as nosealwo, ");
		sqlBuilder.append("comp.nama as compnama,comp.address as compaddress ");
		
		sqlBuilder.append("from t_surat_jalan as data ");
		sqlBuilder.append("left join m_customer_manggala as cust on cust.id = data.idcustomer ");
		sqlBuilder.append("left join m_workorder as wo on wo.id = data.idworkorder ");
		sqlBuilder.append("left join m_vendor as vendordepowo on vendordepowo.id = wo.idvendordepo ");
		sqlBuilder.append("left join m_warehouse as warehouse on warehouse.id = data.idwarehouse ");
		sqlBuilder.append("left join detail_work_order as detailworkorder on detailworkorder.idworkorder = data.idworkorder and detailworkorder.nocontainer = data.nocantainer  ");
		sqlBuilder.append("left join m_partai as partai on partai.id = detailworkorder.idpartai ");
		sqlBuilder.append("left join m_parameter as paramstatus on paramstatus.code = data.status and paramstatus.grup ='STATUS_SURATJALAN' ");
		sqlBuilder.append("left join m_parameter as paramwotype on paramwotype.code = wo.jeniswo and paramwotype.grup ='WO_TYPE' ");
		sqlBuilder.append("left join m_company as comp on comp.id = data.idcompany ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public PrintData mapRow(ResultSet rs, int rowNum) throws SQLException {
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
		final String alamat2 = rs.getString("alamat2");
		final String alamat3 = rs.getString("alamat3");
		final String partainame = rs.getString("partainame");
		final String jumlahkoli = rs.getString("jumlahkoli");
		final String jumlahkg = rs.getString("jumlahkg");
		final String status = rs.getString("status");
		final String warehousename = rs.getString("warehousename");
		final String statusname = rs.getString("statusname");
		final String custalamat = rs.getString("custalamat");
		final String custalamat2 = rs.getString("custalamat2");
		final String custalamat3 = rs.getString("custalamat3");
		final String custprovinsi = rs.getString("custprovinsi");
		final String custkota = rs.getString("custkota");
		final String custkodepos = rs.getString("custkodepos");
		final String wodepo = rs.getString("wodepo");
		final String jeniswo = rs.getString("jeniswo");
		final String wotypename = rs.getString("wotypename");
		final String ancerancer = rs.getString("ancerancer");
		final String warehousenote = rs.getString("warehousenote");
		final String nosealwo = rs.getString("nosealwo");
		final String customertype = rs.getString("customertype");
		final String compnama = rs.getString("compnama");
		final String compaddress = rs.getString("compaddress");
		
		PrintData data = new PrintData();
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
		data.setWarehouseaddress2(alamat2);
		data.setWarehouseaddress3(alamat3);
		data.setCatatan(catatan);
		data.setNocantainer(nocantainer);
		data.setContainerpartai(partainame);
		data.setContainerjumlahkoli(jumlahkoli);
		data.setContainerjumlahkg(jumlahkg);
		data.setStatus(status);
		data.setStatusname(statusname);
		data.setWarehousename(warehousename);
		data.setCustomerAddress(custalamat);
		data.setCustomerAddress2(custalamat2);
		data.setCustomerAddress3(custalamat3);
		data.setCustomerProvince(custprovinsi);
		data.setCustomerCity(custkota);
		data.setCustomerKodePos(custkodepos);
		data.setDepoWO(wodepo);
		data.setWoType(jeniswo);
		data.setWoTypeName(wotypename);
		data.setWarehouseancerancer(ancerancer);
		data.setWarehousecatatan(warehousenote);
		data.setNosealwo(nosealwo);
		data.setCustomertype(customertype);
		data.setCompanyname(compnama);
		data.setCompnama(compnama);
		data.setCompaddress(compaddress);
		return data;
	}

}
