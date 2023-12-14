package com.servlet.workorder.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.servlet.workorder.entity.DetailWorkOrderData;
import com.servlet.workorder.entity.WorkOrderSuratJalan;

public class GetDetailWorkOrderJoinTableWithSuratJalan implements RowMapper<DetailWorkOrderData>{
	private String schemaSql;
	
	public GetDetailWorkOrderJoinTableWithSuratJalan() {
		// TODO Auto-generated constructor stub
		final StringBuilder sqlBuilder = new StringBuilder(400);
		sqlBuilder.append("data.idworkorder as idworkorder, data.idpartai as idpartai, data.jumlahkoli as jumlahkoli, data.jumlahkg as jumlahkg,data.nocontainer as nocontainer, data.noseal as noseal, ");
		sqlBuilder.append("data.barang as barang, partai.name as partainame, ");
		sqlBuilder.append("sj.id as idsuratjalan , sj.nodocument as nodocumentsj, sj.tanggal as tanggalsj, sj.tanggalkembali as tanggalkembalisj, sj.lembur as lembursj, emp.nama as namasupir, ");
		sqlBuilder.append("param.codename as kepemilikanmobil, asset.kepala_nopolisi as kepala_nopolisi, ");
		sqlBuilder.append("vendor.nama as vendorname, city.city_name as warehousecity, district.dis_name as warehousekecamatan  ");
		sqlBuilder.append("from detail_work_order as data ");
		sqlBuilder.append("left join m_partai as partai on partai.id = data.idpartai ");
		sqlBuilder.append("left join t_surat_jalan as sj on sj.idworkorder = data.idworkorder and sj.nocantainer = data.nocontainer ");
		sqlBuilder.append("left join m_employee_manggala as emp on emp.id = sj.idemployee_supir ");
		sqlBuilder.append("left join m_parameter as param on param.code = sj.kepemilikanmobil and param.grup = 'KEPEMILIKAN_MOBIL_SURAT_JALAN' ");
		sqlBuilder.append("left join m_asset as asset on asset.id = sj.idasset ");
		sqlBuilder.append("left join m_vendor as vendor on vendor.id = sj.idvendormobil ");
		sqlBuilder.append("left join m_warehouse as warehouse on warehouse.id = sj.idwarehouse ");
		sqlBuilder.append("left join cities as city on city.city_id = CAST (warehouse.city AS INTEGER)  ");
		sqlBuilder.append("left join districts as district on district.dis_id = CAST (warehouse.kecamatan AS INTEGER) ");
		
		this.schemaSql = sqlBuilder.toString();
	}

	public String schema() {
		return this.schemaSql;
	}
	
	@Override
	public DetailWorkOrderData mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		final Long idworkorder = rs.getLong("idworkorder");
		final Long idpartai = rs.getLong("idpartai");
		final String jumlahkoli = rs.getString("jumlahkoli");
		final String jumlahkg = rs.getString("jumlahkg");
		final String nocontainer = rs.getString("nocontainer");
		final String noseal = rs.getString("noseal");
		final String barang = rs.getString("barang");
		final String partainame = rs.getString("partainame");
		final String vendorname = rs.getString("vendorname");
		final String warehousecity = rs.getString("warehousecity");
		final String warehousekecamatan = rs.getString("warehousekecamatan");
		
		final Long idsuratjalan = rs.getLong("idsuratjalan");
		final String nodocumentsj = rs.getString("nodocumentsj");
		final Timestamp tanggalsj = rs.getTimestamp("tanggalsj");
		final Date tanggalkembalisj = rs.getDate("tanggalkembalisj");
		final String lembursj = rs.getString("lembursj");
		final String namasupir = rs.getString("namasupir");
		final String kepemilikanmobil = rs.getString("kepemilikanmobil");
		final String kepala_nopolisi = rs.getString("kepala_nopolisi");
		
		
		WorkOrderSuratJalan woSuratJalan = new WorkOrderSuratJalan();
		woSuratJalan.setIdSuratJalan(idsuratjalan);
		woSuratJalan.setNodocument(nodocumentsj);
		woSuratJalan.setTanggal(tanggalsj);
		woSuratJalan.setTanggalkembali(tanggalkembalisj);
		woSuratJalan.setLembur(lembursj);
		woSuratJalan.setNamaSupir(namasupir);
		woSuratJalan.setKepemilikanmobil(kepemilikanmobil);
		woSuratJalan.setNoPolisi(kepala_nopolisi);
		woSuratJalan.setVendormobilname(vendorname);
		woSuratJalan.setWarehouseCity(warehousecity);
		woSuratJalan.setWarehouseKecamatan(warehousekecamatan);
		
		DetailWorkOrderData data = new DetailWorkOrderData();
		data.setIdworkorder(idworkorder);
		data.setIdpartai(idpartai);
		data.setJumlahkoli(jumlahkoli);
		data.setJumlahkg(jumlahkg);
		data.setNocontainer(nocontainer);
		data.setNoseal(noseal);
		data.setBarang(barang);
		data.setPartainame(partainame);
		data.setWoSuratJalan(woSuratJalan);
	
		return data;
	}

}
